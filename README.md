# README #

# Hadoop_Graph_Generator #


**Description:**  
A map/reduce program for parallel processing of the [publically available DBLP dataset](https://dblp.uni-trier.de) that contains entries for various publications at many different venues (e.g., conferences and journals). Raw [XML-based DMBLP dataset](https://dblp.uni-trier.de/xml) is also publically available along with its schema and the documentation.  
A graph is also generated where the nodes are the faculty members of our CS department, the sizes of the nodes corresponds to the number of publication each faculty member has, and the edges correspond to joint publications between different faculty members. Each edge has a corresponding weight that shows the number of publications where two faculty members co-authored. A mapper and the reducer are created for this task and run on the DBLP dataset. The output produces a list of relations between our faculty members, i.e., <fk, (fr,..., fp)> where the faculty member, fk, published papers with the faculty members fr, ..., fp. The output of your map/reduce is an input to a graph visualization tool of [GraphViz](https://www.graphviz.org)).


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
 - The scala code converts the MapReduce output to a .dot file which is used via GraphViz to create a graph. You can also do the same by typing: **dot -Tpng -o graphViz.dot author_graph.png**
 
**Execution:**

 - The project can be executed either via IntelliJ or SBT locally and deployed on VM using the Jars Provided in Jars folder.
 - Parameters expected are the input and output folder
 - For GraphViz file the parameters is the directory in which all the files present.
 - To run locally uncomment line 106 in 'XMLByddy' class which sets the system property for hadoop.
 
 
 **The Map/Reduce framework is also deployed on AWS EMR**
 
 - Youtube Link:  https://youtu.be/cgfoCwSOcPw
 
 
