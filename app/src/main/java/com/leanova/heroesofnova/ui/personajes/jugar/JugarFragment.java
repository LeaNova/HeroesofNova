package com.leanova.heroesofnova.ui.personajes.jugar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.leanova.heroesofnova.R;
import com.leanova.heroesofnova.databinding.FragmentJugarBinding;

public class JugarFragment extends Fragment {
    private FragmentJugarBinding binding;
    private JugarViewModel jugarVM;

    private TabLayout tlJugar_JP;
    private ViewPager2 vp2Jugar_JP;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentJugarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        jugarVM = new ViewModelProvider(this).get(JugarViewModel.class);
        jugarVM.obtenerPersonaje(getArguments());

        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View v) {
        this.tlJugar_JP = v.findViewById(R.id.tlJugar_JP);

        this.vp2Jugar_JP = v.findViewById(R.id.vp2Jugar_JP);
        this.vp2Jugar_JP.setAdapter(new FragmentAdapter(getChildFragmentManager(), getLifecycle()));
        this.vp2Jugar_JP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tlJugar_JP.selectTab(tlJugar_JP.getTabAt(position));
            }
        });

        this.tlJugar_JP.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp2Jugar_JP.setCurrentItem(tab.getPosition());
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
                case 0: return new TabPersonajeFragment();
                case 1: return new TabArsenalFragment();
                case 2: return new TabItemFragment();
                default: return null;
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}