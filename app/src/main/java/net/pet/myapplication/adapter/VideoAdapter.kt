package net.pet.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.pet.myapplication.R
import net.pet.myapplication.VideoDialogFragment
import net.pet.myapplication.databinding.ItemBinding
import net.pet.myapplication.model.VideoItemUI
import net.pet.myapplication.utils.extensions.loadImageWithGlide
//in Activity
//clickListener: (url : String) -> Unit)
//private val listener: (url: String) -> Unit = { url ->
//    ....
//}
class VideoAdapter(private var list:List<VideoItemUI> = listOf(), private val clickListener: (url : String) -> Unit) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)

        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submit(newData: List<VideoItemUI>) {
        list = newData.toMutableList()
        notifyDataSetChanged()
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
}