import play.api._
import play.api.mvc._
import play.api.mvc.Results.InternalServerError	
import utils.DailyJobAlert
import play.api.Logger

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("Application has started")
    DailyJobAlert.sendMailIForJobAlert
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }
  override def onError(request: RequestHeader, ex: Throwable) = {
    Logger.error("Error occurred", ex)
    InternalServerError(
      views.html.errorPage("There Is Some Error"))
  }

  override def onHandlerNotFound(request: RequestHeader): Result = {
    Logger.error("Page Not Found - " + request.path)
    InternalServerError(
      views.html.errorPage("Page Not Found - " + request.path))
  }
}
