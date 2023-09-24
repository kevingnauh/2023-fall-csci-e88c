package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Faculty below
class FacultyTest extends StandardTest {
  "Faculty" when {
    "instantiated" should {
      "have name in the description" in {
        new Faculty("James Henry", "jhenry@harvard.edu", "History101").description() should include("James Henry") 
      }
      "have email in the description" in {
        new Faculty("James Henry", "jhenry@harvard.edu", "History101").description() should include("jhenry@harvard.edu")
      }
      "have courseId in the description" in {
        new Faculty("James Henry", "jhenry@harvard.edu", "History101").description() should include("History101")
      }
    }
  }
}
