package com.example.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            Firebase.auth.createUserWithEmailAndPassword(binding.email.editText?.text.toString(),
            binding.password.editText?.text.toString()).addOnCompleteListener {

                if(it.isSuccessful)
                {
                    Toast.makeText(this,"User Created!!!",Toast.LENGTH_LONG).show()

                    val intent= Intent(this,QuizActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"User  not Created!!!",Toast.LENGTH_LONG).show()
                }
            }
        }


    }
}