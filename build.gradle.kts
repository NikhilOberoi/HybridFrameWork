plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
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
    implementation("org.seleniumhq.selenium:selenium-java:4.18.1") //

    // Add a testing framework dependency, for example, JUnit 5 or TestNG
    // For JUnit 5:
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    // For TestNG (optional, if preferred):
    testImplementation("org.testng:testng:7.12.0")


    // Jackson Core - Json Reader
    implementation("org.json:json:20231013")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    // WebDriverManager for automatic browser driver management
    testImplementation("io.github.bonigarcia:webdrivermanager:5.6.3")

    // Use "com.github.User:Repo:Tag" format
    // Example: implementation("com.github.google:guava:30.0-jre")
    //implementation("com.github.User:Repo:Tag")

    // ExtentReports for test reporting
    implementation("com.aventstack:extentreports:5.1.1")

    // Import the Log4j BOM to manage versions
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.23.1")) // Use the latest version

    // Add Log4j API and Core dependencies
    implementation("org.apache.logging.log4j:log4j-api")
    runtimeOnly("org.apache.logging.log4j:log4j-core") // Use runtimeOnly for the core implementation in an application
}

// Performs Junit test
tasks.test {
    useTestNG(){
        suites("src/test/resources/TestNG_Runner/Login.xml")
    }
}

// Performs TestNG test
// Define a new Test task for TestNG
//val testNG by tasks.registering(Test::class) {
//    useTestNG()
//    // Optional: point to testng.xml file
//    // options.suites("src/test/resources/testng.xml")
//    // Filter to only include TestNG annotated classes (optional if file separation is used)
//    // includes = listOf("**/TestNG*.class")
//}
