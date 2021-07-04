package com.muramsyah.mygithubusers.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.muramsyah.mygithubusers.core.data.source.Resource
import com.muramsyah.mygithubusers.core.domain.model.DetailUser
import com.muramsyah.mygithubusers.core.domain.model.User
import com.muramsyah.mygithubusers.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val detailUser = intent.getParcelableExtra<User>(EXTRA_DATA)

        if (detailUser != null) {
            viewModel.getDetailUser(detailUser.login).observe(this, Observer { user ->
                when (user) {
                    is Resource.Loading -> {
                        Log.d("DetailActivity", "Loading")
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        Log.d("DetailActivity", user.data.toString())
                        binding.progressBar.visibility = View.GONE
                        user.data?.let { showDetailUser(it) }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Log.d("DetailActivity", "Error")
                    }
                }
            })
        }
    }

    private fun showDetailUser(detailUser: DetailUser) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(detailUser.avatarUrl)
                .into(binding.imgUser)

            tvUsername.text = "@${detailUser.login}"
            tvFullname.text = detailUser.name
            tvFollowers.text = detailUser.followers.toString()
            tvFollowing.text = detailUser.following.toString()
            tvRepository.text = detailUser.publicRepos.toString()
        }
    }
}