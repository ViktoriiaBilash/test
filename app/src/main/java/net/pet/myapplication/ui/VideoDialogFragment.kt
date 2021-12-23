package net.pet.myapplication.ui

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import net.pet.myapplication.R
import net.pet.myapplication.databinding.CustomToastBinding
import net.pet.myapplication.databinding.DialogFragmentVideoBinding

class VideoDialogFragment(url : String) : DialogFragment() {
    private val binding: DialogFragmentVideoBinding by lazy {
        DialogFragmentVideoBinding.inflate(layoutInflater)
    }
    private val bind : CustomToastBinding by lazy{
        CustomToastBinding.inflate(layoutInflater)
    }
    //init ExoPlayer, mediaItem = url
        private val player : ExoPlayer by lazy {
        ExoPlayer.Builder(requireContext()).build()
            .also { exoPlayer ->
               binding.viewVideo.player = exoPlayer
                val mediaItem = MediaItem.fromUri(url)
                exoPlayer.setMediaItem((mediaItem))
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewVideo.setOnClickListener {
            val inflater = layoutInflater
            val container : ViewGroup = bind.toastLayout
            val layout: View = inflater.inflate(R.layout.custom_toast, container)
            val text: TextView = layout.findViewById(R.id.tv_custom_toast)
            text.text = "Some text"
            with (Toast(context)) {
                setGravity(Gravity.BOTTOM, 0, 0)
                duration = Toast.LENGTH_LONG
                view = layout
                show()
            }
            dismiss()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    //get started ExoPlayer
    override fun onResume() {
        super.onResume()
        player.prepare()
        player.play()
    }

    companion object {
        //unique dialogs tag
        @JvmStatic
        val TAG: String = VideoDialogFragment::class.java.simpleName
    }
}