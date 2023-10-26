package org.cscie88c.week6

final case class Employee(name: String, age: Int, salary: Int)

object Employee {

  implicit val employeeOrdering: Ordering[Employee] = Ordering.by(_.name)

  def defaultSortEmployees(employees: List[Employee]): List[Employee] = employees.sorted

  def sortEmployeesBySalary(employees: List[Employee]): List[Employee] =
    employees.sorted(Ordering.by((employee: Employee) => employee.salary).reverse)

  implicit val employeeAddableTypeclass: AddableTypeclass[Employee] = new AddableTypeclass[Employee] {
    override def addTwoValues(a: Employee, b: Employee): Employee = {
      Employee(s"${a.name},${b.name}", a.age + b.age, a.salary + b.salary)
    }
  }
}


