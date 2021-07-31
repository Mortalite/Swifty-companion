package com.example.swifty_companion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.swifty_companion.adapter.CursusAdapter
import com.example.swifty_companion.adapter.InformationAdapter
import com.example.swifty_companion.adapter.ProjectsAdapter
import com.example.swifty_companion.adapter.SkillsAdapter
import com.example.swifty_companion.databinding.FragmentStudentInfoBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.listener.MainListener
import com.example.swifty_companion.network.InformationEntity
import com.example.swifty_companion.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*

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
        setInformation()
        setCourses()
        setSkills()
        setProjects()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCursusClick(id: Int, adapterPosition: Int) {
        userViewModel?.apply {
            val cursus = userInfo?.value?.cursusUsers?.find {
                it.cursus.id == id
            }

            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val projects = userInfo?.value?.projectsUsers
                ?.filter {
                    !it.markedAt.isNullOrBlank() &&
                    it.cursusIds.contains(id)
                }
                ?.sortedBy { sdf.parse(it.markedAt) }

            buttonSettings?.position = adapterPosition
            buttonSettings?.id = id
            cursusAdapter?.value?.notifyDataSetChanged()
            skillsAdapter?.value?.submitList(cursus?.skills)
            projectsAdapter?.value?.submitList(projects)
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

    private fun setInformation() {
        Glide
            .with(binding.profilePicture.context)
            .load(userViewModel?.userInfo?.value?.imageUrl)
            .into(binding.profilePicture)

        var list: List<InformationEntity> = listOf(
            InformationEntity(R.drawable.ic_login_foreground, "Login", userViewModel?.userInfo?.value?.login.toString()),
            InformationEntity(R.drawable.ic_email_foreground, "Email", userViewModel?.userInfo?.value?.email.toString()),
            InformationEntity(R.drawable.ic_phone_foreground, "Phone", userViewModel?.userInfo?.value?.phone.toString()),
            InformationEntity(R.drawable.ic_wallet_foreground, "Wallet", userViewModel?.userInfo?.value?.wallet.toString())
        )

        userViewModel?.apply {
            informationAdapter?.value = InformationAdapter()
            informationAdapter?.value?.submitList(list)
        }

        binding.informationRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userViewModel?.informationAdapter?.value
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setCourses() {
        userViewModel?.apply {
            cursusAdapter?.value = CursusAdapter(this@UserFragment, userViewModel)
            cursusAdapter?.value?.submitList(userInfo?.value?.cursusUsers)
        }

        binding.cursusRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userViewModel?.cursusAdapter?.value
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setSkills() {
        userViewModel?.apply {
            skillsAdapter?.value = SkillsAdapter()
            skillsAdapter?.value?.submitList(userInfo?.value?.cursusUsers?.get(0)?.skills)
        }

        binding.skillsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userViewModel?.skillsAdapter?.value
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setProjects() {
        userViewModel?.apply {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            val id = userInfo?.value?.cursusUsers?.get(0)?.cursus?.id

            var list = userInfo?.value?.projectsUsers
                ?.filter {
                    !it.markedAt.isNullOrBlank() &&
                    it.cursusIds.contains(id)
                }
                ?.sortedBy { sdf.parse(it.markedAt) }

            Log.e("TEST", "it.cursusIds = ${userInfo?.value?.projectsUsers?.get(0)?.cursusIds}")
            Log.e("TEST", "buttonSettings?.id = ${buttonSettings?.id}")

            projectsAdapter?.value = ProjectsAdapter()
            projectsAdapter?.value?.submitList(list)
        }

        binding.projectsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userViewModel?.projectsAdapter?.value
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
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