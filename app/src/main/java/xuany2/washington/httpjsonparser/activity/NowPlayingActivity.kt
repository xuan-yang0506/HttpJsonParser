package xuany2.washington.httpjsonparser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_now_playing.*
import xuany2.washington.httpjsonparser.MusicApp
import xuany2.washington.httpjsonparser.R
import xuany2.washington.httpjsonparser.manager.MusicManager
import xuany2.washington.httpjsonparser.model.Song

class NowPlayingActivity : AppCompatActivity() {

    lateinit var musicManager: MusicManager
    lateinit var currentSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_playing)

        musicManager = (application as MusicApp).musicManager

        updateSong()

        btnPlay.setOnClickListener {musicManager.play()
                                    updateCount()}
        btnPrevious.setOnClickListener {musicManager.prevSong()
                                        updateSong()}
        btnNext.setOnClickListener {musicManager.nextSong()
                                    updateSong()}
    }

    private fun updateSong() {
        musicManager.getCurrentSong()?.let {
            currentSong = it
        }

        username.text = (application as MusicApp).username
        Picasso.get().load(currentSong.largeImageURL).into(album)
        songTitle.text = currentSong.title
        updateCount()
    }

    fun updateCount() {
        playedTimes.text = getString(R.string.numberPlayed).format(musicManager.getCount())
    }
}
