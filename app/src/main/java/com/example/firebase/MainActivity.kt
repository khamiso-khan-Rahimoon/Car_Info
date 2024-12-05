package com.example.firebase

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.searchbtn.setOnClickListener{
           val searchterm =  binding.etsearch.text.toString()
            if (searchterm.isNotEmpty()){
            readData(searchterm)
            }else{

                Toast.makeText(this, "please enter the vehicle Number", Toast.LENGTH_SHORT).show()
            }
        }

        
    }
    
    private fun readData(vehicleNo:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNo).get()
            .addOnSuccessListener { 
                if (it.exists()){
                    val ownername = it.child("ownerName").value
                    val vBrand = it.child("vehicleBrand").value
                    val vRTO = it.child("vehicleRTO").value
                    Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                    binding.etsearch.text.clear()
                    binding.tvownerName.text = ownername.toString()
                    binding.tvvehicleNOomber.text = vBrand.toString()
                    binding.tvvehicleRTO.text = vRTO?.toString()
                }else{
                    Toast.makeText(this, "Vehicle Number does not exists", Toast.LENGTH_SHORT).show()
                    
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }
}


// Initialize Firebase Database reference
//databaseReference = FirebaseDatabase.getInstance().getReference("users")
//binding.alreadysignIn.setOnClickListener{
//    val intent = Intent(this,SignInActivity::class.java)
//    startActivity(intent)
//}
//
//binding.signupBTN.setOnClickListener {
//    val name = binding.etname.text.toString().trim()
//    val email = binding.etemail.text.toString().trim()
//    val password = binding.editTextText3.text.toString().trim()
//    val uniqueIdd = name
//
//    if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
//        Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
//    } else {
//
//        val user = DataClass(name, email, password, uniqueIdd )
//        databaseReference.child(name).setValue(user)
//            .addOnSuccessListener {
//                Toast.makeText(this, "SignUp Successful", Toast.LENGTH_SHORT).show()
//                binding.etname.text.clear()
//                binding.etemail.text.clear()
//                binding.editTextText3.text?.clear()
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "SignUp failed", Toast.LENGTH_SHORT).show()
//            }
//    }
//}