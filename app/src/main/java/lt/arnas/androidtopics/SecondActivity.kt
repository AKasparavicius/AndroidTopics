package lt.arnas.androidtopics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    lateinit var secondActivityText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        secondActivityText = findViewById(R.id.secondActivityText)

        secondActivityText.text = "Welcome to SecondActivity!"

    }
}