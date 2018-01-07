package dornel.com.pokedex.util

import dornel.com.pokedex.model.Pokemon

/**
 * Created by eduar on 07/01/2018.
 */
class ComparePokemons {

    companion object : Comparator<Pokemon> {
        override fun compare(a: Pokemon, b: Pokemon): Int {
            return when {
                a.id > b.id -> 1
                a.id < b.id -> -1
                else -> 0
            }
        }
    }
}