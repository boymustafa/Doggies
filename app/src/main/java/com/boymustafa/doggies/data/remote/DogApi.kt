package com.boymustafa.doggies.data.remote

import com.boymustafa.doggies.data.entities.Dog
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    /* Get route used to retrieve dog images, limit is the number of dogs item */

    @GET("image/random/{number}")
    fun getDogs(@Path("number") number: Int) : Deferred<Dog>

}