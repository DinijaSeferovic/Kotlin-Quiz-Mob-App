package ba.etf.rma21.projekat.data.models

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity
data class Odgovor(
        @PrimaryKey @SerializedName("id") val id: Int,
        @ColumnInfo(name = "odgovoreno") @SerializedName("odgovoreno") val odgovoreno: Int,
        @ColumnInfo(name = "KvizTakenId") @SerializedName("KvizTakenId") val KvizTakenId: Int,
        @ColumnInfo(name = "PitanjeId") @SerializedName("PitanjeId") val PitanjeId: Int,
        @ColumnInfo(name = "KvizId")  val KvizId: Int ){
}