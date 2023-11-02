package org.cscie88c.week7

import org.cscie88c.testutils.{FuturesTest}
import scala.concurrent.Future

class FutureUtilsTest extends FuturesTest {
  
  "Any future function" should {
    "return a future assertion" in {
      def futureAdd2(x: Int) = Future(x + 2)
      futureAdd2(5).map { x =>
        x shouldBe 7
      }
    }
  }

  "FutureFunctions" when {
    "calling creditScoreAPI" should {
      "return a credit score between 300 and 800" in {
        val testApplicantIds = List(1, 2, 3, 4, 5)
        val creditScoreFutures = testApplicantIds.map(FutureUtils.creditScoreAPI)
        val combinedFuture = Future.sequence(creditScoreFutures)

        combinedFuture.map { creditScores =>
          assert(creditScores.forall(score => score >= 300 && score <= 800))
        }
      }
    }

    "calling futureFactorial" should {
      "return a future with Success(24) for input 4" in {
        val futureResult = FutureUtils.futureFactorial(4)
        futureResult.map { result =>
          assert(result == 24)
        }
      }
    }
  }
  "FutureFunctions" when {
    "calling futurePermutations" should {
      "return the correct permutation value" in {
        val futurePermutationResult = FutureUtils.futurePermutations(5, 2)
        futurePermutationResult.map { result =>
          assert(result == 20)
        }
      }
    }

    "calling asyncAverageCreditScore" should {
      "return an average score between 300 and 800" in {
        val testApplicantIds = List(1, 2, 3, 4, 5)
        val futureAverageCreditScore = FutureUtils.asyncAverageCreditScore(testApplicantIds)
        futureAverageCreditScore.map { result =>
          assert(result >= 300.0 && result <= 800.0)
        }
      }
    }
  }
}
