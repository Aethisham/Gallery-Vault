package com.example.galleryvaulto

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.galleryvaulto.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
val REQUEST_CODE_REQUEST: Int = 555
val REQUEST_CODE: Int = 786
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var  windowManager = this.window
//        windowManager.statusBarColor = ContextCompat.getColor(this,R.color.white)
//        windowManager.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Handler to run a task after a specific time interval.
        Handler().postDelayed({
            // Check the visibility of textConstraint.
            val isVisible = binding.startButtonConstraint.visibility

            if (isVisible == View.VISIBLE) {
                Toast.makeText(this, "Visible", Toast.LENGTH_LONG).show()
            } else {
                binding.seekbar.visibility = View.GONE
                binding.startButtonConstraint.visibility = View.VISIBLE

            }

            // Perform any other actions you need after the 2-second delay.

        }, 2000)

        binding.ButtonStart.setOnClickListener {

            // Implement the logic for ButtonStart click
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (ContextCompat.checkSelfPermission(
                        this, android.Manifest.permission.READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    loadImages()
                }
                else {
                    requestStoragePermission()
                }
            }
            else {
                if (ContextCompat.checkSelfPermission(
                        this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    loadImages()
                }
                else {
                    requestStoragePermission()
                }
            }

        }
    }

    private fun loadImages() {
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
    }

    private fun requestStoragePermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.READ_MEDIA_IMAGES
                )
            )
            {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), REQUEST_CODE
                )
            }
            else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), REQUEST_CODE
                )
            }
        }
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE
                )
            }
            else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                loadImages()
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_IMAGES) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Permission permanently denied
                    Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
                } else {
                    // Show rationale dialog
                    var dialog2 = Dialog(this)
                    dialog2.setContentView(R.layout.rationalbox)
                    dialog2.window?.setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                    val no = dialog2.findViewById<Button>(R.id.cancel_button)
                    val yes = dialog2.findViewById<Button>(R.id.confirm_button)

                    no.setOnClickListener {
                        dialog2.dismiss()

                    }

                    yes.setOnClickListener {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.parse("package:$packageName")
                        startActivityForResult(intent, REQUEST_CODE_REQUEST)
                        dialog2.dismiss()
                    }

                    dialog2.show()
                }
            }
        }


    }
}