import net.minecraftforge.gradle.userdev.UserDevExtension
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.allopen") version "1.7.21"
    id("com.diffplug.spotless") version "6.11.0"
    id("net.minecraftforge.gradle") version "5.1.+"
    jacoco // code coverage reports
}

val modGroup: String by extra
val modVersion: String by extra
val modId: String by extra
val modBaseName: String by extra
val forgeVersion: String by extra
val mappingVersion: String by extra

version = modVersion

group = modGroup

println("Using Java ${JavaVersion.current()}")

configure<UserDevExtension> {
    mappings("official", "1.19.2")

    runs {
        create("client") {
            workingDirectory(project.file("run"))

            // Recommended logging data for a userdev environment
            // The markers can be added/remove as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            property("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read:
            // https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            property("forge.logging.console.level", "debug")

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            property("forge.enabledGameTestNamespaces", modId)
        }

        create("server") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")

            property("forge.logging.console.level", "debug")

            property("forge.enabledGameTestNamespaces", modId)
        }

        // This run config launches GameTestServer and runs all registered gametests, then exits.
        // By default, the server will crash when no gametests are provided.
        // The gametest system is also enabled by default for other run configs under the /test
        // command.
        create("gameTestServer") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")

            property("forge.logging.console.level", "debug")

            property("forge.enabledGameTestNamespaces", modId)
        }

        create("data") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")

            property("forge.logging.console.level", "debug")

            // Specify the modid for data generation, where to output the resulting resource, and
            // where to look for existing resources.
            // args(""--mod"", modId, ""--all"", ""--output"", file("src/generated/resources/"),
            // ""--existing"", file("src/main/resources/"))

            args(
                "--mod",
                modId,
                "--all",
                "--output",
                file("src/generated/resources/"),
                "--existing",
                file("src/main/resources/"))
        }
    }
}

sourceSets.main.configure { resources.srcDir("src/generated/resources") }

repositories {
    mavenCentral()
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
}

dependencies {
    // main
    "minecraft"("net.minecraftforge:forge:${forgeVersion}")
    // geckolib
    implementation("software.bernie.geckolib:geckolib-forge-1.19:3.1.20")
    // logger
    api("ch.qos.logback:logback-classic:1.4.5")
    api("ch.qos.logback:logback-core:1.4.5")
    api("uk.org.lidalia:sysout-over-slf4j:1.0.2")
    // core
    api("io.github.realyusufismail:RealYusufIsmail-Core:1.19-1.0.3")
    // test
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.7.21")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

// processResources
val Project.minecraft: UserDevExtension
    get() = extensions.getByName<UserDevExtension>("minecraft")

tasks.withType<Jar> {
    // this will ensure that this task is redone when the versions change.
    inputs.property("version", project.version)

    baseName = modBaseName

    // replace stuff in mcmod.info, nothing else
    filesMatching("/mcmod.info") {
        expand(
            mapOf(
                "version" to project.version,
                "mcversion" to minecraft.mappingVersion,
            ))
    }
}

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "17" }

tasks.jacocoTestReport {
    group = "Reporting"
    description = "Generate Jacoco coverage reports after running tests."
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    finalizedBy("jacocoTestCoverageVerification")
}

configurations { all { exclude(group = "org.slf4j", module = "slf4j-log4j12") } }

spotless {
    kotlin {
        // Excludes build folder since it contains generated java classes.
        targetExclude("build/**")
        ktfmt("0.39").dropboxStyle()

        licenseHeader(
            """/*
 * Copyright 2022 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ """)
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        ktfmt("0.39").dropboxStyle()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

java {
    withJavadocJar()
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
