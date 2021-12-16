package com.example.ihtask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ihtask.adapter.ArticlesAdapter
import com.example.ihtask.adapter.RecyclerViewClickListener
import com.example.ihtask.databinding.ActivityMainBinding
import com.example.ihtask.models.Articles
import com.example.ihtask.repo.AppRepository
import com.example.ihtask.utils.Resource
import com.example.ihtask.utils.errorSnack
import com.example.ihtask.viewmodel.NewsViewModel
import com.example.ihtask.viewmodel.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: ArticlesAdapter
    private lateinit var viewModel: NewsViewModel
    private val articles: MutableList<Articles> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeRecyclerView()
        setupViewModel()

    }


    private fun initializeRecyclerView() {
        mAdapter = ArticlesAdapter(this)
        binding.newsRv.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 1)
            adapter = mAdapter
        }
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        getArticles()
    }

    private fun getArticles() {
        viewModel.articlesDATA.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { articlesResponse ->
                        articles.clear()
                        articles.addAll(articlesResponse.articles.toMutableList())
                        mAdapter.setData(articles)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        binding.rootLayout.errorSnack(message, Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun onQualitySelected(position: Int) {
        TODO("Not yet implemented")
    }
}