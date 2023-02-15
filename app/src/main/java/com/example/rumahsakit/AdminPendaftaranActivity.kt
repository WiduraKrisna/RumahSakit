package com.example.rumahsakit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class AdminPendaftaranActivity : AppCompatActivity() {
    private lateinit var nomer: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_pendaftaran)
        val antrian: TextView = findViewById(R.id.antrianAdmin)
        val mulai: Button = findViewById(R.id.mulaiAdmin)
        val next: Button = findViewById(R.id.nextAdmin)

        val rootRef = FirebaseDatabase.getInstance().reference
        val uidRef = rootRef.child("antrian")
        uidRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                nomer = snapshot.child("no").getValue(String::class.java).toString()
//                Toast.makeText(context,""+nomer,Toast.LENGTH_SHORT).show()
                antrian.setText(nomer)
                Log.d("antria", "nomer: $nomer")
            } else {
                Log.d("TAG", task.exception!!.message!!) //Don't ignore potential errors!
            }
        }

        mulai.setOnClickListener {
            testVoid("1")
            antrian.setText("1")
        }

        next.setOnClickListener {
            val nomernew = 1 + antrian.text.toString().toInt()
            testVoid(nomernew.toString())
            antrian.setText(nomernew.toString())
        }




    }

    fun testVoid(no: String) {
        val rootRefe = FirebaseDatabase.getInstance().reference
        val uidRefe = rootRefe.child("antrian")

        uidRefe.child("no").setValue(no);
    }
}
