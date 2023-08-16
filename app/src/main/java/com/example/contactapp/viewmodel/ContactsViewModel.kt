package com.example.contactapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.model.ContactData
import com.example.contactapp.repository.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel: ViewModel() {
    var contactsRepo = ContactsRepository()

    fun saveContact(contact: ContactData){
        viewModelScope.launch {
            contactsRepo.saveContact(contact)
        }

    }

    fun getContacts(): LiveData<List<ContactData>>{
        return contactsRepo.getDbContacts()
    }

    fun getContactById(contactId: Int):LiveData<ContactData>{
     return contactsRepo.getContactById(contactId)
    }


}