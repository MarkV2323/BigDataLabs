#!/usr/bin/env sh
mvn clean package
java -cp target/mvinc006_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.mvinc006.App 3 20 5
java -cp target/mvinc006_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.mvinc006.App 3 20 3^5
java -cp target/mvinc006_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.mvinc006.App 3 20 3v5