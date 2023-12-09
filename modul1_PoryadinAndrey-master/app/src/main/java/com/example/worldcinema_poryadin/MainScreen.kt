package com.example.worldcinema_poryadin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainScreen : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://virtserver.swaggerhub.com/focus.lvlup2021/LEVEL_UP_MOBILE/1.0.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        eventAdapter = EventAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = eventAdapter

        swipeRefreshLayout.setOnRefreshListener {

            fetchEventData()
        }

        fetchEventData()
    }

    private fun fetchEventData() {
        val call: Call<EventResponse> = apiService.getEvents()

        call.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val events: List<Event> = response.body()!!.events

                    if (events.isNotEmpty()) {
                        eventAdapter.submitList(events)
                    }
                }


                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                t.printStackTrace()
                swipeRefreshLayout.isRefreshing = false
            }
        })
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