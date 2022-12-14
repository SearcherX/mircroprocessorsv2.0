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
-- Table structure for table `microprocessor`
--

DROP TABLE IF EXISTS `microprocessor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `microprocessor` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Model` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `DataBitDepth` int NOT NULL,
  `AddressBitDepth` int NOT NULL,
  `AddressSpaces` bigint NOT NULL,
  `NumberOfCommands` int DEFAULT NULL,
  `NumberOfElements` int NOT NULL,
  `ReleaseYear` int NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `microprocessor`
--

LOCK TABLES `microprocessor` WRITE;
/*!40000 ALTER TABLE `microprocessor` DISABLE KEYS */;
INSERT INTO `microprocessor` VALUES (1,'4004',4,4,4000,45,2300,1971),(2,'8080',8,8,641000,NULL,10000,1974),(3,'8086',16,16,1000000,134,70000,1982),(4,'8088',16,16,1000000,134,70000,1981),(5,'80186',16,20,1000000,NULL,140000,1984),(6,'80286',16,24,4000000,NULL,180000,1985),(7,'80386',32,32,16000000,240,275000,1987),(8,'80486',32,32,16000000,240,1200000,1989),(9,'Pentium',64,32,4000000000,240,3100000,1993),(10,'Pentium Pro',64,32,4000000000,240,5500000,1995);
/*!40000 ALTER TABLE `microprocessor` ENABLE KEYS */;
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
