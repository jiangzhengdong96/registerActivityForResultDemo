package com.example.activityforresultdemo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.activityforresultdemo.CommonActivity
import com.example.activityforresultdemo.databinding.FragmentCase4otherBinding

class Case4And5And6OtherFragment : Fragment() {
    private lateinit var binding: FragmentCase4otherBinding

    private lateinit var source: String

    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        source = arguments?.getString("source") ?: "case4"
        Log.e("$source", "oncreate ", )

        //case7_2:lifecycle close fragment
        parentFragmentManager.setFragmentResultListener("requestCodecase7_2", this) { _, _ ->
            Log.e("Case7_2", "case7_2:get close fragment result ", )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("$source", "onCreateView ", )
        binding =  FragmentCase4otherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("$source", "onViewCreated ", )
        if (source == "Case7_2") binding.btn1Navigate.text = CLOSE_TEXT
        binding.btn1Navigate.setOnClickListener {
            when(source) {
                "case4" -> {
                    setFragmentResult("requestKeycase4", bundleOf("bundleKey" to "return from Case4"))
//                    requireActivity().supportFragmentManager.setFragmentResult("requestKey01", bundleOf("bundleKey" to "return from Case4"))
                    showToast("set fragment result successful!")
                }
                "case5" -> {
//                    setFragmentResult("requestKeycase5", bundleOf("bundleKey" to "set result to case5"))
                    requireActivity().supportFragmentManager.setFragmentResult("requestKeycase5", bundleOf("bundleKey" to "set result to case5"))
//                    childFragmentManager.setFragmentResult("requestKeycase5", bundleOf("bundleKey" to "set result to case5"))
                }
                "case6" -> setFragmentResult("requestKeycase6", bundleOf())
                "case7_1" -> {
                    setFragmentResult("requestKeycase7_1", bundleOf("bundleKey" to num))
                    binding.btn1Navigate.text = String.format(NAVIGATE_TEXT, num)
                    num++
                }
                "Case7_2" -> {
                    parentFragmentManager.popBackStack()
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("$source", "onStart ", )
    }

    override fun onResume() {
        super.onResume()
        Log.e("$source", "onResume ", )
    }

    override fun onPause() {
        super.onPause()
        Log.e("$source", "onPause ", )
    }

    override fun onStop() {
        super.onStop()
        parentFragmentManager.setFragmentResultListener("requestCodecase7_2", this) { _, _ ->
            Log.e("Case7_2", "case7_2:get close fragment result ", )
        }
        Log.e("$source", "onStop ", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("$source", "onDestoryView ", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("$source", "onDestory ", )
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    companion object{
        fun newInstance(source: String): Case4And5And6OtherFragment =
            Case4And5And6OtherFragment().apply {
                Bundle().apply {
                    putString("source", source)
                }.also {
                    arguments = it
                }
            }

        private const val NAVIGATE_TEXT = "navigate(set fragment result) : %s"
        private const val CLOSE_TEXT = "Close(from case7_2)"
    }
}