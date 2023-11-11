package org.cscie88c.week9

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{LazyLogging}
import org.cscie88c.config.{ConfigUtils}
import org.cscie88c.utils.{SparkUtils}
import org.apache.spark.sql.{Dataset, DataFrame, Row}
import pureconfig.generic.auto._
import org.apache.spark.sql.functions.{when, col}

// run with: sbt "runMain org.cscie88c.week9.SparkSQLApplication"
object SparkSQLApplication {

  def main(args: Array[String]): Unit = {
    implicit val conf:SparkDSConfig = readConfig()
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)
    val transactionDF = loadData(spark)
    val augmentedTransactionsDF = addCategoryColumn(transactionDF)
    augmentedTransactionsDF.createOrReplaceTempView("transactions")
    val sparkSQL = "SELECT category, SUM(transactionAmount) as total FROM transactions GROUP BY category"
    val totalsByCategoryDF = spark.sql(sparkSQL)
    printTransactionTotalsByCategory(totalsByCategoryDF)
    spark.stop()
  }

  def readConfig(): SparkDSConfig = {
    ConfigUtils.loadAppConfig[SparkDSConfig]("spark-ds-application")
  }

  def loadData(spark: SparkSession)(implicit conf: SparkDSConfig): DataFrame = {
    spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(conf.transactionFile)
  }

  def addCategoryColumn(raw: DataFrame): DataFrame = {
    raw.withColumn("category", when(col("transactionAmount") > 80, "High").otherwise("Standard"))
  }

  def printTransactionTotalsByCategory(df: DataFrame): Unit = {
    println("Transaction totals by category:")
    df.collect().foreach { row =>
      val category = row.getAs[String]("category")
      val total = row.getAs[Double]("total")
      println(s"Category: $category, Total: $total")
    }
  }
  
}
