

//organization := "edu.berkeley.cs"
organization := "com.github.kammoh"

name := "firrtl-interpretor"

version := "1.1-SNAPSHOT"


val chiselVersion = System.getProperty("chiselVersion", "3.1-SNAPSHOT")

scalaVersion := "2.11.7"

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
  "org.scalacheck" %% "scalacheck" % "1.13.4",
  "org.scala-lang.modules" % "scala-jline" % "2.12.1",
  "com.github.scopt" %% "scopt" % "3.5.0"
)

pomIncludeRepository := { _ => false }

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}


licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

homepage := Some(url("https://github.com/kammoh/firrtl-interpretor"))

pomExtra := (
  <scm>
    <url>git@github.com:kammoh/firrtl-interpretor.git</url>
    <connection>scm:git:git@github.com:kammoh/firrtl-interpretor.git</connection>
  </scm>
  <developers>
    <developer>
      <id>jsuereth</id>
      <name>Kamyar Mohajerani</name>
      <url>https://github.com/kammoh</url>
    </developer>
  </developers>)

