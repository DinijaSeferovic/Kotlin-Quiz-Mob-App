package ba.etf.rma21.projekat.data.models

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/*
data class Pitanje(val naziv:String, val tekst:String, val opcije:List<String>,val tacan:Int) {
}*/

@Entity
data class Pitanje(
        @PrimaryKey @SerializedName("id") val id: Int,
        @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv: String,
        @ColumnInfo(name = "tekstPitanja") @SerializedName("tekstPitanja") val tekstPitanja: String,
        @TypeConverters(Converter::class)
        @ColumnInfo(name = "opcije") @SerializedName("opcije") val opcije: List<String>,
        @ColumnInfo(name = "tacan") @SerializedName("tacan") val tacan: Int
) {
}


