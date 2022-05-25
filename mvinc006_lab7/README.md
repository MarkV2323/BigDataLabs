# Lab 7

## Student information

* Full name: Mark Alan Vincent II
* E-mail: mvinc006@ucr.edu
* UCR NetID: mvinc006
* Student ID: 862195494

## Answers

* (Q1) What is your command?

    ```shell
    mongoimport --collection contacts --jsonArray contacts.json
    ```

* (Q2) What is the output of the above command?

    ```text
    mvinc006_lab7 % mongoimport --collection contacts --jsonArray contacts.json  
  2022-05-19T04:54:19.305-0700	connected to: mongodb://localhost/
  2022-05-19T04:54:19.337-0700	10 document(s) imported successfully. 0 document(s) failed to import.
    ```

* (Q3) What is your command?

    ```shell
	db.contacts.find().sort({Name: 1}).pretty()
    ```

* (Q4) What is your command?

    ```shell
    db.contacts.find({},{Name: 1, _id: 1}).sort({Name: -1}).pretty()
    ```

* (Q5) Is the comparison of the attribute `Name` case-sensitive?  
Yes it is, the way I tested this was inserting a new document into the collection whose name is lowercase and running the previous query to see the results,
    ```shell
    db.contacts.insert({Name: "aguirre Fox"})  
    WriteResult({ "nInserted" : 1 })  
    db.contacts.find({},{Name: 1, _id: 1}).sort({Name: -1}).pretty()
    { "_id" : ObjectId("6288502a168ed23c3bc79854"), "Name" : "aguirre Fox" }
  { "_id" : ObjectId("62882616a83fca491e9582a5"), "Name" : "Workman Holloway" }
  { "_id" : ObjectId("62882616a83fca491e9582a4"), "Name" : "Susan Graham" }
  { "_id" : ObjectId("62882616a83fca491e9582a9"), "Name" : "Sandy Oneil" }
  { "_id" : ObjectId("62882616a83fca491e9582a3"), "Name" : "Patrick Thornton" }
  { "_id" : ObjectId("62882616a83fca491e9582a6"), "Name" : "Levine Johnston" }
  { "_id" : ObjectId("62882616a83fca491e9582a8"), "Name" : "Hayes Weaver" }
  { "_id" : ObjectId("62882616a83fca491e9582a1"), "Name" : "Craft Parks" }
  { "_id" : ObjectId("62882616a83fca491e9582a0"), "Name" : "Cooke Schroeder" }
  { "_id" : ObjectId("62882616a83fca491e9582a7"), "Name" : "Aimee Mcintosh" }
  { "_id" : ObjectId("62882616a83fca491e9582a2"), "Name" : "Aguirre Fox" }
    ```

* (Q6) What is your command?

    ```shell
    db.contacts.find({},{Name: 1, _id: 0}).sort({Name: -1}).pretty()
    ```

* (Q7) Does MongoDB accept this document while the `Name` field has a different type than other records?  
Yes, it did accept the query and I saw the document in the collection via the previous query.

* (Q8) What is your command?

    ```shell
    db.contacts.insert({Name: {First: "David", Last: "Bark"}})
    ```

* (Q9) What is the output of the above command?

    ```text
    WriteResult({ "nInserted" : 1 })
    ```

* (Q10) Where do you expect the new record to be located in the sort?  
  The ASCII chart tells us that the character, { will be ranked the highest, so in a descending sort it should be at the top.

* (Q11) What is your command?

    ```shell
    db.contacts.insert({Name: ["David", "Bark"]})
    ```

* (Q12) What is the output of the above command?

    ```text
    WriteResult({ "nInserted" : 1 })
    ```

* (Q13) Where do you expect the new document to appear in the sort order. Verify your answer and explain after running the query.  
  The ASCII chart tells us that the character, [ will be ranked in the middle of all the alpha characters, so it'll probably be somewhere in the middle of the ranking. After running the sort command, it turns out that it was in the middle.

* (Q14) Where do you expect the last inserted record, `{Name: ["David", "Bark"]}` to appear this time? Does it appear in the same position relative to the other records? Explain why or why not.  
It should be in the same position as it was on the last run. After running the command, it appeared as the third in ascending, which is strange.

* (Q15) Is MongoDB able to build the index on that field with the different value types stored in the `Name` field?  
I did not get any errors returned, so it appears that it was able to make the index.

* (Q16) What is your command?

    ```shell
    db.contacts.createIndex({Name: 1})
    ```

* (Q17) What is the output of the above command?

    ```text
    {
	"numIndexesBefore" : 1,
	"numIndexesAfter" : 2,
	"createdCollectionAutomatically" : false,
	"ok" : 1
    }
    ```