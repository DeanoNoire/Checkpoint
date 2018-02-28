package com.deano.checkpoint

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_obchuzky.*

class ObchuzkyActivity : AppCompatActivity() {

    private var PRAZDNY = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obchuzky)

        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)

        userLabel.text = sharedPreferences.getString("User",PRAZDNY)

        PrehlasitButton.setOnClickListener({
            val intent = Intent(this, UzivatelActivity::class.java)
            startActivity(intent)
        })

    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)

        userLabel.text = sharedPreferences.getString("User",PRAZDNY)

    }


}
