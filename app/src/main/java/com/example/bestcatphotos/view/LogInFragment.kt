package com.example.bestcatphotos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bestcatphotos.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
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
            logIn()
        }
        return binding.root
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