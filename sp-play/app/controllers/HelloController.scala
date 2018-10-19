package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import io.funwarioisii.sp.domain.Greeting

@Singleton
class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { request: Request[AnyContent] =>
    val message = Greeting messageFor "Graphy-API"
    Ok(message)
  }
}
