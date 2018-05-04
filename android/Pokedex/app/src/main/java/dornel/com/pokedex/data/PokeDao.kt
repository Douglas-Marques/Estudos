package dornel.com.pokedex.data

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.crashlytics.android.Crashlytics
import com.google.firebase.database.*
import com.google.gson.JsonObject
import dornel.com.pokedex.ui.MainActivity
import dornel.com.pokedex.adapter.PokemonAdapter
import dornel.com.pokedex.controller.Reqs
import dornel.com.pokedex.model.Pokemon
import dornel.com.pokedex.util.JsonConverter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "PokeDao"
const val FIREBASE_CHILD = "pokemons"

class PokeDao {

    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
    var index = 449

    //var recentPostsQuery = mDatabase.child(FIREBASE_CHILD).limitToFirst(10)

    fun savePoke(poke: List<Pokemon>) {
        mDatabase.child(FIREBASE_CHILD).setValue(poke)
    }

    fun getPokemons(context: Context) {
        val itemListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                addDataToList(dataSnapshot, context)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Item failed, log a message
                Log.w(TAG, "ninja loadItem:onCancelled", databaseError.toException())
                Crashlytics.log(1, TAG, databaseError.toException().toString())
            }
        }

        mDatabase.addListenerForSingleValueEvent(itemListener)
    }

    private fun addDataToList(dataSnapshot: DataSnapshot, context: Context) {
        val activity: MainActivity = context as MainActivity

        val items = dataSnapshot.children.iterator()

        val pokemons: ArrayList<Pokemon> = ArrayList()
        val adapter = PokemonAdapter(pokemons, context)
        activity.recycler_view.adapter = adapter

        activity.progress.visibility = View.VISIBLE

        //Check if current database contains any collection
        if (items.hasNext()) {

            val pokeIndex = items.next()
            val itemsIterator = pokeIndex.children.iterator()

            //check if the collection has any to do items or not
            while (itemsIterator.hasNext()) {
                //get current item
                val currentItem = itemsIterator.next()
                val pokemon = Pokemon()
                //get current data in a map
                val map = currentItem.value as HashMap<*, *>
                //key will return Firebase ID

                val id: Long = map["id"] as Long

                pokemon.name = map["name"] as String
                pokemon.firstType = map["firstType"] as String
                pokemon.secondType = map["secondType"] as String
                pokemon.imgSrc = map["imgSrc"] as String
                pokemon.id = id.toInt()

                pokemons.add(pokemon)
                adapter.notifyDataSetChanged()
            }
        }

        do {
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
                    savePoke(pokemons)
                }
            })
        } while (802 > index)

        activity.progress.visibility = View.GONE
    }
}