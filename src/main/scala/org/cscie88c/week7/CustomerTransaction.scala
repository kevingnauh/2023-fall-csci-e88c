package org.cscie88c.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

final case class CustomerTransaction(
  customerId: String,
  transactionDate: String,
  transactionAmount: Double
)

object CustomerTransaction {
  def apply(csvString: String): Option[CustomerTransaction] = {
    csvString.split(",") match {
      case Array(customerId, transactionDate, transactionAmount) =>
        Try(transactionAmount.toDouble).toOption.map(amount => CustomerTransaction(customerId, transactionDate, amount))
      case _ => None
    }
  }
  def readFromCSVFile(fileName: String): List[CustomerTransaction] = {
    val lines = Source.fromFile(fileName).getLines().toList
    lines.flatMap(apply)
  }
}