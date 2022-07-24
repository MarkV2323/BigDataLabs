# go into output directory, find output file, save to tweets_topic.json, remove old output directory
cd top_20_keywordsTmp.csv
find . -name '*.csv' -exec cat {} + >> ../top_20_keywords.csv
cd ..
rm -rf top_20_keywordsTmp.csv