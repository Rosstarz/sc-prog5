plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.3"
    id("com.github.node-gradle.node") version "7.0.2"
}

group = "com.ross"
version = "0.0.1-SNAPSHOT"
val springProfilesActiveTests: String by project
val springProfilesActiveDev: String by project

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    // testImplementation("io.projectreactor:reactor-test")
    implementation("org.modelmapper:modelmapper:3.2.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("org.springframework.boot:spring-boot-starter-cache")

    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // CSS
    // implementation("org.webjars:bootstrap:5.3.2")

    // Utils
    // implementation("com.google.code.gson:gson:2.10.1")
    // testImplementation("org.projectlombok:lombok:1.18.30")

    // H2
//    implementation("com.h2database:h2:2.2.224")
    // implementation("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    // JPA
    // runtimeOnly("org.postgresql:postgresql")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

    // Testing
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}

tasks.test {
    println("[INFO] Using spring profile: $springProfilesActiveTests")
    systemProperty("spring.profiles.active", springProfilesActiveTests)
    useJUnitPlatform()
}

tasks.named<Copy>("processResources") {
    dependsOn("npm_run_build")
}
