buildscript {
    val agp_version by extra("8.1.0-rc01")
    repositories {
        gradlePluginPortal()
        google()
        //  mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.android.tools.build:gradle:$agp_version")
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