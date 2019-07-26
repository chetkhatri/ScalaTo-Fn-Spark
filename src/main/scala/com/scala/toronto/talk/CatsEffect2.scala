package com.scala.toronto.talk

import cats.effect.IO
import cats.implicits._
object CatsEffect2 extends App {
  val printF = IO(println("Hi there!"))
  val effects = for {
    _ <- printF
    _ <- printF
  } yield ()

  effects.unsafeRunSync
}