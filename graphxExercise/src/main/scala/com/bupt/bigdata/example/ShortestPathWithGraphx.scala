package com.bupt.bigdata.example

import org.apache.spark.graphx.{TripletFields, VertexId, Graph}
import org.apache.spark.{SparkContext, SparkConf}
import scala.collection.mutable.Map

/**
 * Created by hujun on 2016/2/1.
 */
object ShortestPathWithGraphx {

  def main(args: Array[String]) {
    val host = "local"
    val filePath = "data/graphx/sssp.txt"

    val conf = new SparkConf().setMaster(host).setAppName("ShortestPathWithGraphx")
    val sc = new SparkContext(conf)

    val edgeRdd = GraphUtils.getEdgesFromFile[Double](sc,filePath)

    val graph = Graph.fromEdges(edgeRdd,0.0d)

    val vertexNum = graph.vertices.count().toInt

    val initGraph = graph.mapVertices{case(id,value)=>{
      var attr = Map[VertexId,Double]()
      attr = attr.+(id->0)
      attr
    }}

    val updateGraph = initGraph.aggregateMessages[Map[VertexId,Double]](ctx=>{
      val updateValue = ctx.srcAttr.map(kv=>{
        (kv._1,kv._2+ctx.attr)
        })
      ctx.sendToDst(updateValue)
    },(messageA,messageB)=>{
      val resultMessage = (messageA ++ messageB).map(k=>k->math.min(messageA.getOrElse(k._1,Int.MaxValue.toDouble),
         messageB.getOrElse(k._1,Int.MaxValue.toDouble)))
      resultMessage.map(_._1)
    },TripletFields.All)

    val newGraph = initGraph.outerJoinVertices(updateGraph){
      (vId,oldValue,optNewValue)=>{
        val mapA = oldValue
        val mapB = optNewValue.getOrElse(null)
        val result = (mapA ++ mapB).map(k=>k->math.min(mapA.getOrElse(k._1,Int.MaxValue.toDouble),
        mapB.getOrElse(k._1,Int.MaxValue.toDouble)))
        result.map(_._1)
      }
    }

    newGraph.vertices.collect().foreach(println)

    sc.stop()
  }

}
