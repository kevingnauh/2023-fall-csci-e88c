package org.cscie88c.week3

import org.cscie88c.testutils.{StandardTest}

class StudentTest extends StandardTest {
  "Student Management System" when {
    "creating a student" should {
      "have properties - name, email, subject and score" in {
        // validate properties of Student
        val testStudent = Student("John", "johndoe@example.com", "Math", 100)

        testStudent.name shouldBe ("John")
        testStudent.email shouldBe ("johndoe@example.com")
        testStudent.subject shouldBe ("Math")
        testStudent.score shouldBe (100)
      }
    }

    // validating valid email
    "validating email" should {
      "return true for a valid email" in {
        val student = Student("John", "johndoe@example.com", "Math", 90)
        val isValid = Student.validateEmail(student)
        isValid shouldBe true
      }
      "return false for a invalid email" in {
        val student = Student("John", "johndoe#example.com", "Math", 90)
        val isValid = Student.validateEmail(student)
        isValid shouldBe false
      }
    }

    // validating averageScoreBySubject function
    "validating averageScoreBySubject" should {
      "return average score by subject" in {

        // create student list with sample data
        val studentList = List(
          Student("John", "johndoe@example.com", "Math", 90),
          Student("Mike", "mike@example.com", "Math", 85),
          Student("Chad", "chad@example.com", "Science", 78),
          Student("David", "david@example.com", "Math", 92)
        )

        val subject = "Math"
        val average = Student.averageScoreBySubject(subject, studentList)
        average shouldBe 89.0

      }
    }

    // validating averageScoreByStudent function
    "validating averageScoreByStudent" should {
      "return average score by student" in {

        val studentList = List(
          Student("John", "johndoe@example.com", "Math", 90),
          Student("John", "johndoe@example.com", "Science", 92),
          Student("John", "johndoe@example.com", "History", 94),
          Student("John", "johndoe@example.com", "English", 96),
          Student("Mike", "mike@example.com", "Math", 85),
          Student("Chad", "chad@example.com", "Science", 78),
          Student("David", "david@example.com", "Math", 92)
        )

        val testStudent = Student("John", "johndoe@example.com", "Math", 90)
        val average = Student.averageScoreByStudent(testStudent, studentList)
        average shouldBe 93

      }
    }

  }
}
