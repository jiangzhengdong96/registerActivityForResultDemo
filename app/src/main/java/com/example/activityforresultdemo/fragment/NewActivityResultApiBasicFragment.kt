package com.example.activityforresultdemo.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.activityforresultdemo.contract.MyCustomizeContract
import com.example.activityforresultdemo.databinding.FragmentNewActvityResultApiBasicBinding
import com.example.activityforresultdemo.launchActivityForResult

class NewActivityResultApiBasicFragment : Fragment() {
    private lateinit var binding: FragmentNewActvityResultApiBasicBinding

    //基本用法JJACK01
    private val basicLauncherA = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        it.data?.getStringExtra("my_result_key")?.let { resultData ->
            binding.tvA.text = "resultA $resultData"
            Log.i("JACK","resultA: $resultData")
        }
    }

    //Api提供的contractJJACK02
    // getContent()
    //return 统一资源识别符
    private val getContentLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.ivGetcontent.setImageURI(it)
        Log.i("JACK","resultGetContent: $it")
    }

    //pickContact()
    private val pickContactLauncher = registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
        uri?.let {
            binding.tvPickContact.text = getContactInfo(it)
            Log.i("JACK","resultPickContact: ${getContactInfo(it)}")
        }
    }

    //自定义ContractJJACK03
    private val customizeLauncher = registerForActivityResult(MyCustomizeContract()) { result ->
        binding.tvCustomize.text = "customizeResult: $result"
        Log.i("JACK","customizeResult: $result")
    }

    //注意事项!!!!JJACK04
    //限制条件：registerForActivityResult()的调用范围
    private lateinit var getLimitLauncherA: ActivityResultLauncher<String>

//    launch()的调用范围
    private val getLimitLauncherB: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.tvScopeB.text = it?.toString()
            Log.i("JACK","LimitResultB: $it")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //registerForActivityResult()的调用范围
        getLimitLauncherA = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.tvScopeA.text = it?.toString()
            Log.i("JACK","LimitResultA: $it")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewActvityResultApiBasicBinding.inflate(inflater, container, false).apply {
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //基本用法
            btnA.setOnClickListener {
                basicLauncherA.launch(
                    Intent("android.intent.action.SECONDARY")
                    .putExtra("my_input_key", "input:basicLauncherA")
                )
            }

            //Api提供的contract
            // getContent()文件选择
            btnGetcontent.setOnClickListener {
                getContentLauncher.launch("image/*")
            }

            //pickContact() 获取联系人
            btnPickContact.setOnClickListener {
                pickContactLauncher.launch(null)
            }

            //自定义Contract
            btnCustomize.setOnClickListener {
                customizeLauncher.launch("input:customize")
            }

            //registerForActivityResult()的调用范围
            btnScopeA.setOnClickListener {
                getLimitLauncherA.launch("image/*")
            }

            //launch()的调用范围
            btnScopeB.setOnClickListener {
                getLimitLauncherB.launch("image/*")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //registerForActivityResult()的调用范围/ // 调用 registerForActivityResult 方法时只能在OnStart生命周期前调用，否则会报错  ---
//        getLimitLauncherA = registerForActivityResult(ActivityResultContracts.GetContent()) {
//            binding.tvScopeA.text = it?.toString()
//            Log.i("JACK","LimitResultA: $it")
//        }

        //进一步封装JJACK05
        binding.btnEncapsulation.setOnClickListener {
            val intent = Intent("android.intent.action.SECONDARY")
                .putExtra("my_input_key", "input:encapsulated")
            launchActivityForResult(intent) {
                it.data?.getStringExtra("my_result_key")?.let { result ->
                    binding.tvEncapsulation.text = result
                    Log.i("JACK","encapsulatedResult: $result")
                }
            }
        }
    }

    @SuppressLint("Range")
    private fun getContactInfo(uri: Uri): String {
        var name = ""
        context?.contentResolver?.query(uri, null, null, null, null)?.let {
            while (it.moveToNext()) {
                name = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                break
            }
        }
        return name
    }
}