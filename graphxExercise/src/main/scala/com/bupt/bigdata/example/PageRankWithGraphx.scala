package com.bupt.bigdata.example

import org.apache.spark.graphx.{TripletFields, Graph}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by hujun on 2016/1/24.
 */
object PageRankWithGraphx {

  def main(args: Array[String]) {
    val host = "local"
    val filePath = "data/graphx/pageRank.txt"
    val alpha = 0.85d
    val iter = 5

    val conf = new SparkConf().setMaster(host).setAppName("PageRankWithGraphx")
    val sc = new SparkContext(conf)

    val edgeRdd = GraphUtils.getEdgesFromFile[Double](sc,filePath)

    val graph = Graph.fromEdges[Double,Double](edgeRdd,alpha)

    val vertexNum= graph.vertices.count().toInt

    var rankGraph : Graph[Double,Double]= graph.outerJoinVertices(graph.outDegrees){
      (vId,defaultValue,optDegree)=>{
        optDegree.getOrElse(0)
      }
    }.mapTriplets(e=>1.0d/e.srcAttr,TripletFields.Src)
     .mapVertices((id,attr)=>1.0d/vertexNum)

    var preGraph:Graph[Double,Double] = null
    var i = 0
    while (i<iter){
      rankGraph.cache()
//      send message
      val updateRank = rankGraph.aggregateMessages[Double](ctx=>{
        ctx.sendToDst(ctx.srcAttr*ctx.attr)
      },_+_,TripletFields.Src)

      preGraph = rankGraph
//      update vertext value
      rankGraph = rankGraph.outerJoinVertices(updateRank){
        (vId,oldValue,optnewValue:Option[Double])=>{
           val weight = optnewValue.getOrElse(0d)
           val newValue = alpha *weight + (1-alpha)/vertexNum
           newValue
        }
      }

      rankGraph.vertices.foreach(println)

      preGraph.vertices.unpersist()
      preGraph.edges.unpersist()

      i = i + 1
    }
    sc.stop()
  }

}
