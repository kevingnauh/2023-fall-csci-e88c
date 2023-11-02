package org.cscie88c.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

object OptionUtils {
  
  def fileCharCount(fileName: String): Try[Long] = {
    Try(Source.fromFile(fileName)).flatMap { source =>
      Try(source.getLines().mkString.length.toLong)
    }
  }


  def charCountAsString(fileName: String): String = {
    fileCharCount(fileName) match {
      case Success(count) => s"number of characters: $count"
      case Failure(_) => "error opening file"
    }
  }

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] = {
    Try {
      val source = Source.fromFile(fileName)
      try {
        LazyList.from(source.getLines())
      } finally {
        source.close()
      }
    }.toOption
  }
}
