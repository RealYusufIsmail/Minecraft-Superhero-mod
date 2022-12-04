import net.minecraftforge.gradle.userdev.UserDevExtension
import org.gradle.jvm.tasks.Jar

plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.allopen") version "1.7.21"
    id("com.diffplug.spotless") version "6.11.0"
    id("net.minecraftforge.gradle") version "5.1.+"
    id("org.parchmentmc.librarian.forgegradle") version "1.+"
    id("eclipse")
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
    mappings("parchment", "2022.11.27-1.19.2")

    runs {
        create("client") {
            workingDirectory(project.file("run"))
            accessTransformer(project.file("src/main/resources/META-INF/accesstransformer.cfg"))

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

            mods { create(modId) { source(sourceSets.main.get()) } }
        }

        create("server") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")

            property("forge.logging.console.level", "debug")

            property("forge.enabledGameTestNamespaces", modId)

            mods { create(modId) { source(sourceSets.main.get()) } }
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

            mods { create(modId) { source(sourceSets.main.get()) } }
        }

        create("data") {
            workingDirectory(project.file("run"))

            args(
                "--mod",
                modId,
                "--all",
                "--output",
                file("src/generated/resources/"),
                "--existing",
                file("src/main/resources/"))

            mods { create(modId) { source(sourceSets.main.get()) } }
        }
    }
}

sourceSets { main { resources.srcDir("src/generated/resources") } }

repositories {
    mavenCentral()
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
}

dependencies {
    // main
    "minecraft"("net.minecraftforge:forge:${forgeVersion}")
    // geckolib
    "api"(fg.deobf("software.bernie.geckolib:geckolib-forge-1.19:3.1.38"))
    // logger
    "api"("ch.qos.logback:logback-classic:1.4.5")
    "api"("ch.qos.logback:logback-core:1.4.5")
    "api"("uk.org.lidalia:sysout-over-slf4j:1.0.2")
    // core
    "api"("io.github.realyusufismail:RealYusufIsmail-Core:1.19-1.0.3")
    // test
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.7.21")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

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

tasks.withType<Jar> {
    archiveFileName.set("SuperHeroMod-${modVersion}-Forge-${forgeVersion}.jar")
    manifest {
        from(file("src/main/resources/META-INF/MANIFEST.MF"))
        attributes["Implementation-Version"] = project.version
    }
    exclude("net")
    // filesMatching("META-INF/mods.toml") { expand(project.properties) }
    // filesMatching("mcmod.info") { expand(project.properties) }
}

kotlin {
    target.compilations.configureEach {
        kotlinOptions.jvmTarget = "17"
        kotlinOptions.freeCompilerArgs += listOf("-Xno-param-assertions", "-Xno-call-assertions")
    }
}
