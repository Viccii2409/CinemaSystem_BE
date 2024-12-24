-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: cinemasystem
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `base_price`
--

DROP TABLE IF EXISTS `base_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base_price` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` varchar(255) DEFAULT NULL,
  `default_price` float NOT NULL,
  `updated_at` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_price`
--

LOCK TABLES `base_price` WRITE;
/*!40000 ALTER TABLE `base_price` DISABLE KEYS */;
INSERT INTO `base_price` VALUES (1,NULL,70000,NULL);
/*!40000 ALTER TABLE `base_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `bookingid` bigint NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `type_booking` varchar(255) DEFAULT NULL,
  `showtimeid` bigint DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  `email_customer` varchar(255) DEFAULT NULL,
  `name_customer` varchar(255) DEFAULT NULL,
  `phone_customer` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`bookingid`),
  KEY `FKddthwc6pp9ewhtrhccyxu39qs` (`showtimeid`),
  KEY `FKa3hg04mn1uok34beoh5bqs96q` (`userid`),
  CONSTRAINT `FKa3hg04mn1uok34beoh5bqs96q` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FKddthwc6pp9ewhtrhccyxu39qs` FOREIGN KEY (`showtimeid`) REFERENCES `showtime` (`showtimeid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'782367228654','2024-12-13 23:51:01.771961','ONLINE',14,1,NULL,NULL,NULL,_binary '\0'),(2,'855814063820','2024-12-13 23:55:10.204987','OFFLINE',2,1,NULL,NULL,NULL,_binary '\0'),(3,'563434532353','2024-12-14 00:14:59.515537','ONLINE',14,3,NULL,NULL,NULL,_binary '\0'),(4,'111857760447','2024-12-14 00:18:57.792649','ONLINE',14,3,NULL,NULL,NULL,_binary '\0'),(5,'954542117150','2024-12-16 11:12:38.498587','ONLINE',15,38,NULL,NULL,NULL,_binary '\0'),(6,'591395563840','2024-12-16 13:05:12.371594','ONLINE',15,38,NULL,NULL,NULL,_binary '\0'),(7,'003338419053','2024-12-16 13:17:01.596390','ONLINE',15,38,NULL,NULL,NULL,_binary '\0'),(8,'557112949026','2024-12-16 13:23:26.903169','ONLINE',15,38,NULL,NULL,NULL,_binary '\0'),(9,'270408377411','2024-12-16 13:27:02.726184','ONLINE',15,38,NULL,NULL,NULL,_binary '\0'),(10,'791103185504','2024-12-16 13:31:08.672355','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0123456789',_binary '\0'),(11,'908080878552','2024-12-16 13:32:40.798569','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0123456789',_binary '\0'),(12,'960182195152','2024-12-16 18:36:04.092513','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0123456789',_binary '\0'),(13,'860733243970','2024-12-16 18:40:18.872351','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0123456789',_binary '\0'),(14,'696620372458','2024-12-16 18:40:41.964292','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0123456789',_binary '\0'),(15,'966134681628','2024-12-16 19:02:23.473788','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(16,'321416354830','2024-12-16 19:02:37.458283','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(17,'376171764240','2024-12-16 19:02:59.189032','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(18,'551525231296','2024-12-16 19:16:30.760105','ONLINE',2,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(19,'331527281616','2024-12-16 19:16:44.195673','ONLINE',2,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(20,'799690130943','2024-12-16 19:24:46.364298','ONLINE',2,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(21,'010550362205','2024-12-16 19:29:43.380215','ONLINE',2,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(22,'022764364761','2024-12-16 19:32:01.574510','ONLINE',2,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(23,'491594540178','2024-12-16 19:35:50.796778','ONLINE',2,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(24,'561886017203','2024-12-16 20:07:47.557822','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(25,'297406069333','2024-12-16 20:12:48.464922','ONLINE',15,38,'looaann123@gmail.com','nguyen thi loan','0359926002',_binary '\0'),(26,'833115217396','2024-12-16 20:42:39.122205','OFFLINE',4,1,NULL,NULL,NULL,_binary '\0'),(27,'850695103706','2024-12-17 17:25:56.696001','OFFLINE',4,1,NULL,NULL,NULL,_binary '\0');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day_of_week`
--

DROP TABLE IF EXISTS `day_of_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `day_of_week` (
  `day_of_weekid` bigint NOT NULL AUTO_INCREMENT,
  `day_end` int NOT NULL,
  `day_start` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surcharge` float NOT NULL,
  PRIMARY KEY (`day_of_weekid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day_of_week`
--

LOCK TABLES `day_of_week` WRITE;
/*!40000 ALTER TABLE `day_of_week` DISABLE KEYS */;
INSERT INTO `day_of_week` VALUES (1,5,1,'Từ thứ 2 đến thứ 6',0),(2,7,6,'Thứ 7, Chủ nhật',10000);
/*!40000 ALTER TABLE `day_of_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discountid` bigint NOT NULL AUTO_INCREMENT,
  `description` text,
  `discount_code` varchar(255) DEFAULT NULL,
  `end` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `reduced_value` float NOT NULL,
  `start` date DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `type_discountid` bigint DEFAULT NULL,
  PRIMARY KEY (`discountid`),
  KEY `FKr0xthnbi39g12iawyth7l1ms7` (`type_discountid`),
  CONSTRAINT `FKr0xthnbi39g12iawyth7l1ms7` FOREIGN KEY (`type_discountid`) REFERENCES `type_discount` (`type_discountid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'Ưu đãi cực khủng tháng 10.2024 dành cho khách hàng của LAL Cinemas\r\nGiảm 10K cho hóa đơn từ 0k! Nhập mã: SPPCINE10, đặt vé ngay thôi! \r\n\r\n\r\n- Thời gian áp dụng: 01/11/2024 - 30/11/2024.\r\n\r\n- Áp dụng khi thực hiện giao dịch tại Website/App LAL Cinemas.','SPPCINE10','2024-11-30','https://res.cloudinary.com/dandjf6ke/image/upload/v1732442456/Image/Discount/image_1733935901293.png','DÀNH TẶNG 10K CHO CÁC LAL-ER',10000,'2024-11-01',_binary '\0',2),(2,'Giảm 10% cho hóa đơn từ 0k! Nhập mã: LALHO10, đặt vé ngay thôi! \r\n\r\n\r\n- Thời gian áp dụng: 01/11/2024 - 10/11/2024.\r\n\r\n- Áp dụng khi thực hiện giao dịch tại Website/App LAL Cinemas.','LALHO10','2024-11-10','https://res.cloudinary.com/dandjf6ke/image/upload/v1732443785/Image/Discount/image_1733936030218.jpg','GIẢM 10% KHI MUA VÉ TẠI LAL CINEMA',10,'2024-11-01',_binary '\0',1),(3,'Giảm sốc 15% khi đặt vé tại LAL Cinema','LALHO15','2024-12-31','https://res.cloudinary.com/dandjf6ke/image/upload/v1734323541/Image/Discount/image_1734323537917.jpg','GIẢM 15% KHI MUA VÉ TẠI LAL CINEMA TRONG T12',15,'2024-12-01',_binary '',1),(4,'Giảm sốc 15.000 VND trong tháng 1 khi đặt vé tại LAL Cinema.','LALD15','2025-01-31','https://res.cloudinary.com/dandjf6ke/image/upload/v1734323681/Image/Discount/image_1734323679056.jpg','GIẢM 15.000 VND KHI MUA VÉ TẠI LAL CINEMA TRONG T1',15000,'2025-01-01',_binary '',2);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedbackid` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `star` int NOT NULL,
  `text` text,
  `bookingid` bigint DEFAULT NULL,
  `movieid` bigint DEFAULT NULL,
  PRIMARY KEY (`feedbackid`),
  UNIQUE KEY `UK3q6ssn7gp95fbh3bboiqvec3r` (`bookingid`),
  KEY `FK4pses51sd6k8plfqqbr6hyvs5` (`movieid`),
  CONSTRAINT `FK4pses51sd6k8plfqqbr6hyvs5` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`),
  CONSTRAINT `FKqqc7lwxjmhp9mfqygyas77lur` FOREIGN KEY (`bookingid`) REFERENCES `booking` (`bookingid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,NULL,5,'Phim hay',2,3),(2,'2024-12-14 00:48:12.263501',5,'Phim hay, nội dung cuốn',3,3),(3,'2024-12-25 00:31:18.092384',5,'Phim hay',25,3),(4,'2024-12-25 00:45:49.081754',4,'Phim ổn',4,3);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `genreid` bigint NOT NULL AUTO_INCREMENT,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`genreid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Phim hành động với những pha gay cấn.','Hành Động'),(2,'Phim hài với những tình huống vui nhộn.','Hài Hước'),(3,'Phim kinh dị khiến bạn lạnh gáy.','Kinh Dị'),(4,'Phim tình cảm với những mối quan hệ phức tạp.','Tình Cảm'),(5,'Phim khoa học viễn tưởng về tương lai.','Khoa Học Viễn Tưởng'),(6,'Phim phiêu lưu khám phá những vùng đất mới.','Phiêu Lưu'),(7,'Phim hài kịch mang lại tiếng cười.','Hài Kịch'),(8,'Phim hoạt hình dành cho trẻ em.','Hoạt Hình'),(9,'Phim nhạc với những bài hát hấp dẫn.','Âm Nhạc'),(10,'Phim thể thao về những cuộc thi và trận đấu.','Thể Thao');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `languageid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`languageid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'Tiếng Anh'),(2,'Tiếng Việt'),(3,'Tiếng Pháp'),(4,'Tiếng Tây Ban Nha'),(5,'Tiếng Đức'),(6,'Tiếng Nhật'),(7,'Tiếng Hàn'),(8,'Tiếng Ý'),(9,'Tiếng Trung'),(10,'Tiếng Nga');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `levelid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `quantity_ticket` int NOT NULL,
  PRIMARY KEY (`levelid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (1,'Thường',0),(2,'Bạc',10),(3,'Vàng',20),(4,'Kim Cương',50);
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movieid` bigint NOT NULL AUTO_INCREMENT,
  `cast` text,
  `description` text,
  `director` varchar(255) DEFAULT NULL,
  `duration` int NOT NULL,
  `image` varchar(255) NOT NULL,
  `release_date` date DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `trailer` varchar(255) NOT NULL,
  `languageid` bigint DEFAULT NULL,
  PRIMARY KEY (`movieid`),
  KEY `FKt25khrt834p352t9ksaqtgwcg` (`languageid`),
  CONSTRAINT `FKt25khrt834p352t9ksaqtgwcg` FOREIGN KEY (`languageid`) REFERENCES `language` (`languageid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Anthony Russo','Cuộc chiến cuối cùng của các Avengers chống lại Thanos.','Robert Downey Jr., Chris Evans, Mark Ruffalo',181,'http://res.cloudinary.com/dandjf6ke/image/upload/v1733806937/Movie/tqmmyt0myasciri09uyd.jpg','2024-04-26',_binary '','Avengers: Endgame','http://res.cloudinary.com/dandjf6ke/video/upload/v1733806950/Trailer/aiz1b4h1cixpblx3ylfs.mp4',1),(2,'Frank Darabont','Một người đàn ông bị kết án oan tạo ra kế hoạch vượt ngục trong suốt 19 năm.','Morgan Freeman, Tim Robbins',142,'https://bom.so/Pz30tt','2024-09-23',_binary '','The Shawshank Redemption','https://www.youtube.com/embed/VZ1bY6xgSCo',1),(3,'Francis Ford Coppola','Câu chuyện về gia đình mafia Corleone và sự chuyển giao quyền lực.','Marlon Brando, Al Pacino',175,'https://bom.so/7OqgRg','2024-03-24',_binary '','The Godfather','https://www.youtube.com/embed/fJ9rUzIMcZQ',1),(4,'Christopher Nolan','Một nhóm người thực hiện các vụ trộm ý tưởng từ giấc mơ.','Leonardo DiCaprio, Joseph Gordon-Levitt',148,'https://i.pinimg.com/474x/c3/98/29/c39829b4176947dc7e903c696f2ca990.jpg','2024-07-16',_binary '','Inception','https://www.youtube.com/embed/5psZ9j1G5K0',2),(5,'Christopher Nolan','Batman chiến đấu với Joker để bảo vệ Gotham City.','Matthew McConaughey, Anne Hathaway',152,'https://i.pinimg.com/474x/83/81/3d/83813d55306da1bc0a8ec7044b923b2b.jpg','2024-07-18',_binary '','The Dark Knight','https://www.youtube.com/embed/YbJOTdZBX1g',1),(6,'Christopher Nolan','Câu chuyện về các tay gangster, những vụ giết người và sự ngẫu nhiên trong cuộc sống.','Christian Bale, Heath Ledger',154,'https://i.pinimg.com/474x/e4/05/0b/e4050b92335cde4a3b5ae340fc8c5ee3.jpg','2024-10-14',_binary '','Pulp Fiction','https://www.youtube.com/embed/2Vv-BfVoq4g',1),(7,'Quentin Tarantino','Một người đàn ông bình thường với một cuộc sống phi thường.','John Travolta, Uma Thurman',142,'https://upload.wikimedia.org/wikipedia/en/6/67/Forrest_Gump_poster.jpg','2024-07-06',_binary '','Forrest Gump','https://www.youtube.com/embed/W6uSMXROcGk',1),(8,'Robert Zemeckis','Một hacker khám phá ra sự thật về thế giới ảo và cuộc chiến chống lại các máy móc.','Tom Hanks',136,'https://i.pinimg.com/474x/ed/45/16/ed4516338fa5df348c13a2a7ce1e7998.jpg','2024-03-31',_binary '','The Matrix','https://www.youtube.com/embed/0KSOMA3QBU0',1),(9,'Lana Wachowski, Lilly Wachowski','Cuộc chiến cuối cùng giữa các lực lượng của Middle-earth.','Keanu Reeves, Laurence Fishburne',201,'https://bom.so/Gad76O','2024-12-17',_binary '','The Lord of the Rings: The Return of the King','https://www.youtube.com/embed/4fIxw0zMByA',1),(10,'Peter Jackson','Một nhóm phi hành gia khám phá các lỗ sâu không gian để tìm kiếm một hành tinh mới cho nhân loại.','Elijah Wood, Viggo Mortensen',169,'https://bom.so/u6EG5u','2024-11-07',_binary '','Interstellar','https://www.youtube.com/embed/E-50wD3bU5s',2),(14,'Steven Spielberg','An amazing movie that will keep you on the edge of your seat.','Tom Hanks, Mark Rylance',120,'https://bom.so/S4is4I','2024-11-06',_binary '','The Amazing Movie','https://www.youtube.com/embed/E-50wD3bU5s',2),(23,'Nguyễn Cẩm Ly, Nguyễn Phương Anh','Phim về tình yêu ','Nguyễn Thị Loan',180,'http://res.cloudinary.com/dandjf6ke/image/upload/v1733807124/Movie/um9h4arthcz68ggmbevh.jpg','2024-12-14',_binary '','Mắt biếc','http://res.cloudinary.com/dandjf6ke/video/upload/v1733807135/Trailer/ywtm02hoqxhv7u61pwuu.mp4',2),(26,'fghjk','ghjk','fghjk',12,'http://res.cloudinary.com/dandjf6ke/image/upload/v1734018240/Movie/vqqjcpgz6p91kofrswfd.jpg','2024-12-05',_binary '','wertyu','http://res.cloudinary.com/dandjf6ke/video/upload/v1734018243/Trailer/ng4zdeapihc6ncn88lta.mp4',2),(27,'abc','abc','abc',25,'http://res.cloudinary.com/dandjf6ke/image/upload/v1734020529/Movie/dvxcbtsbc0gvdjd4rplj.jpg','2024-12-13',_binary '','ABC','http://res.cloudinary.com/dandjf6ke/video/upload/v1734020607/Trailer/ich6haeo6batx3poprms.mp4',1),(28,'a','a','a',23,'http://res.cloudinary.com/dandjf6ke/image/upload/v1734091717/Movie/ievf0rqv1h5uegh50oeu.jpg','2024-12-20',_binary '','test ngày ','http://res.cloudinary.com/dandjf6ke/video/upload/v1734091729/Trailer/rgz2cpljh7vsjil6ypmj.mp4',1);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_genre` (
  `movieid` bigint NOT NULL,
  `genreid` bigint NOT NULL,
  KEY `FKc4pi5cw1cfl9mhsbu23wqxlug` (`genreid`),
  KEY `FKkwo9tdjjxhd7054hkp76bywub` (`movieid`),
  CONSTRAINT `FKc4pi5cw1cfl9mhsbu23wqxlug` FOREIGN KEY (`genreid`) REFERENCES `genre` (`genreid`),
  CONSTRAINT `FKkwo9tdjjxhd7054hkp76bywub` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_genre`
--

LOCK TABLES `movie_genre` WRITE;
/*!40000 ALTER TABLE `movie_genre` DISABLE KEYS */;
INSERT INTO `movie_genre` VALUES (2,1),(3,2),(4,3),(5,4),(6,5),(7,6),(8,7),(9,8),(10,9),(14,1),(14,3),(1,1),(23,4),(26,3),(26,4),(27,1),(27,5),(28,1),(28,2);
/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_type_customer`
--

DROP TABLE IF EXISTS `pay_type_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay_type_customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `count` int NOT NULL,
  `paymentid` bigint DEFAULT NULL,
  `type_customerid` bigint DEFAULT NULL,
  `type_seatid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK80qivoxcu76poaorttkba81ti` (`paymentid`),
  KEY `FKbovdj3vlaatyijs4p5qs9s75` (`type_customerid`),
  KEY `FK81hq8e76hdoxi0q0g6k4iar03` (`type_seatid`),
  CONSTRAINT `FK80qivoxcu76poaorttkba81ti` FOREIGN KEY (`paymentid`) REFERENCES `payment` (`paymentid`),
  CONSTRAINT `FK81hq8e76hdoxi0q0g6k4iar03` FOREIGN KEY (`type_seatid`) REFERENCES `type_seat` (`type_seatid`),
  CONSTRAINT `FKbovdj3vlaatyijs4p5qs9s75` FOREIGN KEY (`type_customerid`) REFERENCES `type_customer` (`type_customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_type_customer`
--

LOCK TABLES `pay_type_customer` WRITE;
/*!40000 ALTER TABLE `pay_type_customer` DISABLE KEYS */;
INSERT INTO `pay_type_customer` VALUES (1,1,1,5,1),(2,1,2,3,1),(3,1,3,5,1),(4,1,4,5,1),(5,1,5,5,1),(6,2,6,5,2),(7,1,7,5,2),(8,1,8,5,2),(9,1,9,5,2),(10,1,10,5,2),(11,1,11,5,2),(12,1,12,5,2),(13,1,13,5,2),(14,1,14,5,2),(15,1,15,5,2),(16,1,16,5,2),(17,1,17,5,2),(18,1,18,5,1),(19,1,19,5,1),(20,1,20,5,1),(21,1,21,5,1),(22,1,22,5,1),(23,1,23,5,1),(24,1,24,5,2),(25,1,25,5,2),(26,1,26,3,1),(27,4,27,2,1);
/*!40000 ALTER TABLE `pay_type_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `type_pay` varchar(31) NOT NULL,
  `paymentid` bigint NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `discount_price` float NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` float NOT NULL,
  `money_returned` float DEFAULT NULL,
  `received` float DEFAULT NULL,
  `date_expire` datetime(6) DEFAULT NULL,
  `bookingid` bigint DEFAULT NULL,
  `discountid` bigint DEFAULT NULL,
  PRIMARY KEY (`paymentid`),
  UNIQUE KEY `UKbw9xt5rec88hdbe4doa3p5xs7` (`bookingid`),
  KEY `FKaehjec56hsr2t4oqwypgumntd` (`discountid`),
  CONSTRAINT `FKaehjec56hsr2t4oqwypgumntd` FOREIGN KEY (`discountid`) REFERENCES `discount` (`discountid`),
  CONSTRAINT `FKewpg3143tuu48nagw3xviqu6h` FOREIGN KEY (`bookingid`) REFERENCES `booking` (`bookingid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('PAYONLINE',1,63000,'MOMO1734108661750','2024-12-13 23:51:01.821700',0,'expired',63000,NULL,NULL,'2024-12-14 01:31:01.821700',1,NULL),('PAYCASH',2,70000,'PAYCASHC7AA9FB38E7C','2024-12-13 23:55:10.224912',0,'confirmed',70000,30000,100000,NULL,2,NULL),('PAYONLINE',3,63000,'MOMO1734110099509','2024-12-14 00:14:59.546296',0,'confirmed',63000,NULL,NULL,'2024-12-14 01:54:59.544259',3,NULL),('PAYONLINE',4,63000,'MOMO1734110337786','2024-12-14 00:18:57.806985',0,'confirmed',63000,NULL,NULL,'2024-12-14 01:58:57.806985',4,NULL),('PAYONLINE',5,72000,'MOMO1734322358456','2024-12-16 11:12:38.557356',0,'expired',72000,NULL,NULL,'2024-12-16 12:52:38.554164',5,NULL),('PAYONLINE',6,164000,'MOMO1734329112349','2024-12-16 13:05:12.461816',0,'expired',164000,NULL,NULL,'2024-12-16 14:45:12.459344',6,NULL),('PAYONLINE',7,82000,'MOMO1734329821576','2024-12-16 13:17:01.750339',0,'expired',82000,NULL,NULL,'2024-12-16 14:57:01.745761',7,NULL),('PAYONLINE',8,82000,'MOMO1734330206889','2024-12-16 13:23:26.959112',0,'expired',82000,NULL,NULL,'2024-12-16 15:03:26.958053',8,NULL),('PAYONLINE',9,82000,'MOMO1734330422715','2024-12-16 14:25:51.592770',0,'confirmed',82000,NULL,NULL,'2024-12-16 15:07:02.739152',9,NULL),('PAYONLINE',10,82000,'MOMO1734330668636','2024-12-16 14:22:50.282534',0,'confirmed',82000,NULL,NULL,'2024-12-16 15:11:08.756949',10,NULL),('PAYONLINE',11,82000,'MOMO1734330760784','2024-12-16 14:16:21.772302',0,'confirmed',82000,NULL,NULL,'2024-12-16 15:12:40.844960',11,NULL),('PAYONLINE',12,82000,'MOMO1734348964081','2024-12-16 18:37:27.150941',0,'confirmed',82000,NULL,NULL,'2024-12-16 20:16:04.131803',12,NULL),('PAYONLINE',13,82000,'MOMO1734349218864','2024-12-16 18:53:23.794706',0,'confirmed',82000,NULL,NULL,'2024-12-16 20:20:18.886618',13,NULL),('PAYONLINE',14,82000,'MOMO1734349241957','2024-12-16 18:59:28.012802',0,'confirmed',82000,NULL,NULL,'2024-12-16 20:20:41.973207',14,NULL),('PAYONLINE',15,82000,'MOMO1734350543460','2024-12-16 19:04:10.941100',0,'confirmed',82000,NULL,NULL,'2024-12-16 20:42:23.499746',15,NULL),('PAYONLINE',16,82000,'MOMO1734350557451','2024-12-16 19:06:56.304890',0,'confirmed',82000,NULL,NULL,'2024-12-16 20:42:37.474404',16,NULL),('PAYONLINE',17,82000,'MOMO1734350579182','2024-12-16 19:13:32.371791',0,'confirmed',82000,NULL,NULL,'2024-12-16 20:42:59.198998',17,NULL),('PAYONLINE',18,63000,'MOMO1734351390748','2024-12-16 19:21:59.608571',0,'confirmed',63000,NULL,NULL,'2024-12-16 20:56:30.808423',18,NULL),('PAYONLINE',19,63000,'MOMO1734351404188','2024-12-16 19:28:08.603426',0,'confirmed',63000,NULL,NULL,'2024-12-16 20:56:44.207587',19,NULL),('PAYONLINE',20,63000,'MOMO1734351886357','2024-12-16 19:28:08.603426',0,'confirmed',63000,NULL,NULL,'2024-12-16 21:04:46.381237',20,NULL),('PAYONLINE',21,63000,'MOMO1734352183370','2024-12-16 19:30:37.039931',0,'confirmed',63000,NULL,NULL,'2024-12-16 21:09:43.393642',21,NULL),('PAYONLINE',22,63000,'MOMO1734352321556','2024-12-16 19:35:23.609982',0,'confirmed',63000,NULL,NULL,'2024-12-16 21:12:01.634957',22,NULL),('PAYONLINE',23,63000,'MOMO1734352550788','2024-12-16 19:36:34.628213',0,'confirmed',63000,NULL,NULL,'2024-12-16 21:15:50.808444',23,NULL),('PAYONLINE',24,82000,'MOMO1734354467377','2024-12-16 20:08:40.211499',0,'confirmed',82000,NULL,NULL,'2024-12-16 21:47:47.649826',24,NULL),('PAYONLINE',25,82000,'MOMO1734354768428','2024-12-16 20:23:47.600016',0,'confirmed',82000,NULL,NULL,'2024-12-16 21:52:48.580617',25,NULL),('PAYCASH',26,80000,'PAYCASHFEEB6AE878FD','2024-12-16 20:42:39.171769',0,'confirmed',80000,20000,100000,NULL,26,NULL),('PAYCASH',27,288000,'PAYCASH90BCD73E04BC','2024-12-17 17:25:56.842733',32000,'confirmed',320000,12000,300000,NULL,27,NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `permissionid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permissionid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'BOOKING'),(2,'VIEW_CUSTOMER_INFOR'),(3,'MANAGER_THEATER'),(4,'MANAGER_ROOM'),(5,'MANAGER_PRICETICKET'),(6,'MANAGER_GENRE'),(7,'MANAGER_MOVIE'),(8,'MANAGER_DISCOUNT'),(9,'MANAGER_SELLING'),(10,'MANAGER_SHOWTIME'),(11,'MANAGER_ROLE'),(12,'MANAGER_EMPLOYEE'),(13,'MANAGER_CUSTOMER'),(14,'MANAGER_REVENUE');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `roleid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'MANAGER'),(3,'AGENT'),(4,'CUSTOMER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `roleid` bigint NOT NULL,
  `permissionid` bigint NOT NULL,
  KEY `FK9vqqf9ycmck1ky2ltg74cgvnk` (`permissionid`),
  KEY `FKppv6whyg3nm3h21k62fwuepjg` (`roleid`),
  CONSTRAINT `FK9vqqf9ycmck1ky2ltg74cgvnk` FOREIGN KEY (`permissionid`) REFERENCES `permission` (`permissionid`),
  CONSTRAINT `FKppv6whyg3nm3h21k62fwuepjg` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (4,1),(4,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,1),(1,2),(1,10),(1,11),(1,12),(1,13),(1,14),(3,1),(3,2),(3,9),(2,2),(2,1),(2,3),(2,4),(2,5),(2,9);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `roomid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `num_column` int NOT NULL,
  `num_rows` int NOT NULL,
  `status` bit(1) NOT NULL,
  `theaterid` bigint NOT NULL,
  `type_roomid` bigint NOT NULL,
  PRIMARY KEY (`roomid`),
  KEY `FKptor8dxb82msh7uahkjvrbdnv` (`theaterid`),
  KEY `FKatsp17l3fv4b7b7i5dtbiy0e0` (`type_roomid`),
  CONSTRAINT `FKatsp17l3fv4b7b7i5dtbiy0e0` FOREIGN KEY (`type_roomid`) REFERENCES `type_room` (`type_roomid`),
  CONSTRAINT `FKptor8dxb82msh7uahkjvrbdnv` FOREIGN KEY (`theaterid`) REFERENCES `theater` (`theaterid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Phòng 1',14,10,_binary '',1,1),(2,'Phòng 2',12,10,_binary '',1,1),(3,'Phòng 3',10,8,_binary '',1,2),(4,'Phòng 1',16,12,_binary '',2,1),(5,'Phòng 1',12,10,_binary '',3,1),(11,'Phòng 2',12,10,_binary '',2,2),(12,'Phòng 1',8,8,_binary '',5,1),(13,'Phòng 1',5,5,_binary '',6,1),(14,'Phòng 1',5,5,_binary '',6,1),(15,'Phòng 1',5,5,_binary '',6,1),(16,'Phòng 4',5,5,_binary '',6,1),(17,'Phòng 5',5,5,_binary '',6,4),(18,'PHÒNG 1',10,10,_binary '',7,1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seatid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `row_num` int NOT NULL,
  `seat_num` int NOT NULL,
  `status` bit(1) NOT NULL,
  `roomid` bigint DEFAULT NULL,
  `type_seatid` bigint NOT NULL,
  PRIMARY KEY (`seatid`),
  KEY `FKry4ek81raj395pu717g8ufhbg` (`roomid`),
  KEY `FKs64fy9wiyus8ak48dsjo384em` (`type_seatid`),
  CONSTRAINT `FKry4ek81raj395pu717g8ufhbg` FOREIGN KEY (`roomid`) REFERENCES `room` (`roomid`),
  CONSTRAINT `FKs64fy9wiyus8ak48dsjo384em` FOREIGN KEY (`type_seatid`) REFERENCES `type_seat` (`type_seatid`)
) ENGINE=InnoDB AUTO_INCREMENT=1605 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,'A01',0,0,_binary '',1,1),(2,'A02',0,1,_binary '',1,1),(3,NULL,0,2,_binary '\0',1,1),(4,NULL,0,3,_binary '\0',1,1),(5,NULL,0,4,_binary '\0',1,1),(6,NULL,0,5,_binary '\0',1,1),(7,NULL,0,6,_binary '\0',1,1),(8,NULL,0,7,_binary '\0',1,1),(9,NULL,0,8,_binary '\0',1,1),(10,NULL,0,9,_binary '\0',1,1),(11,NULL,0,10,_binary '\0',1,1),(12,NULL,0,11,_binary '\0',1,1),(13,NULL,0,12,_binary '\0',1,1),(14,NULL,0,13,_binary '\0',1,1),(15,'B01',1,0,_binary '',1,1),(16,'B02',1,1,_binary '',1,1),(17,'B03',1,2,_binary '',1,1),(18,'B04',1,3,_binary '',1,1),(19,'B05',1,4,_binary '',1,1),(20,'B06',1,5,_binary '',1,1),(21,'B07',1,6,_binary '',1,1),(22,'B08',1,7,_binary '',1,1),(23,'B09',1,8,_binary '',1,1),(24,'B10',1,9,_binary '',1,1),(25,NULL,1,10,_binary '\0',1,1),(26,NULL,1,11,_binary '\0',1,1),(27,'B11',1,12,_binary '',1,3),(28,'B12',1,13,_binary '',1,3),(29,'C01',2,0,_binary '',1,1),(30,'C02',2,1,_binary '',1,1),(31,'C03',2,2,_binary '',1,1),(32,'C04',2,3,_binary '',1,1),(33,'C05',2,4,_binary '',1,1),(34,'C06',2,5,_binary '',1,1),(35,'C07',2,6,_binary '',1,1),(36,'C08',2,7,_binary '',1,1),(37,'C09',2,8,_binary '',1,1),(38,'C10',2,9,_binary '',1,1),(39,NULL,2,10,_binary '\0',1,1),(40,NULL,2,11,_binary '\0',1,1),(41,'C11',2,12,_binary '',1,3),(42,'C12',2,13,_binary '',1,3),(43,'D01',3,0,_binary '',1,1),(44,'D02',3,1,_binary '',1,1),(45,'D03',3,2,_binary '',1,1),(46,'D04',3,3,_binary '',1,1),(47,'D05',3,4,_binary '',1,1),(48,'D06',3,5,_binary '',1,1),(49,'D07',3,6,_binary '',1,1),(50,'D08',3,7,_binary '',1,1),(51,'D09',3,8,_binary '',1,1),(52,'D10',3,9,_binary '',1,1),(53,NULL,3,10,_binary '\0',1,1),(54,NULL,3,11,_binary '\0',1,1),(55,'D11',3,12,_binary '',1,3),(56,'D12',3,13,_binary '',1,3),(57,'E01',4,0,_binary '',1,1),(58,'E02',4,1,_binary '',1,1),(59,'E03',4,2,_binary '',1,1),(60,'E04',4,3,_binary '',1,1),(61,'E05',4,4,_binary '',1,1),(62,'E06',4,5,_binary '',1,1),(63,'E07',4,6,_binary '',1,1),(64,'E08',4,7,_binary '',1,1),(65,'E09',4,8,_binary '',1,1),(66,'E10',4,9,_binary '',1,1),(67,NULL,4,10,_binary '\0',1,1),(68,NULL,4,11,_binary '\0',1,1),(69,'E11',4,12,_binary '',1,3),(70,'E12',4,13,_binary '',1,3),(71,'F01',5,0,_binary '',1,2),(72,'F02',5,1,_binary '',1,2),(73,'F03',5,2,_binary '',1,2),(74,'F04',5,3,_binary '',1,2),(75,'F05',5,4,_binary '',1,2),(76,'F06',5,5,_binary '',1,2),(77,'F07',5,6,_binary '',1,2),(78,'F08',5,7,_binary '',1,2),(79,'F09',5,8,_binary '',1,2),(80,'F10',5,9,_binary '',1,2),(81,NULL,5,10,_binary '\0',1,1),(82,NULL,5,11,_binary '\0',1,1),(83,'F11',5,12,_binary '',1,3),(84,'F12',5,13,_binary '',1,3),(85,'G01',6,0,_binary '',1,2),(86,'G02',6,1,_binary '',1,2),(87,'G03',6,2,_binary '',1,2),(88,'G04',6,3,_binary '',1,2),(89,'G05',6,4,_binary '',1,2),(90,'G06',6,5,_binary '',1,2),(91,'G07',6,6,_binary '',1,2),(92,'G08',6,7,_binary '',1,2),(93,'G09',6,8,_binary '',1,2),(94,'G10',6,9,_binary '',1,2),(95,NULL,6,10,_binary '\0',1,1),(96,NULL,6,11,_binary '\0',1,1),(97,'G11',6,12,_binary '',1,3),(98,'G12',6,13,_binary '',1,3),(99,'H01',7,0,_binary '',1,2),(100,'H02',7,1,_binary '',1,2),(101,'H03',7,2,_binary '',1,2),(102,'H04',7,3,_binary '',1,2),(103,'H05',7,4,_binary '',1,2),(104,'H06',7,5,_binary '',1,2),(105,'H07',7,6,_binary '',1,2),(106,'H08',7,7,_binary '',1,2),(107,'H09',7,8,_binary '',1,2),(108,'H10',7,9,_binary '',1,2),(109,NULL,7,10,_binary '\0',1,1),(110,NULL,7,11,_binary '\0',1,1),(111,'H11',7,12,_binary '',1,3),(112,'H12',7,13,_binary '',1,3),(113,'I01',8,0,_binary '',1,2),(114,'I02',8,1,_binary '',1,2),(115,'I03',8,2,_binary '',1,2),(116,'I04',8,3,_binary '',1,2),(117,'I05',8,4,_binary '',1,2),(118,'I06',8,5,_binary '',1,2),(119,'I07',8,6,_binary '',1,2),(120,'I08',8,7,_binary '',1,2),(121,'I09',8,8,_binary '',1,2),(122,'I10',8,9,_binary '',1,2),(123,NULL,8,10,_binary '\0',1,1),(124,NULL,8,11,_binary '\0',1,1),(125,'I11',8,12,_binary '',1,3),(126,'I12',8,13,_binary '',1,3),(127,'J01',9,0,_binary '',1,2),(128,'J02',9,1,_binary '',1,2),(129,'J03',9,2,_binary '',1,2),(130,'J04',9,3,_binary '',1,2),(131,'J05',9,4,_binary '',1,2),(132,'J06',9,5,_binary '',1,2),(133,'J07',9,6,_binary '',1,2),(134,'J08',9,7,_binary '',1,2),(135,'J09',9,8,_binary '',1,2),(136,'J10',9,9,_binary '',1,2),(137,NULL,9,10,_binary '\0',1,1),(138,NULL,9,11,_binary '\0',1,1),(139,'J11',9,12,_binary '',1,3),(140,'J12',9,13,_binary '',1,3),(141,NULL,0,0,_binary '\0',2,1),(142,'A01',0,1,_binary '',2,1),(143,'A02',0,2,_binary '',2,1),(144,'A03',0,3,_binary '',2,1),(145,'A04',0,4,_binary '',2,1),(146,'A05',0,5,_binary '',2,1),(147,'A06',0,6,_binary '',2,1),(148,'A07',0,7,_binary '',2,1),(149,'A08',0,8,_binary '',2,1),(150,'A09',0,9,_binary '',2,1),(151,'A10',0,10,_binary '',2,1),(152,'A11',0,11,_binary '',2,1),(153,NULL,1,0,_binary '\0',2,1),(154,'B01',1,1,_binary '',2,1),(155,'B02',1,2,_binary '',2,1),(156,'B03',1,3,_binary '',2,1),(157,'B04',1,4,_binary '',2,1),(158,'B05',1,5,_binary '',2,1),(159,'B06',1,6,_binary '',2,1),(160,'B07',1,7,_binary '',2,1),(161,'B08',1,8,_binary '',2,1),(162,'B09',1,9,_binary '',2,1),(163,'B10',1,10,_binary '',2,1),(164,'B11',1,11,_binary '',2,1),(165,NULL,2,0,_binary '\0',2,1),(166,'C01',2,1,_binary '',2,1),(167,'C02',2,2,_binary '',2,1),(168,'C03',2,3,_binary '',2,1),(169,'C04',2,4,_binary '',2,1),(170,'C05',2,5,_binary '',2,1),(171,'C06',2,6,_binary '',2,1),(172,'C07',2,7,_binary '',2,1),(173,'C08',2,8,_binary '',2,1),(174,'C09',2,9,_binary '',2,1),(175,'C10',2,10,_binary '',2,1),(176,'C11',2,11,_binary '',2,1),(177,NULL,3,0,_binary '\0',2,1),(178,'D01',3,1,_binary '',2,1),(179,'D02',3,2,_binary '',2,1),(180,'D03',3,3,_binary '',2,1),(181,'D04',3,4,_binary '',2,1),(182,'D05',3,5,_binary '',2,1),(183,'D06',3,6,_binary '',2,1),(184,'D07',3,7,_binary '',2,1),(185,'D08',3,8,_binary '',2,1),(186,'D09',3,9,_binary '',2,1),(187,'D10',3,10,_binary '',2,1),(188,'D11',3,11,_binary '',2,1),(189,'E01',4,0,_binary '',2,2),(190,'E02',4,1,_binary '',2,2),(191,'E03',4,2,_binary '',2,2),(192,'E04',4,3,_binary '',2,2),(193,'E05',4,4,_binary '',2,2),(194,'E06',4,5,_binary '',2,2),(195,'E07',4,6,_binary '',2,2),(196,'E08',4,7,_binary '',2,2),(197,'E09',4,8,_binary '',2,2),(198,'E10',4,9,_binary '',2,2),(199,'E11',4,10,_binary '',2,2),(200,'E12',4,11,_binary '',2,2),(201,'F01',5,0,_binary '',2,2),(202,'F02',5,1,_binary '',2,2),(203,'F03',5,2,_binary '',2,2),(204,'F04',5,3,_binary '',2,2),(205,'F05',5,4,_binary '',2,2),(206,'F06',5,5,_binary '',2,2),(207,'F07',5,6,_binary '',2,2),(208,'F08',5,7,_binary '',2,2),(209,'F09',5,8,_binary '',2,2),(210,'F10',5,9,_binary '',2,2),(211,'F11',5,10,_binary '',2,2),(212,'F12',5,11,_binary '',2,2),(213,'G01',6,0,_binary '',2,2),(214,'G02',6,1,_binary '',2,2),(215,'G03',6,2,_binary '',2,2),(216,'G04',6,3,_binary '',2,2),(217,'G05',6,4,_binary '',2,2),(218,'G06',6,5,_binary '',2,2),(219,'G07',6,6,_binary '',2,2),(220,'G08',6,7,_binary '',2,2),(221,'G09',6,8,_binary '',2,2),(222,'G10',6,9,_binary '',2,2),(223,'G11',6,10,_binary '',2,2),(224,'G12',6,11,_binary '',2,2),(225,'H01',7,0,_binary '',2,2),(226,'H02',7,1,_binary '',2,2),(227,'H03',7,2,_binary '',2,2),(228,'H04',7,3,_binary '',2,2),(229,'H05',7,4,_binary '',2,2),(230,'H06',7,5,_binary '',2,2),(231,'H07',7,6,_binary '',2,2),(232,'H08',7,7,_binary '',2,2),(233,'H09',7,8,_binary '',2,2),(234,'H10',7,9,_binary '',2,2),(235,'H11',7,10,_binary '',2,2),(236,'H12',7,11,_binary '',2,2),(237,'I01',8,0,_binary '',2,3),(238,'I02',8,1,_binary '',2,3),(239,'I03',8,2,_binary '',2,3),(240,'I04',8,3,_binary '',2,3),(241,'I05',8,4,_binary '',2,3),(242,'I06',8,5,_binary '',2,3),(243,'I07',8,6,_binary '',2,3),(244,'I08',8,7,_binary '',2,3),(245,'I09',8,8,_binary '',2,3),(246,'I10',8,9,_binary '',2,3),(247,'I11',8,10,_binary '',2,3),(248,'I12',8,11,_binary '',2,3),(249,'J01',9,0,_binary '',2,3),(250,'J02',9,1,_binary '',2,3),(251,'J03',9,2,_binary '',2,3),(252,'J04',9,3,_binary '',2,3),(253,'J05',9,4,_binary '',2,3),(254,'J06',9,5,_binary '',2,3),(255,'J07',9,6,_binary '',2,3),(256,'J08',9,7,_binary '',2,3),(257,'J09',9,8,_binary '',2,3),(258,'J10',9,9,_binary '',2,3),(259,'J11',9,10,_binary '',2,3),(260,'J12',9,11,_binary '',2,3),(261,'A01',0,0,_binary '',3,1),(262,'A02',0,1,_binary '',3,1),(263,NULL,0,2,_binary '\0',3,1),(264,NULL,0,3,_binary '\0',3,1),(265,NULL,0,4,_binary '\0',3,1),(266,NULL,0,5,_binary '\0',3,1),(267,NULL,0,6,_binary '\0',3,1),(268,NULL,0,7,_binary '\0',3,1),(269,'A03',0,8,_binary '',3,1),(270,'A04',0,9,_binary '',3,1),(271,'B01',1,0,_binary '',3,1),(272,'B02',1,1,_binary '',3,1),(273,'B03',1,2,_binary '',3,1),(274,'B04',1,3,_binary '',3,1),(275,'B05',1,4,_binary '',3,1),(276,'B06',1,5,_binary '',3,1),(277,'B07',1,6,_binary '',3,1),(278,'B08',1,7,_binary '',3,1),(279,'B09',1,8,_binary '',3,1),(280,'B10',1,9,_binary '',3,1),(281,'C01',2,0,_binary '',3,1),(282,'C02',2,1,_binary '',3,1),(283,'C03',2,2,_binary '',3,1),(284,'C04',2,3,_binary '',3,1),(285,'C05',2,4,_binary '',3,1),(286,'C06',2,5,_binary '',3,1),(287,'C07',2,6,_binary '',3,1),(288,'C08',2,7,_binary '',3,1),(289,'C09',2,8,_binary '',3,1),(290,'C10',2,9,_binary '',3,1),(291,'D01',3,0,_binary '',3,1),(292,'D02',3,1,_binary '',3,1),(293,'D03',3,2,_binary '',3,1),(294,'D04',3,3,_binary '',3,1),(295,'D05',3,4,_binary '',3,1),(296,'D06',3,5,_binary '',3,1),(297,'D07',3,6,_binary '',3,1),(298,'D08',3,7,_binary '',3,1),(299,'D09',3,8,_binary '',3,1),(300,'D10',3,9,_binary '',3,1),(301,'E01',4,0,_binary '',3,2),(302,'E02',4,1,_binary '',3,2),(303,'E03',4,2,_binary '',3,2),(304,'E04',4,3,_binary '',3,2),(305,'E05',4,4,_binary '',3,2),(306,'E06',4,5,_binary '',3,2),(307,'E07',4,6,_binary '',3,2),(308,'E08',4,7,_binary '',3,2),(309,'E09',4,8,_binary '',3,2),(310,'E10',4,9,_binary '',3,2),(311,'F01',5,0,_binary '',3,2),(312,'F02',5,1,_binary '',3,2),(313,'F03',5,2,_binary '',3,2),(314,'F04',5,3,_binary '',3,2),(315,'F05',5,4,_binary '',3,2),(316,'F06',5,5,_binary '',3,2),(317,'F07',5,6,_binary '',3,2),(318,'F08',5,7,_binary '',3,2),(319,'F09',5,8,_binary '',3,2),(320,'F10',5,9,_binary '',3,2),(321,'G01',6,0,_binary '',3,2),(322,'G02',6,1,_binary '',3,2),(323,'G03',6,2,_binary '',3,2),(324,'G04',6,3,_binary '',3,2),(325,'G05',6,4,_binary '',3,2),(326,'G06',6,5,_binary '',3,2),(327,'G07',6,6,_binary '',3,2),(328,'G08',6,7,_binary '',3,2),(329,'G09',6,8,_binary '',3,2),(330,'G10',6,9,_binary '',3,2),(331,'H01',7,0,_binary '',3,2),(332,'H02',7,1,_binary '',3,2),(333,'H03',7,2,_binary '',3,2),(334,'H04',7,3,_binary '',3,2),(335,'H05',7,4,_binary '',3,2),(336,'H06',7,5,_binary '',3,2),(337,'H07',7,6,_binary '',3,2),(338,'H08',7,7,_binary '',3,2),(339,'H09',7,8,_binary '',3,2),(340,'H10',7,9,_binary '',3,2),(341,'A01',0,0,_binary '',5,1),(342,'A02',0,1,_binary '',5,1),(343,'A03',0,2,_binary '',5,1),(344,'A04',0,3,_binary '',5,1),(345,'A05',0,4,_binary '',5,1),(346,'A06',0,5,_binary '',5,1),(347,'A07',0,6,_binary '',5,1),(348,'A08',0,7,_binary '',5,1),(349,'A09',0,8,_binary '',5,1),(350,'A10',0,9,_binary '',5,1),(351,'B01',1,0,_binary '',5,1),(352,'B02',1,1,_binary '',5,1),(353,'B03',1,2,_binary '',5,1),(354,'B04',1,3,_binary '',5,1),(355,'B05',1,4,_binary '',5,1),(356,'B06',1,5,_binary '',5,1),(357,'B07',1,6,_binary '',5,1),(358,'B08',1,7,_binary '',5,1),(359,'B09',1,8,_binary '',5,1),(360,'B10',1,9,_binary '',5,1),(361,'C01',2,0,_binary '',5,1),(362,'C02',2,1,_binary '',5,1),(363,'C03',2,2,_binary '',5,1),(364,'C04',2,3,_binary '',5,1),(365,'C05',2,4,_binary '',5,1),(366,'C06',2,5,_binary '',5,1),(367,'C07',2,6,_binary '',5,1),(368,'C08',2,7,_binary '',5,1),(369,'C09',2,8,_binary '',5,1),(370,'C10',2,9,_binary '',5,1),(371,'D01',3,0,_binary '',5,2),(372,'D02',3,1,_binary '',5,2),(373,'D03',3,2,_binary '',5,2),(374,'D04',3,3,_binary '',5,2),(375,'D05',3,4,_binary '',5,2),(376,'D06',3,5,_binary '',5,2),(377,'D07',3,6,_binary '',5,2),(378,'D08',3,7,_binary '',5,2),(379,'D09',3,8,_binary '',5,2),(380,'D10',3,9,_binary '',5,2),(381,'E01',4,0,_binary '',5,2),(382,'E02',4,1,_binary '',5,2),(383,'E03',4,2,_binary '',5,2),(384,'E04',4,3,_binary '',5,2),(385,'E05',4,4,_binary '',5,2),(386,'E06',4,5,_binary '',5,2),(387,'E07',4,6,_binary '',5,2),(388,'E08',4,7,_binary '',5,2),(389,'E09',4,8,_binary '',5,2),(390,'E10',4,9,_binary '',5,2),(391,'F01',5,0,_binary '',5,2),(392,'F02',5,1,_binary '',5,2),(393,'F03',5,2,_binary '',5,2),(394,'F04',5,3,_binary '',5,2),(395,'F05',5,4,_binary '',5,2),(396,'F06',5,5,_binary '',5,2),(397,'F07',5,6,_binary '',5,2),(398,'F08',5,7,_binary '',5,2),(399,'F09',5,8,_binary '',5,2),(400,'F10',5,9,_binary '',5,2),(401,'G01',6,0,_binary '',5,2),(402,'G02',6,1,_binary '',5,2),(403,'G03',6,2,_binary '',5,2),(404,'G04',6,3,_binary '',5,2),(405,'G05',6,4,_binary '',5,2),(406,'G06',6,5,_binary '',5,2),(407,'G07',6,6,_binary '',5,2),(408,'G08',6,7,_binary '',5,2),(409,'G09',6,8,_binary '',5,2),(410,'G10',6,9,_binary '',5,2),(411,'H01',7,0,_binary '',5,2),(412,'H02',7,1,_binary '',5,2),(413,'H03',7,2,_binary '',5,2),(414,'H04',7,3,_binary '',5,2),(415,'H05',7,4,_binary '',5,2),(416,'H06',7,5,_binary '',5,2),(417,'H07',7,6,_binary '',5,2),(418,'H08',7,7,_binary '',5,2),(419,'H09',7,8,_binary '',5,2),(420,'H10',7,9,_binary '',5,2),(421,NULL,0,0,_binary '\0',4,1),(422,'A01',0,1,_binary '',4,1),(423,'A02',0,2,_binary '',4,1),(424,'A03',0,3,_binary '',4,1),(425,'A04',0,4,_binary '',4,1),(426,'A05',0,5,_binary '',4,1),(427,'A06',0,6,_binary '',4,1),(428,'A07',0,7,_binary '',4,1),(429,'A08',0,8,_binary '',4,1),(430,'A09',0,9,_binary '',4,1),(431,'A10',0,10,_binary '',4,1),(432,'A11',0,11,_binary '',4,1),(433,'A12',0,12,_binary '',4,1),(434,'A13',0,13,_binary '',4,1),(435,'A14',0,14,_binary '',4,1),(436,'A15',0,15,_binary '',4,1),(437,NULL,1,0,_binary '\0',4,1),(438,'B01',1,1,_binary '',4,1),(439,'B02',1,2,_binary '',4,1),(440,'B03',1,3,_binary '',4,1),(441,'B04',1,4,_binary '',4,1),(442,'B05',1,5,_binary '',4,1),(443,'B06',1,6,_binary '',4,1),(444,'B07',1,7,_binary '',4,1),(445,'B08',1,8,_binary '',4,1),(446,'B09',1,9,_binary '',4,1),(447,'B10',1,10,_binary '',4,1),(448,'B11',1,11,_binary '',4,1),(449,'B12',1,12,_binary '',4,1),(450,'B13',1,13,_binary '',4,1),(451,'B14',1,14,_binary '',4,1),(452,'B15',1,15,_binary '',4,1),(453,NULL,2,0,_binary '\0',4,1),(454,'C01',2,1,_binary '',4,1),(455,'C02',2,2,_binary '',4,1),(456,'C03',2,3,_binary '',4,1),(457,'C04',2,4,_binary '',4,1),(458,'C05',2,5,_binary '',4,1),(459,'C06',2,6,_binary '',4,1),(460,'C07',2,7,_binary '',4,1),(461,'C08',2,8,_binary '',4,1),(462,'C09',2,9,_binary '',4,1),(463,'C10',2,10,_binary '',4,1),(464,'C11',2,11,_binary '',4,1),(465,'C12',2,12,_binary '',4,1),(466,'C13',2,13,_binary '',4,1),(467,'C14',2,14,_binary '',4,1),(468,'C15',2,15,_binary '',4,1),(469,NULL,3,0,_binary '\0',4,1),(470,'D01',3,1,_binary '',4,1),(471,'D02',3,2,_binary '',4,1),(472,'D03',3,3,_binary '',4,1),(473,'D04',3,4,_binary '',4,1),(474,'D05',3,5,_binary '',4,1),(475,'D06',3,6,_binary '',4,1),(476,'D07',3,7,_binary '',4,1),(477,'D08',3,8,_binary '',4,1),(478,'D09',3,9,_binary '',4,1),(479,'D10',3,10,_binary '',4,1),(480,'D11',3,11,_binary '',4,1),(481,'D12',3,12,_binary '',4,1),(482,'D13',3,13,_binary '',4,1),(483,'D14',3,14,_binary '',4,1),(484,'D15',3,15,_binary '',4,1),(485,'E01',4,0,_binary '',4,2),(486,'E02',4,1,_binary '',4,2),(487,'E03',4,2,_binary '',4,2),(488,'E04',4,3,_binary '',4,2),(489,'E05',4,4,_binary '',4,2),(490,'E06',4,5,_binary '',4,2),(491,'E07',4,6,_binary '',4,2),(492,'E08',4,7,_binary '',4,2),(493,'E09',4,8,_binary '',4,2),(494,'E10',4,9,_binary '',4,2),(495,'E11',4,10,_binary '',4,2),(496,'E12',4,11,_binary '',4,2),(497,'E13',4,12,_binary '',4,2),(498,'E14',4,13,_binary '',4,2),(499,'E15',4,14,_binary '',4,2),(500,'E16',4,15,_binary '',4,2),(501,'F01',5,0,_binary '',4,2),(502,'F02',5,1,_binary '',4,2),(503,'F03',5,2,_binary '',4,2),(504,'F04',5,3,_binary '',4,2),(505,'F05',5,4,_binary '',4,2),(506,'F06',5,5,_binary '',4,2),(507,'F07',5,6,_binary '',4,2),(508,'F08',5,7,_binary '',4,2),(509,'F09',5,8,_binary '',4,2),(510,'F10',5,9,_binary '',4,2),(511,'F11',5,10,_binary '',4,2),(512,'F12',5,11,_binary '',4,2),(513,'F13',5,12,_binary '',4,2),(514,'F14',5,13,_binary '',4,2),(515,'F15',5,14,_binary '',4,2),(516,'F16',5,15,_binary '',4,2),(517,'G01',6,0,_binary '',4,2),(518,'G02',6,1,_binary '',4,2),(519,'G03',6,2,_binary '',4,2),(520,'G04',6,3,_binary '',4,2),(521,'G05',6,4,_binary '',4,2),(522,'G06',6,5,_binary '',4,2),(523,'G07',6,6,_binary '',4,2),(524,'G08',6,7,_binary '',4,2),(525,'G09',6,8,_binary '',4,2),(526,'G10',6,9,_binary '',4,2),(527,'G11',6,10,_binary '',4,2),(528,'G12',6,11,_binary '',4,2),(529,'G13',6,12,_binary '',4,2),(530,'G14',6,13,_binary '',4,2),(531,'G15',6,14,_binary '',4,2),(532,'G16',6,15,_binary '',4,2),(533,'H01',7,0,_binary '',4,2),(534,'H02',7,1,_binary '',4,2),(535,'H03',7,2,_binary '',4,2),(536,'H04',7,3,_binary '',4,2),(537,'H05',7,4,_binary '',4,2),(538,'H06',7,5,_binary '',4,2),(539,'H07',7,6,_binary '',4,2),(540,'H08',7,7,_binary '',4,2),(541,'H09',7,8,_binary '',4,2),(542,'H10',7,9,_binary '',4,2),(543,'H11',7,10,_binary '',4,2),(544,'H12',7,11,_binary '',4,2),(545,'H13',7,12,_binary '',4,2),(546,'H14',7,13,_binary '',4,2),(547,'H15',7,14,_binary '',4,2),(548,'H16',7,15,_binary '',4,2),(549,'I01',8,0,_binary '',4,2),(550,'I02',8,1,_binary '',4,2),(551,'I03',8,2,_binary '',4,2),(552,'I04',8,3,_binary '',4,2),(553,'I05',8,4,_binary '',4,2),(554,'I06',8,5,_binary '',4,2),(555,'I07',8,6,_binary '',4,2),(556,'I08',8,7,_binary '',4,2),(557,'I09',8,8,_binary '',4,2),(558,'I10',8,9,_binary '',4,2),(559,'I11',8,10,_binary '',4,2),(560,'I12',8,11,_binary '',4,2),(561,'I13',8,12,_binary '',4,2),(562,'I14',8,13,_binary '',4,2),(563,'I15',8,14,_binary '',4,2),(564,'I16',8,15,_binary '',4,2),(565,'J01',9,0,_binary '',4,3),(566,'J02',9,1,_binary '',4,3),(567,'J03',9,2,_binary '',4,3),(568,'J04',9,3,_binary '',4,3),(569,'J05',9,4,_binary '',4,3),(570,'J06',9,5,_binary '',4,3),(571,'J07',9,6,_binary '',4,3),(572,'J08',9,7,_binary '',4,3),(573,'J09',9,8,_binary '',4,3),(574,'J10',9,9,_binary '',4,3),(575,'J11',9,10,_binary '',4,3),(576,'J12',9,11,_binary '',4,3),(577,'J13',9,12,_binary '',4,3),(578,'J14',9,13,_binary '',4,3),(579,'J15',9,14,_binary '',4,3),(580,'J16',9,15,_binary '',4,3),(581,'K01',10,0,_binary '',4,3),(582,'K02',10,1,_binary '',4,3),(583,'K03',10,2,_binary '',4,3),(584,'K04',10,3,_binary '',4,3),(585,'K05',10,4,_binary '',4,3),(586,'K06',10,5,_binary '',4,3),(587,'K07',10,6,_binary '',4,3),(588,'K08',10,7,_binary '',4,3),(589,'K09',10,8,_binary '',4,3),(590,'K10',10,9,_binary '',4,3),(591,'K11',10,10,_binary '',4,3),(592,'K12',10,11,_binary '',4,3),(593,'K13',10,12,_binary '',4,3),(594,'K14',10,13,_binary '',4,3),(595,'K15',10,14,_binary '',4,3),(596,'K16',10,15,_binary '',4,3),(597,'L01',11,0,_binary '',4,3),(598,'L02',11,1,_binary '',4,3),(599,'L03',11,2,_binary '',4,3),(600,'L04',11,3,_binary '',4,3),(601,'L05',11,4,_binary '',4,3),(602,'L06',11,5,_binary '',4,3),(603,'L07',11,6,_binary '',4,3),(604,'L08',11,7,_binary '',4,3),(605,'L09',11,8,_binary '',4,3),(606,'L10',11,9,_binary '',4,3),(607,'L11',11,10,_binary '',4,3),(608,'L12',11,11,_binary '',4,3),(609,'L13',11,12,_binary '',4,3),(610,'L14',11,13,_binary '',4,3),(611,'L15',11,14,_binary '',4,3),(612,'L16',11,15,_binary '',4,3),(925,'A01',0,0,_binary '',11,1),(926,'A02',0,1,_binary '',11,1),(927,'A03',0,2,_binary '',11,1),(928,'A04',0,3,_binary '',11,1),(929,'A05',0,4,_binary '',11,1),(930,'A06',0,5,_binary '',11,1),(931,'A07',0,6,_binary '',11,1),(932,'A08',0,7,_binary '',11,1),(933,'A09',0,8,_binary '',11,1),(934,'A10',0,9,_binary '',11,1),(935,'A11',0,10,_binary '',11,1),(936,NULL,0,11,_binary '\0',11,1),(937,'B01',1,0,_binary '',11,1),(938,'B02',1,1,_binary '',11,1),(939,'B03',1,2,_binary '',11,1),(940,'B04',1,3,_binary '',11,1),(941,'B05',1,4,_binary '',11,1),(942,'B06',1,5,_binary '',11,1),(943,'B07',1,6,_binary '',11,1),(944,'B08',1,7,_binary '',11,1),(945,'B09',1,8,_binary '',11,1),(946,'B10',1,9,_binary '',11,1),(947,'B11',1,10,_binary '',11,1),(948,NULL,1,11,_binary '\0',11,1),(949,'C01',2,0,_binary '',11,1),(950,'C02',2,1,_binary '',11,1),(951,'C03',2,2,_binary '',11,1),(952,'C04',2,3,_binary '',11,1),(953,'C05',2,4,_binary '',11,1),(954,'C06',2,5,_binary '',11,1),(955,'C07',2,6,_binary '',11,1),(956,'C08',2,7,_binary '',11,1),(957,'C09',2,8,_binary '',11,1),(958,'C10',2,9,_binary '',11,1),(959,'C11',2,10,_binary '',11,1),(960,NULL,2,11,_binary '\0',11,1),(961,'D01',3,0,_binary '',11,1),(962,'D02',3,1,_binary '',11,1),(963,'D03',3,2,_binary '',11,1),(964,'D04',3,3,_binary '',11,1),(965,'D05',3,4,_binary '',11,1),(966,'D06',3,5,_binary '',11,1),(967,'D07',3,6,_binary '',11,1),(968,'D08',3,7,_binary '',11,1),(969,'D09',3,8,_binary '',11,1),(970,'D10',3,9,_binary '',11,1),(971,'D11',3,10,_binary '',11,1),(972,NULL,3,11,_binary '\0',11,1),(973,'E01',4,0,_binary '',11,2),(974,'E02',4,1,_binary '',11,2),(975,'E03',4,2,_binary '',11,2),(976,'E04',4,3,_binary '',11,2),(977,'E05',4,4,_binary '',11,2),(978,'E06',4,5,_binary '',11,2),(979,'E07',4,6,_binary '',11,2),(980,'E08',4,7,_binary '',11,2),(981,'E09',4,8,_binary '',11,2),(982,'E10',4,9,_binary '',11,2),(983,'E11',4,10,_binary '',11,2),(984,'E12',4,11,_binary '',11,2),(985,'F01',5,0,_binary '',11,2),(986,'F02',5,1,_binary '',11,2),(987,'F03',5,2,_binary '',11,2),(988,'F04',5,3,_binary '',11,2),(989,'F05',5,4,_binary '',11,2),(990,'F06',5,5,_binary '',11,2),(991,'F07',5,6,_binary '',11,2),(992,'F08',5,7,_binary '',11,2),(993,'F09',5,8,_binary '',11,2),(994,'F10',5,9,_binary '',11,2),(995,'F11',5,10,_binary '',11,2),(996,'F12',5,11,_binary '',11,2),(997,'G01',6,0,_binary '',11,2),(998,'G02',6,1,_binary '',11,2),(999,'G03',6,2,_binary '',11,2),(1000,'G04',6,3,_binary '',11,2),(1001,'G05',6,4,_binary '',11,2),(1002,'G06',6,5,_binary '',11,2),(1003,'G07',6,6,_binary '',11,2),(1004,'G08',6,7,_binary '',11,2),(1005,'G09',6,8,_binary '',11,2),(1006,'G10',6,9,_binary '',11,2),(1007,'G11',6,10,_binary '',11,2),(1008,'G12',6,11,_binary '',11,2),(1009,'H01',7,0,_binary '',11,2),(1010,'H02',7,1,_binary '',11,2),(1011,'H03',7,2,_binary '',11,2),(1012,'H04',7,3,_binary '',11,2),(1013,'H05',7,4,_binary '',11,2),(1014,'H06',7,5,_binary '',11,2),(1015,'H07',7,6,_binary '',11,2),(1016,'H08',7,7,_binary '',11,2),(1017,'H09',7,8,_binary '',11,2),(1018,'H10',7,9,_binary '',11,2),(1019,'H11',7,10,_binary '',11,2),(1020,'H12',7,11,_binary '',11,2),(1021,'I01',8,0,_binary '',11,3),(1022,'I02',8,1,_binary '',11,3),(1023,'I03',8,2,_binary '',11,3),(1024,'I04',8,3,_binary '',11,3),(1025,'I05',8,4,_binary '',11,3),(1026,'I06',8,5,_binary '',11,3),(1027,'I07',8,6,_binary '',11,3),(1028,'I08',8,7,_binary '',11,3),(1029,'I09',8,8,_binary '',11,3),(1030,'I10',8,9,_binary '',11,3),(1031,'I11',8,10,_binary '',11,3),(1032,'I12',8,11,_binary '',11,3),(1033,'J01',9,0,_binary '',11,3),(1034,'J02',9,1,_binary '',11,3),(1035,'J03',9,2,_binary '',11,3),(1036,'J04',9,3,_binary '',11,3),(1037,'J05',9,4,_binary '',11,3),(1038,'J06',9,5,_binary '',11,3),(1039,'J07',9,6,_binary '',11,3),(1040,'J08',9,7,_binary '',11,3),(1041,'J09',9,8,_binary '',11,3),(1042,'J10',9,9,_binary '',11,3),(1043,'J11',9,10,_binary '',11,3),(1044,'J12',9,11,_binary '',11,3),(1045,'A01',0,0,_binary '',12,1),(1046,'A02',0,1,_binary '',12,1),(1047,'A03',0,2,_binary '',12,1),(1048,'A04',0,3,_binary '',12,1),(1049,'A05',0,4,_binary '',12,1),(1053,'B01',1,0,_binary '',12,1),(1054,'B02',1,1,_binary '',12,1),(1055,'B03',1,2,_binary '',12,1),(1056,'B04',1,3,_binary '',12,1),(1057,'B05',1,4,_binary '',12,1),(1061,'C01',2,0,_binary '',12,1),(1062,'C02',2,1,_binary '',12,1),(1063,'C03',2,2,_binary '',12,1),(1064,'C04',2,3,_binary '',12,1),(1065,'C05',2,4,_binary '',12,1),(1069,'D01',3,0,_binary '',12,2),(1070,'D02',3,1,_binary '',12,2),(1071,'D03',3,2,_binary '',12,2),(1072,'D04',3,3,_binary '',12,2),(1073,'D05',3,4,_binary '',12,2),(1077,'E01',4,0,_binary '',12,2),(1078,'E02',4,1,_binary '',12,2),(1079,'E03',4,2,_binary '',12,2),(1080,'E04',4,3,_binary '',12,2),(1081,'E05',4,4,_binary '',12,2),(1165,'A06',0,5,_binary '',12,1),(1166,'A07',0,6,_binary '',12,1),(1167,'A08',0,7,_binary '',12,1),(1170,'B06',1,5,_binary '',12,1),(1171,'B07',1,6,_binary '',12,1),(1172,'B08',1,7,_binary '',12,1),(1175,'C06',2,5,_binary '',12,1),(1176,'C07',2,6,_binary '',12,1),(1177,'C08',2,7,_binary '',12,1),(1180,'D06',3,5,_binary '',12,2),(1181,'D07',3,6,_binary '',12,2),(1182,'D08',3,7,_binary '',12,2),(1185,'E06',4,5,_binary '',12,2),(1186,'E07',4,6,_binary '',12,2),(1187,'E08',4,7,_binary '',12,2),(1190,'F01',5,0,_binary '',12,2),(1191,'F02',5,1,_binary '',12,2),(1192,'F03',5,2,_binary '',12,2),(1193,'F04',5,3,_binary '',12,2),(1194,'F05',5,4,_binary '',12,2),(1195,'F06',5,5,_binary '',12,2),(1196,'F07',5,6,_binary '',12,2),(1197,'F08',5,7,_binary '',12,2),(1200,'G01',6,0,_binary '',12,2),(1201,'G02',6,1,_binary '',12,2),(1202,'G03',6,2,_binary '',12,2),(1203,'G04',6,3,_binary '',12,2),(1204,'G05',6,4,_binary '',12,2),(1205,'G06',6,5,_binary '',12,2),(1206,'G07',6,6,_binary '',12,2),(1207,'G08',6,7,_binary '',12,2),(1210,'H01',7,0,_binary '',12,2),(1211,'H02',7,1,_binary '',12,2),(1212,'H03',7,2,_binary '',12,2),(1213,'H04',7,3,_binary '',12,2),(1214,'H05',7,4,_binary '',12,2),(1215,'H06',7,5,_binary '',12,2),(1216,'H07',7,6,_binary '',12,2),(1217,'H08',7,7,_binary '',12,2),(1240,'A11',0,10,_binary '',5,1),(1241,'A12',0,11,_binary '',5,1),(1242,'B11',1,10,_binary '',5,1),(1243,'B12',1,11,_binary '',5,1),(1244,'C11',2,10,_binary '',5,1),(1245,'C12',2,11,_binary '',5,1),(1246,'D11',3,10,_binary '',5,2),(1247,'D12',3,11,_binary '',5,2),(1248,'E11',4,10,_binary '',5,2),(1249,'E12',4,11,_binary '',5,2),(1250,'F11',5,10,_binary '',5,2),(1251,'F12',5,11,_binary '',5,2),(1252,'G11',6,10,_binary '',5,2),(1253,'G12',6,11,_binary '',5,2),(1254,'H11',7,10,_binary '',5,2),(1255,'H12',7,11,_binary '',5,2),(1256,'I01',8,0,_binary '',5,3),(1257,'I02',8,1,_binary '',5,3),(1258,'I03',8,2,_binary '',5,3),(1259,'I04',8,3,_binary '',5,3),(1260,'I05',8,4,_binary '',5,3),(1261,'I06',8,5,_binary '',5,3),(1262,'I07',8,6,_binary '',5,3),(1263,'I08',8,7,_binary '',5,3),(1264,'I09',8,8,_binary '',5,3),(1265,'I10',8,9,_binary '',5,3),(1266,'I11',8,10,_binary '',5,3),(1267,'I12',8,11,_binary '',5,3),(1268,'J01',9,0,_binary '',5,3),(1269,'J02',9,1,_binary '',5,3),(1270,'J03',9,2,_binary '',5,3),(1271,'J04',9,3,_binary '',5,3),(1272,'J05',9,4,_binary '',5,3),(1273,'J06',9,5,_binary '',5,3),(1274,'J07',9,6,_binary '',5,3),(1275,'J08',9,7,_binary '',5,3),(1276,'J09',9,8,_binary '',5,3),(1277,'J10',9,9,_binary '',5,3),(1278,'J11',9,10,_binary '',5,3),(1279,'J12',9,11,_binary '',5,3),(1355,'A01',0,0,_binary '',13,1),(1356,'A02',0,1,_binary '',13,1),(1357,'A03',0,2,_binary '',13,1),(1358,'A04',0,3,_binary '',13,1),(1359,'A05',0,4,_binary '',13,1),(1360,'B01',1,0,_binary '',13,1),(1361,'B02',1,1,_binary '',13,1),(1362,'B03',1,2,_binary '',13,1),(1363,'B04',1,3,_binary '',13,1),(1364,'B05',1,4,_binary '',13,1),(1365,'C01',2,0,_binary '',13,1),(1366,'C02',2,1,_binary '',13,1),(1367,'C03',2,2,_binary '',13,1),(1368,'C04',2,3,_binary '',13,1),(1369,'C05',2,4,_binary '',13,1),(1370,'D01',3,0,_binary '',13,1),(1371,'D02',3,1,_binary '',13,1),(1372,'D03',3,2,_binary '',13,1),(1373,'D04',3,3,_binary '',13,1),(1374,'D05',3,4,_binary '',13,1),(1375,'E01',4,0,_binary '',13,1),(1376,'E02',4,1,_binary '',13,1),(1377,'E03',4,2,_binary '',13,1),(1378,'E04',4,3,_binary '',13,1),(1379,'E05',4,4,_binary '',13,1),(1405,'A01',0,0,_binary '',17,1),(1406,'A02',0,1,_binary '',17,1),(1407,'A03',0,2,_binary '',17,1),(1408,'A04',0,3,_binary '',17,1),(1409,'A05',0,4,_binary '',17,1),(1410,'B01',1,0,_binary '',17,1),(1411,'B02',1,1,_binary '',17,1),(1412,'B03',1,2,_binary '',17,1),(1413,'B04',1,3,_binary '',17,1),(1414,'B05',1,4,_binary '',17,1),(1415,'C01',2,0,_binary '',17,1),(1416,'C02',2,1,_binary '',17,1),(1417,'C03',2,2,_binary '',17,1),(1418,'C04',2,3,_binary '',17,1),(1419,'C05',2,4,_binary '',17,1),(1420,'D01',3,0,_binary '',17,1),(1421,'D02',3,1,_binary '',17,1),(1422,'D03',3,2,_binary '',17,1),(1423,'D04',3,3,_binary '',17,1),(1424,'D05',3,4,_binary '',17,1),(1425,'E01',4,0,_binary '',17,1),(1426,'E02',4,1,_binary '',17,1),(1427,'E03',4,2,_binary '',17,1),(1428,'E04',4,3,_binary '',17,1),(1429,'E05',4,4,_binary '',17,1),(1430,'A01',0,0,_binary '',14,1),(1431,'A02',0,1,_binary '',14,1),(1432,'A03',0,2,_binary '',14,1),(1433,'A04',0,3,_binary '',14,1),(1434,'A05',0,4,_binary '',14,1),(1435,'B01',1,0,_binary '',14,1),(1436,'B02',1,1,_binary '',14,1),(1437,'B03',1,2,_binary '',14,1),(1438,'B04',1,3,_binary '',14,1),(1439,'B05',1,4,_binary '',14,1),(1440,'C01',2,0,_binary '',14,1),(1441,'C02',2,1,_binary '',14,1),(1442,'C03',2,2,_binary '',14,1),(1443,'C04',2,3,_binary '',14,1),(1444,'C05',2,4,_binary '',14,1),(1445,'D01',3,0,_binary '',14,1),(1446,'D02',3,1,_binary '',14,1),(1447,'D03',3,2,_binary '',14,1),(1448,'D04',3,3,_binary '',14,1),(1449,'D05',3,4,_binary '',14,1),(1450,'E01',4,0,_binary '',14,1),(1451,'E02',4,1,_binary '',14,1),(1452,'E03',4,2,_binary '',14,1),(1453,'E04',4,3,_binary '',14,1),(1454,'E05',4,4,_binary '',14,1),(1455,'A01',0,0,_binary '',15,1),(1456,'A02',0,1,_binary '',15,1),(1457,'A03',0,2,_binary '',15,1),(1458,'A04',0,3,_binary '',15,1),(1459,'A05',0,4,_binary '',15,1),(1460,'B01',1,0,_binary '',15,1),(1461,'B02',1,1,_binary '',15,1),(1462,'B03',1,2,_binary '',15,1),(1463,'B04',1,3,_binary '',15,1),(1464,'B05',1,4,_binary '',15,1),(1465,'C01',2,0,_binary '',15,1),(1466,'C02',2,1,_binary '',15,1),(1467,'C03',2,2,_binary '',15,1),(1468,'C04',2,3,_binary '',15,1),(1469,'C05',2,4,_binary '',15,1),(1470,'D01',3,0,_binary '',15,1),(1471,'D02',3,1,_binary '',15,1),(1472,'D03',3,2,_binary '',15,1),(1473,'D04',3,3,_binary '',15,1),(1474,'D05',3,4,_binary '',15,1),(1475,'E01',4,0,_binary '',15,1),(1476,'E02',4,1,_binary '',15,1),(1477,'E03',4,2,_binary '',15,1),(1478,'E04',4,3,_binary '',15,1),(1479,'E05',4,4,_binary '',15,1),(1480,'A01',0,0,_binary '',16,1),(1481,'A02',0,1,_binary '',16,1),(1482,'A03',0,2,_binary '',16,1),(1483,'A04',0,3,_binary '',16,1),(1484,'A05',0,4,_binary '',16,1),(1485,'B01',1,0,_binary '',16,1),(1486,'B02',1,1,_binary '',16,1),(1487,'B03',1,2,_binary '',16,1),(1488,'B04',1,3,_binary '',16,1),(1489,'B05',1,4,_binary '',16,1),(1490,'C01',2,0,_binary '',16,1),(1491,'C02',2,1,_binary '',16,1),(1492,'C03',2,2,_binary '',16,1),(1493,'C04',2,3,_binary '',16,1),(1494,'C05',2,4,_binary '',16,1),(1495,'D01',3,0,_binary '',16,1),(1496,'D02',3,1,_binary '',16,1),(1497,'D03',3,2,_binary '',16,1),(1498,'D04',3,3,_binary '',16,1),(1499,'D05',3,4,_binary '',16,1),(1500,'E01',4,0,_binary '',16,1),(1501,'E02',4,1,_binary '',16,1),(1502,'E03',4,2,_binary '',16,1),(1503,'E04',4,3,_binary '',16,1),(1504,'E05',4,4,_binary '',16,1),(1505,'A01',0,0,_binary '',18,1),(1506,'A02',0,1,_binary '',18,1),(1507,'A03',0,2,_binary '',18,1),(1508,'A04',0,3,_binary '',18,1),(1509,'A05',0,4,_binary '',18,1),(1510,'A06',0,5,_binary '',18,1),(1511,'A07',0,6,_binary '',18,1),(1512,'A08',0,7,_binary '',18,1),(1513,'A09',0,8,_binary '',18,1),(1514,'A10',0,9,_binary '',18,1),(1515,'B01',1,0,_binary '',18,1),(1516,'B02',1,1,_binary '',18,1),(1517,'B03',1,2,_binary '',18,1),(1518,'B04',1,3,_binary '',18,1),(1519,'B05',1,4,_binary '',18,1),(1520,'B06',1,5,_binary '',18,1),(1521,'B07',1,6,_binary '',18,1),(1522,'B08',1,7,_binary '',18,1),(1523,'B09',1,8,_binary '',18,1),(1524,'B10',1,9,_binary '',18,1),(1525,'C01',2,0,_binary '',18,1),(1526,'C02',2,1,_binary '',18,1),(1527,'C03',2,2,_binary '',18,1),(1528,'C04',2,3,_binary '',18,1),(1529,'C05',2,4,_binary '',18,1),(1530,'C06',2,5,_binary '',18,1),(1531,'C07',2,6,_binary '',18,1),(1532,'C08',2,7,_binary '',18,1),(1533,'C09',2,8,_binary '',18,1),(1534,'C10',2,9,_binary '',18,1),(1535,'D01',3,0,_binary '',18,1),(1536,'D02',3,1,_binary '',18,1),(1537,'D03',3,2,_binary '',18,1),(1538,'D04',3,3,_binary '',18,1),(1539,'D05',3,4,_binary '',18,1),(1540,'D06',3,5,_binary '',18,1),(1541,'D07',3,6,_binary '',18,1),(1542,'D08',3,7,_binary '',18,1),(1543,'D09',3,8,_binary '',18,1),(1544,'D10',3,9,_binary '',18,1),(1545,'E01',4,0,_binary '',18,2),(1546,'E02',4,1,_binary '',18,2),(1547,'E03',4,2,_binary '',18,2),(1548,'E04',4,3,_binary '',18,2),(1549,'E05',4,4,_binary '',18,2),(1550,'E06',4,5,_binary '',18,2),(1551,'E07',4,6,_binary '',18,2),(1552,'E08',4,7,_binary '',18,2),(1553,'E09',4,8,_binary '',18,2),(1554,'E10',4,9,_binary '',18,2),(1555,'F01',5,0,_binary '',18,2),(1556,'F02',5,1,_binary '',18,2),(1557,'F03',5,2,_binary '',18,2),(1558,'F04',5,3,_binary '',18,2),(1559,'F05',5,4,_binary '',18,2),(1560,'F06',5,5,_binary '',18,2),(1561,'F07',5,6,_binary '',18,2),(1562,'F08',5,7,_binary '',18,2),(1563,'F09',5,8,_binary '',18,2),(1564,'F10',5,9,_binary '',18,2),(1565,'G01',6,0,_binary '',18,2),(1566,'G02',6,1,_binary '',18,2),(1567,'G03',6,2,_binary '',18,2),(1568,'G04',6,3,_binary '',18,2),(1569,'G05',6,4,_binary '',18,2),(1570,'G06',6,5,_binary '',18,2),(1571,'G07',6,6,_binary '',18,2),(1572,'G08',6,7,_binary '',18,2),(1573,'G09',6,8,_binary '',18,2),(1574,'G10',6,9,_binary '',18,2),(1575,'H01',7,0,_binary '',18,2),(1576,'H02',7,1,_binary '',18,2),(1577,'H03',7,2,_binary '',18,2),(1578,'H04',7,3,_binary '',18,2),(1579,'H05',7,4,_binary '',18,2),(1580,'H06',7,5,_binary '',18,2),(1581,'H07',7,6,_binary '',18,2),(1582,'H08',7,7,_binary '',18,2),(1583,'H09',7,8,_binary '',18,2),(1584,'H10',7,9,_binary '',18,2),(1585,'I01',8,0,_binary '',18,3),(1586,'I02',8,1,_binary '',18,3),(1587,'I03',8,2,_binary '',18,3),(1588,'I04',8,3,_binary '',18,3),(1589,'I05',8,4,_binary '',18,3),(1590,'I06',8,5,_binary '',18,3),(1591,'I07',8,6,_binary '',18,3),(1592,'I08',8,7,_binary '',18,3),(1593,'I09',8,8,_binary '',18,3),(1594,'I10',8,9,_binary '',18,3),(1595,'J01',9,0,_binary '',18,3),(1596,'J02',9,1,_binary '',18,3),(1597,'J03',9,2,_binary '',18,3),(1598,'J04',9,3,_binary '',18,3),(1599,'J05',9,4,_binary '',18,3),(1600,'J06',9,5,_binary '',18,3),(1601,'J07',9,6,_binary '',18,3),(1602,'J08',9,7,_binary '',18,3),(1603,'J09',9,8,_binary '',18,3),(1604,'J10',9,9,_binary '',18,3);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selected_seat`
--

DROP TABLE IF EXISTS `selected_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selected_seat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end` datetime(6) DEFAULT NULL,
  `start` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `seatid` bigint DEFAULT NULL,
  `showtimeid` bigint DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhf58e8uxwllcvsa9wki4kjbm7` (`seatid`),
  KEY `FKq4phipyxkheny0nh85fa596cg` (`showtimeid`),
  KEY `FKa1p6reu8ysfgqdbf121diblue` (`userid`),
  CONSTRAINT `FKa1p6reu8ysfgqdbf121diblue` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FKhf58e8uxwllcvsa9wki4kjbm7` FOREIGN KEY (`seatid`) REFERENCES `seat` (`seatid`),
  CONSTRAINT `FKq4phipyxkheny0nh85fa596cg` FOREIGN KEY (`showtimeid`) REFERENCES `showtime` (`showtimeid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selected_seat`
--

LOCK TABLES `selected_seat` WRITE;
/*!40000 ALTER TABLE `selected_seat` DISABLE KEYS */;
INSERT INTO `selected_seat` VALUES (1,'2024-12-14 00:00:52.000000','2024-12-13 23:50:52.000000','expired',24,14,1),(2,'2024-12-14 00:04:52.000000','2024-12-13 23:54:52.000000','confirmed',152,2,1),(3,'2024-12-14 00:24:51.000000','2024-12-14 00:14:51.000000','confirmed',38,14,3),(4,'2024-12-14 00:28:51.000000','2024-12-14 00:18:51.000000','confirmed',37,14,3),(5,'2024-12-16 11:10:36.000000','2024-12-16 11:00:36.000000','expired',152,15,38),(6,'2024-12-16 11:10:36.000000','2024-12-16 11:00:36.000000','expired',188,15,38),(7,'2024-12-16 11:21:53.000000','2024-12-16 11:11:53.000000','expired',152,15,38),(8,'2024-12-16 11:25:21.000000','2024-12-16 11:15:21.000000','expired',164,15,38),(9,'2024-12-16 11:31:28.000000','2024-12-16 11:21:28.000000','expired',176,15,38),(10,'2024-12-16 11:37:14.000000','2024-12-16 11:27:14.000000','expired',200,15,38),(11,'2024-12-16 11:45:06.000000','2024-12-16 11:35:06.000000','expired',200,15,38),(12,'2024-12-16 11:55:35.000000','2024-12-16 11:45:35.000000','expired',188,15,38),(13,'2024-12-16 12:06:32.000000','2024-12-16 11:56:32.000000','expired',188,15,38),(14,'2024-12-16 13:14:52.000000','2024-12-16 13:04:52.000000','expired',199,15,38),(15,'2024-12-16 13:14:52.000000','2024-12-16 13:04:52.000000','expired',200,15,38),(16,'2024-12-16 13:14:52.000000','2024-12-16 13:04:52.000000','expired',199,15,38),(17,'2024-12-16 13:26:47.000000','2024-12-16 13:16:47.000000','expired',198,15,38),(18,'2024-12-16 13:32:03.000000','2024-12-16 13:22:03.000000','expired',197,15,38),(19,'2024-12-16 13:36:51.000000','2024-12-16 13:26:51.000000','confirmed',196,15,38),(20,'2024-12-16 13:39:06.000000','2024-12-16 13:29:06.000000','confirmed',195,15,38),(21,'2024-12-16 13:42:26.000000','2024-12-16 13:32:26.000000','confirmed',194,15,38),(22,'2024-12-16 18:45:50.000000','2024-12-16 18:35:50.000000','confirmed',197,15,38),(23,'2024-12-16 18:50:07.000000','2024-12-16 18:40:07.000000','confirmed',198,15,38),(24,'2024-12-16 18:50:31.000000','2024-12-16 18:40:31.000000','confirmed',199,15,38),(25,'2024-12-16 19:12:16.000000','2024-12-16 19:02:16.000000','confirmed',200,15,38),(26,'2024-12-16 19:12:30.000000','2024-12-16 19:02:30.000000','confirmed',193,15,38),(27,'2024-12-16 19:12:44.000000','2024-12-16 19:02:44.000000','confirmed',192,15,38),(28,'2024-12-16 19:26:24.000000','2024-12-16 19:16:24.000000','confirmed',164,2,38),(29,'2024-12-16 19:26:38.000000','2024-12-16 19:16:38.000000','confirmed',176,2,38),(30,'2024-12-16 19:34:40.000000','2024-12-16 19:24:40.000000','confirmed',188,2,38),(31,'2024-12-16 19:39:36.000000','2024-12-16 19:29:36.000000','confirmed',187,2,38),(32,'2024-12-16 19:41:53.000000','2024-12-16 19:31:53.000000','confirmed',186,2,38),(33,'2024-12-16 19:45:39.000000','2024-12-16 19:35:39.000000','confirmed',185,2,38),(34,'2024-12-16 20:17:39.000000','2024-12-16 20:07:39.000000','confirmed',191,15,38),(35,'2024-12-16 20:22:40.000000','2024-12-16 20:12:40.000000','confirmed',190,15,38),(36,'2024-12-16 20:52:27.000000','2024-12-16 20:42:27.000000','confirmed',484,4,1),(37,'2024-12-17 17:35:17.000000','2024-12-17 17:25:17.000000','confirmed',483,4,1),(38,'2024-12-17 17:35:17.000000','2024-12-17 17:25:17.000000','confirmed',482,4,1),(39,'2024-12-17 17:35:17.000000','2024-12-17 17:25:17.000000','confirmed',481,4,1),(40,'2024-12-17 17:35:17.000000','2024-12-17 17:25:17.000000','confirmed',480,4,1),(41,'2024-12-24 23:25:29.000000','2024-12-24 23:15:29.000000','expired',19,17,1),(42,'2024-12-24 23:25:29.000000','2024-12-24 23:15:29.000000','expired',20,17,1),(43,'2024-12-25 00:45:20.000000','2024-12-25 00:35:20.000000','expired',1,17,38),(44,'2024-12-25 00:45:20.000000','2024-12-25 00:35:20.000000','expired',2,17,38);
/*!40000 ALTER TABLE `selected_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtime`
--

DROP TABLE IF EXISTS `showtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showtime` (
  `showtimeid` bigint NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `end_time` time(6) DEFAULT NULL,
  `start_time` time(6) NOT NULL,
  `status` bit(1) NOT NULL,
  `base_priceid` bigint DEFAULT NULL,
  `day_of_weekid` bigint DEFAULT NULL,
  `movieid` bigint DEFAULT NULL,
  `roomid` bigint NOT NULL,
  `time_frameid` bigint DEFAULT NULL,
  PRIMARY KEY (`showtimeid`),
  KEY `FKq0d1a3ggmfyr7eoh3bl01xmcc` (`base_priceid`),
  KEY `FK4n5weosauana7969079jmy1yl` (`day_of_weekid`),
  KEY `FKici557wklls9lplk2bk342ajm` (`movieid`),
  KEY `FKhttqwu26y8pmrxfpo4pawn6rm` (`roomid`),
  KEY `FK5ui08xrsik5umibdathbpyre` (`time_frameid`),
  CONSTRAINT `FK4n5weosauana7969079jmy1yl` FOREIGN KEY (`day_of_weekid`) REFERENCES `day_of_week` (`day_of_weekid`),
  CONSTRAINT `FK5ui08xrsik5umibdathbpyre` FOREIGN KEY (`time_frameid`) REFERENCES `time_frame` (`time_frameid`),
  CONSTRAINT `FKhttqwu26y8pmrxfpo4pawn6rm` FOREIGN KEY (`roomid`) REFERENCES `room` (`roomid`),
  CONSTRAINT `FKici557wklls9lplk2bk342ajm` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`),
  CONSTRAINT `FKq0d1a3ggmfyr7eoh3bl01xmcc` FOREIGN KEY (`base_priceid`) REFERENCES `base_price` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtime`
--

LOCK TABLES `showtime` WRITE;
/*!40000 ALTER TABLE `showtime` DISABLE KEYS */;
INSERT INTO `showtime` VALUES (1,'ended','2024-12-20','22:00:00.000000','20:00:00.000000',_binary '\0',1,1,1,1,2),(2,'ended','2024-12-20','18:00:00.000000','16:00:00.000000',_binary '\0',1,1,1,2,1),(3,'ended','2024-12-20','21:30:00.000000','19:30:00.000000',_binary '\0',1,1,1,3,2),(4,'ended','2024-12-21','20:30:00.000000','18:30:00.000000',_binary '\0',1,2,1,4,1),(5,'ended','2024-12-21','19:00:00.000000','17:00:00.000000',_binary '\0',1,2,1,5,1),(6,'ended','2024-12-21','22:30:00.000000','20:30:00.000000',_binary '\0',1,2,1,11,2),(7,'ended','2024-12-22','22:00:00.000000','20:00:00.000000',_binary '\0',1,1,2,12,2),(8,'ended','2024-12-22','18:00:00.000000','16:00:00.000000',_binary '\0',1,1,2,13,1),(9,'ended','2024-12-22','21:30:00.000000','19:30:00.000000',_binary '\0',1,1,2,14,2),(10,'ended','2024-12-23','20:30:00.000000','18:30:00.000000',_binary '\0',1,2,2,15,1),(11,'ended','2024-12-23','19:00:00.000000','17:00:00.000000',_binary '\0',1,2,2,16,1),(12,'ended','2024-12-23','22:30:00.000000','20:30:00.000000',_binary '\0',1,2,2,17,2),(13,'ended','2024-12-13','22:00:00.000000','20:00:00.000000',_binary '\0',1,1,3,1,2),(14,'ended','2024-12-13','18:00:00.000000','16:00:00.000000',_binary '\0',1,1,3,1,1),(15,'ended','2024-12-24','21:30:00.000000','19:30:00.000000',_binary '\0',1,1,3,2,2),(16,'upcoming','2024-12-25','20:30:00.000000','18:30:00.000000',_binary '',1,2,3,3,1),(17,'upcoming','2024-12-25','19:00:00.000000','17:00:00.000000',_binary '',1,2,3,1,1),(18,'upcoming','2024-12-25','22:30:00.000000','20:30:00.000000',_binary '',1,2,3,2,2),(19,'ended','2024-12-24','11:01:00.000000','08:00:00.000000',_binary '\0',1,1,1,1,1);
/*!40000 ALTER TABLE `showtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slideshow`
--

DROP TABLE IF EXISTS `slideshow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slideshow` (
  `slideshowid` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `movieid` bigint DEFAULT NULL,
  PRIMARY KEY (`slideshowid`),
  UNIQUE KEY `UK18qfund6agh3f0ywy3m0xmlpa` (`movieid`),
  CONSTRAINT `FK2lfb0oxxn4497n86n1tp7a3xk` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slideshow`
--

LOCK TABLES `slideshow` WRITE;
/*!40000 ALTER TABLE `slideshow` DISABLE KEYS */;
INSERT INTO `slideshow` VALUES (1,'https://th.bing.com/th/id/R.c2a8419f74568c036f7eed7ae536b101?rik=ZwUEt3cXSMLosQ&riu=http%3a%2f%2fwallpapercave.com%2fwp%2fwp1818196.jpg&ehk=Hu65%2f5cdvKqgcgJI6eWHRQblrCiKNjLXKOYMGNY6YJg%3d&risl=&pid=ImgRaw&r=0',10),(2,'https://image.tmdb.org/t/p/original/9I3n4G2ckZJq8XMotcUWqPLGnhI.jpg',9);
/*!40000 ALTER TABLE `slideshow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theater`
--

DROP TABLE IF EXISTS `theater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theater` (
  `theaterid` bigint NOT NULL AUTO_INCREMENT,
  `address_detail` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `description` text,
  `email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`theaterid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theater`
--

LOCK TABLES `theater` WRITE;
/*!40000 ALTER TABLE `theater` DISABLE KEYS */;
INSERT INTO `theater` VALUES (1,'Tầng hầm B1, tòa nhà Golden West, Số 2 Lê Văn Thiêm','Hà Nội','Thanh Xuân','Nhân Chính','Rạp có vị trí thuận lợi, rất gần những trường đại học, cao đẳng và cấp 3 lớn tại Hà Nội (Trường Đại học Khoa học Tự nhiên, Trường Đại học Khoa học Xã hội và Nhân văn, trường Hà Nội – Amsterdam…) \r\n\r\nLAL Cinemas Thanh Xuân sở hữu tổng cộng 6 phòng chiếu tương đương 838 ghế ngồi. Rạp được trang bị hệ thống màn chiếu, máy chiếu, phòng chiếu hiện đại với 100% nhập khẩu từ nước ngoài. Trong mỗi phòng chiếu đều được lắp đặt hệ thống âm thanh Dolby 7.1 và hệ thống cách âm chuẩn quốc tế. Vì vậy mà mỗi thước phim được chiếu tại rạp đều là những thước phim rõ nét nhất, với âm thanh và hiệu ứng sống động nhất. \r\n\r\nMức giá xem phim tại rạp hết sức ưu đãi, chỉ từ 45.000 VNĐ. Mỗi tuần, rạp còn có những chương trình khuyến mại, ưu đãi đặc biệt dành cho các tín đồ điện ảnh. \r\n\r\nVới địa điểm thuận lợi, cơ sở vật chất hiện đại, tiên tiến, mức giá ưu đãi, Beta Cinemas Thanh Xuân chắc chắn sẽ là địa điểm xem-ăn-chơi không thể bỏ qua của giới trẻ Hà Thành.   ','contact@lalthanhxuan.com','https://res.cloudinary.com/dandjf6ke/image/upload/v1730304829/Image/Theater/image_1733947883614.jpg','LAL THANH XUÂN','0824812878',_binary ''),(2,'Tầng hầm B1, tòa nhà Golden Palace','Hà Nội','Nam Từ Liêm','Mễ Trì','Rạp có vị trí thuận lợi, rất gần những trường đại học, cao đẳng và cấp 3 lớn tại Hà Nội. Beta Cinemas Mỹ Đình sở hữu tổng cộng 7 phòng chiếu tương đương hơn 800 ghế ngồi. Rạp được trang bị hệ thống màn chiếu, máy chiếu, phòng chiếu hiện đại theo tiêu chuẩn Hollywood với 100% nhập khẩu từ nước ngoài. Trong mỗi phòng chiếu đều được lắp đặt hệ thống âm thanh Dolby 7.1 và hệ thống cách âm chuẩn quốc tế. Vì vậy mà mỗi thước phim được chiếu tại rạp đều là những thước phim rõ nét nhất, với âm thanh và hiệu ứng sống động nhất. Mức giá xem phim tại rạp hết sức ưu đãi, chỉ từ 45.000 VNĐ. Mỗi tuần, rạp còn có những chương trình khuyến mại, ưu đãi đặc biệt dành cho các tín đồ điện ảnh.\r\n\r\nVới địa điểm thuận lợi, cơ sở vật chất hiện đại, tiên tiến, mức giá ưu đãi, Beta Cinemas Mỹ Đình chắc chắn sẽ là địa điểm xem-ăn-chơi không thể bỏ qua của giới trẻ Hà Thành.  ','contact@lalmydinh.com','https://res.cloudinary.com/dandjf6ke/image/upload/v1730304694/Image/Theater/image_1733935092945.jpg','LAL MỸ ĐÌNH','0866154610',_binary ''),(3,'tầng 2 tòa nhà HHA, khu đô thị XPHomes (Tân Tây Đô)','Hà Nội','Đan Phượng','Tân Lập','Nhằm phục vụ nhu cầu vui chơi giải trí của người dân ngoại thành Hà Nội, LAL Cinemas Đan Phượng đã có mặt tại khuôn viên khu đô thị Tân Tây Đô (khu đô thị XPHomes) huyện Đan Phượng.\r\n\r\nNằm trong tòa nhà HHA có kiến trúc hiện đại và đa năng, rạp LAL Cinemas Đan Phương cũng sở hữu thiết kế trẻ trung, năng động rất phù hợp với giới trẻ. Rạp có quy mô 5 phòng chiếu với 477 ghế, trong đó có 1 phòng chiếu 3D.\r\n\r\nKhông thua kém 2 người anh em là Beta Mỹ Đình và Beta Thanh Xuân, LAL Cinemas Đan Phượng cũng sẽ được trang bị màn hình chiếu cong cùng với dàn âm thanh Dolby hiện đại. ','contact@laldanphuong.com','https://res.cloudinary.com/dandjf6ke/image/upload/v1730304853/Image/Theater/image_1733935117741.jpg','LAL ĐAN PHƯỢNG','0983901714',_binary ''),(5,'số 645 Quang Trung','Hồ Chí Minh','Gò Vấp','Phường 11','Rạp LAL Cinemas Quang Trung sở hữu 7 phòng chiếu, hơn 1000 ghế ngồi với phong cách kiến trúc trẻ trung năng động, mang đậm âm hưởng Sài Gòn và giá vé chỉ từ 45k.\r\nRạp tọa lạc tại số 645 Quang Trung, Phường 11, Quận Gò Vấp, Thành phố Hồ Chí Minh là điểm đến lý tưởng của giới trẻ Sài Thành bởi không gian siêu đẹp cùng với chất lượng âm thanh ánh sáng siêu đỉnh. Beta Cinemas Quang Trung có vị trí thuận lợi, gần khu vực sinh sống đông dân cư cũng như trung tâm thương mại đầy đủ tiện nghi. Rạp được trang bị hệ thống màn chiếu tiên tiến với máy chiếu kỹ thuật số hiện đại bậc nhất hiện nay theo tiêu chuẩn Hollywood 100% nhập khẩu từ nước ngoài. Mỗi phòng chiếu đều được lắp đặt hệ thống âm thanh Dolby 7.1 và hệ thống cách âm chuẩn quốc tế. Độ sáng và bền màu của máy chiếu laser tại Beta Quang Trung được đánh giá rất cao, sở hữu cường độ sáng lớn và hình ảnh sắc nét. Vì vậy mà mỗi thước phim được chiếu tại rạp đều là những thước phim rõ nét nhất, với âm thanh và hiệu ứng sống động nhất. Mức giá xem phim tại rạp hết sức ưu đãi, chỉ từ 45.000 VNĐ. Mỗi tuần, rạp còn có những chương trình khuyến mại, ưu đãi đặc biệt dành cho các tín đồ điện ảnh, như Đồng giá vé 45k cho Học sinh sinh viên từ thứ 2 đến thứ 6, Thứ 3 Vui Vẻ giá chỉ từ 45k cho mọi người mọi nhà, Giảm thêm 10k khi mua vé kèm combo online... Với địa điểm thuận lợi, cơ sở vật chất hiện đại và mức giá ưu đãi, Beta Cinemas Quang Trung chắc chắn sẽ là địa điểm xem-ăn-chơi không thể bỏ qua của giới trẻ Sài Thành!','contact@lalhochiminh.com','https://res.cloudinary.com/dandjf6ke/image/upload/v1730653821/Image/Theater/image_1733935179429.png','LAL HỒ CHÍ MINH','0706075509',_binary ''),(6,'Tầng 2, Trung tâm văn hóa đa năng IMC, 62 đường Trần Quang Khải','Hồ Chí Minh ','Quận 1','Phường Tân Định','Rạp LAL Cinemas Trần Quang Khải sở hữu 5 phòng chiếu, hơn 340 ghế ngồi với phong cách kiến trúc trẻ trung năng động, mang đậm âm hưởng Sài Gòn.\r\n\r\nRạp tọa lạc tại Tầng 2 Trung tâm văn hóa đa năng IMC, số 62 Trần Quang Khải, phường Tân Định, Quận 1, Thành phố Hồ Chí Minh là điểm đến lý tưởng của giới trẻ Sài Thành bởi không gian siêu đẹp cùng với chất lượng âm thanh ánh sáng siêu đỉnh. LAL Cinemas Trần Quang Khải có vị trí thuận lợi, gần khu vực sinh sống đông dân cư cũng như trung tâm thương mại đầy đủ tiện nghi. Rạp được trang bị hệ thống màn chiếu tiên tiến với máy chiếu kỹ thuật số hiện đại bậc nhất hiện nay theo tiêu chuẩn Hollywood 100% nhập khẩu từ nước ngoài. Mỗi phòng chiếu đều được lắp đặt hệ thống âm thanh Dolby 7.1 và hệ thống cách âm chuẩn quốc tế. Độ sáng và bền màu của máy chiếu laser tại Beta Trần Quang Khải được đánh giá rất cao, sở hữu cường độ sáng lớn và hình ảnh sắc nét. Vì vậy mà mỗi thước phim được chiếu tại rạp đều là những thước phim rõ nét nhất, với âm thanh và hiệu ứng sống động nhất.','contact@laltranquangkhai.com','https://res.cloudinary.com/dandjf6ke/image/upload/v1730668380/Image/Theater/image_1733935031552.jpg','LAL TRẦN QUANG KHẢI','1900638362',_binary ''),(7,'259 Hoàng Gia Plaza','Thái Nguyên','Tân Thịnh','Quang Trung','LAL Cinemas Thái Nguyên có vị trí trung tâm, tọa lạc tại Hoàng Gia Plaza. Rạp tự hào là rạp phim tư nhân duy nhất và đầu tiên sở hữu hệ thống phòng chiếu phim đạt chuẩn Hollywood tại TP. Thái Nguyên.\r\n\r\nRạp được trang bị hệ thống máy chiếu, phòng chiếu hiện đại với 100% nhập khẩu từ nước ngoài, với 4 phòng chiếu tương được 535 ghế ngồi. Hệ thống âm thanh Dolby 7.1 và hệ thống cách âm chuẩn quốc tế đảm bảo chất lượng âm thanh sống động nhất cho từng thước phim bom tấn.\r\n\r\nMức giá xem phim tại LAL Cinemas Thái Nguyên rất cạnh tranh: giá vé 2D chỉ từ 40.000 VNĐ và giá vé 3D chỉ từ 60.000 VNĐ. Không chỉ có vậy, rạp còn có nhiều chương trình khuyến mại, ưu đãi hàng tuần như đồng giá vé 40.000 vào các ngày Thứ 3 vui vẻ, Thứ 4 Beta\'s Day, đồng giá vé cho Học sinh sinh viên, người cao tuổi, trẻ em.....','contact@lalthainguyen.com','https://res.cloudinary.com/dandjf6ke/image/upload/v1734409906/Image/Theater/image_1734409901772.jpg','LAL THÁI NGUYÊN','0867 460 053',_binary '');
/*!40000 ALTER TABLE `theater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticketid` bigint NOT NULL AUTO_INCREMENT,
  `bookingid` bigint DEFAULT NULL,
  `seatid` bigint DEFAULT NULL,
  PRIMARY KEY (`ticketid`),
  KEY `FKkx98llscqtvn251c5vmww3koq` (`bookingid`),
  KEY `FKsd3pwhyh8pk8ump8y4tcqje9e` (`seatid`),
  CONSTRAINT `FKkx98llscqtvn251c5vmww3koq` FOREIGN KEY (`bookingid`) REFERENCES `booking` (`bookingid`),
  CONSTRAINT `FKsd3pwhyh8pk8ump8y4tcqje9e` FOREIGN KEY (`seatid`) REFERENCES `seat` (`seatid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,1,24),(2,2,152),(3,3,38),(4,4,37),(5,5,152),(6,6,200),(7,6,199),(8,7,198),(9,8,197),(10,9,196),(11,10,195),(12,11,194),(13,12,197),(14,13,198),(15,14,199),(16,15,200),(17,16,193),(18,17,192),(19,18,164),(20,19,176),(21,20,188),(22,21,187),(23,22,186),(24,23,185),(25,24,191),(26,25,190),(27,26,484),(28,27,483),(29,27,482),(30,27,481),(31,27,480);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_frame`
--

DROP TABLE IF EXISTS `time_frame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_frame` (
  `time_frameid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `surcharge` float NOT NULL,
  `time_end` time(6) DEFAULT NULL,
  `time_start` time(6) DEFAULT NULL,
  PRIMARY KEY (`time_frameid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_frame`
--

LOCK TABLES `time_frame` WRITE;
/*!40000 ALTER TABLE `time_frame` DISABLE KEYS */;
INSERT INTO `time_frame` VALUES (1,'Trước 17h',0,'17:00:00.000000','07:00:00.000000'),(2,'Sau 17h',10000,'23:00:00.000000','17:00:00.000000');
/*!40000 ALTER TABLE `time_frame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_customer`
--

DROP TABLE IF EXISTS `type_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_customer` (
  `type_customerid` bigint NOT NULL AUTO_INCREMENT,
  `age_end` int DEFAULT NULL,
  `age_start` int DEFAULT NULL,
  `discount` float NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`type_customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_customer`
--

LOCK TABLES `type_customer` WRITE;
/*!40000 ALTER TABLE `type_customer` DISABLE KEYS */;
INSERT INTO `type_customer` VALUES (1,1,13,20,'Trẻ em'),(2,13,23,10,'Học sinh, sinh viên'),(3,23,60,0,'Người lớn'),(4,60,NULL,20,'Người già'),(5,NULL,NULL,10,'Thành viên');
/*!40000 ALTER TABLE `type_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_discount`
--

DROP TABLE IF EXISTS `type_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_discount` (
  `type_discountid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_discountid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_discount`
--

LOCK TABLES `type_discount` WRITE;
/*!40000 ALTER TABLE `type_discount` DISABLE KEYS */;
INSERT INTO `type_discount` VALUES (1,'Giảm theo phần trăm'),(2,'Giảm theo số tiền');
/*!40000 ALTER TABLE `type_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_room`
--

DROP TABLE IF EXISTS `type_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_room` (
  `type_roomid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surcharge` float NOT NULL,
  PRIMARY KEY (`type_roomid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_room`
--

LOCK TABLES `type_room` WRITE;
/*!40000 ALTER TABLE `type_room` DISABLE KEYS */;
INSERT INTO `type_room` VALUES (1,'2D',0),(2,'3D',30000),(3,'4D',50000),(4,'IMAX',70000);
/*!40000 ALTER TABLE `type_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_seat`
--

DROP TABLE IF EXISTS `type_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_seat` (
  `type_seatid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surcharge` float NOT NULL,
  PRIMARY KEY (`type_seatid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_seat`
--

LOCK TABLES `type_seat` WRITE;
/*!40000 ALTER TABLE `type_seat` DISABLE KEYS */;
INSERT INTO `type_seat` VALUES (1,'Thường',0),(2,'Vip',10000),(3,'Ghế đôi',20000);
/*!40000 ALTER TABLE `type_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_type` varchar(31) NOT NULL,
  `userid` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `day_in_work` date DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `status_employee` bit(1) DEFAULT NULL,
  `access_level` varchar(255) DEFAULT NULL,
  `points` int DEFAULT NULL,
  `roleid` bigint DEFAULT NULL,
  `managerid` bigint DEFAULT NULL,
  `levelid` bigint DEFAULT NULL,
  `theaterid` bigint DEFAULT NULL,
  `verification_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `UKpxytbfuhhprygrnns7gtssmpl` (`theaterid`),
  KEY `FK2ovmsl4hvm5vu1w8i308r5j6w` (`roleid`),
  KEY `FKh2q1gkdk95hs1fljf6phepq1l` (`managerid`),
  KEY `FKjaxbsyvx6ld1vrfad4yqdk8jg` (`levelid`),
  CONSTRAINT `FK2ovmsl4hvm5vu1w8i308r5j6w` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`),
  CONSTRAINT `FKcpe446lwtq0a34gm2g2tordvp` FOREIGN KEY (`theaterid`) REFERENCES `theater` (`theaterid`),
  CONSTRAINT `FKh2q1gkdk95hs1fljf6phepq1l` FOREIGN KEY (`managerid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FKjaxbsyvx6ld1vrfad4yqdk8jg` FOREIGN KEY (`levelid`) REFERENCES `level` (`levelid`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('ADMIN',1,'$2a$10$sAAGW0qyj.gC9MhNejTjb.fhd7.OXcxKTr236BID0bDkJ8To.XM4i','a@gmail.com','Hà Nội','1996-06-14','admin1@gmail.com','male','https://res.cloudinary.com/dandjf6ke/image/upload/v1733936135/Image/User/image_1733936134331.jpg','Nguyễn Văn A','0123456789',NULL,_binary '','2024-10-20','Quản trị viên',_binary '','Tất cả quyền trong hệ thống',NULL,1,NULL,NULL,NULL,NULL),('CUSTOMER',3,'$2a$10$XpwcCueNzUpHsmbR5gXN8.BICM7VX7qoqkAlykZsugYRliagbGQCG','loannguyenabcd123@gmail.com','hanoi','2003-11-04','loannguyenabcd123@gmail.com','other',NULL,'Nguyễn Phương Thanh ','0359926002','2024-12-14 00:13:54.632127',_binary '',NULL,NULL,NULL,NULL,0,4,NULL,1,NULL,NULL),('AGENT',5,'$2a$10$vWxJYau0/weTVA0UtO5evuevsGSa2.qLIsqFikkGQBi.kUm/C/bIe','loa@gmail.com','hanoi','2024-12-04','loa@gmail.com','other',NULL,'nguyen thi loan','0123456789',NULL,_binary '',NULL,'Nhân viên bán vé',_binary '',NULL,0,3,39,1,NULL,NULL),('MANAGER',6,'$2a$10$GqfZDxdwFcPEei61OrYkP.SGdPE/jo27N36i3q4TcaFELrqN4b6EG','loan@gmail.com','hanoi','2006-02-16','loan@gmail.com','other','https://res.cloudinary.com/dandjf6ke/image/upload/v1733936241/Image/User/image_1733936240009.jpg','nguyen thi loan','0123456789',NULL,_binary '',NULL,'Quản lý',_binary '',NULL,0,2,NULL,1,1,NULL),('AGENT',7,'$2a$10$nrN5w/dpceLFvL9FWax8xeDUshPen4/EMPnpXBBhmA5WL/5YJFanK','loan123@gmail.com','hanoi','2005-06-30','loan123@gmail.com','other',NULL,'nguyen thi loan','0123456789',NULL,_binary '',NULL,NULL,_binary '',NULL,0,3,6,1,NULL,NULL),('ADMIN',29,'$2a$10$hnF11E72dg4e.HZEgKCzQ.gFBQ1q6zfINPtRdEVRNTZtKQ9wYGbw.','manager5@gmail.com','Hà Nội','1999-07-16','manager5@gmail.com',NULL,NULL,'Mạc Na','0123456789','2024-12-12 01:00:48.254450',_binary '\0','2024-12-12','Quản trị viên',_binary '','Tất cả quyền trong hệ thống ',NULL,1,NULL,NULL,NULL,NULL),('AGENT',38,'$2a$10$.XgfcsdPDGyRZZTbETXGC.lJJFtXumQZTZYovbaQrmaUlipb64giG','looaann123@gmail.com','hanoi','2024-12-09','looaann123@gmail.com','female',NULL,'Vương Ngữ Yên ','0359926002','2024-12-16 10:57:11.307598',_binary '',NULL,NULL,_binary '',NULL,0,3,6,1,NULL,NULL),('MANAGER',39,'$2a$10$n1aKLe0u1dOpq./djorlF.J2KaKWd3TNtMV7H2OZ6wWcyRTyDmm6a','manager3@gmail.com','HN','2024-12-16','manager3@gmail.com',NULL,NULL,'Vu Lượng','0123456789','2024-12-16 23:12:32.469205',_binary '\0','2024-12-16','Quản lý',_binary '',NULL,NULL,2,NULL,NULL,3,NULL),('CUSTOMER',46,'$2a$10$S4PQRfzeRHVarMBNkf.h9OGNinFExbAhVzM5uBxyMHYTjyBxXfvCq','hinhlam357@gmail.com','Hà Nội','2024-12-05','hinhlam357@gmail.com','male',NULL,'Nguyễn Thành Lâm','0123456789','2024-12-25 01:00:45.512742',_binary '',NULL,NULL,NULL,NULL,0,4,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_discount`
--

DROP TABLE IF EXISTS `user_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_discount` (
  `userid` bigint NOT NULL,
  `discountid` bigint NOT NULL,
  KEY `FKrnoh0rjlmt9kf99p4v42bddmd` (`discountid`),
  KEY `FK1ydco38ih83fkvkd5tk9hxtrx` (`userid`),
  CONSTRAINT `FK1ydco38ih83fkvkd5tk9hxtrx` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FKrnoh0rjlmt9kf99p4v42bddmd` FOREIGN KEY (`discountid`) REFERENCES `discount` (`discountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_discount`
--

LOCK TABLES `user_discount` WRITE;
/*!40000 ALTER TABLE `user_discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_genre`
--

DROP TABLE IF EXISTS `user_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_genre` (
  `userid` bigint NOT NULL,
  `genreid` bigint NOT NULL,
  KEY `FK7561ssd53scdn5ppn47x22n9d` (`genreid`),
  KEY `FKd4i5602068on1i9m8fqu3h4fc` (`userid`),
  CONSTRAINT `FK7561ssd53scdn5ppn47x22n9d` FOREIGN KEY (`genreid`) REFERENCES `genre` (`genreid`),
  CONSTRAINT `FKd4i5602068on1i9m8fqu3h4fc` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_genre`
--

LOCK TABLES `user_genre` WRITE;
/*!40000 ALTER TABLE `user_genre` DISABLE KEYS */;
INSERT INTO `user_genre` VALUES (3,1),(3,2),(3,3),(38,3),(38,2),(46,1),(46,5),(46,4);
/*!40000 ALTER TABLE `user_genre` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-25  2:18:31
