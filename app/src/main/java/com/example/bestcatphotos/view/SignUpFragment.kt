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
import com.example.bestcatphotos.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater)
        binding.logInButton.setOnClickListener {
            val passwordFirst = binding.passwordEt.text.toString()
            val passwordSecond = binding.passwordConfirmEt.text.toString()
            val email = binding.emailEt.text.toString()
            if (passwordFirst != "" && passwordSecond != "" && email != "") {
                if (!checkIfEmailIsValid(email)) {
                    showErrorToast("Email is not valid.")
                } else if (!checkIfPasswordsmatches(passwordFirst, passwordSecond)) {
                    showErrorToast("Passwords do not match.")
                } else {
                    signUp(email, passwordFirst)
                }
            } else {
                showErrorToast("Fields cannot be empty.")
            }
        }
        binding.toLogIn.setOnClickListener {
            toLogIn()
        }
        auth = Firebase.auth
        return binding.root
    }

    private fun checkIfPasswordsmatches(password: String, passwordConfirm: String): Boolean {
        var passwordsMatche = true
        if (password != passwordConfirm) {
            passwordsMatche = false
        }
        return passwordsMatche
    }

    private fun checkIfEmailIsValid(email: String): Boolean {
        var emailIsValid = true
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!email.matches(emailPattern.toRegex())) {
            emailIsValid = false
        }
        return emailIsValid
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Account created.", Toast.LENGTH_SHORT).show()
                    toLogIn()
                } else {
                    val message = it.exception.toString()
                    Log.d(TAG, message)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun toLogIn() {
        val action = SignUpFragmentDirections.actionSignUpFragment2ToLogInFragment()
        findNavController().navigate(action)
    }
}