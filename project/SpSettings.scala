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
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.4" % Test,
    ),
  ))
}
