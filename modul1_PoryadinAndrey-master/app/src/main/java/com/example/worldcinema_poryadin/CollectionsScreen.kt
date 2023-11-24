package com.example.worldcinema_poryadin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
class CollectionsScreen : AppCompatActivity() {

    private var count = 0
    private val COUNT_KEY = "count_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections_screen)

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(COUNT_KEY, 0)
        }

        val intent: Intent = intent
        val collectionName: String = intent.getStringExtra("collectionName") ?: ""
        val selectedIcon: Int = intent.getIntExtra("selectedIcon", 0)

        val textView1: TextView = findViewById(R.id.text1)
        val image1: ImageView = findViewById(R.id.image1)

        val textView2: TextView = findViewById(R.id.text2)
        val image2: ImageView = findViewById(R.id.image2)

        val textView3: TextView = findViewById(R.id.text3)
        val image3: ImageView = findViewById(R.id.image3)

        when (count) {
            0 -> {
                textView1.text = collectionName
                image1.setImageResource(selectedIcon)
            }
            1 -> {
                textView2.text = collectionName
                image2.setImageResource(selectedIcon)
            }
            else -> {
                textView3.text = collectionName
                image3.setImageResource(selectedIcon)
            }
        }

        count++
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNT_KEY, count)
    }

    fun col_main_click(view: View) {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }

    fun col_profile_click(view: View) {
        val intent = Intent(this, ProfileScreen::class.java)
        startActivity(intent)
    }

    fun create_click(view: View) {
        val intent = Intent(this, CreateCollection::class.java)
        startActivity(intent)
    }
}
