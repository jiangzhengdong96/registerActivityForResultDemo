package com.example.activityforresultdemo.fragment

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.example.activityforresultdemo.databinding.FragmentCase2Binding

class Case2Fragment : Fragment() {
    private lateinit var binding: FragmentCase2Binding

    private val cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        when {
            granted -> {
                cameraShot.launch(null)
            }
            else -> {
                showToast("denied!")
            }
        }
    }

    private val cameraShot = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            binding.iv2.setImageBitmap(bitmap)
        } else {
            showToast("something wrong!")
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    /////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCase2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //start Activity for result
            btn1Navigate.setOnClickListener {
                handleRequestPermission()
            }

            // register for activity result
            btn2Navigate.setOnClickListener {
                cameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
    }

    ////////////////////////////////////////////////////

    private fun handleRequestPermission() {
        when {
            checkSelfPermission(requireActivity(),Manifest.permission.CAMERA) == PermissionChecker.PERMISSION_GRANTED -> {
                // access to the camera is allowed, open the camera
                startActivityForResult(
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE),
                    PHOTO_REQUEST_CODE
                )
            }
            else -> {
                // access to the camera is denied, requesting permission
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    PHOTO_PERMISSIONS_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            PHOTO_PERMISSIONS_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    startActivityForResult(
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE),
                        PHOTO_REQUEST_CODE
                    )
                } else {
                    return
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PHOTO_REQUEST_CODE -> {
                if (resultCode == RESULT_OK && data != null) {
                    val bitmap = data.extras?.get("data") as Bitmap
                    binding.iv1.setImageBitmap(bitmap)
                } else {
                    // failed to take photo
                }
            }

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }


    companion object {
        private const val PHOTO_REQUEST_CODE = 1
        private const val PHOTO_PERMISSIONS_REQUEST_CODE = 2
    }

}