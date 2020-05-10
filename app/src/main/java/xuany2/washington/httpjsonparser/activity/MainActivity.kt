package xuany2.washington.httpjsonparser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import xuany2.washington.httpjsonparser.MusicApp
import xuany2.washington.httpjsonparser.R
import xuany2.washington.httpjsonparser.SongListAdapter
import xuany2.washington.httpjsonparser.manager.ApiManager
import xuany2.washington.httpjsonparser.manager.MusicManager
import xuany2.washington.httpjsonparser.model.AllSongs

class MainActivity : AppCompatActivity() {
    lateinit var apiManager: ApiManager
    lateinit var musicManager: MusicManager
    var adapter: SongListAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiManager = (application as MusicApp).apiManager
        musicManager = (application as MusicApp).musicManager

        apiManager.fetchSongs(
                {all -> fillSongs(all)},
                {Toast.makeText(this, "Songs fetch failed", Toast.LENGTH_SHORT).show()})
        apiManager.fetchUserInfo(
                {user -> username.text = user.username},
                {Toast.makeText(this, "Username fetch failed", Toast.LENGTH_SHORT).show()})


    }

    fun fillSongs(allSongs: AllSongs) {
        allSongs.let {all ->
            adapter = SongListAdapter(all.songs.toMutableList())
        }
        rvSongs.adapter = adapter
    }
}