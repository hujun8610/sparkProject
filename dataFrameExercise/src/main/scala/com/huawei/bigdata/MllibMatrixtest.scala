package com.huawei.bigdata

import org.apache.spark.mllib.linalg.DenseMatrix
import org.apache.spark.rdd.{RDD, PairRDDFunctions}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.distributed.BlockMatrix
import org.apache.spark.mllib.linalg.Matrix

/**
 * Created by hujun on 2015/11/3.
 */
object MllibMatrixtest {

  private def loadMatrixFromFile(sc: SparkContext, inputPath: String, regex: String = ",", height: Int, width: Int): BlockMatrix = {
    val dataSet = sc.textFile(inputPath).map { case line => {
      val array = line.split(regex).map(_.toInt)
      array.drop(1)
    }
    }

    val rows = dataSet.count()
    val cols = dataSet.first().length

    val blocks = dataSet.zipWithIndex().map(kv => {
      (kv._2 / height, kv._2 % height, kv._1)
    }).flatMap(kv => {
      val block = kv._3.sliding(width, width).zipWithIndex.map(f => {
        ((kv._1.toInt, f._2), (kv._2.toInt, f._1))
      })
      block
    })

    val grouped = new PairRDDFunctions[(Int, Int), (Int, Array[Int])](blocks).groupByKey().map(e => {
      val iterArray = e._2.toArray.sortBy(_._1).map(_._2)
      (e._1, iterArray)
    })

    val blockMatrix: RDD[((Int, Int), Matrix)] = grouped.map(kv => {
      val tmp_array = kv._2
      val innerRow = tmp_array.length
      val innerCol = tmp_array(0).length

      val values = Array.fill[Int](innerCol * innerRow)(0)

      var rowIndex = 0
      var colIndex = 0
      while (rowIndex < innerRow) {
        while (colIndex < innerCol) {
          values(colIndex * innerRow + rowIndex) = tmp_array(rowIndex)(colIndex)
          colIndex += 1
        }
        rowIndex += 1
      }
      val denseMatrix = new DenseMatrix(innerRow, innerCol, values.map(_.toDouble))
      (kv._1, denseMatrix)
    })
    new BlockMatrix(blockMatrix, height, width, rows, cols)
  }


  def main(args: Array[String]) {
    var host = "local"
    var inputPath = "D://testData/matrix"
    var inputPath2 = "D://testData/matrix2"
    var regex = ","
    var blockHeight = 5
    var blockWidth = 5

    if (args.length == 6) {
      host = args(0)
      inputPath = args(1)
      inputPath2 = args(2)
      regex = args(3)
      blockHeight = args(4).toInt
      blockWidth = args(5).toInt
    } else {
      println("<host:String><inputPath:String><inputPath2:String><regex:String><blockHeight:Int><blockWidth:Int>")
      System.exit(-1)
    }

    val conf = new SparkConf().setMaster(host).setAppName("MllibMatrixtest")
    val sc = new SparkContext(conf)

    val matrix = loadMatrixFromFile(sc, inputPath, regex, blockHeight, blockWidth)
    matrix.blocks.count()
    sc.stop()

  }

}
