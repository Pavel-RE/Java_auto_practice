import org.gradle.internal.impldep.org.junit.platform.launcher.TagFilter.excludeTags
import org.gradle.internal.impldep.org.junit.platform.launcher.TagFilter.includeTags

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
    // Source: https://mvnrepository.com/artifact/io.rest-assured/rest-assured
    testImplementation("io.rest-assured:rest-assured:6.0.1")
    testImplementation("org.assertj:assertj-core:3.24.2")
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

// ============================================
// ЗАДАЧИ ДЛЯ Lesson3
// ============================================

// Запускаем задачу 1
tasks.register<Test>("Lesson3Task1") {
    group = "lesson3"
    useJUnitPlatform {
        includeTags("lesson3Task1", "smoke") //включить
        excludeTags("lesson3Task2") //исключить
    }
}
// Запускаем задачу 2. Все таски
tasks.register<Test>("Lesson3Task2") {
    group = "lesson3"
    useJUnitPlatform{
    includeTags("lesson3Task2") //включить
    excludeTags("lesson3Task1") //исключить
        }

}

// Запускаем задачу 2. Таски на выбор
tasks.register<Test>("Lesson3Task2Task") {
    group = "lesson3"
    useJUnitPlatform {
        includeTags("Task2", "Task11") //включить нужные нам таски
    }

}


// ============================================
// ЗАДАЧИ ДЛЯ Lesson4
// ============================================

// Запускаем задачу 1
tasks.register<Test>("Lesson4Task1") {
    group = "lesson4"
    useJUnitPlatform {
        includeTags("lesson4Task1") //включить
        excludeTags("lesson4Task2") //исключить
    }
}
// Запускаем задачу 2
tasks.register<Test>("Lesson4Task2") {
    group = "lesson4"
    useJUnitPlatform {
        includeTags("lesson4Task2") //включить
        excludeTags("lesson4Task1") //исключить
    }
}

// Запускаем обе задачи (только API автотесты)
tasks.register<Test>("Lesson4API") {
    group = "lesson4"
    useJUnitPlatform {
        includeTags("API") //включить API-автотесты
    }
}