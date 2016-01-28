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

    var rankGraph = graph.outerJoinVertices(graph.outDegrees){
      (vId,defaultValue,optDegree)=>{
        1.0d/optDegree.getOrElse(1)
      }
    }

    var preGraph:Graph[Double,Double] = null
    var i = 0
    while (i<iter){
      rankGraph.cache()
      val updateRank = rankGraph.aggregateMessages[Double](ctx=>{
        ctx.sendToDst(ctx.srcAttr*ctx.attr)
      },_+_,TripletFields.Src)

      preGraph = rankGraph
      rankGraph = rankGraph.outerJoinVertices(updateRank){
        (vId,oldValue,optnewValue)=>{
           optnewValue.getOrElse(0)
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
