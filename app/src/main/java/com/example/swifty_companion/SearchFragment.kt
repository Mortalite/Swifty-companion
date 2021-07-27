package com.example.swifty_companion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.example.swifty_companion.databinding.FragmentSearchBinding
import com.example.swifty_companion.viewmodel.OAuth2TokenViewModel
import com.example.swifty_companion.viewmodel.UserInfoViewModel
import io.ktor.client.features.*
import kotlinx.serialization.encodeToString

class SearchFragment : Fragment() {

    private val TAG = this.javaClass.simpleName
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var mainCommunicator: MainCommunicator? = null
    private var oAuth2TokenViewModel: OAuth2TokenViewModel? = null
    private var userInfoViewModel: UserInfoViewModel? = null

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
        oAuth2TokenViewModel = ViewModelProvider(requireActivity()).get(OAuth2TokenViewModel::class.java)
        userInfoViewModel = ViewModelProvider(requireActivity()).get(UserInfoViewModel::class.java)
    }

    fun setSearchButtonOnClick() {
        binding.searchButton.setOnClickListener {
            oAuth2TokenViewModel?.let {
                val login = binding.searchEditText.text.toString()

                try {
                    isValidLogin(login)
                    userInfoViewModel?.userInfo?.value = it.getUserInfo(login)

/*                    oAuth2TokenViewModel?.apply {
                        longLog(jsonFormat.encodeToString(userInfoViewModel?.userInfo))
                    }*/
                    Log.e(TAG, "list size = ${userInfoViewModel?.userInfo?.value?.cursusUsers?.size}")

                    mainCommunicator?.openStudentInfoFragment()
                }
                catch (exception: IllegalArgumentException) {
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