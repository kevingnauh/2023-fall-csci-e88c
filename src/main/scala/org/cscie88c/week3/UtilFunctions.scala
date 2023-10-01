package org.cscie88c.week3

object UtilFunctions {

  def mult2(x: Int, y: Int): Int = x * y

  def pythTest(x: Int, y: Int, z: Int): Boolean = {
    val a = mult2(x, x)
    val b = mult2(y, y)
    val c = mult2(z, z)

    if (a + b == c) {
      true
    } else {
      false
    }

  }

  val pythTriplesUpto100: List[(Int, Int, Int)] = for {
    a1 <- (1 to 100).toList
    b1 <- (1 to 100).toList
    c1 <- (1 to 100).toList
    if pythTest(a1, b1, c1)
  } yield (a1, b1, c1)

}
