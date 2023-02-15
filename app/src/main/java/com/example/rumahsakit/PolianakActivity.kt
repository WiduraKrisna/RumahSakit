package com.example.rumahsakit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class PolianakActivity : AppCompatActivity() {
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polianak)
        context = this
        val antrianU: TextView = findViewById(R.id.noAntriananak)
        val rootRef = FirebaseDatabase.getInstance().reference
        val uidRef = rootRef.child("antrian umum")
        uidRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                val nomer = snapshot.child("no").getValue(String::class.java)
//                Toast.makeText(context,""+nomer,Toast.LENGTH_SHORT).show()
                antrianU.setText(nomer)
                Log.d("antria umum", "nomer: $nomer")
            } else {
                Log.d("TAG", task.exception!!.message!!) //Don't ignore potential errors!
            }
        }



    }
}
