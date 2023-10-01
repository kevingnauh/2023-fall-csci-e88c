package org.cscie88c.week3
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class StudentPropertyTest
    extends AnyFunSuite
    with Matchers
    with ScalaCheckPropertyChecks {

  val emailGen: Gen[String] = for {
    name <- Gen.alphaStr
  } yield s"$name@example.com"

  val studentGen: Gen[Student] = for {
    name <- Gen.alphaStr
    subject <- Gen.oneOf("Math", "History", "Science", "English")
    score <- Gen.choose(0, 100)
  } yield {
    val email = s"$name@example.com"
    Student(name, email, subject, score)
  }

  // complete the student list generator below if attempting bonus problem
  val studentListGen: Gen[List[Student]] = Gen.listOf(studentGen)

  test("description contains name and email") {
    // write your property test below
    forAll(studentGen) { testStudent =>
      testStudent.description should include(testStudent.name)
      testStudent.description should include(testStudent.email)
    }
  }
  // bonus
  test("averageScoreBySubject < 100 for Math") {
    forAll(studentListGen) { students =>
      whenever(students.nonEmpty) {
        val mathAverage = Student.averageScoreBySubject("Math", students)
        mathAverage should be < 100.0
      }
    }
  }
}
