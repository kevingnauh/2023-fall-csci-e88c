package org.cscie88c.week3

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class UtilFunctionsPropertyTest
    extends AnyFunSuite
    with Matchers
    with ScalaCheckPropertyChecks {

  val triplesGen: Gen[(Int, Int, Int)] =
    Gen.oneOf(UtilFunctions.pythTriplesUpto100)

  val randomTriple: (Int, Int, Int) = triplesGen.sample.getOrElse((0, 0, 0))

  val x = randomTriple._1
  val y = randomTriple._2
  val z = randomTriple._3

  test("mult2 result test") {
    forAll { (x: Int, y: Int) =>
      UtilFunctions.mult2(x, y) shouldBe x * y
    }
  }

  test("mult2 distributive property test") {
    forAll { (x: Int, y: Int, z: Int) =>
      val dist_left = UtilFunctions.mult2(x, (y + z))
      val dist_right = UtilFunctions.mult2(x, y) + UtilFunctions.mult2(x, z)
      dist_left shouldBe dist_right
    }
  }

  // Pyth test
  test("Pythagorean triple property: (x, y, z) => (2y, 2x, 2z)") {
    forAll(triplesGen) { case (x, y, z) =>
      val result = UtilFunctions.pythTest(x, y, z)
      val resultDoubled = UtilFunctions.pythTest(2 * y, 2 * x, 2 * z)

      // Check if the property holds
      (result, resultDoubled) should matchPattern {
        case (true, true)   =>
        case (false, false) =>
      }
    }
  }
}
