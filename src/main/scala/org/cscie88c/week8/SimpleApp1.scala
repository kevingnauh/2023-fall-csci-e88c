package org.cscie88c.week8

import com.typesafe.scalalogging.LazyLogging
import scala.util.{Try, Success, Failure}
import scala.io.Source

// run using: sbt "runMain org.cscie88c.week8.SimpleApp1 <args>"
object SimpleApp1 extends LazyLogging {
  
  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(_.toLowerCase.contains(month.toLowerCase))

  def main(args: Array[String]): Unit = {
    val month = args(0)
    val fileName = args(1)

    lineStreamFromFile(fileName) match {
      case Some(lines) =>
        val filteredLines = monthLines(month)(lines)
        val count = filteredLines.size
        if (count > 0) {
          println(s"Transactions in $month: $count")
        } else {
          println(s"No Transactions found for $month")
        }
      case None =>
        logger.error("Failed to read file.")
    }
  }
}

