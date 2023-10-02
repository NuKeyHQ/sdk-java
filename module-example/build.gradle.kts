import xyz.voltawallet.Dependencies

tasks.jar {
  manifest.attributes["Main-Class"] = "xyz.voltawallet.example.Main"
  exclude("META-INF/BC1024KE.RSA", "META-INF/BC1024KE.SF", "META-INF/BC1024KE.DSA")
  exclude("META-INF/BC2048KE.RSA", "META-INF/BC2048KE.SF", "META-INF/BC2048KE.DSA")
  val dependencies = configurations
    .runtimeClasspath
    .get()
    .map(::zipTree) // OR .map { zipTree(it) }
  from(dependencies)
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
  implementation(project(Dependencies.Modules.SDK))
  implementation(Dependencies.Jackson.CORE)
  implementation(Dependencies.Jackson.DATABIND)
}
