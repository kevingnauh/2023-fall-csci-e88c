package org.cscie88c.week9

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{LazyLogging}
import org.cscie88c.config.{ConfigUtils}
import org.cscie88c.utils.{SparkUtils}
import org.apache.spark.sql.{Dataset}
import pureconfig.generic.auto._
import org.apache.spark.sql.functions.{when, sum}

// write config case class below
case class SparkDSConfig(name: String, masterUrl: String, transactionFile: String)

// run with: sbt "runMain org.cscie88c.week9.SparkDSApplication"
object SparkDSApplication {

  // application main entry point
  def main(args: Array[String]): Unit = {
    implicit val conf:SparkDSConfig = readConfig()
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)
    val transactionDS = loadData(spark)
    val totalsByCategoryDS = transactionTotalsByCategory(spark,transactionDS)
    printTransactionTotalsByCategory(totalsByCategoryDS)
    spark.stop()
  }

  def readConfig(): SparkDSConfig = {
    ConfigUtils.loadAppConfig[SparkDSConfig]("org.cscie88c.spark-ds-application")
  }
  
  def loadData(spark: SparkSession)(implicit conf: SparkDSConfig): Dataset[CustomerTransaction] = {
    import spark.implicits._
    spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(conf.transactionFile)
      .as[CustomerTransaction]
  }

  def transactionTotalsByCategory(spark: SparkSession, transactions: Dataset[CustomerTransaction]): Dataset[(String, Double)] = {
    import spark.implicits._
    transactions
      .withColumn("category", when($"transactionAmount" > 80, "High").otherwise("Standard"))
      .groupBy("category")
      .agg(sum("transactionAmount").as("total"))
      .as[(String, Double)]
  }

  def printTransactionTotalsByCategory(ds: Dataset[(String, Double)]): Unit = {
    println("Transaction totals by category:")
    ds.collect().foreach { case (category, total) =>
      println(s"Category: $category, Total: $total")
    }
  }
}
