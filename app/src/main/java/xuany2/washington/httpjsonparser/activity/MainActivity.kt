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
                {Toast.makeText(this, getString(R.string.songsFetchFailed), Toast.LENGTH_SHORT).show()})
        apiManager.fetchUserInfo(
                {user -> username.text = user.username
                    (application as MusicApp).username = user.username},
                {Toast.makeText(this, getString(R.string.userNameFetchFailed), Toast.LENGTH_SHORT).show()})

        setNowplaying()

        btnSeeAllArtists.setOnClickListener {
            val intent = Intent(this, ArtistsActivity::class.java)
            startActivity(intent)
        }

        username.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }

        nowPlayingSection.setOnClickListener {
            if (musicManager.getCurrentSong() == null) {
                Toast.makeText(this, "No song is currently playing!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, NowPlayingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setNowplaying()
    }

    fun fillSongs(allSongs: AllSongs) {
        processBar.visibility = View.GONE
        allSongs.let {all ->
            adapter = SongListAdapter(all.songs.toMutableList())
        }
        adapter?.onSongClickListener = {song ->
            musicManager.setCurrentSong(song)
            setNowplaying()
        }
        rvSongs.adapter = adapter
        musicManager.setAllSongs(allSongs.songs)
    }

    fun setNowplaying() {
        val currentSong = musicManager.getCurrentSong()
        currentSong?.let { song ->
            nowPlayingText.text = getString(R.string.nowPlayingText).format(song.title, song.artist)
            Picasso.get().load(song.smallImageURL).into(albumSmall)
        } ?: run {
            nowPlayingText.text = getString(R.string.noSongPlayingText)
        }
    }
}