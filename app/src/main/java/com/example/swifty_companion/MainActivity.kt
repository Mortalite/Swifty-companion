package com.example.swifty_companion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.swifty_companion.databinding.ActivityMainBinding
import com.example.swifty_companion.viewmodel.OAuth2TokenViewModel

class MainActivity :    AppCompatActivity(),
                        MainCommunicator {

    private val TAG = this.javaClass.simpleName
    private var oAuth2TokenViewModel: OAuth2TokenViewModel? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModels()
    }

    override fun onStart() {
        super.onStart()
        openSearchFragment()
    }

    fun initViewModels() {
        oAuth2TokenViewModel = ViewModelProvider(this).get(OAuth2TokenViewModel::class.java)
    }

    override fun openSearchFragment() {
        val searchFragment = SearchFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_container, searchFragment)
        transaction.commit()
    }

    override fun openStudentInfoFragment(login: String) {
        val studentInfoFragment = StudentInfoFragment.newInstance(login)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_container, studentInfoFragment)
        transaction.commit()
    }

}
