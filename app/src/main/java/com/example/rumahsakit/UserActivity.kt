package com.example.rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        auth = FirebaseAuth.getInstance()

        antrianListener()
        ambulanListener()
    }
    private fun antrianListener(){
        antrian1.setOnClickListener {
            startActivity(Intent(this, PoliActivity::class.java))
        }
    }
    private fun ambulanListener(){
        ambulan123.setOnClickListener {
            startActivity(Intent(this, AmbulanrsActivity::class.java))
        }
    }
}