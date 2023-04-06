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

    //基本用法
    private val basicLauncherA = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        it.data?.getStringExtra("my_result_key")?.let {
            binding.tvA.text = "resultA $it"
            Log.i("JACK","resultA: $it")
        }
    }

    //Api提供的contract
    // getContent()
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

    //自定义Contract
    private val customizeLauncher = registerForActivityResult(MyCustomizeContract()) { result ->
        binding.tvCustomize.text = "customizeResult: $result"
        Log.i("JACK","customizeResult: $result")
    }

    //注意事项
    //限制条件：registerForActivityResult()的调用范围
    private lateinit var getLazyLauncherA: ActivityResultLauncher<String>

//    launch()的调用范围
    private val getLazyLauncherB: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.tvScopeB.text = it?.toString()
            Log.i("JACK","lazyResult: $it")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //registerForActivityResult()的调用范围
//        getLazyLauncherA = registerForActivityResult(ActivityResultContracts.GetContent()) {
//            binding.tvScopeA.text = it?.toString()
//            Log.i("JACK","lazyResult: $it")
//        }
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
                getLazyLauncherA.launch("image/*")
            }

            //launch()的调用范围
            btnScopeB.setOnClickListener {
                getLazyLauncherB.launch("image/*")
            }

            //进一步封装
            btnEncapsulation.setOnClickListener {
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
    }

    override fun onStart() {
        super.onStart()
        //registerForActivityResult()的调用范围/ // 调用 registerForActivityResult 方法时只能在OnStart生命周期前调用，否则会报错  ---
//        getLazyLauncherA = registerForActivityResult(ActivityResultContracts.GetContent()) {
//            binding.tvScopeA.text = it?.toString()
//            Log.i("JACK","lazyResult: $it")
//        }
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