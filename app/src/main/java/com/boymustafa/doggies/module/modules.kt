package com.boymustafa.doggies.module

import com.boymustafa.doggies.data.presentation.MainViewModel
import com.boymustafa.doggies.data.remote.DogApi
import com.boymustafa.doggies.data.repositories.DogRepository
import com.boymustafa.doggies.data.repositories.DogRepositoryImpl
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val DOG_API_BASE_URL = "https://dog.ceo/api/breeds/"

val appModule = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService<DogApi>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = DOG_API_BASE_URL
        )
    }

    // Tells Koin how to create an instance of CatRepository
    factory<DogRepository> { DogRepositoryImpl(dogApi = get()) }
    // Specific viewModel pattern to tell Koin how to build MainViewModel
    viewModel { MainViewModel(dogRepository = get()) }
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type","application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)

    }.build()
}

/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory:CallAdapter.Factory,
    baseUrl:String
) : T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}