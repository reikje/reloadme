organization := "me.reloadme"

name := "reload-test"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.4"

Revolver.settings

seq(jrebelSettings: _*)

libraryDependencies ++= Seq(
    "io.undertow" % "undertow-core" % "1.0.15.Final",
    "com.github.jknack" % "handlebars" % "1.3.1"
)