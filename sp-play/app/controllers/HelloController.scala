package controllers

import io.funwarioisii.sp.domain.Greeting
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

@Singleton
class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { request: Request[AnyContent] =>
    val message = Greeting messageFor "Graphy-API"
    Ok(message)
  }

  def getMethod = Action { request: Request[AnyContent] =>
    val queryString = request.queryString
    if (queryString.contains("src") && queryString.contains("dst")) {
      val srcId = queryString.get("src").head.head
      val dstId = queryString.get("dst").head.head

      Ok(s"srcId : $srcId, dstId: $dstId")
    } else {
      Ok("No message to you")
    }
  }
}