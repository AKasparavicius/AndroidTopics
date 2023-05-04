package lt.arnas.androidtopics

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var firstButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstButton = findViewById(R.id.firstButton)

        timber("onCreate")

        openSecondActivity()
    }

    override fun onStart() {
        super.onStart()
        timber("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        timber("onRestart")
    }

    override fun onResume() {
        super.onResume()
        timber("onResume")
    }

    override fun onStop(){
        super.onStop()
        timber("onStop")
    }

    override fun onPause() {
        super.onPause()
        timber("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        timber("onDestroy")
    }

//    override fun onRestart(){
//        super.onRestart()
//        timber("onRestart")
//    }

    fun timber(text: String){
        Timber.i(
            """
                ***************************
                * ${this.localClassName}
                * $text
                ***************************
            """.trimIndent()
        )
    }

    private fun openSecondActivity(){
        firstButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}