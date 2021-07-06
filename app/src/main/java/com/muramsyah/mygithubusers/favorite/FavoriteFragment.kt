package com.muramsyah.mygithubusers.favorite

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
import com.muramsyah.mygithubusers.core.data.source.Resource
import com.muramsyah.mygithubusers.core.ui.HomeAdapter
import com.muramsyah.mygithubusers.databinding.FragmentUsersBinding
import com.muramsyah.mygithubusers.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()

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

            viewModel.favoriteUsers.observe(viewLifecycleOwner, Observer { favoriteUsers ->
                Log.d("FavoriteFragment", favoriteUsers.toString())
                adapter.setData(favoriteUsers)
                binding.rvUser.adapter = adapter
                binding.layoutItemViewEmpty.root.visibility = if(favoriteUsers.isNotEmpty()) View.GONE else View.VISIBLE
            })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}