package com.example.contactapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.databinding.ContactListItemBinding

class ContactRVAdapter(var contactList: List<ContactData>): RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        var binding = ContactListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var contactsDetails = contactList[position]

        holder.binding.apply {
            tvName.text = contactsDetails.contactName
            tvNumber.text = contactsDetails.phoneNumber
            tvEmail.text = contactsDetails.emailAddress
        }
    }

    override fun getItemCount(): Int {
       return contactList.size
    }
}


class ContactViewHolder(var binding: ContactListItemBinding): RecyclerView.ViewHolder(binding.root)