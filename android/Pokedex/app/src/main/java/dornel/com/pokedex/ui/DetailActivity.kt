package dornel.com.pokedex.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import dornel.com.pokedex.R
import dornel.com.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val poke: Pokemon = intent.getSerializableExtra("poke") as Pokemon
        Glide.with(this).load(poke.imgSrc).into(image_detail_poke)

        image_detail_poke.setOnClickListener({onBackPressed()})
    }

    override fun onBackPressed() {
        finish()
    }
}
