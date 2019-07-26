package com.scala.toronto.talk

import cats.effect.IO
import cats.implicits._
object CatsEffect extends App {
  val effect = for {
    _ <- IO(println("Hi there ! 1"))
    _ <- IO(println("Hi there ! 2"))
  } yield ()

  effect.unsafeRunSync
}