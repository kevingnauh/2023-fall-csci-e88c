package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import Streams.Dog
import Streams._

class StreamsTest extends StandardTest {
  "Streams" when {
    // Bonus problem unit tests
    val sampleDogs: LazyList[Dog] = LazyList(
      Dog("Dog-1", 5, hasCurrentShots = true),
      Dog("Dog-2", 3, hasCurrentShots = false),
      Dog("Dog-3", 7, hasCurrentShots = true),
      Dog("Dog-4", 2, hasCurrentShots = false),
      Dog("Dog-5", 4, hasCurrentShots = true),
      Dog("Dog-6", 6, hasCurrentShots = false)
    )
    "calling healthyDogs" should {
      "return a stream of healthy dogs" in {
        val allDogs: LazyList[Dog] = sampleDogs #::: LazyList.continually(sampleDogs).flatten
        val result: LazyList[Dog] = healthyDogs(allDogs).take(5)
        result.forall(_.hasCurrentShots) shouldBe true
      }
    }
    "calling averageHealthyAge" should {
      "return the correct average age of healthy dogs in the sample" in {
        val allDogs: LazyList[Dog] = sampleDogs #::: LazyList.continually(sampleDogs).flatten
        val result: Double = averageHealthyAge(allDogs, 5)
        result shouldBe 5.6
      }
    }
  }
}
