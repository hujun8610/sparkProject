package com.bupt.bigdata.tfidf

import com.bupt.bigdata.tfidf.InverseDF.DocumentFrequencyAggregator
import org.apache.spark.rdd.RDD
import breeze.linalg.{DenseVector => BDV}
import org.apache.spark.mllib.linalg.{Vectors, DenseVector, SparseVector, Vector}

/**
 * Created by hujun on 2016/4/4.
 */
class InverseDF(val minDocFreq: Int) {

    def this() = this(1)

    def fit(dataSet: RDD[Vector]): InverseDFModel = {
        val idf = dataSet.treeAggregate[DocumentFrequencyAggregator](new DocumentFrequencyAggregator(minDocFreq))(
    seqOp=(df,vector)=>df.add(vector),
    combOp = (df1,df2)=>df1.merge(df2)
    ).idf()
        return new InverseDFModel(idf)
    }
}


private object InverseDF {

    class DocumentFrequencyAggregator(val minDocFreq: Int) extends Serializable {
        var m: Int = 0
        var df: BDV[Long] = _

        def add(doc: Vector): this.type = {
            if (m == 0) {
                df = BDV.zeros(doc.size)
            }

            doc match {
                case SparseVector(size, indices, values) => {
                    val nnz = indices.size
                    var k: Int = 0
                    while (k < nnz) {
                        if (values(k) > 0.0d) {
                            df(indices(k)) += 1L
                        }
                        k += 1
                    }
                }

                case DenseVector(value) => {
                    var k = 0
                    while (k < value.size) {
                        if (value(k) > 0) {
                            df(k) += 1L
                        }
                        k += 1
                    }
                }

                case other => {
                    throw new UnsupportedOperationException(s"can not support the ${other.getClass } add method")
                }
            }
            m += 1
            this
        }

        def merge(other: DocumentFrequencyAggregator): this.type = {
            if (other.m != 0) {
                m += other.m
                if (df == null) {
                    df = other.df.copy
                } else {
                    df += other.df
                }
            }
            return this
        }

        def idf(): Vector = {
            if (m == 0) {
                throw new IllegalArgumentException("there is no docs")
            }
            val len = df.length
            val inv = new Array[Double](len)
            var k = 0
            while (k < len) {
                if (df(k) >= minDocFreq) {
                    inv(k) = math.log((m + 1) / (df(k) + 1))
                }
                k += 1
            }
            Vectors.dense(inv)
        }
    }

}

class InverseDFModel(val idfVector: Vector) extends Serializable {

    def transform(dataSet: RDD[Vector]): RDD[Vector] = {
        val bcIDF = dataSet.sparkContext.broadcast(idfVector)
        dataSet.mapPartitions(iter => iter.map(v => {
            InverseDFModel.transform(v, bcIDF.value)
        }))
    }

}

private object InverseDFModel {
    def transform(tfVector: Vector, idfVector: Vector): Vector = {
        tfVector match {
            case SparseVector(size, indices, values) => {
                val nnz = indices.size
                var k = 0
                val newValues = new Array[Double](nnz)
                while (k < nnz) {
                    newValues(k) = values(k) * idfVector(indices(k))
                    k += 1
                }
                Vectors.sparse(nnz, indices, newValues)
            }

            case DenseVector(values) => {
                val size = values.size
                var k = 0
                val newValue = new Array[Double](size)
                while (k < size) {
                    newValue(k) = values(k) * idfVector(k)
                    k += 1
                }
                Vectors.dense(newValue)
            }

            case other => {
                throw new IllegalArgumentException(s"can not support the ${other.getClass } operation")
            }
        }
    }

}
