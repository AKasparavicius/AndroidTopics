package lt.arnas.androidtopics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var idEditText: EditText
    private lateinit var text01EditText: EditText
    private lateinit var text02EditText: EditText
    private lateinit var closeButton: Button
    private lateinit var saveButton: Button
    private var finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        closeButton = findViewById(R.id.closeButton)
        saveButton = findViewById(R.id.saveButton)

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
        setClickListenerOfSaveButton()

    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SECOND_ACTIVITY_KEY_ITEM_ID, idEditText.text.toString())
            putString(SECOND_ACTIVITY_KEY_ITEM_TEXT01, text01EditText.text.toString())
            putString(SECOND_ACTIVITY_KEY_ITEM_TEXT02, text02EditText.text.toString())
            putInt(SECOND_ACTIVITY_ITEM_FINISH_INTENT_STATUS, finishIntentStatus)
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
            finishIntentStatus = this?.getInt(SECOND_ACTIVITY_ITEM_FINISH_INTENT_STATUS) ?: -1
        }
    }
    private fun getIntentExtra() {

        val itemId: Int = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_ID, -1)
        val itemText01 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_TEXT01) ?: ""
        val itemText02 = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_KEY_ITEM_TEXT02) ?: ""

        if(itemId >= 0){
            idEditText.setText(itemId.toString())
            text01EditText.setText(itemText01)
            text02EditText.setText(itemText02)
        } else {
            finishIntentStatus = SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW
        }
    }

    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun setClickListenerOfSaveButton() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()

            finishIntent.putExtra(SECOND_ACTIVITY_KEY_ITEM_ID, (idEditText.text.toString().toInt()))
            finishIntent.putExtra(SECOND_ACTIVITY_KEY_ITEM_TEXT01, (text01EditText.text.toString()))
            finishIntent.putExtra(SECOND_ACTIVITY_KEY_ITEM_TEXT02, (text02EditText.text.toString()))
            setResult(finishIntentStatus, finishIntent)
            finish()
        }
    }

    companion object{
        const val SECOND_ACTIVITY_KEY_ITEM_ID = "lt.arnas.androidtopics.secondactivity_item_id"
        const val SECOND_ACTIVITY_KEY_ITEM_TEXT01 = "lt.arnas.androidtopics.secondactivity_item_text01"
        const val SECOND_ACTIVITY_KEY_ITEM_TEXT02 = "lt.arnas.androidtopics.secondactivity_item_text02"
        const val SECOND_ACTIVITY_ITEM_FINISH_INTENT_STATUS =
            "lt.arnas.androidtopics.secondactivity_finish_intent_status"


        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW = 101

        const val SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE = 102
    }
}