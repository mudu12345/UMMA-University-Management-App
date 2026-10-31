
package com.example.ummauniversityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ummauniversityapp.data.UniversityDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val spinnerRole = findViewById<Spinner>(R.id.spinnerRole)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)

        val userDao = UniversityDatabase.getDatabase(this).userDao()

        btnLogin.setOnClickListener {

            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()

            when {
                username.isEmpty() -> {
                    etUsername.error = "Enter your email or registration number"
                    etUsername.requestFocus()
                }

                password.isEmpty() -> {
                    etPassword.error = "Enter your password"
                    etPassword.requestFocus()
                }

                else -> {
                    btnLogin.isEnabled = false

                    lifecycleScope.launch {
                        try {
                            val user = userDao.getUserByLogin(username)

                            if (user != null &&
                                PasswordHasher.verifyPassword(
                                    password,
                                    user.passwordHash
                                )
                            ) {
                                val dashboardIntent = Intent(
                                    this@MainActivity,
                                    DashboardActivity::class.java
                                )

                                dashboardIntent.putExtra(
                                    "USER_NAME",
                                    user.fullName
                                )

                                dashboardIntent.putExtra(
                                    "USER_ROLE",
                                    user.role
                                )

                                startActivity(dashboardIntent)
                                etPassword.text.clear()

                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Invalid email, registration number or password.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        } catch (e: Exception) {
                            Toast.makeText(
                                this@MainActivity,
                                "Login failed. Please try again.",
                                Toast.LENGTH_LONG
                            ).show()
                        } finally {
                            btnLogin.isEnabled = true
                        }
                    }
                }
            }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(
                this,
                "Password reset will be implemented next.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
