package com.example.android.chatbot.chat

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.chatbot.chat.MessageType.*
import com.example.android.chatbot.R.layout.item_bot_message
import com.example.android.chatbot.R.layout.item_person_message
import com.example.android.chatbot.ext.inflate
import kotlinx.android.synthetic.main.item_bot_message.view.*

class ChatAdapter : ListAdapter<MessageUi, ChatAdapter.ChatViewHolder>(MessageUiDiffCallback()) {

    companion object {
        private const val TYPE_BOT = 0
        private const val TYPE_PERSON = 1
    }

    override fun getItemViewType(position: Int) = when (getItem(position).type) {
        BOT -> TYPE_BOT
        PERSON -> TYPE_PERSON
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_BOT -> ChatViewHolder.fromBot(parent)
        TYPE_PERSON -> ChatViewHolder.fromPerson(parent)
        else -> throw ClassCastException("Unknown viewType $viewType")

    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(item = getItem(position))
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MessageUi) {
            with(itemView) {
                messageText.text = item.text
                messageUsername.setText(item.usernameRes)
                messageAvatar.setImageResource(item.avatarRes)
            }
        }

        companion object {
            fun fromBot(parent: ViewGroup) = ChatViewHolder(parent.inflate(item_bot_message))
            fun fromPerson(parent: ViewGroup) = ChatViewHolder(parent.inflate(item_person_message))
        }
    }


}