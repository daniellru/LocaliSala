package com.example.localisala.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.localisala.R
import com.example.localisala.adapter.CourseAdapter
import com.example.localisala.databinding.FragmentUniversityBinding
import com.example.localisala.repository.MainRepository
import com.example.localisala.viewmodel.MainViewModel
import com.example.localisala.viewmodel.ViewModelFactory


class UniversityFragment : Fragment() {

    private val args: UniversityFragmentArgs by navArgs()

    private var _binding: FragmentUniversityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels{
        ViewModelFactory(MainRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUniversityBinding.inflate(inflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var courseAdapter = CourseAdapter{ course ->
            navigateToSubjectFragment()
        }

        val rv_couse = binding.rvCourses
        rv_couse.layoutManager = LinearLayoutManager(requireContext())
        rv_couse.adapter = courseAdapter

        viewModel.courses.observe(viewLifecycleOwner){ courses ->

            courses?.let { courseAdapter.submitList(it) }

        }

        viewModel.fetchCourseData(args.university.id)

    }

    private fun navigateToSubjectFragment() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}