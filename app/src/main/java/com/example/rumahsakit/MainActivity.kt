package com.example.rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        btnloginlistener()
        btnRegisterListener()


    }

    private fun btnloginlistener(){
        btn_1.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun btnRegisterListener(){
        btn_2.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

}