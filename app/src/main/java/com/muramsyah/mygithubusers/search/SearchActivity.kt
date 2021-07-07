package com.muramsyah.mygithubusers.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.muramsyah.core.ui.HomeAdapter
import com.muramsyah.mygithubusers.databinding.ActivitySearchBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class SearchActivity : AppCompatActivity() {

    private lateinit var adapter: HomeAdapter
    private lateinit var binding: ActivitySearchBinding

    private val viewModel: SearchViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    viewModel.queryChannel.send(s.toString())
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        adapter = HomeAdapter()

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }

        viewModel.searchResult.observe(this, Observer { users ->
            if (users.isNotEmpty()) {
                adapter.setData(users)
                binding.rvUser.adapter = adapter
                binding.rvUser.setHasFixedSize(true)
            } else {
                Log.d("SearchActivity", "Empty data")
            }
        })

        adapter.onItemClick = { clickedDataUser ->
            Toast.makeText(this, "Kamu memilih ${clickedDataUser.login}!", Toast.LENGTH_SHORT).show()
        }
    }
}