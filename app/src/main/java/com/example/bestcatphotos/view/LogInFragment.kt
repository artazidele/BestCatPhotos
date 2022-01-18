package com.example.bestcatphotos.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bestcatphotos.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater)
        binding.toSignUp.setOnClickListener {
            toSignUp()
        }
        binding.logInButton.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (email == "" || password == "") {
                Toast.makeText(context, "Fields cannot be empty.", Toast.LENGTH_SHORT).show()
            } else {
                signIn(email, password)
            }
        }
        binding.forgotPasswordButton.setOnClickListener {
            val email = binding.emailEt.text.toString()
            if (email != "") {
                forgetPassword(email)
            } else {
                Toast.makeText(context, "Email field cannot be empty.", Toast.LENGTH_SHORT).show()
            }
        }
        auth = Firebase.auth
        return binding.root
    }

    private fun forgetPassword(email: String) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Password reset email is sent.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Email may be incorrect.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    logIn()
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Email or password is incorrect.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun logIn() {
        val action = LogInFragmentDirections.actionLogInFragmentToCatPhotoFragment()
        findNavController().navigate(action)
    }

    private fun toSignUp() {
        val action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment2()
        findNavController().navigate(action)
    }
}
