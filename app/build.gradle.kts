plugins {
    id("java")
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.15.0")
    implementation("org.apache.commons:commons-collections4:4.4")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("info.picocli:picocli:4.7.6")

    implementation ("com.fasterxml.jackson.core:jackson-core:2.15.0")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.15.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.18.1")
}

tasks.test {
    useJUnitPlatform()
}