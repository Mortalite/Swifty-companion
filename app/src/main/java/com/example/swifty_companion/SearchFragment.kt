package com.example.swifty_companion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swifty_companion.databinding.FragmentSearchBinding
import com.example.swifty_companion.viewmodel.OAuth2TokenViewModel
import io.ktor.client.features.*
import kotlinx.serialization.encodeToString

class SearchFragment : Fragment() {

    private val TAG = this.javaClass.simpleName
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var mainCommunicator: MainCommunicator? = null
    private var oAuth2TokenViewModel: OAuth2TokenViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainCommunicator = context as MainCommunicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        initViewModel()
        setSearchButtonOnClick()
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initViewModel() {
        oAuth2TokenViewModel = ViewModelProvider(this).get(OAuth2TokenViewModel::class.java)
    }

    fun setSearchButtonOnClick() {
        binding.searchButton.setOnClickListener {
            oAuth2TokenViewModel?.let {
                val login = binding.searchEditText.text.toString()

                try {
                    isValidLogin(login)

                    val userInfo = it.getUserInfo(login)

                    oAuth2TokenViewModel?.apply {
                        longLog(jsonFormat.encodeToString(userInfo))
                    }

                    mainCommunicator?.openStudentInfoFragment(login)
                }
                catch (exception: IllegalArgumentException) {
                    Log.e("TEST", exception.message.toString())
                    binding.searchEditText.setHint(exception.message)
                    binding.searchEditText.setText("")
                }
                catch (exception: ResponseException) {
                    binding.searchEditText.setHint(exception.response.status.description)
                    binding.searchEditText.setText("")
                }
            }
        }
    }

    fun isValidLogin(login: String) {
        if (login.contains(Regex("[^a-zA-Z]")))
            throw IllegalArgumentException("Login invalid")
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }

}