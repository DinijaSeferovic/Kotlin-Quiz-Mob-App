package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName
import androidx.room.*
/*
data class Grupa(val naziv: String, val nazivPredmeta: String) {
}*/
@Entity
data class Grupa(
        @PrimaryKey @SerializedName("id") val id: Int,
        @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv: String,
        @ColumnInfo(name = "PredmetId") @SerializedName ("PredmetId") val PredmetId: Int) {
}