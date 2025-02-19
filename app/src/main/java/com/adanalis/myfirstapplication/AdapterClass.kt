package com.adanalis.myfirstapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.withContext

class AdapterClass(
    private val dataList:ArrayList<ModelUser>
):RecyclerView.Adapter<AdapterClass.ViewHolder>() {



    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var name:TextView = view.findViewById(R.id.item_username)
        var password:TextView = view.findViewById(R.id.item_password)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var model = dataList[position]
        holder.name.setText(model.name)
        holder.password.setText(model.password)
    }
}