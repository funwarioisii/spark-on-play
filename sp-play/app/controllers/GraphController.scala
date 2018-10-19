package controllers

import io.funwarioisii.sp.domain.Graphing
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.concurrent.Future

@Singleton
class GraphController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action { request: Request[AnyContent] =>
    val graphing = {Graphing.edges}
    Ok(graphing)
  }
} 
  
  
  
