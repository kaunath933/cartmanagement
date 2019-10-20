// This plugin is used to format scala classes.
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")

// This plugin is used to check scala styles.
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

// This plugin is used to generate scala code coverage report.
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0")

// This is a flexible Scala linter.
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.2.1")

//This plugin is used for duplicate code detection using Copy/Paste Detector (CPD) from the PMD project.
addSbtPlugin("com.github.sbt" % "sbt-cpd" % "2.0.0")

