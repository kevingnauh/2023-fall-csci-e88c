package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class EmployeeTest extends StandardTest {
  "Employee" should {
    "have a default sort order" in {
      val employees = List(
        Employee("John", 30, 50),
        Employee("Alice", 35, 75),
        Employee("Bob", 28, 40),
        Employee("Carol", 32, 60)
      )
      val sortedEmployees = Employee.defaultSortEmployees(employees)
      val expectedSortedEmployees = List(
        Employee("Alice", 35, 75),
        Employee("Bob", 28, 40),
        Employee("Carol", 32, 60),
        Employee("John", 30, 50)
      )
      sortedEmployees shouldEqual expectedSortedEmployees
    }

    "sort employees by salary" in {
      val employees = List(
        Employee("John", 30, 50),
        Employee("Alice", 35, 75),
        Employee("Bob", 28, 40),
        Employee("Carol", 32, 60)
      )
      val sortedEmployees = Employee.sortEmployeesBySalary(employees)
      val expectedSortedEmployees = List(
        Employee("Alice", 35, 75),
        Employee("Carol", 32, 60),
        Employee("John", 30, 50),
        Employee("Bob", 28, 40)
      )
      sortedEmployees shouldEqual expectedSortedEmployees
    }
  }
}
