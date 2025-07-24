![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin)
![Compose](https://img.shields.io/badge/Compose-Multiplatform-4285F4?logo=jetpackcompose)
![Download](https://img.shields.io/badge/Download-Google_Play-32CD32?logo=googleplay)
![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg)

**Sainggik** adalah aplikasi pelacak keuangan pribadi yang sederhana, ringan, dan powerful. Terinspirasi dari istilah legendaris Minang "Sainggik" (Rp 250), aplikasi ini mendorong Anda untuk lebih mindful terhadap setiap rupiah yang dikeluarkan—karena bahkan jumlah uang kecil bisa memiliki makna besar.

Dengan interface yang bersih dan mudah digunakan, Sainggik dirancang untuk membantu siapa saja—dari mahasiswa hingga profesional—mengelola keuangan harian mereka secara efisien.

<p align="center">
  <a href="https://play.google.com/store/apps/details?id=id.buaja.sainggik">
    <img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" width="200">
  </a>
</p>

## 🌟 Fitur Utama

### 💰 Pencatatan Keuangan Harian
- **Pencatatan Pemasukan & Pengeluaran** - Catat transaksi harian dengan mudah dan cepat
- **Kategori Transaksi Fleksibel** - Organisir pengeluaran dengan kategori yang dapat disesuaikan
- **Riwayat Transaksi** - Lacak semua transaksi dengan mudah dan terorganisir
- **Tanpa Login** - Data tersimpan lokal di perangkat, privasi terjamin

### 📊 Ringkasan & Analisis Keuangan
- **Ringkasan Harian, Mingguan, Bulanan** - Lihat pola keuangan dalam berbagai periode
- **Pengaturan Anggaran** - Tetapkan budget sesuai kebutuhan dan pantau pencapaiannya
- **Interface Bersih** - Desain sederhana yang mudah dipahami semua kalangan
- **Pengingat Finansial** - Aplikasi yang mengingatkan pentingnya kebiasaan kecil dalam keuangan

## 🚀 Teknologi

Aplikasi dibangun dengan teknologi modern dan best practices:

- **Kotlin Multiplatform** - Codebase bersama untuk Android dan iOS
- **Jetpack Compose Multiplatform** - UI modern deklaratif cross-platform
- **Orbit MVI** - Arsitektur state management yang robust dan predictable
- **SQLDelight** - Database multiplatform dengan type-safe queries
- **Koin** - Dependency injection yang sederhana dan lightweight
- **Material 3 Design** - Mengikuti panduan desain terbaru Google

## 🗂 Struktur Project

```
sainggik/
├── composeApp/              # Modul utama Compose Multiplatform
│   ├── src/
│   │   ├── androidMain/     # Kode khusus Android
│   │   ├── commonMain/      # Kode bersama multiplatform
│   │   │   ├── id/buaja/sainggik/
│   │   │   │   ├── core/    # Komponen inti dan utilities
│   │   │   │   ├── data/    # Repository dan sumber data
│   │   │   │   ├── di/      # Dependency Injection
│   │   │   │   ├── domain/  # Business logic dan use cases
│   │   │   │   ├── feature/ # Fitur aplikasi (anggaran, kategori, transaksi)
│   │   │   │   └── App.kt   # Entry point aplikasi
│   │   │   └── sqldelight/  # Implementasi database
│   │   ├── iosMain/         # Kode khusus iOS
│   │   └── commonTest/      # Unit test
├── iosApp/                  # Modul Xcode untuk iOS
├── gradle/                  # Konfigurasi Gradle
├── libs.versions.toml       # Manajemen versi dependensi
└── build.gradle.kts         # Konfigurasi build utama
```

## 🤝 Kontribusi

Kontribusi sangat diterima! Silakan ikuti langkah berikut:

1. **Fork** repository ini
2. **Buat branch** untuk fitur baru (`git checkout -b feature/AmazingFeature`)
3. **Commit** perubahan (`git commit -m 'Add some AmazingFeature'`)
4. **Push** ke branch (`git push origin feature/AmazingFeature`)
5. **Buat Pull Request**

### Cara Berkontribusi
- 🐛 **Melaporkan bug** - Gunakan GitHub Issues dengan template bug report
- 💡 **Menyarankan fitur baru** - Buat feature request dengan detail yang jelas
- 📖 **Meningkatkan dokumentasi** - Perbaiki atau tambahkan dokumentasi
- 🔧 **Code contribution** - Ikuti coding standards dan pastikan tests pass
