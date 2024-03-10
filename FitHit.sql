-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 10, 2024 at 05:20 PM
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
-- Database: `FitHit`
--

-- --------------------------------------------------------

--
-- Table structure for table `card`
--

CREATE TABLE `card` (
  `codice` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `workout1` varchar(255) NOT NULL,
  `workout2` varchar(255) NOT NULL,
  `workout3` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `card`
--

INSERT INTO `card` (`codice`, `nome`, `cognome`, `workout1`, `workout2`, `workout3`) VALUES
('AT261935', 'Matteo', 'Biasiolo', 'workout1', 'workout2', 'workout3'),
('AT712632', 'Stefano', 'Biasiolo', 'workout1', 'workout2', 'workout3'),
('AT795469', 'Anna', 'Marchesini', 'workout1', 'workout2', 'workout3'),
('TR825347', 'Luca', 'Dalla Valeria', 'workout1', 'workout2', 'workout3'),
('TR840240', 'Lorenzo', 'Loiacono', 'workout1', 'workout2', 'workout3');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `codice` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `peso` double NOT NULL,
  `altezza` double NOT NULL,
  `account` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`codice`, `nome`, `cognome`, `email`, `password`, `peso`, `altezza`, `account`) VALUES
('AT261935', 'Matteo', 'Biasiolo', 'biasi@email.com', '$2a$10$wpyRITmfwWCKppDX7eEMveB7XIb3wg/peqg3B1FqTqAYW135uD0C2', 54, 180, 'Atleta'),
('AT712632', 'Stefano', 'Biasiolo', 'bisso@email.com', '$2a$10$1aRaSk4gKPl.TsA33pcXS.DVIxi6TKzDdzgBxjX1F4wYOvwhj4dpe', 70, 165, 'Atleta'),
('AT795469', 'Anna', 'Marchesini', 'anna@email.com', '$2a$10$i0LgfCqNNtrdsikx92WeJ.Ql0XAle7D865WWnkwKRz0zkC1O.x362', 48, 165, 'Atleta'),
('TR825347', 'Luca', 'Dalla Valeria', 'dalla@email.com', '$2a$10$N3vIJTrvtQHRDpu1D/ERvuWblHY7XjgFtfuIKC25qYlTHEdkFeyYi', 54, 175, 'Trainer'),
('TR840240', 'Lorenzo', 'Loiacono', 'lolo@email.com', '$2a$10$OPGHCIcObY3yg9CIvBoNJuH9VKIdMtsJKMXe3llJElb6MkkPbJ3Xe', 70, 165, 'Trainer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`codice`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`codice`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `card`
--
ALTER TABLE `card`
  ADD CONSTRAINT `codice` FOREIGN KEY (`codice`) REFERENCES `user` (`codice`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
