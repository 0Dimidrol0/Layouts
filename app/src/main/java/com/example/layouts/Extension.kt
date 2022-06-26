package com.example.layouts

import android.content.Context
import android.widget.Toast


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun String.isContainFourNumbers():Boolean{
    var numbers = 0
    this.forEach {
        if(it.isDigit())numbers++
    }
    return numbers > 3
}