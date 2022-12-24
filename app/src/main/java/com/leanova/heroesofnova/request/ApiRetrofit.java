package com.leanova.heroesofnova.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Genero;
import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.modelos.Rol;
import com.leanova.heroesofnova.modelos.Usuario;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiRetrofit {
    private static final String PATH = "http://192.168.0.17:5000/";
    //private static String PATH = "";

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

    /*
    public static void setIP(String ip) {
        PATH = "http://" + ip + ":5000/";
    }
    */
    /*
    public static void setIP(Context context) {
        List<InetAddress> addrs;
        String address = "";
        try{
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces){
                addrs = Collections.list(intf.getInetAddresses());
                for(InetAddress addr : addrs){
                    if(!addr.isLoopbackAddress() && addr instanceof Inet4Address){
                        address = addr.getHostAddress().toUpperCase();
                    }
                }
            }
        }catch (Exception e){
            Log.d("error", e.getMessage());
        }
        //PATH = "http://" + address + ":5000/";
    }*/

    public static String obtenerIP() {
        return PATH;
    }

    public static String obtenerToken(Context context) {
        SharedPreferences sharedP = context.getSharedPreferences("informacion", 0);
        String token = sharedP.getString("token", "");
        return token;
    }

    public interface ServiceApi {
        //LOGIN-SIGNIN
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

        //GENEROS
        @GET("genero/get")
        Call<ArrayList<Genero>> obtenerGeneros(@Header("Authorization") String token);

        //RAZAS
        @FormUrlEncoded
        @POST("raza/crear")
        Call<Raza> crearRaza(
                @Field("nombre") String nombre,
                @Field("descripcion") String descripcion,
                @Field("vidaBase") int vidaBase,
                @Field("baseAtk") int baseAtk,
                @Field("baseAtm") int baseAtm,
                @Field("baseDef") int baseDef,
                @Field("baseDfm") int baseDfm,
                @Field("baseDex") int baseDex,
                @Field("baseEva") int baseEva,
                @Field("baseCrt") int baseCrt,
                @Field("baseAcc") int baseAcc
        );

        @GET("raza/get")
        Call<ArrayList<Raza>> obtenerRazas(@Header("Authorization") String token);

        //CLASES
        @FormUrlEncoded
        @POST("clase/crear")
        Call<Clase> crearClase(
                @Field("nombre") String nombre,
                @Field("descripcion") String descripcion,
                @Field("modVida") float modVida,
                @Field("modAtk") float modAtk,
                @Field("modAtm") float modAtm,
                @Field("modDef") float modDef,
                @Field("modDfm") float modDfm,
                @Field("modDex") float modDex,
                @Field("modEva") float modEva,
                @Field("modCrt") float modCrt,
                @Field("modAcc") float modAcc,
                @Header("Authorization") String token
        );

        @GET("clase/get")
        Call<ArrayList<Clase>> obtenerClases(@Header("Authorization") String token);

        //MOCHILA
        @GET("mochila/get")
        Call<ArrayList<Mochila>> obtenerMochilas(@Header("Authorization") String token);

        //PERSONAJE
        @FormUrlEncoded
        @POST("personaje/crear")
        Call<Personaje> crearPersonaje(
                @Field("nombre") String nombre,
                @Field("razaId") int razaId,
                @Field("generoId") int generoId,
                @Field("claseId") String claseId,
                @Field("vida") int vida,
                @Field("nivel") int nivel,
                @Field("experiencia") int exp,
                @Field("ataque") int atk,
                @Field("atkMagico") int atm,
                @Field("defensa") int def,
                @Field("defMagico") int dfm,
                @Field("agilidad") int dex,
                @Field("evasion") int eva,
                @Field("critico") int crt,
                @Field("precision") int acc,
                @Field("descripcion") String descripcion,
                @Field("mochilaId") int mochilaId,
                @Field("disponible") boolean disponible,
                @Header("Authorization") String token
        );

        @GET("personaje/get")
        Call<ArrayList<Personaje>> obtenerPersonajes(@Header("Authorization") String token);
    }
}
