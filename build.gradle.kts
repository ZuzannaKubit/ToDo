// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        // other repositories...
        mavenCentral()
        google()
    }

    dependencies {
        // other plugins...
        //classpath 'com.google.dagger:hilt-android-gradle-plugin:2.46.1'
        // classpath 'com.android.tools.build:gradle:7.2.2'
        // classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    //Hilt:
    id("com.google.dagger.hilt.android") version "2.44" apply false
}