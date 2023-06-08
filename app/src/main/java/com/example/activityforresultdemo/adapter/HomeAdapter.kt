package com.example.activityforresultdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.activityforresultdemo.OnItemClickListener
import com.example.activityforresultdemo.databinding.ItemHomeListBinding

class HomeAdapter(val listener: OnItemClickListener) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val datas = listOf<HomeItem>(
        HomeItem("new activity result api 基本用法", "case1"),
        HomeItem("contract:RequestPermission和TakePicturePreview用法", "case2"),
        HomeItem("fragment result api 基本用法", "case4"),
        HomeItem("fragment包含另一个子fragment的情况", "case5"),
        HomeItem("在宿主activity中接收结果", "case6"),
        HomeItem("fragment result api生命周期的细微差别", "case7")
    )


    inner class HomeViewHolder(var binding: ItemHomeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: HomeItem) {
            binding.item = item
            binding.listener = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}


data class HomeItem(
    val text: String,
    val source: String
)