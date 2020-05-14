package xuany2.washington.httpjsonparser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_artists.*
import xuany2.washington.httpjsonparser.ArtistListAdapter
import xuany2.washington.httpjsonparser.MusicApp
import xuany2.washington.httpjsonparser.R
import xuany2.washington.httpjsonparser.model.AllArtists

class ArtistsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)

        val apiManager = (application as MusicApp).apiManager

        apiManager.fetchArtists(
            { all -> fillArtists(all)},
            { Toast.makeText(this, getString(R.string.artistsFetchFailed), Toast.LENGTH_SHORT).show()}
        )
    }

    fun fillArtists(all: AllArtists) {
        all.let {
            val adapter = ArtistListAdapter(all.artists)
            rvArtists.adapter = adapter
        }


        processBar.visibility = View.GONE
    }
}