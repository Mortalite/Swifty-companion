package com.example.swifty_companion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swifty_companion.Utils.Companion.loadUrl
import com.example.swifty_companion.adapter.*
import com.example.swifty_companion.databinding.FragmentUserBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.listener.MainListener
import com.example.swifty_companion.network.InformationEntity
import com.example.swifty_companion.viewmodel.UserViewModel
import java.util.*

class UserFragment :    Fragment(),
                        AdapterListener {

    private var _binding: FragmentUserBinding? = null
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
        _binding = FragmentUserBinding.inflate(inflater, container, false)

        initViewModel()
        setNavigationOnClickListener()
        setInformation()
        setCourses()
        setSkills()
        setProjects()
        setAchievements()

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

            buttonSettings?.position = adapterPosition
            buttonSettings?.id = id
            cursusAdapter?.value?.notifyDataSetChanged()
            skillsAdapter?.value?.submitList(cursus?.skills)
            projectsAdapter?.value?.submitList(getProjectsById(id))
        }
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel?.resetButtonSettings()
    }

    private fun setNavigationOnClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            mainListener?.openSearchFragment()
        }
    }

    private fun setInformation() {
        userViewModel?.userInfo?.value?.imageUrl?.let { binding.profilePicture.loadUrl(it) }

        val infoList: MutableList<InformationEntity> = mutableListOf(
            InformationEntity(R.mipmap.ic_login_round, "Login", userViewModel?.userInfo?.value?.login.toString()),
            InformationEntity(R.mipmap.ic_email_round, "Email", userViewModel?.userInfo?.value?.email.toString()),
            InformationEntity(R.mipmap.ic_phone_round, "Phone", userViewModel?.userInfo?.value?.phone.toString()),
            InformationEntity(R.mipmap.ic_wallet_round, "Wallet", userViewModel?.userInfo?.value?.wallet.toString())
        )

        userViewModel?.userInfo?.value?.campus?.let {
            if (it.isNotEmpty()) {
                infoList.add(
                    InformationEntity(R.mipmap.ic_campus_round, "Campus", "${it[0].country}, ${it[0].city}")
                )
            }
        }

        userViewModel?.apply {
            informationAdapter?.value = InformationAdapter()
            informationAdapter?.value?.submitList(infoList)
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
            val id = getCourseIdByPosition(0)

            if (id != null) {
                val projectsList = getProjectsById(id)

                projectsAdapter?.value = ProjectsAdapter()
                projectsAdapter?.value?.submitList(projectsList)
            }
        }

        binding.projectsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userViewModel?.projectsAdapter?.value
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setAchievements() {
        userViewModel?.apply {
            achievementsAdapter?.value = AchievementsAdapter()
            achievementsAdapter?.value?.submitList(userInfo?.value?.achievements)

            binding.achievementsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = achievementsAdapter?.value
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    private fun getCourseIdByPosition(position: Int) = run {
        userViewModel?.userInfo?.value?.cursusUsers?.get(position)?.cursus?.id
    }

    private fun getProjectsById(id: Int) = run {
        userViewModel?.userInfo?.value?.projectsUsers
            ?.filter {
                it.cursusIds.contains(id) &&
                it.project.parentId == null
            }
            ?.sortedWith((compareBy { it.markedAt.toString() }))
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