package com.deano.checkpoint

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_uzivatel.*


class UzivatelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uzivatel)

        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)

        UzivatelEditText.setText(sharedPreferences.getString("User",""),TextView.BufferType.EDITABLE)


        UlozUzivateleButton.setOnClickListener {

            val editor = sharedPreferences.edit()

            editor.putString("User", UzivatelEditText.text.toString())
            editor.apply()

            finish()
        }

    }
}
