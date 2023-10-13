package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import FunctionUtils.CustomerTransaction
import FunctionUtils._

// run using: sbt "testOnly org.cscie88c.week5.FunctionUtilsTest"
class FunctionUtilsTest extends StandardTest {
  "FunctionUtils" when {
    // Problem 1 unit tests
    "calling colorToCode" should {
      "return the correct value for white" in {
        val limeColor = "lime"
        val result = colorToCode(limeColor)
        result shouldEqual (0, 255, 0)
      }
      "return the correct RGB values for 'white'" in {
        val whiteColor = "white"
        val result = colorToCode(whiteColor)
        result shouldEqual (255, 255, 255)
      }
      "throw an exception for an invalid color" in {
        val invalidColor = "invalid_color"
        an [IllegalArgumentException] should be thrownBy colorToCode(invalidColor)
      }
    }

    "calling fizzBuzzString" should {
      "return the correct value for when the input is divisible by 3" in {
        fizzBuzzString(3) shouldEqual "Fizz"
        fizzBuzzString(6) shouldEqual "Fizz"
        fizzBuzzString(9) shouldEqual "Fizz"
      }
      "return the correct value for when the input is divisible by 5" in {
        fizzBuzzString(5) shouldEqual "Buzz"
        fizzBuzzString(10) shouldEqual "Buzz"
        fizzBuzzString(20) shouldEqual "Buzz"
      }
      "return the correct value for when the input is divisible by both 3 and 5" in {
        fizzBuzzString(15) shouldEqual "FizzBuzz"
        fizzBuzzString(30) shouldEqual "FizzBuzz"
        fizzBuzzString(45) shouldEqual "FizzBuzz"
      }
      "return the input number as a string when not divisible by 3 or 5" in {
        fizzBuzzString(1) shouldEqual "1"
        fizzBuzzString(7) shouldEqual "7"
        fizzBuzzString(13) shouldEqual "13"
      }
    }

    "calling fizzBuzz" should {
      "return the correct value" in {
        fizzBuzz(6) shouldEqual List("1", "2", "Fizz", "4", "Buzz", "Fizz")
      }
    }
    
    "tanDegrees" should {
      "be defined for angles other than 90 and -90 degrees" in {
        val anglesToTest = List(-89, -45, 0, 45, 89)
        anglesToTest.foreach { angle =>
          tanDegrees.isDefinedAt(angle) shouldBe true
        }
      }
      "not be defined at 90 degrees" in {
          tanDegrees.isDefinedAt(90) shouldBe false
      }
      "not be defined at -90 degrees" in {
        tanDegrees.isDefinedAt(-90) shouldBe false
      }
    }


    // Problem 2 unit tests
    
    "totalHighValueTransactions" should {
      "include only high-value transactions in the sum" in {
        val transactions = List(
          CustomerTransaction("C1", "2023-10-12", 50.0),
          CustomerTransaction("C2", "2023-10-13", 150.0),
          CustomerTransaction("C3", "2023-10-14", 200.0),
          CustomerTransaction("C4", "2023-10-15", 75.0)
        )

        totalHighValueTransactions(transactions) shouldEqual 350.0
      }

      "return 0.0 if there are no high-value transactions" in {
        val transactions = List(
          CustomerTransaction("C1", "2023-10-12", 50.0),
          CustomerTransaction("C2", "2023-10-13", 60.0),
          CustomerTransaction("C3", "2023-10-14", 75.0),
          CustomerTransaction("C4", "2023-10-15", 90.0)
        )

        totalHighValueTransactions(transactions) shouldEqual 0.0
      }

      "return 0.0 if the transaction list is empty" in {
        val transactions = List.empty[CustomerTransaction]

        totalHighValueTransactions(transactions) shouldEqual 0.0
      }

    "flip2" should {
      "return the correct result after flipping the arguments of math.pow" in {
        val flippedPow = flip2(math.pow)
        val result = flippedPow(2, 3)
        result shouldEqual 9
      }

      "return the correct result after flipping the arguments of a custom binary function" in {
        val concat: (String, String) => String = (s1, s2) => s1 + s2
        val flippedConcat = flip2(concat)
        val result = flippedConcat("Hello, ", "World!")
        result shouldEqual "World!Hello, "
      }
    }

    "sampleList" should {
      "return the first 5 elements from a list of Int values" in {
        val intList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val result = sampleList(intList)
        result shouldEqual List(1, 2, 3, 4, 5)
      }

      "return the first 5 elements from a list of String values" in {
        val stringList = List("one", "two", "three", "four", "five", "six", "seven")
        val result = sampleList(stringList)
        result shouldEqual List("one", "two", "three", "four", "five")
      }

      "return the entire list if the list has less than 5 elements" in {
        val shortList = List(1, 2, 3)
        val result = sampleList(shortList)
        result shouldEqual shortList
      }
    }



    // Bonus unit tests
    }
  }
}
