package lt.arnas.androidtopics

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
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

        for (item in items) {
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
        generateListOfItems(items)
        setUpListView()
        addElements()
        openSecondActivity()
        setClickOpenItemDetails()
        setClickOpenSecondActivity()

    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun generateListOfItems(items: MutableList<Item>) {
        for (item in 1..10) {
            items.add(
                Item(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item)
                )
            )
        }
    }

    private fun addElements() {
        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(102, "text01", "text02"),
            Item(103, "text01", "text02"),
            Item(104, "text01", "text02"),
            Item(105, "text01", "text02"),
        )
    }

    private fun openSecondActivity() {
        binding.firstButton.setOnClickListener {
            startActivityForResult.launch(Intent(this, SecondActivity::class.java))
        }
    }

    private fun setClickOpenSecondActivity() {
        binding.firstButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val id = adapter.getMaxId().inc()
            intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, id)

            startActivityForResult.launch(intent)
        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val itemIntent = Intent(this, SecondActivity::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_OBJECT, item)

            startActivityForResult.launch(itemIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resul ->

            val item: Item?

            when (resul.resultCode) {
                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    item = getExtraFromParcelable(
                        resul.data,
                        SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT
                    )

                    if (item != null) {
                        adapter.add(item)
                    }
                }

                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    item = getExtraFromParcelable(
                        resul.data,
                        SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_OBJECT
                    )
                    adapter.update(item)
                }
            }
        }

    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_ID = "package lt.vcs.androidtopics_item_intent_object"
        const val MAIN_ACTIVITY_ITEM_INTENT_OBJECT = "package lt.vcs.androidtopics_item_intent_id"
    }
}

