-- MySQL dump 10.13  Distrib 5.5.32, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: fourscorepicks
-- ------------------------------------------------------
-- Server version	5.5.32-0ubuntu0.12.04.1

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
-- Table structure for table `team`
--

DROP TABLE IF EXISTS team;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE team (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  location varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES team WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO team VALUES (1,'Ravens','Baltimore'),(2,'Bengals','Cincinnati'),(3,'Browns','Cleveland'),(4,'Steelers','Pittsburgh'),(5,'Texans','Houston'),(6,'Colts','Indianapolis'),(7,'Jaguars','Jacksonville'),(8,'Titans','Tennessee'),(9,'Bills','Buffalo'),(10,'Dolphins','Miami'),(11,'Patriots','New England'),(12,'Jets','New York'),(13,'Broncos','Denver'),(14,'Chiefs','Kansas City'),(15,'Raiders','Oakland'),(16,'Chargers','San Diego'),(17,'Bears','Chicago'),(18,'Lions','Detroit'),(19,'Packers','Green Bay'),(20,'Vikings','Minnesota'),(21,'Falcons','Atlanta'),(22,'Panthers','Carolina'),(23,'Saints','New Orleans'),(24,'Buccaneers','Tampa Bay'),(25,'Cowboys','Dallas'),(26,'Giants','New York'),(27,'Eagles','Philidelphia'),(28,'Redskins','Washington'),(29,'Cardinals','Arizona'),(30,'49ers','San Francisco'),(31,'Seahawks','Seattle'),(32,'Rams','St. Louis');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-11  9:08:42
