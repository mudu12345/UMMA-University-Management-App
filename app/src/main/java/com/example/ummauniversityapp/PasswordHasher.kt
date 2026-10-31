package com.example.ummauniversityapp

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PasswordHasher {

    private const val ITERATIONS = 210000
    private const val KEY_LENGTH = 256
    private const val SALT_LENGTH = 16

    fun hashPassword(password: String): String {
        val salt = ByteArray(SALT_LENGTH)
        SecureRandom().nextBytes(salt)

        val spec = PBEKeySpec(
            password.toCharArray(),
            salt,
            ITERATIONS,
            KEY_LENGTH
        )

        val hash = SecretKeyFactory
            .getInstance("PBKDF2WithHmacSHA256")
            .generateSecret(spec)
            .encoded

        spec.clearPassword()

        val encodedSalt = Base64.encodeToString(
            salt, Base64.NO_WRAP
        )
        val encodedHash = Base64.encodeToString(
            hash, Base64.NO_WRAP
        )

        return "$encodedSalt:$encodedHash"
    }

    fun verifyPassword(
        password: String,
        storedValue: String
    ): Boolean {
        return try {
            val parts = storedValue.split(":")
            if (parts.size != 2) return false

            val salt = Base64.decode(parts[0], Base64.NO_WRAP)
            val expectedHash = Base64.decode(
                parts[1], Base64.NO_WRAP
            )

            val spec = PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                KEY_LENGTH
            )

            val actualHash = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec)
                .encoded

            spec.clearPassword()

            java.security.MessageDigest.isEqual(
                expectedHash,
                actualHash
            )
        } catch (e: Exception) {
            false
        }
    }
}