package com.example.activityforresultdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activityforresultdemo.R
import com.example.activityforresultdemo.databinding.FragmentCase5Binding

class Case5Fragment : Fragment() {
    private lateinit var binding: FragmentCase5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager.setFragmentResultListener("requestKeycase5",this) { requestKey, bundle ->
            bundle.getString("bundleKey")?.let {
                binding.tv1Text.text = it
            }
        }
//        requireActivity().supportFragmentManager.setFragmentResultListener("requestKeycase5",this) { requestKey, bundle ->
//            bundle.getString("bundleKey")?.let {
//                binding.tv1Text.text = it
//            }
//        }
        addFragment(Case4And5And6OtherFragment.newInstance("case5"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCase5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnReset.setOnClickListener {
            binding.tv1Text.text = "empty"
        }
    }


    private fun addFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .add(R.id.fl_child, fragment)
            .addToBackStack(null)
            .commit()
    }
}