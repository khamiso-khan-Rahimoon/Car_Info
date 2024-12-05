package com.example.firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebase.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.alreadyaccount.setOnClickListener {
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signUpBtn.setOnClickListener{
            firebaseAuth = FirebaseAuth.getInstance()
            signUpMethod()

        }






    }
    private fun signUpMethod(){
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.PasswordBtn.text.toString().trim()
        val confirmPassword = binding.confirmPasswordBtn.text.toString().trim()
        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
            if (password == confirmPassword){
                databaseReference = FirebaseDatabase.getInstance().getReference("users")
                val usersdata = usersData(name,email,password,confirmPassword)
                databaseReference.child("$name+321").setValue(usersdata).addOnSuccessListener {
                    firebaseAuth.createUserWithEmailAndPassword(email,confirmPassword)
                        .addOnSuccessListener {
                        Toast.makeText(this, "Sign up Successful", Toast.LENGTH_SHORT).show()
                        binding.etName.text.clear()
                        binding.etEmail.text.clear()
                        binding.PasswordBtn.text.clear()
                        binding.confirmPasswordBtn.text.clear()
                        val intent = Intent(this,SignInActivity::class.java)
                        startActivity(intent)
                        finish()
                    }.addOnFailureListener {
                            Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                        }
                }.addOnFailureListener {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Confirm password not match", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }

    }
}



//val name = intent.getStringExtra(SignInActivity.key2)
//        val email = intent.getStringExtra(SignInActivity.key1)
//        val userId = intent.getStringExtra(SignInActivity.key3)
//
//        binding.username.text = "Name: $name"
//        binding.userEmail.text = "Email: $email"
//        binding.iddd.text ="Id: $userId"