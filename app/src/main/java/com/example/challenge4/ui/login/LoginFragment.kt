package com.example.challenge4.ui.login

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge4.R
import com.example.challenge4.databinding.FragmentLoginBinding
import com.example.challenge4.data.local.database.AppDatabase

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var sharedPreferences: SharedPreferences
    private val sharedPreferencesLogin = "sharedPreferencesLogin"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(sharedPreferencesLogin, MODE_PRIVATE)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).accountDatabaseDao()
        val viewModelFactory = LoginViewModelFactory(dataSource, application)
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        binding.tvDontHaveAccount.setOnClickListener{ toRegistPage() }
        binding.btnLogin.setOnClickListener{ toLoggingIn() }

        autoConnect()
    }

    private fun toLoggingIn() {
        val emailLogin = binding.etLoginEmail.text.toString()
        val passwordLogin = binding.etLoginPassword.text.toString()

        loginViewModel.readAccountById(emailLogin).observe(viewLifecycleOwner){
            if (it.email == emailLogin && it.password == passwordLogin){
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                sharedPreferences.edit {
                    this.putBoolean(PreferenceKey.PREF_USER_LOGIN_KEY.first, true)
                }
            }
            else{
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toRegistPage() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun autoConnect(){
        val connected = sharedPreferences.getBoolean(PreferenceKey.PREF_USER_LOGIN_KEY.first,
            PreferenceKey.PREF_USER_LOGIN_KEY.second)

        if (connected){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        else{

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

object PreferenceKey{
    val PREF_USER_LOGIN_KEY = Pair("PREF_LOGIN_APP_KEY", false)
}