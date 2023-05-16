package lt.arnas.androidtopics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    lateinit var idEditText: EditText
    lateinit var text01EditText: EditText
    lateinit var text02EditText: EditText
    lateinit var closeButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        closeButton = findViewById(R.id.closeButton)

        val id = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_ID, -1)
        val text01 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_TEXT01)
        val text02 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_TEXT02)

//        if(savedInstanceState != null){
//            with(savedInstanceState){
//                idEditText.setText(getString(SECOND_ACTIVITY_KEY_ITEM_ID))
//                text01EditText.setText(getString(SECOND_ACTIVITY_KEY_ITEM_TEXT01))
//                text02EditText.setText(getString(SECOND_ACTIVITY_KEY_ITEM_TEXT02))
//            }
//        }

        getIntentExtra()
        setClickListenerOfCloseButton()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SECOND_ACTIVITY_KEY_ITEM_ID, idEditText.text.toString())
            putString(SECOND_ACTIVITY_KEY_ITEM_TEXT01, text01EditText.text.toString())
            putString(SECOND_ACTIVITY_KEY_ITEM_TEXT02, text02EditText.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)

        with(savedInstanceState){
            idEditText.setText(this?.getString(SECOND_ACTIVITY_KEY_ITEM_ID))
            text01EditText.setText(this?.getString(SECOND_ACTIVITY_KEY_ITEM_TEXT01))
            text02EditText.setText(this?.getString(SECOND_ACTIVITY_KEY_ITEM_TEXT02))
        }
    }
    private fun getIntentExtra() {
        idEditText.setText(
            intent.getIntExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_ID, -1).toString()
        )
        text01EditText.setText(
            intent.getIntExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_TEXT01, -1).toString()
        )
        text02EditText.setText(
            intent.getIntExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_TEXT02, -1).toString()
        )
    }

    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            val finishIntent = Intent()
            finishIntent.putExtra(SECOND_ACTIVITY_KEY_ITEM_ID, idEditText.text)
            finishIntent.putExtra(SECOND_ACTIVITY_KEY_ITEM_TEXT01, text01EditText.text)
            finishIntent.putExtra(SECOND_ACTIVITY_KEY_ITEM_TEXT02, text02EditText.text)
            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }

    companion object{
        const val SECOND_ACTIVITY_KEY_ITEM_ID = "lt.arnas.androidtopics_secondactivity_item_id"
        const val SECOND_ACTIVITY_KEY_ITEM_TEXT01 = "lt.arnas.androidtopics_secondactivity_item_text01"
        const val SECOND_ACTIVITY_KEY_ITEM_TEXT02 = "lt.arnas.androidtopics_secondactivity_item_text02"
    }
}