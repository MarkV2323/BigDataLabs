mvn package

# Part A
spark-submit --class edu.ucr.cs.cs167.mvinc006.App --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar count-all nasa_19950630.22-19950728.12.tsv 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.App --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar code-filter nasa_19950630.22-19950728.12.tsv 302 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.App --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar time-filter nasa_19950630.22-19950728.12.tsv 804955673 805590159 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.App --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar count-by-code nasa_19950630.22-19950728.12.tsv 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.App --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar sum-bytes-by-code nasa_19950630.22-19950728.12.tsv 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.App --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar avg-bytes-by-code nasa_19950630.22-19950728.12.tsv 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.App --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar top-host nasa_19950630.22-19950728.12.tsv 2>/dev/null

# Part B
spark-submit --class edu.ucr.cs.cs167.mvinc006.AppSQL --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar count-all nasa_19950630.22-19950728.12.tsv 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.AppSQL --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar code-filter nasa_19950630.22-19950728.12.tsv 302 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.AppSQL --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar time-filter nasa_19950630.22-19950728.12.tsv 804955673 805590159 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.AppSQL --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar count-by-code nasa_19950630.22-19950728.12.tsv 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.AppSQL --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar sum-bytes-by-code nasa_19950630.22-19950728.12.tsv 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.AppSQL --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar avg-bytes-by-code nasa_19950630.22-19950728.12.tsv 2>/dev/null
spark-submit --class edu.ucr.cs.cs167.mvinc006.AppSQL --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar top-host nasa_19950630.22-19950728.12.tsv 2>/dev/null
# spark-submit --class edu.ucr.cs.cs167.mvinc006.AppSQL --master "local[*]" target/mvinc006_lab6-1.0-SNAPSHOT.jar comparison nasa_19950630.22-19950728.12.tsv 805383872 2>/dev/null