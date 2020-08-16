plugins {
    java
    id("com.phodal.gradoid")
}
//
//templateExampleConfig {
//    message.set("Just trying this gradle plugin...")
//}


dependencies {
//    implementation(gradleApi())
    implementation("net.sf.proguard:proguard-gradle:5.2.1")
    implementation("joda-time:joda-time:2.10.6")
//    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    zzz("org.gradle:gradle-core:2.2")
}
