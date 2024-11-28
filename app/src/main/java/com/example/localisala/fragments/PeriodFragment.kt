package com.example.localisala.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.localisala.adapter.PeriodAdapter
import com.example.localisala.databinding.FragmentPeriodBinding
import com.example.localisala.repository.MainRepository
import com.example.localisala.viewmodel.MainViewModel
import com.example.localisala.viewmodel.ViewModelFactory


class PeriodFragment : Fragment() {

    private val args: PeriodFragmentArgs by navArgs()

    private var _binding: FragmentPeriodBinding?= null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels{
        ViewModelFactory(MainRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeriodBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var periodAdapter = PeriodAdapter{ course ->

        }

        val rv_period = binding.rvPeriod
        rv_period.layoutManager = LinearLayoutManager(requireContext())
        rv_period.adapter = periodAdapter

        viewModel.periodes.observe(viewLifecycleOwner){ periods ->

            periods?.let { periodAdapter.submitList(it) }

        }

        viewModel.fetchPeriodData(args.university.id, args.course.id)


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}