package com.example.galleryvaulto

import android.app.FragmentTransaction
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.galleryvaulto.databinding.ActivityMain2Binding
import com.example.galleryvaulto.ui.fragments.AlbumFragment
import com.example.galleryvaulto.ui.fragments.PhotosFragment
import com.example.galleryvaulto.ui.fragments.SettingFragment
import com.example.galleryvaulto.ui.fragments.VideosFragment

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    val REQUEST_CODE_REQUEST: Int = 555
    val REQUEST_CODE: Int = 786

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val background = ContextCompat.getDrawable(this@MainActivity2, R.color.black)

        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this@MainActivity2,android.R.color.transparent)
        val windowInsetController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetController?.isAppearanceLightStatusBars = true // or false

//        window.navigationBarColor = ContextCompat.getColor(this@MainActivity2,R.color.black)
//        window.setBackgroundDrawable(background)

//        var  windowManager = this.window
//        windowManager.statusBarColor = ContextCompat.getColor(this,R.color.premium_background)
//        windowManager.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        window.statusBarColor = ContextCompat.getColor(this,R.color.white)
//        window?.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        replaceFragment(SettingFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.album -> {
                    replaceFragment(AlbumFragment())
                    replacetitle()


                }
                R.id.photo -> {
                    replaceFragment(PhotosFragment())
                    replacetitle()
                }
                R.id.video -> {
                    replaceFragment(VideosFragment())
                    replacetitle()
                }
                R.id.setting -> {
                    replaceFragment(SettingFragment())
                    // Check the visibility of textConstraint.
                    val isVisible = binding.settingConstraint.visibility

                    if (isVisible == View.VISIBLE) {
                        Toast.makeText(this, "Visible", Toast.LENGTH_LONG).show()
                    } else {
                        binding.searchbarConstraint.visibility = View.GONE
                        binding.settingConstraint.visibility = View.VISIBLE
                    }
                }
                else  ->
                {

                }
            }
            true
        }


    }

    private fun replacetitle() {

        // Check the visibility of textConstraint.
        val isVisible = binding.searchbarConstraint.visibility

        if (isVisible == View.VISIBLE) {
            Toast.makeText(this, "Visible", Toast.LENGTH_LONG).show()
        } else {
            binding.settingConstraint.visibility = View.GONE
            binding.searchbarConstraint.visibility = View.VISIBLE

        }


    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainerView,fragment)
        fragmentTransition.commit()
    }


}



