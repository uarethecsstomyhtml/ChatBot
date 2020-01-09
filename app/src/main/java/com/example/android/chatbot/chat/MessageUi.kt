package com.example.android.chatbot.chat

import androidx.recyclerview.widget.DiffUtil

data class MessageUi(
    val id: Int,
    val usernameRes: Int,
    val text: String,
    val avatarRes: Int,
    val type: MessageType
)


class MessageUiDiffCallback : DiffUtil.ItemCallback<MessageUi>() {
    override fun areItemsTheSame(oldItem: MessageUi, newItem: MessageUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessageUi, newItem: MessageUi): Boolean {
        return oldItem == newItem
    }
}