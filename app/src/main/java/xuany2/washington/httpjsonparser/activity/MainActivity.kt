package xuany2.washington.httpjsonparser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import xuany2.washington.httpjsonparser.MusicApp
import xuany2.washington.httpjsonparser.R
import xuany2.washington.httpjsonparser.SongListAdapter
import xuany2.washington.httpjsonparser.manager.ApiManager
import xuany2.washington.httpjsonparser.manager.MusicManager
import xuany2.washington.httpjsonparser.model.AllSongs
import xuany2.washington.httpjsonparser.model.Song

class MainActivity : AppCompatActivity() {
    lateinit var apiManager: ApiManager
    lateinit var musicManager: MusicManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiManager = (application as MusicApp).apiManager
        musicManager = (application as MusicApp).musicManager

        var allSongs: AllSongs ?= null
        var adapter: SongListAdapter ?= null
        apiManager.fetchSongs({all -> allSongs = all}, { Toast.makeText(this, "Fetch failed", Toast.LENGTH_SHORT).show()})
        allSongs?.let {
            adapter = SongListAdapter(it.songs.toMutableList())
        }
        rvSongs.adapter = adapter
    }
}