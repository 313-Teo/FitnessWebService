-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 22, 2024 at 05:42 PM
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
  `id` int(11) NOT NULL,
  `codice_utente` varchar(255) DEFAULT NULL,
  `codice_workout` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `card`
--

INSERT INTO `card` (`id`, `codice_utente`, `codice_workout`) VALUES
(6, 'AT261935', 'WR948'),
(7, 'AT261935', 'WR278'),
(8, 'AT795469', 'WR802'),
(9, 'AT261935', 'WR802'),
(10, 'AT261935', 'WR763');

-- --------------------------------------------------------

--
-- Table structure for table `exercise`
--

CREATE TABLE `exercise` (
  `codice` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `tempo` time NOT NULL,
  `focus` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `exercise`
--

INSERT INTO `exercise` (`codice`, `nome`, `tempo`, `focus`) VALUES
('EX001', 'Push up', '00:03:00', 'Petto'),
('EX002', 'Squat', '00:05:00', 'Quadricipiti'),
('EX003', 'Crunches', '00:02:30', 'Addominali'),
('EX004', 'Tricipiti ai cavi', '00:03:30', 'Tricipiti'),
('EX005', 'Leg Press', '00:06:00', 'Quadricipiti'),
('EX006', 'Bicipiti con manubri', '00:04:00', 'Bicipiti'),
('EX007', 'Polpacci su macchina', '00:03:30', 'Polpacci'),
('EX008', 'Mountain Climbers', '00:03:00', 'Corpo intero'),
('EX009', 'Jumping Jacks', '00:04:00', 'Corpo intero'),
('EX010', 'Plank', '00:02:00', 'Addominali'),
('EX011', 'Dips su sbarra', '00:03:30', 'Tricipiti'),
('EX012', 'Squat Jump', '00:03:00', 'Quadricipiti'),
('EX013', 'Burpees', '00:05:00', 'Corpo intero'),
('EX014', 'Flessioni Diamond', '00:03:30', 'Tricipiti'),
('EX015', 'Aereoplane Plank', '00:02:30', 'Addominali'),
('EX016', 'Affondi', '00:04:00', 'Quadricipiti'),
('EX017', 'Crunches Obliqui', '00:02:30', 'Addominali'),
('EX018', 'Push up Stretti', '00:03:00', 'Petto'),
('EX019', 'Step Ups', '00:04:00', 'Quadricipiti'),
('EX020', 'Superman', '00:02:30', 'Lombari'),
('EX021', 'Addominali a Rana', '00:03:00', 'Addominali'),
('EX022', 'Curl con Bilanciere', '00:04:00', 'Bicipiti'),
('EX023', 'Skip Jump', '00:03:30', 'Cardio'),
('EX024', 'Plank Laterale', '00:02:30', 'Addominali'),
('EX025', 'Affondi Laterali', '00:04:00', 'Quadricipiti'),
('EX026', 'Mountain Climbers Veloci', '00:03:00', 'Corpo intero'),
('EX027', 'Crunches Inversi', '00:02:30', 'Addominali'),
('EX028', 'Tirate al Lat Machine', '00:04:00', 'Dorsali'),
('EX029', 'Squat con Salto', '00:03:30', 'Quadricipiti'),
('EX030', 'Flessioni Declinate', '00:03:00', 'Petto'),
('EX031', 'Pull-up', '00:03:00', 'Dorsali'),
('EX032', 'Leg Extension', '00:04:00', 'Quadricipiti'),
('EX033', 'Shoulder Press', '00:03:00', 'Deltoidi'),
('EX034', 'Hammer Curl', '00:03:30', 'Bicipiti'),
('EX035', 'Calf Raises', '00:03:00', 'Polpacci'),
('EX036', 'High Knees', '00:03:30', 'Cardio'),
('EX037', 'Russian Twists', '00:02:30', 'Addominali'),
('EX038', 'Reverse Crunches', '00:03:00', 'Addominali'),
('EX039', 'Box Jumps', '00:04:00', 'Quadricipiti'),
('EX040', 'Back Extension', '00:02:30', 'Lombari');

-- --------------------------------------------------------

--
-- Table structure for table `myathletes`
--

CREATE TABLE `myathletes` (
  `id` int(11) NOT NULL,
  `codice_trainer` varchar(255) DEFAULT NULL,
  `codice_atleta` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `myathletes`
--

INSERT INTO `myathletes` (`id`, `codice_trainer`, `codice_atleta`) VALUES
(1, 'TR825347', 'AT261935'),
(6, 'TR825347', 'AT795469'),
(12, 'TR825347', 'AT712632');

-- --------------------------------------------------------

--
-- Table structure for table `training`
--

CREATE TABLE `training` (
  `id` int(11) NOT NULL,
  `codice_workout` varchar(255) DEFAULT NULL,
  `codice_esercizio` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `training`
--

INSERT INTO `training` (`id`, `codice_workout`, `codice_esercizio`) VALUES
(36, 'WR948', 'EX001'),
(37, 'WR948', 'EX004'),
(38, 'WR948', 'EX014'),
(39, 'WR948', 'EX018'),
(40, 'WR948', 'EX027'),
(41, 'WR278', 'EX002'),
(42, 'WR278', 'EX005'),
(43, 'WR278', 'EX016'),
(44, 'WR278', 'EX025'),
(45, 'WR278', 'EX028'),
(46, 'WR763', 'EX006'),
(47, 'WR763', 'EX011'),
(48, 'WR763', 'EX022'),
(49, 'WR763', 'EX028'),
(50, 'WR763', 'EX022'),
(51, 'WR159', 'EX008'),
(52, 'WR159', 'EX013'),
(53, 'WR159', 'EX026'),
(54, 'WR159', 'EX030'),
(55, 'WR159', 'EX023'),
(56, 'WR802', 'EX003'),
(57, 'WR802', 'EX010'),
(58, 'WR802', 'EX015'),
(59, 'WR802', 'EX020'),
(60, 'WR802', 'EX024'),
(61, 'WR367', 'EX040'),
(62, 'WR367', 'EX033'),
(63, 'WR367', 'EX036'),
(64, 'WR367', 'EX028'),
(65, 'WR367', 'EX030');

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

-- --------------------------------------------------------

--
-- Table structure for table `workout`
--

CREATE TABLE `workout` (
  `codice` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `workout`
--

INSERT INTO `workout` (`codice`, `nome`) VALUES
('WR802', 'Workout Addominali'),
('WR763', 'Workout Bicipiti'),
('WR159', 'Workout Cardio'),
('WR278', 'Workout Gambe'),
('WR948', 'Workout Petto'),
('WR367', 'Workout Schiena');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`id`),
  ADD KEY `codice_utente` (`codice_utente`),
  ADD KEY `codice_workout` (`codice_workout`);

--
-- Indexes for table `exercise`
--
ALTER TABLE `exercise`
  ADD PRIMARY KEY (`codice`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- Indexes for table `myathletes`
--
ALTER TABLE `myathletes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `codice_trainer` (`codice_trainer`),
  ADD KEY `codice_atleta` (`codice_atleta`);

--
-- Indexes for table `training`
--
ALTER TABLE `training`
  ADD PRIMARY KEY (`id`),
  ADD KEY `codice_workout` (`codice_workout`),
  ADD KEY `codice_esercizio` (`codice_esercizio`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`codice`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `workout`
--
ALTER TABLE `workout`
  ADD PRIMARY KEY (`codice`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `card`
--
ALTER TABLE `card`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `myathletes`
--
ALTER TABLE `myathletes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `training`
--
ALTER TABLE `training`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `card`
--
ALTER TABLE `card`
  ADD CONSTRAINT `card_ibfk_1` FOREIGN KEY (`codice_utente`) REFERENCES `user` (`codice`),
  ADD CONSTRAINT `card_ibfk_2` FOREIGN KEY (`codice_workout`) REFERENCES `workout` (`codice`);

--
-- Constraints for table `myathletes`
--
ALTER TABLE `myathletes`
  ADD CONSTRAINT `myathletes_ibfk_1` FOREIGN KEY (`codice_trainer`) REFERENCES `user` (`codice`),
  ADD CONSTRAINT `myathletes_ibfk_2` FOREIGN KEY (`codice_atleta`) REFERENCES `user` (`codice`);

--
-- Constraints for table `training`
--
ALTER TABLE `training`
  ADD CONSTRAINT `training_ibfk_1` FOREIGN KEY (`codice_workout`) REFERENCES `workout` (`codice`),
  ADD CONSTRAINT `training_ibfk_2` FOREIGN KEY (`codice_esercizio`) REFERENCES `exercise` (`codice`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
