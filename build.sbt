
lazy val commonSettings = Seq(
  organization := "demo",
  version := "1.0",
  scalaVersion := "2.12.4",
  crossPaths := false
)

lazy val root = (project in file(".")).
  configs(IntegrationTest).
  settings(commonSettings).
  settings(Defaults.itSettings).
  settings(
    name := "demo-finch",
    libraryDependencies ++= {
      val twitterServerVersion = "18.3.0"
      val finchVersion = "0.17.0"
      val scalaTestVersion = "3.0.5"
      val circeVersion = "0.9.1"
      Seq(
        "io.circe" %% "circe-generic" % circeVersion,
        "io.circe" %% "circe-parser" % circeVersion,
        "com.twitter" %% "twitter-server" % twitterServerVersion,
        "com.github.finagle" %% "finch-core" % finchVersion,
        "com.github.finagle" %% "finch-circe" % finchVersion,
        "org.scalatest" %% "scalatest" % scalaTestVersion % Test
      )
    }
  )
