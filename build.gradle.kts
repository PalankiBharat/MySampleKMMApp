buildscript {

    repositories {
        gradlePluginPortal()
        google()
        //  mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
    }
}

allprojects {
    repositories {
        google()
        // mavenLocal()
        mavenCentral()
    }
}