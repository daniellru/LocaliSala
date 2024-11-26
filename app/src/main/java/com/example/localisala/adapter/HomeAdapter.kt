package com.example.localisala.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.localisala.databinding.UniversityItemBinding
import com.example.localisala.model.University

class HomeAdapter(
    private val onClick: (University) -> Unit): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val universities = mutableListOf<University>()

    inner class HomeViewHolder(val itemBinding: UniversityItemBinding):RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemBinding = UniversityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return universities.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.itemBinding.txtUniversity.text = universities[position].name

        holder.itemView.setOnClickListener{
            onClick(universities[position])
        }

    }

    fun submitList(newList: List<University>) {
        universities.clear()
        universities.addAll(newList)
        notifyDataSetChanged()
    }
}