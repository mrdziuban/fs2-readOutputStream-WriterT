package example

import cats.data.WriterT
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import fs2.io.readOutputStream

object Test {
  def main(args: Array[String]): Unit =
    println(readOutputStream[WriterT[IO, List[String], *]](1024)(
      o => WriterT(IO((List("string 1"), o.write("a".getBytes("UTF-8")))))
    )
      .evalMap(x => WriterT(IO.pure((List("string 2"), x))))
      .compile
      .drain
      .run
      .unsafeRunSync())
}
