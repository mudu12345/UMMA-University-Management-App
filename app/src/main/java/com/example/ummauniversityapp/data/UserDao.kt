
package com.example.ummauniversityapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("""
        SELECT * FROM users
        WHERE registrationNumber = :registrationNumber
        LIMIT 1
    """)
    suspend fun getUserByRegistrationNumber(
        registrationNumber: String
    ): User?

    @Query("""
        SELECT * FROM users
        WHERE email = :login OR registrationNumber = :login
        LIMIT 1
    """)
    suspend fun getUserByLogin(login: String): User?

    @Query("SELECT * FROM users ORDER BY fullName ASC")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int
}
