package com.example.aston_intensiv_4.task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_intensiv_4.MainViewModel
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.databinding.FragmentAllUsersBinding
import kotlinx.coroutines.launch


class AllUsersFragment : Fragment() {

    private var _binding: FragmentAllUsersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var usersAdapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllUsersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersAdapter = UsersAdapter { user ->
            openEditUserFragment(user)
        }

        binding.usersRecycler.adapter = usersAdapter
        binding.usersRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch {
            viewModel.userList.collect { users ->
                usersAdapter.submitList(users)
            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openEditUserFragment(user: User) {
        val fragment = EditUserInfoFragment.newInstance(user)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}