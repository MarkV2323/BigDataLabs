# remove previous tmp outputs and final output
bash cleanup.sh

# compile
mvn package

# run program
spark-submit --class org.example.App \
--master "local[*]" \
target/TwitterProject-1.0-SNAPSHOT.jar \
/mnt/c/Users/Mark/Documents/bigDataPlayground/Projects/TwitterProject/tweets_1k.json \
/mnt/c/Users/Mark/Documents/bigDataPlayground/Projects/TwitterProject/output.csv