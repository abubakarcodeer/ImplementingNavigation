package com.adanalis.myfirstapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.adanalis.myfirstapplication.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private lateinit var loginAvtivity: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loginAvtivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(loginAvtivity.root)

        val db = Firebase.firestore

        sharedPreferences = getSharedPreferences("Logindata", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        loginAvtivity.apply {
            signinbtn.setOnClickListener {
                val username = userid.text.toString()
                val userpass = userpas.text.toString()

                if (username.isEmpty() || userpass.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter both username and password",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                } else {
                    db.collection("Userdata").whereEqualTo("name", username)
                        .whereEqualTo("password", userpass).get()
                        .addOnSuccessListener { result ->
                            val userList = ArrayList<ModelUser>()
                            if (!result.isEmpty) {
                                for (document in result) {
                                    val userid: String = document.id
                                    val name: String? = document.getString("name")
                                    val password: String? = document.getString("password")

                                    if (name != null && password != null) {
                                        editor.putBoolean("isLoggedIn", true).apply()
                                        userList.add(ModelUser(name, password, userid))
                                    } else {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "\"Firestore\", \"No user found with the provided credentials\"",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                AlertDialog.Builder(this@MainActivity).setCancelable(false)
                                    .setMessage("SuccessFully Login")
                                    .setPositiveButton("Ok") { _, _ ->
                                        val gson = Gson()
                                        val jsonString = gson.toJson(userList)
                                        val intent =
                                            Intent(this@MainActivity, ActivityPage::class.java)
                                        intent.putExtra("myArrayList", jsonString)
                                        startActivity(intent)
                                        finish()
                                    }.show()
                            } else {
                                AlertDialog.Builder(this@MainActivity).setCancelable(true)
                                    .setMessage("Didn't found Your ID!!").show()
                            }

                        }
                        .addOnFailureListener {
                            AlertDialog.Builder(this@MainActivity).setCancelable(false)
                                .setMessage("Login Fail")
                                .setPositiveButton("OK", null).show()
                        }
                }
            }
            registerbtn.setOnClickListener {
                startActivity(Intent(this@MainActivity, ActivitySignUp::class.java))
                finish()
            }
            userforget.setOnClickListener {
                startActivity(Intent(this@MainActivity, DialogForget::class.java))
            }
        }

    }

}




