package com.example.android.chatbot.chat

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.chatbot.R.drawable.*
import com.example.android.chatbot.R.string.*
import com.example.android.chatbot.chat.MessageType.BOT
import com.example.android.chatbot.chat.MessageType.PERSON
import com.example.android.chatbot.ext.randomText
import kotlin.random.Random

private const val COUNT_DOWN_TIMER = 1000L

class MainViewModel : ViewModel() {

    private val _messages = MessageUiListMutable()
    val messages: MessageUiListLive
        get() = _messages

    private val _toastText = IntMutable()
    val toastText: IntLive = _toastText

    fun sendMessage(text: String) {
        when {
            text.isBlank() -> _toastText.value = toast_empty_text
            else -> addPersonMessage(text)
        }
    }

    private fun addPersonMessage(text: String) {
        val newMessages = _messages.value?.toMutableList() ?: mutableListOf()
        newMessages.add(element = transformToPersonMessage(newMessages.lastIndex, text))
        _messages.value = newMessages
        addBotMessages(newMessages)
    }

    private fun addBotMessages(messages: MutableList<MessageUi>) {
        val random = Random
        val millisInFuture = random.nextInt(1, 5) * COUNT_DOWN_TIMER
        val countDownTimer = object: CountDownTimer(millisInFuture, COUNT_DOWN_TIMER) {
            override fun onFinish() {}

            override fun onTick(remain: Long) {
                _messages.value = messages.apply {
                    add(element = transformToBot1Message(lastIndex, randomText()))
                    add(element = transformToBot2Message(lastIndex, randomText()))
                    add(element = transformToBot3Message(lastIndex, randomText()))
                }
            }

        }
        countDownTimer.start()
    }

    private fun transformToPersonMessage(id: Int, text: String) =
        MessageUi(id, username_person, text, ic_person, PERSON)

    private fun transformToBot1Message(id: Int, text: String) =
        MessageUi(id, username_bot1, text, ic_bot1, BOT)

    private fun transformToBot2Message(id: Int, text: String) =
        MessageUi(id, username_bot2, text, ic_bot2, BOT)

    private fun transformToBot3Message(id: Int, text: String) =
        MessageUi(id, username_bot3, text, ic_bot3, BOT)
}
