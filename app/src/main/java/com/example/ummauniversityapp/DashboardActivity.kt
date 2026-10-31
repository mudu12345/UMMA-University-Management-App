
package com.example.ummauniversityapp

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Receive the logged-in user's details.
        val role = intent.getStringExtra("USER_ROLE") ?: "Student"
        val userName = intent.getStringExtra("USER_NAME") ?: "University Member"

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val tvDashboardRole = findViewById<TextView>(R.id.tvDashboardRole)
        val menuContainer = findViewById<LinearLayout>(R.id.menuContainer)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        tvWelcome.text = "Welcome, $userName!"
        tvDashboardRole.text = "$role Dashboard"

        // Display menu options according to the selected role.
        val menuOptions = when (role) {

            "Student" -> listOf(
                "My Units",
                "Results and GPA",
                "Lecture Timetable",
                "Examination Information",
                "Assignments and Learning Materials",
                "Fees and Payments",
                "Library Services",
                "University News",
                "Help Desk"
            )

            "Lecturer" -> listOf(
                "My Courses",
                "Class Lists",
                "Record Attendance",
                "Upload Course Materials",
                "Manage Assignments",
                "Enter Student Marks",
                "Consultation Appointments"
            )

            "Administrator" -> listOf(
                "Manage User Accounts",
                "Manage Departments and Courses",
                "Academic Calendar",
                "Manage Timetables",
                "Manage Fee Records",
                "Publish Announcements",
                "Generate Reports"
            )

            "Support Staff" -> listOf(
                "Help Desk Requests",
                "Student Welfare",
                "Campus Facilities",
                "Support Contacts",
                "University Notices"
            )

            else -> listOf("University News", "Help Desk")
        }

        // Create a button for every menu option.
        for (option in menuOptions) {

            val menuButton = Button(this).apply {
                text = option
                isAllCaps = false
                setOnClickListener {
                    val moduleIntent = Intent(
                        this@DashboardActivity,
                        ModuleActivity::class.java
                    )

                    moduleIntent.putExtra("MODULE_NAME", option)
                    startActivity(moduleIntent)
                }
            }

            val buttonParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            buttonParams.bottomMargin =
                (8 * resources.displayMetrics.density).toInt()

            menuContainer.addView(menuButton, buttonParams)
        }

        // Return to the login screen when logging out.
        btnLogout.setOnClickListener {
            finish()
        }
    }
}
