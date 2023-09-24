package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for University employee below
class UniversityEmployeeTest extends StandardTest {
  "UniversityEmployee" when {
    "instantiated" should {
      "have a name property" in {
        new UniversityEmployee("Mike Stone", "mstone@harvard.edu").name should be ("Mike Stone")
        new UniversityEmployee("Kevin Huang", "kh@harvard.edu").name should be ("Kevin Huang")
        new UniversityEmployee("John Doe", "johndoe@harvard.edu").name should be ("John Doe")
      }
      "have a email property" in {
        new UniversityEmployee("Mike Stone", "mstone@harvard.edu").email should be ("mstone@harvard.edu")
        new UniversityEmployee("Kevin Huang", "kh@harvard.edu").email should be ("kh@harvard.edu")
        new UniversityEmployee("John Doe", "johndoe@harvard.edu").email should be ("johndoe@harvard.edu")
      }
      "have the correct name in the description" in {
        val employee = new UniversityEmployee("John Doe", "johndoe@harvard.edu")
        val description = employee.description()
        description should include("Name: John Doe")
      }
      "have the correct email in the description" in {
        val employee = new UniversityEmployee("John Doe", "johndoe@harvard.edu")
        val description = employee.description()
        description should include("Email: johndoe@harvard.edu")
      }
    }
  }
}
