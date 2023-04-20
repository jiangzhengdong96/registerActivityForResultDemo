package com.example.activityforresultdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activityforresultdemo.databinding.FragmentObseverABinding

class ObseverAFragment : Fragment() {
    private lateinit var binding: FragmentObseverABinding

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
        binding =  FragmentObseverABinding.inflate(inflater, container, false)
        return binding.root
    }
}