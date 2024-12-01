package com.example.localisala.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.localisala.databinding.UniversityItemBinding
import com.example.localisala.model.Period


class PeriodAdapter(
    private val onClick: (Period) -> Unit
): RecyclerView.Adapter<PeriodAdapter.PeriodViewHolder>() {

    private val periodList = mutableListOf<Period>()

    inner class PeriodViewHolder(val itemBinding: UniversityItemBinding):RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
        val itemBinding = UniversityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeriodViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return periodList.size
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {
        holder.itemBinding.txtUniversity.text = periodList[position].name

        holder.itemView.setOnClickListener{
            onClick(periodList[position])
        }
    }

    fun submitList(newList: List<Period>) {
        periodList.clear()
        periodList.addAll(newList)
        notifyDataSetChanged()
    }
}