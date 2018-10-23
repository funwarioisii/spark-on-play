package controllers

import io.funwarioisii.sp.domain.Graphing
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class GraphController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  private val graphing = Graphing

  def index = Action { _: Request[AnyContent] =>
    val callable = graphing.getCallableNodes.toString
    Ok(callable)
  }

  def getCallableNodes = Action{request =>
    val queryString = request.queryString
    if (queryString.contains("src") && queryString.contains("dst")) {
      val srcId = queryString.get("src").head.head.toLong
      val callable = graphing.getCallableNodes(srcId).toString
      Ok(callable)
    } else {
      Ok("No message to you")
    }
  }

  def getEdgeData = Action {
    request =>
      val queryString = request.queryString
      if (queryString.contains("src") && queryString.contains("dst")) {
        val srcId = queryString.get("src").head.head.toLong
        val dstId = queryString.get("dst").head.head.toLong
        val edgeData = graphing.getEdgeData(srcId, dstId)
        Ok(s"${edgeData._1} ,${edgeData._2}")
      } else {
        Ok("No message to you")
      }
  }

  def updateProb = Action {
    request =>
      val queryString = request.queryString
      if (queryString.contains("src")
        && queryString.contains("dst")
        && queryString.contains("prob")) {

        val srcId = queryString.get("src").head.head.toLong
        val dstId = queryString.get("dst").head.head.toLong
        val prob = queryString.get("prob").head.head.toFloat

        graphing.updateProb(srcId, dstId, prob)
        Ok("updated")
      } else {
        Ok("No message to you")
      }
  }
} 
  
  
  
