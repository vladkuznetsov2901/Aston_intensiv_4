package com.example.aston_intensiv_4.task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = arguments?.getString(KEY_MESSAGE)

        binding.messageText.text = message

        binding.goToABtn.setOnClickListener {
            parentFragmentManager.popBackStack("FirstFragment", 0)
        }

        binding.goToDBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace<FourthFragment>(R.id.fragment_container)
                addToBackStack("FourthFragment")
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_MESSAGE = "message"
    }
}