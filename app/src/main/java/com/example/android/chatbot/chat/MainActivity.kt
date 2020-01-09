package com.example.android.chatbot.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.example.android.chatbot.R.layout.activity_main
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(activity_main) {

    private lateinit var adapter: ChatAdapter
    private lateinit var viewModel: MainViewModel

    companion object {
        private const val TOPIC_BOT1 = "bot1"
        private const val TOPIC_BOT2 = "bot2"
        private const val TOPIC_BOT3 = "bot3"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToTopics()
        initViewModel()
        setupAdapter()
        setupRv()
        observeMessages()
        observeToast()
        imgSend.setOnClickListener(::sendMessage)
    }

    private fun subscribeToTopics() {
        with(FirebaseMessaging.getInstance()) {
            subscribeToTopic(TOPIC_BOT1)
            subscribeToTopic(TOPIC_BOT2)
            subscribeToTopic(TOPIC_BOT3)
        }

    }

    private fun initViewModel() {
        viewModel = MainViewModel()
    }

    private fun setupAdapter() {
        adapter = ChatAdapter()
    }

    @Suppress("UNUSED_PARAMETER")
    private fun sendMessage(view: View) {
        viewModel.sendMessage(text = etMessage.text.trim().toString())
        etMessage.text.clear()
    }

    private fun setupRv() {
        rvChat.adapter = adapter
    }

    private fun observeMessages() {
        viewModel.messages.observe(this, Observer(::onChangeMessages))
    }

    private fun observeToast() {
        viewModel.toastText.observe(this, Observer(::onChangeToast))
    }

    private fun onChangeMessages(messages: List<MessageUi>) {
        adapter.submitList(messages)
        rvChat.scrollToPosition(messages.lastIndex)
    }

    private fun onChangeToast(@StringRes textRes: Int) {
        Toast.makeText(this, getString(textRes), LENGTH_LONG).show()
    }

}
