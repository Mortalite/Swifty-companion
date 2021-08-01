package com.example.swifty_companion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.example.swifty_companion.databinding.ActivityMainBinding
import com.example.swifty_companion.listener.MainListener

class MainActivity :    AppCompatActivity(),
                        MainListener,
                        ImageLoaderFactory  {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Coil.setImageLoader(newImageLoader())
    }

    override fun onStart() {
        super.onStart()
        openSearchFragment()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .componentRegistry { add(SvgDecoder(applicationContext)) }
            .build()
    }

    override fun openSearchFragment() {
        val searchFragment = SearchFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_container, searchFragment)
        transaction.commit()
    }

    override fun openStudentInfoFragment() {
        val studentInfoFragment = UserFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_container, studentInfoFragment)
        transaction.commit()
    }

}
