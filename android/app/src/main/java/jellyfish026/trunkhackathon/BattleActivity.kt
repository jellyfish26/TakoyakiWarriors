package jellyfish026.trunkhackathon

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.util.*
import kotlin.random.Random

class BattleActivity : AppCompatActivity() {

    var my_value = 0;
    var enemy_value = 0;
    var myText: TextView? = null
    var enemyText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.battle_filed)
        Data.nowID = 0
        Data.enemy_nowID = 0
        myText = findViewById<TextView>(R.id.my_takoyaki)
        enemyText = findViewById<TextView>(R.id.enemy_takoyaki)
        myText!!.text = (Data.takoyakikun[Data.nowID] + "\n" +
                "HP: " + Data.HP[Data.nowID] + "\n" +
                "ATK: " + Data.ATK[Data.nowID] + "\n" +
                "DEF: " + Data.DEF[Data.nowID] + "\n" +
                "SPE: " + Data.SPE[Data.nowID] + "\n")

        enemyText!!.text = (Data.enemy_takoyakikun[Data.enemy_nowID] + "\n" +
                "HP: " + Data.enemy_HP[Data.enemy_nowID] + "\n" +
                "ATK: " + Data.enemy_ATK[Data.enemy_nowID] + "\n" +
                "DEF: " + Data.enemy_DEF[Data.enemy_nowID] + "\n" +
                "SPE: " + Data.enemy_SPE[Data.enemy_nowID] + "\n")

        var attackButton = findViewById<Button>(R.id.attack)
        var defenceButton = findViewById<Button>(R.id.defence)
        var specialButton = findViewById<Button>(R.id.special_attack)
        attackButton.setOnClickListener { click(0) }
        defenceButton.setOnClickListener { click(1) }
        specialButton.setOnClickListener { click(2) }
    }

    fun click(temp: Int) {
        my_value = temp
        enemy_value = Random.nextInt(3)
        val ans = (my_value - enemy_value + 3) % 3
        Log.d("win-lose", ans.toString())
        if (ans == 2) {
            AlertDialog.Builder(this).apply {
                setTitle("勝ちました")
                setMessage("相手に攻撃を与えます")
                setIcon(R.drawable.ic_error_outline_black_24dp)
                setPositiveButton("OK") { _, _ ->
                    if (my_value == 0) {
                        Data.enemy_HP[Data.enemy_nowID] -= Data.ATK[Data.nowID]
                    } else if (my_value == 1) {
                        Data.enemy_HP[Data.enemy_nowID] -= Data.DEF[Data.nowID]
                    } else {
                        Data.enemy_HP[Data.enemy_nowID] -= Data.SPE[Data.nowID]
                    }
                    if (Data.enemy_HP[Data.enemy_nowID] <= 0) {
                        ++Data.enemy_nowID;
                        AlertDialog.Builder(this@BattleActivity).apply {
                            setTitle("倒しました")
                            setMessage("たこやき一つ倒しました！")
                            setIcon(R.drawable.ic_error_outline_black_24dp)
                            setPositiveButton("OK") { _, _ ->
                            }
                            // setNegativeButton("Cancel", null)
                            show()
                        }
                        if (Data.enemy_nowID == 3) {
                            --Data.enemy_nowID;
                            AlertDialog.Builder(this@BattleActivity).apply {
                                setTitle("あなたの勝ちです")
                                setMessage("全員倒しました！！たこやきチケットプレゼント！！")
                                setIcon(R.drawable.ic_error_outline_black_24dp)
                                setPositiveButton("OK") { _, _ ->
                                    var intent = Intent(this@BattleActivity, HomeScreen::class.java)
                                    startActivity(intent)
                                }
                                // setNegativeButton("Cancel", null)
                                show()
                            }
                        }
                    }
                    myText!!.text = (Data.takoyakikun[Data.nowID] + "\n" +
                            "HP: " + Data.HP[Data.nowID] + "\n" +
                            "ATK: " + Data.ATK[Data.nowID] + "\n" +
                            "DEF: " + Data.DEF[Data.nowID] + "\n" +
                            "SPE: " + Data.SPE[Data.nowID] + "\n")

                    enemyText!!.text = (Data.enemy_takoyakikun[Data.enemy_nowID] + "\n" +
                            "HP: " + Data.enemy_HP[Data.enemy_nowID] + "\n" +
                            "ATK: " + Data.enemy_ATK[Data.enemy_nowID] + "\n" +
                            "DEF: " + Data.enemy_DEF[Data.enemy_nowID] + "\n" +
                            "SPE: " + Data.enemy_SPE[Data.enemy_nowID] + "\n")
                }
                // setNegativeButton("Cancel", null)
                show()
            }
        } else if (ans == 1) {
            AlertDialog.Builder(this).apply {
                setTitle("引き分けです")
                setMessage("なにもできません")
                setIcon(R.drawable.ic_error_outline_black_24dp)
                setPositiveButton("OK") { _, _ ->
                }
                // setNegativeButton("Cancel", null)
                show()
            }
        } else {
            AlertDialog.Builder(this).apply {
                setTitle("負けました")
                setMessage("相手から攻撃が加えられます")
                setIcon(R.drawable.ic_error_outline_black_24dp)
                setPositiveButton("OK") { _, _ ->
                    if (enemy_value == 0) {
                        Data.HP[Data.nowID] -= Data.enemy_ATK[Data.enemy_nowID]
                    } else if (my_value == 1) {
                        Data.HP[Data.nowID] -= Data.enemy_DEF[Data.enemy_nowID]
                    } else {
                        Data.HP[Data.nowID] -= Data.enemy_SPE[Data.enemy_nowID]
                    }
                    if (Data.HP[Data.nowID] <= 0) {
                        ++Data.nowID;
                        AlertDialog.Builder(this@BattleActivity).apply {
                            setTitle("倒されました")
                            setMessage("一人倒されました")
                            setIcon(R.drawable.ic_error_outline_black_24dp)
                            setPositiveButton("OK") { _, _ ->
                            }
                            // setNegativeButton("Cancel", null)
                            show()
                        }
                        if (Data.nowID == 3) {
                            --Data.nowID
                            AlertDialog.Builder(this@BattleActivity).apply {
                                setTitle("あなたの負けです")
                                setMessage("全員倒されました")
                                setIcon(R.drawable.ic_error_outline_black_24dp)
                                setPositiveButton("OK") { _, _ ->
                                    var intent = Intent(this@BattleActivity, HomeScreen::class.java)
                                    startActivity(intent)
                                }
                                // setNegativeButton("Cancel", null)
                                show()
                            }
                        }
                    }
                    myText!!.text = (Data.takoyakikun[Data.nowID] + "\n" +
                            "HP: " + Data.HP[Data.nowID] + "\n" +
                            "ATK: " + Data.ATK[Data.nowID] + "\n" +
                            "DEF: " + Data.DEF[Data.nowID] + "\n" +
                            "SPE: " + Data.SPE[Data.nowID] + "\n")

                    enemyText!!.text = (Data.enemy_takoyakikun[Data.enemy_nowID] + "\n" +
                            "HP: " + Data.enemy_HP[Data.enemy_nowID] + "\n" +
                            "ATK: " + Data.enemy_ATK[Data.enemy_nowID] + "\n" +
                            "DEF: " + Data.enemy_DEF[Data.enemy_nowID] + "\n" +
                            "SPE: " + Data.enemy_SPE[Data.enemy_nowID] + "\n")
                }
                // setNegativeButton("Cancel", null)
                show()
            }
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
        return false
    }


}