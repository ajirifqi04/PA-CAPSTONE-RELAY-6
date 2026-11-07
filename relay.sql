-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 02, 2026 at 07:42 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `relay`
--

-- --------------------------------------------------------

--
-- Table structure for table `agen`
--

CREATE TABLE `agen` (
  `id_lokasi` int NOT NULL,
  `nama_admin_agen` varchar(100) NOT NULL,
  `kode_agen` varchar(20) NOT NULL,
  `status_operasional` enum('Aktif','Nonaktif') NOT NULL DEFAULT 'Aktif',
  `cabang_id_lokasi` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `agen`
--

INSERT INTO `agen` (`id_lokasi`, `nama_admin_agen`, `kode_agen`, `status_operasional`, `cabang_id_lokasi`) VALUES
(1101, 'Muhammad Rizky Saputra', 'AGN-SMD-001', 'Aktif', 1001),
(1102, 'Aulia Rahma Putri', 'AGN-SMD-002', 'Aktif', 1001),
(1103, 'Dimas Aditya Pratama', 'AGN-SMD-003', 'Aktif', 1001),
(1104, 'Nabila Zahra Ramadhani', 'AGN-BPN-001', 'Aktif', 1002),
(1105, 'Rizal Maulana Putra', 'AGN-BPN-002', 'Aktif', 1002),
(1106, 'Salsabila Nur Azizah', 'AGN-BPN-003', 'Aktif', 1002),
(1107, 'Ahmad Fauzan Hidayat', 'AGN-BTG-001', 'Aktif', 1003),
(1108, 'Putri Amelia Sari', 'AGN-BTG-002', 'Aktif', 1003),
(1109, 'Fikri Ramadhan', 'AGN-BTG-003', 'Aktif', 1003),
(1110, 'Naufal Ardiansyah', 'AGN-TGR-001', 'Aktif', 1004),
(1111, 'Intan Permata Sari', 'AGN-TGR-002', 'Aktif', 1004),
(1112, 'Bagas Pratama Wijaya', 'AGN-TGR-003', 'Aktif', 1004),
(1113, 'Farhan Akbar Maulana', 'AGN-SGT-001', 'Aktif', 1005),
(1114, 'Nadia Safitri', 'AGN-SGT-002', 'Aktif', 1005),
(1115, 'Reza Fahlevi', 'AGN-SGT-003', 'Aktif', 1005);

-- --------------------------------------------------------

--
-- Table structure for table `cabang`
--

CREATE TABLE `cabang` (
  `id_lokasi` int NOT NULL,
  `kode_cabang` varchar(20) NOT NULL,
  `nama_admin_cabang` varchar(100) NOT NULL,
  `status_operasional` enum('Aktif','Nonaktif') NOT NULL DEFAULT 'Aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cabang`
--

INSERT INTO `cabang` (`id_lokasi`, `kode_cabang`, `nama_admin_cabang`, `status_operasional`) VALUES
(1001, 'CBG-SMD-001', 'Andi Pratama', 'Aktif'),
(1002, 'CBG-BPN-001', 'Rizky Ramadhan', 'Aktif'),
(1003, 'CBG-BTG-001', 'Dinda Maharani', 'Aktif'),
(1004, 'CBG-TGR-001', 'Fajar Maulana', 'Aktif'),
(1005, 'CBG-SGT-001', 'Nadia Putri', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `layanan`
--

CREATE TABLE `layanan` (
  `id_layanan` int NOT NULL,
  `nama_layanan` enum('REFast','REStandard','RECheap') NOT NULL,
  `tarif_dasar` decimal(12,2) NOT NULL,
  `estimasi_hari` varchar(10) NOT NULL,
  `status_aktif` enum('Aktif','Nonaktif') NOT NULL DEFAULT 'Aktif'
) ;

--
-- Dumping data for table `layanan`
--

INSERT INTO `layanan` (`id_layanan`, `nama_layanan`, `tarif_dasar`, `estimasi_hari`, `status_aktif`) VALUES
(1, 'REFast', '30000.00', '1-3 hari', 'Aktif'),
(2, 'REStandard', '20000.00', '4-7 hari', 'Aktif'),
(3, 'RECheap', '15000.00', '7-9 hari', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `lokasi_operasional`
--

CREATE TABLE `lokasi_operasional` (
  `id_lokasi` int NOT NULL,
  `nama_lokasi` varchar(100) NOT NULL,
  `jenis_lokasi` enum('Cabang','Agen') NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `kota` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `lokasi_operasional`
--

INSERT INTO `lokasi_operasional` (`id_lokasi`, `nama_lokasi`, `jenis_lokasi`, `alamat`, `kota`) VALUES
(1001, 'Relay Cabang Samarinda', 'Cabang', 'Jl. Gajah Mada, Samarinda', 'Samarinda'),
(1002, 'Relay Cabang Balikpapan', 'Cabang', 'Jl. Jenderal Sudirman, Balikpapan', 'Balikpapan'),
(1003, 'Relay Cabang Bontang', 'Cabang', 'Jl. R. Soeprapto, Bontang', 'Bontang'),
(1004, 'Relay Cabang Tenggarong', 'Cabang', 'Jl. Tepian Pandan, Tenggarong', 'Tenggarong'),
(1005, 'Relay Cabang Sangatta', 'Cabang', 'Jl. Yos Sudarso I, Sangatta Utara', 'Sangatta'),
(1101, 'Relay Agen Sungai Kunjang', 'Agen', 'Jl. Cendana, Sungai Kunjang, Samarinda', 'Samarinda'),
(1102, 'Relay Agen Samarinda Ulu', 'Agen', 'Jl. M. Yamin, Samarinda Ulu', 'Samarinda'),
(1103, 'Relay Agen Samarinda Seberang', 'Agen', 'Jl. Bung Tomo, Samarinda Seberang', 'Samarinda'),
(1104, 'Relay Agen Sepinggan', 'Agen', 'Jl. Syarifuddin Yoes, Balikpapan Selatan', 'Balikpapan'),
(1105, 'Relay Agen Klandasan', 'Agen', 'Jl. Jenderal Sudirman, Klandasan, Balikpapan', 'Balikpapan'),
(1106, 'Relay Agen Gunung Bahagia', 'Agen', 'Jl. Ruhui Rahayu, Gunung Bahagia, Balikpapan', 'Balikpapan'),
(1107, 'Relay Agen Bontang Utara', 'Agen', 'Jl. Awang Long, Bontang Utara', 'Bontang'),
(1108, 'Relay Agen Bontang Selatan', 'Agen', 'Jl. Selat Karimata I, Bontang Selatan', 'Bontang'),
(1109, 'Relay Agen Bontang Barat', 'Agen', 'Jl. Perjuangan, Gunung Telihan, Bontang Barat', 'Bontang'),
(1110, 'Relay Agen Timbau', 'Agen', 'Jl. Naga, Timbau, Tenggarong', 'Tenggarong'),
(1111, 'Relay Agen Mangkurawang', 'Agen', 'Jl. Achmad Dahlan, Mangkurawang, Tenggarong', 'Tenggarong'),
(1112, 'Relay Agen Loa Kulu', 'Agen', 'Jl. Bhayangkara, Loa Kulu, Kutai Kartanegara', 'Tenggarong'),
(1113, 'Relay Agen Sangatta Utara', 'Agen', 'Jl. Yos Sudarso II, Sangatta Utara', 'Sangatta'),
(1114, 'Relay Agen Sangatta Selatan', 'Agen', 'Jl. Kampung Baru, Sangatta Selatan', 'Sangatta'),
(1115, 'Relay Agen Teluk Lingga', 'Agen', 'Jl. Yos Sudarso III, Teluk Lingga, Sangatta Utara', 'Sangatta');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` int NOT NULL,
  `metode_pembayaran` enum('Cash','QRIS','Transfer') NOT NULL,
  `jumlah_bayar` decimal(12,2) NOT NULL,
  `status_pembayaran` enum('Lunas','Belum Lunas') NOT NULL DEFAULT 'Lunas',
  `tanggal_pembayaran` datetime NOT NULL,
  `pengguna_id_pengguna` int NOT NULL,
  `pengiriman_nomor_resi` varchar(30) NOT NULL
) ;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `metode_pembayaran`, `jumlah_bayar`, `status_pembayaran`, `tanggal_pembayaran`, `pengguna_id_pengguna`, `pengiriman_nomor_resi`) VALUES
(5001, 'Cash', '37500.00', 'Lunas', '2026-10-27 09:30:00', 2101, 'RLY20261027-0001'),
(5002, 'QRIS', '32500.00', 'Lunas', '2026-10-27 10:15:00', 2102, 'RLY20261027-0002'),
(5003, 'Transfer', '25000.00', 'Lunas', '2026-10-27 11:00:00', 2103, 'RLY20261027-0003'),
(5004, 'Cash', '72625.00', 'Lunas', '2026-10-27 11:30:00', 2104, 'RLY20261027-0004'),
(5005, 'QRIS', '62000.00', 'Lunas', '2026-10-27 12:15:00', 2105, 'RLY20261027-0005'),
(5006, 'Transfer', '42500.00', 'Lunas', '2026-10-27 13:00:00', 2106, 'RLY20261027-0006'),
(5007, 'Cash', '118000.00', 'Lunas', '2026-10-27 14:00:00', 2107, 'RLY20261027-0007'),
(5008, 'QRIS', '88750.00', 'Lunas', '2026-10-27 14:30:00', 2108, 'RLY20261027-0008'),
(5009, 'Transfer', '57000.00', 'Lunas', '2026-10-27 15:00:00', 2109, 'RLY20261027-0009'),
(5010, 'Cash', '190875.00', 'Lunas', '2026-10-27 15:30:00', 2110, 'RLY20261027-0010'),
(5011, 'QRIS', '135000.00', 'Lunas', '2026-10-27 16:00:00', 2111, 'RLY20261027-0011'),
(5012, 'Transfer', '96250.00', 'Lunas', '2026-10-27 16:30:00', 2112, 'RLY20261027-0012'),
(5013, 'Cash', '60600.00', 'Lunas', '2026-10-27 17:00:00', 2113, 'RLY20261027-0013'),
(5014, 'QRIS', '60800.00', 'Lunas', '2026-10-27 18:00:00', 2114, 'RLY20261027-0014'),
(5015, 'Transfer', '72400.00', 'Lunas', '2026-10-27 19:00:00', 2115, 'RLY20261027-0015');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int NOT NULL,
  `nama_pengguna` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Pusat','Cabang','Agen','Pelanggan') NOT NULL,
  `lokasi_operasional_id_lokasi` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_pengguna`, `username`, `password`, `role`, `lokasi_operasional_id_lokasi`) VALUES
(2001, 'Pusat Relay', 'pusat_relay', 'Relay2026!', 'Pusat', NULL),
(2011, 'Andi Pratama', 'andi_smd', 'Relay2026!', 'Cabang', 1001),
(2012, 'Rizky Ramadhan', 'rizky_bpn', 'Relay2026!', 'Cabang', 1002),
(2013, 'Dinda Maharani', 'dinda_btg', 'Relay2026!', 'Cabang', 1003),
(2014, 'Fajar Maulana', 'fajar_tgr', 'Relay2026!', 'Cabang', 1004),
(2015, 'Nadia Putri', 'nadia_sgt', 'Relay2026!', 'Cabang', 1005),
(2101, 'Muhammad Rizky Saputra', 'rizky_smd01', 'Relay2026!', 'Agen', 1101),
(2102, 'Aulia Rahma Putri', 'aulia_smd02', 'Relay2026!', 'Agen', 1102),
(2103, 'Dimas Aditya Pratama', 'dimas_smd03', 'Relay2026!', 'Agen', 1103),
(2104, 'Nabila Zahra Ramadhani', 'nabila_bpn01', 'Relay2026!', 'Agen', 1104),
(2105, 'Rizal Maulana Putra', 'rizal_bpn02', 'Relay2026!', 'Agen', 1105),
(2106, 'Salsabila Nur Azizah', 'salsabila_bpn03', 'Relay2026!', 'Agen', 1106),
(2107, 'Ahmad Fauzan Hidayat', 'fauzan_btg01', 'Relay2026!', 'Agen', 1107),
(2108, 'Putri Amelia Sari', 'putri_btg02', 'Relay2026!', 'Agen', 1108),
(2109, 'Fikri Ramadhan', 'fikri_btg03', 'Relay2026!', 'Agen', 1109),
(2110, 'Naufal Ardiansyah', 'naufal_tgr01', 'Relay2026!', 'Agen', 1110),
(2111, 'Intan Permata Sari', 'intan_tgr02', 'Relay2026!', 'Agen', 1111),
(2112, 'Bagas Pratama Wijaya', 'bagas_tgr03', 'Relay2026!', 'Agen', 1112),
(2113, 'Farhan Akbar Maulana', 'farhan_sgt01', 'Relay2026!', 'Agen', 1113),
(2114, 'Nadia Safitri', 'nadias_sgt02', 'Relay2026!', 'Agen', 1114),
(2115, 'Reza Fahlevi', 'reza_sgt03', 'Relay2026!', 'Agen', 1115),
(2201, 'Alya Putri', 'alya_putri', 'Relay2026!', 'Pelanggan', NULL),
(2202, 'Bagas Pratama', 'bagas_pratama', 'Relay2026!', 'Pelanggan', NULL),
(2203, 'Citra Maharani', 'citra_maharani', 'Relay2026!', 'Pelanggan', NULL),
(2204, 'Dimas Saputra', 'dimas_saputra', 'Relay2026!', 'Pelanggan', NULL),
(2205, 'Eka Wulandari', 'eka_wulandari', 'Relay2026!', 'Pelanggan', NULL),
(2206, 'Farhan Ramadhan', 'farhan_ramadhan', 'Relay2026!', 'Pelanggan', NULL),
(2207, 'Gina Amelia', 'gina_amelia', 'Relay2026!', 'Pelanggan', NULL),
(2208, 'Hadi Wijaya', 'hadi_wijaya', 'Relay2026!', 'Pelanggan', NULL),
(2209, 'Intan Permata', 'intan_permata', 'Relay2026!', 'Pelanggan', NULL),
(2210, 'Joko Firmansyah', 'joko_firmansyah', 'Relay2026!', 'Pelanggan', NULL),
(2211, 'Kirana Azzahra', 'kirana_azzahra', 'Relay2026!', 'Pelanggan', NULL),
(2212, 'Lukman Hakim', 'lukman_hakim', 'Relay2026!', 'Pelanggan', NULL),
(2213, 'Maya Anggraini', 'maya_anggraini', 'Relay2026!', 'Pelanggan', NULL),
(2214, 'Naufal Rizki', 'naufal_rizki', 'Relay2026!', 'Pelanggan', NULL),
(2215, 'Olivia Rahma', 'olivia_rahma', 'Relay2026!', 'Pelanggan', NULL),
(2216, 'Putra Mahendra', 'putra_mahendra', 'Relay2026!', 'Pelanggan', NULL),
(2217, 'Qiana Safira', 'qiana_safira', 'Relay2026!', 'Pelanggan', NULL),
(2218, 'Rafi Prakoso', 'rafi_prakoso', 'Relay2026!', 'Pelanggan', NULL),
(2219, 'Salsa Nabila', 'salsa_nabila', 'Relay2026!', 'Pelanggan', NULL),
(2220, 'Tegar Maulana', 'tegar_maulana', 'Relay2026!', 'Pelanggan', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pengiriman`
--

CREATE TABLE `pengiriman` (
  `nomor_resi` varchar(30) NOT NULL,
  `tanggal_pengiriman` datetime DEFAULT NULL,
  `tanggal_diterima` datetime DEFAULT NULL,
  `status_pengiriman` enum('Dijadwalkan','Dalam Perjalanan','Sudah Sampai Tujuan') NOT NULL DEFAULT 'Dijadwalkan',
  `bukti_dokumentasi` varchar(255) DEFAULT NULL,
  `tarif_wilayah_id_tarif` int NOT NULL,
  `agen_id_lokasi` int NOT NULL,
  `cabang_id_lokasi` int NOT NULL,
  `pesanan_id_pesanan` int NOT NULL,
  `pesanan_pengguna_id_pengguna` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pengiriman`
--

INSERT INTO `pengiriman` (`nomor_resi`, `tanggal_pengiriman`, `tanggal_diterima`, `status_pengiriman`, `bukti_dokumentasi`, `tarif_wilayah_id_tarif`, `agen_id_lokasi`, `cabang_id_lokasi`, `pesanan_id_pesanan`, `pesanan_pengguna_id_pengguna`) VALUES
('RLY20261027-0001', '2026-10-27 18:00:00', '2026-10-28 10:00:00', 'Sudah Sampai Tujuan', 'bukti_penerimaan_0001.jpg', 3001, 1101, 1002, 4001, 2201),
('RLY20261027-0002', '2026-10-27 22:00:00', NULL, 'Dalam Perjalanan', NULL, 3002, 1102, 1002, 4002, 2202),
('RLY20261027-0003', '2026-10-27 22:00:00', NULL, 'Dalam Perjalanan', NULL, 3003, 1103, 1002, 4003, 2203),
('RLY20261027-0004', '2026-10-27 19:00:00', '2026-10-28 11:00:00', 'Sudah Sampai Tujuan', 'bukti_penerimaan_0004.jpg', 3004, 1104, 1003, 4004, 2204),
('RLY20261027-0005', '2026-10-27 22:00:00', NULL, 'Dalam Perjalanan', NULL, 3005, 1105, 1003, 4005, 2205),
('RLY20261027-0006', '2026-10-27 22:00:00', NULL, 'Dalam Perjalanan', NULL, 3006, 1106, 1003, 4006, 2206),
('RLY20261027-0007', '2026-10-28 09:00:00', NULL, 'Dalam Perjalanan', NULL, 3007, 1107, 1004, 4007, 2207),
('RLY20261027-0008', '2026-10-27 22:00:00', NULL, 'Dalam Perjalanan', NULL, 3008, 1108, 1004, 4008, 2208),
('RLY20261027-0009', '2026-10-27 22:00:00', NULL, 'Dalam Perjalanan', NULL, 3009, 1109, 1004, 4009, 2209),
('RLY20261027-0010', '2026-10-27 22:00:00', NULL, 'Dalam Perjalanan', NULL, 3010, 1110, 1005, 4010, 2210),
('RLY20261027-0011', NULL, NULL, 'Dijadwalkan', NULL, 3011, 1111, 1005, 4011, 2211),
('RLY20261027-0012', NULL, NULL, 'Dijadwalkan', NULL, 3012, 1112, 1005, 4012, 2212),
('RLY20261027-0013', NULL, NULL, 'Dijadwalkan', NULL, 3013, 1113, 1001, 4013, 2213),
('RLY20261027-0014', NULL, NULL, 'Dijadwalkan', NULL, 3014, 1114, 1001, 4014, 2214),
('RLY20261027-0015', NULL, NULL, 'Dijadwalkan', NULL, 3015, 1115, 1001, 4015, 2215);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `id_pesanan` int NOT NULL,
  `nama_pengirim` varchar(100) NOT NULL,
  `no_telepon_pengirim` varchar(20) NOT NULL,
  `alamat_pengirim` varchar(255) NOT NULL,
  `email_pengirim` varchar(100) NOT NULL,
  `nama_penerima` varchar(100) NOT NULL,
  `telepon_penerima` varchar(20) NOT NULL,
  `alamat_tujuan` varchar(255) NOT NULL,
  `kota_tujuan` varchar(100) NOT NULL,
  `jenis_kiriman` enum('Surat','Dokumen','Paket') NOT NULL,
  `berat_barang` decimal(8,2) DEFAULT NULL,
  `total_biaya` decimal(12,2) DEFAULT NULL,
  `tanggal_pesanan` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status_pesanan` enum('Menunggu','Diproses','Dibatalkan','Selesai') NOT NULL DEFAULT 'Diproses',
  `layanan_id_layanan` int NOT NULL,
  `pengguna_id_pengguna` int NOT NULL
) ;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id_pesanan`, `nama_pengirim`, `no_telepon_pengirim`, `alamat_pengirim`, `email_pengirim`, `nama_penerima`, `telepon_penerima`, `alamat_tujuan`, `kota_tujuan`, `jenis_kiriman`, `berat_barang`, `total_biaya`, `tanggal_pesanan`, `status_pesanan`, `layanan_id_layanan`, `pengguna_id_pengguna`) VALUES
(4001, 'Alya Putri', '081234560001', 'Jl. Gajah Mada, Samarinda', 'alya.putri@example.com', 'Nabila Safitri', '082234570001', 'Jl. Jenderal Sudirman, Balikpapan', 'Balikpapan', 'Paket', '0.50', '37500.00', '2026-10-27 08:15:00', 'Selesai', 1, 2201),
(4002, 'Bagas Pratama', '081234560002', 'Jl. Cendana, Samarinda', 'bagas.pratama@example.com', 'Raka Pratama', '082234570002', 'Jl. Syarifuddin Yoes, Balikpapan', 'Balikpapan', 'Dokumen', '1.25', '32500.00', '2026-10-27 09:05:00', 'Selesai', 2, 2202),
(4003, 'Citra Maharani', '081234560003', 'Jl. M. Yamin, Samarinda', 'citra.maharani@example.com', 'Salsabila Putri', '082234570003', 'Jl. Ruhui Rahayu, Balikpapan', 'Balikpapan', 'Surat', '2.00', '25000.00', '2026-10-27 09:45:00', 'Selesai', 3, 2203),
(4004, 'Dimas Saputra', '081234560004', 'Jl. Jenderal Sudirman, Balikpapan', 'dimas.saputra@example.com', 'Yoga Firmansyah', '082234570004', 'Jl. R. Soeprapto, Bontang', 'Bontang', 'Paket', '2.75', '72625.00', '2026-10-27 10:20:00', 'Selesai', 1, 2204),
(4005, 'Eka Wulandari', '081234560005', 'Jl. Syarifuddin Yoes, Balikpapan', 'eka.wulandari@example.com', 'Tania Larasati', '082234570005', 'Jl. Awang Long, Bontang', 'Bontang', 'Dokumen', '4.00', '62000.00', '2026-10-27 11:10:00', 'Selesai', 2, 2205),
(4006, 'Farhan Ramadhan', '081234560006', 'Jl. Ruhui Rahayu, Balikpapan', 'farhan.ramadhan@example.com', 'Beni Kurniawan', '082234570006', 'Jl. Selat Karimata I, Bontang', 'Bontang', 'Surat', '5.00', '42500.00', '2026-10-27 11:55:00', 'Selesai', 3, 2206),
(4007, 'Gina Amelia', '081234560007', 'Jl. R. Soeprapto, Bontang', 'gina.amelia@example.com', 'Lita Nuraini', '082234570007', 'Jl. Tepian Pandan, Tenggarong', 'Tenggarong', 'Paket', '5.50', '118000.00', '2026-10-27 12:35:00', 'Selesai', 1, 2207),
(4008, 'Hadi Wijaya', '081234560008', 'Jl. Awang Long, Bontang', 'hadi.wijaya@example.com', 'Arif Mahendra', '082234570008', 'Jl. Naga, Tenggarong', 'Tenggarong', 'Dokumen', '6.25', '88750.00', '2026-10-27 13:05:00', 'Selesai', 2, 2208),
(4009, 'Intan Permata', '081234560009', 'Jl. Perjuangan, Bontang', 'intan.permata@example.com', 'Vania Puspita', '082234570009', 'Jl. Achmad Dahlan, Tenggarong', 'Tenggarong', 'Surat', '7.00', '57000.00', '2026-10-27 13:50:00', 'Selesai', 3, 2209),
(4010, 'Joko Firmansyah', '081234560010', 'Jl. Tepian Pandan, Tenggarong', 'joko.firmansyah@example.com', 'Fikri Maulana', '082234570010', 'Jl. Yos Sudarso I, Sangatta', 'Sangatta', 'Paket', '9.75', '190875.00', '2026-10-27 14:25:00', 'Selesai', 1, 2210),
(4011, 'Kirana Azzahra', '081234560011', 'Jl. Naga, Tenggarong', 'kirana.azzahra@example.com', 'Rina Oktaviani', '082234570011', 'Jl. Yos Sudarso II, Sangatta', 'Sangatta', 'Dokumen', '10.00', '135000.00', '2026-10-27 15:10:00', 'Selesai', 2, 2211),
(4012, 'Lukman Hakim', '081234560012', 'Jl. Achmad Dahlan, Tenggarong', 'lukman.hakim@example.com', 'Zidan Prakoso', '082234570012', 'Jl. Yos Sudarso III, Sangatta', 'Sangatta', 'Surat', '12.50', '96250.00', '2026-10-27 15:45:00', 'Selesai', 3, 2212),
(4013, 'Maya Anggraini', '081234560013', 'Jl. Yos Sudarso I, Sangatta', 'maya.anggraini@example.com', 'Dewi Pertiwi', '082234570013', 'Jl. Gajah Mada, Samarinda', 'Samarinda', 'Paket', '1.80', '60600.00', '2026-10-27 16:20:00', 'Selesai', 1, 2213),
(4014, 'Naufal Rizki', '081234560014', 'Jl. Yos Sudarso II, Sangatta', 'naufal.rizki@example.com', 'Aldi Nugraha', '082234570014', 'Jl. Cendana, Samarinda', 'Samarinda', 'Dokumen', '3.40', '60800.00', '2026-10-27 17:05:00', 'Selesai', 2, 2214),
(4015, 'Olivia Rahma', '081234560015', 'Jl. Yos Sudarso III, Sangatta', 'olivia.rahma@example.com', 'Meisya Putri', '082234570015', 'Jl. M. Yamin, Samarinda', 'Samarinda', 'Surat', '8.20', '72400.00', '2026-10-27 17:40:00', 'Selesai', 3, 2215),
(4016, 'Putra Mahendra', '081234560016', 'Jl. Pahlawan, Samarinda', 'putra.mahendra@example.com', 'Rizky Ananda', '082234570016', 'Jl. Ahmad Yani, Balikpapan', 'Balikpapan', 'Paket', NULL, NULL, '2026-10-27 18:25:00', 'Diproses', 1, 2216),
(4017, 'Qiana Safira', '081234560017', 'Jl. MT Haryono, Balikpapan', 'qiana.safira@example.com', 'Andri Saputra', '082234570017', 'Jl. Perjuangan, Bontang', 'Bontang', 'Dokumen', NULL, NULL, '2026-10-27 19:10:00', 'Diproses', 2, 2217),
(4018, 'Rafi Prakoso', '081234560018', 'Jl. Selat Karimata I, Bontang', 'rafi.prakoso@example.com', 'Sinta Larasati', '082234570018', 'Jl. Naga, Tenggarong', 'Tenggarong', 'Surat', NULL, NULL, '2026-10-27 20:15:00', 'Diproses', 3, 2218),
(4019, 'Salsa Nabila', '081234560019', 'Jl. Tepian Pandan, Tenggarong', 'salsa.nabila@example.com', 'Bayu Nugroho', '082234570019', 'Jl. Yos Sudarso I, Sangatta', 'Sangatta', 'Paket', NULL, NULL, '2026-10-27 21:00:00', 'Diproses', 1, 2219),
(4020, 'Tegar Maulana', '081234560020', 'Jl. Yos Sudarso II, Sangatta', 'tegar.maulana@example.com', 'Putri Oktavia', '082234570020', 'Jl. Gajah Mada, Samarinda', 'Samarinda', 'Dokumen', NULL, NULL, '2026-10-25 09:30:00', 'Dibatalkan', 2, 2220);

-- --------------------------------------------------------

--
-- Table structure for table `tarif_wilayah`
--

CREATE TABLE `tarif_wilayah` (
  `id_tarif` int NOT NULL,
  `kota_asal` varchar(100) NOT NULL,
  `kota_tujuan` varchar(100) NOT NULL,
  `biaya_per_kg` decimal(12,2) NOT NULL,
  `layanan_id_layanan` int NOT NULL
) ;

--
-- Dumping data for table `tarif_wilayah`
--

INSERT INTO `tarif_wilayah` (`id_tarif`, `kota_asal`, `kota_tujuan`, `biaya_per_kg`, `layanan_id_layanan`) VALUES
(3001, 'Samarinda', 'Balikpapan', '15000.00', 1),
(3002, 'Samarinda', 'Balikpapan', '10000.00', 2),
(3003, 'Samarinda', 'Balikpapan', '5000.00', 3),
(3004, 'Balikpapan', 'Bontang', '15500.00', 1),
(3005, 'Balikpapan', 'Bontang', '10500.00', 2),
(3006, 'Balikpapan', 'Bontang', '5500.00', 3),
(3007, 'Bontang', 'Tenggarong', '16000.00', 1),
(3008, 'Bontang', 'Tenggarong', '11000.00', 2),
(3009, 'Bontang', 'Tenggarong', '6000.00', 3),
(3010, 'Tenggarong', 'Sangatta', '16500.00', 1),
(3011, 'Tenggarong', 'Sangatta', '11500.00', 2),
(3012, 'Tenggarong', 'Sangatta', '6500.00', 3),
(3013, 'Sangatta', 'Samarinda', '17000.00', 1),
(3014, 'Sangatta', 'Samarinda', '12000.00', 2),
(3015, 'Sangatta', 'Samarinda', '7000.00', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agen`
--
ALTER TABLE `agen`
  ADD PRIMARY KEY (`id_lokasi`),
  ADD UNIQUE KEY `kode_agen` (`kode_agen`),
  ADD KEY `fk_agen_cabang` (`cabang_id_lokasi`);

--
-- Indexes for table `cabang`
--
ALTER TABLE `cabang`
  ADD PRIMARY KEY (`id_lokasi`),
  ADD UNIQUE KEY `kode_cabang` (`kode_cabang`);

--
-- Indexes for table `layanan`
--
ALTER TABLE `layanan`
  ADD PRIMARY KEY (`id_layanan`),
  ADD UNIQUE KEY `uq_layanan_nama` (`nama_layanan`);

--
-- Indexes for table `lokasi_operasional`
--
ALTER TABLE `lokasi_operasional`
  ADD PRIMARY KEY (`id_lokasi`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD UNIQUE KEY `uq_pembayaran_resi` (`pengiriman_nomor_resi`),
  ADD KEY `fk_pembayaran_pengguna` (`pengguna_id_pengguna`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `fk_pengguna_lokasi` (`lokasi_operasional_id_lokasi`);

--
-- Indexes for table `pengiriman`
--
ALTER TABLE `pengiriman`
  ADD PRIMARY KEY (`nomor_resi`),
  ADD UNIQUE KEY `uq_pengiriman_pesanan` (`pesanan_id_pesanan`,`pesanan_pengguna_id_pengguna`),
  ADD KEY `fk_pengiriman_tarif` (`tarif_wilayah_id_tarif`),
  ADD KEY `fk_pengiriman_agen` (`agen_id_lokasi`),
  ADD KEY `fk_pengiriman_cabang` (`cabang_id_lokasi`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id_pesanan`,`pengguna_id_pengguna`),
  ADD KEY `fk_pesanan_layanan` (`layanan_id_layanan`),
  ADD KEY `fk_pesanan_pengguna` (`pengguna_id_pengguna`);

--
-- Indexes for table `tarif_wilayah`
--
ALTER TABLE `tarif_wilayah`
  ADD PRIMARY KEY (`id_tarif`),
  ADD UNIQUE KEY `uq_tarif_rute` (`layanan_id_layanan`,`kota_asal`,`kota_tujuan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `layanan`
--
ALTER TABLE `layanan`
  MODIFY `id_layanan` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lokasi_operasional`
--
ALTER TABLE `lokasi_operasional`
  MODIFY `id_lokasi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1116;

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `id_pembayaran` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2221;

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `id_pesanan` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tarif_wilayah`
--
ALTER TABLE `tarif_wilayah`
  MODIFY `id_tarif` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agen`
--
ALTER TABLE `agen`
  ADD CONSTRAINT `fk_agen_cabang` FOREIGN KEY (`cabang_id_lokasi`) REFERENCES `cabang` (`id_lokasi`),
  ADD CONSTRAINT `fk_agen_lokasi` FOREIGN KEY (`id_lokasi`) REFERENCES `lokasi_operasional` (`id_lokasi`);

--
-- Constraints for table `cabang`
--
ALTER TABLE `cabang`
  ADD CONSTRAINT `fk_cabang_lokasi` FOREIGN KEY (`id_lokasi`) REFERENCES `lokasi_operasional` (`id_lokasi`);

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `fk_pembayaran_pengguna` FOREIGN KEY (`pengguna_id_pengguna`) REFERENCES `pengguna` (`id_pengguna`),
  ADD CONSTRAINT `fk_pembayaran_pengiriman` FOREIGN KEY (`pengiriman_nomor_resi`) REFERENCES `pengiriman` (`nomor_resi`);

--
-- Constraints for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD CONSTRAINT `fk_pengguna_lokasi` FOREIGN KEY (`lokasi_operasional_id_lokasi`) REFERENCES `lokasi_operasional` (`id_lokasi`);

--
-- Constraints for table `pengiriman`
--
ALTER TABLE `pengiriman`
  ADD CONSTRAINT `fk_pengiriman_agen` FOREIGN KEY (`agen_id_lokasi`) REFERENCES `agen` (`id_lokasi`),
  ADD CONSTRAINT `fk_pengiriman_cabang` FOREIGN KEY (`cabang_id_lokasi`) REFERENCES `cabang` (`id_lokasi`),
  ADD CONSTRAINT `fk_pengiriman_pesanan` FOREIGN KEY (`pesanan_id_pesanan`,`pesanan_pengguna_id_pengguna`) REFERENCES `pesanan` (`id_pesanan`, `pengguna_id_pengguna`),
  ADD CONSTRAINT `fk_pengiriman_tarif` FOREIGN KEY (`tarif_wilayah_id_tarif`) REFERENCES `tarif_wilayah` (`id_tarif`);

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `fk_pesanan_layanan` FOREIGN KEY (`layanan_id_layanan`) REFERENCES `layanan` (`id_layanan`),
  ADD CONSTRAINT `fk_pesanan_pengguna` FOREIGN KEY (`pengguna_id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `tarif_wilayah`
--
ALTER TABLE `tarif_wilayah`
  ADD CONSTRAINT `fk_tarif_layanan` FOREIGN KEY (`layanan_id_layanan`) REFERENCES `layanan` (`id_layanan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
