package com.example.ihtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ihtask.R
import com.example.ihtask.databinding.NewsCellBinding
import com.example.ihtask.models.Articles

class ArticlesAdapter (
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var articles: List<Articles>? = listOf()

    fun setData(list: List<Articles>) {
        articles = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: NewsCellBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_cell,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articles!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.news = articles!![position]
//        holder.itemBinding.container.setOnClickListener {
//            listener.onRecyclerViewItemClick(
//                position
//            )
//
//        }
    }

    class ViewHolder(var itemBinding: NewsCellBinding) :
        RecyclerView.ViewHolder(itemBinding.root), LayoutContainer {
        override val containerView: View
            get() = itemBinding.root
    }


}