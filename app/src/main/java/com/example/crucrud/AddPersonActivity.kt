package com.example.crucrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import java.lang.Exception

class AddPersonActivity : AppCompatActivity() {
    private lateinit var addPersonED: EditText
    private lateinit var createButton: Button
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // init GUI
        setContentView(R.layout.activity_add_person)
        addPersonED = findViewById(R.id.addPerson)
        createButton = findViewById(R.id.createButton)
        realm = Realm.getDefaultInstance()
        // add onClickListener
        createButton.setOnClickListener{
            createPerson()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun createPerson() {
        try {
            realm.beginTransaction()
            val currentIdNumber: Number? = realm.where(Person.class.java).max("id")
            val nextID:Int
            nextID = if (currentIdNumber == null){ 1 } else {currentIdNumber.toInt() + 1}

            val person = Person()
            person.name = addPersonED.text.toString()
            person.id = nextID.toLong()

            realm.copyToRealmOrUpdate(person)
            Toast.makeText(this, "${person.name} added successfully!", Toast.LENGTH_SHORT)
        } catch (e: Exception){
            Toast.makeText(this, "Failed $e ", Toast.LENGTH_LONG)
        }
    }
}