package com.example.bestcatphotos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bestcatphotos.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater)
        binding.logInButton.setOnClickListener {
            toLogIn()
        }
        binding.toLogIn.setOnClickListener {
            toLogIn()
        }
        return binding.root
    }

    private fun toLogIn() {
        val action = SignUpFragmentDirections.actionSignUpFragment2ToLogInFragment()
        findNavController().navigate(action)
    }
}