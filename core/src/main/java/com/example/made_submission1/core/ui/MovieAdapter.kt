package com.example.made_submission1.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.made_submission1.core.R
import com.example.made_submission1.core.BuildConfig
import com.example.made_submission1.core.databinding.RvHomeItemBinding
import com.example.made_submission1.core.domain.model.Movies

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>(){
    private var listData = ArrayList<Movies>()
    private lateinit var activity: FragmentActivity
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(newListData: List<Movies>?, activity: FragmentActivity) {
        this.activity = activity

        if  (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_home_item, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RvHomeItemBinding.bind(itemView)

        fun bind(data: Movies) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${BuildConfig.LINK_IMAGE}${data.posterPath}")
                    .into(ivHomeItem)

                tvHomeItemTitle.text = data.title
                tvHomeItemDesc.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}