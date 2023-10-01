package org.cscie88c.week2

// write code for class Administrator below

class Administrator(
    var admin_name: String,
    var admin_email: String,
    var budget: Long
) extends UniversityEmployee(admin_name, admin_email) {
  override def description(): String = {
    s"Name: ${admin_name}, Email: ${admin_email}, Budget: $$${budget}"
  }
}
