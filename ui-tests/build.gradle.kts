plugins {
    java
    id("io.qameta.allure")
}

dependencies {
    testImplementation(project(":common"))

    testImplementation("com.codeborne:selenide:7.18.1")
    testImplementation("io.rest-assured:rest-assured:6.0.1")   // ← ДОБАВЬ

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
    testImplementation("io.qameta.allure:allure-selenide:2.29.1")
}

allure {
    version.set("2.36.0")
}

// Обычный запуск всех UI-тестов
tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        events("passed", "failed", "skipped")
    }
    outputs.upToDateWhen { false }
}

// Smoke UI-тестов
tasks.register<Test>("uiSmokeTest") {
    group = "verification"
    useJUnitPlatform {
        includeTags("smoke")
    }
    outputs.upToDateWhen { false }
}