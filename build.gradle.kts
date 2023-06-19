buildscript {
    val agp_version by extra("8.0.2")
    repositories {
        gradlePluginPortal()
        google()
        //  mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        classpath("com.android.tools.build:gradle:$agp_version")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.21")

    }
}

allprojects {
    repositories {
        google()
        // mavenLocal()
        mavenCentral()
    }
}