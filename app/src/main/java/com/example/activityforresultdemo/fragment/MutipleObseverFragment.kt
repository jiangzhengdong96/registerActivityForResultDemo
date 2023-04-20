package com.example.activityforresultdemo.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.activityforresultdemo.databinding.FragmentMutipleObseverBinding

class MutipleObseverFragment : Fragment() {
    private lateinit var binding: FragmentMutipleObseverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentMutipleObseverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        addFragment1(ObseverAFragment())
//
//        Handler().postDelayed({
//            addFragment2(ObseverBFragment())
//        }, 200)

        addFragment2(ObseverBFragment())

        Handler().postDelayed({
            addFragment1(ObseverAFragment())
        }, 200)

        binding.btnSend.setOnClickListener {
            childFragmentManager.setFragmentResult("requestKeyMutiple", bundleOf("bundleKey" to "you get the result"))
        }
    }

    private fun addFragment1(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .add(com.example.activityforresultdemo.R.id.fragment_container_1, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun addFragment2(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .add(com.example.activityforresultdemo.R.id.fragment_container_2, fragment)
            .addToBackStack(null)
            .commit()
    }

}