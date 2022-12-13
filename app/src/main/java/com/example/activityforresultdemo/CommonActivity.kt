package com.example.activityforresultdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                    "case1" -> addFragment(Case1Fragment())
                    "case2" -> addFragment(Case2Fragment())
                    "case3" -> addFragment(Case3And4Fragment())
                    "case4" -> addFragment(Case3And4Fragment())
                    "case5" -> addFragment(Case5Fragment())
                    "case6" -> addFragment(Case4And5And6OtherFragment.newInstance("case6"))
                    "case7" -> addFragment(Case7Fragment())
                }
            } ?: addFragment(Case1Fragment())
        }

        supportFragmentManager.setFragmentResultListener("requestKey04", this) { _, _ ->
            finish()
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }
}