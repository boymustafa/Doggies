package com.boymustafa.doggies.data.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boymustafa.doggies.UseCaseResult
import com.boymustafa.doggies.data.entities.Dog
import com.boymustafa.doggies.data.repositories.DogRepository
import com.boymustafa.doggies.utils.SingleLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel (private val dogRepository: DogRepository) : ViewModel(), CoroutineScope {

    // Coroutine's background job
    private val job = Job()

    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val dogsList : MutableLiveData<Dog> = MutableLiveData<Dog>()
    //    val dogsList : Dog
    val showError = SingleLiveEvent<String>()

    fun loadDogs() {
        // Show progressBar during the operation on the MAIN (default) thread
        showLoading.value = true

        launch {
            // Switching from MAIN to IO thread for API operation
            // Update our data list with the new one from API
            val result = withContext(Dispatchers.IO) {
                dogRepository.getDogList()
            }
            showLoading.value = false
            when (result) {
                is UseCaseResult.Success -> dogsList.value = result.data
                is UseCaseResult.Error -> showError.value = result.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }

}