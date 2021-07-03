package com.muramsyah.mygithubusers.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.muramsyah.mygithubusers.R
import com.muramsyah.mygithubusers.core.data.source.Resource
import com.muramsyah.mygithubusers.core.ui.HomeAdapter
import com.muramsyah.mygithubusers.databinding.FragmentUsersBinding
import org.koin.android.viewmodel.ext.android.viewModel


class UserFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            adapter = HomeAdapter()
            adapter.onItemClick = { clickedUser ->
                Toast.makeText(context, "Kamu memilih ${clickedUser.login}", Toast.LENGTH_SHORT).show()
            }

            binding.rvUser.layoutManager = LinearLayoutManager(context)
            binding.rvUser.setHasFixedSize(true)

            viewModel.getAllUser.observe(viewLifecycleOwner, Observer { users ->
                when (users) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Log.d("UsersFragment", users.data.toString())
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d("UsersFragment", users.data.toString())
                        adapter.setData(users.data)
                        binding.rvUser.adapter = adapter
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Log.e("UsersFragment", users.message.toString())
                    }
                }
            })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}