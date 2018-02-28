package com.deano.checkpoint

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pripojeni.*

class PripojeniActivity : AppCompatActivity() {

    private var PRAZDNY = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pripojeni)

        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)

        emaileditText.setText(sharedPreferences.getString("Email",""), TextView.BufferType.EDITABLE)
        hesloeditText.setText(sharedPreferences.getString("Heslo",""), TextView.BufferType.EDITABLE)

        buttonPripojeni.setOnClickListener {

            val editor = sharedPreferences.edit()

            editor.putString("Email",emaileditText.text.toString())
            editor.putString("Heslo",hesloeditText.text.toString())
            editor.apply()

            Toast.makeText(this,"Uloženo", Toast.LENGTH_LONG ).show()

            finish()
           //var firma =  verifikace("email","heslo")


        }
        overButton.setOnClickListener({
            Toast.makeText(this,"Email: "+sharedPreferences.getString("Email",PRAZDNY), Toast.LENGTH_LONG ).show()
        })

    }

    private fun verifikace(email: String, heslo: String) : String{
        return email+heslo
    }


}
