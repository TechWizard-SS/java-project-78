plugins {
    application
    checkstyle
    id("org.sonarqube") version "7.0.1.6134"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.checkstyleMain {
    dependsOn(tasks.compileJava)
}

tasks.checkstyleTest {
    dependsOn(tasks.compileTestJava)
}

checkstyle {
    toolVersion = "10.12.1"
    configFile = file("Config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
    isShowViolations = true
}

sonar {
    properties {
        property("sonar.projectKey", "TechWizard-SS_java-project-78")
        property("sonar.organization", "techwizard-ss")
    }
}