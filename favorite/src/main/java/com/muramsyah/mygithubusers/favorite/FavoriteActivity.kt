package com.muramsyah.mygithubusers.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muramsyah.core.ui.HomeAdapter
import com.muramsyah.mygithubusers.detail.DetailActivity
import com.muramsyah.mygithubusers.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val rvUser = findViewById<RecyclerView>(R.id.rv_user)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val tvEmpty = findViewById<TextView>(R.id.tv_empty)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadKoinModules(favoriteModule)

        adapter = HomeAdapter()
        adapter.onItemClick = { clickedDataUser ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, clickedDataUser)
            startActivity(intent)
        }

        rvUser.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }

        viewModel.favoriteUsers.observe(this, { favoriteUsers ->
            Log.d("FavoriteFragment", favoriteUsers.toString())
            adapter.setData(favoriteUsers)
            rvUser.adapter = adapter
            tvEmpty.visibility = if(favoriteUsers.isNotEmpty()) View.GONE else View.VISIBLE
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(favoriteModule)
    }
}