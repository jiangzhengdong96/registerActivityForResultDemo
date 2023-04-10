package com.example.activityforresultdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.activityforresultdemo.databinding.FragmentFragmentResultApiBinding


class FragmentResultApiBasicFragment : Fragment() {
    private lateinit var binding: FragmentFragmentResultApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //基本用法
        setFragmentResultListener("key:basic") { _, bundle ->
            bundle.getString("resultKey:basic")?.let {
                binding.tvText.text = it
            }
        }

        //相同层级的fragment可以用parentFragmentManager/supportFragmentManager
        setFragmentResultListener("key:equalLevel") { requestKey, bundle ->
            bundle.getString("bundleKey")?.let {
                MyAlertDialogFragment.newInstance(it)
                    .show(parentFragmentManager, "myAlert")
            }
        }
//        requireActivity().supportFragmentManager.setFragmentResultListener("requestKeycase4", this) { requestKey, bundle ->
//            bundle.getString("bundleKey")?.let {
//                MyAlertDialogFragment.newInstance(it)
//                    .show(parentFragmentManager, "myAlert")
//            }
//        }

        setFragmentResultListener("requestKey_MyAlertDialog") { requestKey, bundle ->
            bundle.getString("bundleKey")?.let {
                binding.tvText.text = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentFragmentResultApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNavigate.setOnClickListener {
            replaceFragment(FragmentResultApiSecondaryFragment.newInstance("basic"))
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