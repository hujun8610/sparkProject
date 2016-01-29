import sbt.Keys._

val sparkVersion="1.6.0"

lazy val sparkDependencies=Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-graphx" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-catalyst" % sparkVersion % "compile",
  "org.apache.hadoop" % "hadoop-client" % "2.6.0" % "compile",
  "org.scalatest" % "scalatest_2.10" % "2.1.3" % "compile",
  "org.apache.lucene" % "lucene-analyzers" % "3.4.0" % "compile",
  "junit" % "junit" % "4.9" % "compile"
)

lazy val commonSettings=Seq(
   organization := "com.huawei.bigdata",
   version := "1.0",
   scalaVersion :="2.10.4"
)

lazy val root=(project in file(".")).aggregate(dataFrameExercise,graphxExercise,machineLearning).settings(aggregate in update :=false)

lazy val dataFrameExercise = (project in file("dataFrameExercise")).settings(commonSettings: _*).settings(
  name := "dataFrameExercise",
  libraryDependencies ++=sparkDependencies
)

lazy val graphxExercise = (project in file("graphxExercise")).settings(commonSettings: _*).settings(
  name := "graphxExercise",
  libraryDependencies ++=sparkDependencies
)

lazy val machineLearning = (project in file("machineLearning")).settings(commonSettings: _*).settings(
  name := "machineLearning",
  libraryDependencies ++= sparkDependencies
)

