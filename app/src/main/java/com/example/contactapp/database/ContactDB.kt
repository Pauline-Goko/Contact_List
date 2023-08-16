package com.example.contactapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactapp.model.ContactData

@Database(entities = arrayOf(ContactData::class), version = 1)
//you cannot instantiate an abstract class
abstract class ContactDB: RoomDatabase() {
    abstract  fun contactDao(): ContactDao

    companion object{
        private var database: ContactDB? = null

        fun getDataBase(context: Context): ContactDB{
            if (database == null){
                database = Room.databaseBuilder(context, ContactDB::class.java, "ContactDB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as ContactDB
        }
    }
}