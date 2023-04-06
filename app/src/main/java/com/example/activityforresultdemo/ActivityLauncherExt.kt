package com.example.activityforresultdemo

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.activityforresultdemo.fragment.ActivityResultEncapsulatedFragment

private const val TAG = "LaunchFragment"

@JvmOverloads
fun FragmentActivity.launchActivityForResult(
    intent: Intent,
    options: ActivityOptionsCompat? = null,
    callback: ActivityResultCallback<ActivityResult>
) {
    val holder = ActivityResultEncapsulatedFragment()
    holder.attach(intent, options, callback)
    supportFragmentManager.beginTransaction()
        .add(holder, TAG)
        .commitAllowingStateLoss()
}

@JvmOverloads
fun FragmentActivity.launchActivityForResult(
    clazz: Class<out Activity>,
    options: ActivityOptionsCompat? = null,
    callback: ActivityResultCallback<ActivityResult>
) {
    launchActivityForResult(Intent(this, clazz), options, callback)
}

@JvmOverloads
fun Fragment.launchActivityForResult(
    intent: Intent,
    options: ActivityOptionsCompat? = null,
    callback: ActivityResultCallback<ActivityResult>
) {
    val holder = ActivityResultEncapsulatedFragment()
    holder.attach(intent, options, callback)
    activity?.supportFragmentManager?.beginTransaction()
        ?.add(holder, TAG)
        ?.commitAllowingStateLoss()
}

@JvmOverloads
fun Fragment.launchActivityForResult(
    clazz: Class<out Activity>,
    options: ActivityOptionsCompat? = null,
    callback: ActivityResultCallback<ActivityResult>
) {
    activity?.let { launchActivityForResult(Intent(it, clazz), options, callback) }
}