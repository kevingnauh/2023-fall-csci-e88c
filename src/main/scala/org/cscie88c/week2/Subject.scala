package org.cscie88c.week2

// complete the definition of the Subject case class and companion object
final case class Subject(
    id: String,
    name: String,
    isSTEM: Boolean
)

object Subject {

  // Data constructor to create a Subject instance from a CSV string
  def apply(csv: String): Subject = {
    val fields = csv.split(",").map(_.trim)
    if (fields.length == 3) {
      new Subject(fields(0), fields(1), fields(2).toBoolean)
    } else {
      throw new IllegalArgumentException(
        "CSV string must contain exactly 3 fields"
      )
    }
  }

  // Define your list of all subjects here
  val allSubjects: List[Subject] = List(
    "1, Physics, true",
    "2, Chemistry, true",
    "3, Math, true",
    "4, English, false"
  ).map(Subject(_))

  // Define the method to retrieve STEM subjects here
  def stemSubjects: List[Subject] = {
    Subject.allSubjects
      .filter(_.isSTEM)

  }
}
