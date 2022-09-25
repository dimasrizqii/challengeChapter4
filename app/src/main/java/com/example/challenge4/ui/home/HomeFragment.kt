package com.example.challenge4.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge4.R
import com.example.challenge4.data.local.database.AppDatabase
import com.example.challenge4.data.local.database.entity.FriendshipEntity
import com.example.challenge4.databinding.FragmentHomeBinding
import com.example.challenge4.ui.form.FormViewModelFactory
import com.example.challenge4.ui.home.adapter.HomeAdapter
import com.example.challenge4.ui.home.adapter.HomeItemClickListener
import com.example.challenge4.utils.ClipboardUtils

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).friendshipDatabaseDao()
        val viewModelFactory = FormViewModelFactory(dataSource, application)
        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        val recyclerView = binding.rvFriendshipList
        adapter = HomeAdapter(object: HomeItemClickListener{
            override fun onItemClicked(item: FriendshipEntity) {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClicked(item: FriendshipEntity) {
                context?.let {
                    ClipboardUtils.copyToClipboard(
                        it,
                        item.friendName ?: "",
                        item.friendshipStatus.orEmpty()
                    )
                }
                Toast.makeText(
                    context,
                    "Password Copied to Clipboard",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onDeleteMenuClicked(item: FriendshipEntity) {
                toDelete(item)
                homeViewModel.deleteFriendship(item)
                Toast.makeText(requireContext(), "Delete Success", Toast.LENGTH_SHORT).show()
            }

            override fun onEditMenuClicked(item: FriendshipEntity) {
                Toast.makeText(requireContext(), "Edit Success", Toast.LENGTH_SHORT).show()
            }

        })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        homeViewModel.readFriendship().observe(viewLifecycleOwner){
            adapter.setItems(it)
        }
        binding.fabAddData.setOnClickListener{ toForm() }
    }

    private fun toDelete(item: FriendshipEntity) {
        adapter.deleteItems(item)
    }

    private fun toForm() {
        findNavController().navigate(R.id.action_homeFragment_to_formFragment)
    }

}