package com.example.swifty_companion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.swifty_companion.databinding.ActivityMainBinding
import com.example.swifty_companion.viewmodel.OAuth2TokenViewModel

class MainActivity :    AppCompatActivity(),
                        MainCommunicator {

    private val TAG = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        openSearchFragment()
    }

    override fun openSearchFragment() {
        val searchFragment = SearchFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_container, searchFragment)
        transaction.commit()
    }

    override fun openStudentInfoFragment() {
        val studentInfoFragment = StudentInfoFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_container, studentInfoFragment)
        transaction.commit()
    }

}
