package lt.arnas.androidtopics

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import lt.arnas.androidtopics.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : ActivityLifeCycles() {

//    private lateinit var firstButton: Button
    private lateinit var adapter: CustomAdapter
//    private lateinit var itemListView: ListView
    private var itemIndex = -1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        firstButton = findViewById(R.id.firstButton)
//        itemListView = findViewById(R.id.itemListView)

        val items = mutableListOf<Item>()
        generateListOfItems(items)

        for (item in items){
            Timber.i(item.toString())
        }

//        adapter = CustomAdapter(this)
//        adapter.add(items)
//        adapter.add(Item(101, "text01", "text02"))
//        adapter.add(
//            Item(102, "text01", "text02"),
//            Item(103, "text01", "text02"),
//            Item(104, "text01", "text02"),
//            Item(105, "text01", "text02"),
//        )
//        itemListView.adapter = adapter
        setUpListView()
        openSecondActivity()
        setClickOpenItemDetails()

    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter

        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(102, "text01", "text02"),
            Item(103, "text01", "text02"),
            Item(104, "text01", "text02"),
            Item(105, "text01", "text02"),
        )
    }


    private fun openSecondActivity(){
        binding.firstButton.setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)
            startActivityForResult.launch(Intent(this, SecondActivity::class.java))
        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item
            itemIndex = position

            val itemIntent = Intent(this, SecondActivity::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_KEY_ITEM_ID, item.id)
            itemIntent.putExtra(MAIN_ACTIVITY_KEY_ITEM_TEXT01, item.text01)
            itemIntent.putExtra(MAIN_ACTIVITY_KEY_ITEM_TEXT02, item.text02)

//            startActivity(itemIntent)
            startActivityForResult.launch(itemIntent)
        }
    }

    private fun generateListOfItems(items: MutableList<Item>) {
        for (item in 1..10){
            items.add(
                Item(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item))
            )
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            when(result.resultCode){
                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    val item = Item(
                        id = 111,
                        text01 = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_KEY_ITEM_TEXT01) ?: "",
                        text02 = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_KEY_ITEM_TEXT02) ?: ""
                    )
                    adapter.add(item)
                }
                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    val item = Item(
                        id = result.data
                            ?.getIntExtra(SecondActivity.SECOND_ACTIVITY_KEY_ITEM_ID, 0) ?: 0,
                        text01 = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_KEY_ITEM_TEXT01) ?: "",
                        text02 = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_KEY_ITEM_TEXT02) ?: ""
                    )
                    if(itemIndex >= 0) {
                        adapter.update(itemIndex, item)
                    }
                }
            }
        }
    companion object{
        const val MAIN_ACTIVITY_KEY_ITEM_ID = "lt.arnas.androidtopics_item_id"
        const val MAIN_ACTIVITY_KEY_ITEM_TEXT01 = "lt.arnas.androidtopics_item_text01"
        const val MAIN_ACTIVITY_KEY_ITEM_TEXT02 = "lt.arnas.androidtopics_item_text02"
    }

}

