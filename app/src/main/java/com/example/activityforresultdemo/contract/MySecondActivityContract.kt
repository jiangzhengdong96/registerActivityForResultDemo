package com.example.activityforresultdemo.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MySecondActivityContract : ActivityResultContract<String, Int?>() {

    override fun createIntent(context: Context, input: String): Intent {
        return Intent("android.intent.action.SECONDARY")
            .putExtra("my_input_key", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int? = when {
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getIntExtra("my_result_key", 42222)
    }

    override fun getSynchronousResult(context: Context, input: String): SynchronousResult<Int?>? {
        return if (input.isNullOrEmpty()) SynchronousResult(42) else null
    }
}