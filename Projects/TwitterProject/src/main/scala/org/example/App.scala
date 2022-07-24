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

    // tmp file for keywords
    val tmp_clean_tweets = "tmp_clean_tweets"

    // Begin operations on file.
    try {
      val tweetDF = spark.read.option("inferSchema", "true").json(inputFile)

      // CREATING A DF FOR THE NESTED DATASTRUCT "entitites.hashtags.text" RENAMED COLLUMNS TO SOLVE ISSUES WITH WRITING TO FILE
      val hashDF = tweetDF.select( "id", "entities.hashtags.text").withColumnRenamed("text", "hashtags")
        .withColumnRenamed("id", "id_2")

      // CREATED MAIN DF WITH ALL NEEDED COLUMNS, EXCEPT "entitites.hashtags.text", NEEDED TO BE FLATTENED
      val cleanDF = tweetDF.select("id","text", "user.description", "retweet_count", "reply_count", "quoted_status_id")

      // JOINING THE TWO TABLES, CREATED A JOIN CONDITION, LEFT JOIN, AND REMOVED THE DUPLICATE COLUMN FROM JOIN CONDITION
      val joinCondition = cleanDF.col("id") === hashDF.col("id_2")
      val joinedDF = cleanDF.join(hashDF, joinCondition)
      val finalDF = joinedDF.drop("id_2").withColumnRenamed("description", "user_description")
      finalDF.write.json(tmp_clean_tweets)



      // CREATING ANOTHER DATAFRAME FROM JSON FILES CREATED BY LAST QUERY
      val analysisDF = spark.read.option("inferSchema", "true").json(tmp_clean_tweets)

      // EXPLODING THE "hashtags" AND COUNTING EACH INSTANCE OF HASHTAG
      val exploded = analysisDF.select(explode(analysisDF.col("hashtags"))).withColumnRenamed("col", "hashtags")
      val explodedCount = exploded.groupBy("hashtags").count().orderBy(desc("count")).limit(20).drop("count")
      explodedCount.write.csv("top_20_tweets")
    } finally {
      // end the session because we are done.
      spark.stop()
    }

  }

}
