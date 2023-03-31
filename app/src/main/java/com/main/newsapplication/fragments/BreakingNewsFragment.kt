package com.main.newsapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.main.newsapplication.ARG_PARAM1
import com.main.newsapplication.ARG_PARAM2
import com.main.newsapplication.R
import com.main.newsapplication.adapter.ArticleAdapter
import com.main.newsapplication.viewModel.NewsViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [BreakingNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {
lateinit var viewModel:NewsViewModel
lateinit var newsAdapter: ArticleAdapter
private fun setupRecyclerView(){
   /* newsAdapter= ArticleAdapter()
    rvBreakingNews.apply{}
    adapter=newsAdapter
    layoutManger=LinearLayoutManager(activity)
}
    newsAdapter.setOnItemClickListener{
        var bundle=Bundle().apply {
            putSerializable("article",it)

        }
        findNavController().navigator(R.id.action_breakingNewsFragment_to_articleFragment,bundle)

    }
    newsAdapter*/
}