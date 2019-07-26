package com.scala.toronto.talk

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object FutureExample2 extends App {
  val printF = Future { println("Hi there!") }
  val futureObject = for {
    _ <- printF
    _ <- printF
  } yield ()

  Await.result(futureObject, 1 seconds)
}