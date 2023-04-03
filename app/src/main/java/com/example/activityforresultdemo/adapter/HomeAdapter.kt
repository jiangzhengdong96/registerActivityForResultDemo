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
        HomeItem("case3:ActivityResultRegistry + LifecycleOwner", "case3"),
        HomeItem("case4:Fragment Result API --- equal level fragments", "case4"),
        HomeItem("case5:Fragment Result API --- parent and children fragments", "case5"),
        HomeItem("case6:Fragment Result API --- Activity get result", "case6"),
        HomeItem("case7:Fragment Result API --- lifecycle", "case7")
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