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
        Log.e("Case7：helloLog", "oncreate ", )

        // repeat send
        setFragmentResultListener("requestKeycase7_1") { requestKey, bundle ->
            val num =  bundle.getInt("bundleKey")
            Log.e("Case7：helloLog", "setFragmentresult  : $num", )
        }

        // close fragment
        setFragmentResultListener("requestKeycase7_2") { requestKey, bundle ->
            Log.e("Case7：helloLog", "setFragmentresult  : close fragment", )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("Case7：helloLog", "onAttach ", )
    }

    override fun onStart() {
        super.onStart()
        Log.e("Case7：helloLog", "onStart ", )
    }

    override fun onResume() {
        super.onResume()
        Log.e("Case7：helloLog", "onResume ", )
    }

    override fun onPause() {
        super.onPause()
        Log.e("Case7：helloLog", "onPause ", )
    }

    override fun onStop() {
        super.onStop()
        Log.e("Case7：helloLog", "onStop ", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("Case7：helloLog", "onDestoryView ", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Case7：helloLog", "onDestory ", )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("Case7：helloLog", "onCreateView ", )
        binding =  FragmentCase7Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Case7：helloLog", "onViewCreated ", )
        binding.btn1Navigate.setOnClickListener {
            replaceFragment(Case4And5And6OtherFragment.newInstance("case7_1"))
        }
        binding.btn2Navigate.setOnClickListener {
            replaceFragment(Case4And5And6OtherFragment.newInstance("Case7_2"))
        }
        binding.btn2Send.setOnClickListener {
            parentFragmentManager.setFragmentResult("requestCodecase7_2", Bundle())
        }
        binding.btn3Navigate.setOnClickListener {
            replaceFragment(Case7OtherFragment())
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(com.example.activityforresultdemo.R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun replaceFragmentWithoutbackStack(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(com.example.activityforresultdemo.R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}