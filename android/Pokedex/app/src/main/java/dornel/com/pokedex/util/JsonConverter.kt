package dornel.com.pokedex.util

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import dornel.com.pokedex.model.Pokemon

/**
 * Created by eduar on 07/01/2018.
 */
class JsonConverter{

    companion object {
        fun convertJsonToPoke(json: JsonObject?) :Pokemon{
            if (json != null){
                val id: JsonElement = json.get("id")
                val name: JsonElement = json.get("name")
                val sprite: JsonObject = json.getAsJsonObject("sprites")
                val srcUrl: JsonElement = sprite.get("front_default")
                val types: JsonArray = json.getAsJsonArray("types")
                val firstType: String = types[0].asJsonObject.getAsJsonObject("type").get("name").asString
                if (types.size() >= 2){
                    val secondType: String = types[1].asJsonObject.getAsJsonObject("type").get("name").asString
                    return Pokemon(id.asInt, name.asString, firstType, secondType, srcUrl.asString)
                }
                return Pokemon(id.asInt, name.asString, firstType, "", srcUrl.asString)
            }
            return Pokemon(0, "erro", "erro", "erro", "")
        }
    }
}