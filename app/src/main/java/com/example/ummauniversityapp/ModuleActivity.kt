
package com.example.ummauniversityapp

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ModuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val moduleName = intent.getStringExtra("MODULE_NAME")
            ?: "University Module"

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }

        val title = TextView(this).apply {
            text = moduleName
            textSize = 26f
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 24)
        }

        val description = TextView(this).apply {
            text = when (moduleName) {
                "My Units" -> """
            REGISTERED ACADEMIC UNITS

            1. BIT 210 - Data Structures and Algorithms

            2. BIT 220 - Web Application Development

            3. BIT 230 - Mobile Computing

            4. BIT 240 - Object Oriented Programming II

            5. BIT 250 - Software Engineering I

            6. BIT 260 - Digital Logic

            7. BIT 270 - Calculus I

            8. BIT 280 - Islamic Studies

            Note: Sample academic information for demonstration.
        """.trimIndent()

                "Results and GPA" -> """
    ACADEMIC RESULTS

    Data Structures and Algorithms
    Grade: A

    Web Application Development
    Grade: B+

    Mobile Computing
    Grade: A-

    Object Oriented Programming II
    Grade: B

    CURRENT GPA: 3.50

    Note: Sample results for demonstration.
""".trimIndent()

                "Lecture Timetable" -> """
    WEEKLY LECTURE TIMETABLE

    Monday: Data Structures
    Tuesday: Web Application Development
    Wednesday: Mobile Computing
    Thursday: Object Oriented Programming II
    Friday: Software Engineering

    Note: Sample timetable for demonstration.
""".trimIndent()
                "Examination Information" -> """
    EXAMINATION INFORMATION

    Semester: Semester One

    Examination Venue: Main Campus

    Examination Requirements:
    1. Student identification card
    2. Examination card
    3. Required stationery

    Arrive at least 30 minutes before the examination.

    Note: Sample information for demonstration.
""".trimIndent()

                "Fees and Payments" -> """
    STUDENT FEE INFORMATION

    Tuition Fees: KES 45,000

    Library Fees: KES 2,000

    Examination Fees: KES 3,000

    Total Fees: KES 50,000

    Payment Status: Pending

    Note: Sample financial information.
""".trimIndent()
                "Library Services" -> """
    UNIVERSITY LIBRARY

    Available Services:

    1. Borrow Books
    2. Return Books
    3. Search for Books
    4. Digital Library
    5. Reading Rooms

    Library Hours:
    Monday - Friday: 8:00 AM - 8:00 PM
    Saturday: 9:00 AM - 4:00 PM

    Note: Sample information for demonstration.
""".trimIndent()

                "University News" -> """
    UNIVERSITY ANNOUNCEMENTS

    1. Student registration is ongoing.

    2. Check the academic calendar for important dates.

    3. Students should clear outstanding fees.

    4. Examination preparation is encouraged.

    5. Visit the university library for learning materials.

    Note: Sample announcements for demonstration.
""".trimIndent()
                "Help Desk" -> """
    STUDENT SUPPORT AND HELP DESK

    Available Support:

    1. Academic Support
    2. IT Technical Support
    3. Financial Support
    4. Library Assistance
    5. Student Welfare

    Contact Information:

    Email: support@umma.ac.ke

    Office: Student Support Centre

    Note: Demonstration contact details.
""".trimIndent()
                "My Courses" -> """
    LECTURER COURSES

    1. Data Structures and Algorithms
    2. Web Application Development
    3. Mobile Computing
    4. Software Engineering I

    Semester: Semester One

    Note: Sample lecturer information.
""".trimIndent()

                "Class Lists" -> """
    STUDENT CLASS LIST

    1. Ahmed Mohamed
    2. Hassan Ali
    3. Fatima Abdi
    4. Maryan Omar
    5. Yusuf Ibrahim

    Total Students: 5

    Note: Sample student records.
""".trimIndent()

                "Record Attendance" -> """
    STUDENT ATTENDANCE

    Course: Data Structures and Algorithms

    Ahmed Mohamed - Present
    Hassan Ali - Present
    Fatima Abdi - Absent
    Maryan Omar - Present
    Yusuf Ibrahim - Present

    Note: Sample attendance records.
""".trimIndent()

                "Upload Course Materials" -> """
    COURSE MATERIALS

    Available Materials:

    1. Lecture Notes
    2. Course Presentations
    3. Practical Exercises
    4. Revision Questions

    Note: Material uploading will require further implementation.
""".trimIndent()

                "Manage Assignments" -> """
    ASSIGNMENT MANAGEMENT

    1. Create Assignment
    2. View Submitted Assignments
    3. Set Submission Deadline
    4. Review Student Work

    Note: Demonstration information.
""".trimIndent()

                "Enter Student Marks" -> """
    STUDENT MARKS

    Course: Data Structures and Algorithms

    Ahmed Mohamed - 78
    Hassan Ali - 85
    Fatima Abdi - 69
    Maryan Omar - 90
    Yusuf Ibrahim - 74

    Note: Sample examination marks.
""".trimIndent()

                "Consultation Appointments" -> """
    LECTURER CONSULTATION

    Available Services:

    1. Student Consultation
    2. Academic Guidance
    3. Project Supervision
    4. Appointment Scheduling

    Note: Demonstration information.
""".trimIndent()
                "Manage User Accounts" -> """
    USER ACCOUNT MANAGEMENT

    Available Functions:

    1. Register New Students
    2. Register Lecturers
    3. View User Accounts
    4. Update User Details
    5. Manage User Roles

    Note: Sample information. Account management
    operations require further implementation.
""".trimIndent()

                "Manage Departments and Courses" -> """
    DEPARTMENTS AND COURSES

    Departments:

    1. Information Technology
    2. Business Administration
    3. Education
    4. Islamic Studies

    Available Functions:

    Add, update and manage courses.

    Note: Sample information.
""".trimIndent()

                "Academic Calendar" -> """
    ACADEMIC CALENDAR

    Semester One:

    Student Registration
    Lectures Begin
    Continuous Assessment Tests
    End of Semester Examinations
    Semester Break

    Note: Sample academic calendar.
""".trimIndent()

                "Manage Timetables" -> """
    TIMETABLE MANAGEMENT

    Available Functions:

    1. Create Lecture Timetables
    2. Update Examination Timetables
    3. Assign Classrooms
    4. Manage Lecture Schedules

    Note: Sample information.
""".trimIndent()

                "Manage Fee Records" -> """
    STUDENT FEE MANAGEMENT

    Available Functions:

    1. View Student Fee Records
    2. Record Payments
    3. Check Outstanding Balances
    4. Generate Fee Statements

    Note: Sample information.
""".trimIndent()

                "Publish Announcements" -> """
    ANNOUNCEMENT MANAGEMENT

    Available Functions:

    1. Create Announcement
    2. Publish University News
    3. Update Notices
    4. Delete Old Announcements

    Note: Sample information.
""".trimIndent()

                "Generate Reports" -> """
    UNIVERSITY REPORTS

    Available Reports:

    1. Student Registration Report
    2. Academic Performance Report
    3. Financial Report
    4. Attendance Report
    5. Staff Report

    Note: Sample information.
""".trimIndent()
                "Help Desk Requests" -> """
    HELP DESK REQUESTS

    Available Functions:

    1. View Student Complaints
    2. Respond to Support Requests
    3. Track Open Requests
    4. Close Resolved Requests

    Note: Sample information.
""".trimIndent()

                "Student Welfare" -> """
    STUDENT WELFARE SERVICES

    Available Services:

    1. Counselling Support
    2. Accommodation Assistance
    3. Student Guidance
    4. Welfare Complaints

    Note: Sample information.
""".trimIndent()

                "Campus Facilities" -> """
    CAMPUS FACILITIES

    Available Facilities:

    1. Computer Laboratories
    2. University Library
    3. Lecture Rooms
    4. Cafeteria
    5. Sports Facilities

    Note: Sample information.
""".trimIndent()

                "Support Contacts" -> """
    SUPPORT CONTACTS

    IT Support
    Academic Office
    Finance Office
    Library Department
    Student Welfare Office

    Note: Demonstration contact information.
""".trimIndent()

                "University Notices" -> """
    UNIVERSITY NOTICES

    1. Keep the campus clean.
    2. Follow university regulations.
    3. Observe library opening hours.
    4. Check university announcements regularly.

    Note: Sample notices.
""".trimIndent()
                else -> "Welcome to $moduleName.\n\nUniversity information and services will be displayed here."
            }

            textSize = 17f
            gravity = Gravity.START
            setPadding(0, 0, 0, 32)
        }

        val backButton = Button(this).apply {
            text = "Back to Dashboard"
            setOnClickListener {
                finish()
            }
        }

        layout.addView(title)
        layout.addView(description)
        layout.addView(backButton)

        setContentView(layout)
    }
}
