package com.example.activityforresultdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.activityforresultdemo.databinding.ActivityCommonBinding
import com.example.activityforresultdemo.fragment.*
import org.jetbrains.annotations.TestOnly

class CommonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_common)
        intent.extras?.apply{
            getString("title", "title")?.let { title = it }
            getString("source", "case1")?.let {
                when(it) {
                    "case1" -> addFragment(NewActivityResultApiBasicFragment())
                    "case2" -> addFragment(NewActivityForResultForPermissionContractFragment())
                    "case3" -> addFragment(ActivityResultRegistryFragment())
                    "case4" -> addFragment(FragmentResultApiBasicFragment())
                    "case5" -> addFragment(ChildrenContainerFragment())
                    "case6" -> addFragment(FragmentResultApiSecondaryFragment.newInstance("acticity with fragment"))
                    "case7" -> addFragment(FragmentResultApiLifecycFragment())
                }
            } ?: addFragment(NewActivityResultApiBasicFragment())
        }

        //在宿主activity中接收结果
        supportFragmentManager.setFragmentResultListener("request:activity", this) { _, _ ->
            showToast("receive result at host activity:close common activity!")
            Log.i("JACK", "host activity：backResult: receive result at host activity:close common activity!")
            finish()
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    @TestOnly
    fun changeTvTextForUITest(text: String) {
        binding.tvNameTest.text = text
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}