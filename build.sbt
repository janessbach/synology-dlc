import org.rbayer.GruntSbtPlugin._
import GruntKeys._

name := """synology-dlc"""

version := "1.0-SNAPSHOT"

lazy val gruntSbtPlugin = uri("https://github.com/rossbayer/grunt-sbt.git")

lazy val root = (project in file(".")).enablePlugins(PlayScala).dependsOn(gruntSbtPlugin)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.3.3",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.1",
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1",
  "org.apache.commons" % "commons-lang3" % "3.0",
  "commons-io" % "commons-io" % "2.4",
  "org.scala-lang.modules" % "scala-async_2.11" % "0.9.6-RC2",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

gruntSettings

gruntPath := "node_modules/grunt-cli/bin/grunt"

gruntTasks in Compile := Seq("bower-install-simple", "sync")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
