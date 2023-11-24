package com.example.worldcinema_poryadin

import android.content.Intent
import android.graphics.drawable.Icon
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView


class CreateCollection : AppCompatActivity() {

    private lateinit var selectedIconImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_collection)

        selectedIconImageView = findViewById(R.id.selectedIconImageView)
        val saveButton: Button = findViewById(R.id.saveButton)

        val selectedIconId = intent.getIntExtra("selectedIconId", -1)

        if (selectedIconId != -1) {
            val selectedIconImageView = findViewById<ImageView>(R.id.selectedIconImageView)
            selectedIconImageView.setImageResource(selectedIconId)
        }

        saveButton.setOnClickListener {
            val editText: EditText = findViewById(R.id.edit_nazv)
            val collectionName: String = editText.text.toString()

            val selectedIconId: Int = selectedIconImageView.tag as? Int ?: R.drawable.icon1

            val intent = Intent(this@CreateCollection, CollectionsScreen::class.java)
            intent.putExtra("collectionName", collectionName)
            intent.putExtra("selectedIcon", selectedIconId)

            startActivity(intent)
        }
    }

    fun back_click(view: View) {
        val intent = Intent(this,CollectionsScreen::class.java)
        startActivity(intent)
    }

    fun selectIcon_click(view: View) {
        val intent = Intent(this, IconSelectionScreen::class.java)
        startActivityForResult(intent, ICON_SELECTION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ICON_SELECTION_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedIconId = data?.getIntExtra("selectedIconId", -1)

            if (selectedIconId != -1) {
                if (selectedIconId != null) {
                    selectedIconImageView.setImageResource(selectedIconId)
                }
                selectedIconImageView.tag = selectedIconId
            }
        }
    }

    companion object {
        const val ICON_SELECTION_REQUEST_CODE = 1
    }
}