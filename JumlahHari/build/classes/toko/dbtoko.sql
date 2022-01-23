/*
SQLyog Enterprise - MySQL GUI v8.05 
MySQL - 5.5.5-10.4.11-MariaDB : Database - dbtoko
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`dbtoko` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `dbtoko`;

/*Table structure for table `buku` */

DROP TABLE IF EXISTS `buku`;

CREATE TABLE `buku` (
  `id_buku` int(11) NOT NULL AUTO_INCREMENT,
  `no_isbn` varchar(13) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `pengarang` varchar(40) NOT NULL,
  `penerbit` varchar(40) NOT NULL,
  `tahun_terbit` varchar(10) NOT NULL,
  `kategori` varchar(40) NOT NULL,
  `stok` int(11) NOT NULL,
  `harga_pokok` int(11) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  PRIMARY KEY (`id_buku`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `buku` */

insert  into `buku`(`id_buku`,`no_isbn`,`judul`,`pengarang`,`penerbit`,`tahun_terbit`,`kategori`,`stok`,`harga_pokok`,`harga_jual`) values (1,'98746209289','Pelangi di pagi hari','Rifqi','Graneda','2019','Sastra',6,40000,45000),(2,'192768790287','Kau yang Indah','Stuti','Garhana media','2020','Novel',6,50000,55000),(3,'8956256757','Senja','kiko','granaganesa','2020','Novel',20,45000,50000),(4,'24242','ku kejar','gatot','samedia','2012','Sastra',5,20000,25000);

/*Table structure for table `kasir` */

DROP TABLE IF EXISTS `kasir`;

CREATE TABLE `kasir` (
  `id_kasir` int(11) NOT NULL AUTO_INCREMENT,
  `nama_kasir` varchar(40) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(13) NOT NULL,
  `email` varchar(40) NOT NULL,
  PRIMARY KEY (`id_kasir`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `kasir` */

insert  into `kasir`(`id_kasir`,`nama_kasir`,`alamat`,`no_telp`,`email`) values (1,'ade','jl.ibrahim aji no.24 ','0897451892','ade10@gmail.com'),(2,'sandi','jl.antapani no.29','08812055409','sandi@gmail.com');

/*Table structure for table `penjualan` */

DROP TABLE IF EXISTS `penjualan`;

CREATE TABLE `penjualan` (
  `id_transaksi` varchar(20) NOT NULL,
  `id_buku` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `judul` varchar(50) NOT NULL,
  `kategori` varchar(40) NOT NULL,
  `hsatuan` int(11) NOT NULL,
  `jumlah_beli` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `penjualan` */

insert  into `penjualan`(`id_transaksi`,`id_buku`,`tanggal`,`judul`,`kategori`,`hsatuan`,`jumlah_beli`,`harga`,`bayar`,`kembalian`) values ('F0001',1,'2020-07-04','Pelangi di pagi hari','Sastra',45000,2,90000,100000,10000),('F0002',2,'2020-07-04','Kau yang Indah','Novel',55000,1,55000,110000,5000),('F0002',4,'2020-07-04','ku kejar','Sastra',25000,2,50000,110000,5000),('F0003',2,'2020-07-05','Kau yang Indah','Novel',55000,1,55000,100000,20000),('F0003',4,'2020-07-05','ku kejar','Sastra',25000,1,25000,100000,20000);

/*Table structure for table `penjualan_detail` */

DROP TABLE IF EXISTS `penjualan_detail`;

CREATE TABLE `penjualan_detail` (
  `id_detail` int(11) NOT NULL AUTO_INCREMENT,
  `id_buku` int(11) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `kategori` varchar(40) NOT NULL,
  `hsatuan` int(11) NOT NULL,
  `jumlah_beli` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  PRIMARY KEY (`id_detail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `penjualan_detail` */

/*Table structure for table `tbl_kategori` */

DROP TABLE IF EXISTS `tbl_kategori`;

CREATE TABLE `tbl_kategori` (
  `id_kategori` int(11) NOT NULL AUTO_INCREMENT,
  `nama_kategori` varchar(40) NOT NULL,
  PRIMARY KEY (`id_kategori`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_kategori` */

insert  into `tbl_kategori`(`id_kategori`,`nama_kategori`) values (1,'Novel'),(10,'Komputer'),(11,'Sastra'),(12,'Filasafat');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(8) NOT NULL,
  `level` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`level`) values (1,'rifqi','rifqi11','pemilik'),(2,'ade','ade123','kasir'),(3,'sandi','sandi01','kasir');

/* Trigger structure for table `penjualan_detail` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `beli` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `beli` AFTER INSERT ON `penjualan_detail` FOR EACH ROW BEGIN
	UPDATE buku SET stok = stok - new.jumlah_beli 
WHERE id_buku = new.`id_buku`; 
    END */$$


DELIMITER ;

/* Trigger structure for table `penjualan_detail` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `batal` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `batal` AFTER DELETE ON `penjualan_detail` FOR EACH ROW BEGIN
UPDATE buku SET stok = stok + OLD.jumlah_beli
WHERE id_buku = OLD.id_buku;
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
