package com.example.activityforresultdemo.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.activityforresultdemo.databinding.FragmentCase7otherBinding

class Case7OtherFragment : Fragment() {
    private lateinit var binding: FragmentCase7otherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCase7otherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        addFragment1(Case7_3_1Fragment())
//
//        Handler().postDelayed({
//            addFragment2(Case7_3_2Fragment())
//        }, 200)

        addFragment2(Case7_3_2Fragment())

        Handler().postDelayed({
            addFragment1(Case7_3_1Fragment())
        }, 200)

        binding.btnSend.setOnClickListener {
            childFragmentManager.setFragmentResult("requestKeycase7_3", bundleOf("bundleKey" to "you get the result"))
        }
    }

    private fun addFragment1(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .add(com.example.activityforresultdemo.R.id.fragment_container_1, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun addFragment2(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .add(com.example.activityforresultdemo.R.id.fragment_container_2, fragment)
            .addToBackStack(null)
            .commit()
    }

}