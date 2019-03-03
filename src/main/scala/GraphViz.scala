import java.io._
import java.util
import java.util.{ArrayList, HashMap, List, Map}
import scala.io.Source

/*
This Class generates the .dot file which is required by grapg Viz to generate the graph.
Just provide the path to the folder in which all the reduced files are stored.
make sure that there are no files/folder apart from the required ones.
 */

object GraphViz {
  val authorPublicationMap = new util.HashMap[String, String]
  val nodeSection = new StringBuffer
  val edgeSection = new StringBuffer

  def main(args: Array[String]): Unit = { //        StringBuffer nodeSection= new StringBuffer();
    //        StringBuffer edgeSection= new StringBuffer();
    val data = new util.ArrayList[String]
    val edgeList = new util.ArrayList[String]
    val nodeList = new util.ArrayList[String]
    try {
      val dir = new File(args(0))
      val filesList = dir.listFiles.filter(_.isFile).toList
//      val br = new BufferedReader(new FileReader(args(0)))
//      var line: String = Option.empty[String].orNull
      for(filePath <- filesList) {
//        "zzz/part-r-00000"
        val bufferedSource = Source.fromFile(filePath.toString)
        for (line <- bufferedSource.getLines) {
          val authData = line.split("(\\t|,)")
          if (authData.length == 2) {
            nodeList.add(line)
            authorPublicationMap.put(authData(0), authData(1))
          }
          else edgeList.add(line)
        }
      }
      data.addAll(nodeList)
      data.addAll(edgeList)
      import scala.collection.JavaConversions._
      for (recline <- data) {
        val nodes = new StringBuffer
        val edges = new StringBuffer
        val record = recline.split("(\\t|,)")
        if (record.length == 2) {
          nodes.append("node [shape = circle fixedsize=true fontsize=\"60\" width=")
          nodes.append(getWidth(record(1).toInt, 2))
          nodes.append("]; \"")
          val pname = getShortName(record(0))
          nodes.append(pname)
          nodes.append(record(1) + "\"\n")
          //System.out.println(sbnodes.toString());
          nodeSection.append(nodes)
        }
        if (record.length == 3) {
          edges.append("\"")
          val name = getShortName(record(0))
          edges.append(name)
          edges.append(authorPublicationMap.get(record(0)))
          edges.append("\" -> \"")
          val toprofname = getShortName(record(1))
          edges.append(toprofname)
          edges.append(authorPublicationMap.get(record(1)))
          edges.append("\"[ fontsize=\"50\" label =\"")
          edges.append(record(2))
          edges.append("\" ][dir=\"both\"];")
          edgeSection.append(edges + "\n")
        }
      }
      writeToDotFile()
    } catch {
      case e: FileNotFoundException =>
        e.printStackTrace()
      case e: IOException =>
        e.printStackTrace()
    }
  }

  def getWidth(x: Int, base: Int): Double = {
    var res = (Math.log(x) / Math.log(base)).toDouble
    res = res + 2
    res * Math.pow(10, 2).round / Math.pow(10, 2)
  }

  def getShortName(name: String): String = {
    val arrNames = name.split(" ")
    val shortName = new StringBuffer
    var i = 0
    while ( {
      i < arrNames.length
    }) {
      if (i != 1 || (i == 1 && arrNames(i).length > 2)) {
        shortName.append(arrNames(i))
        shortName.append("\\n")
      }

      {
        i += 1; i - 1
      }
    }
    shortName.toString
  }

  def writeToDotFile(): Unit = {
    var writer: BufferedWriter = Option.empty[BufferedWriter].orNull
    try {
      writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("graphViz.dot"), "utf-8"))
      writer.write("digraph graphVis_authors{\n")
      writer.write(nodeSection.toString)
      writer.write(edgeSection.toString)
      writer.write("}")
    } catch {
      case ex: IOException =>

      // Report
    } finally try
      writer.close()
    catch {
      case ex: Exception =>

      /*ignore*/
    }
  }
}



