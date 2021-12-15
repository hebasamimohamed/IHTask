package com.example.ihtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ihtask.adapter.ArticlesAdapter
import com.example.ihtask.adapter.RecyclerViewClickListener
import com.example.ihtask.databinding.ActivityMainBinding
import com.example.ihtask.models.Articles
import com.example.ihtask.network.ApiHelper
import com.example.ihtask.network.RetrofitBuilder
import com.example.ihtask.utils.Status
import com.example.ihtask.viewmodel.NewsViewModel
import com.example.ihtask.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(),RecyclerViewClickListener {
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: NewsViewModel
    private val articles: MutableList<Articles> = mutableListOf()
    private lateinit var mAdapter: ArticlesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeRecyclerView()
        setupViewModel()
        setupObservers()


    }
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(NewsViewModel::class.java)
    }
    private fun setupObservers() {
        viewModel.getLiveNews("apple","2021-12-14","publishedAt","d4320324d09b4b81b0190d57b235df90").observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        articles.clear()

                        mAdapter.setData(articles)

                     //  resource.data?.let { users -> articles.addAll(users.articles) }
                    }
                    Status.ERROR -> {

                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }
    private fun initializeRecyclerView() {
        mAdapter = ArticlesAdapter(this)
        binding.newsRv.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 1)
            adapter = mAdapter
        }
    }

    override fun onQualitySelected(position: Int) {
        TODO("Not yet implemented")
    }
}