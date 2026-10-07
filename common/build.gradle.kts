plugins {
    java
    id("java-library")
}

dependencies {
    api("org.aeonbits.owner:owner:1.0.12")
    api("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    compileOnly("org.projectlombok:lombok:1.18.48")
    annotationProcessor("org.projectlombok:lombok:1.18.48")
}