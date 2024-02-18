import java.util.Properties
import java.io.FileInputStream

fun getMapsApiKey(): String {
    val properties = Properties()
    properties.load(rootProject.file("local.properties").inputStream())
    return properties.getProperty("MAPS_API_KEY")
}


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")

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


        val mapsApiKey = getMapsApiKey()
        buildConfigField("String", "MAPS_API_KEY", "\"${mapsApiKey}\"")




        buildTypes {

//            debug {
//                buildConfigField("String", "MAPS_API_KEY", Properties().apply {
//                    load(project.rootProject.file("local.properties").inputStream())
//                }["api.key"].toString())
//
//                manifestPlaceholders["MAPS_API_KEY"] = properties["api.key"].toString()
//            }

            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
//                buildConfigField("String", "MAPS_API_KEY", Properties().apply {
//                    load(project.rootProject.file("local.properties").inputStream())
//                }["api.key"].toString())
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
        implementation("com.google.android.libraries.places:places:3.3.0")
        implementation("com.google.firebase:firebase-storage:20.3.0")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

        implementation("androidx.activity:activity:1.8.0")

        //구글맵
        implementation("com.google.android.gms:play-services-maps:18.0.2")
        implementation("com.google.android.gms:play-services-location:19.0.1")

        //api 의존성
//      implementation ("com.google.android.gms:play-services-places:18.0.0")

        implementation ("com.google.maps.android:android-maps-utils:2.2.6")

        implementation ("com.squareup.okhttp3:okhttp:4.9.1")
        implementation ("com.google.code.gson:gson:2.8.6")

//        implementation ("noman.placesapi:placesAPI:1.1.3")


        implementation("com.google.android.libraries.places:places:3.3.0")

//firebase (라즈베리파이)
        implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
        implementation("com.google.firebase:firebase-analytics")

// realtime database
        implementation("com.google.firebase:firebase-database")

// firebase messaging
        implementation("com.google.firebase:firebase-messaging:23.4.0")

// img_upload -> Glide
        implementation("com.github.bumptech.glide:glide:4.12.0")
        annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

        //Retorfit

        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")


    }


