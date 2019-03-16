package jellyfish026.trunkhackathon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.linecorp.linesdk.LoginDelegate
import com.linecorp.linesdk.Scope
import com.linecorp.linesdk.auth.LineAuthenticationParams
import com.linecorp.linesdk.widget.LoginButton
import java.util.*
import android.util.Log
import com.linecorp.linesdk.LineApiResponseCode
import com.linecorp.linesdk.auth.LineLoginApi

const val LINE_CHANNEL_ID = "1555729377"
const val REQUEST_CODE = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginDelegate = LoginDelegate.Factory.create()
        val loginButton = findViewById<LoginButton>(R.id.line_login_btn)
        // loginButton.setFragment(this@MainActivity)
        loginButton.setChannelId(LINE_CHANNEL_ID)
        loginButton.enableLineAppAuthentication(true);

        loginButton.setAuthenticationParams(LineAuthenticationParams.Builder()
            .scopes(Arrays.asList(Scope.PROFILE))
            .build())
        loginButton.setLoginDelegate(loginDelegate)

        loginButton.setOnClickListener { view ->
            try {
                // App-to-app login
                val loginIntent = LineLoginApi.getLoginIntent(
                    view.context,
                    LINE_CHANNEL_ID,
                    LineAuthenticationParams.Builder()
                        .scopes(Arrays.asList(Scope.PROFILE))
                        .build()
                )
                startActivityForResult(loginIntent, REQUEST_CODE)

            } catch (e: Exception) {
                Log.e("ERROR", e.toString())
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != REQUEST_CODE) {
            Log.e("ERROR", "Unsupported Request")
            return
        }

        val result = LineLoginApi.getLoginResultFromIntent(data)

        when (result.responseCode) {
            LineApiResponseCode.SUCCESS -> {
                // Access token storage, before implementation
                val accessToken = result.lineCredential!!.accessToken.tokenString
                Log.e("INFO", "Login success")
                val transitionIntent = Intent(this, HomeScreen::class.java)
                transitionIntent.putExtra("line_profile", result.lineProfile)
                transitionIntent.putExtra("line_credential", result.lineCredential)
                startActivity(transitionIntent)
            }

            LineApiResponseCode.CANCEL -> Log.e("ERROR", "LINE Login canceled by user.")

            else -> {
                Log.e("ERROR", "Login FAILED!")
                Log.e("ERROR", result.errorData.toString())
            }
        }
    }

}
