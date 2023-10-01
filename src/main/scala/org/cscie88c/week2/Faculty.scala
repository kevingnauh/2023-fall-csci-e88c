package org.cscie88c.week2

// write code for class Faculty below
class Faculty(
    var faculty_name: String,
    var faculty_email: String,
    var courseId: String
) extends UniversityEmployee(faculty_name, faculty_email) {
  override def description(): String = {
    s"Name: $name, Email: $email, Course: $courseId"
  }
}
