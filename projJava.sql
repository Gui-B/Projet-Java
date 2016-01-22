-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Ven 22 Janvier 2016 à 16:01
-- Version du serveur :  5.5.46-0+deb8u1
-- Version de PHP :  5.6.14-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `projJava`
--

-- --------------------------------------------------------

--
-- Structure de la table `Avoir`
--

CREATE TABLE IF NOT EXISTS `Avoir` (
  `IdU` int(11) NOT NULL,
  `idC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Competences`
--

CREATE TABLE IF NOT EXISTS `Competences` (
  `idC` int(11) NOT NULL,
  `NomC` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Diplome`
--

CREATE TABLE IF NOT EXISTS `Diplome` (
  `idD` int(11) NOT NULL,
  `NomD` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Obtenir`
--

CREATE TABLE IF NOT EXISTS `Obtenir` (
  `DateD` date DEFAULT NULL,
  `IdU` int(11) NOT NULL,
  `idD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE IF NOT EXISTS `Utilisateur` (
  `IdU` int(11) NOT NULL,
  `Mail` varchar(32) NOT NULL,
  `Nom` varchar(32) NOT NULL,
  `Prenom` varchar(32) NOT NULL,
  `Admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Avoir`
--
ALTER TABLE `Avoir`
 ADD PRIMARY KEY (`IdU`,`idC`), ADD KEY `FK_Avoir_idC` (`idC`);

--
-- Index pour la table `Competences`
--
ALTER TABLE `Competences`
 ADD PRIMARY KEY (`idC`);

--
-- Index pour la table `Diplome`
--
ALTER TABLE `Diplome`
 ADD PRIMARY KEY (`idD`);

--
-- Index pour la table `Obtenir`
--
ALTER TABLE `Obtenir`
 ADD PRIMARY KEY (`IdU`,`idD`), ADD KEY `FK_Obtenir_idD` (`idD`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
 ADD PRIMARY KEY (`IdU`), ADD UNIQUE KEY `Mail` (`Mail`);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Avoir`
--
ALTER TABLE `Avoir`
ADD CONSTRAINT `FK_Avoir_idC` FOREIGN KEY (`idC`) REFERENCES `Competences` (`idC`),
ADD CONSTRAINT `FK_Avoir_IdU` FOREIGN KEY (`IdU`) REFERENCES `Utilisateur` (`IdU`);

--
-- Contraintes pour la table `Obtenir`
--
ALTER TABLE `Obtenir`
ADD CONSTRAINT `FK_Obtenir_idD` FOREIGN KEY (`idD`) REFERENCES `Diplome` (`idD`),
ADD CONSTRAINT `FK_Obtenir_IdU` FOREIGN KEY (`IdU`) REFERENCES `Utilisateur` (`IdU`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
