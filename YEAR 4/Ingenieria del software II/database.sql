-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sw2
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `competicion`
--

DROP TABLE IF EXISTS `competicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `competicion` (
  `NombreCompeticion` varchar(100) NOT NULL,
  PRIMARY KEY (`NombreCompeticion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competicion`
--

LOCK TABLES `competicion` WRITE;
/*!40000 ALTER TABLE `competicion` DISABLE KEYS */;
INSERT INTO `competicion` VALUES ('Liga Santander');
/*!40000 ALTER TABLE `competicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
  `Nombre` varchar(100) NOT NULL,
  `PosicionLiga` int NOT NULL,
  `PuntuacionTotal` int NOT NULL,
  `Competicion` varchar(100) NOT NULL,
  PRIMARY KEY (`Nombre`),
  KEY `fk_Competicion` (`Competicion`),
  CONSTRAINT `fk_Competicion` FOREIGN KEY (`Competicion`) REFERENCES `competicion` (`NombreCompeticion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES ('AgenteLibre',0,0,'Liga Santander'),('Alaves',20,0,'Liga Santander'),('Athletic',9,0,'Liga Santander'),('Atletico de Madrid',3,0,'Liga Santander'),('Barcelona',2,0,'Liga Santander'),('Betis',5,0,'Liga Santander'),('Cadiz',17,0,'Liga Santander'),('Celta',11,0,'Liga Santander'),('Elche',13,0,'Liga Santander'),('Espanyol',14,0,'Liga Santander'),('Getafe',15,0,'Liga Santander'),('Granada',18,0,'Liga Santander'),('Levante',19,0,'Liga Santander'),('Mallorca',16,0,'Liga Santander'),('Osasuna',10,0,'Liga Santander'),('Rayo',12,0,'Liga Santander'),('Real Madrid',1,0,'Liga Santander'),('Real Sociedad',7,0,'Liga Santander'),('Sevilla',4,0,'Liga Santander'),('Valencia',8,0,'Liga Santander'),('Villareal',6,0,'Liga Santander');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipojuego`
--

DROP TABLE IF EXISTS `equipojuego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipojuego` (
  `Nombre` varchar(100) NOT NULL,
  `PosicionLiga` int NOT NULL,
  `PuntuacionTotal` int NOT NULL,
  `Presupuesto` double NOT NULL,
  `nombreLiga` varchar(75) NOT NULL,
  `nombreUsuario` varchar(75) NOT NULL,
  PRIMARY KEY (`Nombre`),
  UNIQUE KEY `Nombre_UNIQUE` (`Nombre`),
  KEY `fk_nombreUsuario_idx` (`nombreUsuario`),
  KEY `fk_nombreLiga_idx` (`nombreLiga`),
  CONSTRAINT `fk_liga` FOREIGN KEY (`nombreLiga`) REFERENCES `liga` (`Nombre`),
  CONSTRAINT `fk_usuario` FOREIGN KEY (`nombreUsuario`) REFERENCES `usuario` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipojuego`
--

LOCK TABLES `equipojuego` WRITE;
/*!40000 ALTER TABLE `equipojuego` DISABLE KEYS */;
INSERT INTO `equipojuego` VALUES ('barca',1,1458,350000,'unileon','juan'),('cultural',1,1355,415000,'Laciana','gmarcg00'),('laciana',2,868,510000,'Laciana','jariar01'),('lacianiegos',2,1230,415000,'Cangas','josee'),('madrid',2,1074,485000,'unileon','pedro'),('menezacf',1,1259,420000,'meneza','cary'),('narcea',1,1297,480000,'Cangas','eva');
/*!40000 ALTER TABLE `equipojuego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada`
--

DROP TABLE IF EXISTS `jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jornada` (
  `NumJornada` int NOT NULL AUTO_INCREMENT,
  `Fecha` date DEFAULT NULL,
  `Finalizada` tinyint(1) DEFAULT NULL,
  `NombreCompeticion` varchar(100) NOT NULL,
  PRIMARY KEY (`NumJornada`),
  KEY `fk_NombreCompeticion` (`NombreCompeticion`),
  CONSTRAINT `fk_NombreCompeticion` FOREIGN KEY (`NombreCompeticion`) REFERENCES `competicion` (`NombreCompeticion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada`
--

LOCK TABLES `jornada` WRITE;
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
INSERT INTO `jornada` VALUES (1,'2022-09-14',0,'Liga Santander'),(2,'2022-09-22',0,'Liga Santander');
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `Nombre` varchar(100) NOT NULL,
  `Equipo` varchar(100) NOT NULL,
  `Nacionalidad` varchar(50) DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `PuntuacionSemanal` int DEFAULT NULL,
  `PuntuacionTotal` int DEFAULT NULL,
  `Disponible` tinyint(1) DEFAULT NULL,
  `Posicion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Nombre`),
  UNIQUE KEY `Nombre_UNIQUE` (`Nombre`),
  KEY `fk_Nombre_equipo` (`Equipo`),
  CONSTRAINT `fk_Nombre_equipo` FOREIGN KEY (`Equipo`) REFERENCES `equipo` (`Nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES ('Alaba','Real Madrid','Austria',85000,7,234,1,'DFC'),('Alba','Barcelona','Espanol',150000,8,195,1,'DFC'),('Albiol','Villareal','Espanol',19000,10,70,1,'DFC'),('Alcacer','Villareal','Espanol',8000,5,67,1,'DC'),('Ansu','Barcelona','Espanol',80000,0,45,1,'DC'),('Araujo','Barcelona','Uruguayo',220000,9,300,1,'DFC'),('Asensio','Real Madrid','Espanol',25000,5,75,1,'DC'),('Bale','Real Madrid','Gales',20000,0,45,1,'DC'),('Bartra','Betis','Espanol',47000,5,91,1,'DFC'),('Benzema','Real Madrid','Frances',100000,18,157,1,'DC'),('Berenguer','Athletic','Espanol',12000,7,55,1,'DC'),('Bono','Sevilla','Marruecos',46000,5,90,1,'POR'),('Budimir','Osasuna','Espanol',14000,7,89,1,'DC'),('Busquets','Barcelona','Espanol',250000,9,245,1,'MC'),('Camavinga','Real Madrid','Frances',80000,7,110,1,'MC'),('Canales','Betis','Espanol',70000,6,140,1,'MC'),('Carrasco','Atletico de Madrid','Belga',65000,8,197,1,'DC'),('Carvajal','Real Madrid','Espanol',75000,5,135,1,'DFC'),('Coquelin','Villareal','Frances',10000,6,86,1,'MC'),('Courtois','Real Madrid','Belga',70000,20,180,1,'POR'),('Darder','Espanyol','Espanol',31000,0,5,1,'MC'),('De Jong','Barcelona','Holandes',200000,8,180,1,'MC'),('De Paul','Atletico de Madrid','Argentino',60000,7,175,1,'MC'),('Dembele','Barcelona','Frances',145000,6,200,1,'DC'),('Depay','Barcelona','Holandes',90000,5,145,1,'DC'),('Dest','Barcelona','Estados Unidos',56000,7,98,1,'DFC'),('Djene','Getafe','Togones',9000,10,70,1,'DFC'),('Duarte','Alaves','Espanol',20000,10,45,1,'DFC'),('Eric Garcia','Barcelona','Espanol',12000,8,85,1,'DFC'),('Falcao','Rayo','Colombiano',40000,14,25,1,'DC'),('Fali','Cadiz','Espanol',2000,1,15,1,'DFC'),('Fekir','Betis','Frances',190000,8,245,1,'MC'),('Ferran','Barcelona','Espanol',90000,5,150,1,'DC'),('Foyth','Villareal','Argentino',15000,8,90,1,'DFC'),('Gaspar','Villareal','Espanol',5000,3,45,1,'DFC'),('Gavi','Barcelona','Espanol',80000,10,59,1,'MC'),('Gaya','Valencia','Espanol',90000,7,120,1,'DFC'),('Griezmann','Atletico de Madrid','Frances',11000,6,157,1,'DC'),('Guardado','Betis','Mexicano',50000,3,146,1,'MC'),('Guede','Valencia','Portugues',50000,15,91,1,'DC'),('Guedes','Valencia','Portugues',75000,5,90,1,'DC'),('Hazard','Real Madrid','Espanol',40000,4,65,1,'DC'),('Hermoso','Atletico de Madrid','Espanol',25000,5,90,1,'DFC'),('Iborra','Villareal','Espanol',23000,4,56,1,'DFC'),('Iglesias','Betis','Espanol',75000,7,120,1,'DC'),('Illarramendi','Real Sociedad','Espanol',19000,10,49,1,'MC'),('Joa Felix','Atletico de Madrid','Portugues',80000,8,240,1,'DC'),('Joaquin','Betis','Espanol',5000,2,48,1,'MC'),('Jordan','Sevilla','Espanol',50000,6,120,1,'MC'),('Jorge Molina','Granada','Espanol',17000,1,15,1,'DC'),('Jose Mari','Cadiz','Espanol',6000,5,45,1,'MC'),('Joselu','Alaves','Espanol',20000,9,29,1,'DC'),('Koke','Atletico de Madrid','Espanol',42000,10,110,1,'MC'),('Kounde','Sevilla','Frances',135000,7,205,1,'DFC'),('Kubo','Mallorca','Japones',20000,6,97,1,'DC'),('Ledesma','Cadiz','Argentino',4000,1,40,1,'POR'),('Llorente','Atletico de Madrid','Espanol',45000,7,150,1,'MC'),('Lo celso','Betis','Argentino',70000,1,76,1,'MC'),('Lodi','Atletico de Madrid','Brasileno',30000,11,99,1,'DFC'),('Lozano','Cadiz','Hondureno',10000,10,19,1,'DC'),('Maffeo','Mallorca','Espanol',10000,5,90,1,'DFC'),('Martial','Sevilla','Frances',49000,8,37,1,'DC'),('Melamed','Espanyol','Espanol',35000,6,85,1,'MC'),('Melendo','Espanyol','Espanol',25000,5,90,1,'MC'),('Melero','Levante','Espanol',21000,3,59,1,'MC'),('Modric','Real Madrid','Croata',110000,7,59,1,'MC'),('Morales','Levante','Espanol',40000,2,61,1,'DC'),('Moreno','Villareal','Espanol',14000,8,120,1,'DC'),('Muriqi','Mallorca','Kosovo',15000,4,66,1,'DC'),('Nacho Vidal','Osasuna','Espanol',10000,9,69,1,'DFC'),('Neto','Barcelona','Brasil',2000,0,18,1,'POR'),('Nico','Barcelona','Espanol',5000,0,34,1,'MC'),('Nteka','Rayo','Frances',24000,5,63,1,'MC'),('Oblak','Atletico de Madrid','Belga',50000,10,110,1,'POR'),('Ocampos','Sevilla','Aregentino',60000,7,146,1,'DC'),('Oier','Osasuna','Espanol',11000,9,71,1,'MC'),('Pacheco','Alaves','Espanol',10000,10,15,1,'POR'),('Palazón','Rayo','Espanol',34000,6,120,1,'MC'),('Parejo','Villareal','Espanol',7000,8,90,1,'MC'),('Pastore','Elche','Argentino',12000,9,90,1,'MC'),('Pau','Villareal','Espanol',10000,6,63,1,'DFC'),('Pedri','Barcelona','Espanol',80000,14,81,1,'MC'),('Pina','Alaves','Espanol',20000,4,40,1,'MC'),('Pique','Barcelona','Espanol',50000,14,86,1,'DFC'),('Puado','Espanyol','Espanol',45000,5,90,1,'DC'),('Rakitic','Sevilla','Croata',75000,7,150,1,'MC'),('RDT','Espanyol','Espanol',110000,8,295,1,'DC'),('Rulli','Villareal','Argentino',10000,6,75,1,'POR'),('Santi Mina','Celta','Espanol',27000,0,1,1,'DC'),('Sergio Herrera','Osasuna','Espanol',8000,5,51,1,'POR'),('Sergio Rico','Mallorca','Espanol',25000,1,14,1,'POR'),('Soler','Valencia','Espanol',100000,7,110,1,'MC'),('Suarez','Atletico de Madrid','Uruguayo',50000,4,78,1,'DC'),('Sylla','Rayo','Colombiano',40000,5,88,1,'DC'),('Tello','Betis','Espanol',45000,5,56,1,'MC'),('Ter Stegen','Barcelona','Aleman',55000,15,101,1,'POR'),('Trejo','Rayo','Argentino',45000,5,66,1,'DC'),('Trigueros','Villareal','Espanol',3000,4,63,1,'MC'),('Umtiti','Barcelona','Frances',4000,0,5,1,'DFC'),('Valverde','Real Madrid','Uruguayo',65000,7,100,1,'MC'),('Vesga','Athletic','Espanol',9000,8,70,1,'MC'),('Vinicius','Real Madrid','Brasileno',105000,15,129,1,'DC'),('Yeray','Athletic','Espanol',12000,8,81,1,'DFC'),('Zidane','Rayo','Frances',6000,3,43,1,'POR');
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugadorjuego`
--

DROP TABLE IF EXISTS `jugadorjuego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadorjuego` (
  `IdJugador` int NOT NULL AUTO_INCREMENT,
  `EnVenta` tinyint(1) DEFAULT NULL,
  `posicionJuego` int DEFAULT NULL,
  `NombreJugador` varchar(45) NOT NULL,
  `EquipoJuego` varchar(100) DEFAULT NULL,
  `NombreLiga` varchar(45) NOT NULL,
  PRIMARY KEY (`IdJugador`),
  KEY `fk_nombreJugador_idx` (`NombreJugador`),
  KEY `fk_equipoJuego_idx` (`EquipoJuego`),
  KEY `fk_league` (`NombreLiga`),
  CONSTRAINT `fk_equipoJuego` FOREIGN KEY (`EquipoJuego`) REFERENCES `equipojuego` (`Nombre`),
  CONSTRAINT `fk_league` FOREIGN KEY (`NombreLiga`) REFERENCES `liga` (`Nombre`),
  CONSTRAINT `fk_nombreJugador` FOREIGN KEY (`NombreJugador`) REFERENCES `jugador` (`Nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=789 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadorjuego`
--

LOCK TABLES `jugadorjuego` WRITE;
/*!40000 ALTER TABLE `jugadorjuego` DISABLE KEYS */;
INSERT INTO `jugadorjuego` VALUES (373,1,0,'Alaba',NULL,'unileon'),(374,0,0,'Alba','barca','unileon'),(375,0,1,'Albiol','madrid','unileon'),(376,0,0,'Alcacer',NULL,'unileon'),(377,0,0,'Ansu',NULL,'unileon'),(378,0,0,'Araujo',NULL,'unileon'),(379,0,1,'Asensio','barca','unileon'),(380,0,0,'Bale',NULL,'unileon'),(381,0,0,'Bartra',NULL,'unileon'),(382,0,0,'Benzema',NULL,'unileon'),(383,0,2,'Berenguer','barca','unileon'),(384,0,0,'Bono',NULL,'unileon'),(385,0,0,'Budimir',NULL,'unileon'),(386,0,0,'Busquets',NULL,'unileon'),(387,0,0,'Camavinga',NULL,'unileon'),(388,0,0,'Canales',NULL,'unileon'),(389,0,3,'Carrasco','barca','unileon'),(390,0,0,'Carvajal',NULL,'unileon'),(391,0,0,'Coquelin',NULL,'unileon'),(392,0,0,'Courtois',NULL,'unileon'),(393,0,0,'Darder',NULL,'unileon'),(394,0,4,'De Jong','barca','unileon'),(395,0,2,'De Paul','madrid','unileon'),(396,0,0,'Dembele',NULL,'unileon'),(397,0,0,'Depay',NULL,'unileon'),(398,0,0,'Dest',NULL,'unileon'),(399,0,3,'Djene','madrid','unileon'),(400,0,0,'Duarte',NULL,'unileon'),(401,0,0,'Eric Garcia',NULL,'unileon'),(402,0,5,'Falcao','barca','unileon'),(403,0,0,'Fali',NULL,'unileon'),(404,0,4,'Fekir','madrid','unileon'),(405,0,6,'Ferran','barca','unileon'),(406,0,0,'Foyth',NULL,'unileon'),(407,0,0,'Gaspar',NULL,'unileon'),(408,0,0,'Gavi',NULL,'unileon'),(409,0,0,'Gaya',NULL,'unileon'),(410,0,0,'Griezmann',NULL,'unileon'),(411,0,0,'Guardado',NULL,'unileon'),(412,0,0,'Guede',NULL,'unileon'),(413,1,0,'Guedes',NULL,'unileon'),(414,0,7,'Hazard','barca','unileon'),(415,0,0,'Hermoso',NULL,'unileon'),(416,0,5,'Iborra','madrid','unileon'),(417,0,0,'Iglesias',NULL,'unileon'),(418,0,0,'Illarramendi',NULL,'unileon'),(419,0,8,'Joa Felix','barca','unileon'),(420,0,0,'Joaquin',NULL,'unileon'),(421,0,6,'Jordan','madrid','unileon'),(422,0,0,'Jorge Molina',NULL,'unileon'),(423,0,0,'Jose Mari',NULL,'unileon'),(424,0,0,'Joselu',NULL,'unileon'),(425,0,9,'Koke','barca','unileon'),(426,0,0,'Kounde',NULL,'unileon'),(427,0,0,'Kubo',NULL,'unileon'),(428,0,7,'Ledesma','madrid','unileon'),(429,0,0,'Llorente',NULL,'unileon'),(430,0,10,'Lo celso','barca','unileon'),(431,0,0,'Lodi',NULL,'unileon'),(432,0,0,'Lozano',NULL,'unileon'),(433,0,11,'Maffeo','barca','unileon'),(434,1,0,'Martial',NULL,'unileon'),(435,0,0,'Melamed',NULL,'unileon'),(436,0,0,'Melendo',NULL,'unileon'),(437,0,0,'Melero',NULL,'unileon'),(438,0,0,'Modric',NULL,'unileon'),(439,0,0,'Morales',NULL,'unileon'),(440,0,0,'Moreno',NULL,'unileon'),(441,0,8,'Muriqi','madrid','unileon'),(442,0,0,'Nacho Vidal',NULL,'unileon'),(443,0,0,'Neto',NULL,'unileon'),(444,0,9,'Nico','madrid','unileon'),(445,0,0,'Nteka',NULL,'unileon'),(446,1,0,'Oblak',NULL,'unileon'),(447,0,0,'Ocampos',NULL,'unileon'),(448,0,0,'Oier',NULL,'unileon'),(449,0,0,'Pacheco',NULL,'unileon'),(450,0,10,'Palazón','madrid','unileon'),(451,0,0,'Parejo',NULL,'unileon'),(452,0,0,'Pastore',NULL,'unileon'),(453,0,0,'Pau',NULL,'unileon'),(454,0,0,'Pedri',NULL,'unileon'),(455,0,0,'Pina',NULL,'unileon'),(456,0,0,'Pique',NULL,'unileon'),(457,0,0,'Puado',NULL,'unileon'),(458,1,0,'Rakitic',NULL,'unileon'),(459,0,0,'RDT',NULL,'unileon'),(460,0,0,'Rulli',NULL,'unileon'),(461,0,0,'Santi Mina',NULL,'unileon'),(462,0,0,'Sergio Herrera',NULL,'unileon'),(463,0,0,'Sergio Rico',NULL,'unileon'),(464,0,0,'Soler',NULL,'unileon'),(465,0,11,'Suarez','madrid','unileon'),(466,0,0,'Sylla',NULL,'unileon'),(467,0,0,'Tello',NULL,'unileon'),(468,0,0,'Ter Stegen',NULL,'unileon'),(469,0,0,'Trejo',NULL,'unileon'),(470,0,0,'Trigueros',NULL,'unileon'),(471,0,0,'Umtiti',NULL,'unileon'),(472,0,0,'Valverde',NULL,'unileon'),(473,0,0,'Vesga',NULL,'unileon'),(474,0,0,'Vinicius',NULL,'unileon'),(475,0,0,'Yeray',NULL,'unileon'),(476,0,0,'Zidane',NULL,'unileon'),(477,0,2,'Alaba','cultural','Laciana'),(478,1,0,'Alba',NULL,'Laciana'),(479,0,0,'Albiol',NULL,'Laciana'),(480,0,0,'Alcacer',NULL,'Laciana'),(481,0,0,'Ansu','laciana','Laciana'),(482,0,0,'Araujo',NULL,'Laciana'),(483,0,0,'Asensio',NULL,'Laciana'),(484,0,0,'Bale',NULL,'Laciana'),(485,0,0,'Bartra',NULL,'Laciana'),(486,0,0,'Benzema',NULL,'Laciana'),(487,0,0,'Berenguer',NULL,'Laciana'),(488,0,0,'Bono',NULL,'Laciana'),(489,0,0,'Budimir',NULL,'Laciana'),(490,0,1,'Busquets','cultural','Laciana'),(491,0,0,'Camavinga',NULL,'Laciana'),(492,0,1,'Canales','laciana','Laciana'),(493,0,0,'Carrasco',NULL,'Laciana'),(494,0,0,'Carvajal',NULL,'Laciana'),(495,0,11,'Coquelin','cultural','Laciana'),(496,0,0,'Courtois',NULL,'Laciana'),(497,0,2,'Darder','laciana','Laciana'),(498,0,0,'De Jong',NULL,'Laciana'),(499,0,3,'De Paul','cultural','Laciana'),(500,0,0,'Dembele',NULL,'Laciana'),(501,0,0,'Depay',NULL,'Laciana'),(502,1,0,'Dest',NULL,'Laciana'),(503,1,0,'Djene',NULL,'Laciana'),(504,0,0,'Duarte',NULL,'Laciana'),(505,0,0,'Eric Garcia',NULL,'Laciana'),(506,0,0,'Falcao',NULL,'Laciana'),(507,0,0,'Fali',NULL,'Laciana'),(508,0,3,'Fekir','laciana','Laciana'),(509,0,0,'Ferran',NULL,'Laciana'),(510,0,0,'Foyth',NULL,'Laciana'),(511,0,4,'Gaspar','cultural','Laciana'),(512,0,0,'Gavi',NULL,'Laciana'),(513,0,0,'Gaya',NULL,'Laciana'),(514,0,0,'Griezmann',NULL,'Laciana'),(515,0,0,'Guardado',NULL,'Laciana'),(516,0,5,'Guede','cultural','Laciana'),(517,0,0,'Guedes',NULL,'Laciana'),(518,0,0,'Hazard',NULL,'Laciana'),(519,0,0,'Hermoso',NULL,'Laciana'),(520,0,4,'Iborra','laciana','Laciana'),(521,0,5,'Iglesias','laciana','Laciana'),(522,0,0,'Illarramendi',NULL,'Laciana'),(523,0,0,'Joa Felix',NULL,'Laciana'),(524,0,0,'Joaquin',NULL,'Laciana'),(525,0,0,'Jordan',NULL,'Laciana'),(526,0,6,'Jorge Molina','laciana','Laciana'),(527,0,0,'Jose Mari',NULL,'Laciana'),(528,0,0,'Joselu',NULL,'Laciana'),(529,0,0,'Koke',NULL,'Laciana'),(530,0,0,'Kounde',NULL,'Laciana'),(531,0,0,'Kubo',NULL,'Laciana'),(532,0,6,'Ledesma','cultural','Laciana'),(533,1,0,'Llorente',NULL,'Laciana'),(534,0,0,'Lo celso',NULL,'Laciana'),(535,0,0,'Lodi',NULL,'Laciana'),(536,0,0,'Lozano',NULL,'Laciana'),(537,0,7,'Maffeo','cultural','Laciana'),(538,0,0,'Martial',NULL,'Laciana'),(539,0,0,'Melamed',NULL,'Laciana'),(540,0,8,'Melendo','cultural','Laciana'),(541,0,0,'Melero',NULL,'Laciana'),(542,0,0,'Modric',NULL,'Laciana'),(543,0,0,'Morales',NULL,'Laciana'),(544,0,0,'Moreno',NULL,'Laciana'),(545,0,0,'Muriqi',NULL,'Laciana'),(546,0,0,'Nacho Vidal',NULL,'Laciana'),(547,0,0,'Neto',NULL,'Laciana'),(548,0,0,'Nico',NULL,'Laciana'),(549,0,0,'Nteka',NULL,'Laciana'),(550,0,0,'Oblak',NULL,'Laciana'),(551,0,0,'Ocampos',NULL,'Laciana'),(552,0,0,'Oier',NULL,'Laciana'),(553,0,7,'Pacheco','laciana','Laciana'),(554,0,0,'Palazón',NULL,'Laciana'),(555,0,0,'Parejo',NULL,'Laciana'),(556,0,0,'Pastore',NULL,'Laciana'),(557,0,0,'Pau',NULL,'Laciana'),(558,0,9,'Pedri','cultural','Laciana'),(559,0,0,'Pina',NULL,'Laciana'),(560,0,0,'Pique',NULL,'Laciana'),(561,0,10,'Puado','cultural','Laciana'),(562,0,8,'Rakitic','laciana','Laciana'),(563,0,0,'RDT',NULL,'Laciana'),(564,0,0,'Rulli',NULL,'Laciana'),(565,0,0,'Santi Mina',NULL,'Laciana'),(566,0,0,'Sergio Herrera',NULL,'Laciana'),(567,0,9,'Sergio Rico','laciana','Laciana'),(568,0,0,'Soler',NULL,'Laciana'),(569,0,0,'Suarez',NULL,'Laciana'),(570,0,0,'Sylla','cultural','Laciana'),(571,1,0,'Tello',NULL,'Laciana'),(572,0,0,'Ter Stegen',NULL,'Laciana'),(573,0,0,'Trejo',NULL,'Laciana'),(574,0,10,'Trigueros','laciana','Laciana'),(575,0,0,'Umtiti',NULL,'Laciana'),(576,0,0,'Valverde',NULL,'Laciana'),(577,0,0,'Vesga',NULL,'Laciana'),(578,0,0,'Vinicius',NULL,'Laciana'),(579,0,0,'Yeray',NULL,'Laciana'),(580,0,0,'Zidane',NULL,'Laciana'),(581,0,2,'Alaba','lacianiegos','Cangas'),(582,0,0,'Alba',NULL,'Cangas'),(583,0,0,'Albiol',NULL,'Cangas'),(584,0,0,'Alcacer',NULL,'Cangas'),(585,0,0,'Ansu',NULL,'Cangas'),(586,0,0,'Araujo',NULL,'Cangas'),(587,0,1,'Asensio','lacianiegos','Cangas'),(588,0,0,'Bale',NULL,'Cangas'),(589,0,0,'Bartra','narcea','Cangas'),(590,0,1,'Benzema','narcea','Cangas'),(591,0,0,'Berenguer',NULL,'Cangas'),(592,1,0,'Bono',NULL,'Cangas'),(593,0,0,'Budimir',NULL,'Cangas'),(594,0,11,'Busquets','lacianiegos','Cangas'),(595,0,3,'Camavinga','lacianiegos','Cangas'),(596,0,0,'Canales',NULL,'Cangas'),(597,0,0,'Carrasco',NULL,'Cangas'),(598,0,0,'Carvajal',NULL,'Cangas'),(599,0,0,'Coquelin',NULL,'Cangas'),(600,0,4,'Courtois','lacianiegos','Cangas'),(601,0,5,'Darder','lacianiegos','Cangas'),(602,0,0,'De Jong',NULL,'Cangas'),(603,0,0,'De Paul',NULL,'Cangas'),(604,0,0,'Dembele',NULL,'Cangas'),(605,0,0,'Depay',NULL,'Cangas'),(606,0,0,'Dest',NULL,'Cangas'),(607,0,2,'Djene','narcea','Cangas'),(608,0,0,'Duarte',NULL,'Cangas'),(609,0,0,'Eric Garcia',NULL,'Cangas'),(610,0,0,'Falcao',NULL,'Cangas'),(611,0,0,'Fali',NULL,'Cangas'),(612,0,0,'Fekir',NULL,'Cangas'),(613,0,0,'Ferran',NULL,'Cangas'),(614,1,0,'Foyth',NULL,'Cangas'),(615,0,0,'Gaspar',NULL,'Cangas'),(616,0,0,'Gavi',NULL,'Cangas'),(617,0,0,'Gaya',NULL,'Cangas'),(618,0,3,'Griezmann','narcea','Cangas'),(619,0,0,'Guardado',NULL,'Cangas'),(620,0,0,'Guede',NULL,'Cangas'),(621,0,4,'Guedes','narcea','Cangas'),(622,0,6,'Hazard','lacianiegos','Cangas'),(623,0,0,'Hermoso',NULL,'Cangas'),(624,0,7,'Iborra','lacianiegos','Cangas'),(625,0,0,'Iglesias',NULL,'Cangas'),(626,0,0,'Illarramendi',NULL,'Cangas'),(627,0,5,'Joa Felix','narcea','Cangas'),(628,0,0,'Joaquin',NULL,'Cangas'),(629,0,8,'Jordan','lacianiegos','Cangas'),(630,0,0,'Jorge Molina',NULL,'Cangas'),(631,0,0,'Jose Mari',NULL,'Cangas'),(632,0,0,'Joselu',NULL,'Cangas'),(633,0,0,'Koke',NULL,'Cangas'),(634,0,0,'Kounde',NULL,'Cangas'),(635,0,0,'Kubo',NULL,'Cangas'),(636,0,0,'Ledesma',NULL,'Cangas'),(637,0,0,'Llorente',NULL,'Cangas'),(638,0,0,'Lo celso',NULL,'Cangas'),(639,0,6,'Lodi','narcea','Cangas'),(640,0,0,'Lozano',NULL,'Cangas'),(641,1,0,'Maffeo',NULL,'Cangas'),(642,0,0,'Martial',NULL,'Cangas'),(643,0,0,'Melamed',NULL,'Cangas'),(644,0,0,'Melendo',NULL,'Cangas'),(645,0,0,'Melero',NULL,'Cangas'),(646,0,0,'Modric',NULL,'Cangas'),(647,0,0,'Morales',NULL,'Cangas'),(648,0,0,'Moreno',NULL,'Cangas'),(649,0,0,'Muriqi',NULL,'Cangas'),(650,0,0,'Nacho Vidal',NULL,'Cangas'),(651,0,0,'Neto',NULL,'Cangas'),(652,0,0,'Nico',NULL,'Cangas'),(653,0,0,'Nteka',NULL,'Cangas'),(654,0,0,'Oblak',NULL,'Cangas'),(655,0,0,'Ocampos',NULL,'Cangas'),(656,0,0,'Oier',NULL,'Cangas'),(657,0,0,'Pacheco',NULL,'Cangas'),(658,0,7,'Palazón','narcea','Cangas'),(659,0,0,'Parejo',NULL,'Cangas'),(660,0,0,'Pastore',NULL,'Cangas'),(661,0,0,'Pau',NULL,'Cangas'),(662,0,0,'Pedri',NULL,'Cangas'),(663,0,9,'Pina','lacianiegos','Cangas'),(664,0,10,'Pique','lacianiegos','Cangas'),(665,0,0,'Puado',NULL,'Cangas'),(666,0,0,'Rakitic',NULL,'Cangas'),(667,0,0,'RDT',NULL,'Cangas'),(668,0,0,'Rulli',NULL,'Cangas'),(669,0,0,'Santi Mina',NULL,'Cangas'),(670,1,0,'Sergio Herrera',NULL,'Cangas'),(671,0,0,'Sergio Rico','lacianiegos','Cangas'),(672,0,0,'Soler',NULL,'Cangas'),(673,0,8,'Suarez','narcea','Cangas'),(674,1,0,'Sylla',NULL,'Cangas'),(675,0,0,'Tello',NULL,'Cangas'),(676,0,0,'Ter Stegen',NULL,'Cangas'),(677,0,9,'Trejo','narcea','Cangas'),(678,0,0,'Trigueros',NULL,'Cangas'),(679,0,0,'Umtiti',NULL,'Cangas'),(680,0,0,'Valverde',NULL,'Cangas'),(681,0,0,'Vesga',NULL,'Cangas'),(682,0,10,'Vinicius','narcea','Cangas'),(683,0,0,'Yeray',NULL,'Cangas'),(684,0,0,'Zidane',NULL,'Cangas'),(685,0,0,'Alaba',NULL,'meneza'),(686,0,0,'Alba','menezacf','meneza'),(687,0,0,'Albiol',NULL,'meneza'),(688,0,0,'Alcacer',NULL,'meneza'),(689,0,0,'Ansu',NULL,'meneza'),(690,1,0,'Araujo',NULL,'meneza'),(691,0,0,'Asensio',NULL,'meneza'),(692,0,0,'Bale',NULL,'meneza'),(693,0,0,'Bartra',NULL,'meneza'),(694,0,0,'Benzema',NULL,'meneza'),(695,0,0,'Berenguer',NULL,'meneza'),(696,0,0,'Bono',NULL,'meneza'),(697,0,1,'Budimir','menezacf','meneza'),(698,0,0,'Busquets',NULL,'meneza'),(699,0,0,'Camavinga',NULL,'meneza'),(700,1,0,'Canales',NULL,'meneza'),(701,0,0,'Carrasco',NULL,'meneza'),(702,0,0,'Carvajal',NULL,'meneza'),(703,0,0,'Coquelin',NULL,'meneza'),(704,0,0,'Courtois',NULL,'meneza'),(705,0,0,'Darder',NULL,'meneza'),(706,0,0,'De Jong',NULL,'meneza'),(707,0,0,'De Paul',NULL,'meneza'),(708,0,2,'Dembele','menezacf','meneza'),(709,0,0,'Depay',NULL,'meneza'),(710,0,0,'Dest',NULL,'meneza'),(711,0,0,'Djene',NULL,'meneza'),(712,0,0,'Duarte',NULL,'meneza'),(713,0,0,'Eric Garcia',NULL,'meneza'),(714,0,0,'Falcao',NULL,'meneza'),(715,0,0,'Fali',NULL,'meneza'),(716,0,0,'Fekir',NULL,'meneza'),(717,0,0,'Ferran',NULL,'meneza'),(718,0,0,'Foyth',NULL,'meneza'),(719,1,0,'Gaspar',NULL,'meneza'),(720,0,3,'Gavi','menezacf','meneza'),(721,0,0,'Gaya',NULL,'meneza'),(722,0,0,'Griezmann',NULL,'meneza'),(723,0,0,'Guardado',NULL,'meneza'),(724,0,0,'Guede',NULL,'meneza'),(725,0,0,'Guedes',NULL,'meneza'),(726,0,0,'Hazard',NULL,'meneza'),(727,0,0,'Hermoso',NULL,'meneza'),(728,1,0,'Iborra',NULL,'meneza'),(729,0,0,'Iglesias',NULL,'meneza'),(730,0,0,'Illarramendi',NULL,'meneza'),(731,0,0,'Joa Felix',NULL,'meneza'),(732,0,0,'Joaquin',NULL,'meneza'),(733,0,0,'Jordan',NULL,'meneza'),(734,0,0,'Jorge Molina',NULL,'meneza'),(735,0,0,'Jose Mari',NULL,'meneza'),(736,0,4,'Joselu','menezacf','meneza'),(737,0,0,'Koke',NULL,'meneza'),(738,0,0,'Kounde',NULL,'meneza'),(739,0,0,'Kubo',NULL,'meneza'),(740,0,0,'Ledesma',NULL,'meneza'),(741,0,0,'Llorente',NULL,'meneza'),(742,0,0,'Lo celso',NULL,'meneza'),(743,0,0,'Lodi',NULL,'meneza'),(744,0,0,'Lozano',NULL,'meneza'),(745,0,0,'Maffeo',NULL,'meneza'),(746,0,0,'Martial',NULL,'meneza'),(747,0,0,'Melamed',NULL,'meneza'),(748,0,0,'Melendo',NULL,'meneza'),(749,0,0,'Melero',NULL,'meneza'),(750,0,0,'Modric',NULL,'meneza'),(751,0,0,'Morales',NULL,'meneza'),(752,0,5,'Moreno','menezacf','meneza'),(753,0,0,'Muriqi',NULL,'meneza'),(754,0,0,'Nacho Vidal',NULL,'meneza'),(755,0,0,'Neto',NULL,'meneza'),(756,0,0,'Nico',NULL,'meneza'),(757,0,0,'Nteka',NULL,'meneza'),(758,0,0,'Oblak',NULL,'meneza'),(759,0,0,'Ocampos',NULL,'meneza'),(760,0,11,'Oier','menezacf','meneza'),(761,0,0,'Pacheco',NULL,'meneza'),(762,0,0,'Palazón',NULL,'meneza'),(763,0,0,'Parejo',NULL,'meneza'),(764,0,0,'Pastore',NULL,'meneza'),(765,0,7,'Pau','menezacf','meneza'),(766,0,8,'Pedri','menezacf','meneza'),(767,0,0,'Pina',NULL,'meneza'),(768,1,0,'Pique',NULL,'meneza'),(769,0,0,'Puado',NULL,'meneza'),(770,0,0,'Rakitic',NULL,'meneza'),(771,0,9,'RDT','menezacf','meneza'),(772,0,0,'Rulli',NULL,'meneza'),(773,0,0,'Santi Mina',NULL,'meneza'),(774,0,0,'Sergio Herrera',NULL,'meneza'),(775,0,10,'Sergio Rico','menezacf','meneza'),(776,0,0,'Soler',NULL,'meneza'),(777,0,0,'Suarez',NULL,'meneza'),(778,0,0,'Sylla',NULL,'meneza'),(779,0,0,'Tello',NULL,'meneza'),(780,0,0,'Ter Stegen',NULL,'meneza'),(781,0,0,'Trejo',NULL,'meneza'),(782,0,0,'Trigueros',NULL,'meneza'),(783,0,0,'Umtiti',NULL,'meneza'),(784,0,0,'Valverde',NULL,'meneza'),(785,0,0,'Vesga',NULL,'meneza'),(786,0,0,'Vinicius',NULL,'meneza'),(787,0,0,'Yeray',NULL,'meneza'),(788,0,6,'Zidane','menezacf','meneza');
/*!40000 ALTER TABLE `jugadorjuego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liga`
--

DROP TABLE IF EXISTS `liga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liga` (
  `Nombre` varchar(75) NOT NULL,
  `Contraseña` varchar(50) DEFAULT NULL,
  `Completa` tinyint(1) NOT NULL,
  `NombreCompeticion` varchar(100) NOT NULL,
  PRIMARY KEY (`Nombre`),
  UNIQUE KEY `Nombre_UNIQUE` (`Nombre`),
  KEY `fk_NombreCompeticion` (`NombreCompeticion`),
  CONSTRAINT `fk_nombre_Competicion` FOREIGN KEY (`NombreCompeticion`) REFERENCES `competicion` (`NombreCompeticion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liga`
--

LOCK TABLES `liga` WRITE;
/*!40000 ALTER TABLE `liga` DISABLE KEYS */;
INSERT INTO `liga` VALUES ('Cangas','narcea',0,'Liga Santander'),('Laciana','laciana',0,'Liga Santander'),('meneza','',0,'Liga Santander'),('unileon','unileon',0,'Liga Santander');
/*!40000 ALTER TABLE `liga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partido` (
  `IdPartido` int NOT NULL AUTO_INCREMENT,
  `Fecha` date DEFAULT NULL,
  `NombreEquipoLocal` varchar(100) NOT NULL,
  `NombreEquipoVisitante` varchar(100) NOT NULL,
  `NumJornada` int NOT NULL,
  PRIMARY KEY (`IdPartido`),
  KEY `fk_NombreEquipoLocal` (`NombreEquipoLocal`),
  KEY `fk_NombreEquipoVisitante` (`NombreEquipoVisitante`),
  KEY `fk_numJornada` (`NumJornada`),
  CONSTRAINT `fk_NombreEquipoLocal` FOREIGN KEY (`NombreEquipoLocal`) REFERENCES `equipo` (`Nombre`),
  CONSTRAINT `fk_NombreEquipoVisitante` FOREIGN KEY (`NombreEquipoVisitante`) REFERENCES `equipo` (`Nombre`),
  CONSTRAINT `fk_numJornada` FOREIGN KEY (`NumJornada`) REFERENCES `jornada` (`NumJornada`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (1,'2022-09-14','Real Madrid','Getafe',1),(2,'2022-09-14','Barcelona','Levante',1),(3,'2022-09-14','Alaves','Atletico de Madrid',1),(4,'2022-09-14','Granada','Sevilla',1),(5,'2022-09-14','Betis','Cadiz',1),(6,'2022-09-15','Mallorca','Villareal',1),(7,'2022-09-15','Espanyol','Real Sociedad',1),(8,'2022-09-15','Valencia','Elche',1),(9,'2022-09-15','Rayo','Athletic',1),(10,'2022-09-15','Celta','Osasuna',1),(11,'2022-09-22','Real Madrid','Barcelona',2);
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traspaso`
--

DROP TABLE IF EXISTS `traspaso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traspaso` (
  `IdTraspaso` int NOT NULL AUTO_INCREMENT,
  `Valor` double DEFAULT NULL,
  `IdEquipoComprador` varchar(255) DEFAULT NULL,
  `IdEquipoVendedor` varchar(255) DEFAULT NULL,
  `IdJugador` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IdTraspaso`),
  KEY `FK_traspaso_IdEquipoVendedor` (`IdEquipoVendedor`),
  KEY `FK_traspaso_IdEquipoComprador` (`IdEquipoComprador`),
  KEY `FK_traspaso_IdJugador` (`IdJugador`),
  CONSTRAINT `FK_traspaso_IdEquipoComprador` FOREIGN KEY (`IdEquipoComprador`) REFERENCES `equipo` (`Nombre`),
  CONSTRAINT `FK_traspaso_IdEquipoVendedor` FOREIGN KEY (`IdEquipoVendedor`) REFERENCES `equipo` (`Nombre`),
  CONSTRAINT `FK_traspaso_IdJugador` FOREIGN KEY (`IdJugador`) REFERENCES `jugador` (`Nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traspaso`
--

LOCK TABLES `traspaso` WRITE;
/*!40000 ALTER TABLE `traspaso` DISABLE KEYS */;
/*!40000 ALTER TABLE `traspaso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `IdUsuario` varchar(75) NOT NULL,
  `Nombre` varchar(75) NOT NULL,
  `Apellido1` varchar(75) DEFAULT NULL,
  `Apellido2` varchar(75) DEFAULT NULL,
  `Sexo` enum('M','F') NOT NULL,
  `FechaNacimiento` varchar(11) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `nombreLiga` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`),
  UNIQUE KEY `IdUsuario_UNIQUE` (`IdUsuario`),
  KEY `fk_nombreLiga_idx` (`nombreLiga`),
  CONSTRAINT `fk_nombreLiga` FOREIGN KEY (`nombreLiga`) REFERENCES `liga` (`Nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('cary','Caridad','Rodriguez','Santos','F','05/08/1959','mipassword','example@gmail.com','meneza'),('eva','Eva','Diaz','Garrido','F','16/08/2000','mipassword','example@gmail.com','Cangas'),('gmarcg00','Guillermo','Marcos','Garcia','M','09/05/2000','mipassword','example@gmail.com','Laciana'),('gmarg00','Guillermo','Marcos','Garcia','M','10/11/2000','mipassword','example@gmail.com',NULL),('jariar01','Jose Maria','Arias','Rodriguez','M','10/11/2000','mipassword','example@gmail.com','Laciana'),('josee','Jose','Jose','Jose','M','10/11/2000','mipassword','example@gmail.com','Cangas'),('juan','Juan','Juan','Juan','M','10/11/2000','mipassword','example@gmail.com','unileon'),('pedro','Pedro','Pedro','Pedro','M','10/11/2000','mipassword','example@gmail.com','unileon');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `IdVenta` int NOT NULL AUTO_INCREMENT,
  `Valor` double DEFAULT NULL,
  `EquipoComprador` varchar(255) DEFAULT NULL,
  `EquipoVendedor` varchar(255) DEFAULT NULL,
  `Jugador` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IdVenta`),
  KEY `FK_venta_EquipoVendedor` (`EquipoVendedor`),
  KEY `FK_venta_EquipoComprador` (`EquipoComprador`),
  KEY `FK_venta_Jugador` (`Jugador`),
  CONSTRAINT `FK_venta_EquipoComprador` FOREIGN KEY (`EquipoComprador`) REFERENCES `equipo` (`Nombre`),
  CONSTRAINT `FK_venta_EquipoVendedor` FOREIGN KEY (`EquipoVendedor`) REFERENCES `equipo` (`Nombre`),
  CONSTRAINT `FK_venta_Jugador` FOREIGN KEY (`Jugador`) REFERENCES `jugador` (`Nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-29  0:52:46
