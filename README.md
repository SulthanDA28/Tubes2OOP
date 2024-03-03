<!-- # Tubes2_OOP

## Requirement
1. java 17
2. maven

## Build
```
mvn clean package
```

## Run
```
java -jar .\bin\App.jar
``` -->

# Tugas Besar 2 - IF2210 - OOP
<h2 align="center">
  Manajemen Usaha BNMO<br/>
</h2>
<hr>

## Table of Contents
1. [General Info](#general-information)
2. [Creator Info](#creator-information)
3. [Features](#features)
4. [Technologies Used](#technologies-used)
5. [Setup](#setup)
6. [Usage](#usage)
7. [Screenshots](#screenshots)
7. [Structure](#structure)
8. [Project Status](#project-status)
9. [Room for Improvement](#room-for-improvement)
10. [Acknowledgements](#acknowledgements)
11. [Contact](#contact)

<a name="general-information"></a>

## General Information
Manajemen Usaha BNMO merupakan sebuah aplikasi berbasis GUI dengan bahasa pemrograman Java yang dibuat untuk memenuhi Tugas Besar 2 IF2210 - OOP. 
Program pada Tugas Besar 2 ini merupakan sebuah program POS (Point of Sales), yaitu program yang membantu sebuah toko untuk melakukan dan mencatat transaksi yang berhubungan dengan usaha mereka. Program ini yang memiliki fitur dasar manajemen inventaris dan manajemen transaksi. Selain itu, program memiliki fitur membership agar toko dapat memberikan reward kepada pelanggan yang setia, dan juga fitur pembuatan laporan untuk mendukung toko dalam melakukan evaluasi.

<a name="creator-information"></a>

## Creator Information

| Nama                        | NIM      | E-Mail                      |
| --------------------------- | -------- | --------------------------- |
| Ahmad Ghulam Ilham          | 13521118 | 13521118@std.stei.itb.ac.id |
| Muhammad Naufal Nalendra    | 13521152 | 13521152@std.stei.itb.ac.id |
| Muhammad Dhiwaul Akbar      | 13521158 | 13521158@std.stei.itb.ac.id |
| Sulthan Dzaky Alfarozi      | 13521159 | 13521159@std.stei.itb.ac.id |
| Mohammad Rifqi Farhansyah   | 13521166 | 13521166@std.stei.itb.ac.id |
| Muhammad Habibi Husni       | 13521169 | 13521169@std.stei.itb.ac.id |

<a name="features"></a>

## Features
- Melihat `Jam`, `Tanggal`, dan `Waktu` saat ini pada Main Menu
- Melakukan `Registrasi Member` baru serta `Update` data member
- Mencetak `Riwayat Transaksi`
- `Menambahkan dan Menghapus` item pada inventory serta `Update` data item
- Melihat `Laporan Penjualan` serta `bill`
- Membuka `settings` untuk mengubah `tipe masukan data` serta memilih `path data`


<a name="technologies-used"></a>

## Technologies Used
- Java - version 17
- JavaFX - version 17
- Maven - version 3.8.3

> Note: The version of the libraries above is the version that we used in this project. You can use the latest version of the libraries.

<a name="setup"></a>

## Setup
1. Unduh versi JavaFX yang sesuai melalui pranala [berikut](https://gluonhq.com/products/javafx/).
2. Ekstrak berkas yang telah diunduh.
3. Buka IntelliJ IDEA.
4. Pilih menu File > Project Structure.
5. Pada menu Project Settings, pilih Libraries.
6. Klik tombol + dan pilih Java.
7. Pilih direktori lib pada direktori JavaFX yang telah diekstrak.
8. Klik OK.

<a name="usage"></a>

## Usage
1. Clone repositori ini, dengan cara:
```bash
git clone https://github.com/habibibi/Tubes2_OOP.git
```
2. Buka direktori tempat anda menyimpan projek ini.
3. Klik kanan dan `open terminal` di direktori tersebut.
4. Buka direktori `src/main/java/com/himehime/app` dengan IDE anda.
5. Ketikkan perintah `mvn clean package` untuk melakukan build.
6. Ketikkan perintah `mvn clean javafx:run` untuk menjalankan program.

<a name="screenshots"></a>

## Screenshots
<p>
  <p>Gambar 1. Menu Utama</p>
  <img src="/img/SS1.png/">
  <nl>
  <p>Gambar 2. Member Registration</p>
  <img src="/img/SS2.png/">
  <nl>
  <p>Gambar 3. Update Membership</p>
  <img src="/img/SS3.png/">
  <nl>
   <p>Gambar 4. Riwayat</p>
   <img src="/img/SS4.png/">
   <nl>
   <p>Gambar 5. Add Inventory</p>
   <img src="/img/SS5.png/">
   <nl>
   <p>Gambar 6. Update Inventory</p>
   <img src="/img/SS6.png/">
   <nl>
    <p>Gambar 7. Sales Report</p>
    <img src="/img/SS7.png/">
    <nl>
    <p>Gambar 8. Settings</p>
    <img src="/img/SS9.png/">
    <nl>
</p>

<a name="structure"></a>s

## Structure
```bash
│   .gitignore
│   dependency-reduced-pom.xml
│   pom.xml
│   README.md
│
├───.idea
│       .gitignore
│       compiler.xml
│       encodings.xml
│       jarRepositories.xml
│       misc.xml
│       vcs.xml
│       workspace.xml
│
├───bin
│       App.jar
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───himehime
│   │   │           ├───app
│   │   │           │       AddInventory.java
│   │   │           │       App.java
│   │   │           │       App2.java
│   │   │           │       ApplicationInterface.java
│   │   │           │       BackendTest.java
│   │   │           │       BillPage.java
│   │   │           │       MainMenu.java
│   │   │           │       MemberRegistration.java
│   │   │           │       Riwayat.java
│   │   │           │       SalesReport.java
│   │   │           │       SettingsMenu.java
│   │   │           │       UpdateInventory.java
│   │   │           │       UpdateMembership.java
│   │   │           │
│   │   │           └───lib
│   │   │                   Bill.java
│   │   │                   Customer.java
│   │   │                   CustomerManager.java
│   │   │                   DataStoreInterface.java
│   │   │                   Dummy.java
│   │   │                   FixedBill.java
│   │   │                   ImageAdaptor.java
│   │   │                   Item.java
│   │   │                   ItemSubscriber.java
│   │   │                   JSONAdapter.java
│   │   │                   Member.java
│   │   │                   MementoItem.java
│   │   │                   ReportSystem.java
│   │   │                   SettingManager.java
│   │   │                   VIPMember.java
│   │   │                   Warehouse.java
│   │   │                   XMLAdapter.java
│   │   │
│   │   └───resources
│   │           application.css
│   │           bg.png
│   │           bg1.png
│   │           hor.png
│   │           icon_awal.png
│   │           logo.png
│   │           rec.png
│   │           registImage.png
│   │           settings_img.png
│   │           store.png
│   │           tmp
│   │           updateImage.png
│   │           ver.png
│   │
│   └───test
│       └───java
│               tmp
│
└───target
    ├───classes
    │   │   application.css
    │   │   bg.png
    │   │   bg1.png
    │   │   hor.png
    │   │   icon_awal.png
    │   │   logo.png
    │   │   rec.png
    │   │   registImage.png
    │   │   settings_img.png
    │   │   store.png
    │   │   tmp
    │   │   updateImage.png
    │   │   ver.png
    │   │
    │   └───com
    │       └───himehime
    │           ├───app
    │           │       AddInventory$1.class
    │           │       AddInventory$2.class
    │           │       AddInventory.class
    │           │       App$1.class
    │           │       App.class
    │           │       App2.class
    │           │       ApplicationInterface.class
    │           │       BackendTest.class
    │           │       BillPage$1.class
    │           │       BillPage$2.class
    │           │       BillPage.class
    │           │       MainMenu$1.class
    │           │       MainMenu.class
    │           │       MemberRegistration$1.class
    │           │       MemberRegistration.class
    │           │       Riwayat$1.class
    │           │       Riwayat.class
    │           │       SalesReport$1.class
    │           │       SalesReport.class
    │           │       SettingsMenu$1.class
    │           │       SettingsMenu$2.class
    │           │       SettingsMenu.class
    │           │       UpdateInventory$1.class
    │           │       UpdateInventory.class
    │           │       UpdateMembership$1.class
    │           │       UpdateMembership.class
    │           │
    │           └───lib
    │                   Bill.class
    │                   Customer.class
    │                   CustomerManager.class
    │                   DataStoreInterface.class
    │                   Dummy.class
    │                   FixedBill.class
    │                   ImageAdaptor.class
    │                   Item.class
    │                   ItemSubscriber.class
    │                   JSONAdapter$1.class
    │                   JSONAdapter$2.class
    │                   JSONAdapter$3.class
    │                   JSONAdapter.class
    │                   Member.class
    │                   MementoItem.class
    │                   ReportSystem$1.class
    │                   ReportSystem$2.class
    │                   ReportSystem.class
    │                   SettingManager.class
    │                   VIPMember.class
    │                   Warehouse.class
    │                   XMLAdapter.class
    │
    ├───generated-sources
    │   └───annotations
    └───maven-status
        └───maven-compiler-plugin
            └───compile
                └───default-compile
                        createdFiles.lst
                        inputFiles.lst
```

<a name="project-status">

## Project Status
Project is: _complete_

<a name="room-for-improvement">

## Room for Improvement
- Meningkatkan performa dan tampilan program

<a name="acknowledgements">

## Acknowledgements
- Thanks To Allah SWT

<a name="contact"></a>

## Contact
<h4 align="center">
  Contact Us : Himehime<br/>
  2023
</h4>
<hr>
