package com.example.contactapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class ContactData(
    var contactName: String,
    @PrimaryKey(autoGenerate = true)var contactId:Int,
    var phoneNumber: String,
    var emailAddress: String,
    var image: String,

)