package com.example.android.chatbot.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


typealias MessageUiList = List<MessageUi>
typealias MessageUiListLive = LiveData<MessageUiList>
typealias MessageUiListMutable = MutableLiveData<MessageUiList>
typealias IntLive = LiveData<Int>
typealias IntMutable = MutableLiveData<Int>
