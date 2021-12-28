package net.pet.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import net.pet.myapplication.databinding.ActivityMainBinding
import net.pet.myapplication.ui.adapter.VideoAdapter
import net.pet.myapplication.ui.viewmodel.VideoViewModel
import net.pet.myapplication.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModelVideo: VideoViewModel by viewModel()
    private var query :String = Constants.DEFAULT_QUERY

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
            query = binding.searchView.query.toString()
            with(binding.searchView){
                setQuery("", false)
                clearFocus()
                Log.e("TAG", "setOnSearchClickListener = $query")
            }
        }
    }

    private fun observe() {
        Log.e("TAG", "MainActivity observe()")
        videoAdapter.withLoadStateFooter(StateAdapter())
        this.lifecycleScope.launchWhenCreated {
            viewModelVideo.newData.collectLatest(videoAdapter::submitData)
        }
    }
}