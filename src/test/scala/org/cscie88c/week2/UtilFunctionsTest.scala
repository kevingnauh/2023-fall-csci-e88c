package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

class UtilFunctionsTest extends StandardTest {

  "UtilFunctions" when {
    "maximum" should {
      "return maximum of two ints when first integer is greater" in {
        UtilFunctions.maximum(2, 1) should be(2)
      }
      // add more unit tests for maximum below
      "return maximum of two ints when second integer is greater" in {
        UtilFunctions.maximum(1, 2) should be(2)
      }
      "return second integer when both integers are equal" in {
        UtilFunctions.maximum(2, 2) should be(2)
      }
    }
    // add unit tests for average below
    "average" should {
      "return average value of two ints" in {
        UtilFunctions.average(3, 5) should be(4)
      }
    }

  }
}
