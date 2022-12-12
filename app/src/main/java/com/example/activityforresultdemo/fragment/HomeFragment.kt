package com.example.activityforresultdemo.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activityforresultdemo.CommonActivity
import com.example.activityforresultdemo.OnItemClickListener
import com.example.activityforresultdemo.R
import com.example.activityforresultdemo.adapter.HomeAdapter
import com.example.activityforresultdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.rvHome.apply {
            adapter = HomeAdapter(this@HomeFragment)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun navigateBySource(source: String, title: String) {
        Intent(activity, CommonActivity::class.java).apply {
            putExtra("source", source)
            putExtra("title", title)
        }.also { startActivity(it) }
    }

    private fun isActivitySource(source: String) =
        source == "case1" || source == "case2" || source == "case3" || source == "case4"
}