package com.example.sharedpref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpref.helper.Constant
import com.example.sharedpref.helper.PreferenceHelper
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

  lateinit var sharedpref: PreferenceHelper

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user)

    sharedpref = PreferenceHelper(this)

    txtUsername.text = sharedpref.getString(Constant.PREF_USERNAME)

    btnLogout.setOnClickListener {
      sharedpref.clear()
      showMsg("Logout Success")
      moveIntent()
    }
  }

  private fun moveIntent() {
    startActivity(Intent(this, MainActivity::class.java))
    finish()
  }

  private fun showMsg(msgText: String) {
    Toast.makeText(applicationContext, msgText, Toast.LENGTH_SHORT).show()
  }
}