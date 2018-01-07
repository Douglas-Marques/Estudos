package dornel.com.pokedex.controller

import com.google.gson.JsonObject
import dornel.com.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by eduar on 05/01/2018.
 */
object Reqs {
    private val service : Api

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(Api::class.java)
    }

    fun getPokemon(id: Number) : Call<JsonObject> = service.getPokemon(id)

}