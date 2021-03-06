package dornel.com.pokedex.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import dornel.com.pokedex.R

/**
 * Created by eduar on 04/01/2018.
 */
class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textIdNumber: TextView = itemView.findViewById(R.id.text_number)
    var textName: TextView = itemView.findViewById(R.id.text_name)
    var textFirstAbility: TextView = itemView.findViewById(R.id.text_first_ability)
    var textSecondAbility: TextView = itemView.findViewById(R.id.text_second_ability)
    var imagePokemon: ImageView = itemView.findViewById(R.id.image_poke)
}