package com.example.swifty_companion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swifty_companion.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val TAG = this.javaClass.simpleName
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var connectionViewModel: ConnectionViewModel? = null
    private var mainCommunicator: MainCommunicator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        mainCommunicator = context as MainCommunicator

        initViewModel()
        setOnClick()
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initViewModel() {
        connectionViewModel = ViewModelProvider(this).get(ConnectionViewModel::class.java)
    }

    fun setOnClick() {
        binding.searchButton.setOnClickListener {
            mainCommunicator?.openStudentInfoFragment(binding.searchEditText.text.toString())
        }
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