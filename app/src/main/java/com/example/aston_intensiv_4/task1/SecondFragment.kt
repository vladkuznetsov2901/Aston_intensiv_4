package com.example.aston_intensiv_4.task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.goToCBtn.setOnClickListener {
            val thirdFragment = ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_MESSAGE, "Hello Fragment C")
                }
            }
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, thirdFragment)
                addToBackStack("ThirdFragment")
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