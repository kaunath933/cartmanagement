name := "cartmanagement"
version := "0.1"
scalaVersion := "2.12.8"
mainClass in Compile := Some("CartMgmtImpl")
libraryDependencies ++= {
  val slickVersion           = "3.3.2"
  val mySqlVersion           = "8.0.17"
  Seq(
    "com.typesafe.slick" %% "slick" % slickVersion,
    "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
    "mysql" % "mysql-connector-java" % mySqlVersion,
    "com.typesafe.akka" %% "akka-http"   % "10.1.10",
    "com.typesafe.akka" %% "akka-stream" % "2.5.23",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.scalatest" %% "scalatest" % "3.0.8" % "test",
    "com.h2database" % "h2" % "1.4.192",
    "com.typesafe.akka" %% "akka-http-testkit" % "10.1.10",
    "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.23",
    "com.pauldijou" %% "jwt-core" % "4.1.0"
    //    "com.typesafe.akka" %% "akka-testkit" % "2.5.21" % Test
  )
}




//import Dependencies._
//import CommonSettings._
//import scoverage.ScoverageKeys
//
//name := "cartmanagement"
//
//version := "1.0"
//
//scalaVersion := scala
//
//lazy val root = (
//  project.in(file(".")).settings(
//    run := {
//      (run in cartmgmt in Compile).evaluated
//    })
//    aggregate (cartmgmt)
//  )
//lazy val cartmgmt = (
//  baseProject("cartmgmt")
//    settings(libraryDependencies ++= compileDeps(cartDependencies) ++ testDeps(scalaTest, mock, slickTest1, slickTest2, slickTest3, slickTest4),
//    ScoverageKeys.coverageMinimum := 90,
//    ScoverageKeys.coverageFailOnMinimum := true,
//    ScoverageKeys.coverageExcludedPackages := ""
//  ))
//
//def compileDeps(deps: Seq[ModuleID]): Seq[ModuleID] = deps map (_ % "compile")
//
//def testDeps(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
