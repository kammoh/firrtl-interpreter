name := "firrtl-interpreter"

//organization := "edu.berkeley.cs"
organization := "com.github.kammoh"

version := "1.1-SNAPSHOT"

resolvers += "jitpack" at "https://jitpack.io"

val chiselVersion = System.getProperty("chiselVersion", "3.1-SNAPSHOT")

scalaVersion := "2.11.8"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("public")
)

// Provide a managed dependency on X if -DXVersion="" is supplied on the command line.
val defaultVersions = Map("firrtl" -> "1.1-SNAPSHOT")

libraryDependencies ++= (Seq("firrtl").map {
  dep: String => "com.github.kammoh" %% dep % sys.props.getOrElse(dep + "Version", defaultVersions(dep)) })

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.0",
  "org.scalacheck" %% "scalacheck" % "1.13.3",
  "org.scala-lang.modules" % "scala-jline" % "2.12.1",
  "com.github.scopt" %% "scopt" % "3.5.0"
)

//javaOptions in run ++= Seq(
    //"-Xms2G", "-Xmx4G", "-XX:MaxPermSize=1024M", "-XX:+UseConcMarkSweepGC")

