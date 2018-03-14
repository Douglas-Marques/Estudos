package dornel.com.pokedex.model
import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class Pokemon : Serializable {
    var id: Int = 0
    var name: String = "Pokémon"
    var firstType: String = "Tipo 1"
    var secondType: String = ""
    var imgSrc: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"

    constructor() {
        // Construtor vazio é necessário para chamar DataSnapshot.getValue(Pokemon.class)
    }

    constructor(id: Int, name: String, firstType: String, secondType: String, imgSrc: String) {
        this.id = id
        this.name = name
        this.firstType = firstType
        this.secondType = secondType
        this.imgSrc = imgSrc
    }

    override fun toString(): String {
        return "ID: " + this.id +
                " Nome: " + this.name +
                " Tipo1: " + this.firstType +
                " Tipo2: " + this.secondType +
                " Url icone: " + this.imgSrc
    }
}