package net.pet.myapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import net.pet.myapplication.R
import net.pet.myapplication.databinding.LoadStateFooterViewItemBinding

class StateAdapter : LoadStateAdapter<StateAdapter.LoadStateViewHolder>() {
    init {
        Log.e("TAG", "StateAdapter()")
    }
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        Log.e("TAG", "onBindViewHolder")
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        Log.e("TAG", "StateAdapter() onCreateViewHolder")
        return LoadStateViewHolder.create(parent)
    }

    class LoadStateViewHolder(private val binding: LoadStateFooterViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            Log.e("TAG", "StateAdapter() LoadStateViewHolder bind  = $loadState")


            binding.progressBar.isVisible = loadState is LoadState.Loading
        }

        companion object {
            fun create(parent: ViewGroup): LoadStateViewHolder {
                Log.e("TAG", "StateAdapter() LoadStateViewHolder companion object")
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.load_state_footer_view_item, parent, false)
                val binding = LoadStateFooterViewItemBinding.bind(view)
                return LoadStateViewHolder(binding)
            }
        }
    }
}