package com.deano.checkpoint

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        ObchuzkyButton.setOnClickListener(
                {
                    val intent = Intent(this, ObchuzkyActivity::class.java)
                    startActivity(intent)

                })

        TagyActivityButton.setOnClickListener(
                {
                    val intent = Intent(this, TagyActivity::class.java)
                    startActivity(intent)

                })

        UzivatelActivityButton.setOnClickListener(
                {
                    val intent = Intent(this, UzivatelActivity::class.java)
                    startActivity(intent)

                })


                SettingsActivityButton.setOnClickListener(
                {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)

                })


    }
}
