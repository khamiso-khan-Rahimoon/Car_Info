package com.example.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.savebutton.setOnClickListener {
            val ownerName = binding.etOwnername.text.toString().trim()
            val vehicleBrand = binding.hehicleBrenadEt.text.toString().trim()
            val vehicleRTo = binding.etvehicalRTo.text.toString().trim()
            val vehicleNo = binding.etVehicaleNo.text.toString().trim()

            databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")

            val vehicleInfo = VehicleData(ownerName,vehicleBrand,vehicleRTo,vehicleNo)
            databaseReference.child(vehicleNo).setValue(vehicleInfo)

                .addOnSuccessListener {
                   binding.etOwnername.text.clear()
                   binding.hehicleBrenadEt.text.clear()
                   binding.etvehicalRTo.text.clear()
                    binding.etVehicaleNo.text.clear()
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()


            }
                .addOnFailureListener {

                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }


    }
}