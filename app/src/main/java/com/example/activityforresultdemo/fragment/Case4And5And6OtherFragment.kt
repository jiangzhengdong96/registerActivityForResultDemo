package com.example.activityforresultdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.activityforresultdemo.databinding.FragmentCase4Binding

class Case4And5And6OtherFragment : Fragment() {
    private lateinit var binding: FragmentCase4Binding

    private lateinit var source: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        source = arguments?.getString("source") ?: "case4"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCase4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btn1Navigate.setOnClickListener {
            when(source) {
                "case4" -> {
                    setFragmentResult("requestKey01", bundleOf("bundleKey" to "return from Case4"))
                    showToast("set fragment result successful!")
                }
                "case5" -> setFragmentResult("requestKey03", bundleOf("bundleKey" to "set result to case5"))
                "case6" -> setFragmentResult("requestKey04", bundleOf())
            }

        }
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
    }
}