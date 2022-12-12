package com.example.activityforresultdemo.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

class ActivityResultFragment : Fragment() {

    private lateinit var callback: ActivityResultCallback<ActivityResult>
    private lateinit var intent: Intent
    private var options: ActivityOptionsCompat? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(HOST_ACTIVITY_HASH_CODE, activity?.hashCode() ?: 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "fragment onCreate @${hashCode()}")
        if (this::intent.isInitialized) {
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                callback.onActivityResult(it)
                removeSelf()
            }.launch(intent, options)
        } else {
            val lastHasCode = savedInstanceState?.getInt(HOST_ACTIVITY_HASH_CODE) ?: 0
            val hostName = activity?.javaClass?.simpleName
            Log.e(
                TAG,
                "$hostName@$lastHasCode is destroy, current activity is $hostName@${activity?.hashCode()}"
            )
            removeSelf()
        }
    }

    fun attach(
        intent: Intent,
        options: ActivityOptionsCompat?,
        callback: ActivityResultCallback<ActivityResult>
    ) {
        this.intent = intent
        this.options = options
        this.callback = callback
    }

    private fun removeSelf() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commitAllowingStateLoss()
    }

    companion object {
        private const val TAG = "ActivityLauncher"
        private const val HOST_ACTIVITY_HASH_CODE = "activity_hash_code"
    }

}