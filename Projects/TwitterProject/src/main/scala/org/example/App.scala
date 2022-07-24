package org.example

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

// Author: Mark Alan Vincent II
// Group: Group 35, Project D, Part 2
// SID: mvinc006
// Email: mvinc006@ucr.edu
object App {

  def main(args : Array[String]) {
    println("Starting Twitter Project...")
    // Disable logs.
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    // Create new SparkConfig
    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")

    // Build a spark session
    val spark = SparkSession
      .builder()
      .appName("TwitterProject")
      .config(conf)
      .getOrCreate()

    // Get the input and output file names.
    val inputFile: String = args(0)
    val outputFile: String = args(1)

    // Stores top 20 keywords found in it's own file.
    val top20KW: String = "top_20_keywords.csv"

    // tmp file for keywords
    val tmp_clean_tweets = "tmp_clean_tweets"

    // Begin operations.
    try {
      // Load JSON data into a dataframe.
      println("Loading file: ", inputFile)
      val tweetDF = spark.read.option("inferSchema", "true").json(inputFile)

      // Create new dataframe based on relevant attributes.
      // Rename columns to avoid confusion.
      val hashDF = tweetDF
        .select( "id", "entities.hashtags.text")
        .withColumnRenamed("text", "hashtags")
        .withColumnRenamed("id", "id_2")

      // Create new dataframe based on all relevant attributes to avoid extra complexity.
      // Does not include "entities.hashtags.text".
      val cleanDF = tweetDF
        .select("id","text", "user.description", "retweet_count", "reply_count", "quoted_status_id")

      // Create new dataframe based on left joining hashDF and cleanDF based on join condition.
      val joinCondition = cleanDF.col("id") === hashDF.col("id_2")
      val joinedDF = cleanDF.join(hashDF, joinCondition)

      // Create new dataframe based on dropping duplicate column, and renaming description attribute.
      val analysisDF = joinedDF
        .drop("id_2")
        .withColumnRenamed("description", "user_description")

      // Uses exploded method on "hashtags" attribute and counts each instance of "hashtags"
      val explodedDF = analysisDF
        .select(explode(analysisDF.col("hashtags")))
        .withColumnRenamed("col", "hashtags")

      // Create new dataframe based on counting each instance of "hashtags", limiting the top 20.
      val top20DF = explodedDF
        .groupBy("hashtags")
        .count()
        .orderBy(desc("count"))
        .limit(20).drop("count")

      // Write top 20 keywords to file.
      top20DF.write.csv(top20KW)

    } finally {
      // end the session because we are done.
      spark.stop()
    }

  }

}
