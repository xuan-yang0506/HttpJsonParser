package xuany2.washington.httpjsonparser.manager

import android.content.Context
import xuany2.washington.httpjsonparser.model.Song
import kotlin.random.Random

class MusicManager() {
    private var current: Song ?= null
    private var count: Int = 0
    private var allSongs: List<Song> = listOf()

    fun getCurrentSong(): Song? {
        return current
    }

    fun setAllSongs(songs: List<Song>): Unit {
        allSongs = songs
    }

    fun setCurrentSong(song: Song): Unit {
        this.current = song
        this.count = Random.nextInt(0, 20001)
    }

    fun nextSong(): Unit {
        current?.let { it ->
            var index = allSongs.indexOf(it)
            if (index == allSongs.size) {
                index = 0
            } else {
                index++
            }
            current = allSongs[index]
        }
        this.count = Random.nextInt(0, 20001)
    }

    fun prevSong(): Unit {
        var index = allSongs.indexOf(current)
        if (index == 0) {
            index = allSongs.size - 1
        } else {
            index--
        }
        current = allSongs[index]
        this.count = Random.nextInt(0, 20001)
    }

    fun play(): Unit {
        count++
    }

    fun getCount(): Int {
        return count
    }
}