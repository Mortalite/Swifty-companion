package com.example.swifty_companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.swifty_companion.adapter.CursusAdapter
import com.example.swifty_companion.adapter.SkillsAdapter
import com.example.swifty_companion.databinding.FragmentStudentInfoBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.listener.MainListener
import com.example.swifty_companion.viewmodel.UserViewModel

class UserFragment :    Fragment(),
                        AdapterListener {

    private var _binding: FragmentStudentInfoBinding? = null
    private val binding get() = _binding!!

    private var mainListener: MainListener? = null
    private var userViewModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainListener = context as MainListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentInfoBinding.inflate(inflater, container, false)

        initViewModel()
        setNavigationOnClickListener()
        setGeneralInformation()


        userViewModel?.apply {
            cursusAdapter?.value = CursusAdapter(this@UserFragment)
            cursusAdapter?.value?.submitList(userInfo?.value?.cursusUsers)
        }

        binding.cursusRecyclerView.apply {
//            setNestedScrollingEnabled(false);
            layoutManager = LinearLayoutManager(context)
//            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = userViewModel?.cursusAdapter?.value
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        userViewModel?.apply {
            skillsAdapter?.value = SkillsAdapter()
            skillsAdapter?.value?.submitList(userInfo?.value?.cursusUsers?.get(0)?.skills)
        }

        binding.skillsRecyclerView.apply {
//            setNestedScrollingEnabled(false);
            layoutManager = LinearLayoutManager(context)
//            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = userViewModel?.skillsAdapter?.value
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCursusClick(id: Int) {
        userViewModel?.apply {
            val cursus = userInfo?.value?.cursusUsers?.find {
                it.cursus.id == id
            }

            skillsAdapter?.value?.submitList(cursus?.skills)
        }
    }


    private fun initViewModel() {
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
    }

    private fun setNavigationOnClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            mainListener?.openSearchFragment()
        }
    }

    private fun setGeneralInformation() {
        Glide
            .with(binding.profilePicture.context)
            .load(userViewModel?.userInfo?.value?.imageUrl)
            .into(binding.profilePicture)
        binding.textViewLogin.text = userViewModel?.userInfo?.value?.login
        binding.textViewEmail.text = userViewModel?.userInfo?.value?.email
        binding.textViewPhone.text = userViewModel?.userInfo?.value?.phone
//        binding.textViewLevel.text = userInfoViewModel?.userInfo?
        binding.textViewWallet.text = userViewModel?.userInfo?.value?.wallet
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            UserFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }
}