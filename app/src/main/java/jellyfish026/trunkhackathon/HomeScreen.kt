package jellyfish026.trunkhackathon


import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.home_screen.*

class HomeScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, HomeFragment())
            .commit()

        navigation_view.setOnNavigationItemSelectedListener { item ->
            var message = ""
            when(item.itemId){
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, HomeFragment())
                        .commit()
                }
                R.id.action_takoyaki -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, TakoyakiFragment())
                        .commit()
                }
                R.id.action_battle -> message = "battle!!"
            }

            Toast.makeText(this,
                "$message clicked!",
                Toast.LENGTH_SHORT
            ).show()

            return@setOnNavigationItemSelectedListener true
        }
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

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event!!.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder(this).apply {
                setTitle("INFO")
                setMessage("アプリケーションを終了しますか")
                setIcon(R.drawable.ic_error_outline_black_24dp)
                setPositiveButton("OK") { _, _ ->
                    Toast.makeText(context, "アプリケーションを終了しました。", Toast.LENGTH_LONG).show()
                    moveTaskToBack(true)
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
                setNegativeButton("Cancel", null)
                show()
            }
        }
        return super.dispatchKeyEvent(event)
    }
}