package xuany2.washington.httpjsonparser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_info.*
import xuany2.washington.httpjsonparser.MusicApp
import xuany2.washington.httpjsonparser.R
import xuany2.washington.httpjsonparser.model.User

class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val apiManager = (application as MusicApp).apiManager

        apiManager.fetchUserInfo(
            { info -> fillInfo(info)},
            { Toast.makeText(this, getString(R.string.userInfoFetchFailed), Toast.LENGTH_SHORT).show()})
    }

    private fun fillInfo(user : User) {
        username.text = user.username
        fullName.text = getString(R.string.fullName).format(user.firstName, user.lastName)
        if (user.hasNode) {
            hasNose.text = getString(R.string.userHasNose)
        } else {
            hasNose.text = getString(R.string.userNoNose)
        }
        platform.text = getString(R.string.platformText).format(user.platform)
        Picasso.get().load(user.profilePicURL).into(userPhoto)
        processBar.visibility = View.GONE
    }
}