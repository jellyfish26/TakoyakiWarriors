package jellyfish026.trunkhackathon

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.widget.Toast

class HomeScreen: Activity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.home_screen)
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