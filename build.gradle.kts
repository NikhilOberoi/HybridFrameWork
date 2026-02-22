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

    // Cucumber JVM (latest compatible versions)
    testImplementation("io.cucumber:cucumber-java:7.15.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.15.0")
    testImplementation("io.cucumber:cucumber-picocontainer:7.15.0")

    // JUnit 5 Platform
    testImplementation("org.junit.platform:junit-platform-suite:1.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")

    // Selenium 4
    testImplementation("org.seleniumhq.selenium:selenium-java:4.17.0")

    // WebDriverManager for automatic browser driver management
    testImplementation("io.github.bonigarcia:webdrivermanager:5.6.3")

    // Use "com.github.User:Repo:Tag" format
    // Example: implementation("com.github.google:guava:30.0-jre")
    implementation("com.github.User:Repo:Tag")
}

tasks.test {
    useJUnitPlatform()
}