-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 25, 2026 at 01:02 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_bengkel`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_pelanggan`
--

CREATE TABLE `t_pelanggan` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nomor_plat` varchar(15) NOT NULL,
  `keluhan` text DEFAULT NULL,
  `id_sparepart` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_pelanggan`
--

INSERT INTO `t_pelanggan` (`id`, `nama`, `nomor_plat`, `keluhan`, `id_sparepart`) VALUES
(16, 'Rezi', 'B 6753 TUB', 'Oli Mesin Repsol 150', NULL),
(17, 'Asep', 'B 8976 BCA', 'Oli Mesin Repsol 150', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_sparepart`
--

CREATE TABLE `t_sparepart` (
  `id` int(11) NOT NULL,
  `nama_sparepart` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_sparepart`
--

INSERT INTO `t_sparepart` (`id`, `nama_sparepart`, `harga`, `stok`) VALUES
(1, 'Vanbelt Vario 125', 120000, 10),
(2, 'Piston Mio 110', 450000, 5),
(3, 'Rante keteng Jupiter MX 150', 210000, 3),
(4, 'Oli Mesin Yamalube Matic 125', 45000, 10),
(5, 'Oli Mesin Repsol 150', 50000, 15),
(6, 'Oli Gardan Yamalube Matic', 35000, 12),
(7, 'Sockbeker Honda PCX', 250000, 5),
(8, 'Handle Rem Vario 125', 120000, 3),
(9, 'Ban Tubbeless Michelline Matic R-14', 350000, 3),
(10, 'Lampu LED Matic + Kipas', 200000, 5),
(11, 'Kampas Rem Depan Aerok 150', 45000, 10),
(12, 'Kampas Rem Belakang Matic NMAX 160', 75000, 20),
(13, 'Spakbor Depan RX King', 450000, 3),
(14, 'Spion Vario 150', 160000, 5),
(15, 'Handle Rem Kanan Vario 125', 70000, 10),
(16, 'Handle Rem Kiri Vario 125', 70000, 10),
(17, 'Kipas Pully Beat', 120000, 3),
(19, 'CVT Pully', 20000, 3);

-- --------------------------------------------------------

--
-- Table structure for table `t_transaksi`
--

CREATE TABLE `t_transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `tanggal_transaksi` datetime NOT NULL DEFAULT current_timestamp(),
  `nama_pelanggan` varchar(100) NOT NULL,
  `nomor_plat` varchar(20) NOT NULL,
  `sparepart_diganti` text NOT NULL,
  `total_harga_part` int(11) NOT NULL,
  `biaya_servis` int(11) NOT NULL,
  `total_tagihan` int(11) NOT NULL,
  `total_bayar` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_transaksi`
--

INSERT INTO `t_transaksi` (`id_transaksi`, `tanggal_transaksi`, `nama_pelanggan`, `nomor_plat`, `sparepart_diganti`, `total_harga_part`, `biaya_servis`, `total_tagihan`, `total_bayar`, `kembalian`) VALUES
(5, '2026-06-23 12:07:05', 'Torik', 'B 3168 SCK', 'Piston Mio 110', 450000, 120000, 570000, 600000, 30000),
(6, '2026-06-23 12:07:31', 'Andi', 'B 1234 SSA', 'Ban Tubbeless Michelline Matic R-14', 350000, 25000, 375000, 400000, 25000),
(7, '2026-06-23 12:16:06', 'Alim', 'B 2321 SKC', 'Rante keteng Jupiter MX 150', 210000, 60000, 270000, 300000, 30000),
(8, '2026-06-23 12:26:37', 'Liza', 'B 4321 SAG', 'Oli Mesin Repsol 150', 50000, 12000, 62000, 100000, 38000),
(9, '2026-06-23 13:04:13', 'Kharis', 'B 6543 EYA', 'Rante keteng Jupiter MX 150', 210000, 80000, 290000, 300000, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'admin', 'bengkel123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_pelanggan`
--
ALTER TABLE `t_pelanggan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_sparepart`
--
ALTER TABLE `t_sparepart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_transaksi`
--
ALTER TABLE `t_transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_pelanggan`
--
ALTER TABLE `t_pelanggan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `t_sparepart`
--
ALTER TABLE `t_sparepart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `t_transaksi`
--
ALTER TABLE `t_transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
