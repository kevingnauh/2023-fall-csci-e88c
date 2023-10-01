package org.cscie88c.week3

import org.cscie88c.testutils.{StandardTest}
import scala.util.Random

class UtilFunctionsTest extends StandardTest {
  "UtilFunctions" when {
    // get pyth list
    val testList = UtilFunctions.pythTriplesUpto100

    "with pythTriplesUpto100" should {
      "verify elements in list are pythagorean triples" in {
        // write your test here
        for (_ <- 1 to 3) {
          // get a random index and select a random list
          val randomIndex = Random.nextInt(testList.size)
          val selectedList: (Int, Int, Int) = testList(randomIndex)

          val a = selectedList._1
          val b = selectedList._2
          val c = selectedList._3

          val a_squared = a * a
          val b_squared = b * b
          val c_squared = c * c

          (a_squared + b_squared) should be(c_squared)

        }
      }
    }
  }
}
