# Doggies
Koin + Coroutines with MVVM in Kotlin

This repo is demonstrate how to develop Android app with good practices using [Koin](https://github.com/InsertKoinIO/koin) and [Coroutines](https://github.com/Kotlin/kotlinx.coroutines). The design pattern that I use is MVVM. Why MVVM? because Google support it with the new [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) libraries. The app use [thedogapi] (https://dog.ceo/dog-api/documentation/random) to show random images of dogs. It will show the list of dogs in MainActivity.

Libraries that I use in this repo:

        // Glide for loading and caching dog images
        implementation 'com.github.bumptech.glide:glide:4.9.0'
        kapt 'com.github.bumptech.glide:compiler:4.9.0'
        // Retrofit as our REST service
        implementation 'com.squareup.retrofit2:retrofit:2.5.0'
        implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'
        implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
        // Koin for the dependencies injections
        implementation 'org.koin:koin-android-viewmodel:2.0.0-rc-2'
        // Coroutines for asynchronous calls (and Deferred’s adapter)
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0'
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.2.0'
        // Coroutines - Deferred adapter
        implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'


