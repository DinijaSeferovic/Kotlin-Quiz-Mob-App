package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

class Changed (
    @SerializedName("changed") val changed: Boolean,
    @SerializedName("message") val message: String)
{
}