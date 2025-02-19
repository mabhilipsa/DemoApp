package com.nisum.demo.user.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RandomUserResponseDto(
    @SerializedName("results") val results: List<User>,
    @SerializedName("info") val info: Info
): Parcelable
@Parcelize
data class Info(
    @SerializedName("seed") val seed: String,
    @SerializedName("results") val results: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("version") val version: String
) :Parcelable
@Parcelize
data class User(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: Name,
    @SerializedName("location") val location: Location,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val dob: Dob,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("picture") val picture: Picture
): Parcelable
@Parcelize
data class Name(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
): Parcelable
@Parcelize
data class Location(
    @SerializedName("street") val street: Street,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String
): Parcelable
@Parcelize
data class Street(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String
): Parcelable
@Parcelize
data class Dob(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
): Parcelable
@Parcelize
data class Picture(
    @SerializedName("large") val large: String
): Parcelable
