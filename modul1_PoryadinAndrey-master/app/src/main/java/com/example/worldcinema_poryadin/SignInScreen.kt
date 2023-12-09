package com.example.worldcinema_poryadin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInScreen : AppCompatActivity() {

    private val apiService:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://virtserver.swaggerhub.com/focus.lvlup2021/LEVEL_UP_MOBILE/1.0.0/") // IP Comp
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


    private lateinit var password:EditText
    private lateinit var email:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_screen)

        password = findViewById(R.id.pass)
        email = findViewById(R.id.email)
    }

    fun reg_click(view: View) {
        val intent = Intent(this,SignUpScreen::class.java)
        startActivity(intent)
    }

    fun logbut_click(view: View) {
        val userEmail = email.text.toString()

        apiService.getLogins().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val username = response.body()?.user?.username

                    if (username == userEmail) {
                        val intent = Intent(this@SignInScreen, MainScreen::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignInScreen,
                            "Неверный логин",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    val errorBody = response.message()
                    Toast.makeText(
                        this@SignInScreen,
                        "Ошибка: $errorBody",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(
                    this@SignInScreen,
                    "Ошибка сети: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    }


