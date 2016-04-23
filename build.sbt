import play.twirl.sbt.Import.TwirlKeys._

name := """synology-dlc"""

version := "1.0-SNAPSHOT"

lazy val gruntSbtPlugin = uri("https://github.com/rossbayer/grunt-sbt.git")

lazy val root = (project in file(".")).enablePlugins(PlayScala).dependsOn(gruntSbtPlugin)

scalaVersion := "2.11.7"

templateImports ++= Seq(
  "modules.core.TemplateMixin._",
  "modules.core.controllers.RequestContext"
)

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

watchSources := (watchSources.value --- baseDirectory.value / "public" ** "*").get

def Grunt(base: File) = {
  import play.sbt.PlayRunHook
  import sbt._
  object Grunt {
    def apply(base: File): PlayRunHook = {
      object GruntProcess extends PlayRunHook {
        override def beforeStarted(): Unit = {
          Process("grunt dist", base).run
        }
      }
      GruntProcess
    }
  }
  Grunt(base)
}

PlayKeys.playRunHooks += Grunt(baseDirectory.value)

sources in (Compile, doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

