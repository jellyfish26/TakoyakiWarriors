package jellyfish026.trunkhackathon

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

class HomeScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.home_screen)
    }
}