package io.funwarioisii.sp.domain

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD

object Graphing {
  val conf = new SparkConf(false)
    .setMaster(s"local[2]")
    .setAppName("graph")
    .set("spark.logConf", "true")

  val sc = new SparkContext(conf)

  val users: RDD[(VertexId, (String, String))] =
    sc.parallelize(
      Array(
        (3L, ("rxin", "student")),
        (7L, ("jgonzal", "postdoc")),
        (5L, ("franklin", "prof")),
        (2L, ("istoica", "prof"))))

  // Create an RDD for edges
  val relationships: RDD[Edge[String]] =
    sc.parallelize(
      Array(
        Edge(3L, 7L, "collab"),
        Edge(5L, 3L, "advisor"),
        Edge(2L, 5L, "colleague"),
        Edge(5L, 7L, "pi")))

  val graph = Graph(users, relationships)

  def edges : String = graph.edges.foreach{f => f.toString}.toString
}





  
  
  
