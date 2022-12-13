package com.example.activityforresultdemo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.activityforresultdemo.R
import com.example.activityforresultdemo.databinding.FragmentCase5Binding
import com.example.activityforresultdemo.databinding.FragmentCase7Binding

class Case7Fragment : Fragment() {
    private lateinit var binding: FragmentCase7Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("helloLog", "oncreate ", )
        setFragmentResultListener("requestKey05") { requestKey, bundle ->
            val num =  bundle.getInt("bundleKey")
            Log.e("helloLog", "setFragmentresult  : $num", )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("helloLog", "onAttach ", )
    }

    override fun onStart() {
        super.onStart()
        Log.e("helloLog", "onStart ", )
    }

    override fun onResume() {
        super.onResume()
        Log.e("helloLog", "onResume ", )
    }

    override fun onPause() {
        super.onPause()
        Log.e("helloLog", "onPause ", )
    }

    override fun onStop() {
        super.onStop()
        Log.e("helloLog", "onStop ", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("helloLog", "onDestoryView ", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("helloLog", "onDestory ", )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("helloLog", "onCreateView ", )
        binding =  FragmentCase7Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("helloLog", "onViewCreated ", )
        binding.btnClick.setOnClickListener {
            replaceFragment(Case4And5And6OtherFragment.newInstance("case7"))
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(com.example.activityforresultdemo.R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}