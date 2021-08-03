package com.example.swifty_companion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
import java.text.SimpleDateFormat
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
            buttonSettings?.position = adapterPosition
            buttonSettings?.id = id
            cursusAdapter?.value?.notifyDataSetChanged()
            skillsAdapter?.value?.submitList(getSkillsByIdSortedLevel(id))
            projectsAdapter?.value?.submitList(getProjectsByIdSortedDate(id))
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
        userViewModel?.apply {
            userInfo?.value?.apply {
                binding.profilePicture.loadUrl(imageUrl)

                val infoList: MutableList<InformationEntity> = mutableListOf(
                    InformationEntity(R.mipmap.ic_login_round, "Login", login),
                    InformationEntity(R.mipmap.ic_email_round, "Email", email),
                    InformationEntity(R.mipmap.ic_phone_round, "Phone", phone),
                    InformationEntity(R.mipmap.ic_wallet_round, "Wallet", wallet),
                    InformationEntity(R.mipmap.ic_correction_point, "Correction point", correctionPoint),
                )

                campus.let {
                    if (it.isNotEmpty()) {
                        infoList.add(
                            InformationEntity(R.mipmap.ic_campus_round, "Campus", "${it[0].country}, ${it[0].city}")
                        )
                    }
                }

                informationAdapter?.value = InformationAdapter()
                informationAdapter?.value?.submitList(infoList)

                with(binding.informationRecyclerView) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = informationAdapter?.value
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                }
            }
        }
    }

    private fun setCourses() {
        userViewModel?.apply {
            cursusAdapter?.value = CursusAdapter(this@UserFragment, this)
            cursusAdapter?.value?.submitList(getCoursesSortedDate())

            with(binding.cursusRecyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = cursusAdapter?.value
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    private fun setSkills() {
        userViewModel?.apply {
            val skills = getSkillsByPositionSortedLevel(0)

            if (skills.isNullOrEmpty()) {
                binding.skillsHeader.visibility = View.GONE
            }
            skillsAdapter?.value = SkillsAdapter()
            skillsAdapter?.value?.submitList(skills)

            with(binding.skillsRecyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = skillsAdapter?.value
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    private fun setProjects() {
        userViewModel?.apply {
            getCourseIdByPosition(0)?.let {
                projectsAdapter?.value = ProjectsAdapter()
                projectsAdapter?.value?.submitList(getProjectsByIdSortedDate(it))
            }

            with(binding.projectsRecyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = projectsAdapter?.value
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    private fun setAchievements() {
        userViewModel?.apply {
            if (achievements.isNullOrEmpty()) {
                binding.achievementHeader.visibility = View.GONE
            }
            achievementsAdapter?.value = AchievementsAdapter()
            achievementsAdapter?.value?.submitList(achievements)

            binding.achievementsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = achievementsAdapter?.value
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
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