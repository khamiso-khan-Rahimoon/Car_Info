package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

            private lateinit var binding: ActivitySignInBinding
        //    companion object{
        //        const val key1 = "com.example.firebase.SignInActivity.mail"
        //        const val key2 = "com.example.firebase.SignInActivity.name"
        //        const val key3 = "com.example.firebase.SignInActivity.uniqueid"
        //    }
        //    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
            binding.noAccount.setOnClickListener {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            binding.signInBtn.setOnClickListener {
                firebaseAuth = FirebaseAuth.getInstance()
                val email = binding.etSignInEmail.text.toString().trim()
                val password = binding.PasswordSignInBtn.text.toString().trim()
                if (email.isNotEmpty() && password.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Loge in successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                    }.addOnFailureListener {
                            Toast.makeText(this, "Log in failed", Toast.LENGTH_SHORT).show()
                        }


                }else{
                    Toast.makeText(this,"Please fill all fields", Toast.LENGTH_SHORT).show()
                }

            }


    }
}




//    binding.signInBTN.setOnClickListener{
//           val userEmaill = binding.etSignemail.text.toString()
//            if (userEmaill.isNotEmpty()){
//                readData(userEmaill)
//
//            }else{
//                Toast.makeText(this, "Please Enter user name", Toast.LENGTH_LONG).show()
//            }
//
//
//        }
//
//
//
//    }
//
//    private fun readData(name:String) {
//        databaseReference = FirebaseDatabase.getInstance().getReference("users")
//        databaseReference.child(name).get()
//            .addOnSuccessListener {
//                if (it.exists()){
//                    val emial = it.child("email").value
//                    val namee = it.child("name").value
//                    val uniqueid = it.child("uniqueId").value
//                    val intentwelcome = Intent(this,HomeActivity::class.java)
//                    intentwelcome.putExtra(key1,emial.toString())
//                    intentwelcome.putExtra(key2,namee.toString())
//                    intentwelcome.putExtra(key3,uniqueid.toString())
//                    startActivity(intentwelcome)
//                }else{
//                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
//                }
//
//
//        }.addOnFailureListener {
//            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
//        }
//    }