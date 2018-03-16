package com.deano.checkpoint

/**
 * Created by deano on 3.3.18.
 */

import android.app.Activity
import android.app.PendingIntent
import android.content.*
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.widget.TextView
import android.widget.Toast
import java.io.IOException
import java.util.*


object NFCUtil {

    fun createNFCMessage(payload: String, intent: Intent?): Boolean {

        val pathPrefix = "deano.com:nfcapp"
        val nfcRecord = NdefRecord(NdefRecord.TNF_EXTERNAL_TYPE, pathPrefix.toByteArray(), ByteArray(0), payload.toByteArray())
        val nfcMessage = NdefMessage(arrayOf(nfcRecord))
        intent?.let {
            val tag = it.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            return writeMessageToTag(nfcMessage, tag)
        }
        return false

       // val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)
    }

   fun retrieveNFCMessage(intent: Intent?, textView: TextView,context: Context): String {
        intent?.let {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {

                val nDefMessages = getNDefMessages(intent)
                nDefMessages[0].records?.let {
                    it.forEach {
                        it?.payload.let {
                            it?.let {
                                predaniDat(it,textView,context)
                                toastovani(context)
                                FirebasePush.pushuj(context)
                                return String(it)
                                }
                        }
                    }
                }

            } else {
                return ""
            }
        }
        return ""
    }

   private fun toastovani(context: Context) {
        val sharedPreferences = context.getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)
        val tag = sharedPreferences.getString("Tag", "")
        val time = sharedPreferences.getString("Time", "")
        val user = sharedPreferences.getString("User", "")
        val email = sharedPreferences.getString("Email", "")
        val o1 = " "
        val zprava = tag + o1 + time + o1 + user + o1 + email
        Toast.makeText(context, zprava, Toast.LENGTH_LONG).show()
    }

   fun predaniDat(it: ByteArray, textView: TextView,context: Context) {
        val sharedPreferences = context.getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Tag",String(it))
        editor.putString("Time", ziskaniCasu(textView))
        editor.apply()
    }

   fun cas() : String {
        var timezone = TimeZone.getTimeZone("CET")
        var cal = Calendar.getInstance(timezone)
        var den = cal.get(Calendar.DAY_OF_MONTH).toString()
        var mesic = (cal.get(Calendar.MONTH)+1).toString()
        var rok = cal.get(Calendar.YEAR).toString()
        var hod = cal.get(Calendar.HOUR).toString()
        var min = cal.get(Calendar.MINUTE).toString()
        val o1 = "."
        val o2 = ":"
        val o3 = " "
        return den +o1+ mesic +o1+ rok +o3+ hod +o2+ min
    }

   fun ziskaniCasu(textView: TextView) : String {

       var hodnota = cas()
        textView.text = hodnota
       return hodnota
    }

    private fun getNDefMessages(intent: Intent): Array<NdefMessage> {

        val rawMessage = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
        rawMessage?.let {
            return rawMessage.map {
                it as NdefMessage
            }.toTypedArray()
        }
        // Unknown tag type
        val empty = byteArrayOf()
        val record = NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty)
        val msg = NdefMessage(arrayOf(record))
        return arrayOf(msg)
    }

    fun disableNFCInForeground(nfcAdapter: NfcAdapter, activity: Activity) {
        nfcAdapter.disableForegroundDispatch(activity)
    }

    fun <T> enableNFCInForeground(nfcAdapter: NfcAdapter, activity: Activity, classType: Class<T>) {
        val pendingIntent = PendingIntent.getActivity(activity, 0,
                Intent(activity, classType).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
        val nfcIntentFilter = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        val filters = arrayOf(nfcIntentFilter)

        val TechLists = arrayOf(arrayOf(Ndef::class.java.name), arrayOf(NdefFormatable::class.java.name))

        nfcAdapter.enableForegroundDispatch(activity, pendingIntent, filters, TechLists)
    }

    private fun writeMessageToTag(nfcMessage: NdefMessage, tag: Tag?): Boolean {

        try {
            val nDefTag = Ndef.get(tag)

            nDefTag?.let {
                it.connect()
                if (it.maxSize < nfcMessage.toByteArray().size) {
                    //Message to large to write to NFC tag
                    return false
                }
                if (it.isWritable) {
                    it.writeNdefMessage(nfcMessage)
                    it.close()
                    //Message is written to tag
                    return true
                } else {
                    //NFC tag is read-only
                    return false
                }
            }

            val nDefFormatableTag = NdefFormatable.get(tag)

            nDefFormatableTag?.let {
                try {
                    it.connect()
                    it.format(nfcMessage)
                    it.close()
                    //The data is written to the tag
                    return true
                } catch (e: IOException) {
                    //Failed to format tag
                    return false
                }
            }
            //NDEF is not supported
            return false

        } catch (e: Exception) {
            //Write operation has failed
        }
        return false
    }


   



}