package org.cscie88c.week3

final case class Student(
    name: String,
    email: String,
    subject: String,
    score: Int
) {
  def description: String =
    s"name: ${name}, email: ${email}, subject: ${subject}, score: ${score}"
}

object Student {

  def validateEmail(student: Student): Boolean = {
    if (student.email.contains('@')) {
      true
    } else {
      false
    }
  }

  def averageScoreBySubject(
      subject: String,
      studentList: List[Student]
  ): Double = {
    // filter students by subject
    val filteredStudents = studentList.filter(_.subject == subject)

    // calculate total score for filtered students
    val totalScore = filteredStudents.map(_.score).sum

    // get total number of students
    val numberOfStudents = filteredStudents.length

    // calculate average score
    if (numberOfStudents > 0) {
      totalScore.toDouble / numberOfStudents
    } else {
      0.0
    }
  }

  def averageScoreByStudent(
      student: Student,
      studentList: List[Student]
  ): Double = {
    // filter students by student name and email
    val filteredStudents = studentList.filter(s =>
      s.name == student.name && s.email == student.email
    )

    // calculate total score for filtered students
    val totalScore = filteredStudents.map(_.score).sum

    // get total number of students
    val numberOfStudents = filteredStudents.length

    // calculate average score
    if (numberOfStudents > 0) {
      totalScore.toDouble / numberOfStudents
    } else {
      0.0
    }
  }
}
