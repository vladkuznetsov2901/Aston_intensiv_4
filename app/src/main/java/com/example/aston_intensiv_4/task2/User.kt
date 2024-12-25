package com.example.aston_intensiv_4.task2

import android.os.Parcel
import android.os.Parcelable

data class User(
    val id: Int,
    val photo: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {return 0}

    override fun writeToParcel(p0: Parcel, p1: Int) {}

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
