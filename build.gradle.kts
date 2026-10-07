plugins {
    id("java")
    id("io.qameta.allure") version "2.12.0" apply false
}

subprojects {
    apply(plugin = "java")

    group = "org.example"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
        }
        outputs.upToDateWhen { false }
    }
}

// ============================================
// ПРОГОН ВСЕХ ТЕСТОВ (API + UI)
// ============================================
tasks.register("testAll") {
    group = "verification"
    description = "Запускает все тесты (API + UI)"
    dependsOn(":api-tests:test")
    dependsOn(":ui-tests:test")
}

// ============================================
// ПРОГОН ВСЕХ API-ТЕСТОВ
// ============================================
tasks.register("testApi") {
    group = "verification"
    description = "Запускает все API-тесты"
    dependsOn(":api-tests:test")
}

// ============================================
// ПРОГОН ВСЕХ UI-ТЕСТОВ
// ============================================
tasks.register("testUi") {
    group = "verification"
    description = "Запускает все UI-тесты"
    dependsOn(":ui-tests:test")
}

// ============================================
// SMOKE-ПРОГОН (API + UI)
// ============================================
tasks.register("smokeTest") {
    group = "verification"
    description = "Запускает smoke-тесты API и UI"
    dependsOn(":api-tests:apiSmokeTest")
    dependsOn(":ui-tests:uiSmokeTest")
}

// ============================================
// ALLURE
// ============================================
tasks.register("allureReport") {
    group = "allure"
    description = "Формирует Allure-отчёт"
    dependsOn(":api-tests:allureReport")
    dependsOn(":ui-tests:allureReport")
}

tasks.register("allureServe") {
    group = "allure"
    description = "Открывает Allure-отчёт в браузере"
    dependsOn(":api-tests:allureServe")
}

tasks.register<Delete>("cleanAllure") {
    group = "allure"
    description = "Очищает результаты и отчёт Allure"
    delete(
        fileTree(rootDir) {
            include("**/allure-results/**")
            include("**/allure-report/**")
        }
    )
}