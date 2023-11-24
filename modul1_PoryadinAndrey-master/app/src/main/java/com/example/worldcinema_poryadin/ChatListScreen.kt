package com.example.worldcinema_poryadin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChatListScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list_screen)
    }

    fun back_click(view: View) {
        val intent = Intent(this,ProfileScreen::class.java)
        startActivity(intent)
    }

    fun chat1_click(view: View) {
        val intent =Intent(this@ChatListScreen,ChatScreen::class.java)
        startActivity(intent)
    }
}