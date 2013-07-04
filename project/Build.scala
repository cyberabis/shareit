import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "shareit"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "org.mongodb" % "mongo-java-driver" % "2.9.3",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
