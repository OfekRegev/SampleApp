package com.ofek.sample.extensions

fun String?.norNullAndEmpty() : Boolean {
    return this != null && isNotEmpty()
}