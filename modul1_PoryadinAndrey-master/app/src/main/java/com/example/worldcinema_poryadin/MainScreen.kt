package com.example.worldcinema_poryadin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
    }

    fun profile_click(view: View) {
        val intent = Intent(this,ProfileScreen::class.java)
        startActivity(intent)
    }

    fun collections_click(view: View) {
        val intent = Intent(this,CollectionsScreen::class.java)
        startActivity(intent)
    }
}