package com.muramsyah.mygithubusers.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.muramsyah.mygithubusers.R
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
        private var isFavorite = false
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
            if (detailUser.isFavorite) {
                isFavorite = detailUser.isFavorite
                setFavoriteState(true)
            } else {
                isFavorite = detailUser.isFavorite
                setFavoriteState(false)
            }

            viewModel.getDetailUser(detailUser.login).observe(this, Observer { user ->
                when (user) {
                    is com.muramsyah.mygithubusers.core.data.Resource.Loading -> {
                        Log.d("DetailActivity", "Loading")
                        setVisibilityLayout(false)
                    }
                    is com.muramsyah.mygithubusers.core.data.Resource.Success -> {
                        Log.d("DetailActivity", user.data.toString())
                        setVisibilityLayout(true)
                        user.data?.let { showDetailUser(it) }
                    }
                    is com.muramsyah.mygithubusers.core.data.Resource.Error -> {
                        setVisibilityLayout(false)
                        Log.d("DetailActivity", "Error")
                    }
                }
            })
        }
    }

    private fun showDetailUser(detailUser: DetailUser) {
        with(binding.layoutItemDetailHeader) {
            Glide.with(this@DetailActivity)
                .load(detailUser.avatarUrl)
                .into(imgUser)

            tvUsername.text = getString(R.string.username, detailUser.login)
            tvFullname.text = detailUser.name
            tvFollowers.text = getString(R.string.followers, detailUser.followers)
            tvFollowing.text = getString(R.string.followers, detailUser.following)
            tvRepository.text = getString(R.string.followers, detailUser.publicRepos)
            tvDetailOf.text = getString(R.string.detail_user_item_detail, detailUser.login)

            if (detailUser.bio == null) tvBio.text = getString(R.string.null_text) else tvBio.text = detailUser.bio
            if (detailUser.blog == null) tvBlog.text = getString(R.string.null_text) else tvBlog.text = detailUser.blog
            if (detailUser.company == null) tvCompany.text = getString(R.string.null_text) else tvCompany.text = detailUser.company
            if (detailUser.location == null) tvLocation.text = getString(R.string.null_text) else tvLocation.text = detailUser.location

            binding.fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                viewModel.setFavoriteUser(detailUser, isFavorite)
                setFavoriteState(isFavorite)
            }
        }
    }

    private fun setVisibilityLayout(state: Boolean) {
        if (state) {
            binding.layoutItemDetailHeader.root.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        } else {
            binding.layoutItemDetailHeader.root.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border))
        }
    }
}