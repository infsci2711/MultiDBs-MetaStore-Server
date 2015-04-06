-- phpMyAdmin SQL Dump
-- version 4.2.5
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Apr 06, 2015 at 12:51 AM
-- Server version: 5.5.38
-- PHP Version: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `group1`
--

-- --------------------------------------------------------

--
-- Table structure for table `metaStore`
--

CREATE TABLE `metaStore` (
`ID` int(11) NOT NULL,
  `DBtype` varchar(45) DEFAULT NULL,
  `IPAddress` varchar(45) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `DBname` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `metaStore`
--

INSERT INTO `metaStore` (`ID`, `DBtype`, `IPAddress`, `port`, `username`, `password`, `DBname`) VALUES
(1, 'MySQL', 'localhost', 3306, 'root', 'hao', 'group1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `metaStore`
--
ALTER TABLE `metaStore`
 ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `metaStore`
--
ALTER TABLE `metaStore`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;