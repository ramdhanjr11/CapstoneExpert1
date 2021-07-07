package com.muramsyah.mygithubusers.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muramsyah.mygithubusers.core.domain.model.User
import com.muramsyah.mygithubusers.databinding.ItemUserLayoutBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {

    private val mData = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null

    fun setData(data: List<User>?) {
        if (data == null) return
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class ListViewHolder(private val binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            with(binding) {
                Log.d("avatarUrl", data.avatarUrl)
                Glide.with(itemView.context)
                    .load(data.avatarUrl)
                    .into(imgItemUser)

                tvUsername.text = data.login
                tvUrl.text = data.url
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[bindingAdapterPosition])
            }
        }
    }
}