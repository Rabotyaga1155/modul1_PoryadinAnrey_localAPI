package com.example.worldcinema_poryadin

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatListScreen : AppCompatActivity() {

    private val apiService:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://virtserver.swaggerhub.com/focus.lvlup2021/LEVEL_UP_MOBILE/1.0.0/") // IP Comp
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private lateinit var name1:TextView
    private lateinit var score1:TextView
    private lateinit var name2:TextView
    private lateinit var score2:TextView
    private lateinit var name3:TextView
    private lateinit var score3:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list_screen)

        name1 = findViewById(R.id.friendName1)
        score1 = findViewById(R.id.friendScore1)
        name2 = findViewById(R.id.friendName2)
        score2 = findViewById(R.id.friendScore2)
        name3 = findViewById(R.id.friendName3)
        score3 = findViewById(R.id.friendScore3)

        fetchFriends()
    }

    fun back_click(view: View) {
        val intent = Intent(this,ProfileScreen::class.java)
        startActivity(intent)
    }

    fun chat1_click(view: View) {
        val intent =Intent(this@ChatListScreen,ChatScreen::class.java)
        startActivity(intent)
    }

    private fun fetchFriends() {
        val call: Call<FriendsResponse> = apiService.getFriends()

        call.enqueue(object : Callback<FriendsResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<FriendsResponse>, response: Response<FriendsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val events: List<Friend> = response.body()!!.friends

                    if (events.isNotEmpty()) {

                        val friendId:String = events[0].id
                        val friendName1: String = events[0].name
                        val friendScore1: Double = events[0].score
                        val friendName2: String = events[1].name
                        val friendScore2: Double = events[1].score
                        val friendName3: String = events[2].name
                        val friendScore3: Double = events[2].score


                        name1.text = friendName1
                        score1.text = "Счет - $friendScore1"
                        name2.text = friendName2
                        score2.text = "Счет - $friendScore2"
                        name3.text = friendName3
                        score3.text = "Счет - $friendScore3"


                    }
                }
            }

            override fun onFailure(call: Call<FriendsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}