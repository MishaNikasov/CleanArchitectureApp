package com.nikasov.cleanarchitectureapp.presentation.base

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

abstract class BaseAdapter<Model> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected abstract var differ: AsyncListDiffer<Model>

    protected val callback = object : DiffUtil.ItemCallback<Model>() {
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem?.equals(newItem) ?: true
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    var list: List<Model>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount(): Int {
        return list.size
    }

}
