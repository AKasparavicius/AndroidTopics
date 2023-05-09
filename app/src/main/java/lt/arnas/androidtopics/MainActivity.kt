package lt.arnas.androidtopics

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import timber.log.Timber

class MainActivity : ActivityLifeCycles() {

    lateinit var firstButton: Button
    lateinit var adapter: CustomAdapter
    lateinit var itemListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstButton = findViewById(R.id.firstButton)
        itemListView = findViewById(R.id.itemListView)

        openSecondActivity()

        val items = mutableListOf<Item>()
        generateListOfItems(items)

        for (item in items){
            Timber.i(item.toString())
        }

        adapter = CustomAdapter(this)
        adapter.add(items)
        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(102, "text01", "text02"),
            Item(103, "text01", "text02"),
            Item(104, "text01", "text02"),
            Item(105, "text01", "text02"),
        )
        itemListView.adapter = adapter

    }


    private fun openSecondActivity(){
        firstButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
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
}