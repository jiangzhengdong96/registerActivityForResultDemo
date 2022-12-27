package com.example.activityforresultdemo.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.activityforresultdemo.R


class MyAlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString("title") ?: "title"
        return AlertDialog.Builder(activity).apply {
            setTitle(title)
            setMessage("Are you ok?")
            setIcon(R.drawable.ic_launcher_foreground)
            setPositiveButton("I'm ok!") { dialogInterface, i ->
                setFragmentResult("requestKey_MyAlertDialog", bundleOf("bundleKey" to "I'm OK！"))
            }
        }.create()
    }


    companion object {
        fun newInstance(title: String): MyAlertDialogFragment =
            MyAlertDialogFragment().apply {
                Bundle().apply {
                    putString("title", title)
                }.also {
                    arguments = it
                }
            }
    }
}