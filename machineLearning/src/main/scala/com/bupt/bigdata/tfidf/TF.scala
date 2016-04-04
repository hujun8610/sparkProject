package com.bupt.bigdata.tfidf

import org.apache.spark.mllib.linalg
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD

/**
 * Created by hujun on 2016/4/3.
 */
class TF(val numFeatures:Int) extends Serializable{

  def indexOf(term:Any):Int={
    val hashcode = term.hashCode()%numFeatures
    return (hashcode+numFeatures)%numFeatures
  }

  def transform(documents:Iterable[_]):linalg.Vector={
    val termFrequencies = scala.collection.mutable.HashMap.empty[Int,Double]
    documents.foreach{case term=>{
      val index = indexOf(term)
      termFrequencies.put(index,termFrequencies.getOrElse(index,0d)+1.0d)
    }}
    Vectors.sparse(numFeatures,termFrequencies.toSeq)
  }

  /**
   * @param dataset
   * scala协变与逆变的使用以及泛型通配符的使用
   * */
  def tranform[D <: Iterable[_]](dataset: RDD[D]):RDD[linalg.Vector]={
    return dataset.map(this.transform)
  }

}
