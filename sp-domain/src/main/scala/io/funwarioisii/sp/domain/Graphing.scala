package io.funwarioisii.sp.domain


import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Graphing {
  val conf = new SparkConf(false)
    .setMaster(s"local[1]")
    .setAppName("graph")
    .set("spark.logConf", "true")

  val sc = new SparkContext(conf)

  /**
    *
    *   1 - 2 - 3
    *   |   |   |
    *   4 - 5 - 6
    *
    */

  val nodes : RDD[(VertexId, String)] =
    sc.parallelize(
      Array(
        (310L,"3F1"),
        (320L,"3F2"),
        (330L,"3F3"),
        (340L,"3F4"),
        (350L,"3F5"),
        (360L,"3F6")
      )
    )

  // 連結の一方向を表す
  var pairs =
    Array(
      (310L, 320L),
      (310L, 340L),
      (320L, 330L),
      (320L, 350L),
      (340L, 350L),
      (350L, 360l)
    )

  // 逆方向も追加する
  pairs ++= pairs.map{
    f: (Long, Long) =>
      (f._2, f._1)
  }

  // 各Edgeに対するパラメータ
  // Edge(src, dst, (key, prob))
  private val edgeData = pairs.map{
    f: (Long, Long) =>
      Edge(f._1, f._2, (s"${f._1}_to_${f._2}", 0.0f))
  }

  // Edgeの生成
  private val edge: RDD[Edge[(String, Float)]] = sc.parallelize(edgeData)

  private var graph = Graph(nodes, edge)


  def getNodes: RDD[(VertexId, String)] = nodes

  def getEdges: RDD[Edge[(String, Float)]] = edge

  def getGraph: Graph[String, (String, Float)] = graph

  /**
    * ある地点からある地点へのEdgeのパラメータを返す
    * @param src
    * @param dst
    * @return
    */
  def getEdgeData(src: VertexId, dst: VertexId): (String, Float) =
    graph
      .edges
      .filter{
        case Edge(srcId, dstId, (_, _)) =>
          srcId == src && dstId == dst
      }
      .first
      .attr

  /**
    * 初期位置から接続可能な場所を返す
    * @return
    */
  def getCallableNodes: List[Long] = nodes.collect().map{
    case (id: VertexId, _: String) =>
      id.toLong
  }.toList

  /**
    * IDから接続可能なノードを返す
    * @param id
    * @return
    */
  def getCallableNodes(id: Double): List[Long] =
    graph
      .edges
      .filter{
        case Edge(srcId, _, _) =>
          srcId == id
      }
      .map{
        case Edge(_, dstId, _) =>
          dstId.toLong
      }
      .collect()
      .toList

  def updateProb(src: Long, dst: Long, prob: Float): Unit = {
    // I couldn't realize update a param of edge, so reset edge data, edge and assign graph.
    edgeData
      .filter{case Edge(srcId, dstId, attr) => srcId==src && dstId==dst}
      .foreach(f => f.attr=(f.attr._1, prob))
    val edge: RDD[Edge[(String, Float)]] = sc.parallelize(edgeData)

    graph = Graph(nodes, edge)
  }


  /**
    * こーどのれんしゅうちょう
    * @param args
    */
  def main(args: Array[String]): Unit = {
    println(getCallableNodes)
    println(getCallableNodes(310L))
    updateProb(310L, 320L, 0.5f)
    println(graph.edges.filter(f => f.dstId == 320L && f.srcId==310L).collect().head.attr._2)
  }
}
