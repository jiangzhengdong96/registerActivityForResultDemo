package com.example.activityforresultdemo

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.activityforresultdemo.contract.MyCustomizeContract
import com.example.activityforresultdemo.databinding.FragmentCase3Binding

class MyLifecycleObserver(private val registry : ActivityResultRegistry,val binding: FragmentCase3Binding)
    : DefaultLifecycleObserver {
    lateinit var getContent : ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        getContent = registry.register("key", owner, MyCustomizeContract()) { num ->
            binding.tv1Text.text = num.toString()
        }
    }

    fun sendData() {
        getContent.launch("hello secondary page! --- ActivityResultRegistry")
    }
}