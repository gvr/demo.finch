package demo.hello

// the example from https://github.com/finagle/finch
object HelloWorld extends App {

  import com.twitter.finagle.Http
  import io.finch._
  import io.finch.syntax._

  val api: Endpoint[String] = get("hello") {
    Ok("Hello, World!")
  }

  Http.server.serve(":8080", api.toServiceAs[Text.Plain])

  scala.io.StdIn.readLine("Press any key to quit")

}
