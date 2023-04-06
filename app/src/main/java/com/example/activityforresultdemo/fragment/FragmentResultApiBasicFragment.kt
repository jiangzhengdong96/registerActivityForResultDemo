package com.example.activityforresultdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.activityforresultdemo.databinding.FragmentCase4Binding


class FragmentResultApiBasicFragment : Fragment() {
    private lateinit var binding: FragmentCase4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //相同级别的fragment可以用parentFragmentManager/supportFragmentManager
        setFragmentResultListener("requestKeycase4") { requestKey, bundle ->
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
                binding.tv2Text.text = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCase4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn2Navigate.setOnClickListener {
            replaceFragment(Case4And5And6OtherFragment.newInstance("case4"))
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