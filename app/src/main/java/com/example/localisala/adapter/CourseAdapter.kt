package com.example.localisala.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.localisala.databinding.UniversityItemBinding
import com.example.localisala.model.Course
import com.example.localisala.model.University


class CourseAdapter(
    private val onClick: (Course) -> Unit
): RecyclerView.Adapter<CourseAdapter.PeriodViewHolder>() {

    private val courseList = mutableListOf<Course>()

    inner class PeriodViewHolder(val itemBinding: UniversityItemBinding): RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
        val itemBinding = UniversityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeriodViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {
        holder.itemBinding.txtUniversity.text = courseList[position].name

        holder.itemView.setOnClickListener{
            onClick(courseList[position])
        }
    }

    fun submitList(newList: List<Course>) {
        courseList.clear()
        courseList.addAll(newList)
        notifyDataSetChanged()
    }
}