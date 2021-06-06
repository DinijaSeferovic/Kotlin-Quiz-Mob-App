package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

/*
data class Pitanje(val naziv:String, val tekst:String, val opcije:List<String>,val tacan:Int) {
}*/


data class Pitanje(
        @SerializedName("id") val id: Int,
        @SerializedName("naziv") val naziv: String,
        @SerializedName("tekstPitanja") val tekstPitanja: String,
        @SerializedName("opcije") val opcije: List<String>,
        @SerializedName("tacan") val tacan: Int) {
}
