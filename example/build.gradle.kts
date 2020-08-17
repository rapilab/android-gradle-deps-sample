plugins {
//    java
    id("com.phodal.gradoid")
}

repositories {
    mavenCentral()
}

dependencies {
//    implementation(gradleApi())
    implementation("net.sf.proguard:proguard-gradle:5.2.1")
    phodalImplementation("joda-time:joda-time:2.10.6")
}
