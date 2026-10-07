plugins {
    java
    id("io.qameta.allure")
}

dependencies {

    testImplementation(project(":common"))

    testImplementation("io.rest-assured:rest-assured:6.0.1")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.29.1")
}

allure {
    version.set("2.36.0")
}

tasks.register<Test>("apiSmokeTest") {
    group = "verification"
    useJUnitPlatform {
        includeTags("smoke")
    }
    outputs.upToDateWhen { false }
}