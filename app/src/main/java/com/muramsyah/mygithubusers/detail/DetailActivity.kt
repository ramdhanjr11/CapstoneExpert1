package com.muramsyah.mygithubusers.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.muramsyah.mygithubusers.R
import com.muramsyah.mygithubusers.core.data.source.Resource
import com.muramsyah.mygithubusers.core.domain.model.DetailUser
import com.muramsyah.mygithubusers.core.domain.model.User
import com.muramsyah.mygithubusers.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModel()
    private var menu: Menu? = null

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
            viewModel.getDetailUser(detailUser.login).observe(this, Observer { user ->
                when (user) {
                    is Resource.Loading -> {
                        Log.d("DetailActivity", "Loading")
                        setVisibilityLayout(false)
                    }
                    is Resource.Success -> {
                        Log.d("DetailActivity", user.data.toString())
                        setVisibilityLayout(true)
                        user.data?.let { showDetailUser(it) }
                    }
                    is Resource.Error -> {
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

        }
    }

    private fun setVisibilityLayout(state: Boolean) {
        if (state) {
            with(binding.layoutItemDetailHeader) {
                view.visibility = View.VISIBLE
                view2.visibility = View.VISIBLE
                tvDetailOf.visibility = View.VISIBLE
                tvLocation.visibility = View.VISIBLE
                tvCompany.visibility = View.VISIBLE
                tvBlog.visibility = View.VISIBLE
                tvBio.visibility = View.VISIBLE
                tvFollowing.visibility = View.VISIBLE
                tvFollowers.visibility = View.VISIBLE
                tvFullname.visibility = View.VISIBLE
                tvRepository.visibility = View.VISIBLE
                textView2.visibility = View.VISIBLE
                textView4.visibility = View.VISIBLE
                textView6.visibility = View.VISIBLE
                textView8.visibility = View.VISIBLE
            }
            binding.progressBar.visibility = View.GONE
        } else {
            with(binding.layoutItemDetailHeader) {
                view.visibility = View.GONE
                view2.visibility = View.GONE
                tvDetailOf.visibility = View.GONE
                tvLocation.visibility = View.GONE
                tvCompany.visibility = View.GONE
                tvBlog.visibility = View.GONE
                tvBio.visibility = View.GONE
                tvFollowing.visibility = View.GONE
                tvFollowers.visibility = View.GONE
                tvFullname.visibility = View.GONE
                tvRepository.visibility = View.GONE
                textView2.visibility = View.GONE
                textView4.visibility = View.GONE
                textView6.visibility = View.GONE
                textView8.visibility = View.GONE
            }
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_activity, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite) {
            if (!isFavorite) {
                isFavorite = !isFavorite
                setFavoriteState(true)
                Toast.makeText(this, "Kamu menyimpan user", Toast.LENGTH_SHORT).show()
            } else {
                isFavorite = !isFavorite
                setFavoriteState(false)
                Toast.makeText(this, "Kamu menghapus user", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setFavoriteState(state: Boolean) {
        if (state) {
            menu?.findItem(R.id.favorite)!!.icon = getDrawable(R.drawable.ic_baseline_favorite)
        } else {
            menu?.findItem(R.id.favorite)!!.icon = getDrawable(R.drawable.ic_baseline_favorite_border)
        }
    }
}