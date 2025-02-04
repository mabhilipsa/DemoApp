package com.nisum.demo.user.data.dto

import com.google.gson.annotations.SerializedName

data class RandomUserResponseDto(
    @SerializedName("results") val users: List<User>,
)

data class User(
    val name: Name,
    val location: Location,
    val email: String,
    val dob: Dob,
    val phone: String,
    val cell: String,
    val picture: Picture,
)

data class Name(
    val first: String,
    val last: String,
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Any,
)

data class Street(
    val number: Int,
    val name: String,
)

data class Dob(
    val date: String,
    val age: Int,
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String,
)





