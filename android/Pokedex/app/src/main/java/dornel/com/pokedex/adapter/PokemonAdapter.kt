package dornel.com.pokedex.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dornel.com.pokedex.R
import dornel.com.pokedex.activity.DetailActivity
import dornel.com.pokedex.model.Pokemon
import android.content.Intent
import android.view.View.VISIBLE


/**
 * Created by eduar on 04/01/2018.
 */
class PokemonAdapter(private var pokemonList: ArrayList<Pokemon>, private val context: Context) : RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.textName.text = pokemonList[position].name.capitalize()
        holder.textIdNumber.text = handleId(pokemonList[position].id.toString())
        holder.textFirstAbility.text = pokemonList[position].first_type
        if (pokemonList[position].second_type.equals("")){
            holder.textSecondAbility.visibility = GONE
        }else{
            holder.textSecondAbility.visibility = VISIBLE
            holder.textSecondAbility.text = pokemonList[position].second_type
        }
        Glide.with(context).load(pokemonList[position].img_src).into(holder.imagePokemon)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.item_pokemon, parent,false)
        val pokeViewHolder = PokemonViewHolder(inflatedView)

        inflatedView.setOnClickListener {
            goToDetailsScreen(pokemonList[(pokeViewHolder.layoutPosition)])
        }

        return pokeViewHolder
    }

    private fun handleId(id: String): String{
        return when(id.length){
            1 -> "#00" + id
            2 -> "#0" + id
            else -> "#" + id
        }
    }

    private fun goToDetailsScreen(poke: Pokemon){
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("poke", poke)
        context.startActivity(intent)
    }
}