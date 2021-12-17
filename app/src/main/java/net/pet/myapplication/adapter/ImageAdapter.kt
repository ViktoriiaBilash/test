package net.pet.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.pet.myapplication.R
import net.pet.myapplication.databinding.ItemBinding
import net.pet.myapplication.model.Image
import net.pet.myapplication.utils.extensions.loadImageWithGlide

class ImageAdapter(private var list: List<Image> = listOf(), private val listener : ItemClickListener) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)

        return ImageViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submit(newData: List<Image>) {
        list = newData.toMutableList()
        notifyDataSetChanged()
    }

    class ImageViewHolder(view: View, private val listener: ItemClickListener) : RecyclerView.ViewHolder(view) {

        private val binding = ItemBinding.bind(itemView)

        fun bind(item: Image) {
            with(binding){
                textViewTags.text = "Tags: ${item.tags}"
                textViewViews.text = "Views: ${item.views.toString()}"
                imageImage.loadImageWithGlide(item.previewURL)

            }
        }

    }
}