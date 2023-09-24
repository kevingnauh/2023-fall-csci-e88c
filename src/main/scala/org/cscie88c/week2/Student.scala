package org.cscie88c.week2

// complete the definition of the Student case class and companion object
final case class Student(
  id: String, 
  firstName: String, 
  lastName: String, 
  email: String, 
  gender: String, 
  country: String
)

object Student {
  // Data constructor to create a Student instance from a CSV string
  def apply(csv: String): Student = {
    val fields = csv.split(",").map(_.trim)
      if (fields.length == 6) {
        new Student(fields(0), fields(1), fields(2), fields(3), fields(4), fields(5))
      } else {
        throw new IllegalArgumentException("CSV string must contain exactly 6 fields")
      }
  }
  
  val allStudents: List[Student] = List(
    "1, Emmy, Conrart, econrart0@gizmodo.com, Male, China",
    "2, Marin, Blasoni, mblasoni1@edublogs.org, Female, United States",
    "3, Jesse, Chismon, chismon2@hostgator.com, Male, China",
    "4, Delmore, Scriver, dscriver3@boston.com, Male, United States",
    "5, Jocelyn, Blaxlande, jblaxlande4@europa.eu, Female, China"
  ).map(Student(_))

  def studentNamesByCountry(country: String): List[String] = {
    Student.allStudents
      .filter(_.country.equalsIgnoreCase(country))
      .map(student => s"${student.firstName} ${student.lastName}")
  }

  def studentTotalsByCountry(country: String): Int = {
    Student.allStudents
      .count(_.country.equalsIgnoreCase(country))
  }

}