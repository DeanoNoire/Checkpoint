package com.deano.checkpoint

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_obchuzky.*

import android.nfc.NfcAdapter
import java.util.*

class ObchuzkyActivity : AppCompatActivity() {

    private var mNfcAdapter: NfcAdapter? = null
    private var PRAZDNY = ""
    private var hodnota = ""
    private var server_url = "http://192.168.1.1/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_obchuzky)

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)

        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)
        userLabel.text = sharedPreferences.getString("User", PRAZDNY)

        PrehlasitButton.setOnClickListener({
            val intent = Intent(this, UzivatelActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)
        userLabel.text = sharedPreferences.getString("User",PRAZDNY)
        tep1.text = NFCUtil.retrieveNFCMessage(intent,tep2,this)

        //tep3.text = sharedPreferences.getString("Tag",PRAZDNY)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }


}

