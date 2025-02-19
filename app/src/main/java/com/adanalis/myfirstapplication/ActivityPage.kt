package com.adanalis.myfirstapplication

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adanalis.myfirstapplication.databinding.ActivityPageBinding
import com.adanalis.myfirstapplication.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ActivityPage : AppCompatActivity(){
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        val jsonString = intent.getStringExtra("myArrayList")

        if (jsonString.isNullOrEmpty()) {
            finish()
            return
        }
        val userType = object : TypeToken<ArrayList<ModelUser>>() {}.type
        val userList: ArrayList<ModelUser> = gson.fromJson(jsonString, userType)

        var recyclerView: RecyclerView = findViewById(R.id.recycleview)

        recyclerView.adapter = AdapterClass(userList)
        recyclerView.layoutManager = LinearLayoutManager(this@ActivityPage)
    }

}