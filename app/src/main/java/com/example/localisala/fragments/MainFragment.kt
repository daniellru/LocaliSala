package com.example.localisala.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.localisala.HomeAdapter
import com.example.localisala.viewmodel.MainViewModel
import com.example.localisala.databinding.FragmentMainBinding
import com.example.localisala.repository.MainRepository
import com.example.localisala.viewmodel.ViewModelFactory

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels{
        ViewModelFactory(MainRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var homeAdapter = HomeAdapter{ university ->
            navigateToUniversityFragment()
        }

        val recyclerView = binding.rvUniversities
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = homeAdapter

        viewModel.universities.observe(viewLifecycleOwner){universities ->
            universities?.let { homeAdapter.submitList(it) }
        }

        viewModel.fetchData()
    }

    private fun navigateToUniversityFragment() {
        val action = MainFragmentDirections.actionMainFragmentToUniversityFragment()
        if(action != null){
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}