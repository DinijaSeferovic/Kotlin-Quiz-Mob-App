package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName
import androidx.room.*
/*
data class Predmet(val naziv: String, val godina: Int) {
}*/

@Entity
data class Predmet(
        @PrimaryKey @SerializedName("id") val id: Int,
        @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv: String,
        @ColumnInfo(name = "godina") @SerializedName("godina") val godina: Int) {
}
