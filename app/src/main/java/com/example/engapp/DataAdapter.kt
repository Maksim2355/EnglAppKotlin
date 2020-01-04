package com.example.engapp

import android.R
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sun.applet.AppletResourceLoader.getImage


internal class DataAdapter(context: Context?, private val phones: List<Phone>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>()
    {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view: View = inflater.inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val phone = phones[position]
        holder.imageView.setImageResource(phone.getImage())
        holder.nameView.setText(phone.getName())
        holder.companyView.setText(phone.getCompany())
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView
        val nameView: TextView
        val companyView: TextView

        init {
            imageView = view.findViewById(R.id.image) as ImageView
            nameView = view.findViewById(R.id.name)
            companyView = view.findViewById(R.id.company)
        }
    }

    }