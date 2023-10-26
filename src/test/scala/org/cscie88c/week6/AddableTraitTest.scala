package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class AddableTraitTest extends StandardTest {

  "plus" should {

    "add two MyInt values correctly" in {
      // unit tests for MyInt
      val myInt1 = MyInt(5)
      val myInt2 = MyInt(3)
      val result = myInt1.plus(myInt2)
      result.value shouldBe 8
    }

    "add two MyBool values correctly and eval to true" in {
      // unit tests for MyBool
      val myBool1 = MyBool(true)
      val myBool2 = MyBool(false)
      val result1 = myBool1.plus(myBool2)
      result1.value shouldBe true
    }
    "add two Mybool values correctly and eval to false" in {
      val myBool5 = MyBool(false)
      val myBool6 = MyBool(false)
      val result3 = myBool5.plus(myBool6)
      result3.value shouldBe false
    }
  } 
}
