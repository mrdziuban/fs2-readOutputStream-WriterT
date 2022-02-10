package example

import cats.data.WriterT
import cats.effect.{Blocker, IO}
import fs2.io.readOutputStream
import scala.concurrent.ExecutionContext

object Test {
  implicit val cs = IO.contextShift(ExecutionContext.global)
  val blocker = Blocker.liftExecutionContext(ExecutionContext.global)

  def main(args: Array[String]): Unit =
    println(readOutputStream[WriterT[IO, List[String], *]](blocker, 1024)(
      o => WriterT(IO((List("string 1"), o.write("a".getBytes("UTF-8")))))
    )
      .evalMap(x => WriterT(IO.pure((List("string 2"), x))))
      .compile
      .drain
      .run
      .unsafeRunSync())
}

