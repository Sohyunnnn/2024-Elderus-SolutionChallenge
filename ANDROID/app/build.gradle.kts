import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.elderus"
    compileSdk = 34
    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.elderus"
        minSdk = 24
        targetSdk = 34  // 변경
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        


        buildTypes {

            debug {
                buildConfigField("String", "MAPS_API_KEY", Properties().apply {
                    load(project.rootProject.file("local.properties").inputStream())
                }["api.key"].toString())

                manifestPlaceholders["MAPS_API_KEY"] = properties["api.key"].toString()
            }

            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                buildConfigField("String", "MAPS_API_KEY", Properties().apply {
                    load(project.rootProject.file("local.properties").inputStream())
                }["api.key"].toString())
            }


            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }


    }

    dependencies {
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.11.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

        implementation("androidx.activity:activity:1.8.0")

        //구글맵
        implementation("com.google.android.gms:play-services-maps:18.0.2")
        implementation("com.google.android.gms:play-services-location:19.0.1")
    }


