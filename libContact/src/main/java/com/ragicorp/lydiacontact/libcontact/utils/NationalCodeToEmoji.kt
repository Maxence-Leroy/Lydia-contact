package com.ragicorp.lydiacontact.libcontact.utils

fun nationalCodeToEmoji(code: String): String {
    // Inspired by https://stackoverflow.com/a/35849652
    val firstLetter = code.codePointAt(0) - 0x41 + 0x1F1E6
    val secondLetter = code.codePointAt(1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}