package com.example.challenge4.ui.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge4.R
import com.example.challenge4.data.local.database.AppDatabase
import com.example.challenge4.data.local.database.entity.FriendshipEntity
import com.example.challenge4.databinding.FragmentFormBinding
import com.example.challenge4.ui.login.LoginViewModel
import com.example.challenge4.ui.login.LoginViewModelFactory

class FormFragment : Fragment() {
    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    private lateinit var formViewModel: FormViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).friendshipDatabaseDao()
        val viewModelFactory = FormViewModelFactory(dataSource, application)
        formViewModel = ViewModelProvider(this, viewModelFactory)[FormViewModel::class.java]

        binding.btnSave.setOnClickListener{ toSave() }
    }

    private fun toSave() {
        val friendName = binding.etFriendName.text.toString()
        val friendshipStatus = binding.etFriendshipStatus.text.toString()

        formViewModel.insertFriendship(FriendshipEntity(friendName = friendName, friendshipStatus = friendshipStatus))
        Toast.makeText(requireContext(), "Save Success", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_formFragment_to_homeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}