name := "ScalaTo-Fn-Session"

version := "0.1"

scalaVersion := "2.11.12"
libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.2.1" % "provided"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.2.1" % "provided"
// https://mvnrepository.com/artifact/io.monix/monix
libraryDependencies += "io.monix" %% "monix" % "3.0.0-8084549"
// https://mvnrepository.com/artifact/org.typelevel/cats-core
libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0-M4"
