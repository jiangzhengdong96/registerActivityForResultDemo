package com.example.activityforresultdemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activityforresultdemo.R
import com.example.activityforresultdemo.databinding.FragmentChildContainerBinding

class ChildrenContainerFragment : Fragment() {
    private lateinit var binding: FragmentChildContainerBinding
    private lateinit var childFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Fragment中包含另一个fragment的之间进行数据传输
        //1、接收方使用childFragmentManager， 发送方使用parentFragmentManager
//        childFragmentManager.setFragmentResultListener("request:containLevel",this) { requestKey, bundle ->
//            bundle.getString("result:containLevel")?.let {
//                binding.tvText.text = it
//                Log.i("JACK", "containLevel：backResult: $it")
//            }
//        }

        //2、接收方用supportFragmentManager/parentFragmentManager,发送方用supportFragmentManager
//        requireActivity().supportFragmentManager.setFragmentResultListener("request:containLevel",this) { requestKey, bundle ->
//            bundle.getString("result:containLevel")?.let {
//                binding.tvText.text = it
//        Log.i("JACK", "containLevel：backResult: $it")
//            }
//        }


        FragmentResultApiSecondaryFragment.newInstance("contain")?.let {
            addFragment(it)
            childFragment = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentChildContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnReset.setOnClickListener {
            binding.tvText.text = "empty"
        }

        //ParentFragmentManager、ChildFragmentManager 和Activity Host FragmentManager (supportFragmentManager) 之间的关系
        Log.i("JACK","activity-supportFragmentManager: ${activity?.supportFragmentManager}")
        Log.i("JACK","currentFragment-parentFragmentManager: $parentFragmentManager")
        Log.i("JACK","currentFragment-childFragmentManager: $childFragmentManager")
        Log.i("JACK","childFragment-parentFragmentManager: ${childFragment.parentFragmentManager}")
    }


    private fun addFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .add(R.id.fl_child, fragment)
            .addToBackStack(null)
            .commit()
    }
}