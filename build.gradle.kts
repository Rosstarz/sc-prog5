plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.ross"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

// tasks.bootJar {
// //     // Use Spring Boot DevTool only when we run Gradle bootRun task
// //     classpath = sourceSets.main.runtimeClasspath + configurations.developmentOnly
// // 	sourceResources = sourceSets.main
//     excludeDevtools = false
// }

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

    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // CSS
    implementation("org.webjars:bootstrap:5.3.2")

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
}

tasks.withType<Test> {
    useJUnitPlatform()
}

