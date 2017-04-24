-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: apollo
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bringlist`
--

DROP TABLE IF EXISTS `bringlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bringlist` (
  `bring_id` int(11) NOT NULL AUTO_INCREMENT,
  `bringlist` varchar(45) NOT NULL,
  `party_id` int(11) NOT NULL,
  PRIMARY KEY (`bring_id`),
  KEY `bringlist_party_id_idx` (`party_id`),
  CONSTRAINT `bringlist_party_id` FOREIGN KEY (`party_id`) REFERENCES `party` (`party_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bringlist`
--

LOCK TABLES `bringlist` WRITE;
/*!40000 ALTER TABLE `bringlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `bringlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guestlist`
--

DROP TABLE IF EXISTS `guestlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guestlist` (
  `guest_id` int(11) NOT NULL AUTO_INCREMENT,
  `guestname` varchar(45) NOT NULL,
  `party_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`guest_id`),
  KEY `party_id_idx` (`party_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `guest_party_id` FOREIGN KEY (`party_id`) REFERENCES `party` (`party_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `guest_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guestlist`
--

LOCK TABLES `guestlist` WRITE;
/*!40000 ALTER TABLE `guestlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `guestlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music` (
  `music_id` int(11) NOT NULL AUTO_INCREMENT,
  `songList` varchar(45) NOT NULL,
  `party_id` int(11) NOT NULL,
  PRIMARY KEY (`music_id`),
  KEY `party_id_idx` (`party_id`),
  CONSTRAINT `party_id` FOREIGN KEY (`party_id`) REFERENCES `party` (`party_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partiesattending`
--

DROP TABLE IF EXISTS `partiesattending`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partiesattending` (
  `invited_id` int(11) NOT NULL AUTO_INCREMENT,
  `party_id` int(11) NOT NULL,
  `guest_id` int(11) NOT NULL,
  `going` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`invited_id`),
  KEY `invited_party_id_idx` (`party_id`),
  KEY `invited_guest_id_idx` (`guest_id`),
  CONSTRAINT `invited_guest_id` FOREIGN KEY (`guest_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `invited_party_id` FOREIGN KEY (`party_id`) REFERENCES `party` (`party_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partiesattending`
--

LOCK TABLES `partiesattending` WRITE;
/*!40000 ALTER TABLE `partiesattending` DISABLE KEYS */;
/*!40000 ALTER TABLE `partiesattending` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `party`
--

DROP TABLE IF EXISTS `party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `party` (
  `party_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `stime` varchar(45) NOT NULL,
  `etime` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `public` tinyint(1) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`party_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `party`
--

LOCK TABLES `party` WRITE;
/*!40000 ALTER TABLE `party` DISABLE KEYS */;
/*!40000 ALTER TABLE `party` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Ryan','Kennedy','rpk72167@uga.edu','dryv','test',19);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-24 15:34:42
