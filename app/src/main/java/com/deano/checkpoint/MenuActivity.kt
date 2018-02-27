package com.deano.checkpoint

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

                SettingsActivityButton.setOnClickListener(
                {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)

                })

    }
}
