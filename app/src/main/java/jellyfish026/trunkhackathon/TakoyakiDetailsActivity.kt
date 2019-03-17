package jellyfish026.trunkhackathon

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.NumberFormatException


class TakoyakiDetailsActivity : AppCompatActivity() {
    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_takoyaki_details)

        Log.d("INFO", "aaa")

        textView = findViewById(R.id.takoyaki_details)
        textView!!.text = (Data.takoyakikun[Data.nowID] + "\n" +
                "HP: " + Data.HP[Data.nowID] + "\n" +
                "ATK: " + Data.ATK[Data.nowID] + "\n" +
                "DEF: " + Data.DEF[Data.nowID] + "\n" +
                "SPE: " + Data.SPE[Data.nowID] + "\n")

        val trainingButton = findViewById<Button>(R.id.materialButton2)
        trainingButton.setOnClickListener {
            Log.d("INFO", "aaa")
            val items = arrayOf("HP", "ATK", "DEF", "SPE")
            AlertDialog.Builder(this)
                .setTitle("育成する種類を選択してください")
                .setItems(items, DialogInterface.OnClickListener { dialog, which ->
                    numberInputDialog(which)
                })
                .show()
        }

        val selectButton = findViewById<Button>(R.id.materialButton)
        selectButton.setOnClickListener {
            Toast.makeText(this, "選択しました", Toast.LENGTH_SHORT).show()
        }
    }

    fun numberInputDialog(temp: Int) {
        val myedit = EditText(this)
        val dialog = AlertDialog.Builder(this)
        var inputText = "";
        dialog.setTitle("数値を入力してください")
        dialog.setView(myedit)
        dialog.setPositiveButton("OK", DialogInterface.OnClickListener {_, _ ->
            inputText = myedit.getText().toString()
            Toast.makeText(this, "$inputText と入力しました", Toast.LENGTH_SHORT).show()
            try {
                Data.returnValue = inputText.toInt()
            } catch (e: NumberFormatException) {
                Data.returnValue = 0
            }
            when (temp) {
               0 -> Data.HP[Data.nowID] += Data.returnValue
               1 -> Data.ATK[Data.nowID] += Data.returnValue
               2 -> Data.DEF[Data.nowID] += Data.returnValue
               3 -> Data.SPE[Data.nowID] += Data.returnValue
            }
            textView!!.text = (Data.takoyakikun[Data.nowID] + "\n" +
                    "HP: " + Data.HP[Data.nowID] + "\n" +
                    "ATK: " + Data.ATK[Data.nowID] + "\n" +
                    "DEF: " + Data.DEF[Data.nowID] + "\n" +
                    "SPE: " + Data.SPE[Data.nowID] + "\n")
        })
        dialog.setNegativeButton("キャンセル", null)
        dialog.show()
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