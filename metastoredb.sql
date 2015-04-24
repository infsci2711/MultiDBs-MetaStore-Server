SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `group1`
--

-- --------------------------------------------------------

--
-- Table structure for table `metaStore`
--
CREATE DATABASE  IF NOT EXISTS `metastore` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `metastore`;

CREATE TABLE `datasources` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DBtype` varchar(45) DEFAULT NULL,
  `IPAddress` varchar(45) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `DBname` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8; 

CREATE USER 'metastoreUser'@'localhost' IDENTIFIED BY 'metastoreUser';
grant all privileges on `metastore`.* to 'metastoreUser'@'localhost';

CREATE USER 'metastoreUser'@'%' IDENTIFIED BY 'metastoreUser';
grant all privileges on `metastore`.* to 'metastoreUser'@'%';
