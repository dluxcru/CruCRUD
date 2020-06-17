package com.example.crucrud

import android.content.Intent
import android.location.Address
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.realm.*
import io.realm.annotations.PrimaryKey


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var addPerson: FloatingActionButton
    private lateinit var contactsRV: RecyclerView
    private lateinit var personList: ArrayList<Person>
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Init the GUI components

//        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        addPerson = findViewById(R.id.addPerson)
        contactsRV = findViewById(R.id.contactsRV)

        // set OnClickListener(s)
        addPerson.setOnClickListener {
            startActivity(Intent(this, AddPersonActivity::class.java))
            finish()
        }
        //init personList
        personList = ArrayList()

//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//    }
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("Contacts.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(config)
        realm = Realm.getDefaultInstance()
        contactsRV.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        getAllPeople()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getAllPeople() {
        personList.clear()
        personList = ArrayList()
        val results:RealmResults<Person> = realm.where(Person::class.java).findAll()
        contactsRV.adapter = PersonAdapter(results, this)
        contactsRV.adapter!!.notifyDataSetChanged()

    }

    fun realmStuff() {
        val scsu = Address()
        scsu.lineOne = "720 4th Ave S"
        scsu.city = "St. Cloud"
        scsu.state = "MN"
        scsu.zip = "56301"

        val person = Person()
        person.name = "D'Lux Cru"

        val realm = Realm.getDefaultInstance()
    }
}

