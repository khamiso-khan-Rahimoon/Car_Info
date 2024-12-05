package com.example.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uploadBTN.setOnClickListener {
            val intent = Intent(this,UploadActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.updateBTN.setOnClickListener {
            val intent = Intent(this,UpdateActivity2::class.java)
            startActivity(intent)

        }
        binding.deleteBTN.setOnClickListener {
            val intent = Intent(this,DeleteActivity::class.java)
            startActivity(intent)

        }

    }
}