-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hardware_db
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clockSpeed`
--

DROP TABLE IF EXISTS `clockSpeed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clockSpeed` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `MinValue` decimal(10,2) NOT NULL,
  `MaxValue` decimal(10,2) DEFAULT NULL,
  `microprocessorId` int NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_microprocessorId_idx` (`microprocessorId`),
  KEY `FK_microprocessor_idx` (`microprocessorId`),
  CONSTRAINT `FK_micro` FOREIGN KEY (`microprocessorId`) REFERENCES `microprocessor` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clockSpeed`
--

LOCK TABLES `clockSpeed` WRITE;
/*!40000 ALTER TABLE `clockSpeed` DISABLE KEYS */;
INSERT INTO `clockSpeed` VALUES (1,4.77,NULL,1),(2,8.00,NULL,0),(3,10.00,NULL,5),(4,10.00,33.00,6),(5,25.00,50.00,7),(6,33.00,100.00,8),(7,50.00,150.00,9),(8,66.00,200.00,10),(29,4.77,NULL,2),(30,4.77,NULL,3),(31,4.77,NULL,4),(32,8.00,NULL,3),(33,8.00,NULL,4),(34,8.00,NULL,5);
/*!40000 ALTER TABLE `clockSpeed` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-13 18:58:04
