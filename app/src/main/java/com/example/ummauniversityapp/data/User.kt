
package com.example.ummauniversityapp.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [
        Index(value = ["registrationNumber"], unique = true),
        Index(value = ["email"], unique = true)
    ]
)
data class User(

    val fullName: String,

    val registrationNumber: String,

    val email: String,

    val department: String,

    val role: String,

    // Store a password hash, never the original password.
    val passwordHash: String,

    val contactNumber: String = "",

    // This can later store the URI of the profile photo.
    val profilePhotoUri: String? = null,

    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0
)
