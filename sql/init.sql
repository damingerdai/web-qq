-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.28 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for webqq
CREATE DATABASE IF NOT EXISTS `webqq` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `webqq`;

-- Dumping structure for table webqq.message
CREATE TABLE IF NOT EXISTS `message` (
  `id` varchar(36) NOT NULL,
  `sendUserId` varchar(64) DEFAULT NULL,
  `receiveUserId` varchar(64) DEFAULT NULL,
  `content` text,
  `sendDate` datetime DEFAULT NULL,
  `sendDeleteFlag` int(11) DEFAULT '0',
  `receiveDeleteFlag` int(11) DEFAULT '0',
  `create_id` varchar(50) DEFAULT NULL COMMENT '创建者',
  `creation_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table webqq.relationships
CREATE TABLE IF NOT EXISTS `relationships` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid1` varchar(64) NOT NULL,
  `userid2` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table webqq.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
