package com.example.crucrud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import kotlinx.android.synthetic.main.contacts_rv_layout.view.*

class PersonAdapter(private val personList: RealmResults<Person>, private val context: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contacts_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.nameTV.text = personList[position]!!.name
        holder.itemView.idTV.text = personList[position]!!.id.toString()
        /* TODO(Bind Addresses) */
    }

    class ViewHolder(v:View?): RecyclerView.ViewHolder(v!!){
        val nameTV = itemView.findViewById<TextView>(R.id.nameTV)
        val addressTV =  itemView.findViewById<TextView>(R.id.addressTV)
        val idTV = itemView.findViewById<TextView>(R.id.idTV)
    }
}