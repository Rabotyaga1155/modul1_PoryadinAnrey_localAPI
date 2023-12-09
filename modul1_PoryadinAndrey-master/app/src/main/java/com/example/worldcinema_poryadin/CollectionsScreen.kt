package com.example.worldcinema_poryadin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
class CollectionsScreen : AppCompatActivity() {



    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections_screen)




        val collectionName: String = intent.getStringExtra("collectionName") ?: ""
        val selectedIcon: Int = intent.getIntExtra("selectedIcon", 0)

        val textView1: TextView = findViewById(R.id.text1)
        val image1: ImageView = findViewById(R.id.image1)




                textView1.text = collectionName
                image1.setImageResource(selectedIcon)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

         var savedCollectionName = sharedPreferences.getString("collectionName", "")
         var savedSelectedIcon = sharedPreferences.getInt("selectedIcon", 0)



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
