package ba.etf.rma21.projekat.data.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    val retrofit : Api = Retrofit.Builder()
            .baseUrl("https://rma21-etf.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
}