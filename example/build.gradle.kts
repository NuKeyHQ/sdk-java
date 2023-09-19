import xyz.volta.Dependencies

dependencies {
    implementation (project(Dependencies.Modules.SDK))
    implementation (Dependencies.Libs.jacksonCore)
    implementation (Dependencies.Libs.jacksonData)
    implementation (Dependencies.Libs.rxJava)
}