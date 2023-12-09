package com.example.worldcinema_poryadin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileScreen : AppCompatActivity() {

    private val REQUEST_IMAGE_PICK = 2
    private lateinit var avatarImageView: ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var name:TextView
    private lateinit var rank:TextView

    private val apiService:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://virtserver.swaggerhub.com/focus.lvlup2021/LEVEL_UP_MOBILE/1.0.0/") // IP Comp
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        avatarImageView = findViewById(R.id.avatarImageView)
        name = findViewById(R.id.nameText)
        rank = findViewById(R.id.rankText)

        val imagePath = sharedPreferences.getString("imagePath", "")
        if (imagePath != null) {
            if (imagePath.isNotEmpty()) {
                val imageBitmap = BitmapFactory.decodeFile(imagePath)
                avatarImageView.setImageBitmap(imageBitmap)
            }
        }


        apiService.getLogins().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val username = response.body()?.user?.username

                    name.text = username.toString()

                } else {
                    val errorBody = response.message()
                    Toast.makeText(
                        this@ProfileScreen,
                        "Ошибка: $errorBody",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(
                    this@ProfileScreen,
                    "Ошибка сети: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        apiService.getRank().enqueue(object : Callback<PayLoadResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PayLoadResponse>, response: Response<PayLoadResponse>) {
                if (response.isSuccessful) {
                    val ranking = response.body()?.payload?.rank

                    rank.text = "Ранг : $ranking"

                } else {
                    val errorBody = response.message()
                    Toast.makeText(
                        this@ProfileScreen,
                        "Ошибка: $errorBody",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<PayLoadResponse>, t: Throwable) {
                Toast.makeText(
                    this@ProfileScreen,
                    "Ошибка сети: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun exit_click(view: View) {
        val intent = Intent(this, SignInScreen::class.java)
        startActivity(intent)
    }

    fun main_click(view: View) {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
    }

    fun prof_coll_click(view: View) {
        val intent = Intent(this, CollectionsScreen::class.java)
        startActivity(intent)
    }

    fun obs_click(view: View) {
        val intent = Intent(this, ChatListScreen::class.java)
        startActivity(intent)
    }

    fun change_click(view: View) {

        val items = arrayOf<CharSequence>("Камера","Галерея")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Выберите источник фотографии")
        builder.setItems(items) { _, which ->
            when (which) {
                0 -> pickImageFromGallery()
                1 -> pickImageFromGallery()
            }
        }
        builder.show()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_PICK -> {
                    val selectedImage = data?.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                    cursor!!.moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    val picturePath = cursor.getString(columnIndex)
                    cursor.close()

                    saveImagePathToPrefs(picturePath)

                    val imageBitmap = BitmapFactory.decodeFile(picturePath)
                    avatarImageView.setImageBitmap(imageBitmap)
                }
            }
        } else {
            Toast.makeText(this, "Отменено", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImagePathToPrefs(imagePath: String) {
        val editor = sharedPreferences.edit()
        editor.putString("imagePath", imagePath)
        editor.apply()
    }
}