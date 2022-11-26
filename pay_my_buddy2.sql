-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Nov 26, 2022 at 03:00 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pay_my_buddy`
--

-- --------------------------------------------------------

--
-- Table structure for table `commissions_pmb`
--

DROP TABLE IF EXISTS `commissions_pmb`;
CREATE TABLE IF NOT EXISTS `commissions_pmb` (
  `numero_transaction` int(11) NOT NULL AUTO_INCREMENT,
  `montant` double NOT NULL,
  PRIMARY KEY (`numero_transaction`)
) ENGINE=InnoDB AUTO_INCREMENT=1973333730 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commissions_pmb`
--

INSERT INTO `commissions_pmb` (`numero_transaction`, `montant`) VALUES
(-904766254, 0.04),
(-785016070, 5),
(-215950430, 0.05),
(76604791, 0.5),
(138451443, 0.5),
(653037198, 6.5),
(655981928, 0.5),
(1214806648, 0.05),
(1422445377, 11.5),
(1424657944, 0.5),
(1800775578, 1),
(1973333729, 0.035);

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `id_utilisateur` int(11) NOT NULL,
  `id_compte` varchar(15) NOT NULL,
  `iban` varchar(27) NOT NULL,
  `bic` char(11) NOT NULL,
  `banque` varchar(15) NOT NULL,
  `fonds` double NOT NULL,
  PRIMARY KEY (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `compte`
--

INSERT INTO `compte` (`id_utilisateur`, `id_compte`, `iban`, `bic`, `banque`, `fonds`) VALUES
(6, '12345678922', 'FR8030003000406666623948L05', 'CMDIFR21', 'CDCM', 54972);

-- --------------------------------------------------------

--
-- Table structure for table `connexions`
--

DROP TABLE IF EXISTS `connexions`;
CREATE TABLE IF NOT EXISTS `connexions` (
  `receveur_id_utilisateur` int(11) NOT NULL,
  `demandeur_id_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`demandeur_id_utilisateur`,`receveur_id_utilisateur`),
  KEY `receveur_id_utilisateur` (`receveur_id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `connexions`
--

INSERT INTO `connexions` (`receveur_id_utilisateur`, `demandeur_id_utilisateur`) VALUES
(-654230357, 6),
(1, -2078524915),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 618359922),
(2, 1),
(6, 1),
(6, 2),
(6, 3),
(618359922, 6),
(698111856, 6);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `montant` int(11) DEFAULT NULL,
  `emetteur` int(11) NOT NULL,
  `destinataire` int(11) NOT NULL,
  PRIMARY KEY (`date`,`destinataire`,`emetteur`),
  KEY `emetteur` (`emetteur`),
  KEY `destinataire` (`destinataire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`date`, `description`, `montant`, `emetteur`, `destinataire`) VALUES
('2022-08-25 00:00:00', 'donuts', 5, 3, 2),
('2022-09-07 00:00:00', 'Sortie Cinéma', 15, 3, 1),
('2022-09-07 14:46:27', 'Cinema', 5, 1, 4),
('2022-09-10 14:47:10', 'Pizza', 15, 1, 5),
('2022-09-16 00:00:00', 'Bar', 11, 1, 4),
('2022-09-27 00:00:00', 'Sortie VTT', 16, 1, 4),
('2022-09-29 00:00:00', 'Voyage', 10, 1, 4),
('2022-09-30 00:00:00', 'Le retour du roi', 9, 1, 5),
('2022-10-03 00:00:00', 'Test front ', 10, 1, 5),
('2022-10-06 00:00:00', 'Nouvel Essai Ctrl R ', 11, 1, 2),
('2022-10-06 00:00:00', 'Essai  ctrl R ', 13, 1, 4),
('2022-10-06 00:00:00', 'Still Tryin', 9, 1, 5),
('2022-11-03 00:00:00', 'essai com save', 100, 1, 6),
('2022-11-16 00:00:00', 'je saisa âs ', 7, 1, 5),
('2022-11-21 00:00:00', 'last try ', 8, 1, 5),
('2022-11-24 00:00:00', 'album', 100, 1, -2078524915),
('2022-11-24 00:00:00', 'formation', 1000, 6, 1),
('2022-11-24 00:00:00', 'donuts ', 10, 6, 2),
('2022-11-24 00:00:00', 'test', 200, 6, 3),
('2022-11-25 00:00:00', 'essai', 100, 6, 1),
('2022-11-25 00:00:00', 'test235', 23, 6, 2),
('2022-11-25 00:00:00', 'test3', 10, 6, 3);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `id_utilisateur` int(11) NOT NULL,
  `adresse_email` varchar(255) DEFAULT NULL,
  `date_de_naissance` datetime DEFAULT NULL,
  `fonds_disponibles` double DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(10) NOT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `adresse_email` (`adresse_email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id_utilisateur`, `adresse_email`, `date_de_naissance`, `fonds_disponibles`, `nom`, `prenom`, `mot_de_passe`) VALUES
(-2078524915, 'mJAgger.com', '1940-01-25 00:00:00', 100, 'Jagger', 'Mick', 'rolls'),
(-2011051568, 'jadit@paymybuddy.com', '1985-11-11 00:00:00', 0, 'Adit', 'Jack', 'juser'),
(-654230357, 'mTest.com', '1989-01-25 00:00:00', 0, 'Monsieur', 'Test', 'test'),
(-8147522, 'chardy@pmb.com', '1986-12-03 00:00:00', 0, 'Hardy', 'Christian', 'froufrou'),
(1, 'jNippard.com', '1990-02-25 00:00:00', 4479.035, 'Nippard', 'Jeff', 'minimalism'),
(2, 'wTenny.com', '1994-04-14 00:00:00', 5167, 'William', 'Tennyson', ''),
(3, 'gDoucette.com', '1978-04-14 00:00:00', 5210, 'Greg', 'Doucette', ''),
(4, 'totot@totototsrzer', '2022-09-13 13:04:11', 36, 'totot', 'toto', ''),
(5, 'ezorijzoerohzer', '2022-09-04 13:04:11', 5024, 'titit', 'tititi', ''),
(6, 'acoudin@paymybud.io', '1990-01-04 00:00:00', 44020.05, 'Coudin ', 'Anthony ', 'antho'),
(7, 'jDoe@paymybud.com', '1996-02-01 00:00:00', 1500, 'Doe', 'John', ''),
(618359922, 'saTatouille@legume.fr', '1992-08-02 00:00:00', 0, 'Tatouille', 'Sarah', 'sarah'),
(698111856, 'mtestDeux.com', '1989-01-25 00:00:00', 0, 'Monsieur ', 'TestDeeux', 'test'),
(895343630, 'mPlates.com', '1990-02-15 00:00:00', 0, 'MorePlates', 'MoreDates', 'derek'),
(1321929246, 'jDoe.com', '1940-01-25 00:00:00', 0, 'Doe', 'John', 'doe');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `connexions`
--
ALTER TABLE `connexions`
  ADD CONSTRAINT `connexions_ibfk_1` FOREIGN KEY (`receveur_id_utilisateur`) REFERENCES `utilisateurs` (`id_utilisateur`),
  ADD CONSTRAINT `connexions_ibfk_2` FOREIGN KEY (`demandeur_id_utilisateur`) REFERENCES `utilisateurs` (`id_utilisateur`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`emetteur`) REFERENCES `utilisateurs` (`id_utilisateur`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`destinataire`) REFERENCES `utilisateurs` (`id_utilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
