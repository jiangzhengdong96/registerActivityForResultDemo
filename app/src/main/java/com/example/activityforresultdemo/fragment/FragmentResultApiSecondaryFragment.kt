package com.example.activityforresultdemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.activityforresultdemo.databinding.FragmentFragmentResultApiSecondaryBinding

class FragmentResultApiSecondaryFragment : Fragment() {
    private lateinit var binding: FragmentFragmentResultApiSecondaryBinding
    private var num = 0
    private lateinit var source: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        source = arguments?.getString("source") ?: ""

        //关闭又打开
        parentFragmentManager.setFragmentResultListener("requestSentMessage", this) { _, _ ->
            Log.e("JACK", "result: get sent message", )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("$source", "onCreateView ", )
        binding =  FragmentFragmentResultApiSecondaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("$source", "onViewCreated ", )
        if (source == "Case7_2") binding.btnNavigateTest.text = CLOSE_TEXT
        binding.btnNavigateTest.setOnClickListener {
            when(source) {
                "basic" -> {
                    //基本用法JJACK06
                    setFragmentResult("key:basic", bundleOf("resultKey:basic" to "result to basic page"))
                    showToast("set fragment result to basic successful!")
                }

                "equalLevel"-> {
                    //相同层级的fragment可以用parentFragmentManager/supportFragmentManager
//                    setFragmentResult("key:equalLevel", bundleOf("resultKey:equalLevel" to "result to equal level"))

                    requireActivity().supportFragmentManager.setFragmentResult("key:equalLevel", bundleOf("resultKey:equalLevel" to "result to equal level"))
                    Log.i("JACK", "equalLevel：supportFragmentManager: ${requireActivity().supportFragmentManager}/${requireActivity().supportFragmentManager.fragments}")
                    Log.i("JACK", "equalLevel：parentFragmentManager: ${parentFragmentManager}/${parentFragmentManager.fragments}")
                    showToast("set fragment result to equalLevel successful!")
                }

                "contain" -> {
                    //1、接收方使用childFragmentManager， 发送方使用parentFragmentManager
                    setFragmentResult("request:containLevel", bundleOf("result:containLevel" to "result to contain level"))
                    //2、接收方用supportFragmentManager/parentFragmentManager,发送方用supportFragmentManager
//                    requireActivity().supportFragmentManager.setFragmentResult("request:containLevel", bundleOf("result:containLevel" to "result to contain level"))

//                  childFragmentManager.setFragmentResult("request:containLevel", bundleOf("result:containLevel" to "result to contain level"))
                }
                "acticity with fragment" -> setFragmentResult("request:activity", bundleOf())
                "repeat send" -> {
                    setFragmentResult("request:repeat send", bundleOf("result:repeat send" to num))
                    binding.btnNavigateTest.text = String.format(NAVIGATE_TEXT, num)
                    num++
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
        fun newInstance(source: String): FragmentResultApiSecondaryFragment =
            FragmentResultApiSecondaryFragment().apply {
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