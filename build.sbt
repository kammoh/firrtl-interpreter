name := "firrtl-interpreter"

organization := "edu.berkeley.cs"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("public")
)

libraryDependencies ++= Seq(
  "edu.berkeley.cs" %% "firrtl" % "0.2-SNAPSHOT",
  "org.scalatest" % "scalatest_2.11" % "2.2.6",
  "org.scalacheck" %% "scalacheck" % "1.12.5",
  "org.scala-lang.modules" % "scala-jline" % "2.12.1",
  "com.github.scopt" %% "scopt" % "3.4.0"
)

//javaOptions in run ++= Seq(
    //"-Xms2G", "-Xmx4G", "-XX:MaxPermSize=1024M", "-XX:+UseConcMarkSweepGC")

