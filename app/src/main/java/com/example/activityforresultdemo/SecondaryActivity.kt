package com.example.activityforresultdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.activityforresultdemo.databinding.ActivitySecondaryBinding

class SecondaryActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySecondaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_secondary)
        intent.extras?.getString("my_input_key")?.let {
            binding.tvContent.text = it
        }
        binding.btnClose.setOnClickListener {
            val resultIntent = intent.apply {
                putExtra("my_result_key", 432222)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}