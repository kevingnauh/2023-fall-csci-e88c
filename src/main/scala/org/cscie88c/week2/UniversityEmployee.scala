package org.cscie88c.week2

// write the class UniversityEmployee below
class UniversityEmployee(var name: String, var email: String){
    def description(): String = {
        s"Name: ${name}, Email: ${email}"
    }
}