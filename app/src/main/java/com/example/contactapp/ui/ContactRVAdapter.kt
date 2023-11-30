package com.example.contactapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.model.ContactData
import com.example.contactapp.databinding.ContactListItemBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File

class ContactRVAdapter(var contactList: List<ContactData>, val context: Context): RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var contactsDetails = contactList[position]

        holder.binding.apply {
            tvName.text = contactsDetails.contactName
            tvNumber.text = contactsDetails.phoneNumber
            tvEmail.text = contactsDetails.emailAddress
            if (contactsDetails.image.isNotBlank()) {

                Picasso
                    .get()
                    .load(File(contactsDetails.image))
                    .resize(80, 80)
                    .centerCrop()
                    .transform(CropCircleTransformation())
                    .into(holder.binding.ivContactImage)


            }
            holder.binding.cvContact.setOnClickListener{
                val intent = Intent(context, ContactDetailsActivity::class.java)
                intent.putExtra("CONTACT_ID",contactsDetails.contactId)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
       return contactList.size
    }
}


class ContactViewHolder(var binding: ContactListItemBinding): RecyclerView.ViewHolder(binding.root)