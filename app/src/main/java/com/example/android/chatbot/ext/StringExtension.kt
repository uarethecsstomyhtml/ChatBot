package com.example.android.chatbot.ext

import kotlin.random.Random


fun randomText(): String {
    val generator = Random
    val randomStringBuilder = StringBuilder()
    val randomLength: Int = generator.nextInt(24)
    var tempChar: Char
    for (i in 0 until randomLength) {
        tempChar = ((generator.nextInt(96) + 32).toChar())
        randomStringBuilder.append(tempChar)
    }
    return randomStringBuilder.toString()
}