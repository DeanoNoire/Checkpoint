package com.deano.checkpoint

import android.content.Context
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by deano on 16.3.18.
 */
object FirebasePush {


  fun pushujLog(context: Context){

        var PRAZDNY = ""
        val database = FirebaseDatabase.getInstance().getReference()
        val ref = database.child("Tagy")
        val sharedPreferences = context.getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)

    val timeStamp = sharedPreferences.getString("Time",PRAZDNY)
    val tag = sharedPreferences.getString("Tag",PRAZDNY)
    val user = sharedPreferences.getString("User",PRAZDNY)
    val firma = sharedPreferences.getString("Firma",PRAZDNY)

    val data = DataClass.Log(timeStamp,tag,user,firma)
    val key = ref.child(firma).push().key
    ref.child(firma).child(key).setValue(data)

    }

    fun pushujUdalost(context: Context){

        var PRAZDNY = ""
        val database = FirebaseDatabase.getInstance().getReference()
        val ref = database.child("Tagy")
        val sharedPreferences = context.getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)

        val timeStamp = sharedPreferences.getString("Time",PRAZDNY)
        val tag = sharedPreferences.getString("Tag",PRAZDNY)
        val user = sharedPreferences.getString("User",PRAZDNY)
        val firma = sharedPreferences.getString("Firma",PRAZDNY)
        val komentar = "Tag přepsán"

        val data = DataClass.Udalost(timeStamp,tag,komentar,user,firma)
        val key = ref.child(firma).push().key
        ref.child(firma).child(key).setValue(data)

    }
}