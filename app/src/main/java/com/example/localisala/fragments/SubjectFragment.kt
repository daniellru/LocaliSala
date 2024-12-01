package com.example.localisala.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.localisala.R
import com.example.localisala.adapter.SubjectAdapter
import com.example.localisala.databinding.FragmentPeriodBinding
import com.example.localisala.databinding.FragmentSubjectBinding
import com.example.localisala.repository.MainRepository
import com.example.localisala.viewmodel.MainViewModel
import com.example.localisala.viewmodel.ViewModelFactory


class SubjectFragment : Fragment() {

    private var _binding: FragmentSubjectBinding?= null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels{
        ViewModelFactory(MainRepository())
    }

    private val args: SubjectFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubjectBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var subjecAdapter = SubjectAdapter()

        val rv_subject = binding.rvSubject
        rv_subject.layoutManager = LinearLayoutManager(requireContext())
        rv_subject.adapter = subjecAdapter

        viewModel.subjects.observe(viewLifecycleOwner){subjects ->

            subjects?.let { subjecAdapter.submitList(it) }
        }

        viewModel.fetchSubjectData(
            args.university.id,
            args.course.id,
            args.period.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}