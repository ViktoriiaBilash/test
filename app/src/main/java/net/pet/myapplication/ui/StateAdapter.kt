package net.pet.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import net.pet.myapplication.R
import net.pet.myapplication.databinding.LoadStateFooterViewItemBinding

class StateAdapter() : LoadStateAdapter<StateAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent)
    }

    class LoadStateViewHolder(private val binding: LoadStateFooterViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }

        companion object {
            fun create(parent: ViewGroup): LoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.load_state_footer_view_item, parent, false)
                val binding = LoadStateFooterViewItemBinding.bind(view)
                return LoadStateViewHolder(binding)
            }
        }
    }
}