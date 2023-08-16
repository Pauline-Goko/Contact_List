package com.example.contactapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import com.example.contactapp.R
import com.example.contactapp.databinding.ActivityAddContactBinding
import com.example.contactapp.model.ContactData
import com.example.contactapp.viewmodel.ContactsViewModel

class AddContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddContactBinding
    val contactsViewModel: ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnSave.setOnClickListener {
            checkError()
            validateContacts()
        }
    }

    fun checkError(){

        binding.tieFirstName.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilFirstName.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        } )

        binding.tiePhoneNumber.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilPhoneNumber.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        } )
        binding.tieEmail.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilEmail.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        } )
    }
    fun validateContacts(){
        val name =binding. tieFirstName.text.toString()
        val phoneNumber = binding.tiePhoneNumber.text.toString()
        val email =binding. tieEmail.text.toString()

        var error = false

        if (name.isBlank()){
            binding.tieFirstName.error = getString(R.string.name)
            error = true
        }

        if (phoneNumber.isBlank()){
            binding.tiePhoneNumber.error = getString(R.string.phoneNumber)
            error = true
        }

        if (email.isBlank()){
            binding.tieEmail.error = getString(R.string.email)
            error = true
        }


        if (!error){
            val newContact = ContactData(contactId = 0, contactName = name, phoneNumber = phoneNumber, emailAddress = email, image = "")
            contactsViewModel.saveContact(newContact)
            Toast.makeText(this, "Contact saved", Toast.LENGTH_LONG).show()
                finish()
        }

    }

}


