# CS167 Lab 2

## Student Information:
* Name: Mark Alan Vincent II
* Email: mvinc006@ucr.edu
* NID: mvinc006
* SID: 862195494

## Answers:
(Q1) Verify the file size and record the running time.   
2271210910 bytes in 249.527 seconds

(Q2) Record the running time of the copy command.   
2.450 seconds

(Q3) How do the two numbers compare?   
The CP command is much faster compared to the program I wrote. Perhaps it has to do with how hadoop works compared to the operating system, or how my specific program works in terms of reading and writing with each byte.

(Q4) Does the program run after you change the default file system to HDFS? What is the error message, if any, that you get?  
I am told that the file does not exist. I am guessing this is due to HDFS being the deault file system.

(Q5) Use your program to test the following cases and record the running time for each case. (In the table)    
All of these test use the AREAWATER.csv file.  
Test 1: Copy a file from local file system to HDFS  
Test 2: Copy a file from HDFS to local file system.  
Test 3: Copy a file from HDFS to HDFS.

| TEST | TIME               |
|------|--------------------|
| 1    | 276.320748 seconds |
| 2    | 423.540818 seconds |
| 3    | 452.598956 seconds |
