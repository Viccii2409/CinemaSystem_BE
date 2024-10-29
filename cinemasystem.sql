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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `userid` bigint NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `FKn1bafc2a3wjs4dsu0ke873w0v` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'user1','password1'),(2,'user2','password2'),(3,'user3','password3'),(4,'user4','password4'),(5,'user5','password5'),(6,'user6','password6'),(7,'user7','password7'),(8,'user8','password8'),(9,'user9','password9'),(10,'user10','password10');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_price`
--

DROP TABLE IF EXISTS `base_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base_price` (
  `default_price` float NOT NULL,
  `base_priceid` bigint NOT NULL AUTO_INCREMENT,
  `created_at` varchar(255) DEFAULT NULL,
  `updated_at` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`base_priceid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_price`
--

LOCK TABLES `base_price` WRITE;
/*!40000 ALTER TABLE `base_price` DISABLE KEYS */;
INSERT INTO `base_price` VALUES (100,1,'2024-01-01','2024-01-01'),(150,2,'2024-01-02','2024-01-02'),(200,3,'2024-01-03','2024-01-03'),(250,4,'2024-01-04','2024-01-04'),(300,5,'2024-01-05','2024-01-05'),(350,6,'2024-01-06','2024-01-06'),(400,7,'2024-01-07','2024-01-07'),(450,8,'2024-01-08','2024-01-08'),(500,9,'2024-01-09','2024-01-09'),(550,10,'2024-01-10','2024-01-10');
/*!40000 ALTER TABLE `base_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cast`
--

DROP TABLE IF EXISTS `cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cast` (
  `castid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`castid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cast`
--

LOCK TABLES `cast` WRITE;
/*!40000 ALTER TABLE `cast` DISABLE KEYS */;
INSERT INTO `cast` VALUES (1,'Leonardo DiCaprio'),(2,'Natalie Portman'),(3,'Brad Pitt'),(4,'Meryl Streep'),(5,'Robert Downey Jr.'),(6,'Scarlett Johansson'),(7,'Tom Hanks'),(8,'Jennifer Lawrence'),(9,'Denzel Washington'),(10,'Emma Stone');
/*!40000 ALTER TABLE `cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day_of_week`
--

DROP TABLE IF EXISTS `day_of_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `day_of_week` (
  `day_end` int NOT NULL,
  `day_start` int NOT NULL,
  `surcharge` float NOT NULL,
  `day_of_weekid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`day_of_weekid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day_of_week`
--

LOCK TABLES `day_of_week` WRITE;
/*!40000 ALTER TABLE `day_of_week` DISABLE KEYS */;
INSERT INTO `day_of_week` VALUES (2,1,0,1,'Thứ Hai'),(3,2,0,2,'Thứ Ba'),(4,3,0,3,'Thứ Tư'),(5,4,0,4,'Thứ Năm'),(6,5,0,5,'Thứ Sáu'),(7,6,10,6,'Thứ Bảy'),(1,7,10,7,'Chủ Nhật'),(7,1,20,8,'Ngày lễ'),(7,6,15,9,'Ngày cuối tuần'),(5,1,0,10,'Ngày thường');
/*!40000 ALTER TABLE `day_of_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `director` (
  `directorid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`directorid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (1,'Christopher Nolan'),(2,'Martin Scorsese'),(3,'Steven Spielberg'),(4,'Quentin Tarantino'),(5,'Ridley Scott'),(6,'James Cameron'),(7,'Woody Allen'),(8,'Alfred Hitchcock'),(9,'Francis Ford Coppola'),(10,'Peter Jackson');
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `reduced_value` float NOT NULL,
  `status` bit(1) NOT NULL,
  `discountid` bigint NOT NULL AUTO_INCREMENT,
  `type_discountid` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount_code` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`discountid`),
  KEY `FKr0xthnbi39g12iawyth7l1ms7` (`type_discountid`),
  CONSTRAINT `FKr0xthnbi39g12iawyth7l1ms7` FOREIGN KEY (`type_discountid`) REFERENCES `type_discount` (`type_discountid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (10,_binary '',1,1,'Giảm giá 10% cho tất cả các vé','DISCOUNT10','discount_1.jpg','Giảm giá 10%'),(20,_binary '',2,2,'Giảm giá 20% cho vé VIP','DISCOUNT20','discount_2.jpg','Giảm giá 20%'),(15,_binary '',3,1,'Giảm giá 15% cho vé thường','DISCOUNT15','discount_3.jpg','Giảm giá 15%'),(30,_binary '',4,3,'Giảm giá 30% cho nhóm từ 5 người','DISCOUNT30','discount_4.jpg','Giảm giá 30%'),(5,_binary '',5,1,'Giảm giá 5% cho sinh viên','DISCOUNT05','discount_5.jpg','Giảm giá 5%'),(25,_binary '',6,2,'Giảm giá 25% cho vé mùa','DISCOUNT25','discount_6.jpg','Giảm giá 25%'),(40,_binary '',7,3,'Giảm giá 40% cho lễ hội','DISCOUNT40','discount_7.jpg','Giảm giá 40%'),(50,_binary '',8,2,'Giảm giá 50% cho ngày đặc biệt','DISCOUNT50','discount_8.jpg','Giảm giá 50%'),(12,_binary '',9,1,'Giảm giá 12% cho khách hàng thân thiết','DISCOUNT12','discount_9.jpg','Giảm giá 12%'),(18,_binary '',10,3,'Giảm giá 18% cho vé cuối tuần','DISCOUNT18','discount_10.jpg','Giảm giá 18%');
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
  `movieid` bigint DEFAULT NULL,
  `ratingid` bigint DEFAULT NULL,
  `ticket_boughtid` bigint DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`feedbackid`),
  UNIQUE KEY `UKwnhdmrp1l58pihm7iibpy3w5` (`ticket_boughtid`),
  KEY `FK4pses51sd6k8plfqqbr6hyvs5` (`movieid`),
  KEY `FKov0s2inw5frsfvrpwhyhifdq5` (`ratingid`),
  CONSTRAINT `FK4pses51sd6k8plfqqbr6hyvs5` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`),
  CONSTRAINT `FKlanxch1warx33axly0tqept0s` FOREIGN KEY (`ticket_boughtid`) REFERENCES `ticket_bought` (`ticket_boughtid`),
  CONSTRAINT `FKov0s2inw5frsfvrpwhyhifdq5` FOREIGN KEY (`ratingid`) REFERENCES `rating` (`ratingid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,1,1,NULL,'2024-10-27','Great movie, loved the storyline!'),(2,1,2,NULL,'2024-10-27','Amazing visuals and sound effects.'),(3,2,5,NULL,'2024-10-27','The acting was superb, highly recommend!'),(4,2,3,NULL,'2024-10-27','Not what I expected, but still enjoyable.'),(5,3,4,NULL,'2024-10-27','The plot was a bit slow but picked up.'),(6,3,5,NULL,'2024-10-27','Loved the characters and their development.'),(7,4,3,NULL,'2024-10-27','Could have been better in terms of pacing.'),(8,4,5,NULL,'2024-10-27','A classic that everyone should watch!'),(9,5,2,NULL,'2024-10-27','Didn’t live up to the hype.'),(10,5,5,NULL,'2024-10-27','An unforgettable experience!');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `status` bit(1) NOT NULL,
  `genreid` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`genreid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (_binary '',1,'Phim hành động với những pha gay cấn.','Hành Động'),(_binary '',2,'Phim hài với những tình huống vui nhộn.','Hài Hước'),(_binary '',3,'Phim kinh dị khiến bạn lạnh gáy.','Kinh Dị'),(_binary '',4,'Phim tình cảm với những mối quan hệ phức tạp.','Tình Cảm'),(_binary '',5,'Phim khoa học viễn tưởng về tương lai.','Khoa Học Viễn Tưởng'),(_binary '',6,'Phim phiêu lưu khám phá những vùng đất mới.','Phiêu Lưu'),(_binary '',7,'Phim hài kịch mang lại tiếng cười.','Hài Kịch'),(_binary '',8,'Phim hoạt hình dành cho trẻ em.','Hoạt Hình'),(_binary '',9,'Phim nhạc với những bài hát hấp dẫn.','Âm Nhạc'),(_binary '',10,'Phim thể thao về những cuộc thi và trận đấu.','Thể Thao');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `type` bit(1) NOT NULL,
  `imageid` bigint NOT NULL AUTO_INCREMENT,
  `movieid` bigint DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`imageid`),
  KEY `FKlcm8wfp1u77qvnyio6op74tvp` (`movieid`),
  CONSTRAINT `FKlcm8wfp1u77qvnyio6op74tvp` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (_binary '',1,1,'https://upload.wikimedia.org/wikipedia/en/0/0c/Avengers_Endgame_poster.jpg'),(_binary '',2,2,'https://upload.wikimedia.org/wikipedia/en/e/ec/The_Shawshank_Redemption.jpg'),(_binary '',3,3,'https://upload.wikimedia.org/wikipedia/en/1/1c/The_Godfather_%281972%29.jpg'),(_binary '',4,4,'https://upload.wikimedia.org/wikipedia/en/2/28/Inception_%282010%29.png'),(_binary '',5,5,'https://upload.wikimedia.org/wikipedia/en/8/8a/The_Dark_Knight.jpg'),(_binary '',6,6,'https://upload.wikimedia.org/wikipedia/en/8/82/Pulp_Fiction_Poster.jpg'),(_binary '',7,7,'https://upload.wikimedia.org/wikipedia/en/6/67/Forrest_Gump_poster.jpg'),(_binary '',8,8,'https://upload.wikimedia.org/wikipedia/en/c/c3/The_Matrix_Revolution_poster.jpg'),(_binary '',9,9,'https://upload.wikimedia.org/wikipedia/en/7/74/The_Lord_of_the_Rings_The_Return_of_the_King.jpg'),(_binary '',10,10,'https://upload.wikimedia.org/wikipedia/en/2/23/Spirited_Away_poster.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
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
  PRIMARY KEY (`levelid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (1,'Bronze'),(2,'Silver'),(3,'Gold'),(4,'Platinum'),(5,'Diamond'),(6,'Emerald'),(7,'Sapphire'),(8,'Ruby'),(9,'Black'),(10,'VIP');
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `duration` int NOT NULL,
  `status` bit(1) NOT NULL,
  `directorid` bigint DEFAULT NULL,
  `languageid` bigint DEFAULT NULL,
  `movieid` bigint NOT NULL AUTO_INCREMENT,
  `release_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movieid`),
  KEY `FKq867msy9fpdbt7odcgrl16jx3` (`directorid`),
  KEY `FKt25khrt834p352t9ksaqtgwcg` (`languageid`),
  CONSTRAINT `FKq867msy9fpdbt7odcgrl16jx3` FOREIGN KEY (`directorid`) REFERENCES `director` (`directorid`),
  CONSTRAINT `FKt25khrt834p352t9ksaqtgwcg` FOREIGN KEY (`languageid`) REFERENCES `language` (`languageid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (181,_binary '',1,1,1,'2019-04-26 00:00:00.000000','Cuộc chiến cuối cùng của các Avengers chống lại Thanos.','Avengers: Endgame'),(142,_binary '',2,1,2,'1994-09-23 00:00:00.000000','Một người đàn ông bị kết án oan tạo ra kế hoạch vượt ngục trong suốt 19 năm.','The Shawshank Redemption'),(175,_binary '',3,1,3,'1972-03-24 00:00:00.000000','Câu chuyện về gia đình mafia Corleone và sự chuyển giao quyền lực.','The Godfather'),(148,_binary '',1,2,4,'2010-07-16 00:00:00.000000','Một nhóm người thực hiện các vụ trộm ý tưởng từ giấc mơ.','Inception'),(152,_binary '',4,1,5,'2008-07-18 00:00:00.000000','Batman chiến đấu với Joker để bảo vệ Gotham City.','The Dark Knight'),(154,_binary '',2,1,6,'1994-10-14 00:00:00.000000','Câu chuyện về các tay gangster, những vụ giết người và sự ngẫu nhiên trong cuộc sống.','Pulp Fiction'),(142,_binary '',3,1,7,'1994-07-06 00:00:00.000000','Một người đàn ông bình thường với một cuộc sống phi thường.','Forrest Gump'),(136,_binary '',1,1,8,'1999-03-31 00:00:00.000000','Một hacker khám phá ra sự thật về thế giới ảo và cuộc chiến chống lại các máy móc.','The Matrix'),(201,_binary '',4,1,9,'2003-12-17 00:00:00.000000','Cuộc chiến cuối cùng giữa các lực lượng của Middle-earth.','The Lord of the Rings: The Return of the King'),(169,_binary '',1,2,10,'2014-11-07 00:00:00.000000','Một nhóm phi hành gia khám phá các lỗ sâu không gian để tìm kiếm một hành tinh mới cho nhân loại.','Interstellar');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_cast`
--

DROP TABLE IF EXISTS `movie_cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_cast` (
  `castid` int NOT NULL,
  `movieid` bigint NOT NULL,
  KEY `FKierla4vj0rc5myy2xkperpwjg` (`castid`),
  KEY `FK3rie7r8vlrhdcqy99y67cafmy` (`movieid`),
  CONSTRAINT `FK3rie7r8vlrhdcqy99y67cafmy` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`),
  CONSTRAINT `FKierla4vj0rc5myy2xkperpwjg` FOREIGN KEY (`castid`) REFERENCES `cast` (`castid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_cast`
--

LOCK TABLES `movie_cast` WRITE;
/*!40000 ALTER TABLE `movie_cast` DISABLE KEYS */;
INSERT INTO `movie_cast` VALUES (1,1),(2,1),(3,2),(4,3),(5,4),(6,5),(7,6),(8,7),(9,8),(10,9);
/*!40000 ALTER TABLE `movie_cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_genre` (
  `genreid` bigint NOT NULL,
  `movieid` bigint NOT NULL,
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
INSERT INTO `movie_genre` VALUES (1,1),(2,1),(3,2),(4,3),(5,4),(6,5),(7,6),(8,7),(9,8),(10,9);
/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notificationid` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notificationid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'Giảm giá 50% cho tất cả vé xem phim vào cuối tuần này.','2024-10-27','Khuyến Mãi Mới!'),(2,'Phim \"Kẻ Săn Tìm\" sẽ được chiếu lúc 20:00 thay vì 19:00.','2024-10-28','Thay Đổi Giờ Chiếu'),(3,'Tham gia ngay để nhận được nhiều ưu đãi hấp dẫn!','2024-10-29','Chương Trình Khách Hàng Thân Thiết'),(4,'Phim \"Cuộc Chiến Không Gian\" sẽ ra mắt vào ngày 1 tháng 11.','2024-10-30','Ra Mắt Phim Mới'),(5,'Đừng quên mời bạn bè tham gia cùng!','2024-10-31','Mời Bạn Xem Phim'),(6,'Giảm giá 30% cho tất cả vé trước 1 tuần.','2024-11-01','Giảm Giá Vé'),(7,'Cảm ơn bạn đã ủng hộ chúng tôi trong thời gian qua!','2024-11-02','Cảm Ơn Khách Hàng'),(8,'Đến xem phim trong tuần này để nhận quà tặng bất ngờ!','2024-11-03','Thông Báo Đặc Biệt'),(9,'Tham gia sự kiện giao lưu với các diễn viên vào ngày 5 tháng 11.','2024-11-04','Tổ Chức Sự Kiện'),(10,'Khuyến mãi hấp dẫn cho mọi khán giả đến rạp vào thứ Bảy.','2024-11-05','Chương Trình Khuyến Mãi');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `money_returned` float DEFAULT NULL,
  `received` float DEFAULT NULL,
  `agentid` bigint DEFAULT NULL,
  `discountid` bigint DEFAULT NULL,
  `paymentid` bigint NOT NULL AUTO_INCREMENT,
  `type_pay` varchar(31) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paymentid`),
  KEY `FKcddyxy1wvhdc2lb5qrrwo8mr` (`agentid`),
  KEY `FKaehjec56hsr2t4oqwypgumntd` (`discountid`),
  CONSTRAINT `FKaehjec56hsr2t4oqwypgumntd` FOREIGN KEY (`discountid`) REFERENCES `discount` (`discountid`),
  CONSTRAINT `FKcddyxy1wvhdc2lb5qrrwo8mr` FOREIGN KEY (`agentid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (50,200,1,1,1,'Credit Card','123456789','Bank A','123456789','2024-10-20','https://example.com/image1.jpg','Nguyễn Văn A'),(25,100,2,1,2,'Debit Card','987654321','Bank B','987654321','2024-10-21','https://example.com/image2.jpg','Trần Thị B'),(0,50,3,2,3,'Cash','123456789','N/A','246801357','2024-10-22','https://example.com/image3.jpg','Lê Mỹ C'),(50,250,4,2,4,'PayPal','456789123','Bank C','456789123','2024-10-23','https://example.com/image4.jpg','Phạm Văn D'),(50,150,5,3,5,'Bank Transfer','321654987','Bank D','321654987','2024-10-24','https://example.com/image5.jpg','Nguyễn Thị E'),(50,350,6,3,6,'Credit Card','159753486','Bank E','159753486','2024-10-25','https://example.com/image6.jpg','Trần Văn F'),(30,150,7,4,7,'Debit Card','753159486','Bank F','753159486','2024-10-26','https://example.com/image7.jpg','Lê Văn G'),(0,80,8,4,8,'Cash','753159487','N/A','852963741','2024-10-27','https://example.com/image8.jpg','Phạm Thị H'),(10,100,9,5,9,'PayPal','147258369','Bank G','147258369','2024-10-28','https://example.com/image9.jpg','Nguyễn Văn I'),(50,200,10,5,10,'Bank Transfer','963852741','Bank H','963852741','2024-10-29','https://example.com/image10.jpg','Trần Thị J');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `status` bit(1) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (_binary '',1,'Manager'),(_binary '',2,'Assistant Manager'),(_binary '',3,'Ticket Seller'),(_binary '',4,'Concessions Attendant'),(_binary '',5,'Projectionist'),(_binary '',6,'Customer Service Representative'),(_binary '',7,'Usher'),(_binary '',8,'Marketing Coordinator'),(_binary '',9,'Event Coordinator'),(_binary '',10,'Maintenance Staff');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_role`
--

DROP TABLE IF EXISTS `position_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position_role` (
  `positionid` bigint NOT NULL,
  `roleid` bigint NOT NULL,
  KEY `FK2j841j9xouxgxan9lplwq8a9v` (`roleid`),
  KEY `FKrmmekqdnu8allltar14kaiaoc` (`positionid`),
  CONSTRAINT `FK2j841j9xouxgxan9lplwq8a9v` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`),
  CONSTRAINT `FKrmmekqdnu8allltar14kaiaoc` FOREIGN KEY (`positionid`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_role`
--

LOCK TABLES `position_role` WRITE;
/*!40000 ALTER TABLE `position_role` DISABLE KEYS */;
INSERT INTO `position_role` VALUES (1,1),(1,2),(2,3),(2,4),(3,5),(3,6),(4,7),(5,8),(5,9),(6,10);
/*!40000 ALTER TABLE `position_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `star` int NOT NULL,
  `ratingid` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ratingid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (5,1,'Phim xuất sắc, đáng xem!'),(4,2,'Phim rất hay nhưng có một số điểm cần cải thiện.'),(3,3,'Phim tạm, không có gì đặc biệt.'),(2,4,'Phim khá tệ, không thuyết phục.'),(1,5,'Phim rất tệ, không nên xem.'),(4,6,'Diễn xuất tốt, nhưng cốt truyện không hấp dẫn.'),(5,7,'Một trong những bộ phim hay nhất tôi từng xem!'),(3,8,'Cảnh quay đẹp nhưng nội dung không lôi cuốn.'),(2,9,'Nội dung yếu kém, diễn viên không truyền tải được cảm xúc.'),(4,10,'Một trải nghiệm xem phim khá tốt, có thể xem lại.');
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator'),(2,'Manager'),(3,'Assistant Manager'),(4,'Ticket Seller'),(5,'Concessions Attendant'),(6,'Projectionist'),(7,'Customer Service Representative'),(8,'Usher'),(9,'Marketing Coordinator'),(10,'Event Coordinator');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `status` bit(1) NOT NULL,
  `roomid` bigint NOT NULL AUTO_INCREMENT,
  `theaterid` bigint DEFAULT NULL,
  `type_roomid` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roomid`),
  KEY `FKptor8dxb82msh7uahkjvrbdnv` (`theaterid`),
  KEY `FKatsp17l3fv4b7b7i5dtbiy0e0` (`type_roomid`),
  CONSTRAINT `FKatsp17l3fv4b7b7i5dtbiy0e0` FOREIGN KEY (`type_roomid`) REFERENCES `type_room` (`type_roomid`),
  CONSTRAINT `FKptor8dxb82msh7uahkjvrbdnv` FOREIGN KEY (`theaterid`) REFERENCES `theater` (`theaterid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (_binary '',1,1,1,'Room 1'),(_binary '',2,1,2,'Room 2'),(_binary '',3,2,1,'Room 3'),(_binary '',4,2,2,'Room 4'),(_binary '\0',5,1,1,'Room 5'),(_binary '',6,3,3,'Room 6'),(_binary '',7,3,2,'Room 7'),(_binary '\0',8,3,1,'Room 8'),(_binary '',9,2,2,'Room 9'),(_binary '',10,1,3,'Room 10');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seat_num` int NOT NULL,
  `status` bit(1) NOT NULL,
  `roomid` bigint DEFAULT NULL,
  `seatid` bigint NOT NULL AUTO_INCREMENT,
  `type_seatid` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `row_num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seatid`),
  KEY `FKry4ek81raj395pu717g8ufhbg` (`roomid`),
  KEY `FKs64fy9wiyus8ak48dsjo384em` (`type_seatid`),
  CONSTRAINT `FKry4ek81raj395pu717g8ufhbg` FOREIGN KEY (`roomid`) REFERENCES `room` (`roomid`),
  CONSTRAINT `FKs64fy9wiyus8ak48dsjo384em` FOREIGN KEY (`type_seatid`) REFERENCES `type_seat` (`type_seatid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,_binary '',1,1,1,'A1','A'),(2,_binary '',1,2,1,'A2','A'),(3,_binary '',1,3,2,'A3','A'),(4,_binary '',1,4,2,'B1','B'),(5,_binary '',1,5,1,'B2','B'),(6,_binary '\0',2,6,1,'C1','C'),(7,_binary '',2,7,2,'C2','C'),(8,_binary '',2,8,2,'C3','C'),(9,_binary '',3,9,1,'D1','D'),(10,_binary '',3,10,1,'D2','D');
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_availability`
--

DROP TABLE IF EXISTS `seat_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat_availability` (
  `is_available` bit(1) NOT NULL,
  `seat_availabilityid` bigint NOT NULL AUTO_INCREMENT,
  `seatid` bigint DEFAULT NULL,
  `showtimeid` bigint DEFAULT NULL,
  PRIMARY KEY (`seat_availabilityid`),
  KEY `FKpd3cye1gn80syqatxd2j5edqx` (`seatid`),
  KEY `FKcd1885nxp2p3285twnoplhiq2` (`showtimeid`),
  CONSTRAINT `FKcd1885nxp2p3285twnoplhiq2` FOREIGN KEY (`showtimeid`) REFERENCES `showtime` (`showtimeid`),
  CONSTRAINT `FKpd3cye1gn80syqatxd2j5edqx` FOREIGN KEY (`seatid`) REFERENCES `seat` (`seatid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_availability`
--

LOCK TABLES `seat_availability` WRITE;
/*!40000 ALTER TABLE `seat_availability` DISABLE KEYS */;
INSERT INTO `seat_availability` VALUES (_binary '',1,1,1),(_binary '',2,2,1),(_binary '\0',3,3,1),(_binary '',4,4,1),(_binary '\0',5,5,1),(_binary '',6,1,2),(_binary '',7,2,2),(_binary '',8,3,2),(_binary '\0',9,4,2),(_binary '',10,5,2);
/*!40000 ALTER TABLE `seat_availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_reservation`
--

DROP TABLE IF EXISTS `seat_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat_reservation` (
  `seat_reservationid` bigint NOT NULL AUTO_INCREMENT,
  `seatid` bigint DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  `expiry_time` varchar(255) DEFAULT NULL,
  `reservation_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seat_reservationid`),
  KEY `FK8tl23l2mfawb69q373uq3gpvn` (`userid`),
  KEY `FKiyb3kwhiyfa33tsrn60oho6vl` (`seatid`),
  CONSTRAINT `FK8tl23l2mfawb69q373uq3gpvn` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FKiyb3kwhiyfa33tsrn60oho6vl` FOREIGN KEY (`seatid`) REFERENCES `seat` (`seatid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_reservation`
--

LOCK TABLES `seat_reservation` WRITE;
/*!40000 ALTER TABLE `seat_reservation` DISABLE KEYS */;
INSERT INTO `seat_reservation` VALUES (1,1,1,'2024-10-28','2024-10-27'),(2,2,1,'2024-10-28','2024-10-27'),(3,3,2,'2024-10-28','2024-10-27'),(4,4,2,'2024-10-28','2024-10-27'),(5,5,3,'2024-10-28','2024-10-27'),(6,1,4,'2024-10-28','2024-10-27'),(7,2,4,'2024-10-28','2024-10-27'),(8,3,5,'2024-10-28','2024-10-27'),(9,4,5,'2024-10-28','2024-10-27'),(10,5,6,'2024-10-28','2024-10-27');
/*!40000 ALTER TABLE `seat_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_ticket`
--

DROP TABLE IF EXISTS `seat_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat_ticket` (
  `base_priceid` bigint DEFAULT NULL,
  `day_of_weekid` bigint DEFAULT NULL,
  `seat_ticketid` bigint NOT NULL AUTO_INCREMENT,
  `seatid` bigint DEFAULT NULL,
  `time_frameid` bigint DEFAULT NULL,
  `type_userid` bigint DEFAULT NULL,
  PRIMARY KEY (`seat_ticketid`),
  KEY `FKbt0g0p9xnefhc6sxliquyv0va` (`base_priceid`),
  KEY `FKm0mx7t5b2huth09xknjpqmy2j` (`day_of_weekid`),
  KEY `FK25vxtc3hn113byu70fbhbe1gk` (`seatid`),
  KEY `FK5a3lhfkv6gmi5dxq72yuqeerw` (`time_frameid`),
  KEY `FKeuvy083a16c5svh9w5dcui3sa` (`type_userid`),
  CONSTRAINT `FK25vxtc3hn113byu70fbhbe1gk` FOREIGN KEY (`seatid`) REFERENCES `seat` (`seatid`),
  CONSTRAINT `FK5a3lhfkv6gmi5dxq72yuqeerw` FOREIGN KEY (`time_frameid`) REFERENCES `time_frame` (`time_frameid`),
  CONSTRAINT `FKbt0g0p9xnefhc6sxliquyv0va` FOREIGN KEY (`base_priceid`) REFERENCES `base_price` (`base_priceid`),
  CONSTRAINT `FKeuvy083a16c5svh9w5dcui3sa` FOREIGN KEY (`type_userid`) REFERENCES `type_user` (`type_userid`),
  CONSTRAINT `FKm0mx7t5b2huth09xknjpqmy2j` FOREIGN KEY (`day_of_weekid`) REFERENCES `day_of_week` (`day_of_weekid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_ticket`
--

LOCK TABLES `seat_ticket` WRITE;
/*!40000 ALTER TABLE `seat_ticket` DISABLE KEYS */;
INSERT INTO `seat_ticket` VALUES (1,1,1,1,1,1),(1,1,2,2,2,1),(1,2,3,1,3,2),(1,2,4,3,4,2),(2,1,5,2,1,3),(2,1,6,1,2,3),(2,3,7,4,3,2),(2,2,8,5,4,1),(2,3,9,3,1,3),(3,3,10,2,2,1);
/*!40000 ALTER TABLE `seat_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtime`
--

DROP TABLE IF EXISTS `showtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showtime` (
  `end_time` time(6) DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `movieid` bigint DEFAULT NULL,
  `roomid` bigint DEFAULT NULL,
  `showtimeid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`showtimeid`),
  KEY `FKici557wklls9lplk2bk342ajm` (`movieid`),
  KEY `FKhttqwu26y8pmrxfpo4pawn6rm` (`roomid`),
  CONSTRAINT `FKhttqwu26y8pmrxfpo4pawn6rm` FOREIGN KEY (`roomid`) REFERENCES `room` (`roomid`),
  CONSTRAINT `FKici557wklls9lplk2bk342ajm` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtime`
--

LOCK TABLES `showtime` WRITE;
/*!40000 ALTER TABLE `showtime` DISABLE KEYS */;
INSERT INTO `showtime` VALUES ('12:00:00.000000','10:00:00.000000',_binary '','2024-10-28 00:00:00.000000',1,1,1),('15:00:00.000000','13:00:00.000000',_binary '','2024-10-28 00:00:00.000000',1,1,2),('18:00:00.000000','16:00:00.000000',_binary '','2024-10-28 00:00:00.000000',2,2,3),('13:00:00.000000','11:00:00.000000',_binary '','2024-10-29 00:00:00.000000',2,2,4),('16:00:00.000000','14:00:00.000000',_binary '','2024-10-29 00:00:00.000000',3,1,5),('19:00:00.000000','17:00:00.000000',_binary '','2024-10-29 00:00:00.000000',3,1,6),('12:00:00.000000','10:00:00.000000',_binary '','2024-10-30 00:00:00.000000',1,3,7),('15:00:00.000000','13:00:00.000000',_binary '','2024-10-30 00:00:00.000000',2,3,8),('18:00:00.000000','16:00:00.000000',_binary '','2024-10-30 00:00:00.000000',3,2,9),('21:00:00.000000','19:00:00.000000',_binary '','2024-10-30 00:00:00.000000',1,2,10);
/*!40000 ALTER TABLE `showtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theater`
--

DROP TABLE IF EXISTS `theater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theater` (
  `status` bit(1) NOT NULL,
  `theaterid` bigint NOT NULL AUTO_INCREMENT,
  `address_detail` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`theaterid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theater`
--

LOCK TABLES `theater` WRITE;
/*!40000 ALTER TABLE `theater` DISABLE KEYS */;
INSERT INTO `theater` VALUES (_binary '',1,'123 Thanh Xuân St','Hà Nội','Rạp chiếu phim hiện đại tại Thanh Xuân.','Thanh Xuân','contact@lalthanhxuan.com','image1.jpg','LAL Thanh Xuân','0123456789','Ward 1'),(_binary '',2,'456 Đà Nẵng Ave','Đà Nẵng','Nơi lý tưởng để thưởng thức phim tại Đà Nẵng.','Hải Châu','info@laldanang.com','image2.jpg','LAL Đà Nẵng','0987654321','Ward 2'),(_binary '',3,'789 TP.HCM Rd','TP.HCM','Rạp phim nổi bật tại TP.HCM.','Quận 1','support@laltphcm.com','image3.jpg','LAL TP.HCM','0123987654','Ward 3'),(_binary '',4,'321 Nha Trang Blvd','Khánh Hòa','Thưởng thức những bộ phim hấp dẫn tại Nha Trang.','Vĩnh Hải','hello@lalnhatrang.com','image4.jpg','LAL Nha Trang','0765432189','Ward 4'),(_binary '',5,'654 Hội An St','Quảng Nam','Rạp chiếu phim gần gũi tại Hội An.','Hội An','contact@lalhoian.com','image5.jpg','LAL Hội An','0456781234','Ward 5'),(_binary '',6,'987 Vũng Tàu Ln','Bà Rịa - Vũng Tàu','Trải nghiệm phim ảnh tuyệt vời tại Vũng Tàu.','Vũng Tàu','info@lavungtau.com','image6.jpg','LAL Vũng Tàu','0345678901','Ward 6'),(_binary '',7,'135 Cần Thơ St','Cần Thơ','Rạp phim sôi động tại Cần Thơ.','Ninh Kiều','support@lalcantho.com','image7.jpg','LAL Cần Thơ','0123456780','Ward 7'),(_binary '',8,'246 Huế Dr','Thừa Thiên Huế','Trải nghiệm phim ảnh phong cách tại Huế.','Phú Nhuận','contact@lalhue.com','image8.jpg','LAL Huế','0789012345','Ward 8'),(_binary '',9,'357 Hạ Long Ave','Quảng Ninh','Khám phá những bộ phim tại Hạ Long.','Hạ Long','info@lalhalong.com','image9.jpg','LAL Hạ Long','0678901234','Ward 9'),(_binary '',10,'468 Bắc Ninh Rd','Bắc Ninh','Nơi gặp gỡ của những tín đồ yêu phim tại Bắc Ninh.','Bắc Ninh','support@lalbacninh.com','image10.jpg','LAL Bắc Ninh','0567890123','Ward 10');
/*!40000 ALTER TABLE `theater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_bought`
--

DROP TABLE IF EXISTS `ticket_bought`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_bought` (
  `customerid` bigint DEFAULT NULL,
  `paymentid` bigint DEFAULT NULL,
  `seat_ticketid` bigint DEFAULT NULL,
  `showtimeid` bigint DEFAULT NULL,
  `ticket_boughtid` bigint NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ticket_boughtid`),
  UNIQUE KEY `UKkf4wry0u0lg05usw7laa7dm5w` (`seat_ticketid`),
  KEY `FKmmlix0i5kqxdu2u6kmgrv4a0l` (`customerid`),
  KEY `FKfubwlk9q5k23gwoiyp1mxwhd3` (`paymentid`),
  KEY `FK9siu1loe3qyptju9iawtfyq4y` (`showtimeid`),
  CONSTRAINT `FK9siu1loe3qyptju9iawtfyq4y` FOREIGN KEY (`showtimeid`) REFERENCES `showtime` (`showtimeid`),
  CONSTRAINT `FKfubwlk9q5k23gwoiyp1mxwhd3` FOREIGN KEY (`paymentid`) REFERENCES `payment` (`paymentid`),
  CONSTRAINT `FKmmlix0i5kqxdu2u6kmgrv4a0l` FOREIGN KEY (`customerid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FKnvqeau46ys28ktdwlwbyegsem` FOREIGN KEY (`seat_ticketid`) REFERENCES `seat_ticket` (`seat_ticketid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_bought`
--

LOCK TABLES `ticket_bought` WRITE;
/*!40000 ALTER TABLE `ticket_bought` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_bought` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_frame`
--

DROP TABLE IF EXISTS `time_frame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_frame` (
  `surcharge` float NOT NULL,
  `time_end` time(6) DEFAULT NULL,
  `time_start` time(6) DEFAULT NULL,
  `time_frameid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`time_frameid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_frame`
--

LOCK TABLES `time_frame` WRITE;
/*!40000 ALTER TABLE `time_frame` DISABLE KEYS */;
INSERT INTO `time_frame` VALUES (0,'12:00:00.000000','08:00:00.000000',1,'Buổi sáng'),(10,'17:00:00.000000','12:00:00.000000',2,'Buổi chiều'),(15,'21:00:00.000000','17:00:00.000000',3,'Buổi tối'),(20,'23:59:59.000000','21:00:00.000000',4,'Khuya'),(5,'15:00:00.000000','13:00:00.000000',5,'Giữa buổi'),(25,'17:00:00.000000','15:00:00.000000',6,'Giờ vàng'),(10,'08:00:00.000000','06:00:00.000000',7,'Sáng sớm'),(30,'23:59:59.000000','23:00:00.000000',8,'Đêm muộn'),(0,'13:00:00.000000','12:00:00.000000',9,'Giờ nghỉ trưa'),(10,'22:00:00.000000','10:00:00.000000',10,'Giờ cuối tuần');
/*!40000 ALTER TABLE `time_frame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trailer`
--

DROP TABLE IF EXISTS `trailer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trailer` (
  `movieid` bigint DEFAULT NULL,
  `trailerid` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`trailerid`),
  UNIQUE KEY `UK88a2282q4pdt9qgmwmmxhhhgr` (`movieid`),
  CONSTRAINT `FKgydmon72mu7di3jk7i2abx9dk` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movieid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trailer`
--

LOCK TABLES `trailer` WRITE;
/*!40000 ALTER TABLE `trailer` DISABLE KEYS */;
INSERT INTO `trailer` VALUES (1,1,'Trailer for Inception - A thief who steals corporate secrets through the use of dream-sharing technology.','https://www.youtube.com/watch?v=YoHD9XEInc0'),(2,2,'Trailer for The Dark Knight - When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.','https://www.youtube.com/watch?v=VZ1bY6xgSCo'),(3,3,'Trailer for Bohemian Rhapsody - A chronicle of the years leading up to Queen\'s legendary appearance at the Live Aid (1985) concert.','https://www.youtube.com/watch?v=fJ9rUzIMcZQ'),(4,4,'Trailer for Interstellar - A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.','https://www.youtube.com/watch?v=5psZ9j1G5K0'),(5,5,'Trailer for The Matrix - A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.','https://www.youtube.com/watch?v=YbJOTdZBX1g'),(6,6,'Trailer for A Star Is Born - A musician helps a young singer find fame as age and alcoholism send his own career into a downward spiral.','https://www.youtube.com/watch?v=2Vv-BfVoq4g'),(7,7,'Trailer for Joker - In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society.','https://www.youtube.com/watch?v=W6uSMXROcGk'),(8,8,'Trailer for Avengers: Endgame - After the devastating events of Avengers: Infinity War (2018), the universe is in ruins.','https://www.youtube.com/watch?v=0KSOMA3QBU0'),(9,9,'Trailer for Spider-Man: Into the Spider-Verse - Teen Miles Morales becomes the Spider-Man of his universe and must join forces with five spider-powered individuals from other dimensions to stop a threat.','https://www.youtube.com/watch?v=4fIxw0zMByA'),(10,10,'Trailer for Parasite - Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.','https://www.youtube.com/watch?v=E-50wD3bU5s');
/*!40000 ALTER TABLE `trailer` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_discount`
--

LOCK TABLES `type_discount` WRITE;
/*!40000 ALTER TABLE `type_discount` DISABLE KEYS */;
INSERT INTO `type_discount` VALUES (1,'Giảm giá sinh nhật'),(2,'Giảm giá học sinh'),(3,'Giảm giá theo nhóm'),(4,'Giảm giá dịp lễ'),(5,'Giảm giá combo'),(6,'Giảm giá thành viên'),(7,'Giảm giá khuyến mãi'),(8,'Giảm giá tuần đầu tiên'),(9,'Giảm giá cho người cao tuổi'),(10,'Giảm giá cuối tuần');
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
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_roomid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_room`
--

LOCK TABLES `type_room` WRITE;
/*!40000 ALTER TABLE `type_room` DISABLE KEYS */;
INSERT INTO `type_room` VALUES (1,'Phòng 2D'),(2,'Phòng 3D'),(3,'Phòng IMAX'),(4,'Phòng VIP'),(5,'Phòng thường'),(6,'Phòng gia đình'),(7,'Phòng chiếu đặc biệt'),(8,'Phòng chiếu phim ngắn'),(9,'Phòng chiếu thể thao'),(10,'Phòng chiếu trực tiếp');
/*!40000 ALTER TABLE `type_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_seat`
--

DROP TABLE IF EXISTS `type_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_seat` (
  `surcharge` float NOT NULL,
  `type_seatid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_seatid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_seat`
--

LOCK TABLES `type_seat` WRITE;
/*!40000 ALTER TABLE `type_seat` DISABLE KEYS */;
INSERT INTO `type_seat` VALUES (0,1,'Ghế Thường'),(50,2,'Ghế VIP'),(100,3,'Ghế Giường'),(20,4,'Ghế 3D'),(30,5,'Ghế Đặc Biệt'),(70,6,'Ghế Gia Đình'),(80,7,'Ghế Sát Màn Hình'),(60,8,'Ghế Cách Âm'),(10,9,'Ghế Trẻ Em'),(0,10,'Ghế Cổ Điển');
/*!40000 ALTER TABLE `type_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_user`
--

DROP TABLE IF EXISTS `type_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_user` (
  `age_end` int NOT NULL,
  `age_start` int NOT NULL,
  `surcharge` float NOT NULL,
  `type_userid` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_userid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_user`
--

LOCK TABLES `type_user` WRITE;
/*!40000 ALTER TABLE `type_user` DISABLE KEYS */;
INSERT INTO `type_user` VALUES (64,18,0,1,'Người lớn'),(12,0,50,2,'Trẻ em'),(120,65,30,3,'Người cao tuổi'),(25,18,20,4,'Sinh viên'),(17,6,15,5,'Học sinh'),(50,18,10,6,'Quân nhân'),(60,18,5,7,'Nhân viên công ty'),(120,0,40,8,'Gia đình'),(120,0,25,9,'Người khuyết tật'),(120,18,10,10,'Người nước ngoài');
/*!40000 ALTER TABLE `type_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `gender` bit(1) NOT NULL,
  `managed_employees` int DEFAULT NULL,
  `points` int DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `levelid` bigint DEFAULT NULL,
  `positionid` bigint DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `theaterid` bigint DEFAULT NULL,
  `userid` bigint NOT NULL AUTO_INCREMENT,
  `user_type` varchar(31) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `assigned_tasks` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_login` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mid_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `privileges` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `FKi1jvgoc9uahfscl97s3ts7bdx` (`positionid`),
  KEY `FKcpe446lwtq0a34gm2g2tordvp` (`theaterid`),
  KEY `FKjaxbsyvx6ld1vrfad4yqdk8jg` (`levelid`),
  CONSTRAINT `FKcpe446lwtq0a34gm2g2tordvp` FOREIGN KEY (`theaterid`) REFERENCES `theater` (`theaterid`),
  CONSTRAINT `FKi1jvgoc9uahfscl97s3ts7bdx` FOREIGN KEY (`positionid`) REFERENCES `position` (`id`),
  CONSTRAINT `FKjaxbsyvx6ld1vrfad4yqdk8jg` FOREIGN KEY (`levelid`) REFERENCES `level` (`levelid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (_binary '',2,100,_binary '','1990-01-01 00:00:00.000000',1,1,'2024-10-01 00:00:00.000000',1,1,'CUSTOMER','Hà Nội','Task A','a.nguyen@example.com','Nguyễn','image_a.jpg','2024-10-20 08:00:00','A','Văn','0123456789','User'),(_binary '\0',3,150,_binary '','1992-02-15 00:00:00.000000',1,1,'2024-10-01 00:00:00.000000',2,2,'ADMIN','Hồ Chí Minh','Task B','b.tran@example.com','Trần','image_b.jpg','2024-10-20 09:00:00','B','Thị','0123456790','Admin'),(_binary '',1,200,_binary '','1988-03-20 00:00:00.000000',3,1,'2024-10-01 00:00:00.000000',3,3,'MANAGER','Đà Nẵng','Task C','c.le@example.com','Lê','image_c.jpg','2024-10-20 10:00:00','C','Mỹ','0123456701','Manager'),(_binary '',2,80,_binary '','1985-04-25 00:00:00.000000',4,1,'2024-10-01 00:00:00.000000',4,4,'CUSTOMER','Cần Thơ','Task D','d.pham@example.com','Phạm','image_d.jpg','2024-10-20 11:00:00','D','Văn','0123456712','User'),(_binary '\0',1,60,_binary '','2000-05-30 00:00:00.000000',5,1,'2024-10-01 00:00:00.000000',1,5,'CUSTOMER','Nha Trang','Task E','e.nguyen@example.com','Nguyễn','image_e.jpg','2024-10-20 12:00:00','E','Thị','0123456723','User'),(_binary '',2,90,_binary '','1995-06-10 00:00:00.000000',3,1,'2024-10-01 00:00:00.000000',3,6,'CUSTOMER','Đà Lạt','Task F','f.tran@example.com','Trần','image_f.jpg','2024-10-20 13:00:00','F','Văn','0123456734','User'),(_binary '\0',3,70,_binary '','1993-07-15 00:00:00.000000',2,1,'2024-10-01 00:00:00.000000',2,7,'CUSTOMER','Vũng Tàu','Task G','g.le@example.com','Lê','image_g.jpg','2024-10-20 14:00:00','G','Thị','0123456745','User'),(_binary '',2,40,_binary '','1994-08-20 00:00:00.000000',1,1,'2024-10-01 00:00:00.000000',4,8,'CUSTOMER','Hải Phòng','Task H','h.pham@example.com','Phạm','image_h.jpg','2024-10-20 15:00:00','H','Văn','0123456756','User'),(_binary '\0',1,20,_binary '','1997-09-25 00:00:00.000000',1,1,'2024-10-01 00:00:00.000000',5,9,'CUSTOMER','Quảng Ninh','Task I','i.nguyen@example.com','Nguyễn','image_i.jpg','2024-10-20 16:00:00','I','Thị','0123456767','User'),(_binary '',3,10,_binary '','1989-10-30 00:00:00.000000',1,1,'2024-10-01 00:00:00.000000',3,10,'CUSTOMER','Nam Định','Task J','j.tran@example.com','Trần','image_j.jpg','2024-10-20 17:00:00','J','Văn','0123456778','User');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_discount`
--

DROP TABLE IF EXISTS `user_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_discount` (
  `discountid` bigint NOT NULL,
  `userid` bigint NOT NULL,
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
INSERT INTO `user_discount` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(1,6),(2,7),(3,8),(4,9),(5,10);
/*!40000 ALTER TABLE `user_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_genre`
--

DROP TABLE IF EXISTS `user_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_genre` (
  `genreid` bigint NOT NULL,
  `userid` bigint NOT NULL,
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
INSERT INTO `user_genre` VALUES (1,1),(2,1),(1,2),(3,2),(2,3),(4,3),(1,4),(5,4),(2,5),(3,6);
/*!40000 ALTER TABLE `user_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notification`
--

DROP TABLE IF EXISTS `user_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_notification` (
  `notificationid` bigint NOT NULL,
  `userid` bigint NOT NULL,
  KEY `FKqp9s82824lgh3iw8cx6qok4b` (`notificationid`),
  KEY `FKpvsg81hsa5pm8ukr9yof8l2ka` (`userid`),
  CONSTRAINT `FKpvsg81hsa5pm8ukr9yof8l2ka` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FKqp9s82824lgh3iw8cx6qok4b` FOREIGN KEY (`notificationid`) REFERENCES `notification` (`notificationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notification`
--

LOCK TABLES `user_notification` WRITE;
/*!40000 ALTER TABLE `user_notification` DISABLE KEYS */;
INSERT INTO `user_notification` VALUES (1,1),(2,1),(1,2),(3,2),(2,3),(4,3),(1,4),(5,4),(2,5),(3,6);
/*!40000 ALTER TABLE `user_notification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-29  3:46:55
