package id.phephen.mvipattern.ui.main.view

import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.phephen.mvipattern.R
import id.phephen.mvipattern.data.api.ApiHelperImpl
import id.phephen.mvipattern.data.api.RetrofitBuilder
import id.phephen.mvipattern.data.model.User
import id.phephen.mvipattern.ui.main.adapter.MainAdapter
import id.phephen.mvipattern.ui.main.intent.MainIntent
import id.phephen.mvipattern.ui.main.viewmodel.MainViewModel
import id.phephen.mvipattern.ui.main.viewstate.MainState
import id.phephen.mvipattern.util.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())
    private lateinit var btnFetch: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        progressBar = findViewById(R.id.progressBar)
        btnFetch = findViewById<Button>(R.id.buttonFetchUser)
        btnFetch.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun setupUI() {
        rv = findViewById(R.id.recyclerView)
        rv.run {
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, (rv.layoutManager as LinearLayoutManager).orientation)
            )
        }
        rv.adapter = adapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this, ViewModelFactory(
            ApiHelperImpl(
                RetrofitBuilder.apiService
            )
        ))[MainViewModel::class.java]
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when(it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        btnFetch.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        progressBar.visibility = View.GONE
                        btnFetch.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        btnFetch.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        rv.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}