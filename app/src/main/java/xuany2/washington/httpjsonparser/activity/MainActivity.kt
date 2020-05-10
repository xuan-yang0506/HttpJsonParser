package xuany2.washington.httpjsonparser.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
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
    private var adapter: SongListAdapter ?= null

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

        setNowplaying()

        btnSeeAllArtists.setOnClickListener {
            val intent = Intent(this, ArtistsActivity::class.java)
            startActivity(intent)
        }

        username.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }
    }

    fun fillSongs(allSongs: AllSongs) {
        processBar.visibility = View.GONE
        allSongs.let {all ->
            adapter = SongListAdapter(all.songs.toMutableList())
        }
        adapter?.onSongClickListener = {song ->
            (application as MusicApp).currentSong = song
            setNowplaying()
        }
        rvSongs.adapter = adapter
    }

    fun setNowplaying() {
        val currentSong = (application as MusicApp).currentSong
        currentSong?.let { song ->
            nowPlayingText.text = "${song.title} - ${song.artist}"
            Picasso.get().load(song.smallImageURL).into(albumSmall)
        } ?: run {
            nowPlayingText.text = "No song is playing"
        }
    }
}