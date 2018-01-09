package dornel.com.pokedex.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by eduar on 04/01/2018.
 */
data class Pokemon(val id: Int, val name: String, val first_type: String, val second_type: String?, val img_src: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(first_type)
        parcel.writeString(second_type)
        parcel.writeString(img_src)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}