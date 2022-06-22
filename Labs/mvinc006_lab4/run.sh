#!/usr/bin/env sh
# Build
mvn clean package

# Yarn large output, Filter
yarn jar target/mvinc006_lab4-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.mvinc006.Filter hdfs:///user/vincentmark/nasa_19950630.22-19950728.12.tsv hdfs:///user/vincentmark/filter_output.tsv 200

# Yarn Filter output, Agg
yarn jar target/mvinc006_lab4-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.mvinc006.Aggregation hdfs:///user/vincentmark/filter_output.tsv hdfs:///user/vincentmark/aggregation_output.tsv
