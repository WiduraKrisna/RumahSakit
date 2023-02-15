package com.example.rumahsakit

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var context: Context
    private lateinit var pref: preferences
    private lateinit var auth: FirebaseAuth
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var test: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        context = this
        pref = preferences(context)

        val signInEmail : EditText = findViewById(R.id.signInEmail)
        val signInPassword : EditText = findViewById(R.id.signInPassword)
        val signInPasswordLayout : TextInputLayout = findViewById(R.id.signInPasswordLayout)
        val signInBtn : Button = findViewById(R.id.signInBtn)
        val signInProgressbar : ProgressBar = findViewById(R.id.signInProgressbar)


        signInBtn.setOnClickListener {
            signInPasswordLayout.isPasswordVisibilityToggleEnabled = true

            val  email = signInEmail.text.toString()
            val password = signInPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                if (email.isEmpty()) {
                    signInEmail.error = "Email tidak boleh kosong"
                }
                if (password.isEmpty()) {
                    signInPassword.error = "Password tidak boleh kosong"
                    signInPasswordLayout.isPasswordVisibilityToggleEnabled = false

                }
                signInProgressbar.visibility = View.GONE
                Toast.makeText(this, "Masukan Email Dengan Benar", Toast.LENGTH_SHORT).show()

            }else if (!email.matches(emailPattern.toRegex())) {
                signInProgressbar.visibility = View.VISIBLE
                signInEmail.error = "email Harus Di isi Dengan Benar"
                Toast.makeText(this, "Mohon Isi Password Dengan Benar", Toast.LENGTH_SHORT).show()
            }else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {


                        val rootRef = FirebaseDatabase.getInstance().reference
                        val ordersRef = rootRef.child("pengguna").orderByChild("email").equalTo(email)
                        val valueEventListener = object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (ds in dataSnapshot.children) {
                                    test = ds.child("level").getValue(String::class.java).toString()
                                    if (test != null) {
                                        //Log.d("output", test)
                                        //Toast.makeText(context,test,Toast.LENGTH_SHORT).show()
                                         if(test.equals("user")){
                                            val intent = Intent(context, UserActivity::class.java)
                                            startActivity(intent)
                                        } else {
                                            startActivity(Intent(context, AdminActivity::class.java))
                                        }
                                    }
                                }
                            }



                            override fun onCancelled(databaseError: DatabaseError) {
                                Log.d(TAG, databaseError.getMessage()) //Don't ignore errors!
                            }
                        }
                        ordersRef.addListenerForSingleValueEvent(valueEventListener)



                    }else
                        Toast.makeText(this, "Mohon Isi Password Dengan Benar", Toast.LENGTH_SHORT).show()
                }

            }
        }

        btnBacklistener()
        btnRegisterListener()


    }

    private fun btnBacklistener(){
        backSign.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun btnRegisterListener(){
        signInText.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

}
