package jellyfish026.trunkhackathon

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView

class TakoyakiDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_takoyaki_details)

        Log.d("INFO", "aaa")

        var textView = findViewById<TextView>(R.id.takoyaki_details)
        textView.text = (Data.takoyakikun[Data.nowID] + "\n" +
                "HP: " + Data.HP[Data.nowID] + "\n" +
                "ATK: " + Data.ATK[Data.nowID] + "\n" +
                "DEF: " + Data.DEF[Data.nowID] + "\n" +
                "SPE: " + Data.SPE[Data.nowID] + "\n")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
    }
}