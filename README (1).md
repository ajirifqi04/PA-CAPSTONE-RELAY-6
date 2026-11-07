# RELAY

## Sistem Informasi Pengelolaan Pengiriman Surat, Dokumen, dan Paket pada Perusahaan Jasa Pos Relay di Wilayah Kalimantan Timur

**Project gabungan Pemrograman Berorientasi Objek (PBO) dan Sistem Basis
Data (SBD)**  
**Kelompok 6 – Program Studi Sistem Informasi, Fakultas Teknik,
Universitas Mulawarman (2026)**

------------------------------------------------------------------------

## 1. Deskripsi Project

RELAY merupakan aplikasi desktop untuk mendukung pengelolaan proses
pengiriman surat, dokumen, dan paket pada perusahaan jasa pos Relay di
wilayah Kalimantan Timur. Aplikasi dikembangkan menggunakan Java dengan
antarmuka Java Swing dan terhubung ke basis data MySQL melalui
Hibernate.

Sistem mengelola data pengguna, lokasi operasional, layanan pengiriman,
tarif wilayah, pesanan, pengiriman, dan pembayaran. Perancangan basis
data terdiri dari sembilan entitas/tabel yang saling berhubungan melalui
primary key dan foreign key.

Project ini menggabungkan penerapan konsep Pemrograman Berorientasi
Objek (PBO) pada struktur aplikasi Java dan konsep Sistem Basis Data
(SBD) pada perancangan serta pengelolaan data.

## 2. Contributor

| No. | Nama                       | NIM        |
|-----|----------------------------|------------|
| 1   | Claudya Yusfa Ariyani      | 2509116043 |
| 2   | Aji Rifqi Suryana          | 2509116054 |
| 3   | Muhammad Dzikri Andhika    | 2509116103 |
| 4   | Farrel Fazzadinata Sumarna | 2509116111 |

**Program Studi:** Sistem Informasi  
**Fakultas:** Teknik  
**Universitas:** Universitas Mulawarman  
**Tahun:** 2026

## 3. Fitur Utama Program

### 3.1 Fitur Pelanggan

Pelanggan merupakan pengguna yang dapat melakukan pemesanan layanan pengiriman surat, dokumen, dan paket melalui aplikasi RELAY. Pelanggan juga dapat melihat riwayat pesanan, memantau status pengiriman, serta mengelola informasi akun.

- Registrasi akun pelanggan.
- Login menggunakan username dan password.
- Membuat pesanan pengiriman.
- Melihat riwayat pesanan.
- Mencari informasi pesanan melalui kolom pencarian pada halaman
  riwayat.
- Melihat informasi status pengiriman yang terkait dengan pesanan.
- Melihat dan memperbarui informasi akun.

### 3.2 Fitur Agen

Agen merupakan pengguna yang bertugas mengelola data pesanan pelanggan pada tingkat agen.

Fitur Agen meliputi:

- Login menggunakan akun Agen.
- Melihat data pesanan yang dibuat oleh pelanggan.
- Menambahkan data pesanan pelanggan dengan melengkapi informasi tambahan yang diperlukan oleh Agen.
- Melihat status pengiriman untuk mengetahui perkembangan proses pengiriman barang.

### 3.3 Fitur Pusat

Pusat merupakan pengguna yang berperan dalam memantau hasil operasional dan mengelola akun internal pada sistem RELAY.

Fitur Pusat dalam rancangan sistem meliputi:


- Menampilkan dashboard yang berisi Key Performance Indicator (KPI) untuk memantau kinerja operasional RELAY.
- Melihat laporan bulanan total hasil pendapatan.
- Memfilter laporan berdasarkan data yang tersedia.
- Melakukan registrasi akun internal untuk kebutuhan operasional RELAY.

### 3.4 Fitur Cabang

Cabang merupakan pengguna yang bertugas mengelola jadwal dan proses penerimaan pengiriman barang pada wilayah operasionalnya.

Fitur Cabang dalam rancangan sistem meliputi:

- Menambahkan jadwal pengiriman ke Cabang penerima.
- Menampilkan jadwal pengiriman yang diterima Cabang.
- Mencatat penerimaan barang dari Cabang pengirim.
- Menampilkan data pesanan pelanggan.
- Mencari data pengiriman berdasarkan nomor resi.
- Memperbarui jadwal pengiriman ke Agen penerima.

## 4.Penerapan 4 Pilar OOP

### 4.1 Encapsulation

Encapsulation diterapkan dengan menggunakan atribut `private` pada class
model dan menyediakan method getter serta setter untuk mengakses atau
mengubah nilai atribut. Contohnya terdapat pada class `Pengguna`,
`Pesanan`, `Pengiriman`, `Pembayaran`, dan class model lainnya.
### Dokumentasi Penerapan Encapsulation

Konsep Encapsulation pada aplikasi RELAY diterapkan dalam class `Pengguna.java`. Atribut pengguna dideklarasikan menggunakan access modifier `private` untuk membatasi akses langsung dari luar class.

Untuk mengakses dan mengubah nilai atribut, class menyediakan method getter dan setter. Dengan penerapan ini, pengelolaan data pengguna menjadi lebih terstruktur.


**1. Penerapan Private Attribute**

<img width="450" alt="Private Attribute Pengguna.java" src="https://github.com/user-attachments/assets/51aa746a-d8f0-4e6b-82f3-7e51c0534c32" />

**2. Implementasi Getter dan Setter**

<img width="450" alt="Getter dan Setter Pengguna.java" src="https://github.com/user-attachments/assets/cbed3159-86fa-42f5-bdd5-ea2e893a2930" />


### 4.2 Inheritance

Inheritance diterapkan melalui hubungan pewarisan class:

- `LokasiOperasional` sebagai class induk.
- `Cabang` sebagai class turunan dari `LokasiOperasional`.
- `Agen` sebagai class turunan dari `LokasiOperasional`.

Pada pemetaan Hibernate, pewarisan tersebut menggunakan strategi
`InheritanceType.JOINED`, sehingga data umum lokasi disimpan pada tabel
induk dan data khusus disimpan pada tabel turunan.


**Dokumentasi Penerapan Inheritance**

Konsep Inheritance pada aplikasi RELAY diterapkan melalui class `Cabang` yang mewarisi class `LokasiOperasional` menggunakan keyword `extends`. Dengan pewarisan ini, class `Cabang` dapat menggunakan atribut dan method yang diwariskan dari class induknya serta memiliki atribut khusus seperti `kodeCabang`, `namaAdminCabang`, dan `statusOperasional`.

**1. Implementasi Inheritance pada Cabang.java**

<img width="434" height="260" alt="Screenshot 2026-11-06 114143" src="https://github.com/user-attachments/assets/12e9c48d-83e4-410d-94f7-79c4dae42cc3" />





### 4.3 Abstraction

Abstraction diterapkan melalui interface generik `Repository<T>` yang
mendefinisikan operasi dasar penyimpanan data, yaitu:

- `tambah(T data)`
- `ubah(T data)`
- `tampilkanSemua()`

Interface ini menjadi kontrak bagi class DAO yang mengelola data
masing-masing entitas.

**Dokumentasi Penerapan Abstraction**

Konsep Abstraction pada aplikasi RELAY diterapkan melalui interface `Repository<T>` yang mendefinisikan operasi dasar pengelolaan data, yaitu `tambah()`, `ubah()`, dan `tampilkanSemua()`.

Interface ini hanya menentukan method yang harus disediakan, sedangkan implementasinya dilakukan oleh class DAO seperti `PengirimanDAO`, `PesananDAO`, dan `PenggunaDAO`. Dengan demikian, proses pengelolaan data memiliki struktur yang konsisten.

**1. Implementasi Abstraction pada Repository.java**

<img width="274" height="111" alt="Screenshot 2026-11-06 114851" src="https://github.com/user-attachments/assets/39f4aa3b-7de1-485c-b604-9c2cd47c4f8a" />


### 4.4 Polymorphism

Polymorphism diterapkan melalui implementasi interface `Repository<T>`
oleh beberapa class DAO, seperti `PenggunaDAO`, `PesananDAO`,
`PengirimanDAO`, `PembayaranDAO`, `LayananDAO`, `LokasiDAO`, dan
`TarifWilayahDAO`. Setiap DAO menyediakan implementasi operasi
repository sesuai jenis data yang dikelolanya.


**Dokumentasi Penerapan Polymorphism**

Konsep Polymorphism pada aplikasi RELAY didukung oleh penggunaan interface `Repository<T>` yang diimplementasikan oleh berbagai class DAO, seperti `PengirimanDAO`, `PesananDAO`, dan `PenggunaDAO`.

Setiap class DAO menyediakan implementasi method yang sama, seperti `tambah()`, `ubah()`, dan `tampilkanSemua()`, tetapi bekerja dengan jenis entitas yang berbeda. Melalui interface `Repository<T>`, objek DAO dapat digunakan melalui tipe interface yang sama.

**1. Implementasi Polymorphism pada PengirimanDAO.java**

<img width="436" height="260" alt="Screenshot 2026-11-06 122653" src="https://github.com/user-attachments/assets/98e62c78-a7c4-4fe0-8e95-6d63d56cda94" />




## 5. Struktur Folder/Package

Aplikasi memisahkan tanggung jawab program ke dalam beberapa package:

**Dokumentasi Struktur Package Aplikasi RELAY**

## 5. Arsitektur dan Struktur Package

Aplikasi RELAY dikembangkan menggunakan bahasa pemrograman Java dengan struktur package yang dipisahkan berdasarkan fungsi dan tanggung jawab masing-masing komponen. Pembagian package ini bertujuan untuk memudahkan pengembangan, pemeliharaan, serta pengelolaan kode program.

### 1. Package Config dan Controller

Package `config` digunakan untuk menyimpan konfigurasi yang diperlukan oleh aplikasi, termasuk pengaturan koneksi dan pengelolaan sesi pengguna. Beberapa class yang terdapat dalam package ini adalah `HibernateUtil.java` dan `SesiPengguna.java`.

Sementara itu, package `controller` berfungsi sebagai penghubung antara antarmuka pengguna dengan proses pengolahan data. Controller menangani permintaan dari pengguna dan mengarahkan proses sesuai kebutuhan masing-masing peran.

Beberapa class yang terdapat dalam package `controller` antara lain:
- `AgenController.java`
- `CabangController.java`
- `LoginController.java`
- `PelangganController.java`
- `PusatController.java`
- `RegistrasiController.java`

**Dokumentasi Package Config:**

<img width="149" height="46" alt="Screenshot 2026-11-07 182933" src="https://github.com/user-attachments/assets/11a8e2b7-9e43-42d7-8f94-8cd18aaeb038" />


**Dokumentasi Package Controller:**

<img width="133" height="62" alt="Screenshot 2026-11-07 182958" src="https://github.com/user-attachments/assets/8042ce5d-2f42-4cd1-b4ff-8439dc8f031b" />


### 2. Package DAO dan Main

Package `dao` (Data Access Object) digunakan untuk mengelola proses akses data, seperti penyimpanan, pengambilan, pembaruan, dan penghapusan data yang diperlukan oleh aplikasi. Penggunaan DAO membantu memisahkan proses pengelolaan data dari tampilan pengguna.

Package `main` berfungsi sebagai titik awal untuk menjalankan aplikasi RELAY. Di dalam package ini terdapat class `Main.java` yang digunakan untuk memulai program.

**Dokumentasi Package DAO:**

<img width="140" height="100" alt="Screenshot 2026-11-07 183009" src="https://github.com/user-attachments/assets/9b528e37-f5fa-4b9a-930d-b5d4fc2b948c" />


**Dokumentasi Package Main:**

<img width="101" height="18" alt="Screenshot 2026-11-07 183027" src="https://github.com/user-attachments/assets/746e44ea-3ddc-45e8-b138-7b2213d4761c" />


### 3. Package Model dan Util

Package `model` berfungsi untuk menyimpan class yang merepresentasikan data atau entitas dalam aplikasi RELAY. Class model digunakan untuk mengelola informasi yang berkaitan dengan pengguna, pesanan, pengiriman, pembayaran, dan layanan.

Sementara itu, package `util` menyediakan fungsi pendukung yang dapat digunakan oleh bagian lain dalam aplikasi. Salah satu class yang terdapat pada package ini adalah `PasswordHash.java`, yang berkaitan dengan pengolahan hash kata sandi.

**Dokumentasi Package Model:**

<img width="131" height="101" alt="Screenshot 2026-11-07 183038" src="https://github.com/user-attachments/assets/6685fdd6-aca9-4b43-be90-4d4a36bdb8d9" />


**Dokumentasi Package Util:**

<img width="150" height="136" alt="Screenshot 2026-11-07 183104" src="https://github.com/user-attachments/assets/1ce19bb8-1fb4-4f12-90d9-64d75824f663" />


### 4. Package View dan View Agen

Package `view` digunakan untuk menyimpan class antarmuka utama yang berinteraksi langsung dengan pengguna. Pada struktur project RELAY, package ini memuat `LoginView.java` untuk tampilan login dan `RegistrasiPelangganView.java` untuk tampilan registrasi pelanggan.

Sementara itu, package `view.agen` berisi class antarmuka yang digunakan oleh pengguna dengan peran Agen. Antarmuka tersebut mendukung aktivitas operasional agen, termasuk pengelolaan pesanan dan pengiriman sesuai fungsi yang tersedia dalam aplikasi.

**Dokumentasi Package View:**

<img width="134" height="28" alt="Screenshot 2026-11-07 183121" src="https://github.com/user-attachments/assets/098393f1-46bc-4820-b77a-6acd10591a24" />


**Dokumentasi Package View Agen:**

<img width="139" height="35" alt="Screenshot 2026-11-07 183130" src="https://github.com/user-attachments/assets/b67e7d0d-b113-40c2-9af5-f0100c5489b2" />


### 5. Package View Cabang dan View Pelanggan

Package `view.cabang` berisi class antarmuka yang digunakan untuk mendukung kegiatan operasional Cabang. Beberapa class dalam package ini meliputi:
- `CabangView.java`
- `KelolaPengirimanKeAgenView.java`
- `KelolaPengirimanKeCabangView.java`
- `TerimaBarangCabangView.java`

Class tersebut menyediakan antarmuka untuk kegiatan cabang, termasuk pengelolaan pengiriman dan penerimaan barang.

Sementara itu, package `view.pelanggan` menyediakan antarmuka yang digunakan pelanggan untuk menjalankan berbagai aktivitas dalam aplikasi. Beberapa class yang terdapat dalam package ini adalah:
- `BuatPesananView.java`
- `KelolaAkunPelangganView.java`
- `PelangganView.java`
- `RiwayatPesananView.java`

Melalui antarmuka tersebut, pelanggan dapat mengakses halaman utama, membuat pesanan, mengelola informasi akun, dan melihat riwayat pesanan.

**Dokumentasi Package View Cabang:**

<img width="143" height="44" alt="Screenshot 2026-11-07 183142" src="https://github.com/user-attachments/assets/39405320-c3c5-47b8-af93-025a0e1f76cb" />

**Dokumentasi Package View Pelanggan:**

<img width="136" height="46" alt="Screenshot 2026-11-07 183153" src="https://github.com/user-attachments/assets/8ff8be19-ae79-42a7-8342-3349a6454c64" />


### 6. Package View Pusat

Package `view.pusat` berisi class antarmuka yang digunakan oleh pengguna dengan peran Pusat. Package ini mendukung kegiatan pengelolaan dan pemantauan operasional aplikasi RELAY melalui tampilan yang disediakan untuk pengguna Pusat.

Fungsi antarmuka pada bagian Pusat mencakup akses ke dashboard, pengelolaan laporan bulanan, serta registrasi akun internal sesuai fitur yang tersedia dalam aplikasi.

**Dokumentasi Package View Pusat:**

<img width="140" height="47" alt="Screenshot 2026-11-07 183205" src="https://github.com/user-attachments/assets/9296a1e9-5030-485c-afc2-eede100eeb11" />





Keterangan package:

| Package      | Fungsi                                                                           |
|--------------|----------------------------------------------------------------------------------|
| `model`      | Menyimpan class entitas yang merepresentasikan data aplikasi dan tabel database. |
| `view`       | Menyediakan antarmuka pengguna menggunakan Java Swing.                           |
| `controller` | Mengatur alur proses antara tampilan dan pengolahan data.                        |
| `dao`        | Menangani operasi akses dan manipulasi data menggunakan Hibernate.               |
| `config`     | Menyediakan konfigurasi Hibernate dan pengelolaan sesi pengguna.                 |
| `main`       | Menyediakan titik awal untuk menjalankan aplikasi.                               |
### 5.1 Main Class (`Main.java`)

Class `Main` merupakan titik awal (*entry point*) ketika aplikasi RELAY dijalankan. Class ini bertanggung jawab untuk menginisialisasi kebutuhan awal aplikasi sebelum menampilkan halaman login.

Pada class ini terdapat beberapa proses utama:

1. **Pengaturan Tampilan:** Mengatur Look and Feel menggunakan Nimbus agar tampilan aplikasi lebih menarik.
2. **Inisialisasi Database:** Memanggil `HibernateUtil.getSessionFactory()` untuk menginisialisasi SessionFactory Hibernate.
3. **Menampilkan Halaman Login:** Membuat objek `LoginView` sebagai halaman awal aplikasi.
4. **Pengaturan Posisi Tampilan:** Menggunakan `setLocationRelativeTo(null)` agar jendela login muncul di tengah layar.
5. **Penanganan Kesalahan:** Menggunakan `try-catch` untuk menangani kesalahan ketika proses inisialisasi aplikasi berlangsung.

**Dokumentasi Source Code Main.java**

<img width="463" height="240" alt="Screenshot 2026-11-06 122000" src="https://github.com/user-attachments/assets/4b587899-fc0e-401a-a287-9432a15f307c" />



### 5.2 Repository Interface (`Repository.java`)
Interface `Repository<T>` merupakan komponen yang digunakan sebagai dasar pengelolaan akses data pada aplikasi RELAY. Interface ini menggunakan konsep generics sehingga dapat diterapkan pada berbagai jenis objek tanpa harus membuat struktur interface yang berbeda untuk setiap entitas.

Pada interface ini terdapat tiga method utama:
1. **tambah(T data):** Mendefinisikan operasi untuk menambahkan data baru ke dalam sistem.
2. **ubah(T data):** Mendefinisikan operasi untuk memperbarui data yang sudah tersedia.
3. **tampilkanSemua():** Mendefinisikan operasi untuk mengambil seluruh data dalam bentuk `List<T>`.
**Konsep Pemrograman Berorientasi Objek:**
- **Abstraction:** Memisahkan definisi operasi pengelolaan data dari detail implementasinya.
- **Generics:** Memungkinkan interface digunakan oleh berbagai tipe objek sehingga struktur kode lebih fleksibel dan dapat digunakan kembali.

**Dokumentasi Source Code Repository.java**

<img width="469" height="110" alt="Screenshot 2026-11-06 122151" src="https://github.com/user-attachments/assets/c5975e25-a873-4df4-ba88-381cfb3d56a6" />


### 5.3 Data Access Object (`PengirimanDAO.java`)
Class `PengirimanDAO` merupakan bagian dari Data Access Object (DAO) yang bertugas mengelola proses akses dan manipulasi data pengiriman pada database. Class ini mengimplementasikan interface `Repository<Pengiriman>` dan menggunakan Hibernate untuk menjalankan operasi database.

Pada class ini terdapat beberapa proses utama:

1. **Penambahan Data:** Menggunakan method `tambah()` dengan `session.persist()` untuk menyimpan data pengiriman baru ke database.
2. **Perubahan Data:** Menggunakan method `ubah()` dengan `session.merge()` untuk memperbarui data pengiriman yang sudah tersimpan.
3. **Pencarian Data:** Menggunakan method `cariBerdasarkanResi()` untuk mencari data pengiriman berdasarkan nomor resi.
4. **Menampilkan Data:** Menggunakan method `tampilkanSemua()` untuk mengambil seluruh data pengiriman berdasarkan tanggal pengiriman terbaru.
5. **Filter Data:** Menyediakan method untuk menampilkan data berdasarkan cabang, agen, dan status pengiriman menggunakan Hibernate Query Language (HQL).
6. **Penghapusan Data:** Menggunakan method `hapus()` dengan `session.remove()` untuk menghapus data pengiriman berdasarkan nomor resi.
7. **Pengelolaan Transaksi:** Menggunakan `commit()` untuk menyimpan perubahan dan `rollback()` untuk membatalkan transaksi apabila terjadi kesalahan.

**Dokumentasi Source Code PengirimanDAO.java**

**1. Implementasi Repository dan Penambahan Data**

<img width="451" height="262" alt="Screenshot 2026-11-06 122257" src="https://github.com/user-attachments/assets/9a208d6d-ed30-4155-98d3-09504dd1c977" />

**2. Penghapusan Data dan Pengelolaan Transaksi**

<img width="430" height="229" alt="Screenshot 2026-11-06 122424" src="https://github.com/user-attachments/assets/27e5ce58-95a7-4740-a01e-87a3f89661a6" />

### 5.4 Ringkasan Implementasi DAO

Selain PengirimanDAO, aplikasi RELAY juga memiliki beberapa
class DAO yang digunakan untuk mengelola data pada database
menggunakan Hibernate.

Class DAO yang digunakan meliputi:

1. **PesananDAO:** Mengelola data pesanan pelanggan.
2. **PenggunaDAO:** Mengelola data pengguna dan proses autentikasi.
3. **LayananDAO:** Mengelola data layanan pengiriman.
4. **LokasiDAO:** Mengelola data lokasi operasional, cabang, dan agen.
5. **PembayaranDAO:** Mengelola data transaksi pembayaran.
6. **TarifWilayahDAO:** Mengelola tarif pengiriman berdasarkan wilayah.

Setiap class DAO memiliki fungsi pengelolaan data sesuai
dengan entitas masing-masing melalui Hibernate.


## 6. Perancangan Sistem

### Dokumentasi Perancangan Sistem

**1. Use Case Diagram RELAY**

Use Case Diagram menggambarkan interaksi antara pengguna dan sistem RELAY berdasarkan perannya, yaitu Pusat, Cabang, Agen, dan Pelanggan. Diagram ini menunjukkan fitur serta aktivitas yang dapat dilakukan oleh masing-masing pengguna sesuai dengan hak aksesnya.

<img width="1400" height="1582" alt="Use Case Kelompok 6 Relay drawio" src="https://github.com/user-attachments/assets/0ea8a596-a8bd-442e-8964-528e7a63ecb7" />












**2. Flowchart Menu Login**

Flowchart Menu Login menggambarkan alur proses autentikasi pengguna pada aplikasi RELAY. Proses dimulai dari pengisian username dan password, dilanjutkan dengan validasi akun. Jika data benar, sistem mengarahkan pengguna ke menu sesuai perannya, yaitu Pusat, Cabang, Agen, atau Pelanggan. Jika data tidak sesuai, sistem menampilkan notifikasi kegagalan login.

<img width="8820" height="13620" alt="Flowchart Kelompok 6 Relay-Menu Login drawio" src="https://github.com/user-attachments/assets/a0ac5e96-2c49-45ce-b63b-75a071d4c3cb" />







**3. Flowchart Menu Pelanggan**

Flowchart Menu Pelanggan menggambarkan alur aktivitas pelanggan dalam aplikasi RELAY. Pada menu ini, pelanggan dapat memilih empat fitur utama, yaitu membuat pesanan, melihat riwayat pesanan, mengelola akun, dan kembali ke menu login. Pada fitur membuat pesanan, pelanggan mengisi data pengiriman dan memilih layanan yang tersedia. Pada fitur riwayat pesanan, pelanggan dapat mencari informasi pesanan menggunakan nomor resi. Sementara itu, fitur kelola akun memungkinkan pelanggan memperbarui informasi akun dengan melalui proses validasi data. Setelah proses selesai, sistem menyimpan atau menampilkan informasi yang sesuai dan pengguna dapat kembali ke Menu Pelanggan.

<img width="915" height="2042" alt="Flowchart Kelompok 6 Relay-Menu Pelanggan jpg" src="https://github.com/user-attachments/assets/38e515c2-f399-4308-bc22-5ff6b1bd569c" />







**4. Flowchart Menu Agen**


Flowchart Menu Agen menggambarkan alur aktivitas Agen dalam mengelola data pesanan pelanggan. Agen dapat melihat data pesanan yang telah dibuat pelanggan, melengkapi informasi tambahan pada pesanan, serta menampilkan status pengiriman. Diagram ini menunjukkan tahapan proses yang berkaitan dengan kegiatan operasional Agen pada sistem RELAY.


<img width="11764" height="16384" alt="Flowchart Kelompok 6 Relay-Menu Agen drawio" src="https://github.com/user-attachments/assets/a2883e62-98dd-4a74-a082-f21fae5b745b" />





**5. ERD Logical RELAY**

ERD Logical menggambarkan rancangan konseptual basis data RELAY yang terdiri dari sembilan entitas utama, yaitu lokasi operasional, cabang, agen, pengguna, layanan, tarif wilayah, pesanan, pengiriman, dan pembayaran. Diagram ini memperlihatkan atribut serta hubungan antarentitas sebagai dasar pengembangan database aplikasi.

<img width="3147" height="3344" alt="ERDLogicalRelay" src="https://github.com/user-attachments/assets/1539867b-57f9-4300-8045-041e49131474" />






**6. ERD Relational RELAY**

ERD Relational menunjukkan implementasi rancangan basis data RELAY dalam bentuk tabel yang saling berhubungan. Diagram ini menggambarkan struktur atribut, tipe data, Primary Key (PK), Foreign Key (FK), serta relasi antartabel. Perancangan tersebut digunakan untuk mendukung pengelolaan dan menjaga keterkaitan data pada database MySQL.

<img width="6295" height="5160" alt="ERDRelationalRelay" src="https://github.com/user-attachments/assets/88f89137-0ed8-41aa-a801-6ddaeea5e947" />



Database yang digunakan bernama **`relay`** dan terdiri dari sembilan
tabel berikut.

| No. | Tabel                | Fungsi                                                                                        |
|-----|----------------------|-----------------------------------------------------------------------------------------------|
| 1   | `lokasi_operasional` | Menyimpan informasi umum lokasi operasional Cabang dan Agen.                                  |
| 2   | `cabang`             | Menyimpan informasi khusus kantor Cabang.                                                     |
| 3   | `agen`               | Menyimpan informasi khusus Agen dan hubungan Agen dengan Cabang induk.                        |
| 4   | `pengguna`           | Menyimpan akun, username, password, role, dan keterkaitan lokasi pengguna.                    |
| 5   | `layanan`            | Menyimpan pilihan layanan, tarif dasar, estimasi waktu, dan status layanan.                   |
| 6   | `tarif_wilayah`      | Menyimpan tarif pengiriman berdasarkan kota asal, kota tujuan, dan layanan.                   |
| 7   | `pesanan`            | Menyimpan data pengirim, penerima, jenis kiriman, berat, biaya, dan status pesanan.           |
| 8   | `pengiriman`         | Menyimpan nomor resi, tanggal, status pengiriman, bukti dokumentasi, dan keterkaitan pesanan. |
| 9   | `pembayaran`         | Menyimpan metode, jumlah, status, dan tanggal pembayaran.                                     |

### 6.1 Relasi dan Kunci

Basis data menggunakan primary key (PK), foreign key (FK), unique
constraint, dan composite key untuk menjaga keterkaitan serta integritas
data.

Salah satu penerapan supertype dan subtype adalah:

- `lokasi_operasional` sebagai supertype.
- `cabang` sebagai subtype.
- `agen` sebagai subtype.

Tabel `pesanan` menggunakan primary key gabungan (`id_pesanan`,
`pengguna_id_pengguna`). Tabel `pengiriman` menghubungkan pesanan dengan
tarif wilayah, Agen, dan Cabang. Tabel `pembayaran` terhubung dengan
data pengguna dan pengiriman.

### 6.2 Penerapan SQL

Pada laporan SBD, penerapan query mencakup:

- Data Definition Language (DDL): `CREATE` dan `ALTER`.
- Data Manipulation Language (DML): `INSERT` dan `UPDATE`.
- SELECT tingkat lanjut: `IN`, `NOT IN`, `EXISTS`, `NOT EXISTS`, `ANY`,
  `ALL`, `UNION`, `UNION ALL`, `GROUP BY`, `HAVING`, fungsi agregat,
  `LIKE`, dan `REGEXP`.
- Data Control Language (DCL): `GRANT` dan `REVOKE`.
- Transaction Control Language (TCL): `START TRANSACTION`, `SAVEPOINT`,
  `ROLLBACK`, dan `COMMIT`.
- JOIN tingkat lanjut: `INNER JOIN`, `LEFT JOIN`, `EQUI JOIN`,
  `NON-EQUI JOIN`, `SELF JOIN`, `NATURAL JOIN`, `SEMI JOIN`, dan
  `ANTI JOIN`.

## 7. Tech Stack (Teknologi dan Tools)

| Teknologi/Tools               | Kegunaan                                                               |
|-------------------------------|------------------------------------------------------------------------|
| Java 17                       | Bahasa pemrograman aplikasi.                                           |
| Java Swing                    | Pembuatan antarmuka desktop.                                           |
| Apache Maven                  | Pengelolaan project dan dependency.                                    |
| MySQL                         | Sistem manajemen basis data relasional.                                |
| Hibernate ORM 6.6.15.Final    | Pemetaan objek Java ke tabel database dan pengelolaan persistensi.     |
| Jakarta Persistence API 3.1.0 | Anotasi dan standar pemetaan entitas.                                  |
| MySQL Connector/J 9.2.0       | Konektor Java ke MySQL.                                                |
| NetBeans GUI Builder          | File form `.form` disertakan bersama source code untuk tampilan Swing. |

## 8. Persiapan dan Instalasi

Pastikan perangkat telah memiliki:

- JDK 17.
- MySQL Server 8.x.
- Apache Maven.
- IDE Java yang mendukung project Maven dan Java Swing (NetBeans dapat
  digunakan).

### 8.1 Menyiapkan Database

1.  Jalankan MySQL Server.
2.  Buat database dengan nama `relay`:

``` sql
CREATE DATABASE relay;
```

3.  Pilih database `relay`, kemudian impor file `relay.sql` yang
    tersedia pada repository.

Alternatif melalui terminal:

``` bash
mysql -u root -p relay < relay.sql
```

### 8.2 Mengatur Koneksi Database


**Dokumentasi Konfigurasi Hibernate dan MySQL**

File `hibernate.cfg.xml` digunakan untuk mengatur koneksi aplikasi RELAY dengan database MySQL serta pemetaan entitas Java melalui Hibernate.

**1. Konfigurasi Koneksi MySQL**

Screenshot berikut menunjukkan pengaturan JDBC Driver, URL database, username, password, dan dialect MySQL.

<img width="428" height="233" alt="image" src="https://github.com/user-attachments/assets/7d28e231-bbe2-47b5-b3be-8b163b086c9e" />


**2. Pengaturan Hibernate dan Pemetaan Entitas**

Screenshot berikut menunjukkan pengaturan Hibernate dan daftar class model yang dipetakan ke dalam database.

<img width="466" height="183" alt="image" src="https://github.com/user-attachments/assets/35832844-16fb-4662-9e5b-e4ab5d56a4a2" />



Ganti username dan password sesuai konfigurasi MySQL pada perangkat
masing-masing. Jangan mengunggah password pribadi atau kredensial
database yang digunakan pada lingkungan nyata ke repository publik.

Konfigurasi `hibernate.hbm2ddl.auto` pada project menggunakan nilai
`validate`. Artinya, tabel database harus sudah tersedia dan sesuai
dengan pemetaan entitas; Hibernate tidak membuat tabel secara otomatis.

### 8.3 Menjalankan Aplikasi

1.  Buka folder project `Relay-Kel6` sebagai project Maven di IDE.
2.  Pastikan dependency Maven berhasil dimuat.
3.  Pastikan MySQL aktif dan database `relay` sudah diimpor.
4.  Pastikan konfigurasi pada `hibernate.cfg.xml` sudah sesuai.
5.  Jalankan class utama:

``` text
main.Main
```

6.  Jika koneksi berhasil, aplikasi akan menampilkan halaman Login
    RELAY.

## 9. Alur Penggunaan Aplikasi


### Dokumentasi GUI Halaman Login

1. Buka aplikasi RELAY.
2. Pada halaman Login, masukkan username dan password sesuai akun yang dimiliki.
3. Klik tombol **Login** untuk masuk ke aplikasi.
4. Jika autentikasi berhasil, pengguna diarahkan ke dashboard sesuai perannya, yaitu Pelanggan, Agen, Pusat, atau Cabang.
5. Klik tombol **Registrasi** jika ingin membuat akun Pelanggan baru.
6. Klik tombol **Keluar** untuk menutup aplikasi.

**Dokumentasi GUI Halaman Login RELAY**

Halaman Login merupakan tampilan awal aplikasi RELAY yang digunakan untuk melakukan autentikasi pengguna. Halaman ini menyediakan kolom Username dan Password serta tombol Login, Registrasi, dan Keluar. Setelah berhasil login, pengguna akan diarahkan ke halaman utama berdasarkan peran akun masing-masing.

<img width="741" height="386" alt="Screenshot 2026-11-07 140118" src="https://github.com/user-attachments/assets/28d4d1dc-8245-419e-80a2-843462006e1a" />



### 9.1 Pelanggan

1.  Buka aplikasi RELAY.
2.  Pilih menu registrasi untuk membuat akun pelanggan jika belum
    memiliki akun.
3.  Login menggunakan username dan password.
4.  Masuk ke dashboard Pelanggan.
5.  Pilih menu **Buat Pesanan**.
6.  Isi informasi pengirim, penerima, jenis kiriman, kota tujuan, dan
    layanan.
7.  Simpan pesanan.
8.  Buka menu **Riwayat Pesanan** untuk melihat data pesanan.
9.  Gunakan menu **Kelola Akun** untuk memperbarui informasi akun.

 **Dokumentasi GUI Dashboard Pelanggan**

Dashboard Pelanggan merupakan halaman utama yang ditampilkan setelah pelanggan berhasil login ke aplikasi RELAY. Halaman ini menampilkan ringkasan jumlah pesanan berdasarkan status pengiriman, yaitu Belum Dikirim, Dalam Perjalanan, dan Tiba di Tujuan.

Dashboard juga menyediakan akses cepat ke fitur Kelola Akun, Pesanan, dan Riwayat Pesanan. Pada bagian Pesanan Terbaru, pelanggan dapat melihat informasi pesanan, agen asal, agen tujuan, tanggal, dan status pengiriman.

<img width="779" height="473" alt="Screenshot 2026-11-07 141320" src="https://github.com/user-attachments/assets/1d149e59-1943-4229-b3ca-8a336a683258" />





### 9.2 Agen
1. Buka aplikasi RELAY.
2. Login menggunakan akun dengan role Agen.
3. Masuk ke dashboard Agen.
4. Melihat daftar pesanan yang telah dibuat oleh pelanggan.
5. Memilih pesanan pelanggan yang perlu diproses.
6. Melengkapi data tambahan pesanan melalui formulir Agen.
7. Menyimpan data pesanan yang telah dilengkapi.
8. Melihat informasi status pengiriman yang tersedia pada aplikasi.

**Dokumentasi GUI Dashboard Agen**

Dashboard Agen merupakan halaman utama yang ditampilkan setelah agen berhasil login ke aplikasi RELAY. Halaman ini menampilkan informasi identitas agen, wilayah kecamatan, jumlah total pesanan, jumlah barang diterima, jumlah pesanan dalam perjalanan, dan jumlah pesanan yang menunggu verifikasi.

Pada halaman ini juga tersedia menu utama, yaitu Dashboard, Kelola Pesanan, dan Terima Barang Cabang. Selain itu, agen dapat melihat daftar data pesanan pelanggan yang sedang dikelola serta menggunakan fitur pencarian untuk menemukan data pesanan tertentu.

<img width="816" height="473" alt="Screenshot 2026-11-07 141650" src="https://github.com/user-attachments/assets/3b56cf08-80b8-4a24-8aa1-b6182ac555aa" />




### 9.3 Pusat

### Dokumentasi GUI Menu Pusat

Menu Pusat merupakan halaman utama aplikasi RELAY yang digunakan oleh pengguna dengan peran Pusat untuk mengakses fitur pengelolaan dan pemantauan operasional pengiriman. Pada halaman ini terdapat tiga menu utama, yaitu Dashboard, Laporan Bulanan, dan Registrasi Internal.

**Fungsi setiap menu:**

1. **Dashboard (Lihat Dashboard):** Digunakan untuk membuka halaman ringkasan kinerja operasional perusahaan sehingga pengguna Pusat dapat memantau aktivitas pengiriman.

2. **Laporan Bulanan (Lihat Laporan):** Digunakan untuk mengakses informasi laporan bulanan yang berkaitan dengan jumlah pengiriman dan status pengiriman barang.

3. **Registrasi Internal (Daftarkan Akun):** Digunakan untuk membuka formulir pendaftaran akun petugas operasional, seperti petugas Cabang dan Agen. Pengguna dapat mengisi nama lengkap, username, password, konfirmasi password, memilih peran akun, serta memasukkan informasi Cabang atau Agen.

4. **Kembali:** Digunakan untuk kembali ke halaman sebelumnya.

Melalui menu-menu tersebut, pengguna Pusat dapat mengakses informasi operasional dan melakukan pendaftaran akun internal sesuai kebutuhan aplikasi RELAY.

**Screenshot GUI Menu Pusat:**

<img width="1562" height="1006" alt="WhatsApp Image 2026-11-07 at 22 07 17" src="https://github.com/user-attachments/assets/25fde620-3425-478e-a5da-f330c24eade9" />





### 9.4 Cabang

1. Buka aplikasi RELAY.
2. Login menggunakan username dan password akun dengan role Cabang.
3. Masuk ke Dashboard Cabang.
4. Pilih menu **Pengiriman ke Cabang** untuk mengelola jadwal pengiriman menuju cabang penerima.
5. Pilih menu **Terima Barang Cabang** untuk mengelola penerimaan barang dari cabang pengirim.
6. Pilih menu **Kelola Pengiriman ke Agen** untuk mengatur pengiriman menuju agen penerima.
7. Gunakan kolom pencarian untuk mencari data pengiriman berdasarkan nomor resi.
8. Klik **Keluar** untuk meninggalkan Dashboard Cabang.

**Dokumentasi GUI Dashboard Cabang**

Dashboard Cabang merupakan halaman utama yang ditampilkan setelah pengguna dengan role Cabang berhasil login ke aplikasi RELAY. Halaman ini menampilkan identitas cabang, lokasi kota, serta ringkasan informasi pengiriman, meliputi total pengiriman, barang diterima cabang, pengiriman dalam perjalanan, dan pengiriman yang telah dijadwalkan.

Dashboard Cabang menyediakan menu Pengiriman ke Cabang, Terima Barang Cabang, dan Kelola Pengiriman ke Agen. Selain itu, terdapat tabel Data Pengiriman yang menampilkan nomor resi, ID pesanan, cabang pengirim, serta cabang tujuan. Fitur pencarian juga disediakan untuk mempermudah pencarian data pengiriman.

<img width="786" height="473" alt="Screenshot 2026-11-07 142011" src="https://github.com/user-attachments/assets/4e6a0def-060b-498f-a57e-ee1a19477267" />





   
## 10. Catatan Implementasi

- Aplikasi RELAY menyediakan antarmuka untuk empat peran pengguna, yaitu Pelanggan, Agen, Pusat, dan Cabang.
- Dashboard masing-masing peran telah dapat ditampilkan melalui aplikasi desktop berbasis Java Swing.
- Sistem menggunakan Hibernate untuk menghubungkan aplikasi dengan database MySQL.
- Beberapa fitur dan proses transaksi masih memerlukan pengujian lebih lanjut untuk memastikan kesesuaian antara alur aplikasi, controller, dan struktur database.
- Pengujian transaksi pengiriman perlu memperhatikan kesesuaian nilai status pengiriman dengan tipe data ENUM pada tabel `pengiriman` di MySQL.


## 11. Keamanan dan Penggunaan Data

File SQL yang disertakan memuat data contoh pengguna untuk kebutuhan
pengujian. Ganti kredensial contoh sebelum digunakan di lingkungan
nyata. Untuk pengembangan lebih lanjut, penyimpanan password sebaiknya
menggunakan mekanisme hashing dan bukan teks biasa.

------------------------------------------------------------------------

**RELAY – Your Delivery Partner!**  
Project Kelompok 6  
Program Studi Sistem Informasi, Fakultas Teknik  
Universitas Mulawarman – 2026
