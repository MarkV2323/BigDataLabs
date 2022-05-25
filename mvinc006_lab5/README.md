# Lab 5

## Student information

* Full name: Mark Alan Vincent II
* E-mail: mvinc006@ucr.edu
* UCR NetID: mvinc006
* Student ID: 862195494

## Answers

* (Q1) Do you think it will use your local cluster? Why or why not?  
After running the spark-submit command and getting the proper output, I checked the webpage
to see if any new applications were listed. None were, not even in the completed section, so I assume that it is
not currently using the pseudo-distributed mode.  


* (Q2) Does the application use the cluster that you started? How did you find out?  
After changing the code and recompiling, now the application appears to be running under the pseudo-distributed mode.
I can tell because after running the program and getting the output at the console, I refreshed the webpage and saw a
new completed Application listed.  


* (Q3) What is the Spark master printed on the standard output on IntelliJ IDEA?  
  Using Spark master 'local[*]'  


* (Q4) What is the Spark master printed on the standard output on the terminal?  
  Using Spark master 'spark://127.0.0.1:7077'  


* (Q5) For the previous command that prints the number of matching lines, list all the processed input splits.  
This is the first split  
2022-05-03 17:07:58,613 INFO rdd.HadoopRDD: Input split: hdfs://localhost:9000/user/vincentmark/nasa_19950801.tsv:0+1169610  
and the second  
2022-05-03 17:07:58,613 INFO rdd.HadoopRDD: Input split: hdfs://localhost:9000/user/vincentmark/nasa_19950801.tsv:1169610+1169610


* (Q6) For the previous command that counts the lines and prints the output, how many splits were generated?  
4 input splits were generated from the previous command.  


* (Q7) Compare this number to the one you got earlier.  
It has 2 more splits than the other, and the 2 new ones are in the opposite order of the first two (1169610+1169610 then 0+1169610)  


* (Q8) Explain why we get these numbers.  
It would appear that the file has to be opened twice, hence the doubling of the splits. 
The JavaRDD object seems to be the culprit.  


* (Q9) What can you do to the current code to ensure that the file is read only once?  
According to the hint in the lab manual, a cache() function can be called on JavaRDD objects that persist the RDD in
memory, ensuring it only gets read once.  
