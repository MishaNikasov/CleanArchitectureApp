package com.nikasov.cleanarchitectureapp.presentation.base

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

abstract class BaseAdapter<Model>(
    private val differ: AsyncListDiffer<Model>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: List<Model> = differ.currentList

    fun submitList(list: List<Model>?) {
        if (list == null) return
        differ.submitList(list)
    }

    override fun getItemCount() = list.size

}
