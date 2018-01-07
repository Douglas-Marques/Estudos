package dornel.com.pokedex

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.gson.JsonObject
import dornel.com.pokedex.controller.Api
import dornel.com.pokedex.controller.Reqs
import dornel.com.pokedex.model.Pokemon
import dornel.com.pokedex.util.ComparePokemons
import dornel.com.pokedex.util.JsonConverter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    private val pokemons: ArrayList<Pokemon> = ArrayList()
    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_view.layoutManager = LinearLayoutManager(this)

        val adapter = PokemonAdapter(pokemons, context)
        recycler_view.adapter = adapter

        var total = 0
        do{
            total++
            progress_loader.visibility = GONE
            Reqs.getPokemon(total).enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                    val poke: Pokemon = JsonConverter.convertJsonToPoke(response?.body())
                    pokemons.add(poke)
                    pokemons.sortBy { pokeItem -> pokeItem.id }
                    adapter.notifyDataSetChanged()
                }
            })
        }while (10 >= total)
    }

    private fun registerPokemon(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }


}
