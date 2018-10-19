lazy val `sp-play` = project.
  settings(SpSettings.commons).
  settings(libraryDependencies ++= Seq(
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
    "org.apache.spark" %% "spark-core" % "2.3.2",
    "org.apache.spark" %% "spark-graphx" % "2.3.2",
    "org.scalanlp" %% "breeze" % "0.13.2"
  )).
  enablePlugins(PlayScala).
  dependsOn(`sp-domain`)

lazy val `sp-domain` = project.
  settings(SpSettings.commons)

lazy val root = Project("sp-root", file("."))
