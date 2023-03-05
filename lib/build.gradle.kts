plugins {
    kotlin("jvm") version "1.8.10"

    id("com.github.johnrengelman.shadow") version "7.1.2"

    `java`
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("ch.qos.logback:logback-core:1.4.5")
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")

    testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
    testImplementation("io.kotest:kotest-assertions-core:5.5.5")
    testImplementation("io.kotest:kotest-property:5.5.5")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "dev.kotlinautas"
            artifactId = "twitch4k"
            version = "2.0"

            from(components["java"])
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}