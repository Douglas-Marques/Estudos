package dornel.com.pokedex.controller

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import dornel.com.pokedex.model.Pokemon


/**
 * Created by eduar on 05/01/2018.
 */

interface Api {
    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Number) : Call<JsonObject>
}