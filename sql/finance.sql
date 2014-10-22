-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.36 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table finance.cif
CREATE TABLE IF NOT EXISTS `cif` (
  `cif_no` varchar(8) NOT NULL DEFAULT '',
  `password` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `mother_maiden_name` varchar(50) DEFAULT NULL,
  `id_number` varchar(50) DEFAULT NULL,
  `id_type` char(1) DEFAULT NULL,
  PRIMARY KEY (`cif_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table finance.cif: ~0 rows (approximately)
/*!40000 ALTER TABLE `cif` DISABLE KEYS */;
/*!40000 ALTER TABLE `cif` ENABLE KEYS */;


-- Dumping structure for table finance.loan_account
CREATE TABLE IF NOT EXISTS `loan_account` (
  `account_no` varchar(6) NOT NULL DEFAULT '',
  `plafond` decimal(15,2) DEFAULT NULL,
  `tenure` int(11) DEFAULT NULL,
  `interest_rate` decimal(5,2) DEFAULT NULL,
  `interest_type` char(1) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `cif_no` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`account_no`),
  KEY `FK_loan_account_cif` (`cif_no`),
  CONSTRAINT `FK_loan_account_cif` FOREIGN KEY (`cif_no`) REFERENCES `cif` (`cif_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table finance.loan_account: ~0 rows (approximately)
/*!40000 ALTER TABLE `loan_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan_account` ENABLE KEYS */;


-- Dumping structure for table finance.loan_account_schedule
CREATE TABLE IF NOT EXISTS `loan_account_schedule` (
  `account_no` varchar(6) NOT NULL DEFAULT '',
  `period` int(11) NOT NULL DEFAULT '0',
  `due_date` date DEFAULT NULL,
  `principal` decimal(15,2) DEFAULT NULL,
  `interest` decimal(15,2) DEFAULT NULL,
  `installment` decimal(15,2) DEFAULT NULL,
  `outstanding` decimal(15,2) DEFAULT NULL,
  `paid_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`account_no`,`period`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table finance.loan_account_schedule: ~0 rows (approximately)
/*!40000 ALTER TABLE `loan_account_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan_account_schedule` ENABLE KEYS */;


-- Dumping structure for table finance.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` decimal(15,2) DEFAULT NULL,
  `account_no` varchar(8) DEFAULT NULL,
  `payment_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table finance.payment: ~0 rows (approximately)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


-- Dumping structure for table finance.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table finance.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
