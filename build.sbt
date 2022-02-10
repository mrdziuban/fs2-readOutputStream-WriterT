def proj(fs2Version: String) = {
  val majorVersion = fs2Version.split('.').head
  Project(s"fs2-$majorVersion", file(s"fs2-$majorVersion")).settings(
    name := s"fs2-$majorVersion-readOutputStream-WriterT",
    organization := "com.example",
    scalaVersion := "2.13.8",
    version      := "0.1.0-SNAPSHOT",
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full),
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % fs2Version,
      "co.fs2" %% "fs2-io" % fs2Version
    ),
    run / fork := true
  )
}

lazy val fs2_2 = proj("2.5.10")
lazy val fs2_3 = proj("3.2.4")
