package com.example.activityforresultdemo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activityforresultdemo.databinding.FragmentCase731Binding

class Case7_3_1Fragment : Fragment() {
    private lateinit var binding: FragmentCase731Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentFragmentManager.setFragmentResultListener("requestKeycase7_3", this) { requestkey, bundle ->
            bundle.getString("bundleKey")?.let {
                binding.tvContent.text = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCase731Binding.inflate(inflater, container, false)
        return binding.root
    }
}