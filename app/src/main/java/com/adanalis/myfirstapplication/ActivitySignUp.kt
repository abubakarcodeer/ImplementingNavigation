package com.adanalis.myfirstapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adanalis.myfirstapplication.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ActivitySignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = Firebase.firestore;

        binding.signupBtn.setOnClickListener() {
            var userName = binding.signupUsername.text.toString();
            var userPassword = binding.signupPassword.text.toString();
            var confirmpass = binding.signupConfirmpassword.text.toString();

            if (userName.isEmpty() || userPassword.isEmpty()) {
                AlertDialog.Builder(this@ActivitySignUp)
                    .setCancelable(false)
                    .setMessage("Username and Password cannot be empty.")
                    .setPositiveButton("Ok", null)
                    .show()
                return@setOnClickListener
            } else if (userPassword != confirmpass) {
                AlertDialog.Builder(this@ActivitySignUp)
                    .setCancelable(false)
                    .setMessage("Confirm Password and Password cannot be Different.")
                    .setPositiveButton("Ok", null)
                    .show()
                return@setOnClickListener
            }

            var userData = ModelUser(userName, userPassword, "")
            db.collection("Userdata").add(userData)
                .addOnSuccessListener { result ->

                    db.collection("Userdata").document(result.id).update("id", result.id)

                    AlertDialog.Builder(this@ActivitySignUp).setCancelable(false)
                        .setMessage("SuccessFully Creating an Account")
                        .setPositiveButton("Ok") { _, _ ->
                            startActivity(Intent(this@ActivitySignUp, MainActivity::class.java))
                            finish()
                        }.show()
                }
                .addOnFailureListener() {
                   AlertDialog.Builder(this@ActivitySignUp).setCancelable(false)
                        .setMessage("Error Occuring during Creating an Account")
                        .setPositiveButton("Ok",null).show()
                }


        }
        binding.btnsignin.setOnClickListener(){
            startActivity(Intent(this@ActivitySignUp, MainActivity::class.java))
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}