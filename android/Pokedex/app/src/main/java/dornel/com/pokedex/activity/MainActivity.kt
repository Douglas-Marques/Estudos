package dornel.com.pokedex.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.JsonObject
import dornel.com.pokedex.adapter.PokemonAdapter
import dornel.com.pokedex.R
import dornel.com.pokedex.controller.Reqs
import dornel.com.pokedex.model.Pokemon
import dornel.com.pokedex.util.JsonConverter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.RecyclerView
import dornel.com.pokedex.adapter.PokemonViewHolder
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private val pokemons: ArrayList<Pokemon> = ArrayList()
    private val context: Context = this
    private val mLayoutManager = LinearLayoutManager(this)
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = PokemonAdapter(pokemons, context)

        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_view.layoutManager = mLayoutManager
        recycler_view.addOnScrollListener(OnScrollListener(mLayoutManager, adapter, pokemons))

        recycler_view.adapter = adapter
        //progress_loader.visibility = GONE

        do{
            index++
            Reqs.getPokemon(index).enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                    println("Erro na requisição " + t.toString())
                }

                override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                    val poke: Pokemon = JsonConverter.convertJsonToPoke(response?.body())
                    pokemons.add(poke)
                    pokemons.sortBy { pokeItem -> pokeItem.id }
                    adapter.notifyDataSetChanged()
                }
            })
        }while (10 > index)
    }

    private fun registerPokemon(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}

class OnScrollListener(private val layoutManager: LinearLayoutManager, private val adapter: RecyclerView.Adapter<PokemonViewHolder>, private val dataList: ArrayList<Pokemon>) : RecyclerView.OnScrollListener() {
    var previousTotal = 0
    var loading = true
    val visibleThreshold = 10
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var total = 10
    var end = 11

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            val initialSize = dataList.size
            end += 10
            updateDataList(dataList)
            val updatedSize = dataList.size
            recyclerView.post { adapter.notifyItemRangeInserted(initialSize, updatedSize) }
            loading = true
        }
    }

    fun updateDataList(dataList: ArrayList<Pokemon>) : ArrayList<Pokemon> {
        do{
            total++
            Reqs.getPokemon(total).enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                    println("Erro na requisição " + t.toString())
                }

                override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                    val poke: Pokemon = JsonConverter.convertJsonToPoke(response?.body())
                    dataList.add(poke)
                    dataList.sortBy { pokeItem -> pokeItem.id }
                    adapter.notifyDataSetChanged()
                }
            })
        }while (end > total)
        return dataList
    }
}