--
-- Base de données :  'projJava'
--

--
-- Nettoyage de la base;
--

DROP IF EXISTS TABLE Obtenir;
DROP IF EXISTS TABLE Avoir;
DROP IF EXISTS TABLE Diplome;
DROP IF EXISTS TABLE Competences;
DROP IF EXISTS TABLE Utilisateur;

-- --------------------------------------------------------

--
-- Structure de la table 'Utilisateur'
--

CREATE TABLE IF NOT EXISTS 'Utilisateur' (
  'IdU' int(11) NOT NULL,
  'Mail' varchar(32) NOT NULL,
  'Nom' varchar(32) NOT NULL,
  'Prenom' varchar(32) NOT NULL,
  'Admin' tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE 'Utilisateur'
 ADD PRIMARY KEY ('IdU'), ADD UNIQUE KEY 'Mail' ('Mail');

-- --------------------------------------------------------

--
-- Structure de la table 'Competences'
--

CREATE TABLE IF NOT EXISTS 'Competences' (
  'idC' int(11) NOT NULL,
  'NomC' varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE 'Competences'
 ADD PRIMARY KEY ('idC');

-- --------------------------------------------------------

--
-- Structure de la table 'Diplome'
--

CREATE TABLE IF NOT EXISTS 'Diplome' (
  'idD' int(11) NOT NULL,
  'NomD' varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE 'Diplome'
 ADD PRIMARY KEY ('idD');

-- --------------------------------------------------------

--
-- Structure de la table 'Avoir'
--
CREATE TABLE IF NOT EXISTS 'Avoir' 
(
  'idU' int(11) NOT NULL,
  'idC' int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE 'Avoir'
  ADD PRIMARY KEY ('IdU','idC'), ADD KEY 'FK_Avoir_idC' ('idC');

ALTER TABLE 'Avoir'
  ADD CONSTRAINT 'FK_Avoir_idC' FOREIGN KEY ('idC') REFERENCES 'Competences' ('idC'),
  ADD CONSTRAINT 'FK_Avoir_IdU' FOREIGN KEY ('IdU') REFERENCES 'Utilisateur' ('IdU');


-- --------------------------------------------------------

--
-- Structure de la table 'Obtenir'
--

CREATE TABLE IF NOT EXISTS 'Obtenir' (
  'DateD' date DEFAULT NULL,
  'IdU' int(11) NOT NULL,
  'idD' int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE 'Obtenir'
 ADD PRIMARY KEY ('IdU','idD'), ADD KEY 'FK_Obtenir_idD' ('idD');

 ALTER TABLE 'Obtenir'
  ADD CONSTRAINT 'FK_Obtenir_idD' FOREIGN KEY ('idD') REFERENCES 'Diplome' ('idD'),
  ADD CONSTRAINT 'FK_Obtenir_IdU' FOREIGN KEY ('IdU') REFERENCES 'Utilisateur' ('IdU');