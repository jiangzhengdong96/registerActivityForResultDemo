package com.example.activityforresultdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activityforresultdemo.databinding.FragmentObseverBBinding

class ObseverBFragment : Fragment() {
    private lateinit var binding: FragmentObseverBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentFragmentManager.setFragmentResultListener("requestKeyMutiple", this) { requestkey, bundle ->
            bundle.getString("bundleKey")?.let {
                binding.tvContent.text = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentObseverBBinding.inflate(inflater, container, false)
        return binding.root
    }
}