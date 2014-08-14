import com.github.jknack.handlebars.Handlebars
import io.undertow.server.{HttpServerExchange, HttpHandler}
import io.undertow.{Handlers, Undertow}

import scala.beans.BeanProperty

object Start {
  def main(args: Array[String]) {
    val server = Undertow.builder()
    .addHttpListener(6060, "0.0.0.0")
    .setHandler(
      Handlers.path()
        .addPrefixPath("/reload", new WorksHandler())
    )
    .build()
    server.start()

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() = server.stop()
    })
  }
}

case class Response(@BeanProperty var now: Long)

class WorksHandler() extends HttpHandler {
  override def handleRequest(exchange: HttpServerExchange) = {
    val handlebars = new Handlebars()
    val template = handlebars.compile("foo")
    val out = template.apply(new Response(now = System.currentTimeMillis()))
    exchange.getResponseSender.send(out)
  }
}