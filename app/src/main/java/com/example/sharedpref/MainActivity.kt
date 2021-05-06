package com.example.sharedpref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpref.helper.Constant
import com.example.sharedpref.helper.PreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  lateinit var sharedpref: PreferenceHelper

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    sharedpref = PreferenceHelper(this)

    btnLogin.setOnClickListener {
      if (etUsername.text.isNotEmpty() && etPassword.text.isNotEmpty()) {
        saveSession(etUsername.text.toString(), etPassword.text.toString())
        showMsg("Login Success")
        moveIntent()
      }
    }
  }

  override fun onStart() {
    super.onStart()
    if (sharedpref.getBoolean(Constant.PREF_IS_LOGIN)) moveIntent()
  }

  private fun moveIntent() {
    startActivity(Intent(this, UserActivity::class.java))
    finish()
  }

  private fun saveSession(username: String, password: String) {
    sharedpref.put(Constant.PREF_USERNAME, username)
    sharedpref.put(Constant.PREF_PASSWORD, password)
    sharedpref.put(Constant.PREF_IS_LOGIN, true)
  }

  private fun showMsg(msgText: String) {
    Toast.makeText(applicationContext, msgText, Toast.LENGTH_SHORT).show()
  }
}