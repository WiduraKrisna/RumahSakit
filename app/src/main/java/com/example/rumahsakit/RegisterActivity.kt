package com.example.rumahsakit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference


        val signUpName: EditText = findViewById(R.id.signUpName)
        val signUpEmail: EditText = findViewById(R.id.signUpEmail)
        val signUpPhone: EditText = findViewById(R.id.signUpPhone)
        val signUpPassword: EditText = findViewById(R.id.signUpPassword)
        val signUpCPassword: EditText = findViewById(R.id.signUpCPassword)
        val signUpPasswordLayout: TextInputLayout = findViewById(R.id.signUpPasswordLayout)
        val signUpCPasswordLayout: TextInputLayout = findViewById(R.id.signUpCPasswordLayout)
        val signUpBtn: Button = findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val name = signUpName.text.toString()
            val email = signUpEmail.text.toString()
            val phone = signUpPhone.text.toString()
            val password = signUpPassword.text.toString()
            val cPassword = signUpCPassword.text.toString()

            signUpPasswordLayout.isPasswordVisibilityToggleEnabled = true
            signUpCPasswordLayout.isPasswordVisibilityToggleEnabled = true


            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || cPassword.isEmpty()) {
                if (name.isEmpty()) {
                    signUpName.error = "Full Name Harus Diisi"
                }
                if (email.isEmpty()) {
                    signUpEmail.error = "Full Name Harus Diisi"
                }
                if (phone.isEmpty()) {
                    signUpPhone.error = "Full Name Harus Diisi"
                }
                if (password.isEmpty()) {
                    signUpPasswordLayout.isPasswordVisibilityToggleEnabled = false
                    signUpPassword.error = "Full Name Harus Diisi"
                }
                if (cPassword.isEmpty()) {
                    signUpCPasswordLayout.isPasswordVisibilityToggleEnabled = false
                    signUpCPassword.error = "Full Name Harus Diisi"
                }
                Toast.makeText(this, "Mohon Isi Dengan Benar", Toast.LENGTH_SHORT).show()
            }else if (!email.matches(emailPattern.toRegex())) {
                signUpEmail.error = "Email Harus Di isi Dengan Benar"
                Toast.makeText(this, "Mohon Isi email Dengan Benar", Toast.LENGTH_SHORT).show()
            }else if(phone.length != 12){
                signUpPhone.error = "No Telpon Harus Di isi Dengan Benar"
                Toast.makeText(this, "Mohon Isi nomor telpon Dengan Benar", Toast.LENGTH_SHORT).show()
            }else if(password.length < 10){
                signUpPasswordLayout.isPasswordVisibilityToggleEnabled = true
                signUpPassword.error = "Password Harus 6 Karakter "
                Toast.makeText(this, "Mohon Isi Password Dengan Benar", Toast.LENGTH_SHORT).show()
            }else if(password != cPassword){
                signUpCPasswordLayout.isPasswordVisibilityToggleEnabled = true
                signUpCPassword.error = "Password Tidak Sama, Ulang Lagi"
                Toast.makeText(this, "Mohon Isi Password Yang Sama Dengan Benar", Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful){

                        val databaseRef = database.child("pengguna").push()
                        val user : User = User(name, email, phone, auth.currentUser!!.uid,password,"user")

                        databaseRef.setValue(user).addOnCompleteListener {
                            if (it.isSuccessful){
                                val intent = Intent(this, RegisterActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this, "Ada yang salah,Ulang kembali", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }else{
                        Toast.makeText(this, "Ada yang slah,Ulang kembali", Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }


        btnloginlistener()
        btnBackListener()
    }


    private fun btnloginlistener(){
        signUpText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun btnBackListener(){
        R_img_1.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}