# Tugas-Akhir-Pemrograman2
Project Akhir Pemrograman 2 - Aplikasi Manajemen Bengkel Motor

**Judul Proyek:** RANCANG BANGUN APLIKASI MANAJEMEN BENGKEL MOTOR BERBASIS JAVA SWING DAN MYSQL

* **Nama:** Moh. Torik
* **NIM:** 231011402871
* **Objek/Domain:** Bengkel Motor (Torik Jaya Motor)

---

## 1. Antarmuka Form Login Bengkel
Halaman autentikasi untuk membatasi hak akses sistem. Gunakan kredensial berikut untuk masuk ke dalam sistem:
* **Username:** `admin`
* **Password:** `bengkel123`

<img width="387" height="508" alt="Form Login" src="https://github.com/user-attachments/assets/2cacdcbe-e14f-4d12-9e62-2b94b0cf3c0a" />

---

## 2. Menu Pelanggan (Manajemen Antrean)
Setelah berhasil login, pengguna akan diarahkan ke Form Pelanggan untuk memasukkan data antrean awal. Antarmuka ini menerapkan arsitektur *Sidebar Menu Layout* di sisi kiri untuk navigasi antar-form. 

Panel utama diisi kontainer berwarna ungu dengan komponen input teks, serta menu drop-down (`JComboBox`) Kerusakan yang datanya ditarik dinamis dari persediaan suku cadang. Di bagian bawah, terdapat tabel antrean (`JTable`) penampung data pelanggan yang belum melakukan proses pembayaran.

<img width="827" height="437" alt="Form Pelanggan" src="https://github.com/user-attachments/assets/dfba8587-e143-488d-9670-3b0cc9b765f3" />

---

## 3. Menu Sparepart (Manajemen Inventaris)
Form ini berfungsi untuk memproses data inventaris suku cadang secara permanen (*Data Persistence CRUD*). Antarmuka didominasi warna marun dan dilengkapi barisan tombol aksi (Simpan, Edit, Hapus, Reset) serta kolom pencarian cepat yang responsif terhadap ketikan.

<img width="827" height="428" alt="Form Sparepart" src="https://github.com/user-attachments/assets/776b5a3d-befb-48e0-a02a-ac9f212389d1" />

---

## 4. Menu Pembayaran (Kalkulator Kasir)
Antarmuka kalkulator transaksional berwarna jingga yang memisahkan area input data dan area kalkulasi secara visual. Sisi kanan atas didominasi oleh panel *Total Tagihan* berukuran besar berwarna abu-abu untuk meningkatkan keterbacaan kasir, disertai textfield input tunai dan textfield kembalian yang dihitung secara *realtime*.

<img width="827" height="437" alt="Form Pembayaran" src="https://github.com/user-attachments/assets/044aa17f-a6a2-47f8-943c-41d6aa87c613" />

---

## 5. Menu Transaksi (Riwayat Keuangan)
Menampilkan halaman arsip seluruh data pembayaran yang telah berstatus lunas. Data ditampilkan ke dalam tabel berlatar hijau yang tersusun secara rapi dan terurut secara *ascending* (berdasarkan urutan ID transaksi terkecil ke terbesar).

<img width="827" height="426" alt="Form Transaksi" src="https://github.com/user-attachments/assets/d246a1e2-c1b6-4103-865e-be9da22be76f" />

---

## 6. Dialog Pop-up Preview Struk Kasir
Menampilkan kotak dialog pracetak (*live preview*) nota belanja digital dengan jenis huruf *monospaced* lurus (`Courier New`) untuk memastikan susunan karakter pembatas nota (`-`, `=`, `|`) sejajar sempurna sebelum dokumen fisik diproses oleh printer atau dicetak menjadi berkas PDF.

<img width="827" height="438" alt="Preview Struk" src="https://github.com/user-attachments/assets/18623f08-b27d-4171-81d7-1de6de987d34" />

---

## 7. Dialog Pop-up Preview Rekap Laporan Penjualan Harian
Menampilkan rangkuman akuntansi berkas pendapatan total operasional bengkel secara menyeluruh. Kotak dialog ini menghitung akumulasi otomatis untuk total penjualan suku cadang, total pendapatan jasa servis mekanik, hingga kalkulasi *Grand Total Omzet Harian*. Lembaran pratinjau ini dirancang untuk dicetak dalam orientasi halaman Lanskap (*Landscape*).

<img width="827" height="437" alt="Preview Laporan" src="https://github.com/user-attachments/assets/da5996b8-c881-406e-9c50-a66c830c09bc" />
