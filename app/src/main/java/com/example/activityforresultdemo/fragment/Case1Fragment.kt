package com.example.activityforresultdemo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.activityforresultdemo.contract.MySecondActivityContract
import com.example.activityforresultdemo.contract.MySecondaryActivityContract_2
import com.example.activityforresultdemo.databinding.FragmentCase1Binding
import com.example.activityforresultdemo.launchActivityForResult

class Case1Fragment : Fragment() {
    private lateinit var binding: FragmentCase1Binding

    // 1/2.注册内容完全相同
    private val getLauncherA = registerForActivityResult(MySecondActivityContract()) { num: Int? ->
        binding.tv1Text.text = "LauncherA" + num.toString()
    }

    private val getLauncherB = registerForActivityResult(MySecondActivityContract()) { num: Int? ->
        binding.tv2Text.text = "LauncherB" + num.toString()
    }

    // 3.使用api提供的合约
    private val getLauncherForActivityResultContact = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        result.data?.getIntExtra("my_result_key", 0)?.let {
            binding.tv3Text.text = "3：" + it.toString()
        }
    }

    //4 intent提出合约/注册范围
    private lateinit var getLauncherLazy: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLauncherLazy = registerForActivityResult(MySecondaryActivityContract_2()) { num: Int? ->
            binding.tv4Text.text = "4：" + num.toString()
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
                getLauncherA.launch("hello secondary page!--from launchercase1")
            }

            btn2Navigate.setOnClickListener {
                getLauncherB.launch("hello secondary page!--from launchercase1")
            }

            val intentTemp = Intent("android.intent.action.SECONDARY")
                .putExtra("my_input_key", "hello secondary page!--from launchercase1")

            btn3Navigate.setOnClickListener {
                getLauncherForActivityResultContact.launch(intentTemp)
            }

            btn4Navigate.setOnClickListener {
                // 调用 registerForActivityResult 方法时只能在OnStart生命周期前调用，否则会报错  ---
//                java.lang.IllegalStateException: Fragment Case1Fragment{2686b6d} (bbb51937-6033-4511-89dd-726b2a4178c5 id=0x7f0800c4) is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate()).
//                getLauncherLazy = registerForActivityResult(MySecondaryActivityContract_2()) { num: Int? ->
//                    binding.tv2Text.text = num.toString()
//                }
                getLauncherLazy.launch(intentTemp)
            }

            // 5.封装后的
            btn5Navigate.setOnClickListener {
                launchActivityForResult(intentTemp) {
                    it.data?.getIntExtra("my_result_key", 42)?.let { num ->
                        binding.tv5Text.text = "5" + num.toString()
                    }
                }
            }
        }
    }

}