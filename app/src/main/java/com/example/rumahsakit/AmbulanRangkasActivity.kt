package com.example.rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_ambulan_rangkas.*

class AmbulanRangkasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambulan_rangkas)
        mapsListener()
    }

    private fun mapsListener() {
        btnMaps.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }
}