package org.cscie88c.week5

import java.util.UUID
import scala.util.Random

object Streams {
  val rnd = new Random()
  def uuid: String = UUID.randomUUID.toString.replaceAll("-", "")

  case class Dog(name: String, age: Int, hasCurrentShots: Boolean)

  val mult5: LazyList[Int] = LazyList.from(0).map(_ * 5).takeWhile(_ <= 100)
  
  val randIntStream: LazyList[Int] = LazyList.continually(Random.nextInt(16))

  val dogs: LazyList[Dog] = LazyList.continually {
    val uuid = UUID.randomUUID().toString
    val age = Random.nextInt(16)
    val hasShots = Random.nextBoolean()
    Dog(s"dog-$uuid", age, hasShots)
  }

  def healthyDogs(dogs: LazyList[Dog]): LazyList[Dog] = {
    dogs.filter(_.hasCurrentShots)
  }

  def averageHealthyAge(allDogs: LazyList[Dog], sampleSize: Int): Double = {
    val healthyDogsList = allDogs.filter(_.hasCurrentShots).take(sampleSize)
    if (healthyDogsList.isEmpty) 0
    else healthyDogsList.map(_.age).sum.toDouble / healthyDogsList.length
  }


}
