# Android Explorer

Aplikasi belajar yang menjelaskan fungsi dan komponen Android (Jetpack Compose). Setiap topik berisi **penjelasan**, **demo interaktif**, dan **contoh kode**. Daftar isi (TOC) ada di drawer yang dibuka dari app bar (tampil permanen di layar lebar).

Dibangun dengan Kotlin Multiplatform + Compose Multiplatform. Target: **Android** dan **Desktop (JVM)**.

## Menjalankan
- Android: buka di Android Studio, jalankan konfigurasi `composeApp`, atau `./gradlew :composeApp:installDebug`.
- Desktop: `./gradlew :composeApp:run`.

## Struktur
- `composeApp/src/commonMain` kode bersama (UI, TOC, materi).
  - `content/` daftar topik per kategori.
  - `model/Topic.kt` model satu materi.
  - `ui/` TOC dan halaman topik.
- `composeApp/src/androidMain` `MainActivity`, manifest, `actual` Android.
- `composeApp/src/desktopMain` entry point desktop dan `actual` desktop.

## Menambah topik
Tambahkan `Topic(...)` ke salah satu file di `content/`, lalu otomatis muncul di TOC.
