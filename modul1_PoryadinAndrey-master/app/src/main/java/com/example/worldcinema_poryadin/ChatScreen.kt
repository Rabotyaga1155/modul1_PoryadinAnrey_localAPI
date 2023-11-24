package com.example.worldcinema_poryadin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ChatScreen : AppCompatActivity() {

    private lateinit var messageEditText: EditText
    private lateinit var chatListView: ListView
    private lateinit var adapter: ChatAdapter
    private var messageCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)

        messageEditText = findViewById(R.id.messageEditText)
        chatListView = findViewById(R.id.chatListView)

        val messages = mutableListOf(
            ChatAdapter.ChatMessage("Завтра уже выйдет финальная серия", R.drawable.user,"Агата Петрова","18:30"),

        )
        adapter = ChatAdapter(this, messages)
        chatListView.adapter = adapter
    }

    fun sendMessage(view: View) {
        val message = messageEditText.text.toString().trim()

        if (message.isNotEmpty()) {
            adapter.addItem(message, R.drawable.ell,"Иван Иванов")
            messageEditText.text.clear()

            messageCounter++

            if (messageCounter == 2) {
                adapter.addItem("Как вам последняя серия?", R.drawable.user,"Агата Петрова")
            }

            if (messageCounter == 3) {
                adapter.addItem("Тоже так считаю!", R.drawable.secuser,"Макс Потапов")
            }
            if (messageCounter ==3) {
                adapter.addItem("Пересматривала несколько раз, очень круто снято.",R.drawable.secuser,"Макс Потапов")
            }

            chatListView.smoothScrollToPosition(adapter.count - 1)
        }
    }

    fun back_click(view: View) {
        val intent = Intent(this,ChatListScreen::class.java)
        startActivity(intent)
    }
}