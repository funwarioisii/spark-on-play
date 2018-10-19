import sbt._
import Keys._
import sbt.Def.SettingList

object SpSettings {
  lazy val commons = new SettingList(Seq(
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xfuture",
    ),
    dependencyOverrides ++= Seq(
      "com.fasterxml.jackson.core" % "jackson-core" % "2.8.7",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7",
      "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.7"
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.4" % Test,
      "org.apache.spark" %% "spark-core" % "2.0.2" exclude("org.slf4j", "slf4j-log4j12"),
      "org.apache.spark" %% "spark-graphx" % "2.0.2" exclude("org.slf4j", "slf4j-log4j12")
    ),
  ))
}
