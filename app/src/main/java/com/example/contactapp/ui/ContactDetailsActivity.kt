package com.example.contactapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.contactapp.R
import com.example.contactapp.databinding.ActivityContactDetailsBinding
import com.example.contactapp.model.ContactData
import com.example.contactapp.viewmodel.ContactsViewModel

class ContactDetailsActivity : AppCompatActivity() {
    var contactId = 0
    val viewModel: ContactsViewModel by viewModels()
    lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var bundle = intent.extras
//        if (bundle!=null){
//
//            contactId = bundle.getInt("CONTACT_ID")
//            Toast.makeText(this, "$contactId", Toast.LENGTH_LONG).show()
//        }
        val contactId = intent.getIntExtra("CONTACT_ID", 0)
        viewModel.getContactById(contactId).observe(this, Observer<ContactData>{ contact ->
        if (contact!=null){
            displayContactDetails(contact)
        }
            else{
                Toast.makeText(this, "Conatct not found", Toast.LENGTH_LONG).show()
        }

        })
    }


    fun displayContactDetails(contact: ContactData){
        binding.tvNameDetails.text = contact.contactName
        binding.tvPhoneNumberDetails.text = contact.phoneNumber
        binding.tvEmailDetails.text = contact.emailAddress

    }
    
}