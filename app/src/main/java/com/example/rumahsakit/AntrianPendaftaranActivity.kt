package com.example.rumahsakit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class AntrianPendaftaranActivity : AppCompatActivity() {
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antrian_pendaftaran)
        context = this

        val antrian: TextView = findViewById(R.id.noAntrian)

        val rootRef = FirebaseDatabase.getInstance().reference
        val uidRef = rootRef.child("antrian")
        uidRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                val nomer = snapshot.child("no").getValue(String::class.java)
//                Toast.makeText(context,""+nomer,Toast.LENGTH_SHORT).show()
                antrian.setText(nomer)
                Log.d("antria", "nomer: $nomer")
            } else {
                Log.d("TAG", task.exception!!.message!!) //Don't ignore potential errors!
            }
        }



    }
}