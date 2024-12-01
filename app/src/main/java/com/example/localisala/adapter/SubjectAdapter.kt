package com.example.localisala.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.localisala.databinding.SubjectItemBinding
import com.example.localisala.model.Period
import com.example.localisala.model.Subject

class SubjectAdapter(): RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    private val subjectList = mutableListOf<Subject>()

    inner class SubjectViewHolder(val itemBinding: SubjectItemBinding):RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val itemBinding = SubjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjectList[position]

        holder.itemBinding.txtSubject.text = subject.subjectName
        holder.itemBinding.txtProfessor.text = "Professor: ${subject.professor}"
        holder.itemBinding.txtSala.text = "Sala: ${subject.classroom}"
        holder.itemBinding.txtBloco.text = "Bloco: ${subject.block}"

        holder.itemBinding.expandableLayout.visibility = if (subject.isExpandable) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            subject.isExpandable = !subject.isExpandable
            notifyItemChanged(position)
        }

    }

    fun submitList(newList: List<Subject>) {
        subjectList.clear()
        subjectList.addAll(newList)
        notifyDataSetChanged()
    }
}