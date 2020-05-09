package xuany2.washington.httpjsonparser.manager

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import xuany2.washington.httpjsonparser.model.AllArtists
import xuany2.washington.httpjsonparser.model.AllSongs
import xuany2.washington.httpjsonparser.model.User

class ApiManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun fetchSongs(onFetchReady: (AllSongs) -> Unit, onFetchError: () -> Unit) {
        val songsUrl = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json"

        val request = StringRequest(
            Request.Method.GET, songsUrl,
            { response ->
                // success
                val gson = Gson()
                val allsongs = gson.fromJson(response, AllSongs::class.java)

                onFetchReady(allsongs)
            },
            {
                // error
                onFetchError.invoke()
            }
        )
    }

    fun fetchArtists(onFetchReady: (AllArtists) -> Unit, onFetchError: () -> Unit) {
        val artistsUrl = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/allartists.json"

        val request = StringRequest(
            Request.Method.GET, artistsUrl,
            { response ->
                // success
                val gson = Gson()
                val allartists = gson.fromJson(response, AllArtists::class.java)

                onFetchReady(allartists)
            },
            {
                // error
                onFetchError.invoke()
            }
        )
    }

    fun fetchUserInfo(onFetchready: (User) -> Unit, onFetchError: () -> Unit) {
        val userUrl = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/user_info.json"

        val request = StringRequest(
            Request.Method.GET, userUrl,
            { response ->
                val gson = Gson()
                val user = gson.fromJson(response, User::class.java)

                onFetchready(user)
            },
            {
                onFetchError.invoke()
            }
        )
    }

}