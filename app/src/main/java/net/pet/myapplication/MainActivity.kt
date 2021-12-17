package net.pet.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.pet.myapplication.adapter.VideoAdapter
import net.pet.myapplication.databinding.ActivityMainBinding
import net.pet.myapplication.viewmodel.ImageViewModel
import net.pet.myapplication.viewmodel.VideoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ImageViewModel by viewModel()
    private val viewModelVideo : VideoViewModel by viewModel()

    private val listener: (url: String) -> Unit = { url ->
        val dialog = VideoDialogFragment(url ?: "")
        dialog.show(supportFragmentManager, VideoDialogFragment.TAG)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //if class contains several optional parameters
    //"clickListener = listener" show which parameters is gave
   private val videoAdapter : VideoAdapter by lazy {
       VideoAdapter(clickListener = listener)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        observe()
    }

     private fun init() {
        with(binding.recyclerViewImage) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = videoAdapter
        }
    }

    //submit adapter if it needs
    private fun observe() {
        viewModelVideo.data.observe(this, { videos ->
            videoAdapter.submit(videos)
        })
    }
}