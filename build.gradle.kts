import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version("1.4.10")
}

version = "2.0.0"
group = "org.hashids"
description = "Kotlin implementation of Hashids https://hashids.org"

repositories {
    jcenter()
}

kotlin {
    jvm()

    sourceSets {
        val jvmTest by getting {
            dependencies {
                implementation("org.junit.jupiter:junit-jupiter:5.5.1")
                implementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
                implementation("org.junit.jupiter:junit-jupiter-engine:5.5.1")
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.useIR = true
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf(
                "Implementation-Version" to project.version,
                "Implementation-Title" to project.description
        ))
    }
}
