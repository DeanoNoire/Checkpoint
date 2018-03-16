package com.deano.checkpoint

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pripojeni.*
import java.util.Map

class PripojeniActivity : AppCompatActivity() {

    private var PRAZDNY = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pripojeni)

        val sharedPreferences = getSharedPreferences("PRIPOJENI", Context.MODE_PRIVATE)

        emaileditText.setText(sharedPreferences.getString("Email",""), TextView.BufferType.EDITABLE)
        hesloeditText.setText(sharedPreferences.getString("Heslo",""), TextView.BufferType.EDITABLE)

        buttonPripojeni.setOnClickListener {

            val editor = sharedPreferences.edit()

            editor.putString("Email",emaileditText.text.toString())
            editor.putString("Heslo",hesloeditText.text.toString())
            editor.apply()
            finish()


        }
        overButton.setOnClickListener({

            val editor = sharedPreferences.edit()

            editor.putString("Email",emaileditText.text.toString())
            editor.putString("Heslo",hesloeditText.text.toString())
            editor.apply()

            if(emaileditText.text.toString().trim().isEmpty())
            {
                    Toast.makeText(this,"Prázdný email",Toast.LENGTH_SHORT).show()

                }
            else {

                if (hesloeditText.text.toString().trim().isEmpty())
                {
                    Toast.makeText(this,"Prázdné heslo",Toast.LENGTH_SHORT).show()
                }

                else {
                    val email = sharedPreferences.getString("Email", PRAZDNY)
                    val carkovanyEmail = email.replace(".",",")
                    val heslo = sharedPreferences.getString("Heslo", PRAZDNY)


                   // Toast.makeText(this, "Email: " + email + "Heslo" + heslo, Toast.LENGTH_LONG).show()

                    val database = FirebaseDatabase.getInstance().getReference()
                    val ref = database.child("Firmy")
                    //ref.orderByChild("email").equalTo(carkovanyEmail)
                    var hesloHandshake = ""
                    var firmaHandshake = ""
                    ref.addListenerForSingleValueEvent(object : ValueEventListener  {

                        override fun onDataChange(snapshot: DataSnapshot?) {
                            val children = snapshot!!.children

                                println("children: "+snapshot.children.toString())
                                children.forEach {
                                if(carkovanyEmail.equals(it.child("email").value) && heslo.equals(it.child("heslo").value)) {

                                        System.out.println(it.child("nazev").value as String)
                                        System.out.println(it.child("heslo").value as String)
                                        firmaHandshake = it.child("nazev").value as String
                                        hesloHandshake = it.child("heslo").value as String

                                }
 }

                            if(firmaHandshake.isEmpty()){
                                System.out.println("Špatné udaje")
                                Toast.makeText(this@PripojeniActivity,"Špatné údaje",Toast.LENGTH_LONG).show()
                            }
                            else {
                                // Správnej název firmy uložim do proměnný
                                val editor2 = sharedPreferences.edit()
                                editor2.putString("Firma", firmaHandshake)
                                editor2.apply()
                                finish()
                                Toast.makeText(this@PripojeniActivity,"Firma rozpoznána: "+firmaHandshake,Toast.LENGTH_LONG).show()
                            }






                        }

                        override fun onCancelled(error: DatabaseError?) {
                            println("Chyba")
                        }

                    })






                }
            }


        })

    }



}
