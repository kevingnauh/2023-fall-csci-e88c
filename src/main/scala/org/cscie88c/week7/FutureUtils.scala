package org.cscie88c.week7

import scala.concurrent.{Future}
import scala.util.{Try, Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random
// import scala.collection.parallel.CollectionConverters._

object FutureUtils {
  
  val rnd = new Random()

  def creditScoreAPI(applicantId: Int): Future[Int] = Future {
    val creditScore = rnd.nextInt(501) + 300 
    creditScore
  }

  def printCreditScore(applicantId: Int): Unit = {
    val futureCreditScore = creditScoreAPI(applicantId)
    futureCreditScore.onComplete {
      case Success(creditScore) => println(s"The credit score for $applicantId is: $creditScore")
      case Failure(_) => println(s"Cannot get credit score for $applicantId at this time")
    }
  }

  def futureFactorial(n: Int): Future[Int] = {
    if (n < 0) {
      Future.failed(new IllegalArgumentException("Input should be a non-negative integer"))
    } else {
      Future {
        (1 to n).product
      }
    }
  }

  def passedCreditCheck(applicantId: Int): Future[Boolean] = {
    creditScoreAPI(applicantId).map(creditScore => creditScore > 600)
  }

  def futurePermutations(n: Int, r: Int): Future[Int] = {
    for {
      factorialN <- futureFactorial(n)
      factorialNMinusR <- futureFactorial(n - r)
    } yield factorialN / factorialNMinusR
  }

  def asyncAverageCreditScore(idList: List[Int]): Future[Double] = {
    val futureCreditScores = idList.map(creditScoreAPI)
    val combinedFuture = Future.sequence(futureCreditScores)

    combinedFuture.map { creditScores =>
      if (creditScores.isEmpty) 0
      else creditScores.sum.toDouble / creditScores.length
    }
  }

  def slowMultiplication(x: Long, y: Long): Long = {
    Thread.sleep(1000) // pause for 1 second
    x * y
  }

  def concurrentFactorial(n: Long): Long = ???

  def sequentialFactorial(n: Long): Long = {
    if (n < 0) {
      throw new IllegalArgumentException("Input should be a non-negative integer")
    } else {
      (1L to n).foldLeft(1L)((acc, i) => slowMultiplication(acc, i))
    }
  }

}
