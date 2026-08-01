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

tasks.test {
    useJUnitPlatform()
}

tasks.register("simpleTask"){
    group = "firstStep"
    println("Simple task is running")
    doLast {
        println("Final")
    }
}

tasks.named("simpleTask"){
    dependsOn("clean")
    dependsOn("anotherSimpleTask")
}


tasks.register("anotherSimpleTask"){
    doLast {
        println("Last string")
    }
    println("Env is setted!")
}