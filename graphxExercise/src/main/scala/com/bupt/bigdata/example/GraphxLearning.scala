package com.bupt.bigdata.example

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD


/**
 * Created by hujun on 2015/10/30.
 */


object GraphxLearning {

  def main(args: Array[String]) {
    //    val userGraph:Graph[(String,String),String] = null
    val host = "local"
    val conf = new SparkConf().setMaster(host).setAppName("GraphxLearning")
    val sc = new SparkContext(conf)

    val user: RDD[(VertexId, (String, String))] = sc.parallelize(Array[(VertexId, (String, String))]((3L, ("hujun", "student")),
      (7L, ("hj", "postdoc")), (5L, ("fran", "professor")), (2L, ("istoical", "processor"))))
    val relationShip: RDD[Edge[String]] = sc.parallelize(Array[Edge[String]](Edge(3L, 7L, "Collaborator"), Edge(5L, 3L, "Advistor"),
      Edge(2L, 5L, "Colleague"), Edge(5L, 7L, "pi")))

    val defaultUser = ("John tom", "Missing")
    val graph = Graph(user, relationShip, defaultUser)

    val vertex = graph.vertices.filter { case (id, (name, pos)) => name == "hujun" }.count
    val edges = graph.edges.filter(e => e.srcId < e.dstId).count

    val fact = graph.triplets.map(triplet=>{
      triplet.srcAttr + " is the " + triplet.attr + " of" + triplet.dstAttr
    })

    println(s"vertex num is $vertex")
    println(s"edges num is $edges")
    fact.foreach(println)

    sc.stop()


  }


}
