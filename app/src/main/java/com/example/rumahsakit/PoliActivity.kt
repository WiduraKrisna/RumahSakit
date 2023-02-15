package com.example.rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_poli.*

class PoliActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poli)

        auth = FirebaseAuth.getInstance()

        poli1Listener()
        poli2Listener()
    }
    private fun poli1Listener(){
        poli1.setOnClickListener {
            startActivity(Intent(this, AntrianPendaftaranActivity::class.java))
        }
    }

    private fun poli2Listener(){
        poli2.setOnClickListener {
            startActivity(Intent(this, PoliUmumActivity::class.java))
        }
    }
}