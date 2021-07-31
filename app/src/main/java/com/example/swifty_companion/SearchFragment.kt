package com.example.swifty_companion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swifty_companion.databinding.FragmentSearchBinding
import com.example.swifty_companion.listener.MainListener
import com.example.swifty_companion.viewmodel.OAuth2TokenViewModel
import com.example.swifty_companion.viewmodel.UserViewModel
import io.ktor.client.features.*
import kotlinx.serialization.encodeToString

class SearchFragment : Fragment() {

    private val TAG = this.javaClass.simpleName
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var mainListener: MainListener? = null
    private var oAuth2TokenViewModel: OAuth2TokenViewModel? = null
    private var userViewModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainListener = context as MainListener
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
        oAuth2TokenViewModel = ViewModelProvider(requireActivity()).get(OAuth2TokenViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
    }

    fun setSearchButtonOnClick() {
        binding.searchButton.setOnClickListener {
            oAuth2TokenViewModel?.let {
                val login = binding.searchEditText.text.toString()

                try {
                    isValidLogin(login)
                    userViewModel?.userInfo?.value = it.getUserInfo(login)

                    oAuth2TokenViewModel?.apply {
                        longLog(jsonFormat.encodeToString(userViewModel?.userInfo?.value))
                    }
                    Log.e(TAG, "list size = ${userViewModel?.userInfo?.value?.cursusUsers?.size}")

                    mainListener?.openStudentInfoFragment()
                }
                catch (exception: IllegalArgumentException) {
                    Log.e(TAG, "IllegalArgumentException ${exception.message}")
                    binding.searchEditText.setHint(exception.message)
                    binding.searchEditText.setText("")
                }
                catch (exception: ResponseException) {
                    Log.e(TAG, "ResponseException ${exception.message}")
                    binding.searchEditText.setHint(exception.response.status.description)
                    binding.searchEditText.setText("")
                }
            }
        }
    }

    fun isValidLogin(login: String) {
        if (login.contains(Regex("[^a-zA-Z0-9]")))
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