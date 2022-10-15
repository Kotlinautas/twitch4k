import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:5.3.2")
    testImplementation("io.kotest:kotest-assertions-core:5.3.2")
    testImplementation("io.kotest:kotest-property:5.3.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}