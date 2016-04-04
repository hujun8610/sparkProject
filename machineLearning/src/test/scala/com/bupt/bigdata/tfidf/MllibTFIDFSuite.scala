package com.bupt.bigdata.tfidf

import org.apache.spark.mllib.feature.{IDF, HashingTF}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.apache.spark.mllib.linalg.{Vectors, Vector}

/**
 * Created by hujun on 2016/4/2.
 */
class MllibTFIDFSuite extends FunSuite with BeforeAndAfterAll{

  var sc:SparkContext = _
  val inputPath = "data/tfidf/"

  override def beforeAll(): Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("MllibTFIDFSuite")
    sc = new SparkContext(conf)

  }

  override def afterAll():Unit={
    sc.stop()
  }

  test("mllib hashingTF algorithm"){
    val documents:RDD[Seq[String]] = sc.textFile(inputPath).map(_.split(" ").toSeq)
    val hashingTF = new HashingTF(100)
    val tf:RDD[Vector] = hashingTF.transform(documents)
    tf.collect()

  }

  test("com.bupt.bigdata.tfidf.TF.transform method"){
    val documents:RDD[Seq[String]] = sc.textFile(inputPath).map(_.split(" ").toSeq)
    val hashingTF = new HashingTF(100)
    val mllibResult = hashingTF.transform(documents)

    val tf = new TF(100)
    val tfResult = tf.tranform(documents)

    assert(mllibResult.collect()===tfResult.collect())
  }

  test("mllib idf algorithm "){
    val seq = Array(
    Array(0,1,2,0,1,3),
    Array(0,1,1,2,3,1),
    Array(3,2,1,0,1,3)
    ).toSeq

    val tfRdd = sc.makeRDD(seq,2).map{case arr=>{
      Vectors.dense(arr.map(_.toDouble))
    }}

    val idfModel = new IDF().fit(tfRdd)
    val tfidf = idfModel.transform(tfRdd)
    tfidf.foreach(println)
  }

  test("com.bupt.bigdata.tfidf.InverseDF"){
    val seq = Array(
      Array(0,1,2,0,1,3),
      Array(0,1,1,2,3,1),
      Array(3,2,1,0,1,3)
    ).toSeq

    val tfRdd = sc.makeRDD(seq,2).map{case arr=>{
      Vectors.dense(arr.map(_.toDouble))
    }}

    val idfModel = new IDF().fit(tfRdd)
    val tfidf = idfModel.transform(tfRdd)

    val inverseDF = new InverseDF(0)
    val inverseModel = inverseDF.fit(tfRdd)
    val invertfIdf = inverseModel.transform(tfRdd)

    assert(invertfIdf.collect()===tfidf.collect())


    tfidf.foreach(println)
  }


}
