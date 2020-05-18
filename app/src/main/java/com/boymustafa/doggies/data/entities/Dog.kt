package com.boymustafa.doggies.data.entities

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class Dog(

    val status:String,
    @SerializedName("message")
    val message:MutableList<String>
)