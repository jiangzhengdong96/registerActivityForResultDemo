package com.example.activityforresultdemo.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.activityforresultdemo.databinding.FragmentResultApiLifecycleBinding

class FragmentResultApiLifecycFragment : Fragment() {
    private lateinit var binding: FragmentResultApiLifecycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("JACK", "FragmentResultApiLifecycFragment-oncreate ", )

        // 重复接收到多个结果只会接收到最新的
        setFragmentResultListener("request:repeat send") { requestKey, bundle ->
            val num =  bundle.getInt("result:repeat send")
            Log.e("JACK", "repeat send result: $num", )
        }

        // close fragment
        setFragmentResultListener("requestKeycase7_2") { requestKey, bundle ->
            Log.e("JACK", "setFragmentresult  : close fragment", )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("JACK", "FragmentResultApiLifecycFragment-onAttach ", )
    }

    override fun onStart() {
        super.onStart()
        Log.e("JACK", "FragmentResultApiLifecycFragment-onStart ", )
    }

    override fun onResume() {
        super.onResume()
        Log.e("JACK", "FragmentResultApiLifecycFragment-onResume ", )
    }

    override fun onPause() {
        super.onPause()
        Log.e("JACK", "FragmentResultApiLifecycFragment-onPause ", )
    }

    override fun onStop() {
        super.onStop()
        Log.e("JACK", "FragmentResultApiLifecycFragment-onStop ", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("JACK", "FragmentResultApiLifecycFragment-onDestoryView ", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("JACK", "FragmentResultApiLifecycFragment-onDestory ", )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("JACK", "FragmentResultApiLifecycFragment-onCreateView ", )
        binding =  FragmentResultApiLifecycleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("JACK", "FragmentResultApiLifecycFragment-onViewCreated ", )
        binding.btnRepeatSend.setOnClickListener {
            replaceFragment(FragmentResultApiSecondaryFragment.newInstance("repeat send"))
        }
        binding.btn2Navigate.setOnClickListener {
            replaceFragment(FragmentResultApiSecondaryFragment.newInstance("Case7_2"))
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