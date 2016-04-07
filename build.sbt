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
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

gruntSettings

gruntPath := "node_modules/grunt-cli/bin/grunt"

gruntTasks in Compile := Seq("bower-install-simple")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
