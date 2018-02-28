package com.deano.checkpoint

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_pripojeni.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private var PRAZDNY = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)


            firmaSharedLabel.text = sharedPreferences.getString("Firma",PRAZDNY)
            emailSharedLabel.text = sharedPreferences.getString("Email",PRAZDNY)
            pwdSharedLabel.text = sharedPreferences.getString("Heslo",PRAZDNY)


        PripojeniButton.setOnClickListener(
                {
                    val intent = Intent(this, PripojeniActivity::class.java)
                    startActivity(intent)

                })

        buttonReset.setOnClickListener(
                {
                    val editor = sharedPreferences.edit()
                    editor.putString("Firma",PRAZDNY)
                    editor.putString("Email",PRAZDNY)
                    editor.putString("Heslo",PRAZDNY)
                    editor.apply()

                    firmaSharedLabel.text = sharedPreferences.getString("Firma",PRAZDNY)
                    emailSharedLabel.text = sharedPreferences.getString("Email",PRAZDNY)
                    pwdSharedLabel.text = sharedPreferences.getString("Heslo",PRAZDNY)


                })



    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)

           firmaSharedLabel.text = sharedPreferences.getString("Firma",PRAZDNY)
            emailSharedLabel.text = sharedPreferences.getString("Email",PRAZDNY)
            pwdSharedLabel.text = sharedPreferences.getString("Heslo",PRAZDNY)


    }



}
