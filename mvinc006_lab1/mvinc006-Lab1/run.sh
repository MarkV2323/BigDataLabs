#!/usr/bin/env sh
mvn clean package
hadoop jar target/mvinc006_lab1-1.0-SNAPSHOT.jar input.txt output.txt