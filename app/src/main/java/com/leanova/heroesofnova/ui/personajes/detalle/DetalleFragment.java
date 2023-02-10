package com.leanova.heroesofnova.ui.personajes.detalle;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentDetalleBinding;

public class DetalleFragment extends Fragment {
    private FragmentDetalleBinding binding;
    private DetalleViewModel detalleViewModel;

    private TabLayout tlDetalle_DP;
    private ViewPager2 vp2Detalle_DP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        detalleViewModel = new ViewModelProvider(this).get(DetalleViewModel.class);
        detalleViewModel.obtenerPersonaje(getArguments());

        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View v) {
        this.tlDetalle_DP = v.findViewById(R.id.tlDetalle_JP);

        this.vp2Detalle_DP = v.findViewById(R.id.vp2Detalle_DP);
        this.vp2Detalle_DP.setAdapter(new FragmentAdapter(getChildFragmentManager(), getLifecycle()));
        this.vp2Detalle_DP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tlDetalle_DP.selectTab(tlDetalle_DP.getTabAt(position));
            }
        });

        this.tlDetalle_DP.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp2Detalle_DP.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class FragmentAdapter extends FragmentStateAdapter {

        public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position) {
                case 0: return new TabDetallePersonajeFragment();
                case 1: return new TabInventarioPersonajeFragment();
                default: return null;
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}