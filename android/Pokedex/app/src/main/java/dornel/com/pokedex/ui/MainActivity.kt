package dornel.com.pokedex.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.JsonObject
import dornel.com.pokedex.R
import dornel.com.pokedex.controller.Reqs
import dornel.com.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import dornel.com.pokedex.adapter.PokemonViewHolder
import kotlin.collections.ArrayList
import dornel.com.pokedex.data.PokeDao
import com.crashlytics.android.Crashlytics
import dornel.com.pokedex.adapter.PokemonAdapter
import dornel.com.pokedex.util.JsonConverter
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "MainActivity"
const val TAG_SCROLL = "OnScrollListener"

class MainActivity : AppCompatActivity() {

    private val mLayoutManager = LinearLayoutManager(this)
    var index = 0
    private val pokemons: ArrayList<Pokemon> = ArrayList()
    private val context: Context = this
    val adapter = PokemonAdapter(pokemons, context)

    private val pokeDao = PokeDao()

    //http://www.appsdeveloperblog.com/push-notifications-example-kotlin-firebase/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fabric.with(this, Crashlytics())

        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_view.layoutManager = mLayoutManager
        //recycler_view.adapter = adapter

        pokeDao.getPokemons(this)

        /*do {
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
                    pokeDao.savePoke(pokemons)
                }
            })
        } while (802 > index)*/

        //recycler_view.addOnScrollListener(OnScrollListener(mLayoutManager, adapter, pokemons, progress_lazy))
    }
}

class OnScrollListener(private val layoutManager: LinearLayoutManager, private val adapter: RecyclerView.Adapter<PokemonViewHolder>, private val dataList: ArrayList<Pokemon>, private val lazyLoad: ProgressBar) : RecyclerView.OnScrollListener() {
    private var visibleThreshold = 5
    // The current offset index of data you have loaded
    private var currentPage = 0
    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0
    // True if we are still waiting for the last set of data to load.
    private var loading = true
    // Sets the starting page index
    private var startingPageIndex = 0

    private val pokeDao = PokeDao()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++
            updateDataList(dataList)
            loading = true
        }
    }

    private fun updateDataList(dataList: ArrayList<Pokemon>) {
        if (dataList.size >= 10) {
            lazyLoad.visibility = View.VISIBLE
            Reqs.getPokemon(dataList.size + 1).enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                    Log.e(TAG_SCROLL, "Erro na requisição do pokemon. " + t.toString())
                }

                override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                    //TODO trazer mais pokemons
                }
            })
        }
    }
}