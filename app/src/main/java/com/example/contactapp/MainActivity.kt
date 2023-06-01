package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        displayContacts()
    }

    fun displayContacts(){
        var contact1 = ContactData("Hannah Wanjiru", "+254786123432",
        "hannWanjiru@gmail.com", "Han")
        var contact2 = ContactData("Dana Adams", "+254734567654",
            "danAdams@gmail.com", "DanaA")
        var contact3 = ContactData("Sparks LIz", "+254712345676",
            "lizziesparks@gmail.com", "sparkie")
        var contact4 = ContactData("Ranee Mwangi", "+254709876549",
            "ranemwa@gmail.com", "ram")
        var contact5 = ContactData("Sean Omondi", "+254752323454",
            "omondiseab@gmail.com", "omosh")
        var contact6 = ContactData("Maxine Savara", "+254767542311",
            "maxsav@gmail.com", "maxi")
        var contact7 = ContactData("Val Brandon", "+254734567876",
            "valb@gmail.com", "valndon")
        var contact8 = ContactData("Saru Anderson", "+25471234654",
            "saruad@gmail.com", "son")


        var contactLists = listOf<ContactData>(contact1, contact2, contact3, contact4, contact5,
        contact6, contact7, contact8)


        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        var adapterContacts = ContactRVAdapter(contactLists)
        binding.rvContacts.adapter = adapterContacts
    }
    }









