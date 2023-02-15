package com.example.rumahsakit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class AdminUnunActivity : AppCompatActivity() {
    private lateinit var nomer: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_unun)
        val antrian2: TextView = findViewById(R.id.antrianUmum)
        val mulai2: Button = findViewById(R.id.mulaiUmum)
        val next2: Button = findViewById(R.id.nextUmum)

        val rootRef = FirebaseDatabase.getInstance().reference
        val uidRef = rootRef.child("admin antrian umum")
        uidRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                nomer = snapshot.child("no").getValue(String::class.java).toString()
//                Toast.makeText(context,""+nomer,Toast.LENGTH_SHORT).show()
                antrian2.setText(nomer)
                Log.d("admin antria umum", "nomer: $nomer")
            } else {
                Log.d("TAG", task.exception!!.message!!) //Don't ignore potential errors!
            }
        }

        mulai2.setOnClickListener {
            testVoid("1")
            antrian2.setText("1")
        }

        next2.setOnClickListener {
            val nomernew = 1 + antrian2.text.toString().toInt()
            testVoid(nomernew.toString())
            antrian2.setText(nomernew.toString())
        }




    }

    fun testVoid(no: String) {
        val rootRefe = FirebaseDatabase.getInstance().reference
        val uidRefe = rootRefe.child("antrian umum")

        uidRefe.child("no").setValue(no);
    }
}