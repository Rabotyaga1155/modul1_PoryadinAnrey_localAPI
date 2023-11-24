package com.example.worldcinema_poryadin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ProfileScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)
    }

    fun exit_click(view: View) {
        val intent = Intent(this,SignInScreen::class.java)
        startActivity(intent)
    }

    fun main_click(view: View) {
        val intent = Intent(this,MainScreen::class.java)
        startActivity(intent)
    }

    fun prof_coll_click(view: View) {
        val intent = Intent(this,CollectionsScreen::class.java)
        startActivity(intent)
    }

    fun obs_click(view: View) {
        val intent = Intent(this,ChatListScreen::class.java)
        startActivity(intent)
    }
}