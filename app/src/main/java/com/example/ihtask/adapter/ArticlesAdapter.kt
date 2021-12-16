package com.example.ihtask.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ihtask.R
import com.example.ihtask.databinding.NewsCellBinding
import com.example.ihtask.models.Articles
import com.example.ihtask.utils.BindingUtils

class ArticlesAdapter(
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>(), Filterable {

    private var articles: List<Articles>? = listOf()
    var articlesFiltered: List<Articles>? = listOf()

    fun setData(list: List<Articles>) {
        articles = list
        articlesFiltered = articles

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
        return articlesFiltered!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.news = articlesFiltered!![position]
        holder.itemBinding.duration.text = BindingUtils.getSourceAndTime(
            articles!![position].source.name,
            articles!![position].publishedAt
        )
        holder.itemBinding.container.setOnClickListener {
            listener.onArticleSelected(
                position
            )

        }
    }

    class ViewHolder(var itemBinding: NewsCellBinding) :
        RecyclerView.ViewHolder(itemBinding.root), LayoutContainer {
        override val containerView: View
            get() = itemBinding.root
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString() ?: ""
                if (charString.isEmpty()) articlesFiltered = articles else {
                    val filteredList: MutableList<Articles> = mutableListOf()
                    articles
                        ?.filter {
                            (it.author.contains(constraint))

                        }
                        ?.forEach { filteredList.add(it) }
                    articlesFiltered = filteredList

                    Log.e("performFiltering: t1: ", filteredList.size.toString())

                }
                return FilterResults().apply { values = articlesFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                articlesFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as List<Articles>?
                notifyDataSetChanged()


            }
        }
    }


}