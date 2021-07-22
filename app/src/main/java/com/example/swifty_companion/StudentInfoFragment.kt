package com.example.swifty_companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swifty_companion.databinding.FragmentSearchBinding
import com.example.swifty_companion.databinding.FragmentStudentInfoBinding

class StudentInfoFragment : Fragment() {

    private var BUNDLE_LOGIN_KEY = "BUNDLE_LOGIN_KEY"
    private var login: String? = null

    private var _binding: FragmentStudentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        login = arguments?.getString(BUNDLE_LOGIN_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.textView.text = login
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(login: String) =
            StudentInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_LOGIN_KEY, login)
                }
            }

    }
}