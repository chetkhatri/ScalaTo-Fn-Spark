package com.scala.toronto.talk

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}
object FutureExample extends App {

  val futureObject = for {
    _ <- Future {
      println("Hi there 1!")
    }
    _ <- Future {
      println("Hi there 2!")
    }
  } yield ()

  Await.result(futureObject, 1 seconds)

}