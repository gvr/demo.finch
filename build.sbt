
lazy val commonSettings = Seq(
  organization := "demo",
  version := "1.0",
  scalaVersion := "2.12.4",
  crossPaths := false
)

lazy val root = (project in file(".")).
  configs(IntegrationTest).
  settings(commonSettings: _*).
  settings(Defaults.itSettings: _*).
  settings(
    name := "demo-finch",
    libraryDependencies ++= {
      val finchVersion = "0.17.0"
      val scalaTestVersion = "3.0.5"
      Seq(
        "com.github.finagle" %% "finch-core" % finchVersion,
        "org.scalatest" %% "scalatest" % "3.0.5" % "test"
      )
    }
  )
