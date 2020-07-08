package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.infra.motivationConstants
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSave.setOnClickListener(this)

        verifyName()

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSave) {
            handleSave()
        }

    }

    private fun verifyName(){
        val name = mSecurityPreferences.getString(motivationConstants.KEY.PERSON_NAME)
        if (name != ""){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()

        if (name != "") {
            mSecurityPreferences.getString(motivationConstants.KEY.PERSON_NAME)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
}