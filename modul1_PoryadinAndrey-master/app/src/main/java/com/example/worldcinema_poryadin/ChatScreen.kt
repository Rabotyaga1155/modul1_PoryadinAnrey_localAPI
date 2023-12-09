package com.example.worldcinema_poryadin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatScreen : AppCompatActivity() {

    private lateinit var messageEditText: EditText
    private lateinit var chatListView: ListView
    private lateinit var adapter: ChatAdapter
    private var messageCounter = 0

    private val apiService:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://virtserver.swaggerhub.com/focus.lvlup2021/LEVEL_UP_MOBILE/1.0.0/") // IP Comp
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

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
            sendToApi(message)
        }
    }

    private fun sendToApi(message: String) {
        val userId = "507f191e810c19729de860ea"
        val mediaUri = "MEDIA_URI_IMG"

        val request = PostMessageRequest(userId, mediaUri, message)
        val call: Call<Response<Void>> = apiService.postMessage(userId, request)

        call.enqueue(object : Callback<Response<Void>> {
            override fun onResponse(call: Call<Response<Void>>, response: Response<Response<Void>>) {
                if (!response.isSuccessful) {

                    adapter.addItem(message, R.drawable.ell, "Иван Иванов")
                    messageEditText.text.clear()

                    messageCounter++

                    if (messageCounter == 2) {
                        adapter.addItem("Как вам последняя серия?", R.drawable.user, "Агата Петрова")
                    }

                    if (messageCounter == 3) {
                        adapter.addItem("Тоже так считаю!", R.drawable.secuser, "Макс Потапов")
                    }
                    if (messageCounter == 3) {
                        adapter.addItem(
                            "Пересматривала несколько раз, очень круто снято.",
                            R.drawable.secuser,
                            "Макс Потапов"
                        )
                    }

                    chatListView.smoothScrollToPosition(adapter.count - 1)
                } else {
                    Toast.makeText(this@ChatScreen, "Ошибка при отправке в API", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response<Void>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@ChatScreen, "Ошибка при отправке в API", Toast.LENGTH_SHORT).show()
            }
        })
    }




    fun back_click(view: View) {
        val intent = Intent(this,ChatListScreen::class.java)
        startActivity(intent)
    }
}