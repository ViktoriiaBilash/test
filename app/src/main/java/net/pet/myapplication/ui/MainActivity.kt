package net.pet.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import net.pet.myapplication.databinding.ActivityMainBinding
import net.pet.myapplication.ui.adapter.VideoAdapter
import net.pet.myapplication.ui.viewmodel.VideoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModelVideo: VideoViewModel by viewModel()

    private val listener: (url: String) -> Unit = { url ->
        val dialog = VideoDialogFragment(url ?: "")
        dialog.show(supportFragmentManager, VideoDialogFragment.TAG)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val videoAdapter: VideoAdapter by lazy {
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
            adapter = videoAdapter.withLoadStateFooter(StateAdapter())
        }

        binding.searchView.setOnClickListener {
           val query = binding.searchView.query.toString()
            viewModelVideo.updateQuery(query)
            with(binding.searchView){
                setQuery("", false)
                clearFocus()
            }
        }
    }

     private fun observe() {
        videoAdapter.withLoadStateFooter(StateAdapter())
        this.lifecycleScope.launchWhenCreated {
            viewModelVideo.newData.collectLatest(videoAdapter::submitData)
        }
    }
}