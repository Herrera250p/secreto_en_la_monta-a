exitexit-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: medacz
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id_admin` int NOT NULL AUTO_INCREMENT,
  `nom_admin` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) DEFAULT NULL,
  `apellido_materno` varchar(50) DEFAULT NULL,
  `id_usu` int NOT NULL,
  PRIMARY KEY (`id_admin`),
  KEY `id_usu` (`id_usu`),
  CONSTRAINT `administrador_ibfk_1` FOREIGN KEY (`id_usu`) REFERENCES `usuario` (`id_usu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arrendamiento`
--

DROP TABLE IF EXISTS `arrendamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arrendamiento` (
  `id_arr` int NOT NULL AUTO_INCREMENT,
  `fecha_arr` datetime DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `id_hos` int NOT NULL,
  `id_eqpo` int NOT NULL,
  PRIMARY KEY (`id_arr`),
  KEY `id_hos` (`id_hos`),
  KEY `id_eqpo` (`id_eqpo`),
  CONSTRAINT `arrendamiento_ibfk_1` FOREIGN KEY (`id_hos`) REFERENCES `hospital` (`id_hos`),
  CONSTRAINT `arrendamiento_ibfk_2` FOREIGN KEY (`id_eqpo`) REFERENCES `equipo_medico` (`id_eqpo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arrendamiento`
--

LOCK TABLES `arrendamiento` WRITE;
/*!40000 ALTER TABLE `arrendamiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `arrendamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checklist`
--

DROP TABLE IF EXISTS `checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checklist` (
  `id_check` int NOT NULL AUTO_INCREMENT,
  `verificaciones` varchar(100) DEFAULT NULL,
  `observaciones` varchar(150) DEFAULT NULL,
  `anomalias` varchar(150) DEFAULT NULL,
  `fecha_check` datetime DEFAULT NULL,
  `id_eqpo` int NOT NULL,
  `id_plan` int NOT NULL,
  PRIMARY KEY (`id_check`),
  KEY `id_eqpo` (`id_eqpo`),
  KEY `id_plan` (`id_plan`),
  CONSTRAINT `checklist_ibfk_1` FOREIGN KEY (`id_eqpo`) REFERENCES `equipo_medico` (`id_eqpo`),
  CONSTRAINT `checklist_ibfk_2` FOREIGN KEY (`id_plan`) REFERENCES `plantilla_checklist` (`id_plan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist`
--

LOCK TABLES `checklist` WRITE;
/*!40000 ALTER TABLE `checklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `checklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_medico`
--

DROP TABLE IF EXISTS `equipo_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo_medico` (
  `id_eqpo` int NOT NULL AUTO_INCREMENT,
  `nom_eqpo` varchar(50) NOT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `num_serie` int NOT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `id_hos` int NOT NULL,
  PRIMARY KEY (`id_eqpo`),
  KEY `id_hos` (`id_hos`),
  CONSTRAINT `equipo_medico_ibfk_1` FOREIGN KEY (`id_hos`) REFERENCES `hospital` (`id_hos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_medico`
--

LOCK TABLES `equipo_medico` WRITE;
/*!40000 ALTER TABLE `equipo_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `id_hos` int NOT NULL AUTO_INCREMENT,
  `nom_hos` varchar(50) NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `id_usu` int NOT NULL,
  PRIMARY KEY (`id_hos`),
  KEY `id_usu` (`id_usu`),
  CONSTRAINT `hospital_ibfk_1` FOREIGN KEY (`id_usu`) REFERENCES `usuario` (`id_usu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plantilla_checklist`
--

DROP TABLE IF EXISTS `plantilla_checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plantilla_checklist` (
  `id_plan` int NOT NULL AUTO_INCREMENT,
  `nom_plan` varchar(50) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_crea` datetime DEFAULT NULL,
  PRIMARY KEY (`id_plan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plantilla_checklist`
--

LOCK TABLES `plantilla_checklist` WRITE;
/*!40000 ALTER TABLE `plantilla_checklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `plantilla_checklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporte_servicio`
--

DROP TABLE IF EXISTS `reporte_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reporte_servicio` (
  `id_repo` int NOT NULL AUTO_INCREMENT,
  `fecha_repo` datetime DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `seguimiento` varchar(150) DEFAULT NULL,
  `id_soli` int NOT NULL,
  PRIMARY KEY (`id_repo`),
  KEY `id_soli` (`id_soli`),
  CONSTRAINT `reporte_servicio_ibfk_1` FOREIGN KEY (`id_soli`) REFERENCES `solicitud_servicio` (`id_soli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte_servicio`
--

LOCK TABLES `reporte_servicio` WRITE;
/*!40000 ALTER TABLE `reporte_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporte_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_servicio`
--

DROP TABLE IF EXISTS `solicitud_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud_servicio` (
  `id_soli` int NOT NULL AUTO_INCREMENT,
  `fecha_soli` datetime DEFAULT NULL,
  `tipo_servi` varchar(50) DEFAULT NULL,
  `observaciones` varchar(100) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `id_hos` int NOT NULL,
  PRIMARY KEY (`id_soli`),
  KEY `id_hos` (`id_hos`),
  CONSTRAINT `solicitud_servicio_ibfk_1` FOREIGN KEY (`id_hos`) REFERENCES `hospital` (`id_hos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_servicio`
--

LOCK TABLES `solicitud_servicio` WRITE;
/*!40000 ALTER TABLE `solicitud_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitud_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usu` int NOT NULL AUTO_INCREMENT,
  `nom_usu` varchar(50) NOT NULL,
  `pas_usu` varchar(50) NOT NULL,
  `stat_usu` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_usu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-03  5:15:30
