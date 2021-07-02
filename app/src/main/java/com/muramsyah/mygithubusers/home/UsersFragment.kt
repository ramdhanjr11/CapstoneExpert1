package com.muramsyah.mygithubusers.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.muramsyah.mygithubusers.R
import com.muramsyah.mygithubusers.core.data.source.Resource
import org.koin.android.viewmodel.ext.android.viewModel


class UsersFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            viewModel.getAllUser.observe(viewLifecycleOwner, Observer { users ->
                when (users) {
                    is Resource.Loading -> {
                        // do something
                        Log.d("UsersFragment", users.data.toString())
                    }
                    is Resource.Success -> {
                        Log.d("UsersFragment", users.data.toString())
                    }
                    is Resource.Error -> {
                        Log.e("UsersFragment", users.message.toString())
                    }
                }
            })

        }
    }

}