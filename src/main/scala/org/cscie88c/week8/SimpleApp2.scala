package org.cscie88c.week8

import com.typesafe.scalalogging.LazyLogging
import scala.util.{Try, Success, Failure}
import scala.io.Source
import org.cscie88c.config.{ConfigUtils}
import pureconfig.generic.auto._

// write case class here
case class SimpleApp2Config(fileName: String, month: String)

// run with: sbt "runMain org.cscie88c.week8.SimpleApp2"
object SimpleApp2 extends LazyLogging {

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(_.contains(month))

  def main(args: Array[String]): Unit = {
    val config = ConfigUtils.loadAppConfig[SimpleApp2Config]("org.cscie88c.simple-app-2")
    // println(config)
    val lines = lineStreamFromFile(config.fileName)
    lines match {
      case Some(lazyLines) =>
        val monthCount = monthLines(config.month)(lazyLines).size
        println(s"Number of transactions in ${config.month}: $monthCount")
      case None =>
        logger.error(s"Failed to read file: ${config.fileName}")
    }
  }
}
