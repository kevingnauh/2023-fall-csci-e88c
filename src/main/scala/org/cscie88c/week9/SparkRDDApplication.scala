package org.cscie88c.week9

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{LazyLogging}
import org.cscie88c.config.{ConfigUtils}
import org.cscie88c.utils.{SparkUtils}
import org.apache.spark.rdd.{RDD}
import pureconfig.generic.auto._

// write case class below
import org.cscie88c.week9.CustomerTransaction

case class SparkRDDConfig(name: String, masterUrl: String, transactionFile: String)

// run with: sbt "runMain org.cscie88c.week9.SparkRDDApplication"
object SparkRDDApplication extends LazyLogging {

  // application entry point
  def main(args: Array[String]): Unit = {
    implicit val conf:SparkRDDConfig = readConfig()                         // 1. read configuration
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)          // 2. initialize spark session
    val rddLines = loadData(spark)                                          // 3.load data
    val rddTransactions = lineToTransactions(rddLines)                      // 4. convert lines to transaction objects
    val yearlyTransactionsRDD = transactionsAmountsByYear(rddTransactions)  // 5. transform data
    printTransactionsAmountsByYear(yearlyTransactionsRDD)                   // 6. print results
    spark.stop()                                                            // 7. stop spark cluster
  }

  def readConfig(): SparkRDDConfig = {
    ConfigUtils.loadAppConfig[SparkRDDConfig]("org.cscie88c.spark-rdd-application")
  }

  def loadData(spark: SparkSession)(implicit conf: SparkRDDConfig): RDD[String] = {
    spark.sparkContext.textFile(conf.transactionFile)
  }

  def lineToTransactions(lines: RDD[String]): RDD[CustomerTransaction] = {
    lines
      .flatMap(line => CustomerTransaction.apply(line))
    }

  def transactionsAmountsByYear(transactions: RDD[CustomerTransaction]): RDD[(String, Double)] = {
    transactions
      .map(transaction => (transaction.transactionYear, transaction.transactionAmount))
      .reduceByKey(_ + _)
  }

  def printTransactionsAmountsByYear(transactions: RDD[(String, Double)]): Unit = {
    println("Transactions amounts by year:")
    transactions.collect().foreach { case (year, sum) =>
      println(s"Year: $year, Sum Transactions: $sum")
    }
  }
}

