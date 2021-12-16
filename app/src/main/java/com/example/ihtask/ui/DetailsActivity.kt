package com.example.ihtask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ihtask.databinding.ActivityDetailsBinding
import com.example.ihtask.models.Articles
import com.example.ihtask.utils.BindingUtils
import com.example.ihtask.utils.Constants
import com.squareup.picasso.Picasso
import android.content.Intent
import android.graphics.Paint
import android.net.Uri


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var articles: Articles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val i = intent
        articles = i.getSerializableExtra(Constants.SELECTED_ARTICLE) as Articles
        binding.author.text = articles.author
        Picasso.get().load(articles.urlToImage).into(binding.newsImg)
        binding.newsTitle.text = articles.title
        binding.newsDescription.text = articles.description
        binding.source.text = BindingUtils.getSourceAndTime(
            articles.source.name,
            articles.publishedAt
        )
        binding.source.paintFlags = binding.source.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        binding.back.setOnClickListener {
            finish()
        }
        binding.source.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(articles.url)
            startActivity(i)
        }

    }
}