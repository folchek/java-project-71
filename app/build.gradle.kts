plugins {
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
application {
    mainClass = "hexlet.code.App"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("info.picocli:picocli:4.7.6")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.6")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
}

tasks.test {
    useJUnitPlatform()
}



