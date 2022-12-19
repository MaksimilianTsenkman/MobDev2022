package com.example.finalproject.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.finalproject.databinding.FragmentTodoOverviewBinding


class TodoOverviewFragment : Fragment() {

    private lateinit var binding: FragmentTodoOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoOverviewBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}