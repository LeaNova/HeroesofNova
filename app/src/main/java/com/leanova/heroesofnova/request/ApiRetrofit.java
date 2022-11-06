package com.leanova.heroesofnova.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leanova.heroesofnova.modelos.Rol;
import com.leanova.heroesofnova.modelos.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class ApiRetrofit {
    private static final String PATH = "http://192.168.0.17:5000/";

    private static ServiceApi serviceApi;

    public static ServiceApi getServiceApi() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serviceApi = retrofit.create(ServiceApi.class);

        return serviceApi;
    }

    public static String obtenerIP() {
        return PATH;
    }

    public static String obtenerToken(Context context) {
        SharedPreferences sharedP = context.getSharedPreferences("informacion", 0);
        String token = sharedP.getString("token", "");
        return token;
    }

    public interface ServiceApi {
        @FormUrlEncoded
        @POST("usuario/login")
        Call<String> login(
                @Field("mail") String mail,
                @Field("pass") String pass
        );

        @GET("rol/get")
        Call<ArrayList<Rol>> obtenerRoles();

        @FormUrlEncoded
        @POST("usuario/signin")
        Call<Usuario> signin(
                @Field("nombre") String nombre,
                @Field("apellido") String apellido,
                @Field("mail") String mail,
                @Field("pass") String pass,
                @Field("rolId") int rolId,
                @Header("Authorization") String token
        );
    }
}
