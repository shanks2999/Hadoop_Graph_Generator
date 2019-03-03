# README #

# Cloud CS441 - Homework 2 #


**Description:**  
This is a XML parsing using Hadoop's MapReduce Framework which setup locally and tested on Hortonworks VM as well as deployed on EMR.   


**Project Structure:** 

- **Resource**: Containing the list of UIC professors which will be required to create the graph
    
- **Main**: 

    - It contains 2 Main classes (one for Hadoop application and another for GraphViz Graph generation)
    - The Hadoop code is in Java as I was not able to deploy it using scala
    - The graph vis converter code is in scala
    
**Explain/Notes:**

 - Imported XmlInputFormat class from Github Mahout and customized it accordingly for parsing xml shards. Edited the same for multiple tags
 - Only processing 2 tags (article and inproceedings) as most of other tags didn't had an author field 
 - The code for multiple tags is there (uncomment line 49,50 and comment 50,51 in 'XmlInputFormat' class)
 - The authors are filtered in tha mapper section to only UIC professors.(The implementation is done by overiding setup method of mapper)
 - The authors name after map reduce process is sorted in ascending order to avoid ambiguity between same author names in the graph.
 - Compiled jar is in the jar's folder
 - For graphViz the graphViz.dot file as well as author_graph.png is also provided for reference.
 
**Execution:**

 - The project can be executed either via IntelliJ or SBT locally and deployed on VM using the Jars Provided in Jars folder.
 - Parameters expected are the input and output folder
 - For GraphViz file the parameters is the directory in which all the files present.
 - To run locally uncomment line 106 in 'XMLByddy' class which sets the system property for hadoop.
 
 
 **Bonus:**
 
 - Youtube Link: 
 

 PS: spent more than 5 days in setting up HortonWorks VM and installing/configuring Hadoop locally
 
That's It ! :) 