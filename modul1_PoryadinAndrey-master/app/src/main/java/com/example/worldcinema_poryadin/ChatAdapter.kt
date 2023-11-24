package com.example.worldcinema_poryadin

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(private val context: Context, private val messages: MutableList<ChatMessage>) : BaseAdapter() {

    data class ChatMessage(val message: String, val profileImage: Int, val senderName: String, val timestamp: String)

    fun addItem(message: String, profileImage: Int, senderName: String) {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val chatMessage = ChatMessage(message, profileImage, senderName, currentTime)
        messages.add(chatMessage)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(position: Int): Any {
        return messages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.message_item, parent, false)

        val profileImageView = itemView.findViewById<ImageView>(R.id.profileImageView)
        val senderNameTextView = itemView.findViewById<TextView>(R.id.senderNameTextView)
        val messageTextView = itemView.findViewById<TextView>(R.id.messageTextView)

        val chatMessage = getItem(position) as ChatMessage

        profileImageView.setImageResource(chatMessage.profileImage)

        senderNameTextView.text = "${chatMessage.senderName}, ${chatMessage.timestamp}"

        messageTextView.text = chatMessage.message

        return itemView
    }
}