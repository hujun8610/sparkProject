import com.bupt.bigdata.example.GraphUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, FunSuite}

/**
 * Created by hujun on 2016/1/29.
 */
class GraphUtilsSuite extends FunSuite with BeforeAndAfterAll{

  private var sc:SparkContext =_

  override def beforeAll(): Unit ={
    val conf = new SparkConf().setMaster("local[4]").setAppName("GraphUtils")
    sc = new SparkContext(conf)
  }

  override def afterAll(): Unit ={
    sc.stop()
  }

  test("getEdgesFromFile method test"){
    val inputPath = "data/graphx/pageRank.txt"
    val edgeRdd = GraphUtils.getEdgesFromFile[Double](sc,inputPath)

    println(edgeRdd.collect().apply(0).attr)
    assert(edgeRdd.count()==9L)
  }

}
