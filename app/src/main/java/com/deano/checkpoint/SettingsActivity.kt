package com.deano.checkpoint

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class SettingsActivity : AppCompatActivity() {

    val PREFS_FILE = "com.deano.checkpoint.firma"
    val PREFS_FIRMA = "firma"
    var PREFS: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        PREFS = this.getSharedPreferences(PREFS_FILE, 0)
        val firma = PREFS!!.getInt(PREFS_FIRMA, 1)




    }





}
