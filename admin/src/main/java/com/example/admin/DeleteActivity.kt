package com.example.admin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deletebtnn.setOnClickListener{
            val vno = binding.etdelete.text.toString()
            if (vno.isNotEmpty()){
            deleteData(vno)
            
            }else{
                Toast.makeText(this, "Please Enter the vehicle Number", Toast.LENGTH_SHORT).show()
            }
            
            
        }
        
       
    }
    private fun deleteData(vehicleNumber:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).get()
            .addOnSuccessListener { 
                if (it.exists()){
                    databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener { 
                        binding.etdelete.text.clear()
                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Data does not exists", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Unable to get data", Toast.LENGTH_SHORT).show()
            }
        
    }
}