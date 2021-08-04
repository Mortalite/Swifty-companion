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
import com.example.swifty_companion.viewmodel.OAuth2TokenViewModel.Companion.longLog
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
        setAnimationViewOnClick()
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

    fun setAnimationViewOnClick() {
        binding.animationView.apply {
            setOnClickListener {
                if (isAnimating)
                    cancelAnimation()
                else
                    resumeAnimation()
            }
        }
    }

    fun setSearchButtonOnClick() {
        binding.searchButton.setOnClickListener {
            oAuth2TokenViewModel?.let {
                val login = binding.searchEditText.text.toString()

                try {
                    userViewModel?.userInfo?.value = it.getUserInfo(login)
                    // Debug
                    longLog(Utils.jsonFormat.encodeToString(userViewModel?.userInfo?.value))
                    mainListener?.openStudentInfoFragment()
                }
                catch (exception: IllegalArgumentException) {
                    Log.e(TAG, "IllegalArgumentException ${exception.message}")
                    binding.searchEditText.setHint(trimMessage(exception.message))
                    binding.searchEditText.setText("")
                }
                catch (exception: ResponseException) {
                    Log.e(TAG, "ResponseException ${exception.message}")
                    binding.searchEditText.setHint(trimMessage(exception.response.status.description))
                    binding.searchEditText.setText("")
                }
            }
        }
    }

    fun trimMessage(message: String?): String {
        if (message == null || message.length > 20)
            return "Login invalid"
        return message
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