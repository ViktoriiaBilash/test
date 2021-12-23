package net.pet.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.pet.myapplication.R
import net.pet.myapplication.databinding.ItemBinding
import net.pet.myapplication.model.VideoItemUI
import net.pet.myapplication.utils.extensions.loadImageWithGlide

class VideoAdapter(private val clickListener: (url : String) -> Unit) :
    PagingDataAdapter<VideoItemUI, VideoAdapter.VideoViewHolder>(VideoComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)

        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBinding.bind(itemView)
        fun bind(item: VideoItemUI) {
            with(binding){
                textViewTags.text = "Tags: ${item.tags}"
                textViewViews.text = "Views: ${item.views}"
                imageImage.loadImageWithGlide("https://i.vimeocdn.com/video/${item.pictureId}_295x166.jpg")
                imageImage.setOnClickListener {
                    clickListener.invoke(item.url)
                }
            }
        }
    }

    object VideoComparator : DiffUtil.ItemCallback<VideoItemUI>(){
        override fun areItemsTheSame(oldItem: VideoItemUI, newItem: VideoItemUI)=
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: VideoItemUI, newItem: VideoItemUI)=
            oldItem == newItem
    }
}