package com.muramsyah.mygithubusers.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.muramsyah.core.ui.HomeAdapter
import com.muramsyah.mygithubusers.databinding.FragmentUsersBinding
import com.muramsyah.mygithubusers.detail.DetailActivity
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
            adapter.onItemClick = { clickedDataUser ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, clickedDataUser)
                startActivity(intent)
            }

            binding.rvUser.apply {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                setHasFixedSize(true)
            }

            viewModel.getAllUser.observe(viewLifecycleOwner, Observer { users ->
                when (users) {
                    is com.muramsyah.core.data.Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Log.d("UsersFragment", users.data.toString())
                    }
                    is com.muramsyah.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d("UsersFragment", users.data.toString())
                        adapter.setData(users.data)
                        binding.rvUser.adapter = adapter
                    }
                    is com.muramsyah.core.data.Resource.Error -> {
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