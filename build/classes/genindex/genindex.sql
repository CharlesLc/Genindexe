-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 18 Mai 2015 à 10:16
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- --------------------------------------------------------

--
-- Structure de la table `job`
--

DROP TABLE IF EXISTS `job`;
CREATE TABLE IF NOT EXISTS `job` (
  `jobID` int(5) NOT NULL AUTO_INCREMENT COMMENT 'PK AI',
  `jobName` varchar(30) NOT NULL COMMENT 'job name',
  PRIMARY KEY (`jobID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Job table' AUTO_INCREMENT=4 ;

--
-- Contenu de la table `job`
--

INSERT INTO `job` (`jobID`, `jobName`) VALUES
(1, 'Secretary'),
(2, 'Validator'),
(3, 'Technician');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `login` varchar(30) NOT NULL COMMENT 'user login',
  `passwd` varchar(30) NOT NULL COMMENT 'user password',
  `jobID` int(5) NOT NULL COMMENT 'Foreign key',
  PRIMARY KEY (`login`),
  KEY `jobID` (`jobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='User table';

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`login`, `passwd`, `jobID`) VALUES
('secretary', 'secretary', 1),
('technician', 'technician', 3),
('validator', 'validator', 2);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `jobID_user_jobID_job_FK` FOREIGN KEY (`jobID`) REFERENCES `job` (`jobID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
