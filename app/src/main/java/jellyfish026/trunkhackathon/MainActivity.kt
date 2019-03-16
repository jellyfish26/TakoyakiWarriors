package jellyfish026.trunkhackathon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.linecorp.linesdk.LoginDelegate
import com.linecorp.linesdk.Scope
import com.linecorp.linesdk.auth.LineAuthenticationParams
import com.linecorp.linesdk.widget.LoginButton
import java.util.*
import android.widget.Toast
import com.linecorp.linesdk.auth.LineLoginResult
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import com.linecorp.linesdk.LoginListener

const val LINE_CHANNEL_ID = "1555729377";

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginDelegate = LoginDelegate.Factory.create()
        val loginButton = findViewById<LoginButton>(R.id.line_login_btn)
        // loginButton.setFragment(this);
        loginButton.setChannelId(LINE_CHANNEL_ID)
        loginButton.enableLineAppAuthentication(true);

        loginButton.setAuthenticationParams(LineAuthenticationParams.Builder()
            .scopes(Arrays.asList(Scope.PROFILE))
            .build())
        loginButton.setLoginDelegate(loginDelegate)
        loginButton.addLoginListener(object : LoginListener {
            override fun onLoginSuccess(result: LineLoginResult) {
                Toast.makeText(this@MainActivity, "Login success", Toast.LENGTH_SHORT).show()
            }

            override fun onLoginFailure(result: LineLoginResult?) {
                Toast.makeText(this@MainActivity, "Login failure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
