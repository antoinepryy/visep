-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 13 juin 2019 à 19:17
-- Version du serveur :  10.1.39-MariaDB
-- Version de PHP :  7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `visep`
--

-- --------------------------------------------------------

--
-- Structure de la table `association`
--

CREATE TABLE `association` (
  `id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` text NOT NULL,
  `recruitment` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `association`
--

INSERT INTO `association` (`id`, `admin_id`, `name`, `description`, `recruitment`) VALUES
(16, 0, 'ISEPLive', 'ISEPLive est responsable de tous les vecteurs de communication inter-élèves de l\'ISEP : affiches, sites web des associations, journal de l\'école, podcast, ...', 'A venir ... Restez à l\'affut !');

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `description` text NOT NULL,
  `association_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `date`, `description`, `association_id`) VALUES
(12, '2019-06-17', 'Afterwork', 16),
(13, '2019-06-20', 'Shooting photo de fin d\'année', 16),
(14, '2019-07-02', 'Livraison des pulls de promo', 16);

-- --------------------------------------------------------

--
-- Structure de la table `follower`
--

CREATE TABLE `follower` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `association_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `membership`
--

CREATE TABLE `membership` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `association_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `id_sender` int(11) NOT NULL,
  `id_recipient` int(11) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `first_name` varchar(256) NOT NULL,
  `last_name` varchar(256) NOT NULL,
  `password` varchar(1024) NOT NULL,
  `code` int(11) NOT NULL,
  `mail` varchar(256) NOT NULL,
  `is_admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `password`, `code`, `mail`, `is_admin`) VALUES
(13, 'Antoine', 'Perry', '1f0dea80a1af4eefa352c04bdaaa79f79433a7b458aaeaa927cb73ac9f63326a', 10373, 'antoine.perry@isep.fr', 0),
(15, 'Valentin', 'Rouleau', 'bfb301b26ca5590c4cd741bea37c36d5b4e5fb92dc4880e8c89448bf82b2b94c', 10790, 'valentin.rouleau@isep.fr', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `association`
--
ALTER TABLE `association`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `follower`
--
ALTER TABLE `follower`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `membership`
--
ALTER TABLE `membership`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `association`
--
ALTER TABLE `association`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `follower`
--
ALTER TABLE `follower`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT pour la table `membership`
--
ALTER TABLE `membership`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
