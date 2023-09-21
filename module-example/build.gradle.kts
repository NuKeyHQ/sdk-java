import xyz.volta.Dependencies

dependencies {
  implementation(project(Dependencies.Modules.SDK))
  implementation(Dependencies.Jackson.CORE)
  implementation(Dependencies.Jackson.DATABIND)
}
