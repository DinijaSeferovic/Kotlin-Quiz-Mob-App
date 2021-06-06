package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

/*
data class Grupa(val naziv: String, val nazivPredmeta: String) {
}*/

data class Grupa(
        @SerializedName("id") val id: Int,
        @SerializedName("naziv") val naziv: String,
        @SerializedName ("PredmetId") val PredmetId: Int) {
}