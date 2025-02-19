package com.adanalis.myfirstapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adanalis.myfirstapplication.databinding.ActivityDialogForgetBinding
import com.adanalis.myfirstapplication.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class DialogForget : AppCompatActivity() {
    private lateinit var binding: ActivityDialogForgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDialogForgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = Firebase.firestore
        binding.apply {
            forgetBtn.setOnClickListener(){
                var username = forgetUser.text.toString()
                var newpass = forgetPassword.text.toString()

                if (username.isEmpty() || newpass.isEmpty()) {
                    Toast.makeText(this@DialogForget, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                else {
                    db.collection("Userdata").whereEqualTo("name", username).get()
                        .addOnSuccessListener { result ->
                            if (!result.isEmpty) {
                                for (document in result) {
                                    db.collection("Userdata").document(document.id)
                                        .update("password", newpass)
                                        .addOnSuccessListener() {
                                            Toast.makeText(
                                                this@DialogForget,
                                                "Password updated successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        .addOnFailureListener() {
                                            Toast.makeText(
                                                this@DialogForget,
                                                "Password didn't update successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                }
                            } else {
                                Toast.makeText(
                                    this@DialogForget,
                                    "User not found",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        .addOnFailureListener() { e ->
                            Toast.makeText(
                                this@DialogForget,
                                "An error occurred: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}