SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
CREATE DATABASE  IF NOT EXISTS `group1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `group1`;
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
(1, 'MySQL', '1.2.3.4', 80, 'hao', 'hao', 'metastore'),
(2, 'MySQL', '1.2.3.4', 80, 'hao', 'hao', 'metastore'),
(3, 'MySQL', '1.2.3.4', 0, 'hao', 'hao', 'metastore'),
(4, 'MySQL', '1.2.3.4', 0, 'hao', 'hao', 'store'),
(5, '1', '1', 1, '1', '1', '1'),
(6, '1', '1', 1, '1', '1', '1'),
(7, '2', '2', 2, '2', '2', '2');

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