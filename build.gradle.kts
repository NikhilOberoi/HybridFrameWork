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

// Performs testNG test
tasks.test {
    val suiteFile = System.getProperty("testSuite", "Login.xml")
    useTestNG {
        suites("src/test/resources/TestNG_Runner/$suiteFile")
    }
    // Optional: Log standard streams to Jenkins console output
    testLogging.showStandardStreams = true
}

val smokeTests by tasks.registering(Test::class) {
    group = "Verification" // Assign a group
    useTestNG {
        suites("src/test/resources/TestNG_Runner/smoke.xml")
        // Optional: Log standard streams to Jenkins console output
        testLogging.showStandardStreams = true
    }
    // Ensure the task depends on the 'testClasses' from the test source set
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
}

val regressionTests by tasks.registering(Test::class) {
    group = "Verification" // Assign a group
    useTestNG {
        suites("src/test/resources/TestNG_Runner/regression.xml")
        // Optional: Log standard streams to Jenkins console output
        testLogging.showStandardStreams = true
    }

    // Ensure the task depends on the 'testClasses' from the test source set
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
}

tasks.register("sanityTests", Test::class) {
    useTestNG { // Tells Gradle to use TestNG
        // Specify the path to your suite XML file
        suites("src/test/resources/TestNG_Runner/smoke.xml")
        useDefaultListeners = true // Optional: enables TestNG's default reporters
    }
    // Optional: show standard streams (like System.out.println) in the console output
    testLogging {
        showStandardStreams = true
        events("PASSED", "FAILED", "SKIPPED") //
    }
    // Ensure the task depends on the 'testClasses' from the test source set
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath
}