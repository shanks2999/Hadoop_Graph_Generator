name := "shashank_maithani_cs441_hw2"

version := "0.1"

scalaVersion := "2.12.8"

//Compile/mainClass := Some("Main") 
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
//unmanagedBase := baseDirectory.value / "jars"
unmanagedClasspath in Runtime += baseDirectory.value / "resources"
unmanagedClasspath in Test += baseDirectory.value / "resources"
unmanagedClasspath in (Compile, runMain) += baseDirectory.value / "resources"

// https://mvnrepository.com/artifact/org.apache.commons/commons-math3
//libraryDependencies += "org.apache.commons" % "commons-math3" % "3.6.1"
//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

//libraryDependencies += "com.typesafe" % "config" % "1.3.2"
//libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
//libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

//// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common
//libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.0.0"
//
//
//// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-mapreduce-client-jobclient
//libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-jobclient" % "3.0.0" % "provided"
// https://mvnrepository.com/artifact/org.apache.commons/commons-text
libraryDependencies += "org.apache.commons" % "commons-text" % "1.6"


//// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-mapreduce-client-core
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.8.0"

//// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-core
//libraryDependencies += "org.apache.hadoop" % "hadoop-core" % "1.2.1"
//// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common
//libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.8.0"
//// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-mapreduce-client-core
//libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "2.8.0"

