package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Student below

class StudentTest extends StandardTest {
  "Student" when {
    val student =
      Student("1, Emmy, Conrart, econrart0@gizmodo.com, Male, China")

    "instantiated" should {
      "have an id property" in {
        student.id should be("1")
      }
      "have a firstName property" in {
        student.firstName should be("Emmy")
      }
      "have a lastName property" in {
        student.lastName should be("Conrart")
      }
      "have a email property" in {
        student.email should be("econrart0@gizmodo.com")
      }
      "have a gender property" in {
        student.gender should be("Male")
      }
      "have a country property" in {
        student.country should be("China")
      }
    }
    "instantiated with an invalid CSV string" should {
      "throw an IllegalArgumentException for missing fields" in {
        assertThrows[IllegalArgumentException] {
          Student(
            "1, Emmy, Conrart, econrart0@gizmodo.com, Male"
          ) // Missing country field
        }
      }
    }
    "studentNamesByCountry" should {
      "return a list of student names for a given country" in {
        val chinaStudents = Student.studentNamesByCountry("China")
        val usaStudents = Student.studentNamesByCountry("United States")

        chinaStudents should contain theSameElementsAs List(
          "Emmy Conrart",
          "Jesse Chismon",
          "Jocelyn Blaxlande"
        )
        usaStudents should contain theSameElementsAs List(
          "Marin Blasoni",
          "Delmore Scriver"
        )
      }
      "return an empty list when there are no students from the given country" in {
        val germanyStudents = Student.studentNamesByCountry("Germany")
        germanyStudents shouldBe empty
      }
    }
    "studentTotalsByCountry" should {
      "return an int for a given country" in {
        val chinaStudentsCount = Student.studentTotalsByCountry("China")
        val usaStudentsCount = Student.studentTotalsByCountry("United States")

        chinaStudentsCount should be(3)
        usaStudentsCount should be(2)
      }
      "return 0 for a country not in the list" in {
        val germanStudentsCount = Student.studentTotalsByCountry("Germany")

        germanStudentsCount should be(0)
      }
    }
  }
}
