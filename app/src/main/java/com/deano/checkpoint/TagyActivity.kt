package com.deano.checkpoint

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_tagy.*
import java.io.IOException

class TagyActivity : AppCompatActivity() {


    private var mNfcAdapter : NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagy)

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        var vysledek : String?



    }

    override fun onResume() {
        super.onResume()
        mNfcAdapter?.let{
            NFCUtil.enableNFCInForeground(it, this,javaClass)
        }
    }

    override fun onPause() {
        super.onPause()
        mNfcAdapter?.let {
            NFCUtil.disableNFCInForeground(it,this)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val messageWrittenSuccefully = NFCUtil.createNFCMessage(nfc1.text.toString(),intent)
        var vysledek = ifElse(messageWrittenSuccefully,"Zapsáno: "+nfc1.text.toString(),"Chyba")
        Toast.makeText(this,vysledek,Toast.LENGTH_LONG).show()
        finish()
    }

    fun<T> ifElse(condition: Boolean, primaryResult: T, secondaryResult: T) = if(condition) primaryResult else secondaryResult



}


