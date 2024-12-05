package com.example.admin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityUpdate2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity2 : AppCompatActivity() {
     private lateinit var binding: ActivityUpdate2Binding
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdate2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updatSavebtn.setOnClickListener {
            val vno = binding.updatevehicalNO.text.toString()
            val vrto = binding.etupdateRTO.text.toString()
            val vbrand = binding.etupdatedbrand.text.toString()
            val name = binding.updareowner.text.toString()

            updateData(vno,name,vbrand,vrto)

        }

    }

    private fun updateData(vehicalNumber:String,ownername:String,vehicleBrand:String,vehicleRTO:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val vehicleData = mapOf<String,String>("ownerName" to ownername, "vehicleBrand" to vehicleBrand, "vehicleRTO" to vehicleRTO )
        databaseReference.child(vehicalNumber).get()
            .addOnSuccessListener {
               if (it.exists()){
                   databaseReference.child(vehicalNumber).updateChildren(vehicleData)
                       .addOnSuccessListener {
                           binding.etupdateRTO.text.clear()
                           binding.updareowner.text.clear()
                           binding.etupdatedbrand.text.clear()
                           binding.updatevehicalNO.text.clear()
                           Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
                       }.addOnFailureListener {
                           Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                       }
                   
               }else{
                   Toast.makeText(this, "data does not exists", Toast.LENGTH_SHORT).show()
               }

                 


            }
            .addOnFailureListener {
                Toast.makeText(this, "Unable to update", Toast.LENGTH_SHORT).show()
            }

    }


}