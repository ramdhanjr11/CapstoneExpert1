package com.muramsyah.mygithubusers.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.muramsyah.core.data.Resource
import com.muramsyah.core.ui.HomeAdapter
import com.muramsyah.mygithubusers.R
import com.muramsyah.mygithubusers.databinding.ActivityMainBinding
import com.muramsyah.mygithubusers.databinding.FragmentUsersBinding
import com.muramsyah.mygithubusers.detail.DetailActivity
import com.muramsyah.mygithubusers.search.SearchActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        adapter = HomeAdapter()
        adapter.onItemClick = { clickedDataUser ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, clickedDataUser)
            startActivity(intent)
        }

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }

        viewModel.getAllUser.observe(this, Observer { users ->
            when (users) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("MainActivity", users.data.toString())
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("MainActivity", users.data.toString())
                    adapter.setData(users.data)
                    binding.rvUser.adapter = adapter
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("MainActivity", users.message.toString())
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.action_favorite -> {
                val uri = Uri.parse("mygithubusers://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}