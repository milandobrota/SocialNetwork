-- MySQL dump 10.13  Distrib 5.1.50, for apple-darwin10.3.0 (i386)
--
-- Host: localhost    Database: socialNetwork
-- ------------------------------------------------------
-- Server version	5.1.50

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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `personId` int(11) NOT NULL,
  `postId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `commentsPostId` (`postId`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'text','2011-05-20 19:55:34',1,1),(2,'text','2011-05-20 20:41:16',1,1),(3,'text','2011-05-20 22:35:04',1,1),(4,'text','2011-05-20 22:35:48',1,1),(5,'text','2011-05-20 22:35:54',1,1),(7,'new','2011-05-21 17:46:31',1,1),(8,'a new comment','2011-05-21 17:56:05',1,1),(9,'aaa','2011-05-21 17:59:00',0,1),(10,'aaa','2011-05-21 17:59:07',0,1),(11,'aaa','2011-05-21 17:59:09',0,1),(12,'aaaa','2011-05-21 17:59:40',0,1),(13,'aaa','2011-05-21 18:01:02',1,1),(14,'brand new','2011-05-21 18:12:45',1,1),(15,'new comment','2011-05-21 18:13:15',1,1),(16,'afaf','2011-05-21 20:15:39',1,1),(17,'new comment','2011-05-21 21:28:13',1,10),(18,'aaaaaaa','2011-05-21 21:29:03',1,11),(19,'bbb','2011-05-21 21:29:55',1,10),(20,'aha','2011-05-21 21:30:09',1,3),(21,'ahah','2011-05-21 21:30:11',1,3),(22,'#1','2011-05-21 21:30:17',1,3),(23,'brand new','2011-05-22 03:55:16',1,3),(24,'aa','2011-05-22 05:45:18',1,1),(25,'aaa','2011-05-22 14:27:27',1,4),(26,'Also commenting.','2011-05-22 22:30:02',6,5),(27,'My comment','2011-05-22 22:49:45',1,5),(28,'let\'s see what happens now','2011-05-22 23:33:35',6,15),(29,'boo','2011-05-22 23:37:31',6,15);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendRequests`
--

DROP TABLE IF EXISTS `friendRequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendRequests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `sourceId` int(11) NOT NULL,
  `targetId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendRequests`
--

LOCK TABLES `friendRequests` WRITE;
/*!40000 ALTER TABLE `friendRequests` DISABLE KEYS */;
INSERT INTO `friendRequests` VALUES (1,'UNANSWERED','2011-05-20 18:41:31',1,2),(2,'ACCEPTED','2011-05-20 18:43:08',2,1),(4,'UNANSWERED','2011-05-22 20:37:40',1,7),(5,'ACCEPTED','2011-05-22 22:30:20',1,6),(6,'ACCEPTED','2011-05-22 23:03:45',1,6),(7,'ACCEPTED','2011-05-22 23:03:56',1,6);
/*!40000 ALTER TABLE `friendRequests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `links`
--

DROP TABLE IF EXISTS `links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `links` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) DEFAULT NULL,
  `postId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `links`
--

LOCK TABLES `links` WRITE;
/*!40000 ALTER TABLE `links` DISABLE KEYS */;
INSERT INTO `links` VALUES (1,'link',16),(2,'link',17),(3,'www.nba.com',12),(4,'',13),(5,'',14),(6,'http://www.nba.com',1),(7,'aa',3);
/*!40000 ALTER TABLE `links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `personId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'Sabrina Gatlin created a new post on your wall.','wall','2011-05-22 23:19:35',1),(2,'Milan Dobrota created a new post on your wall.','wall','2011-05-22 23:26:35',6),(3,'Sabrina Gatlin created a new post on your wall.','wall','2011-05-22 23:30:23',1),(4,'Sabrina Gatlin created a new post on your wall.','wall','2011-05-22 23:31:29',1),(5,'Wild Coyote created a new post on your wall.','wall','2011-05-22 23:32:29',1),(6,'Sabrina Gatlin commented on the post you have written.','wall?ownerId=1','2011-05-22 23:33:35',2),(7,'Sabrina Gatlin commented on the post on your wall.','wall1','2011-05-22 23:33:35',1),(8,'Sabrina Gatlin commented on the post you have written.','wall?ownerId=1','2011-05-22 23:37:31',2),(9,'Sabrina Gatlin commented on the post on your wall.','wall','2011-05-22 23:37:31',1),(10,'New User created a new post on your wall.','wall','2011-05-22 23:44:04',8);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `dateOfBirth` datetime DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `place` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `employment` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `education` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_people_on_email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (1,'Milan','Dobrota','1900-01-01 00:00:00',1,'milan@milandobrota.com','pass','Chicago','http://milandobrota.com','web developer','Chicago Tribune','/a2596562356824108659garfield-003-01.jpg','ftn'),(2,'Wild','Coyote','1986-03-30 00:00:00',1,'wild@coyote.com','pass123','Desert',NULL,'inventor','ACME','person.gif',NULL),(6,'Sabrina','Gatlin','1901-02-02 00:00:00',0,'bush34@gmail.com','pass','Chicago',NULL,'none','elementary','person.gif','kid'),(7,'M','L','1900-01-01 00:00:00',1,'elitecoding@gmail.com','pass','Chicago','http://milandobrota.com','employment','education','/a-905435601278619539garfield-003-01.jpg','occupation'),(9,'Garfield','Again','1900-01-01 00:00:00',1,'garfield@again.com','-8939392437705474512','','','','','/a8289908250348806473garfield-003-01.jpg',''),(8,'New','User','1900-01-01 00:00:00',1,'new@user.com','pass','','','','','person.gif','');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pictures`
--

DROP TABLE IF EXISTS `pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pictures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `postId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pictures`
--

LOCK TABLES `pictures` WRITE;
/*!40000 ALTER TABLE `pictures` DISABLE KEYS */;
INSERT INTO `pictures` VALUES (1,'/a-3249588586754785234garfield-003-01.jpg',17),(2,'/a-4510542988061707077garfield-003-01.jpg',18),(3,'/a-2652808899865196305garfield-003-01.jpg',2),(4,'/a1006871337755713785garfield-003-01.jpg',5),(5,'/a-6999495756044458136garfield-003-01.jpg',12),(6,'/a1804416723769847241garfield-003-01.jpg',16),(7,'/a-2567081302168189061garfield-003-01.jpg',1),(8,'/a-3143267380924410547garfield-003-01.jpg',6);
/*!40000 ALTER TABLE `pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `posterId` int(11) NOT NULL,
  `ownerId` int(11) NOT NULL,
  `popularity` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'new title','2011-05-22 04:47:25','new text',1,1,1),(2,'aaaa','2011-05-22 05:45:34','aaa',1,1,0),(3,'aaa','2011-05-22 05:46:29','aaa',1,1,0),(4,'Amerie','2011-05-22 05:56:07','1 thing',1,1,1),(5,'Sabrina\'s post','2011-05-22 22:29:54','blah',6,6,2),(6,'Hey Sabrina','2011-05-22 23:09:13','What\'s up',1,6,0),(7,'Aha','2011-05-22 23:14:04','I can do this',1,6,0),(8,'I can do this too','2011-05-22 23:14:44','yep',1,1,0),(9,'aaa','2011-05-22 23:17:16','bbb',1,1,0),(10,'yeah','2011-05-22 23:18:01','notification should be there',6,1,0),(11,'let\'s try now','2011-05-22 23:19:35','notification',6,1,0),(12,'Thanks for posting','2011-05-22 23:26:35','That\'s cool',1,6,0),(13,'Another post','2011-05-22 23:30:23','To see if the order is correct',6,1,0),(14,'cool','2011-05-22 23:31:29','hip',6,1,0),(15,'Wild E Coyote rules','2011-05-22 23:32:29','no doubt',2,1,2),(16,'aaa','2011-05-22 23:44:04','aaa',8,8,0);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `videos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) DEFAULT NULL,
  `postId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (1,'video',16),(2,'video',17),(3,'http://www.youtube.com/v/BXJaAW8z6jc',12),(4,'',13),(5,'',14),(6,'http://www.youtube.com/v/BXJaAW8z6jc',1),(7,'aa',3),(8,'http://youtube.com/v/bbqVg_23otg',4);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-05-30  0:45:13
