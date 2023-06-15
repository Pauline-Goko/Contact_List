package com.example.contactapp

import android.content.Intent
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

        binding.fab.setOnClickListener {
            val intent = Intent(this,addContact::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        displayContacts()
    }

    fun displayContacts(){
        var contact1 = ContactData("Hannah Wanjiru", "+254786123432",
        "hannWanjiru@gmail.com", "https://i.pinimg.com/236x/0c/2e/5c/0c2e5c6ae0393845bf3a4c159c933f03.jpg")
        var contact2 = ContactData("Dana Adams", "+254734567654",
            "danAdams@gmail.com", "https://i.pinimg.com/564x/66/c3/31/66c331a7757b9d87397a05c46a678527.jpg")
        var contact3 = ContactData("Sparks LIz", "+254712345676",
            "lizziesparks@gmail.com", "https://i.pinimg.com/236x/60/82/3f/60823f679b52a16c912d378c66d22d80.jpg")
        var contact4 = ContactData("Ranee Mwangi", "+254709876549",
            "ranemwa@gmail.com", "https://i.pinimg.com/236x/9f/11/98/9f119847ba03cc1320c47c8bfc53d590.jpg")
        var contact5 = ContactData("Sean Omondi", "+254752323454",
            "omondiseab@gmail.com", "https://i.pinimg.com/236x/23/1e/27/231e27b8a69ff944a5a9e3a609fdb530.jpg")
        var contact6 = ContactData("Maxine Savara", "+254767542311",
            "maxsav@gmail.com", "https://i.pinimg.com/236x/8a/94/ef/8a94ef7fdafcf01e13bdda397ce0982f.jpg")
        var contact7 = ContactData("Val Brandon", "+254734567876",
            "valb@gmail.com", "https://i.pinimg.com/236x/ad/63/70/ad6370554c2992b181e42c41fa5d0fa2.jpg")
        var contact8 = ContactData("Saru Anderson", "+25471234654",
            "saruad@gmail.com", "https://i.pinimg.com/236x/77/ad/53/77ad53d39384edb20dcca76e3f0d2b20.jpg")


        var contactLists = listOf<ContactData>(contact1, contact2, contact3, contact4, contact5,
        contact6, contact7, contact8)



        var adapterContacts = ContactRVAdapter(contactLists)
        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = adapterContacts
    }
    }









