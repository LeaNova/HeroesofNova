package com.leanova.heroesofnova.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leanova.heroesofnova.modelos.Arma;
import com.leanova.heroesofnova.modelos.Armadura;
import com.leanova.heroesofnova.modelos.Artefacto;
import com.leanova.heroesofnova.modelos.Categoria;
import com.leanova.heroesofnova.modelos.Clase;
import com.leanova.heroesofnova.modelos.Genero;
import com.leanova.heroesofnova.modelos.Grupo;
import com.leanova.heroesofnova.modelos.InvArmadura;
import com.leanova.heroesofnova.modelos.InvArma;
import com.leanova.heroesofnova.modelos.InvArtefacto;
import com.leanova.heroesofnova.modelos.InvItem;
import com.leanova.heroesofnova.modelos.Item;
import com.leanova.heroesofnova.modelos.Mochila;
import com.leanova.heroesofnova.modelos.Participante;
import com.leanova.heroesofnova.modelos.Personaje;
import com.leanova.heroesofnova.modelos.Raza;
import com.leanova.heroesofnova.modelos.Rol;
import com.leanova.heroesofnova.modelos.Seccion;
import com.leanova.heroesofnova.modelos.Tipo;
import com.leanova.heroesofnova.modelos.Usuario;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiRetrofit {
    private static final String PATH = "http://192.168.0.17:5000/";
    //private static String PATH = "";

    private static ServiceApi serviceApi;

    public static ServiceApi getServiceApi() {
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
                @Field("energiaBase") int energiaBase,
                @Field("baseAtk") int baseAtk,
                @Field("baseAtm") int baseAtm,
                @Field("baseDef") int baseDef,
                @Field("baseDfm") int baseDfm,
                @Field("baseDex") int baseDex,
                @Field("baseEva") int baseEva,
                @Field("baseCrt") int baseCrt,
                @Field("baseAcc") int baseAcc,
                @Header("Authorization") String token);

        @DELETE("raza/borrar/{id}")
        Call<ResponseBody> borrarRaza(
                @Path("id") int id,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("raza/modificar/{id}")
        Call<Raza> editarRaza(
                @Path("id") int id,
                @Field("nombre") String nombre,
                @Field("descripcion") String descripcion,
                @Field("vidaBase") int vidaBase,
                @Field("energiaBase") int energiaBase,
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

        @GET("raza/get/{id}")
        Call<Raza> obtenerRaza(
                @Path("id") int id,
                @Header("Authorization") String token);

        //CLASES
        @FormUrlEncoded
        @POST("clase/crear")
        Call<Clase> crearClase(
                @Field("nombre") String nombre,
                @Field("modVida") float modVida,
                @Field("modEnergia") float modEnergia,
                @Field("modAtk") float modAtk,
                @Field("modAtm") float modAtm,
                @Field("modDef") float modDef,
                @Field("modDfm") float modDfm,
                @Field("modDex") float modDex,
                @Field("modEva") float modEva,
                @Field("modCrt") float modCrt,
                @Field("modAcc") float modAcc,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @DELETE("clase/borrar/{id}")
        Call<ResponseBody> borrarClase(
                @Path("id") int id,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("clase/modificar/{id}")
        Call<Clase> editarClase(
                @Path("id") int id,
                @Field("nombre") String nombre,
                @Field("modVida") float modVida,
                @Field("modEnergia") float modEnergia,
                @Field("modAtk") float modAtk,
                @Field("modAtm") float modAtm,
                @Field("modDef") float modDef,
                @Field("modDfm") float modDfm,
                @Field("modDex") float modDex,
                @Field("modEva") float modEva,
                @Field("modCrt") float modCrt,
                @Field("modAcc") float modAcc,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @GET("clase/get")
        Call<ArrayList<Clase>> obtenerClases(@Header("Authorization") String token);

        @GET("clase/get/{id}")
        Call<Clase> obtenerClase(
                @Path("id") int id,
                @Header("Authorization") String token);

        //MOCHILA
        @FormUrlEncoded
        @POST("mochila/crear")
        Call<Mochila> crearMochila(
                @Field("nombre") String nombre,
                @Field("pesoMax") int pesoMax,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @DELETE("mochila/borrar/{id}")
        Call<ResponseBody> borrarMochila(
                @Path("id") int id,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("mochila/modificar/{id}")
        Call<Mochila> editarMochila(
                @Path("id") int id,
                @Field("nombre") String nombre,
                @Field("pesoMax") int pesoMax,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @GET("mochila/get")
        Call<ArrayList<Mochila>> obtenerMochilas(@Header("Authorization") String token);

        @GET("mochila/get/{id}")
        Call<Mochila> obtenerMochila(
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
                @Field("energia") int energia,
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

        @DELETE("personaje/borrar/{id}")
        Call<ResponseBody> borrarPersonaje(
                @Path("id") int id,
                @Header("Authorization") String token);

        @PUT("personaje/baja/{id}")
        Call<Personaje> baja(
                @Path("id") int id,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("personaje/finalizar/{idPersonaje}")
        Call<Personaje> finalizar(
                @Path("idPersonaje") int idPersonaje,
                @Field("nivel") int nivel,
                @Field("experiencia") int exp,
                @Field("vida") int vida,
                @Field("energia") int energia,
                @Field("ataque") int atk,
                @Field("atkMagico") int atm,
                @Field("defensa") int def,
                @Field("defMagico") int dfm,
                @Field("agilidad") int dex,
                @Field("evasion") int eva,
                @Field("critico") int crt,
                @Field("precision") int acc,
                @Field("mochilaId") int mochilaId,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("cambiar_mochila/{idPersonaje}")
        Call<Personaje> cambiarMochila(
                @Path("idPersonaje") int idPersonaje,
                @Field("mochilaId") int mochilaId,
                @Header("Authorization") String token);

        @GET("personaje/get")
        Call<ArrayList<Personaje>> obtenerPersonajes(@Header("Authorization") String token);

        @GET("personaje/get/{id}")
        Call<Personaje> obtenerPersonaje(
                @Path("id") int id,
                @Header("Authorization") String token);

        //GRUPO
        @FormUrlEncoded
        @POST("grupo/crear")
        Call<Grupo> crearGrupo(
                @Field("nombre") String nombre,
                @Field("descripcion") String descripcion,
                @Field("pass") String pass,
                @Field("disponible") boolean disponible,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("grupo/modificar/{id}")
        Call<Grupo> editarGrupo(
                @Path("id") int id,
                @Field("nombre") String nombre,
                @Field("descripcion") String descripcion,
                @Field("pass") String pass,
                @Field("disponible") boolean disponible,
                @Header("Authorization") String token);

        @DELETE("grupo/borrar/{id}")
        Call<ResponseBody> borrarGrupo(
                @Path("id") int id,
                @Header("Authorization") String token);

        @POST("grupo/baja/{id}")
        Call<Grupo> bajaGrupo(
                @Path("id") int id,
                @Header("Authorization") String token);

        @GET("grupo/get/{id}")
        Call<Grupo> obtenerId(
                @Path("id") int id,
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

        @DELETE("participante/borrar/grupo={grupoId}")
        Call<ResponseBody> salirGrupo(
                @Path("grupoId") int grupoId,
                @Header("Authorization") String token);

        @GET("participante/get/grupos")
        Call<ArrayList<Participante>> obtenerMisGrupos(@Header("Authorization") String token);

        @GET("participante/get/grupo/{id}")
        Call<ArrayList<Participante>> obtenerParticipantes(
                @Path("id") int id,
                @Header("Authorization") String token);

        //ARMAS
        @FormUrlEncoded
        @POST("arma/crear")
        Call<Arma> crearArma(
                @Field("nombre") String nombre,
                @Field("categoriaId") int categoriaId,
                @Field("danioArma") int danioArma,
                @Field("bonoArma") int bonoArma,
                @Field("bonoAtk") int bonoAtk,
                @Field("bonoAtm") int bonoAtm,
                @Field("bonoDef") int bonoDef,
                @Field("bonoDfm") int bonoDfm,
                @Field("bonoCrt") int bonoCrt,
                @Field("bonoAcc") int bonoAcc,
                @Field("modAtk") float modAtk,
                @Field("modAtm") float modAtm,
                @Field("modDef") float modDef,
                @Field("modDfm") float modDfm,
                @Field("precio") int precio,
                @Field("peso") float peso,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @DELETE("arma/borrar/{id}")
        Call<ResponseBody> borrarArma(
                @Path("id") int id,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("arma/modificar/{id}")
        Call<Arma> editarArma(
                @Path("id") int id,
                @Field("nombre") String nombre,
                @Field("danioArma") int danioArma,
                @Field("bonoArma") int bonoArma,
                @Field("bonoAtk") int bonoAtk,
                @Field("bonoAtm") int bonoAtm,
                @Field("bonoDef") int bonoDef,
                @Field("bonoDfm") int bonoDfm,
                @Field("bonoCrt") int bonoCrt,
                @Field("bonoAcc") int bonoAcc,
                @Field("modAtk") float modAtk,
                @Field("modAtm") float modAtm,
                @Field("modDef") float modDef,
                @Field("modDfm") float modDfm,
                @Field("precio") int precio,
                @Field("peso") float peso,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @GET("arma/get")
        Call<ArrayList<Arma>> obtenerArmas(@Header("Authorization") String token);

        @GET("arma/get/{id}")
        Call<Arma> obtenerArma(
                @Path("id") int id,
                @Header("Authorization") String token);

        @GET("arma/search/{nombre}")
        Call<ArrayList<Arma>> buscarArmas(
                @Path("nombre") String nombre,
                @Header("Authorization") String token);

        @GET("categoria/get")
        Call<ArrayList<Categoria>> obtenerCategorias(@Header("Authorization") String token);

        //ARMADURAS
        @FormUrlEncoded
        @POST("armadura/crear")
        Call<Armadura> crearArmadura(
                @Field("nombre") String nombre,
                @Field("bonoDef") int bonoDef,
                @Field("bonoDfm") int bonoDfm,
                @Field("bonoDex") int bonoDex,
                @Field("bonoEva")int bonoEva,
                @Field("modDef") float modDef,
                @Field("modDfm") float modDfm,
                @Field("precio") int precio,
                @Field("peso") float peso,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @DELETE("armadura/borrar/{id}")
        Call<ResponseBody> borrarArmadura(
                @Path("id") int id,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("armadura/modificar/{id}")
        Call<Armadura> editarArmadura(
                @Path("id") int id,
                @Field("nombre") String nombre,
                @Field("bonoDef") int bonoDef,
                @Field("bonoDfm") int bonoDfm,
                @Field("bonoDex") int bonoDex,
                @Field("bonoEva")int bonoEva,
                @Field("modDef") float modDef,
                @Field("modDfm") float modDfm,
                @Field("precio") int precio,
                @Field("peso") float peso,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @GET("armadura/get")
        Call<ArrayList<Armadura>> obtenerArmaduras(@Header("Authorization") String token);

        @GET("armadura/get/{id}")
        Call<Armadura> obtenerArmadura(
                @Path("id") int id,
                @Header("Authorization") String token);

        @GET("armadura/search/{nombre}")
        Call<ArrayList<Armadura>> buscarArmaduras(
                @Path("nombre") String nombre,
                @Header("Authorization") String token);

        //ITEMS
        @FormUrlEncoded
        @POST("item/crear")
        Call<Item> crearItem(
                @Field("nombre") String nombre,
                @Field("tipoId") int tipoId,
                @Field("bonoVida") int bonoVida,
                @Field("bonoEnergia") int bonoEnergia,
                @Field("bonoAtk") int bonoAtk,
                @Field("bonoAtm") int bonoAtm,
                @Field("bonoDef") int bonoDef,
                @Field("bonoDfm") int bonoDfm,
                @Field("bonoDex") int bonoDex,
                @Field("bonoEva") int bonoEva,
                @Field("bonoCrt") int bonoCrt,
                @Field("bonoAcc") int bonoAcc,
                @Field("precio") int precio,
                @Field("peso") float peso,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @DELETE("item/borrar/{id}")
        Call<ResponseBody> borrarItem(
                @Path("id") int id,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("item/modificar/{id}")
        Call<Item> editarItem(
                @Path("id") int id,
                @Field("nombre") String nombre,
                @Field("bonoVida") int bonoVida,
                @Field("bonoEnergia") int bonoEnergia,
                @Field("bonoAtk") int bonoAtk,
                @Field("bonoAtm") int bonoAtm,
                @Field("bonoDef") int bonoDef,
                @Field("bonoDfm") int bonoDfm,
                @Field("bonoDex") int bonoDex,
                @Field("bonoEva") int bonoEva,
                @Field("bonoCrt") int bonoCrt,
                @Field("bonoAcc") int bonoAcc,
                @Field("precio") int precio,
                @Field("peso") float peso,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @GET("item/get")
        Call<ArrayList<Item>> obtenerItems(@Header("Authorization") String token);

        @GET("item/get/{id}")
        Call<Item> obtenerItem(
                @Path("id") int id,
                @Header("Authorization") String token);

        @GET("tipo/get")
        Call<ArrayList<Tipo>> obtenerTipos(@Header("Authorization") String token);

        @GET("item/search/{nombre}")
        Call<ArrayList<Item>> buscarItems(
                @Path("nombre") String nombre,
                @Header("Authorization") String token);

        //ARTEFACTOS
        @FormUrlEncoded
        @POST("artefacto/crear")
        Call<Artefacto> crearArtefacto(
                @Field("nombre") String nombre,
                @Field("seccionId") int seccionId,
                @Field("bonoVida") int bonoVida,
                @Field("bonoEnergia") int bonoEnergia,
                @Field("bonoAtk") int bonoAtk,
                @Field("bonoAtm") int bonoAtm,
                @Field("bonoDef") int bonoDef,
                @Field("bonoDfm") int bonoDfm,
                @Field("bonoDex") int bonoDex,
                @Field("bonoEva") int bonoEva,
                @Field("bonoCrt") int bonoCrt,
                @Field("bonoAcc") int bonoAcc,
                @Field("precio") int precio,
                @Field("peso") float peso,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @DELETE("artefacto/borrar/{id}")
        Call<ResponseBody> borrarArtefacto(
                @Path("id") int id,
                @Header("Authorization") String token);

        @FormUrlEncoded
        @PUT("artefacto/modificar/{id}")
        Call<Artefacto> editarArtefacto(
                @Path("id") int id,
                @Field("nombre") String nombre,
                @Field("bonoVida") int bonoVida,
                @Field("bonoEnergia") int bonoEnergia,
                @Field("bonoAtk") int bonoAtk,
                @Field("bonoAtm") int bonoAtm,
                @Field("bonoDef") int bonoDef,
                @Field("bonoDfm") int bonoDfm,
                @Field("bonoDex") int bonoDex,
                @Field("bonoEva") int bonoEva,
                @Field("bonoCrt") int bonoCrt,
                @Field("bonoAcc") int bonoAcc,
                @Field("precio") int precio,
                @Field("peso") float peso,
                @Field("descripcion") String descripcion,
                @Header("Authorization") String token);

        @GET("artefacto/get")
        Call<ArrayList<Artefacto>> obtenerArtefactos(@Header("Authorization") String token);

        @GET("artefacto/get/{id}")
        Call<Artefacto> obtenerArtefacto(
                @Path("id") int id,
                @Header("Authorization") String token);

        @GET("seccion/get")
        Call<ArrayList<Seccion>> obtenerSecciones(@Header("Authorization") String token);

        @GET("artefacto/search/{nombre}")
        Call<ArrayList<Artefacto>> buscarArtefactos(
                @Path("nombre") String nombre,
                @Header("Authorization") String token);

        //INVENTARIOS
        /**Armaduras**/
        @FormUrlEncoded
        @POST("inventario/armadura/crear")
        Call<ResponseBody> agregarArmadura(
                @Field("mochilaId") int mochilaId,
                @Field("personajeId") int personajeId,
                @Field("armaduraId") int armaId,
                @Field("cantidad") int cantidad,
                @Header("Authorization") String token);

        @GET("inventario/armadura/get/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvArmadura>> obtenerMisArmaduras(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int personajeId,
                @Header("Authorization") String token);

        /**Armas**/
        @FormUrlEncoded
        @POST("inventario/arma/crear")
        Call<ResponseBody> agregarArma(
                @Field("mochilaId") int mochilaId,
                @Field("personajeId") int personajeId,
                @Field("armaId") int armaId,
                @Field("cantidad") int cantidad,
                @Header("Authorization") String token);

        @GET("inventario/arma/get/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvArma>> obtenerMisArmas(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int personajeId,
                @Header("Authorization") String token);

        /**Items**/
        @FormUrlEncoded
        @POST("inventario/item/crear")
        Call<ResponseBody> agregarItem(
                @Field("mochilaId") int mochilaId,
                @Field("personajeId") int personajeId,
                @Field("itemId") int itemId,
                @Field("cantidad") int cantidad,
                @Header("Authorization") String token);

        @PUT("inventario/item/modificar/{mochilaId}/{personajeId}/{itemId}")
        Call<InvItem> usarItem(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int personajeId,
                @Path("itemId") int itemId,
                @Header("Authorization") String token);

        @GET("inventario/item/get/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvItem>> obtenerMisItems(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int personajeId,
                @Header("Authorization") String token);

        @GET("inventario/item/get/consumibles/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvItem>> obtenerConsumibles(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int id,
                @Header("Authorization") String token);

        /**Artefacto**/
        @FormUrlEncoded
        @POST("inventario/artefacto/crear")
        Call<ResponseBody> agregarArtefacto(
                @Field("mochilaId") int mochilaId,
                @Field("personajeId") int personajeId,
                @Field("artefactoId") int artefactoId,
                @Field("cantidad") int cantidad,
                @Header("Authorization") String token);

        @GET("inventario/artefacto/get/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvArtefacto>> obtenerMisArtefactos(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int personajeId,
                @Header("Authorization") String token);

        @GET("inventario/artefacto/get/corona/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvArtefacto>> obtenerCoronas(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int id,
                @Header("Authorization") String token);

        @GET("inventario/artefacto/get/izquierda/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvArtefacto>> obtenerIzquierda(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int id,
                @Header("Authorization") String token);

        @GET("inventario/artefacto/get/derecha/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvArtefacto>> obtenerDerechas(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int id,
                @Header("Authorization") String token);

        @GET("inventario/artefacto/get/adorno/mochila={mochilaId}/personaje={personajeId}")
        Call<ArrayList<InvArtefacto>> obtenerAdornos(
                @Path("mochilaId") int mochilaId,
                @Path("personajeId") int id,
                @Header("Authorization") String token);
    }
}
