package com.example.activityforresultdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.activityforresultdemo.databinding.ActivityCommonBinding
import com.example.activityforresultdemo.fragment.*

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
                    "case3" -> addFragment(Case3Fragment())
                    "case4" -> addFragment(Case4Fragment())
                    "case5" -> addFragment(Case5Fragment())
                    "case6" -> addFragment(Case4And5And6OtherFragment.newInstance("case6"))
                    "case7" -> addFragment(Case7Fragment())
                }
            } ?: addFragment(NewActivityResultApiBasicFragment())
        }

        supportFragmentManager.setFragmentResultListener("requestKeycase6", this) { _, _ ->
            showToast("case6:close common activity!")
            finish()
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}