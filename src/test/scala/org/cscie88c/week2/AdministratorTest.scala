package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Administrator below
class AdministratorTest extends StandardTest {
  "Administrator" when {
    "instantiated" should {
      "have name in the description" in {
        new Administrator("Malcolm Zieto", "mzieto@harvard.edu", 1500800)
          .description() should include("Malcolm Zieto")
      }
      "have email in the description" in {
        new Administrator("Malcolm Zieto", "mzieto@harvard.edu", 1500800)
          .description() should include("mzieto@harvard.edu")
      }
      "have budget in the description" in {
        new Administrator("Malcolm Zieto", "mzieto@harvard.edu", 1500800)
          .description() should include("1500800")
      }
    }
  }
}
