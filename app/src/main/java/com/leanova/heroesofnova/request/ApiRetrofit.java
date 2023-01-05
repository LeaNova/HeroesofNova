package com.leanova.heroesofnova.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Genero;
import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.modelos.Participante;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.modelos.Rol;
import com.leanova.heroesofnova.modelos.Usuario;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiRetrofit {
    private static final String PATH = "http://192.168.0.17:5000/";
    //private static String PATH = "";

    private static ServiceApi serviceApi;

    public static ServiceApi getServiceApi() {
        Date dateF = new Date();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .setLenient()
                .create();

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
        //LOGIN
        @FormUrlEncoded
        @POST("usuario/login")
        Call<String> login(
                @Field("usuario") String usuario,
                @Field("pass") String pass);

        @GET("rol/get")
        Call<ArrayList<Rol>> obtenerRoles();

        //SIGNIN
        @FormUrlEncoded
        @POST("usuario/signin")
        Call<Usuario> signin(
                @Field("nombre") String nombre,
                @Field("apellido") String apellido,
                @Field("mail") String mail,
                @Field("usuario") String usuario,
                @Field("pass") String pass,
                @Field("rolId") int rolId,
                @Header("Authorization") String token);

        //USUARIO
        @GET("usuario/get")
        Call<Usuario> obtener(@Header("Authorization") String token);

        @GET("usuario/check_mail/{mail}")
        Call<Usuario> obtenerMail(@Path("mail") String mail);

        @GET("usuario/check_usuario/{usuario}")
        Call<Usuario> obtenerUsuario(@Path("usuario") String usuario);

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
                @Field("baseAcc") int baseAcc,
                @Header("Authorization") String token);

        @GET("raza/get")
        Call<ArrayList<Raza>> obtenerRazas(@Header("Authorization") String token);

        @DELETE("raza/borrar/{id}")
        Call<ResponseBody> borrarRaza(
                @Path("id") int id,
                @Header("Authorization") String token);

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
                @Header("Authorization") String token);

        @GET("clase/get")
        Call<ArrayList<Clase>> obtenerClases(@Header("Authorization") String token);

        @DELETE("clase/borrar/{id}")
        Call<ResponseBody> borrarClase(
                @Path("id") int id,
                @Header("Authorization") String token);

        //MOCHILA
        @FormUrlEncoded
        @POST("mochila/crear")
        Call<Mochila> crearMochila(
                @Field("nombre") String nombre,
                @Field("descripcion") String descripcion,
                @Field("pesoMax") int pesoMax,
                @Header("Authorization") String token);

        @GET("mochila/get")
        Call<ArrayList<Mochila>> obtenerMochilas(@Header("Authorization") String token);

        @DELETE("mochila/borrar/{id}")
        Call<ResponseBody> borrarMochila(
                @Path("id") int id,
                @Header("Authorization") String token);

        //PERSONAJE
        @FormUrlEncoded
        @POST("personaje/crear")
        Call<Personaje> crearPersonaje(
                @Field("nombre") String nombre,
                @Field("razaId") int razaId,
                @Field("generoId") int generoId,
                @Field("claseId") int claseId,
                @Field("nivel") int nivel,
                @Field("experiencia") int exp,
                @Field("vida") int vida,
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
                @Header("Authorization") String token);

        @GET("personaje/get")
        Call<ArrayList<Personaje>> obtenerPersonajes(@Header("Authorization") String token);

        //GRUPO
        @FormUrlEncoded
        @POST("grupo/crear")
        Call<Grupo> crearGrupo(
                @Field("nombre") String nombre,
                @Field("pass") String pass,
                @Field("descripcion") String descripcion,
                @Field("disponible") boolean disponible,
                @Header("Authorization") String token);

        @GET("grupo/get_own")
        Call<ArrayList<Grupo>> obtenerGrupos(@Header("Authorization") String token);

        @GET("grupo/get_nombre/{nombre}")
        Call<ArrayList<Grupo>> obtenerGrupos(
                @Path("nombre") String nombre,
                @Header("Authorization") String token);

        //PARTICIPANTE
        @FormUrlEncoded
        @POST("participante/crear")
        Call<Participante> crearParticipante(
                @Field("grupoId") int grupoId,
                @Field("personajeId") int personajeId,
                @Header("Authorization") String token);

        @GET("participante/get/grupos")
        Call<ArrayList<Participante>> obtenerMisGrupos(@Header("Authorization") String token);
    }
}
