package com.bupt.bigdata.example

import org.apache.spark.SparkContext
import org.apache.spark.graphx.Edge
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag

/**
 * Created by hujun on 2016/1/24.
 */
object GraphUtils {

  def getEdgesFromFile[T:ClassTag](sc:SparkContext,filePath:String):RDD[Edge[T]]={
    val tClass = scala.reflect.classTag[T]
    val manifest =  scala.reflect.Manifest

    val edgeRdd = sc.textFile(filePath).map(line=>{
      val array = line.split(",")
      val srcId = array(0).toLong
      val desId = array(1).toLong
      val weight = array(2)
      new Edge(srcId,desId,weight)
    })

    val resultRdd = tClass match {
      case manifest.Int => edgeRdd.map(e=>new Edge(e.srcId,e.dstId,e.attr.toInt.asInstanceOf[T]))
      case manifest.Long => edgeRdd.map(e=>new Edge(e.srcId,e.dstId,e.attr.toLong.asInstanceOf[T]))
      case manifest.Double => edgeRdd.map(e=>new Edge(e.srcId,e.dstId,e.attr.toDouble.asInstanceOf[T]))
      case _ => throw new Exception("Not support type=" + tClass.toString())
    }
    resultRdd
  }


}
