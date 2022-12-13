package com.example.activityforresultdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.activityforresultdemo.MyLifecycleObserver
import com.example.activityforresultdemo.databinding.FragmentCase3Binding


class Case3And4Fragment : Fragment() {
    private lateinit var binding: FragmentCase3Binding

    lateinit var observer : MyLifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //相同级别的fragment可以用parentFragmentManager/supportFragmentManager
        setFragmentResultListener("requestKey01") { requestKey, bundle ->
            bundle.getString("bundleKey")?.let {
                MyAlertDialogFragment.newInstance(it)
                    .show(parentFragmentManager, "myAlert")
            }
        }
//        requireActivity().supportFragmentManager.setFragmentResultListener("requestKey01", this) { requestKey, bundle ->
//            bundle.getString("bundleKey")?.let {
//                MyAlertDialogFragment.newInstance(it)
//                    .show(parentFragmentManager, "myAlert")
//            }
//        }

        setFragmentResultListener("requestKey02") { requestKey, bundle ->
            bundle.getString("bundleKey")?.let {
                binding.tv2Text.text = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCase3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        observer = MyLifecycleObserver(requireActivity().activityResultRegistry, binding)
//        lifecycle.addObserver(observer)
        binding.btn1Navigate.setOnClickListener {
            observer.sendData()
        }

        binding.btn2Navigate.setOnClickListener {
            replaceFragment(Case4And5And6OtherFragment.newInstance("case4"))
        }
    }

    override fun onStart() {
        super.onStart()
        observer = MyLifecycleObserver(requireActivity().activityResultRegistry, binding)
        lifecycle.addObserver(observer)
    }

    override fun onResume() {
        super.onResume()
//        Caused by: java.lang.IllegalStateException: LifecycleOwner Case3Fragment{8f29ab5} (f7334e8e-6db7-4b0a-ad56-de4254c8535b id=0x7f0800c4) is attempting to register while current state is STARTED. LifecycleOwners must call register before they are STARTED.
//        observer = MyLifecycleObserver(requireActivity().activityResultRegistry, binding)
//        lifecycle.addObserver(observer)
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(com.example.activityforresultdemo.R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}