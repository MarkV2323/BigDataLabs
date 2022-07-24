# remove previous tmp outputs and final output
rm -rf tmp_clean_tweets

# compile
mvn package

# run program
spark-submit --class org.example.App \
--master "local[*]" \
target/TwitterProject-1.0-SNAPSHOT.jar \
file:///mnt/c/Users/Mark/Documents/TwitterProject/tweets_1k.json \
file:///mnt/c/Users/Mark/Documents/TwitterProject/output.tsv