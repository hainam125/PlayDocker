name := """warehouse"""
organization := "nam.nh"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
