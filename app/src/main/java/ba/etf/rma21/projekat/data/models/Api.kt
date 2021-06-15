package ba.etf.rma21.projekat.data.models;

import ba.etf.rma21.projekat.BuildConfig;
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface Api {

    @GET("/kviz")
    suspend fun getAll(
    ): Response<List<Kviz>>

    @GET("/kviz/{id}")
    suspend fun getKviz(
            @Path("id") id: Int
    ): Response<Kviz>

    @GET("/grupa/{id}/kvizovi")
    suspend fun getUpisani(
            @Path("id") id: Int
    ): Response<List<Kviz>>

    @GET("/student/{id}/kviztaken/{ktid}/odgovori")
    suspend fun getOdgovoriKviz(
            @Path("id") id: String,
            @Path("ktid") ktid: Int
    ): List<Odgovor>


    @POST("/student/{id}/kviztaken/{ktid}/odgovor")
    suspend fun postaviOdgovorKviz(
            @Path("id") hash: String,
            @Path("ktid")idKvizTaken: Int,
            @Body odgovor: OdgovorenoPitanje)

    @GET("/kviz/{id}/pitanja")
    suspend fun getPitanja(
            @Path("id") id: Int
    ): Response<List<Pitanje>>

    @GET("/kviz/{id}/grupa")
    suspend fun getGrupeZaKviz(
            @Path("id") id: Int
    ): List<Grupa>

    @GET("/predmet")
    suspend fun getPredmeti(
    ): List<Predmet>

    @GET("/grupa")
    suspend fun getGrupe(
    ): Response<List<Grupa>>

    @GET("/predmet/{id}/grupa")
    suspend fun getGrupeZaPredmet(
            @Path("id") id: Int
    ): List<Grupa>


    @POST("/grupa/{gid}/student/{id}")
    suspend fun upisiUGrupu(
            @Path("gid") gid: Int ,
            @Path("id") id: String
    ): Message

    @GET("/student/{id}/grupa")
    suspend fun getUpisaneGrupe(
            @Path("id") id: String
    ): Response<List<Grupa>>

    @GET("/student/{id}/kviztaken")
    suspend fun getPocetiKvizovi(
            @Path("id") id: String
    ): Response<List<KvizTaken>>


    @POST("/student/{id}/kviz/{kid}")
    suspend fun zapocniKviz(
            @Path("id") id: String,
            @Path("kid") kid: Int
    ): Response<KvizTaken>

    @GET ("/account/{id}/lastUpdate/?=date")
    suspend fun zadnjiUpdate (
            @Path("id") id: String,
            @Query("date") date: String
    ): Changed

}
