package demo.api

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import io.circe.Json
import io.circe.parser._
import io.finch._
import io.finch.syntax._
import io.finch.circe._

object DemoApi extends TwitterServer {

  lazy val version: Endpoint[String] = get("version") {
    Ok("demo.finch version 1.0")
  }

  lazy val job: Endpoint[Json] = post("api" :: "job" :: path[String] :: stringBody) { (segment: String, body: String) =>
    val response = s"""{"path": "$segment", "body": "$body"}"""
    parse(response) match {
      case Right(jsonResponse) => Ok(jsonResponse)
      case Left(error) => InternalServerError(error)
    }
  }

  lazy val api: Service[Request, Response] = (
    job :+: version
    ).handle({
    case e: Exception => NotFound(e)
  }).toServiceAs //[Text.Plain]

  def main(): Unit = {

    val port = 8080

    val server = Http.server
      .withStatsReceiver(statsReceiver)
      .serve(s":${port}", api)

    onExit { server.close() }

    Await.ready(adminHttpServer)
  }

}
