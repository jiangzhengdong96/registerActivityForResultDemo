package com.example.activityforresultdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activityforresultdemo.MyLifecycleObserver
import com.example.activityforresultdemo.databinding.FragmentActivityResultRegistryBinding


class ActivityResultRegistryFragment : Fragment() {
    private lateinit var binding: FragmentActivityResultRegistryBinding

    lateinit var observer : MyLifecycleObserver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentActivityResultRegistryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        observer = MyLifecycleObserver(requireActivity().activityResultRegistry, binding)
//        lifecycle.addObserver(observer)
        binding.btnNavigate.setOnClickListener {
            observer.sendData()
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

}