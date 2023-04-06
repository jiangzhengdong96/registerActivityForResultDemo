package com.example.activityforresultdemo.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MyCustomizeContract : ActivityResultContract<String, String?>() {

    override fun createIntent(context: Context, input: String): Intent {
        return Intent("android.intent.action.SECONDARY")
            .putExtra("my_input_key", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? = when {
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getStringExtra("my_result_key")
    }
}