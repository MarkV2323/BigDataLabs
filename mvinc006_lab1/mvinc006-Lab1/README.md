# CS167 Lab 1

## Student Information:
* Name: Mark Alan Vincent II
* Email: mvinc006@ucr.edu
* NID: mvinc006
* SID: 862195494

## Answers:
(Q1) What is the name of the created directory?  
mvinc006_lab1 is the name of the created directory.

(Q2) What do you see at the console output?  
Hello World! is the output from running the .jar file.

(Q3) What do you see at the output?  
`log4j:WARN No appenders could be found for logger   (org.apache.hadoop.metrics2.lib.MutableMetricsFactory).  
log4j:WARN Please initialize the log4j system properly.  
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.  
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 0  
	at edu.ucr.cs.cs167.mvinc006.App.main(App.java:63)`  
  
(Q4) What is the output that you see at the console?  
`log4j:WARN No appenders could be found for logger (org.apache.hadoop.metrics2.lib.MutableMetricsFactory).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.`  
The code ran fine and returned an exit code of 0.

(Q5) Does it run? Why or why not?  
`Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/hadoop/conf/Configuration
	at edu.ucr.cs.cs167.mvinc006.App.main(App.java:55)`  
So I would assume it has to do with the dependencies not being loaded correctly. And not to mention that we never actually specified the input.txt or output.txt as part of the arguements, though that would be the next error after handling the classes.