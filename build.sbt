lazy val `sp-play` = project.
  settings(SpSettings.commons).
  settings(libraryDependencies ++= Seq(
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  )).
  enablePlugins(PlayScala).
  dependsOn(`sp-domain`)

lazy val `sp-domain` = project.
  settings(SpSettings.commons)

lazy val root = Project("sp-root", file("."))
