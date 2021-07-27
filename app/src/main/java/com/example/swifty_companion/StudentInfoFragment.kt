package com.example.swifty_companion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat.setNestedScrollingEnabled
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.swifty_companion.adapter.ProjectsAdapter
import com.example.swifty_companion.databinding.FragmentStudentInfoBinding
import com.example.swifty_companion.network.UserInfoDTO
import com.example.swifty_companion.viewmodel.OAuth2TokenViewModel
import com.example.swifty_companion.viewmodel.UserInfoViewModel

class StudentInfoFragment : Fragment() {

    private var _binding: FragmentStudentInfoBinding? = null
    private val binding get() = _binding!!

    private var mainCommunicator: MainCommunicator? = null
    private var userInfoViewModel: UserInfoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainCommunicator = context as MainCommunicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentInfoBinding.inflate(inflater, container, false)

        initViewModel()
        setNavigationOnClickListener()
        setGeneralInformation()

        userInfoViewModel?.apply {
            projectsAdapter?.value = ProjectsAdapter()
            projectsAdapter?.value?.submitList(userInfo?.value?.cursusUsers)
        }

        binding.projectsRecycler.apply {
//            setNestedScrollingEnabled(false);
            layoutManager = LinearLayoutManager(context)
//            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = userInfoViewModel?.projectsAdapter?.value
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViewModel() {
        userInfoViewModel = ViewModelProvider(requireActivity()).get(UserInfoViewModel::class.java)
    }

    private fun setNavigationOnClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            mainCommunicator?.openSearchFragment()
        }
    }

    private fun setGeneralInformation() {
        Glide
            .with(binding.profilePicture.context)
            .load(userInfoViewModel?.userInfo?.value?.imageUrl)
            .into(binding.profilePicture)
        binding.textViewLogin.text = userInfoViewModel?.userInfo?.value?.login
        binding.textViewEmail.text = userInfoViewModel?.userInfo?.value?.email
        binding.textViewPhone.text = userInfoViewModel?.userInfo?.value?.phone
//        binding.textViewLevel.text = userInfoViewModel?.userInfo?
        binding.textViewWallet.text = userInfoViewModel?.userInfo?.value?.wallet
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            StudentInfoFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }
}