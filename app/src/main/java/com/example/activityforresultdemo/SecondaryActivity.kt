package com.example.activityforresultdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.activityforresultdemo.databinding.ActivitySecondaryBinding

class SecondaryActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySecondaryBinding

    private var source: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_secondary)
        intent.extras?.getString("my_input_key")?.let {
            source = it.split(":")[1]
            binding.tvContent.text = it
            Log.i("JACK", it)
        }
        binding.btnClose.setOnClickListener {
            val resultIntent = intent.apply {
                putExtra("my_result_key", "callback result for: $source")
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}