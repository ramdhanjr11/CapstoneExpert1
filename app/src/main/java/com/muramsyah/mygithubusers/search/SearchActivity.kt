package com.muramsyah.mygithubusers.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muramsyah.mygithubusers.R
import com.muramsyah.mygithubusers.core.ui.HomeAdapter
import com.muramsyah.mygithubusers.databinding.ActivitySearchBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class SearchActivity : AppCompatActivity() {

    private lateinit var adapter: HomeAdapter

    private val viewModel: SearchViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val actionBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(actionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val edtSearch = findViewById<EditText>(R.id.edt_search)
        val rvUser = findViewById<RecyclerView>(R.id.rv_user)

        edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    viewModel.queryChannel.send(s.toString())
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        adapter = HomeAdapter()

        rvUser.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }

        viewModel.searchResult.observe(this, Observer { users ->
            if (users.isNotEmpty()) {
                adapter.setData(users)
                rvUser.adapter = adapter
                rvUser.setHasFixedSize(true)
            } else {
                Log.d("SearchActivity", "Empty data")
            }
        })
    }
}