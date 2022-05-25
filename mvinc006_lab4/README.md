# Lab 4

## Student information

* Full Name: Mark Alan Vincent II
* Email: mvinc006@ucr.edu
* NID: mvinc006
* SID: 862195494

## Answers

* (Q1) What do you think the line `job.setJarByClass(Filter.class);` does?  
According to the documentation, this line does,
Set the Jar by finding where a given class came from.
From my research, it appears that this line "sends" the cluster the required classes for Map and Reduce job to run properly.  


* (Q2) What is the effect of the line `job.setNumReduceTasks(0);`?  
I think that none of to reduce tasks exists and instead the output is just sent directly to the output path.  


* (Q3) Where does the `main` function run? (Driver node, Master node, or an executor node).  
Deferentially not on the executor nodes, I think it runs on the Driver node due to "main" methods typically being the driver of any application.
Also, the Master node's job is to ensure the keeping of metadata and block rep info, not the driver program itself.  


* (Q4) How many lines do you see in the output?  
27972  


* (Q5) How many files are produced in the output?  
Just one file, filter_output.tsv, tho it is a "folder" containing four files, a ._SUCCESS.crc
.part-m-00000.crc and _SUCCESS and part-m-000000 txt file.  


* (Q6) Explain this number based on the input file size and default block size.  
The file is 2339220 bytes large, and the default block size is 128MB. I don't believe any more output files are needed as we have not ran it with Hadoop quite yet.  


* (Q7) How many files are produced in the output?  
The same amount at Q5, and the same names. So 4, though I think we only care about 1 of them.  


* (Q8) Explain this number based on the input file size and default block size.  
So the settings are input file size and default block size are the same.  Perhaps it has something to do with having no reduce tasks? I am not entirely sure.  


* (Q9) How many files are produced in the output directory and how many lines are there in each file?  
This time I got 6 files, 3 hidden files (ones with a period in front)
and the other three being, _SUCCESS, part-r-00000 and part-r-00001. As
for the amount of lines, part-r-00000 had 4 lines (so I suppose four response codes)
while part-r-00001 has nothing in it? It doesn't look like a text file 
and the command head returned nothing in the terminal, so I guess no lines.  


* (Q10) Explain these numbers based on the number of reducers and number of response codes in the input file.  
So four total response codes, and 2 total reducers. I think the number of response codes didn't have much to do with the amount 
of output files, though the number of reducers being 2 probably has something to do with the fact that two part files are created.  


* (Q11) How many files are produced in the output directory and how many lines are there in each file?  
The same amount of files are produced in the output directory with the same naming conventions,
5 Lines (or codes) exist in 00000 and 2 Lines exist in 00001  


* (Q12) Explain these numbers based on the number of reducers and number of response codes in the input file.  
So the same amount of reducers exist, but now we have a total of 7 codes that exist. I think it has to do with balancing,
7 is an odd number not easily divisible by 2, hence it put as many as it could in file 00000, and left and easily divisible amount
in file 00001, 2 codes.  


* (Q13) How many files are produced in the output directory and how many lines are there in each file?  
2 part files are produced in the output, with 00000 having 1 line and 00001 having no lines.  


* (Q14) Explain these numbers based on the number of reducers and number of response codes in the input file.  
So filter gets rid of all codes except for one, then aggregate only runs on one code. So the reducers don't play any real
effect due to only one code being present, unlike the previous situation where both files had lines.