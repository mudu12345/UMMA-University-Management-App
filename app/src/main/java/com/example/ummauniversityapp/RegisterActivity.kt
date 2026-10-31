package com.example.ummauniversityapp

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ummauniversityapp.data.UniversityDatabase
import com.example.ummauniversityapp.data.User
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etRegNumber = findViewById<EditText>(R.id.etRegNumber)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etDepartment = findViewById<EditText>(R.id.etDepartment)
        val etNewPassword = findViewById<EditText>(R.id.etNewPassword)
        val etConfirmPassword =
            findViewById<EditText>(R.id.etConfirmPassword)
        val spinnerRole =
            findViewById<Spinner>(R.id.spinnerRegisterRole)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        val database = UniversityDatabase.getDatabase(this)
        val userDao = database.userDao()

        btnRegister.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val regNumber = etRegNumber.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val department = etDepartment.text.toString().trim()
            val password = etNewPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()
            val role = spinnerRole.selectedItem.toString()

            when {
                fullName.isEmpty() -> {
                    etFullName.error = "Enter your full name"
                    etFullName.requestFocus()
                }

                regNumber.isEmpty() -> {
                    etRegNumber.error =
                        "Enter your registration number or staff ID"
                    etRegNumber.requestFocus()
                }

                email.isEmpty() ||
                        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    etEmail.error = "Enter a valid email address"
                    etEmail.requestFocus()
                }

                department.isEmpty() -> {
                    etDepartment.error = "Enter your department"
                    etDepartment.requestFocus()
                }

                password.length < 8 -> {
                    etNewPassword.error =
                        "Password must contain at least 8 characters"
                    etNewPassword.requestFocus()
                }

                password != confirmPassword -> {
                    etConfirmPassword.error = "Passwords do not match"
                    etConfirmPassword.requestFocus()
                }

                else -> {
                    btnRegister.isEnabled = false

                    lifecycleScope.launch {
                        try {
                            val existingUser =
                                userDao.getUserByEmail(email)

                            val existingRegNumber =
                                userDao.getUserByRegistrationNumber(regNumber)

                            if (existingUser != null ||
                                existingRegNumber != null
                            ) {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Email or registration number already exists.",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                val hashedPassword =
                                    PasswordHasher.hashPassword(password)

                                val user = User(
                                    fullName = fullName,
                                    registrationNumber = regNumber,
                                    email = email,
                                    department = department,
                                    role = role,
                                    passwordHash = hashedPassword
                                )

                                userDao.insertUser(user)

                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Account registered successfully!",
                                    Toast.LENGTH_LONG
                                ).show()

                                finish()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registration failed. Please try again.",
                                Toast.LENGTH_LONG
                            ).show()
                        } finally {
                            btnRegister.isEnabled = true
                        }
                    }
                }
            }
        }
    }
}
