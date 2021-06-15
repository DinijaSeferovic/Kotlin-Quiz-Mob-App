package ba.etf.rma21.projekat.data.models

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*
/*
data class Kviz(
        val naziv: String,
        val nazivPredmeta: String,
        val datumPocetka: Date,
        val datumKraj: Date,
        var datumRada: Date?,
        val trajanje: Int,
        val nazivGrupe: String,
        var osvojeniBodovi: Float?
) {

}*/

@Entity
data class Kviz(
        @PrimaryKey @SerializedName("id") val id: Int,
        @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv: String,
        @TypeConverters(Converter::class)
        @ColumnInfo(name = "datumPocetka") @SerializedName("datumPocetak") val datumPocetka: Date,
        @TypeConverters(Converter::class)
        @ColumnInfo(name = "datumKraj") @SerializedName("datumKraj") val datumKraj: Date?,
        @ColumnInfo(name = "trajanje") @SerializedName("trajanje") val trajanje: Int){
        //@ColumnInfo(name = "idKvizTaken")  val idKvizTaken: Int){


}



