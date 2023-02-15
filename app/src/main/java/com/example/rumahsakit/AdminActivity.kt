package com.example.rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin.*

class AdminActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        admpoli1listener()
        admpoli2Listener()
    }
    private fun admpoli1listener(){
        admpoli1.setOnClickListener {
            startActivity(Intent(this, AdminPendaftaranActivity::class.java))
        }
    }

    private fun admpoli2Listener(){
        admpoli2.setOnClickListener {
            startActivity(Intent(this, AdminUnunActivity::class.java))
        }
    }
}