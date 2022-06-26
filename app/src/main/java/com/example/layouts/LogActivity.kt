package com.example.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.layouts.Constants.Companion.PASSWORD_KEY
import com.example.layouts.Constants.Companion.USERNAME_KEY
import kotlinx.android.synthetic.main.activity_log.*

class LogActivity : AppCompatActivity() {
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        val username = intent.getStringExtra(USERNAME_KEY)
        val password = intent.getStringExtra(PASSWORD_KEY)
        tv_forgot.text = username + password

        tv_forgot.setOnClickListener {
            number++
            (it as TextView).text = number.toString()
        }
        Log.d("TAG", "onCreate: $number")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart: $number")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "onRestart: $number")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: $number")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: $number")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop: $number")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy: $number")
    }
}