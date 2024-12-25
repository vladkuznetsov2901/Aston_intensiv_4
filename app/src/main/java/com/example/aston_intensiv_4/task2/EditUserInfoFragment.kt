package com.example.aston_intensiv_4.task2

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.aston_intensiv_4.MainViewModel
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.databinding.FragmentAllUsersBinding
import com.example.aston_intensiv_4.databinding.FragmentEditUserInfoBinding


class EditUserInfoFragment : Fragment() {

    private var _binding: FragmentEditUserInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: User


    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable("user", User::class.java)
                    ?: throw IllegalArgumentException("User is null")
            } else {
                @Suppress("DEPRECATION")
                it.getParcelable("user") ?: throw IllegalArgumentException("User is null")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditUserInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.setImageResource(user.photo)
        binding.userNameEditText.setText(user.firstName)
        binding.userLastnameEditText.setText(user.lastName)
        binding.userNumberEditText.setText(user.phoneNumber)

        binding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.saveBtn.setOnClickListener {
            val updatedUser = user.copy(
                firstName = binding.userNameEditText.text.toString(),
                lastName = binding.userLastnameEditText.text.toString(),
                phoneNumber = binding.userNumberEditText.text.toString()
            )

            viewModel.updateUser(updatedUser)

            parentFragmentManager.popBackStack()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(user: User): EditUserInfoFragment {
            val fragment = EditUserInfoFragment()
            val args = Bundle().apply {
                putParcelable("user", user)
            }
            fragment.arguments = args
            return fragment
        }
    }

}