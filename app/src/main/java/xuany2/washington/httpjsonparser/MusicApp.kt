package xuany2.washington.httpjsonparser

import android.app.Application
import xuany2.washington.httpjsonparser.manager.ApiManager
import xuany2.washington.httpjsonparser.manager.MusicManager
import xuany2.washington.httpjsonparser.model.Song

class MusicApp: Application() {

    lateinit var apiManager: ApiManager
    lateinit var musicManager: MusicManager
    lateinit var username: String


    override fun onCreate() {
        super.onCreate()

        apiManager = ApiManager(this)
        musicManager = MusicManager()
    }
}