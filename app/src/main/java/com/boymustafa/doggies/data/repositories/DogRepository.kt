package com.boymustafa.doggies.data.repositories

import com.boymustafa.doggies.UseCaseResult
import com.boymustafa.doggies.data.entities.Dog
import com.boymustafa.doggies.data.remote.DogApi

const val NUMBER_OF_DOGS = 30

interface DogRepository {
    // Suspend is used to await the result from Deferred
    suspend fun getDogList() : UseCaseResult<Dog>

}

class DogRepositoryImpl(private val dogApi: DogApi) : DogRepository {
    override suspend fun getDogList(): UseCaseResult<Dog> {
        return try {
            val result = dogApi.getDogs(NUMBER_OF_DOGS).await()
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
//    override suspend fun getDogList(): UseCaseResult<<Dog> {
//        /*
//        We try to return a list of cats from the API
//         Await the result from web service and then return it, catching any error from API
//         */
//
//        return try {
//            val result = dogApi.getDogs(NUMBER_OF_DOGS).await()
//            UseCaseResult.Success(result)
//        } catch (ex: Exception) {
//            UseCaseResult.Error(ex)
//        }
//    }


}