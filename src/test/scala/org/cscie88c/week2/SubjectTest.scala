package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Subject below

class SubjectTest extends StandardTest {
  "Subject" when {
    val subject = Subject("1, Physics, true")

    "instantiated" should {
      "have an id property" in {
        subject.id should be("1")
      }
      "have a name property" in {
        subject.name should be("Physics")
      }
      "have a isSTEM property" in {
        subject.isSTEM should be(true)
      }
    }
    "instantiated with an invalid CSV string" should {
      "throw an IllegalArgumentException for missing fields" in {
        assertThrows[IllegalArgumentException] {
          Subject("1, Physics") // Missing isStem field
        }
      }
    }
  }
}
