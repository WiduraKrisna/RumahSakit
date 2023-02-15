package com.example.rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ambulanrs.*

class AmbulanrsActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambulanrs)
        auth = FirebaseAuth.getInstance()

        rkslistener()


    }

    private fun rkslistener(){
        ambulan2.setOnClickListener {
            startActivity(Intent(this, AmbulanRangkasActivity::class.java))
        }
    }
}