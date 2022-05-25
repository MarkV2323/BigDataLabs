# For just the needed line, App.
#spark-submit --class edu.ucr.cs.cs167.mvinc006.App target/mvinc006_lab5-1.0-SNAPSHOT.jar hdfs:///user/vincentmark/nasa_19950801.tsv 2>/dev/null

# For just the needed line, Filter.
#spark-submit --class edu.ucr.cs.cs167.mvinc006.Filter target/mvinc006_lab5-1.0-SNAPSHOT.jar hdfs:///user/vincentmark/nasa_19950801.tsv 2>/dev/null

# For 3 Inputs, Filter, code 302.
spark-submit --class edu.ucr.cs.cs167.mvinc006.Filter target/mvinc006_lab5-1.0-SNAPSHOT.jar hdfs:///nasa_19950630.22-19950728.12.tsv hdfs:///filter_output.tsv 302
# For Aggregation, lab did not go over outputting for Aggregation so I simply left it at console output..
spark-submit --class edu.ucr.cs.cs167.mvinc006.Aggregation target/mvinc006_lab5-1.0-SNAPSHOT.jar hdfs:///filter_output.tsv