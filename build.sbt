import org.rbayer.GruntSbtPlugin._
import GruntKeys._

name := """synology-dlc"""

version := "1.0-SNAPSHOT"

lazy val gruntSbtPlugin = uri("https://github.com/rossbayer/grunt-sbt.git")

lazy val root = (project in file(".")).enablePlugins(PlayScala).dependsOn(gruntSbtPlugin)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "net.codingwell" %% "scala-guice" % "4.0.1",
  "org.apache.commons" % "commons-lang3" % "3.0",
  "commons-io" % "commons-io" % "2.4",
  "org.scala-lang.modules" % "scala-async_2.11" % "0.9.6-RC2",
  "com.mohiva" %% "play-html-compressor" % "0.5.0",
  "org.jsoup" % "jsoup" % "1.8.3",
  filters,
  ws,
  "org.mockito" % "mockito-core" % "1.8.5" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % "test",
  "de.leanovate.play-mockws" %% "play-mockws" % "2.5.0" % Test
)

gruntSettings

gruntPath := "node_modules/grunt-cli/bin/grunt"

gruntTasks in Compile := Seq("bower-install-simple", "sync")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
