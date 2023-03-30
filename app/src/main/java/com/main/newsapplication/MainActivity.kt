package com.main.newsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.main.newsapplication.databinding.ActivityMainBinding
import com.main.newsapplication.model.repository.NewsRepository
import com.main.newsapplication.model.repository.db.ArticleDatabase
import com.main.newsapplication.viewModel.NewsViewModel
import com.main.newsapplication.viewModel.NewsViewModelFactory
import androidx.navigation.ui.setupWithNavController


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel:NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val newsRepository=NewsRepository(ArticleDatabase(this))
        val viewModelProvider= NewsViewModelFactory(newsRepository)
        viewModel=ViewModelProvider(this,viewModelProvider).get(NewsViewModel::class.java)
        val newsFragment = supportFragmentManager.findFragmentById(R.id.newsFragment)
        if (newsFragment != null) {
            binding.bottomNavigationView.setupWithNavController(newsFragment.findNavController())
        }
    }
}