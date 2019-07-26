package com.scala.toronto.talk

import cats._, cats.data._, cats.implicits._
import monix.eval.{ Coeval, Task }
import monix.execution.Scheduler
import monix.execution.Scheduler.Implicits.global
import org.apache.spark.sql.{ DataFrame, Dataset, SparkSession }
import org.apache.spark.sql.{ functions => f }
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
object MonixSpark extends App {
  def buildSession: Task[SparkSession] = Task.eval {
    SparkSession
      .builder()
      .appName("Scala-Toronto-July-2019")
      .master("local[*]")
      .config("spark.scheduler.mode", "FAIR")
      .getOrCreate()
  }

  def createDF(data: List[Int])(implicit session: SparkSession): Task[DataFrame] = Task.eval {
    import session.implicits._
    val rdd = session.sparkContext.parallelize(data)

    rdd.toDF
  }

  def computeAvg(df: DataFrame, pool: String)(implicit session: SparkSession): Task[Double] =
    Task.eval {
      session.sparkContext.setLocalProperty("spark.scheduler.pool", pool)
      val result = df.agg(f.avg("value")).head().getDouble(0)
      session.sparkContext.setLocalProperty("spark.scheduler.pool", null)

      result
    }

  def program: Task[Double] = for {
    sparkSession <- buildSession
    result <- {
      implicit val session = sparkSession
      import scala.util.Random

      val data = List.fill(100)(Random.nextInt)

      for {
        df <- createDF(data)
        avg <- computeAvg(df, "pool")
      } yield avg
    }
  } yield result

  program.runAsync

}