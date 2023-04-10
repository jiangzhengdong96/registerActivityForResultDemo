package com.example.activityforresultdemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activityforresultdemo.R
import com.example.activityforresultdemo.databinding.FragmentCase5Binding

class ChildrenContainerFragment : Fragment() {
    private lateinit var binding: FragmentCase5Binding
    private lateinit var childFragment: Fragment
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
        FragmentResultApiSecondaryFragment.newInstance("case5")?.let {
            addFragment(it)
            childFragment = it
        }
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

        //ParentFragmentManager、ChildFragmentManager 和Activity Host FragmentManager (supportFragmentManager) 之间的关系
        Log.i("JACK","activity-supportFragmentManager: ${activity?.supportFragmentManager}")
        Log.i("JACK","currentFragment-parentFragmentManager: $parentFragmentManager")
        Log.i("JACK","currentFragment-childFragmentManager: $childFragmentManager")
        Log.i("JACK","childFragment-parentFragmentManager: ${childFragment.parentFragmentManager}")
        Log.i("JACK","childFragment-childFragmentManager: ${childFragment.childFragmentManager}")
    }


    private fun addFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .add(R.id.fl_child, fragment)
            .addToBackStack(null)
            .commit()
    }
}