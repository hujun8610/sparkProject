import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by hujun on 2015/8/23.
 */
object HelloWorld {
  def main(args: Array[String]) {
    val conf=new SparkConf().setMaster("local").setAppName("Hello")
    val sc=new SparkContext(conf)

    val rdd=sc.parallelize(Seq(1,1,2,3,3))
    println(s"count ${rdd.count()}")

    sc.stop()

  }

}
