package com.example.contactapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapp.model.ContactData
import com.example.contactapp.databinding.ActivityMainBinding
import com.example.contactapp.viewmodel.ContactsViewModel

class  MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val contactsViewModel: ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactsViewModel.getContactById(1)

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        contactsViewModel.getContacts().observe(this,
            {contacts->displayContacts(contacts)}
        )
    }

    fun displayContacts(contactList:List<ContactData>){
        val contactsAdapter = ContactRVAdapter(contactList, this)
        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = contactsAdapter
    }
    }









