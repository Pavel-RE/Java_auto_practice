plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Из лекции 2
/*
tasks.register<Test>("smoke"){
    systemProperty("CIRCUIT", System.getProperty("circuit", "DEV"))
 useJUnitPlatform{
     includeTags("Smoke")
 }
}
*/

// ============================================
// НАСТРОЙКА ТЕСТОВ (ОБЩАЯ)
// ============================================
tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
    outputs.upToDateWhen { false }
}

// ============================================
// ЗАДАЧА ДЛЯ Lesson2Tests1
// ============================================
tasks.register<Test>("Lesson2Tests1") {
    group = "lesson2"
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
    outputs.upToDateWhen { false }
    filter {
        filter.includeTestsMatching("Lesson2Tests1")
    }
}

// ============================================
// ЗАДАЧА ДЛЯ Lesson2Tests2
// ============================================
tasks.register<Test>("Lesson2Tests2") {
    group = "lesson2"
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
    outputs.upToDateWhen { false }
    filter {
        filter.includeTestsMatching("Lesson2Tests2")
    }
}

// ============================================
// testRunIsOver - запускает только Lesson2Tests1
// ============================================
tasks.register("testRunIsOver") {
    group = "lesson2"
    description = "Запускает Lesson2Tests1 и выводит сообщение"
    dependsOn("Lesson2Tests1")  // ← зависит от testLesson1, а не от test
    doLast {
        println()
        println("========================================")
        println("Test run is over (Lesson2Tests1)")
        println("========================================")
    }
}