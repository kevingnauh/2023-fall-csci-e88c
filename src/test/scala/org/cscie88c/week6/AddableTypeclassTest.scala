package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class AddableTypeclassTest extends StandardTest {
  
  "AddableAggregator" should {
    "sum a list of integers" in {
      val intList = List(1, 2, 3, 4, 5)
      val result = AddableAggregator.sumWithAddable(intList)
      result shouldBe 15
    }

    "sum a list of booleans" in {
      val boolList = List(true, true, false, true)
      val result = AddableAggregator.sumWithAddable(boolList)
      result shouldBe true
    }
    
    "sum a list of employees" in {
      val employeeList = List(Employee("john", 25, 80000), Employee("burns", 35, 90000))
      val result = AddableAggregator.sumWithAddable(employeeList)
      result shouldBe Employee("john,burns", 60, 170000)
    }
  }
}
