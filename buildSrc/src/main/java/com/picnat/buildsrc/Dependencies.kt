package com.picnat.buildsrc

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30
    const val buildTools = "30.0.3"

    const val kotlin = "1.4.30"
    const val ktx = "1.3.2"

    const val appCompat = "1.2.0"
    const val material = "1.2.1"
    const val legacySupport = "1.0.0"
    const val constraintLayout = "2.0.4"

    const val firebaseAuth = "20.0.2"
    const val firebaseStorage = "19.2.1"
    const val firebaseBom = "26.5.0"

    const val coroutines = "1.4.1"
    const val coroutinesPlayServices = "1.1.1"
    const val koin = "2.2.2"

    const val lifecycleExtensions = "2.2.0"
    const val lifecycleCommon = "2.3.0"
    const val lifecycleLiveData = "2.3.0"

    const val roomDatabase = "2.2.6"

    const val junit = "4.12"
    const val espresso = "3.3.0"
    const val junitTest = "1.1.2"
    const val testRunner = "1.2.0"
}

object Libraries{
    const val kotlinLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coreKtxLib = "androidx.core:core-ktx:${Versions.ktx}"
    const val constraintLib = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val firebaseAuthLib = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    const val firebaseStorageLib = "com.google.firebase:firebase-storage:${Versions.firebaseStorage}"
    const val firebaseFirestoreLib = "com.google.firebase:firebase-firestore-ktx"
    const val firebaseBomLib = "com.google.firebase:firebase-bom:26.5.0:${Versions.firebaseBom}"

    const val coroutinesLib = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesPlayServicesLib = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesPlayServices}"

    const val lifecycleLib = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleCommon}"
    const val lifecycleLiveDataLib = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLiveData}"
    const val lifecycleExtensionsLib = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"

    const val koinLib = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModelLib = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinFragmentLib = "org.koin:koin-androidx-fragment:${Versions.koin}"

    const val roomLib = "androidx.room:room-runtime:${Versions.roomDatabase}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomDatabase}"
    const val roomCoroutinesLib = "androidx.room:room-ktx:${Versions.roomDatabase}"
}

object SupportLibraries {
    val appCompatLib = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val materialLib = "com.google.android.material:material:${Versions.material}"
    val constraintLayoutLib = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val legacyLib = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val junitTest = "androidx.test.ext:junit:${Versions.junitTest}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testRunner = "androidx.test.runner:${Versions.testRunner}"
}