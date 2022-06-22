#!/usr/bin/env sh
mvn clean package
hadoop jar target/mvinc006_lab2-1.0-SNAPSHOT.jar file:///`pwd`/AREAWATER.csv hdfs:///AREAWATER.csv
hadoop jar target/mvinc006_lab2-1.0-SNAPSHOT.jar hdfs:///AREAWATER.csv file:///`pwd`/AREAWATER_HDFS_COPY.csv
hadoop jar target/mvinc006_lab2-1.0-SNAPSHOT.jar hdfs:///AREAWATER.csv hdfs:///AREAWATER_HDFS_COPY.csv