package com.example.contactapp.repository

import androidx.lifecycle.LiveData
import com.example.contactapp.MyContactsApp
import com.example.contactapp.database.ContactDB
import com.example.contactapp.model.ContactData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
    val database = ContactDB.getDataBase(MyContactsApp.appContext)

    suspend fun saveContact(contact: ContactData){
        withContext(Dispatchers.IO){
            database.contactDao().insertContact(contact)
        }
    }

    fun getDbContacts(): LiveData<List<ContactData>>{
        return database.contactDao().getAllContacts()
    }

    fun getContactById(contactId: Int): LiveData<ContactData>{
        return  database.contactDao().getContactById(contactId)
    }
}