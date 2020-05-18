package com.boymustafa.doggies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.boymustafa.doggies.data.presentation.MainViewModel
import com.boymustafa.doggies.data.presentation.adapter.DogAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

const val NUMBER_OF_COLUMN = 3


class MainActivity : AppCompatActivity() {

    // Instantiate viewModel with Koin
    private val viewModel: MainViewModel by viewModel()
    private lateinit var dogAdapter: DogAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate custom Adapter
        dogAdapter = DogAdapter()
        dogsRecyclerView.apply {
            // Displaying data in a Grid design
            layoutManager = GridLayoutManager(this@MainActivity, NUMBER_OF_COLUMN)
            adapter =  dogAdapter
        }

        // Initiate the observers on viewModel fields and then starts the API request
        initViewModel()
    }

    private fun initViewModel(){
        // Observe dogsList and update our adapter if we get new one from API
        viewModel.dogsList.observe(this, Observer { newDogList ->
            dogAdapter.updateData(newDogList.message)
        })

        // Observe showLoading value and display or hide our activity's progressBar
        viewModel.showLoading.observe(this, Observer { showLoading ->
            mainProgressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })

        // Observe showError value and display the error message as a Toast
        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this,showError,Toast.LENGTH_SHORT).show()
        })

        // The observers are set, we can now ask API to load a data list
        viewModel.loadDogs()
    }
}
