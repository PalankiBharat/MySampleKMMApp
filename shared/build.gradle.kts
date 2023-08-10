plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.google.devtools.ksp") version "1.9.0-1.0.11"
    id("kotlinx-serialization")
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-9"
    id("io.realm.kotlin") version "1.9.1"
    id("app.cash.sqldelight") version "2.0.0-alpha05"


}

val ktorVersion = "2.3.2"
val sqlDelightVersion = "2.0.0-alpha05"
val dateTimeVersion = "0.4.0"

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()


    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting{
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("io.insert-koin:koin-core:3.3.3"){
                    because("DI for KMM")
                }
                //Only needed when you want to use Kotlin Serialization
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")


                implementation("io.github.aakira:napier:2.6.1"){
                    because("Used for Logging in KMM shared module")
                }
                api("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-8"){
                    because("Used for sharing viewModels")
                }
                implementation("io.realm.kotlin:library-base:1.9.1") {
                    because("Used for storing data offline in NO SQL format")
                }

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")


            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }



        val androidMain by getting{
            dependencies {
                implementation("io.insert-koin:koin-androidx-compose:3.4.5")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("app.cash.sqldelight:android-driver:$sqlDelightVersion")

            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies{
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("app.cash.sqldelight:native-driver:$sqlDelightVersion")

            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.mysamplekmmapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 25
    }
}

sqldelight {
    databases {
        create("SuperheroDb") {
            packageName.set("com.example")
        }
    }
}

