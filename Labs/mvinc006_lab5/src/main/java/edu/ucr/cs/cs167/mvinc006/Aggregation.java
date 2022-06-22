package edu.ucr.cs.cs167.mvinc006;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Map;

public class Aggregation {
    public static void main(String[] args) {
        final String inputPath = args[0];
        final String outputPath = args[1];
        SparkConf conf = new SparkConf();
        if (!conf.contains("spark.master"))
            conf.setMaster("local[*]");
        System.out.printf("Using Spark master '%s'\n", conf.get("spark.master"));
        conf.setAppName("CS167-Lab5");
        try (JavaSparkContext spark = new JavaSparkContext(conf)) {
            JavaRDD<String> logFile = spark.textFile(inputPath);
            JavaPairRDD<String, Integer> codes = logFile.mapToPair(
                    (PairFunction<String, String, Integer>) s -> new Tuple2<String, Integer>(s.split("\t")[5], 1));
            // To do 1: transform via `mapToPair`, return `Tuple2`
            Map<String, Long> counts = codes.countByKey(); // To do 2: `countByKey`
            for (Map.Entry<String, Long> entry : counts.entrySet()) {
                System.out.printf("Code '%s' : number of entries %d\n", entry.getKey(), entry.getValue());
            }
            codes.saveAsTextFile(outputPath);
        }
    }
}
