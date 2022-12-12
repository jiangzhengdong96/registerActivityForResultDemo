package com.example.activityforresultdemo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.example.activityforresultdemo.contract.MySecondActivityContract
import com.example.activityforresultdemo.contract.MySecondaryActivityContract_2
import com.example.activityforresultdemo.databinding.FragmentCase1Binding
import com.example.activityforresultdemo.launchActivityForResult

class Case1Fragment : Fragment() {
    private lateinit var binding: FragmentCase1Binding

    private val getLauncher = registerForActivityResult(MySecondActivityContract()) { num: Int? ->
        binding.tv1Text.text = "y" + num.toString()
    }
    private val getLauncherOther = registerForActivityResult(MySecondActivityContract()) { num: Int? ->
        binding.tv1xText.text = "x" + num.toString()
    }

    private lateinit var getLauncher2: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLauncher2 = registerForActivityResult(MySecondaryActivityContract_2()) { num: Int? ->
            binding.tv2Text.text = num.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCase1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btn1Navigate.setOnClickListener {
                getLauncher.launch("hello secondary page!--from launcher1")
            }

            btn1xNavigate.setOnClickListener {
                getLauncherOther.launch("hello secondary page!--from launcher1")
            }

            val intentTemp = Intent("android.intent.action.SECONDARY")
                .putExtra("my_input_key", "hello secondary page!--from launcher2")

            btn2Navigate.setOnClickListener {
                // 调用 registerForActivityResult 方法时只能在OnStart生命周期前调用，否则会报错  ---
//                java.lang.IllegalStateException: Fragment Case1Fragment{2686b6d} (bbb51937-6033-4511-89dd-726b2a4178c5 id=0x7f0800c4) is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate()).
//                getLauncher2 = registerForActivityResult(MySecondaryActivityContract_2()) { num: Int? ->
//                    binding.tv2Text.text = num.toString()
//                }
                getLauncher2.launch(intentTemp)
            }

            btn3Navigate.setOnClickListener {
                launchActivityForResult(intentTemp) {
                    it.data?.getIntExtra("my_result_key", 42)?.let { num ->
                        binding.tv3Text.text = num.toString()
                    }
                }
            }
        }
    }

}