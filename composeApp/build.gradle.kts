import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.mokkery)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SainggikApp"
            isStatic = false
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            // Koin
            implementation(libs.koin.android)
            // SQLDelight
            implementation(libs.sqldelight.android.driver)
        }
        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            // Common ViewModel
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            // Navigation
            implementation(libs.androidx.navigation.compose)
            // Serialization
            implementation(libs.kotlinx.serialization.json)
            // MVVM+
            implementation(libs.orbit.core)
            implementation(libs.orbit.viewmodel)
            implementation(libs.orbit.compose)
            // Kermit - Logging
            implementation(libs.kermit)
            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            // SQLDelight
            implementation(libs.sqldelight.primitive.adapters)
            implementation(libs.sqldelight.coroutines.extensions)
            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
            // DateTime
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.0")
        }
        iosMain.dependencies {
            // SQLDelight
            implementation(libs.sqldelight.native.driver)
        }

        androidUnitTest.dependencies {
            // SQLDelight
            implementation(libs.sqldelight.sqlite.driver)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            // Turbine
            implementation(libs.turbine)
            // Coroutines
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

android {
    namespace = "id.buaja.sainggik"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "id.buaja.sainggik"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 2
        versionName = "0.9.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    lint {
        disable += "NullSafeMutableLiveData"
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

sqldelight {
    databases {
        // Creates a database configuration named 'SainggikDatabase'
        // This generates a Kotlin class: SainggikDatabase
        create("SainggikDatabase") {

            // Sets the output package for generated classes like SainggikDatabase, BudgetsQueries, etc.
            packageName.set("id.buaja.sainggik")

            // Uses .sqm migration files to derive the current schema
            // Enable this AFTER creating at least one migration (e.g., 1.sqm)
            // If true, schema generation will use migration steps instead of .sq definitions
            deriveSchemaFromMigrations.set(false)

            // Enables asynchronous code generation for improved build performance
            generateAsync.set(true)

            // Validates that your migration files result in the expected final schema
            // If mismatched, the build will fail (very useful in CI/CD or release builds)
            verifyMigrations.set(true)

            // Directory where the generated schema .db file will be saved
            // Required for schema validation and historical tracking of your schema
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/databases"))

            // 👇 CLI commands to use with this configuration:

            // To generate the initial schema from .sq files (BEFORE you create migrations):
            // ./gradlew generateCommonMainSainggikDatabaseSchema

            // To verify that your migrations are correct (AFTER you have migrations):
            // ./gradlew verifyCommonMainSainggikDatabaseMigration
        }
    }
}
