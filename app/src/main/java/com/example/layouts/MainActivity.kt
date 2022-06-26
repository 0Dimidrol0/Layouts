package com.example.layouts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.layouts.Constants.Companion.USERNAME_KEY
import com.example.layouts.Constants.Companion.PASSWORD_KEY
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogin: AppCompatButton
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var constraint: ConstraintLayout
    private lateinit var inflater: LayoutInflater
    private lateinit var toast: Toast
    private lateinit var invalidResourceSnackBarView: View
    private lateinit var invalidUserSnackBar: Snackbar
    private lateinit var invalidPasswordSnackBar: Snackbar
    private lateinit var myIntent: Intent
    private lateinit var snackBarLayout: Snackbar.SnackbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        invalidUserSnackBar = initInvalidResourceSnackBar(getString(R.string.username_warning))
        invalidPasswordSnackBar = initInvalidResourceSnackBar(getString(R.string.password_warning))
        initToast()
        initListeners()

    }


    private fun initInvalidResourceSnackBar(warningText : String):Snackbar {
        invalidResourceSnackBarView = inflater.inflate(R.layout.snackbar_layout, findViewById(R.id.snackBar_root))
        val text: AppCompatTextView = invalidResourceSnackBarView.findViewById(R.id.tv_warning)
        text.text = warningText
        val snackBar = Snackbar.make(constraint, "", Snackbar.LENGTH_LONG)
        snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.removeAllViews()
        snackBarLayout.addView(invalidResourceSnackBarView)
        return snackBar
    }

    private fun initListeners() {
        btnLogin.run {
            setOnClickListener {
                val username : String = username.text.trim().toString()
                val password : String = password.text.trim().toString()
                username.run {
                    if (isUsernameValid(this)) {
                        checkPassword(password)
                    }else {
                        invalidUserSnackBar.show()
                    }
                }
            }
        }
    }

    private fun goToLogActivity() {
        myIntent = Intent(this@MainActivity, LogActivity::class.java)
        myIntent.putExtra(USERNAME_KEY, username.text.toString())
        myIntent.putExtra(PASSWORD_KEY, password.text.toString())
        startActivity(myIntent)
    }

    private fun isUsernameValid(username: String): Boolean =
          username.isNotEmpty() && !username.first().isUpperCase() && username.length > 7

    private fun checkPassword(password: String) {
        password.also {
            if (it.isNotEmpty() && it.first().isUpperCase() && it.isContainFourNumbers())
            {
                toast.show()
                goToLogActivity()
            } else {
                invalidPasswordSnackBar.show()
            }
        }
    }

    private fun initView() {
        btnLogin = findViewById(R.id.bt_login)
        constraint = findViewById(R.id.constraint)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        inflater = layoutInflater
    }

    private fun initToast() {
        val toastView = inflater.inflate(R.layout.custom_toast, findViewById(R.id.toast_root))
        toast = Toast(this).apply {
            view = toastView
            duration = LENGTH_SHORT
            setGravity(Gravity.CENTER, 0, 0)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop: MainActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy: MainActivity")
    }
}
