-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.37-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema irbdb_inst
--

CREATE DATABASE IF NOT EXISTS irbdb_inst;
USE irbdb_inst;

--
-- Temporary table structure for view `view_active_personal`
--
DROP TABLE IF EXISTS `view_active_personal`;
DROP VIEW IF EXISTS `view_active_personal`;
CREATE TABLE `view_active_personal` (
  `personalcode` varchar(255),
  `version` int(11),
  `deleted` bit(1),
  `name` varchar(100),
  `surname` varchar(100),
  `dni` varchar(100),
  `birth_date` datetime,
  `birth_city` varchar(100),
  `street` varchar(100),
  `city` varchar(100),
  `zip_code` varchar(100),
  `phone` varchar(100),
  `phone2` varchar(100),
  `ice_phone` varchar(100),
  `ss_number` varchar(100),
  `bank_account` varchar(100),
  `research_project` varchar(100),
  `sponsoring_agency` varchar(100),
  `holding_institution` varchar(100),
  `principal_investigator` varchar(100),
  `end_of_contract_reason` varchar(100),
  `end_of_contract_address` varchar(100),
  `end_of_contract_city` varchar(100),
  `end_of_contract_zip_code` varchar(100),
  `end_of_contract_phone` varchar(100),
  `end_of_contract_email` varchar(100),
  `birth_country` varchar(255),
  `nationality` varchar(255),
  `nationality_2` varchar(255),
  `country` varchar(255),
  `payments` varchar(255),
  `end_of_contract_country` varchar(255),
  `gender` varchar(255),
  `marital_status` varchar(255),
  `bank` varchar(255),
  `working_hours` varchar(255),
  `category` varchar(255),
  `state` varchar(255),
  `photo` varchar(255),
  `isemployee` int(2)
);

--
-- Temporary table structure for view `view_all_personal`
--
DROP TABLE IF EXISTS `view_all_personal`;
DROP VIEW IF EXISTS `view_all_personal`;
CREATE TABLE `view_all_personal` (
  `personalcode` varchar(255),
  `version` int(11),
  `deleted` bit(1),
  `name` varchar(100),
  `surname` varchar(100),
  `dni` varchar(100),
  `birth_date` datetime,
  `birth_city` varchar(100),
  `street` varchar(100),
  `city` varchar(100),
  `zip_code` varchar(100),
  `phone` varchar(100),
  `phone2` varchar(100),
  `ice_phone` varchar(100),
  `ss_number` varchar(100),
  `bank_account` varchar(100),
  `research_project` varchar(100),
  `sponsoring_agency` varchar(100),
  `holding_institution` varchar(100),
  `principal_investigator` varchar(100),
  `end_of_contract_reason` varchar(100),
  `end_of_contract_address` varchar(100),
  `end_of_contract_city` varchar(100),
  `end_of_contract_zip_code` varchar(100),
  `end_of_contract_phone` varchar(100),
  `end_of_contract_email` varchar(100),
  `birth_country` varchar(255),
  `nationality` varchar(255),
  `nationality_2` varchar(255),
  `country` varchar(100),
  `payments` varchar(255),
  `end_of_contract_country` varchar(255),
  `gender` varchar(255),
  `marital_status` varchar(255),
  `bank` varchar(255),
  `working_hours` varchar(255),
  `category` varchar(255),
  `state` varchar(255),
  `photo` varchar(255),
  `surname1` varchar(100),
  `surname2` varchar(100),
  `validationCode` varchar(100)
);

--
-- Temporary table structure for view `view_compensation`
--
DROP TABLE IF EXISTS `view_compensation`;
DROP VIEW IF EXISTS `view_compensation`;
CREATE TABLE `view_compensation` (
  `compensationcode` varchar(255),
  `version` int(11),
  `deleted` bit(1),
  `start_date` datetime,
  `end_date` datetime,
  `description` varchar(255),
  `amount` decimal(19,2),
  `compensation_personal` varchar(255),
  `type_of_compensation` varchar(255)
);

--
-- Temporary table structure for view `view_little_personal`
--
DROP TABLE IF EXISTS `view_little_personal`;
DROP VIEW IF EXISTS `view_little_personal`;
CREATE TABLE `view_little_personal` (
  `personalcode` varchar(255),
  `name` varchar(100),
  `surname` varchar(100),
  `dni` varchar(100),
  `birth_date` datetime,
  `country` varchar(100)
);

--
-- Temporary table structure for view `view_notirb_active_personal`
--
DROP TABLE IF EXISTS `view_notirb_active_personal`;
DROP VIEW IF EXISTS `view_notirb_active_personal`;
CREATE TABLE `view_notirb_active_personal` (
  `personalcode` varchar(255),
  `version` int(11),
  `deleted` bit(1),
  `name` varchar(100),
  `surname` varchar(100),
  `dni` varchar(100),
  `birth_date` datetime,
  `birth_city` varchar(100),
  `street` varchar(100),
  `city` varchar(100),
  `zip_code` varchar(100),
  `phone` varchar(100),
  `phone2` varchar(100),
  `ice_phone` varchar(100),
  `ss_number` varchar(100),
  `bank_account` varchar(100),
  `research_project` varchar(100),
  `sponsoring_agency` varchar(100),
  `holding_institution` varchar(100),
  `principal_investigator` varchar(100),
  `end_of_contract_reason` varchar(100),
  `end_of_contract_address` varchar(100),
  `end_of_contract_city` varchar(100),
  `end_of_contract_zip_code` varchar(100),
  `end_of_contract_phone` varchar(100),
  `end_of_contract_email` varchar(100),
  `birth_country` varchar(255),
  `nationality` varchar(255),
  `nationality_2` varchar(255),
  `country` varchar(255),
  `payments` varchar(255),
  `end_of_contract_country` varchar(255),
  `gender` varchar(255),
  `marital_status` varchar(255),
  `bank` varchar(255),
  `working_hours` varchar(255),
  `category` varchar(255),
  `state` varchar(255),
  `photo` varchar(255),
  `surname1` varchar(100),
  `surname2` varchar(100),
  `validationCode` varchar(100),
  `personal_email` varchar(255),
  `language` varchar(15),
  `username` varchar(45)
);

--
-- Temporary table structure for view `view_organizationunit_unit`
--
DROP TABLE IF EXISTS `view_organizationunit_unit`;
DROP VIEW IF EXISTS `view_organizationunit_unit`;
CREATE TABLE `view_organizationunit_unit` (
  `organization_unitcode` varchar(255),
  `organization_unit_desc` varchar(100),
  `unitcode` varchar(255),
  `unit_desc` varchar(100)
);

--
-- Temporary table structure for view `view_personal_professional`
--
DROP TABLE IF EXISTS `view_personal_professional`;
DROP VIEW IF EXISTS `view_personal_professional`;
CREATE TABLE `view_personal_professional` (
  `personalcode` varchar(255),
  `name` varchar(100),
  `surname1` varchar(100),
  `surname2` varchar(100),
  `dni` varchar(100),
  `birth_date` datetime,
  `birth_city` varchar(100),
  `street` varchar(100),
  `city` varchar(100),
  `zip_code` varchar(100),
  `phone` varchar(100),
  `phone2` varchar(100),
  `ice_phone` varchar(100),
  `ss_number` varchar(100),
  `bank_account` varchar(100),
  `research_project` varchar(100),
  `sponsoring_agency` varchar(100),
  `holding_institution` varchar(100),
  `principal_investigator` varchar(100),
  `end_of_contract_reason` varchar(100),
  `end_of_contract_address` varchar(100),
  `end_of_contract_city` varchar(100),
  `end_of_contract_zip_code` varchar(100),
  `end_of_contract_phone` varchar(100),
  `end_of_contract_email` varchar(100),
  `end_of_contract_country` varchar(100),
  `birth_country` varchar(100),
  `nationality` varchar(100),
  `nationality_2` varchar(100),
  `country` varchar(100),
  `payments` varchar(100),
  `gender` varchar(100),
  `marital_status` varchar(100),
  `bank` varchar(100),
  `working_hours` varchar(100),
  `category` varchar(100),
  `state` varchar(255),
  `personal_email` varchar(255),
  `language` varchar(15),
  `start_date` datetime,
  `end_date` datetime,
  `location` varchar(255),
  `email` varchar(100),
  `professional_phone` varchar(100),
  `mobile_long` varchar(100),
  `mobile_short` varchar(100),
  `lab_phone_number` varchar(100),
  `fax` varchar(100),
  `type_of_contract` varchar(100),
  `contract_is_irbs` bit(1),
  `position` varchar(100),
  `payroll_institution` varchar(100),
  `payroll_institution_2` varchar(100),
  `research_group` varchar(255),
  `professional_unit` varchar(100),
  `organization_unit` varchar(100)
);

--
-- Definition of table `application_preferences`
--

DROP TABLE IF EXISTS `application_preferences`;
CREATE TABLE `application_preferences` (
  `applicationPreferencescode` varchar(255) NOT NULL,
  `obtentionPhaseActive` bit(1) default NULL,
  PRIMARY KEY  (`applicationPreferencescode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `application_preferences`
--

/*!40000 ALTER TABLE `application_preferences` DISABLE KEYS */;
INSERT INTO `application_preferences` VALUES  ('1',0x00);
/*!40000 ALTER TABLE `application_preferences` ENABLE KEYS */;


--
-- Definition of table `area`
--

DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `areacode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`areacode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `area`
--

/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES  ('00001',51,0x00,'Quí­mico, Farmacéutico y Sanitario'),
 ('00002',11,0x00,'Fabricación Alimentos y Bebidas'),
 ('00003',10,0x00,'Automoción, Transporte, Logística y Distribución'),
 ('00004',7,0x00,'Construcción e Instalaciones'),
 ('00005',8,0x00,'Siderurgia, Metalurgia, Fabricación y Comercialización de Maquinaria'),
 ('00006',18,0x00,'Asesoria'),
 ('00007',18,0x00,'Artes Gráficas, Edición y Reproducción'),
 ('00008',8,0x00,'Actividades Recreativas, Culturales, Ocio'),
 ('00009',6,0x00,'Banca y Seguros'),
 ('00010',11,0x01,'AdministraciÃ³n PÃºblica'),
 ('00011',46,0x00,'Educación, Investigación y Desarrollo Científico'),
 ('00012',1,0x00,'Hosteleria y Restauración'),
 ('00013',4,0x00,'Comunicación y Audiovisual'),
 ('00014',13,0x00,'Actividades Asociativas y  Sindicales'),
 ('00015',25,0x00,'Otros'),
 ('00016',8,0x00,'Actividades y Servicios Informáticos'),
 ('00017',32,0x01,'zzzaaaffffs'),
 ('00018',5,0x01,'zzzaaÃ¡'),
 ('00019',1,0x01,'zzzzzz'),
 ('00020',10,0x01,'zzzzzz');
INSERT INTO `area` VALUES  ('00021',2,0x01,'Otros'),
 ('00022',1,0x01,'dfdfsff'),
 ('00023',2,0x01,'rrrrrr'),
 ('00024',1,0x01,'rewr'),
 ('00025',1,0x01,'gdgdgdgdg'),
 ('00026',1,0x00,'Administración Pública'),
 ('00027',2,0x01,'dfdfsff'),
 ('00028',1,0x01,'zzz'),
 ('00029',1,0x01,'yyyyyyyy'),
 ('00030',1,0x01,'dddd'),
 ('00031',1,0x01,'qqqq'),
 ('00032',1,0x01,'aaaaa'),
 ('00033',12,0x01,'dafÂ´r'),
 ('00034',1,0x01,'d\'eee'),
 ('00035',1,0x01,'d\'aaaaa'),
 ('99999',2,0x00,'Fabricación Textil, Calzado y Confección');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;


--
-- Definition of table `auditmessage`
--

DROP TABLE IF EXISTS `auditmessage`;
CREATE TABLE `auditmessage` (
  `auditmessagecode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `timestamp` datetime default NULL,
  `message` text,
  `user` varchar(100) default NULL,
  `type` varchar(255) default NULL,
  PRIMARY KEY  (`auditmessagecode`),
  KEY `FK8A94FCCA0174429` (`user`),
  KEY `FK8A94FCC94A23110` (`type`),
  CONSTRAINT `FK8A94FCC94A23110` FOREIGN KEY (`type`) REFERENCES `auditmessagetype` (`auditmessagetypecode`),
  CONSTRAINT `FK8A94FCCA0174429` FOREIGN KEY (`user`) REFERENCES `usuario` (`usuariocode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auditmessage`
--

/*!40000 ALTER TABLE `auditmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `auditmessage` ENABLE KEYS */;


--
-- Definition of table `auditmessagetype`
--

DROP TABLE IF EXISTS `auditmessagetype`;
CREATE TABLE `auditmessagetype` (
  `auditmessagetypecode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`auditmessagetypecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `auditmessagetype`
--

/*!40000 ALTER TABLE `auditmessagetype` DISABLE KEYS */;
INSERT INTO `auditmessagetype` VALUES  ('0',0,0x00,'mail'),
 ('1',1,0x00,'create'),
 ('2',2,0x00,'modify'),
 ('3',0,0x00,'delete');
/*!40000 ALTER TABLE `auditmessagetype` ENABLE KEYS */;


--
-- Definition of table `aux_date`
--

DROP TABLE IF EXISTS `aux_date`;
CREATE TABLE `aux_date` (
  `mes` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`mes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `aux_date`
--

/*!40000 ALTER TABLE `aux_date` DISABLE KEYS */;
INSERT INTO `aux_date` VALUES  (199001),
 (199002),
 (199003),
 (199004),
 (199005),
 (199006),
 (199007),
 (199008),
 (199009),
 (199010),
 (199011),
 (199012),
 (199101),
 (199102),
 (199103),
 (199104),
 (199105),
 (199106),
 (199107),
 (199108),
 (199109),
 (199110),
 (199111),
 (199112),
 (199201),
 (199202),
 (199203),
 (199204),
 (199205),
 (199206),
 (199207),
 (199208),
 (199209),
 (199210),
 (199211),
 (199212),
 (199301),
 (199302),
 (199303),
 (199304),
 (199305),
 (199306),
 (199307),
 (199308),
 (199309),
 (199310),
 (199311),
 (199312),
 (199401),
 (199402),
 (199403),
 (199404),
 (199405),
 (199406),
 (199407),
 (199408),
 (199409),
 (199410),
 (199411),
 (199412),
 (199501),
 (199502),
 (199503),
 (199504),
 (199505),
 (199506),
 (199507),
 (199508),
 (199509),
 (199510),
 (199511),
 (199512),
 (199601),
 (199602),
 (199603),
 (199604),
 (199605),
 (199606),
 (199607),
 (199608),
 (199609),
 (199610),
 (199611),
 (199612),
 (199701),
 (199702),
 (199703),
 (199704),
 (199705),
 (199706);
INSERT INTO `aux_date` VALUES  (199707),
 (199708),
 (199709),
 (199710),
 (199711),
 (199712),
 (199801),
 (199802),
 (199803),
 (199804),
 (199805),
 (199806),
 (199807),
 (199808),
 (199809),
 (199810),
 (199811),
 (199812),
 (199901),
 (199902),
 (199903),
 (199904),
 (199905),
 (199906),
 (199907),
 (199908),
 (199909),
 (199910),
 (199911),
 (199912),
 (200001),
 (200002),
 (200003),
 (200004),
 (200005),
 (200006),
 (200007),
 (200008),
 (200009),
 (200010),
 (200011),
 (200012),
 (200101),
 (200102),
 (200103),
 (200104),
 (200105),
 (200106),
 (200107),
 (200108),
 (200109),
 (200110),
 (200111),
 (200112),
 (200201),
 (200202),
 (200203),
 (200204),
 (200205),
 (200206),
 (200207),
 (200208),
 (200209),
 (200210),
 (200211),
 (200212),
 (200301),
 (200302),
 (200303),
 (200304),
 (200305),
 (200306),
 (200307),
 (200308),
 (200309),
 (200310),
 (200311),
 (200312),
 (200401),
 (200402),
 (200403),
 (200404),
 (200405),
 (200406),
 (200407),
 (200408),
 (200409),
 (200410),
 (200411),
 (200412);
INSERT INTO `aux_date` VALUES  (200501),
 (200502),
 (200503),
 (200504),
 (200505),
 (200506),
 (200507),
 (200508),
 (200509),
 (200510),
 (200511),
 (200512),
 (200601),
 (200602),
 (200603),
 (200604),
 (200605),
 (200606),
 (200607),
 (200608),
 (200609),
 (200610),
 (200611),
 (200612),
 (200701),
 (200702),
 (200703),
 (200704),
 (200705),
 (200706),
 (200707),
 (200708),
 (200709),
 (200710),
 (200711),
 (200712),
 (200801),
 (200802),
 (200803),
 (200804),
 (200805),
 (200806),
 (200807),
 (200808),
 (200809),
 (200810),
 (200811),
 (200812),
 (200901),
 (200902),
 (200903),
 (200904),
 (200905),
 (200906),
 (200907),
 (200908),
 (200909),
 (200910),
 (200911),
 (200912),
 (201001),
 (201002),
 (201003),
 (201004),
 (201005),
 (201006),
 (201007),
 (201008),
 (201009),
 (201010),
 (201011),
 (201012),
 (201101),
 (201102),
 (201103),
 (201104),
 (201105),
 (201106),
 (201107),
 (201108),
 (201109),
 (201110),
 (201111),
 (201112),
 (201201),
 (201202),
 (201203),
 (201204),
 (201205),
 (201206);
INSERT INTO `aux_date` VALUES  (201207),
 (201208),
 (201209),
 (201210),
 (201211),
 (201212),
 (201301),
 (201302),
 (201303),
 (201304),
 (201305),
 (201306),
 (201307),
 (201308),
 (201309),
 (201310),
 (201311),
 (201312),
 (201401),
 (201402),
 (201403),
 (201404),
 (201405),
 (201406),
 (201407),
 (201408),
 (201409),
 (201410),
 (201411),
 (201412),
 (201501),
 (201502),
 (201503),
 (201504),
 (201505),
 (201506),
 (201507),
 (201508),
 (201509),
 (201510),
 (201511),
 (201512),
 (201601),
 (201602),
 (201603),
 (201604),
 (201605),
 (201606),
 (201607),
 (201608),
 (201609),
 (201610),
 (201611),
 (201612),
 (201701),
 (201702),
 (201703),
 (201704),
 (201705),
 (201706),
 (201707),
 (201708),
 (201709),
 (201710),
 (201711),
 (201712),
 (201801),
 (201802),
 (201803),
 (201804),
 (201805),
 (201806),
 (201807),
 (201808),
 (201809),
 (201810),
 (201811),
 (201812),
 (201901),
 (201902),
 (201903),
 (201904),
 (201905),
 (201906),
 (201907),
 (201908),
 (201909),
 (201910),
 (201911),
 (201912);
INSERT INTO `aux_date` VALUES  (202001),
 (202002),
 (202003),
 (202004),
 (202005),
 (202006),
 (202007),
 (202008),
 (202009),
 (202010),
 (202011),
 (202012),
 (202101),
 (202102),
 (202103),
 (202104),
 (202105),
 (202106),
 (202107),
 (202108),
 (202109),
 (202110),
 (202111),
 (202112),
 (202201),
 (202202),
 (202203),
 (202204),
 (202205),
 (202206),
 (202207),
 (202208),
 (202209),
 (202210),
 (202211),
 (202212),
 (202301),
 (202302),
 (202303),
 (202304),
 (202305),
 (202306),
 (202307),
 (202308),
 (202309),
 (202310),
 (202311),
 (202312),
 (202401),
 (202402),
 (202403),
 (202404),
 (202405),
 (202406),
 (202407),
 (202408),
 (202409),
 (202410),
 (202411),
 (202412),
 (202501),
 (202502),
 (202503),
 (202504),
 (202505),
 (202506),
 (202507),
 (202508),
 (202509),
 (202510),
 (202511),
 (202512),
 (202601),
 (202602),
 (202603),
 (202604),
 (202605),
 (202606),
 (202607),
 (202608),
 (202609),
 (202610),
 (202611),
 (202612),
 (202701),
 (202702),
 (202703),
 (202704),
 (202705),
 (202706);
INSERT INTO `aux_date` VALUES  (202707),
 (202708),
 (202709),
 (202710),
 (202711),
 (202712),
 (202801),
 (202802),
 (202803),
 (202804),
 (202805),
 (202806),
 (202807),
 (202808),
 (202809),
 (202810),
 (202811),
 (202812),
 (202901),
 (202902),
 (202903),
 (202904),
 (202905),
 (202906),
 (202907),
 (202908),
 (202909),
 (202910),
 (202911),
 (202912),
 (203001),
 (203002),
 (203003),
 (203004),
 (203005),
 (203006),
 (203007),
 (203008),
 (203009),
 (203010),
 (203011),
 (203012),
 (203101),
 (203102),
 (203103),
 (203104),
 (203105),
 (203106),
 (203107),
 (203108),
 (203109),
 (203110),
 (203111),
 (203112),
 (203201),
 (203202),
 (203203),
 (203204),
 (203205),
 (203206),
 (203207),
 (203208),
 (203209),
 (203210),
 (203211),
 (203212),
 (203301),
 (203302),
 (203303),
 (203304),
 (203305),
 (203306),
 (203307),
 (203308),
 (203309),
 (203310),
 (203311),
 (203312),
 (203401),
 (203402),
 (203403),
 (203404),
 (203405),
 (203406),
 (203407),
 (203408),
 (203409),
 (203410),
 (203411),
 (203412);
INSERT INTO `aux_date` VALUES  (203501),
 (203502),
 (203503),
 (203504),
 (203505),
 (203506),
 (203507),
 (203508),
 (203509),
 (203510),
 (203511),
 (203512),
 (203601),
 (203602),
 (203603),
 (203604),
 (203605),
 (203606),
 (203607),
 (203608),
 (203609),
 (203610),
 (203611),
 (203612),
 (203701),
 (203702),
 (203703),
 (203704),
 (203705),
 (203706),
 (203707),
 (203708),
 (203709),
 (203710),
 (203711),
 (203712),
 (203801),
 (203802),
 (203803),
 (203804),
 (203805),
 (203806),
 (203807),
 (203808),
 (203809),
 (203810),
 (203811),
 (203812),
 (203901),
 (203902),
 (203903),
 (203904),
 (203905),
 (203906),
 (203907),
 (203908),
 (203909),
 (203910),
 (203911),
 (203912),
 (204001),
 (204002),
 (204003),
 (204004),
 (204005),
 (204006),
 (204007),
 (204008),
 (204009),
 (204010),
 (204011),
 (204012),
 (204101),
 (204102),
 (204103),
 (204104),
 (204105),
 (204106),
 (204107),
 (204108),
 (204109),
 (204110),
 (204111),
 (204112),
 (204201),
 (204202),
 (204203),
 (204204),
 (204205),
 (204206);
INSERT INTO `aux_date` VALUES  (204207),
 (204208),
 (204209),
 (204210),
 (204211),
 (204212),
 (204301),
 (204302),
 (204303),
 (204304),
 (204305),
 (204306),
 (204307),
 (204308),
 (204309),
 (204310),
 (204311),
 (204312),
 (204401),
 (204402),
 (204403),
 (204404),
 (204405),
 (204406),
 (204407),
 (204408),
 (204409),
 (204410),
 (204411),
 (204412),
 (204501),
 (204502),
 (204503),
 (204504),
 (204505),
 (204506),
 (204507),
 (204508),
 (204509),
 (204510),
 (204511),
 (204512),
 (204601),
 (204602),
 (204603),
 (204604),
 (204605),
 (204606),
 (204607),
 (204608),
 (204609),
 (204610),
 (204611),
 (204612),
 (204701),
 (204702),
 (204703),
 (204704),
 (204705),
 (204706),
 (204707),
 (204708),
 (204709),
 (204710),
 (204711),
 (204712),
 (204801),
 (204802),
 (204803),
 (204804),
 (204805),
 (204806),
 (204807),
 (204808),
 (204809),
 (204810),
 (204811),
 (204812),
 (204901),
 (204902),
 (204903),
 (204904),
 (204905),
 (204906),
 (204907),
 (204908),
 (204909),
 (204910),
 (204911),
 (204912);
INSERT INTO `aux_date` VALUES  (205001),
 (205002),
 (205003),
 (205004),
 (205005),
 (205006),
 (205007),
 (205008),
 (205009),
 (205010),
 (205011),
 (205012),
 (205101),
 (205102),
 (205103),
 (205104),
 (205105),
 (205106),
 (205107),
 (205108),
 (205109),
 (205110),
 (205111),
 (205112),
 (205201),
 (205202),
 (205203),
 (205204),
 (205205),
 (205206),
 (205207),
 (205208),
 (205209),
 (205210),
 (205211),
 (205212),
 (205301),
 (205302),
 (205303),
 (205304),
 (205305),
 (205306),
 (205307),
 (205308),
 (205309),
 (205310),
 (205311),
 (205312),
 (205401),
 (205402),
 (205403),
 (205404),
 (205405),
 (205406),
 (205407),
 (205408),
 (205409),
 (205410),
 (205411),
 (205412),
 (205501),
 (205502),
 (205503),
 (205504),
 (205505),
 (205506),
 (205507),
 (205508),
 (205509),
 (205510),
 (205511),
 (205512),
 (205601),
 (205602),
 (205603),
 (205604),
 (205605),
 (205606),
 (205607),
 (205608),
 (205609),
 (205610),
 (205611),
 (205612),
 (205701),
 (205702),
 (205703),
 (205704),
 (205705),
 (205706);
INSERT INTO `aux_date` VALUES  (205707),
 (205708),
 (205709),
 (205710),
 (205711),
 (205712),
 (205801),
 (205802),
 (205803),
 (205804),
 (205805),
 (205806),
 (205807),
 (205808),
 (205809),
 (205810),
 (205811),
 (205812),
 (205901),
 (205902),
 (205903),
 (205904),
 (205905),
 (205906),
 (205907),
 (205908),
 (205909),
 (205910),
 (205911),
 (205912),
 (206001),
 (206002),
 (206003),
 (206004),
 (206005),
 (206006),
 (206007),
 (206008),
 (206009),
 (206010),
 (206011),
 (206012),
 (206101),
 (206102),
 (206103),
 (206104),
 (206105),
 (206106),
 (206107),
 (206108),
 (206109),
 (206110),
 (206111),
 (206112),
 (206201),
 (206202),
 (206203),
 (206204),
 (206205),
 (206206),
 (206207),
 (206208),
 (206209),
 (206210),
 (206211),
 (206212),
 (206301),
 (206302),
 (206303),
 (206304),
 (206305),
 (206306),
 (206307),
 (206308),
 (206309),
 (206310),
 (206311),
 (206312),
 (206401),
 (206402),
 (206403),
 (206404),
 (206405),
 (206406),
 (206407),
 (206408),
 (206409),
 (206410),
 (206411),
 (206412);
INSERT INTO `aux_date` VALUES  (206501),
 (206502),
 (206503),
 (206504),
 (206505),
 (206506),
 (206507),
 (206508),
 (206509),
 (206510),
 (206511),
 (206512),
 (206601),
 (206602),
 (206603),
 (206604),
 (206605),
 (206606),
 (206607),
 (206608),
 (206609),
 (206610),
 (206611),
 (206612),
 (206701),
 (206702),
 (206703),
 (206704),
 (206705),
 (206706),
 (206707),
 (206708),
 (206709),
 (206710),
 (206711),
 (206712),
 (206801),
 (206802),
 (206803),
 (206804),
 (206805),
 (206806),
 (206807),
 (206808),
 (206809),
 (206810),
 (206811),
 (206812),
 (206901),
 (206902),
 (206903),
 (206904),
 (206905),
 (206906),
 (206907),
 (206908),
 (206909),
 (206910),
 (206911),
 (206912),
 (207001),
 (207002),
 (207003),
 (207004),
 (207005),
 (207006),
 (207007),
 (207008),
 (207009),
 (207010),
 (207011),
 (207012),
 (207101),
 (207102),
 (207103),
 (207104),
 (207105),
 (207106),
 (207107),
 (207108),
 (207109),
 (207110),
 (207111),
 (207112),
 (207201),
 (207202),
 (207203),
 (207204),
 (207205),
 (207206);
INSERT INTO `aux_date` VALUES  (207207),
 (207208),
 (207209),
 (207210),
 (207211),
 (207212),
 (207301),
 (207302),
 (207303),
 (207304),
 (207305),
 (207306),
 (207307),
 (207308),
 (207309),
 (207310),
 (207311),
 (207312),
 (207401),
 (207402),
 (207403),
 (207404),
 (207405),
 (207406),
 (207407),
 (207408),
 (207409),
 (207410),
 (207411),
 (207412),
 (207501),
 (207502),
 (207503),
 (207504),
 (207505),
 (207506),
 (207507),
 (207508),
 (207509),
 (207510),
 (207511),
 (207512),
 (207601),
 (207602),
 (207603),
 (207604),
 (207605),
 (207606),
 (207607),
 (207608),
 (207609),
 (207610),
 (207611),
 (207612),
 (207701),
 (207702),
 (207703),
 (207704),
 (207705),
 (207706),
 (207707),
 (207708),
 (207709),
 (207710),
 (207711),
 (207712),
 (207801),
 (207802),
 (207803),
 (207804),
 (207805),
 (207806),
 (207807),
 (207808),
 (207809),
 (207810),
 (207811),
 (207812),
 (207901),
 (207902),
 (207903),
 (207904),
 (207905),
 (207906),
 (207907),
 (207908),
 (207909),
 (207910),
 (207911),
 (207912);
INSERT INTO `aux_date` VALUES  (208001),
 (208002),
 (208003),
 (208004),
 (208005),
 (208006),
 (208007),
 (208008),
 (208009),
 (208010),
 (208011),
 (208012),
 (208101),
 (208102),
 (208103),
 (208104),
 (208105),
 (208106),
 (208107),
 (208108),
 (208109),
 (208110),
 (208111),
 (208112),
 (208201),
 (208202),
 (208203),
 (208204),
 (208205),
 (208206),
 (208207),
 (208208),
 (208209),
 (208210),
 (208211),
 (208212),
 (208301),
 (208302),
 (208303),
 (208304),
 (208305),
 (208306),
 (208307),
 (208308),
 (208309),
 (208310),
 (208311),
 (208312),
 (208401),
 (208402),
 (208403),
 (208404),
 (208405),
 (208406),
 (208407),
 (208408),
 (208409),
 (208410),
 (208411),
 (208412),
 (208501),
 (208502),
 (208503),
 (208504),
 (208505),
 (208506),
 (208507),
 (208508),
 (208509),
 (208510),
 (208511),
 (208512),
 (208601),
 (208602),
 (208603),
 (208604),
 (208605),
 (208606),
 (208607),
 (208608),
 (208609),
 (208610),
 (208611),
 (208612),
 (208701),
 (208702),
 (208703),
 (208704),
 (208705),
 (208706);
INSERT INTO `aux_date` VALUES  (208707),
 (208708),
 (208709),
 (208710),
 (208711),
 (208712),
 (208801),
 (208802),
 (208803),
 (208804),
 (208805),
 (208806),
 (208807),
 (208808),
 (208809),
 (208810),
 (208811),
 (208812),
 (208901),
 (208902),
 (208903),
 (208904),
 (208905),
 (208906),
 (208907),
 (208908),
 (208909),
 (208910),
 (208911),
 (208912),
 (209001),
 (209002),
 (209003),
 (209004),
 (209005),
 (209006),
 (209007),
 (209008),
 (209009),
 (209010),
 (209011),
 (209012),
 (209101),
 (209102),
 (209103),
 (209104),
 (209105),
 (209106),
 (209107),
 (209108),
 (209109),
 (209110),
 (209111),
 (209112),
 (209201),
 (209202),
 (209203),
 (209204),
 (209205),
 (209206),
 (209207),
 (209208),
 (209209),
 (209210),
 (209211),
 (209212),
 (209301),
 (209302),
 (209303),
 (209304),
 (209305),
 (209306),
 (209307),
 (209308),
 (209309),
 (209310),
 (209311),
 (209312),
 (209401),
 (209402),
 (209403),
 (209404),
 (209405),
 (209406),
 (209407),
 (209408),
 (209409),
 (209410),
 (209411),
 (209412);
INSERT INTO `aux_date` VALUES  (209501),
 (209502),
 (209503),
 (209504),
 (209505),
 (209506),
 (209507),
 (209508),
 (209509),
 (209510),
 (209511),
 (209512),
 (209601),
 (209602),
 (209603),
 (209604),
 (209605),
 (209606),
 (209607),
 (209608),
 (209609),
 (209610),
 (209611),
 (209612),
 (209701),
 (209702),
 (209703),
 (209704),
 (209705),
 (209706),
 (209707),
 (209708),
 (209709),
 (209710),
 (209711),
 (209712),
 (209801),
 (209802),
 (209803),
 (209804),
 (209805),
 (209806),
 (209807),
 (209808),
 (209809),
 (209810),
 (209811),
 (209812),
 (209901),
 (209902),
 (209903),
 (209904),
 (209905),
 (209906),
 (209907),
 (209908),
 (209909),
 (209910),
 (209911),
 (209912),
 (210001),
 (210002),
 (210003),
 (210004),
 (210005),
 (210006),
 (210007),
 (210008),
 (210009),
 (210010),
 (210011),
 (210012);
/*!40000 ALTER TABLE `aux_date` ENABLE KEYS */;


--
-- Definition of table `bank`
--

DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `bankcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`bankcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bank`
--

/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES  ('',70,0x01,''),
 ('ES0019',0,0x00,'Deutsche Bank, S.A.E.'),
 ('ES0030',0,0x00,'Banco Español de Crédito, S.A.'),
 ('ES0049',0,0x00,'Banco Santander Central Hispano, S.A.'),
 ('ES0058',0,0x00,'BNP Paribas España, S.A.'),
 ('ES0065',0,0x00,'Barclays Bank, S.A.'),
 ('ES0072',0,0x00,'Banco Pastor, S.A.'),
 ('ES0073',0,0x00,'Open Bank Santander Consumer, S.A.'),
 ('ES0075',25,0x00,'Banco Popular Español, S.A.'),
 ('ES0081',10,0x00,'Banco de Sabadell, S.A.'),
 ('ES0122',0,0x00,'Citibank España, S.A.'),
 ('ES0128',0,0x00,'Bankinter, S.A.'),
 ('ES0149',0,0x00,'BNP Paribas, S.E.'),
 ('ES0152',0,0x00,'Barclays Bank plc, S.E.'),
 ('ES0162',0,0x00,'HSBC Bank plc, S.E.'),
 ('ES0182',0,0x00,'Banco Bilbao Vizcaya Argentaria, S.A.'),
 ('ES0216',0,0x00,'Banco Popular Hipotecario, S.A.'),
 ('ES0227',0,0x00,'Unoe Bank, S.A.'),
 ('ES0229',0,0x00,'Bancopopular-E, S.A.'),
 ('ES1465',0,0x00,'ING Direct, N.V. S.E'),
 ('ES2013',0,0x00,'Caixa d\' Estalvis de Catalunya'),
 ('ES2030',0,0x00,'Caixa D\'Estalvis de Girona'),
 ('ES2038',2,0x00,'Caja de Ahorros y M.P. de Madrid');
INSERT INTO `bank` VALUES  ('ES2041',0,0x00,'Caixa D\'Estalvis de Manresa'),
 ('ES2042',2,0x00,'Caixa D\'Estalvis Laietana'),
 ('ES2048',0,0x00,'Caja de Ahorros de Asturias'),
 ('ES2051',0,0x00,'Caja de Ahorros y M.P. de las Baleares'),
 ('ES2054',1,0x00,'Caja de Ahorros y M.P. de Navarra'),
 ('ES2059',0,0x00,'Caixa D\'Estalvis de Sabadell'),
 ('ES2073',0,0x00,'Caixa D\'Estalvis de Tarragona'),
 ('ES2074',5,0x00,'Caixa D\'Estalvis de Terrassa'),
 ('ES2077',0,0x00,'Caja Ah.Valencia, Castellón y Alicante,Bancaja'),
 ('ES2081',0,0x00,'Caixa D\'Estalvis del Penedés'),
 ('ES2085',0,0x00,'Caja de Ahorros y M.P. de Zaragoza, Aragón y Rioja'),
 ('ES2086',0,0x00,'Caja de Ahorros de la Inmaculada de Aragón'),
 ('ES2090',0,0x00,'Caja de Ahorros del Mediterráneo'),
 ('ES2095',0,0x00,'Bilbao Bizkaia Kutxa,Aurrezki Kutxa eta Bahitetxea'),
 ('ES2097',0,0x00,'Caja de Ahorros de Vitoria y Álava'),
 ('ES2100',10,0x00,'Caja de Ahorros y Pensiones de Barcelona'),
 ('ES2101',0,0x00,'Caja de Ahorros y M.P. de Gipuzkoa y San Sebastián'),
 ('ES3008',0,0x00,'Caja R. de Navarra, S.C.C.');
INSERT INTO `bank` VALUES  ('ES3009',0,0x00,'Caja R. de Extremadura, S.C.C.'),
 ('ES3025',4,0x00,'Caixa de C. dels Enginyers-C.C. Ingenieros S.C.C.'),
 ('ES3058',0,0x00,'Cajamar Caja Rural, S.C.C.'),
 ('ES3082',0,0x00,'Caja R. del Mediterráneo, Ruralcaja, S.C.C.'),
 ('ES3083',0,0x00,'Caja R. del Duero, S.C.C.L.'),
 ('ES3094',0,0x00,'Caja Campo, Caja Rural, S.C.C.'),
 ('ES3159',0,0x00,'Caixa Popular-Caixa Rural, S.C.C.V.'),
 ('ES3187',0,0x00,'Caja R. del Sur, S.C.C.'),
 ('ES3188',0,0x00,'Credit Valencia, Caja Rural C.C.V.'),
 ('ES3189',3,0x00,'Caja Rural Aragonesa y de los Pirineos S.C.C.');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;


--
-- Definition of table `beancodes`
--

DROP TABLE IF EXISTS `beancodes`;
CREATE TABLE `beancodes` (
  `bean` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `depvalues` varchar(100) NOT NULL,
  `version` int(11) NOT NULL,
  `depnames` varchar(150) default NULL,
  `pattern` varchar(50) default NULL,
  `codenumber` varchar(30) default NULL,
  PRIMARY KEY  (`bean`,`name`,`depvalues`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `beancodes`
--

/*!40000 ALTER TABLE `beancodes` DISABLE KEYS */;
INSERT INTO `beancodes` VALUES  ('area','code','null',31,'null','{n}','00036'),
 ('auditmessage','code','null',5127,'null','{n}','0000000004417'),
 ('bank','code','null',0,'null','{n}','0000000000001'),
 ('benefits','code','null',9,'null','{n}','0000000000010'),
 ('calendar','code','null',79,'null','{n}','0000000000080'),
 ('category','code','null',9,'null','{n}','00023'),
 ('child','code','null',19,'null','{n}','0000000000020'),
 ('column','code','null',216,'null','{n}','0000000000213'),
 ('compensation','code','null',27,'null','{n}','0000000000014'),
 ('country','code','null',0,'null','{n}','0000000000001'),
 ('customlist','code','null',45,'null','{n}','0000000000030'),
 ('education','code','null',143,'null','{n}','0000000000122'),
 ('filter','code','null',73,'null','{n}','0000000000064'),
 ('funding_detail','code','null',15,'null','{n}','0000000000012'),
 ('gender','code','null',0,'null','{n}','00003'),
 ('grant','code','null',3,'null','{n}','0000000000004'),
 ('grant_concession','code','null',41,'null','{n}','0000000000034'),
 ('holiday','code','null',1,'null','{n}','0000000000002');
INSERT INTO `beancodes` VALUES  ('irbholiday','code','null',524,'null','{n}','0000000000525'),
 ('irbholidayinfo','code','null',745,'null','{n}','0000000000746'),
 ('location','code','null',0,'null','{n}','0000000000001'),
 ('marital_status','code','null',1,'null','{n}','00008'),
 ('nationality','code','null',0,'null','{n}','0000000000001'),
 ('orderby','code','null',106,'null','{n}','0000000000096'),
 ('organization_unit','code','null',0,'null','{n}','00005'),
 ('payment','code','null',0,'null','{n}','00003'),
 ('payroll_institution','code','null',6,'null','{n}','00011'),
 ('personal','code','null',0,'null','{n}','00001'),
 ('personalphoto','code','null',75,'null','{n}','0000000000055'),
 ('position','code','null',5,'null','{n}','00019'),
 ('professional','code','null',134,'null','{n}','0000000000105'),
 ('report','code','null',0,'null','{n}','0000000000001'),
 ('reportparameter','code','null',0,'null','{n}','0000000000001'),
 ('research_group','code','null',4,'null','{n}','00029'),
 ('table_grant','code','null',0,'null','{n}','00010'),
 ('type_of_benefit','code','null',0,'null','{n}','00002');
INSERT INTO `beancodes` VALUES  ('type_of_compensation','code','null',0,'null','{n}','00003'),
 ('type_of_contract','code','null',1,'null','{n}','00010'),
 ('type_of_education','code','null',3,'null','{n}','00013'),
 ('type_of_grant','code','null',0,'null','{n}','00003'),
 ('type_of_holidays','code','null',5,'null','{n}','00011'),
 ('type_of_institution','code','null',4,'null','{n}','00008'),
 ('unit','code','null',1,'null','{n}','00019'),
 ('usuario','code','null',254,'null','{n}','00146'),
 ('working_hours','code','null',2,'null','{n}','00007'),
 ('work_experience','code','null',109,'null','{n}','0000000000101');
/*!40000 ALTER TABLE `beancodes` ENABLE KEYS */;


--
-- Definition of table `benefits`
--

DROP TABLE IF EXISTS `benefits`;
CREATE TABLE `benefits` (
  `benefitscode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `description` text,
  `benefits_personal` varchar(255) default NULL,
  `type_of_benefit` varchar(255) default NULL,
  PRIMARY KEY  (`benefitscode`),
  KEY `FK9322CEBC88E6B373` (`benefits_personal`),
  KEY `FK9322CEBCAF4635D8` (`type_of_benefit`),
  CONSTRAINT `FK9322CEBC88E6B373` FOREIGN KEY (`benefits_personal`) REFERENCES `personal` (`personalcode`),
  CONSTRAINT `FK9322CEBCAF4635D8` FOREIGN KEY (`type_of_benefit`) REFERENCES `type_of_benefit` (`type_of_benefitcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `benefits`
--

/*!40000 ALTER TABLE `benefits` DISABLE KEYS */;
INSERT INTO `benefits` VALUES  ('0000000000001',1,0x00,'2007-01-29 00:00:00',NULL,'','00001','00001'),
 ('0000000000002',3,0x00,'2007-02-19 00:00:00',NULL,'','00002','00001'),
 ('0000000000003',1,0x00,'2007-03-06 00:00:00',NULL,'','00028','00001'),
 ('0000000000004',2,0x00,'2006-04-01 00:00:00',NULL,'Ticket restaurant','00029','00001'),
 ('0000000000005',1,0x00,'2006-04-16 00:00:00',NULL,'Ticket restaurant','00031','00001'),
 ('0000000000008',2,0x01,'2008-04-22 00:00:00','2008-04-23 00:00:00','','00118','00001'),
 ('0000000000009',2,0x01,NULL,NULL,'','00118',NULL);
/*!40000 ALTER TABLE `benefits` ENABLE KEYS */;


--
-- Definition of table `budget`
--

DROP TABLE IF EXISTS `budget`;
CREATE TABLE `budget` (
  `irb_budgetcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`irb_budgetcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `budget`
--

/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
INSERT INTO `budget` VALUES  ('0000',1,0x00,'0000'),
 ('Botín',1,0x00,'Botín'),
 ('Informática',0,0x00,'Informática'),
 ('Recursos Humanos',0,0x00,'Recursos Humanos');
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;


--
-- Definition of table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
CREATE TABLE `calendar` (
  `calendarcode` varchar(45) NOT NULL,
  `day` date NOT NULL,
  `type` char(1) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY  USING BTREE (`calendarcode`),
  UNIQUE KEY `Index_2` USING BTREE (`day`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calendar`
--

/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES  ('0000000000022','2009-06-05','0',''),
 ('0000000000024','2009-12-09','0',''),
 ('0000000000025','2010-06-18','0',''),
 ('0000000000029','2009-08-10','0',''),
 ('0000000000042','2009-05-16','0',''),
 ('0000000000043','2008-05-25','0',''),
 ('0000000000044','2008-05-17','0',''),
 ('0000000000050','2008-07-12','0',''),
 ('0000000000052','2008-06-19','0','fsfsfgf'),
 ('0000000000053','2008-06-27','0','aha'),
 ('0000000000054','2008-08-27','0','aha'),
 ('0000000000055','2008-11-22','0','daddadfa'),
 ('0000000000056','2008-10-15','0','sdasd'),
 ('0000000000057','2008-12-27','0','hola'),
 ('0000000000060','2008-12-31','0',''),
 ('0000000000061','2008-03-12','0','mb'),
 ('0000000000062','2009-01-01','0','dghkaj'),
 ('0000000000063','2009-12-31','0','xd'),
 ('0000000000064','2008-04-19','0','hola'),
 ('0000000000066','2008-02-22','0','ev'),
 ('0000000000067','2008-01-10','0','jdad'),
 ('0000000000068','2008-02-08','0','ujyytjuyjytu'),
 ('0000000000070','2008-09-18','0','gfdgg'),
 ('0000000000071','2008-06-04','0','aha');
INSERT INTO `calendar` VALUES  ('0000000000072','2008-02-23','1','gdfgsdfg'),
 ('0000000000073','2008-10-13','2','fddf'),
 ('0000000000077','2008-01-24','0','vxcvfd');
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;


--
-- Definition of table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `categorycode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`categorycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES  ('00001',2,0x01,'Investigador senior'),
 ('00002',3,0x01,'Investigador junior'),
 ('00003',2,0x01,'Personal de dirección'),
 ('00004',2,0x01,'Personal de administración y recursos humanos'),
 ('00005',5,0x01,'Doctor'),
 ('00006',2,0x01,'Doctorando'),
 ('00007',2,0x01,'Ingeniero senior'),
 ('00008',2,0x01,'Ingeniero junior'),
 ('00009',2,0x01,'Ingeniero en prácticas'),
 ('00010',46,0x00,'Analista Informático'),
 ('00011',2,0x00,'Analista Programador'),
 ('00012',2,0x01,'Programador de aplicaciones web'),
 ('00013',1,0x00,'Becario'),
 ('00014',0,0x00,'Ayudante de Investigador'),
 ('00015',60,0x00,'Titulado Grado Superior'),
 ('00016',0,0x00,'Programador'),
 ('00017',1,0x00,'Titulado Grado Medio'),
 ('00018',0,0x00,'Oficial 1ª'),
 ('00019',10,0x00,'Oficial 2ª'),
 ('00020',0,0x00,'Auxiliar Administrativo'),
 ('00021',0,0x00,'Técnico Laboratorio'),
 ('00022',1,0x01,'');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


--
-- Definition of table `child`
--

DROP TABLE IF EXISTS `child`;
CREATE TABLE `child` (
  `childcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `birth_date` datetime default NULL,
  `observations` varchar(100) default NULL,
  `child_personal` varchar(255) default NULL,
  PRIMARY KEY  (`childcode`),
  KEY `FK3D1FCFC2EE2C913` (`child_personal`),
  CONSTRAINT `FK3D1FCFC2EE2C913` FOREIGN KEY (`child_personal`) REFERENCES `personal` (`personalcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `child`
--

/*!40000 ALTER TABLE `child` DISABLE KEYS */;
INSERT INTO `child` VALUES  ('0000000000001',1,0x00,'1993-01-13 00:00:00','','00002'),
 ('0000000000002',1,0x00,'2006-01-01 00:00:00','','00029'),
 ('0000000000003',2,0x00,'2004-01-13 00:00:00','Discapacitat','00031'),
 ('0000000000004',2,0x01,NULL,'Discapacitat','00031'),
 ('0000000000005',1,0x00,'2008-03-02 00:00:00','','00001'),
 ('0000000000006',1,0x00,'2008-03-12 00:00:00','','00001'),
 ('0000000000007',2,0x01,'1997-04-22 00:00:00','dfds','00111'),
 ('0000000000008',2,0x01,'2008-04-15 00:00:00','','00111'),
 ('0000000000009',2,0x01,'2000-04-19 00:00:00','','00111'),
 ('0000000000010',2,0x01,'2002-10-09 00:00:00','nmjk','00111'),
 ('0000000000018',1,0x00,'2008-04-07 00:00:00','cbb','00001');
/*!40000 ALTER TABLE `child` ENABLE KEYS */;


--
-- Definition of table `compensation`
--

DROP TABLE IF EXISTS `compensation`;
CREATE TABLE `compensation` (
  `compensationcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `description` varchar(255) default NULL,
  `amount` decimal(19,2) default NULL,
  `compensation_personal` varchar(255) default NULL,
  `type_of_compensation` varchar(255) default NULL,
  `current` bit(1) NOT NULL default '\0',
  PRIMARY KEY  (`compensationcode`),
  KEY `FK74BFCC5A9D66564A` (`type_of_compensation`),
  KEY `FK74BFCC5ADFCC1F95` (`compensation_personal`),
  CONSTRAINT `FK74BFCC5A9D66564A` FOREIGN KEY (`type_of_compensation`) REFERENCES `type_of_compensation` (`type_of_compensationcode`),
  CONSTRAINT `FK74BFCC5ADFCC1F95` FOREIGN KEY (`compensation_personal`) REFERENCES `personal` (`personalcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compensation`
--

/*!40000 ALTER TABLE `compensation` DISABLE KEYS */;
INSERT INTO `compensation` VALUES  ('0000000000001',7,0x00,'2007-01-29 00:00:00','2008-03-01 00:00:00','','45000.00','00001','00001',0x01),
 ('0000000000002',2,0x00,'2007-02-19 00:00:00',NULL,'','26000.00','00002','00001',0x01),
 ('0000000000003',3,0x00,'2007-03-06 00:00:00','2007-05-31 00:00:00','Salario base','38000.00','00028','00001',0x00),
 ('0000000000004',2,0x00,'2006-04-01 00:00:00','2006-12-01 00:00:00','Basic Salary','47500.00','00029','00001',0x00),
 ('0000000000005',1,0x00,'2007-01-01 00:00:00',NULL,'Aumento anual pactado','2500.00','00029','00001',0x01),
 ('0000000000006',1,0x00,'2007-04-16 00:00:00',NULL,'Basic Salary','50000.00','00031','00001',0x01),
 ('0000000000007',2,0x01,'2007-06-01 00:00:00',NULL,'incremento','2000.00','00028','00001',0x01),
 ('0000000000008',2,0x01,'2007-06-01 00:00:00',NULL,'','40000.00','00028','00001',0x01),
 ('0000000000009',2,0x01,'2007-06-29 00:00:00',NULL,'','40000.00','00028',NULL,0x01),
 ('0000000000010',2,0x01,'2008-03-02 00:00:00','2008-03-21 00:00:00','','5885.00','00001','00002',0x00),
 ('0000000000013',2,0x01,NULL,NULL,'','0.00','00118',NULL,0x00);
/*!40000 ALTER TABLE `compensation` ENABLE KEYS */;


--
-- Definition of table `country`
--

DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `countrycode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`countrycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `country`
--

/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES  ('AD',0,0x00,'Andorra'),
 ('AE',16,0x00,'United Arab Emirates'),
 ('AF',2,0x00,'Afghanistan'),
 ('AG',1,0x00,'Antigua and Barbuda'),
 ('AI',0,0x00,'Anguilla'),
 ('AL',1,0x00,'Albania'),
 ('AM',0,0x00,'Armenia'),
 ('AN',1,0x00,'Netherlands Antilles'),
 ('AO',0,0x00,'Angola'),
 ('AQ',1,0x00,'Antarctica'),
 ('AR',8,0x00,'Argentina'),
 ('AS',1,0x00,'American Samoa'),
 ('AT',0,0x00,'Austria'),
 ('AU',0,0x00,'Australia'),
 ('AW',0,0x00,'Aruba'),
 ('AX',14,0x00,'Åland islands'),
 ('AZ',0,0x00,'Azerbaijan'),
 ('BA',0,0x00,'Bosnia and herzegovina'),
 ('BB',0,0x00,'Barbados'),
 ('BD',0,0x00,'Bangladesh'),
 ('BE',0,0x00,'Belgium'),
 ('BF',0,0x00,'Burkina faso'),
 ('BG',0,0x00,'Bulgaria'),
 ('BH',0,0x00,'Bahrain'),
 ('BI',0,0x00,'Burundi'),
 ('BJ',0,0x00,'Benin'),
 ('BM',0,0x00,'Bermuda'),
 ('BN',0,0x00,'Brunei darussalam'),
 ('BO',0,0x00,'Bolivia'),
 ('BR',0,0x00,'Brazil'),
 ('BS',0,0x00,'Bahamas'),
 ('BT',0,0x00,'Bhutan'),
 ('BV',0,0x00,'Bouvet island'),
 ('BW',0,0x00,'Botswana'),
 ('BY',0,0x00,'Belarus');
INSERT INTO `country` VALUES  ('BZ',0,0x00,'Belize'),
 ('CA',0,0x00,'Canada'),
 ('CC',0,0x00,'Cocos (keeling) islands'),
 ('CD',0,0x00,'Congo, the democratic republic of the'),
 ('CF',0,0x00,'Central african republic'),
 ('CG',0,0x00,'Congo'),
 ('CH',8,0x00,'Switzerland'),
 ('CI',0,0x00,'Côte d\'ivoire'),
 ('CK',0,0x00,'Cook islands'),
 ('CL',0,0x00,'Chile'),
 ('CM',0,0x00,'Cameroon'),
 ('CN',2,0x00,'China'),
 ('CO',4,0x00,'Colombia'),
 ('CR',0,0x00,'Costa rica'),
 ('CU',0,0x00,'Cuba'),
 ('CV',0,0x00,'Cape verde'),
 ('CX',0,0x00,'Christmas island'),
 ('CY',0,0x00,'Cyprus'),
 ('CZ',0,0x00,'Czech republic'),
 ('DE',18,0x00,'Germany'),
 ('DJ',0,0x00,'Djibouti'),
 ('DK',0,0x00,'Denmark'),
 ('DM',0,0x00,'Dominica'),
 ('DO',0,0x00,'Dominican republic'),
 ('DZ',0,0x00,'Algeria'),
 ('EC',0,0x00,'Ecuador'),
 ('EE',0,0x00,'Estonia'),
 ('EG',0,0x00,'Egypt'),
 ('EH',24,0x00,'Western sahara'),
 ('ER',0,0x00,'Eritrea'),
 ('ES',154,0x00,'Spain'),
 ('ET',0,0x00,'Ethiopia'),
 ('FI',0,0x00,'Finland'),
 ('FJ',0,0x00,'Fiji');
INSERT INTO `country` VALUES  ('FK',0,0x00,'Falkland islands (malvinas)'),
 ('FM',0,0x00,'Micronesia, federated states of'),
 ('FO',0,0x00,'Faroe islands'),
 ('FR',3,0x00,'France'),
 ('GA',1,0x00,'Gabon'),
 ('GB',16,0x00,'United Kingdom'),
 ('GD',0,0x00,'Grenada'),
 ('GE',0,0x00,'Georgia'),
 ('GF',0,0x00,'French guiana'),
 ('GG',0,0x00,'Guernsey'),
 ('GH',0,0x00,'Ghana'),
 ('GI',0,0x00,'Gibraltar'),
 ('GL',0,0x00,'Greenland'),
 ('GM',0,0x00,'Gambia'),
 ('GN',0,0x00,'Guinea'),
 ('GP',0,0x00,'Guadeloupe'),
 ('GQ',0,0x00,'Equatorial guinea'),
 ('GR',0,0x00,'Greece'),
 ('GS',1,0x00,'South georgia and the south sandwich islands'),
 ('GT',0,0x00,'Guatemala'),
 ('GU',0,0x00,'Guam'),
 ('GW',0,0x00,'Guinea-bissau'),
 ('GY',0,0x00,'Guyana'),
 ('HK',0,0x00,'Hong kong'),
 ('HM',0,0x00,'Heard island and mcdonald islands'),
 ('HN',0,0x00,'Honduras'),
 ('HR',0,0x00,'Croatia'),
 ('HT',0,0x00,'Haiti'),
 ('HU',0,0x00,'Hungary'),
 ('ID',0,0x00,'Indonesia'),
 ('IE',2,0x00,'Ireland'),
 ('IL',12,0x00,'Israel'),
 ('IM',0,0x00,'Isle of man');
INSERT INTO `country` VALUES  ('IN',0,0x00,'India'),
 ('IO',0,0x00,'British indian ocean territory'),
 ('IQ',0,0x00,'Iraq'),
 ('IR',0,0x00,'Iran, islamic republic of'),
 ('IS',0,0x00,'Iceland'),
 ('IT',3,0x00,'Italy'),
 ('JE',0,0x00,'Jersey'),
 ('JM',0,0x00,'Jamaica'),
 ('JO',0,0x00,'Jordan'),
 ('JP',0,0x00,'Japan'),
 ('KE',0,0x00,'Kenya'),
 ('KG',0,0x00,'Kyrgyzstan'),
 ('KH',0,0x00,'Cambodia'),
 ('KI',0,0x00,'Kiribati'),
 ('KM',0,0x00,'Comoros'),
 ('KN',0,0x00,'Saint kitts and nevis'),
 ('KP',0,0x00,'Korea, democratic people\'s republic of'),
 ('KR',0,0x00,'Korea, republic of'),
 ('KW',0,0x00,'Kuwait'),
 ('KY',0,0x00,'Cayman islands'),
 ('KZ',0,0x00,'Kazakhstan'),
 ('LA',0,0x00,'Lao people\'s democratic republic'),
 ('LB',0,0x00,'Lebanon'),
 ('LC',0,0x00,'Saint lucia'),
 ('LI',0,0x00,'Liechtenstein'),
 ('LK',0,0x00,'Sri lanka'),
 ('LR',0,0x00,'Liberia'),
 ('LS',0,0x00,'Lesotho'),
 ('LT',0,0x00,'Lithuania'),
 ('LU',0,0x00,'Luxembourg'),
 ('LV',0,0x00,'Latvia'),
 ('LY',0,0x00,'Libyan arab jamahiriya'),
 ('MA',0,0x00,'Morocco');
INSERT INTO `country` VALUES  ('MC',0,0x00,'Monaco'),
 ('MD',0,0x00,'Moldova, republic of'),
 ('ME',0,0x00,'Montenegro'),
 ('MG',0,0x00,'Madagascar'),
 ('MH',0,0x00,'Marshall islands'),
 ('MK',0,0x00,'Macedonia, the former yugoslav republic of'),
 ('ML',0,0x00,'Mali'),
 ('MM',0,0x00,'Myanmar'),
 ('MN',0,0x00,'Mongolia'),
 ('MO',0,0x00,'Macao'),
 ('MP',0,0x00,'Northern mariana islands'),
 ('MQ',0,0x00,'Martinique'),
 ('MR',0,0x00,'Mauritania'),
 ('MS',0,0x00,'Montserrat'),
 ('MT',0,0x00,'Malta'),
 ('MU',0,0x00,'Mauritius'),
 ('MV',0,0x00,'Maldives'),
 ('MW',0,0x00,'Malawi'),
 ('MX',9,0x00,'Mexico'),
 ('MY',0,0x00,'Malaysia'),
 ('MZ',0,0x00,'Mozambique'),
 ('NA',0,0x00,'Namibia'),
 ('NC',0,0x00,'New caledonia'),
 ('NE',0,0x00,'Niger'),
 ('NF',0,0x00,'Norfolk island'),
 ('NG',0,0x00,'Nigeria'),
 ('NI',0,0x00,'Nicaragua'),
 ('NL',1,0x00,'Netherlands'),
 ('NO',0,0x00,'Norway'),
 ('NP',0,0x00,'Nepal'),
 ('NR',0,0x00,'Nauru'),
 ('NU',0,0x00,'Niue'),
 ('NZ',0,0x00,'New zealand'),
 ('OM',0,0x00,'Oman'),
 ('PA',0,0x00,'Panama');
INSERT INTO `country` VALUES  ('PE',0,0x00,'Peru'),
 ('PF',0,0x00,'French polynesia'),
 ('PG',0,0x00,'Papua new guinea'),
 ('PH',0,0x00,'Philippines'),
 ('PK',0,0x00,'Pakistan'),
 ('PL',0,0x00,'Poland'),
 ('PM',0,0x00,'Saint pierre and miquelon'),
 ('PN',0,0x00,'Pitcairn'),
 ('PR',0,0x00,'Puerto rico'),
 ('PS',0,0x00,'Palestinian territory, occupied'),
 ('PT',3,0x00,'Portugal'),
 ('PW',0,0x00,'Palau'),
 ('PY',0,0x00,'Paraguay'),
 ('QA',0,0x00,'Qatar'),
 ('RE',0,0x00,'Réunion'),
 ('RO',0,0x00,'Romania'),
 ('RS',0,0x00,'Serbia'),
 ('RU',0,0x00,'Russian federation'),
 ('RW',0,0x00,'Rwanda'),
 ('SA',1,0x00,'Saudi arabia'),
 ('SB',0,0x00,'Solomon islands'),
 ('SC',0,0x00,'Seychelles'),
 ('SD',0,0x00,'Sudan'),
 ('SE',0,0x00,'Sweden'),
 ('SG',0,0x00,'Singapore'),
 ('SH',2,0x00,'Saint helena'),
 ('SI',0,0x00,'Slovenia'),
 ('SJ',0,0x00,'Svalbard and jan mayen'),
 ('SK',0,0x00,'Slovakia'),
 ('SL',0,0x00,'Sierra leone'),
 ('SM',0,0x00,'San marino'),
 ('SN',0,0x00,'Senegal'),
 ('SO',0,0x00,'Somalia'),
 ('SR',0,0x00,'Suriname');
INSERT INTO `country` VALUES  ('ST',0,0x00,'Sao tome and principe'),
 ('SV',0,0x00,'El salvador'),
 ('SY',0,0x00,'Syrian arab republic'),
 ('SZ',0,0x00,'Swaziland'),
 ('TC',0,0x00,'Turks and caicos islands'),
 ('TD',0,0x00,'Chad'),
 ('TF',0,0x00,'French southern territories'),
 ('TG',0,0x00,'Togo'),
 ('TH',0,0x00,'Thailand'),
 ('TJ',1,0x00,'Tajikistan'),
 ('TK',2,0x00,'Tokelau'),
 ('TL',0,0x00,'Timor-leste'),
 ('TM',0,0x00,'Turkmenistan'),
 ('TN',0,0x00,'Tunisia'),
 ('TO',1,0x00,'Tonga'),
 ('TR',0,0x00,'Turkey'),
 ('TT',0,0x00,'Trinidad and tobago'),
 ('TV',0,0x00,'Tuvalu'),
 ('TW',0,0x00,'Taiwan, province of china'),
 ('TZ',0,0x00,'Tanzania, united republic of'),
 ('UA',8,0x00,'Ukraine'),
 ('UG',13,0x00,'Uganda'),
 ('UM',1,0x00,'United states minor outlying islands'),
 ('US',5,0x00,'United states'),
 ('UY',2,0x00,'Uruguay'),
 ('UZ',4,0x00,'Uzbekistan'),
 ('VA',0,0x00,'Holy see (vatican city state)'),
 ('VC',0,0x00,'Saint vincent and the grenadines'),
 ('VE',20,0x00,'Venezuela'),
 ('VG',4,0x00,'Virgin islands, british'),
 ('VI',2,0x00,'Virgin islands, u.s.');
INSERT INTO `country` VALUES  ('VN',1,0x00,'Viet nam'),
 ('VU',1,0x00,'Vanuatu'),
 ('WF',1,0x00,'Wallis and futuna'),
 ('WS',0,0x00,'Samoa'),
 ('YE',2,0x00,'Yemen'),
 ('YT',0,0x00,'Mayotte'),
 ('ZA',0,0x00,'South africa'),
 ('ZM',3,0x01,'Zambia'),
 ('ZW',0,0x01,'Zimbabwe');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;


--
-- Definition of table `country_region`
--

DROP TABLE IF EXISTS `country_region`;
CREATE TABLE `country_region` (
  `country_regioncode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`country_regioncode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `country_region`
--

/*!40000 ALTER TABLE `country_region` DISABLE KEYS */;
INSERT INTO `country_region` VALUES  ('00001',0,0x00,'Spain'),
 ('00002',0,0x00,'European non Spanish'),
 ('00003',0,0x00,'North American'),
 ('00004',0,0x00,'South American'),
 ('00005',0,0x00,'Asian'),
 ('00006',0,0x00,'African'),
 ('00007',0,0x00,'Australian'),
 ('00008',0,0x00,'Pending');
/*!40000 ALTER TABLE `country_region` ENABLE KEYS */;


--
-- Definition of table `education`
--

DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
  `educationcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `graduation_date` datetime default NULL,
  `title` varchar(100) default NULL,
  `speciality` varchar(100) default NULL,
  `center` varchar(100) default NULL,
  `education_personal` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `education_country` varchar(255) default NULL,
  PRIMARY KEY  (`educationcode`),
  KEY `FK94C3778824C4708F` (`type`),
  KEY `FK94C377888FC6FAC5` (`education_country`),
  KEY `FK94C37788CA457407` (`education_personal`),
  CONSTRAINT `FK94C3778824C4708F` FOREIGN KEY (`type`) REFERENCES `type_of_education` (`type_of_educationcode`),
  CONSTRAINT `FK94C377888FC6FAC5` FOREIGN KEY (`education_country`) REFERENCES `country` (`countrycode`),
  CONSTRAINT `FK94C37788CA457407` FOREIGN KEY (`education_personal`) REFERENCES `personal` (`personalcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `education`
--

/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES  ('0000000000001',3,0x01,'1989-01-01 00:00:00','1996-01-01 00:00:00','2000-10-10 00:00:00','Telecos','Comunicaciones','ETSETB','00001','00003','ES'),
 ('0000000000002',2,0x00,'1985-09-01 00:00:00','1990-06-30 00:00:00','1990-06-30 00:00:00','Licenciado en Informática','no','Universidad de Deusto - Bilbao','00028','00004','ES'),
 ('0000000000004',2,0x01,'2007-06-12 00:00:00',NULL,'2007-06-28 00:00:00','demo','de','de','00030','00007','ES'),
 ('0000000000005',2,0x01,'2007-06-04 00:00:00',NULL,'2007-06-22 00:00:00','a','a','a','00030','00007','ES'),
 ('0000000000006',2,0x00,'1987-09-01 00:00:00',NULL,'1992-07-31 00:00:00','Ciències Econòmiques i Empresarials','Economia de l\'Empresa','Universitat Autònoma de Barcelona','00031','00004','ES'),
 ('0000000000007',1,0x00,'2001-09-04 00:00:00',NULL,'2006-10-27 00:00:00','Doctor','Biología','Universidad Politécnica de Valencia','00034','00002','ES'),
 ('0000000000008',2,0x01,'2007-06-01 00:00:00',NULL,'2007-06-30 00:00:00','a','b','c','00030','00004','ES'),
 ('0000000000009',1,0x00,'1986-09-01 00:00:00',NULL,'1992-06-30 00:00:00','Licenciado','Informatica','Universidad Politécnica de Madrid','00029','00004','ES');
INSERT INTO `education` VALUES  ('0000000000010',2,0x01,'2007-05-31 00:00:00',NULL,NULL,'gfgfgf','gfgfgfgf','gfgfg','00029','00005','AL'),
 ('0000000000011',1,0x00,'1999-09-01 00:00:00',NULL,'2004-03-18 00:00:00','Farmàcia','-','Facultat de Farmàcia UB','00038','00004','ES'),
 ('0000000000012',1,0x00,'1996-09-22 00:00:00',NULL,'2000-06-23 00:00:00','Biochemistry','Biochemistry','Universitat de Barcelona','00041','00004','ES'),
 ('0000000000013',1,0x00,'2000-06-25 00:00:00',NULL,'2006-06-24 00:00:00','PhD','Molecular Medicine','IRB','00041','00002','ES'),
 ('0000000000014',1,0x00,'1989-09-04 00:00:00',NULL,'1993-05-31 00:00:00','Bachelor of Arts','English Literature/Italian Studies','Wesleyan University','00043','00002','US'),
 ('0000000000015',1,0x00,'1989-09-17 00:00:00',NULL,'1994-09-18 00:00:00','Periodisme','No speciality','Universitat Pompeu Fabra','00039','00004','ES'),
 ('0000000000016',2,0x01,'1981-10-03 00:00:00',NULL,'1986-07-11 00:00:00','Licenciado en Biología','Biología Molecular y Celular','Universidad de La Laguna','00042','00004','ES'),
 ('0000000000017',2,0x00,'1981-10-03 00:00:00',NULL,'1986-07-18 00:00:00','Biología','Biología Molecular y Celular','Universidad de La Laguna','00042','00004','ES');
INSERT INTO `education` VALUES  ('0000000000018',2,0x00,'1987-01-01 00:00:00',NULL,'1991-07-17 00:00:00','Doctor en Ciencias','Biología Molecular y Celular','Universidad Autónoma de Madrid','00042','00002','ES'),
 ('0000000000019',2,0x00,'1986-09-15 00:00:00',NULL,'1990-06-22 00:00:00','BUP y COU','Científico','Joan Boscà','00036','00008','ES'),
 ('0000000000020',1,0x00,'1991-09-16 00:00:00',NULL,'1993-06-22 00:00:00','técnico especialista rama sanitaría','Sanitaría','Centro de Estudios DOLMEN','00036','00007','ES'),
 ('0000000000021',1,0x00,'1998-09-15 00:00:00',NULL,'2000-06-21 00:00:00','técnico superior de diagnóstico clínico','Sanitaría','Centro de Estudios DOLMEN','00036','00007','ES'),
 ('0000000000022',2,0x01,'2007-06-01 00:00:00',NULL,'2007-06-30 00:00:00','a','s','c','00030','00008','AE'),
 ('0000000000023',1,0x00,'1981-10-01 00:00:00',NULL,'1987-04-13 00:00:00','Ciencias Biológicas','Biología Molecular y Celular','Universidad de la Laguna','00040','00004','ES'),
 ('0000000000024',1,0x00,'1988-09-01 00:00:00',NULL,'1993-03-12 00:00:00','Ciencias ','Biológicas','Universidad Autónoma de Madrid ','00040','00002','ES');
INSERT INTO `education` VALUES  ('0000000000025',1,0x00,'1999-09-15 00:00:00',NULL,'2003-06-18 00:00:00','Chemistry','Biochemistry-Organic Chemistry','University of Barcelona','00046','00004','ES'),
 ('0000000000026',2,0x00,'1996-10-02 00:00:00',NULL,'2001-09-02 00:00:00','Biology','Biologia Agroalimentària i Biotecnologia','Universitat de Barcelona','00048','00004','ES'),
 ('0000000000027',2,0x00,'1996-09-01 00:00:00',NULL,'2001-07-01 00:00:00','Licenciada en Bioquímica','Bioquímica','Universitat de Barcelona','00047','00004','ES'),
 ('0000000000028',1,0x00,'2002-01-01 00:00:00',NULL,'2006-10-24 00:00:00','Doctora en Bioquímica','Bioquímica','Universitat de Barcelona','00047','00002','ES'),
 ('0000000000029',1,0x00,'2002-09-15 00:00:00',NULL,'2003-06-23 00:00:00','TECNICO DE LABORATORIO','QUIMICO','PROVENÇANA','00049','00006','ES'),
 ('0000000000030',1,0x00,'1998-09-01 00:00:00',NULL,'2003-06-30 00:00:00','Biology','Human Biology','Universitat Pompeu Fabra','00051','00004','ES'),
 ('0000000000031',1,0x00,'1998-09-01 00:00:00',NULL,'2004-06-01 00:00:00','Chemistry','Biochemistry-Organic Chemistry','University of Barcelona','00035','00004','ES');
INSERT INTO `education` VALUES  ('0000000000032',1,0x00,'2002-09-01 00:00:00',NULL,'2004-06-30 00:00:00','Biochemistry','Biomedicine','University of Barcelona','00035','00004','ES'),
 ('0000000000033',1,0x00,'1989-08-01 00:00:00',NULL,'1997-03-31 00:00:00','Bióloga','Bioquímica','Facultad de Ciencias, UNAM','00044','00004','MX'),
 ('0000000000034',1,0x00,'1998-08-03 00:00:00',NULL,'2004-06-16 00:00:00','Doctora en Ciencias','Bioquímica','Facultad de Química, UNAM','00044','00002','MX'),
 ('0000000000035',1,0x00,'1975-10-05 00:00:00',NULL,'1980-06-19 00:00:00','yes','ADMINISTRATIVA','LUISA CURA','00002','00007','ES'),
 ('0000000000036',1,0x00,'1987-10-01 00:00:00',NULL,'1994-06-30 00:00:00','Derecho','No','UB','00001','00004','ES'),
 ('0000000000037',3,0x00,'1994-11-01 00:00:00',NULL,'1996-06-30 00:00:00','Dirección de Recursos Humanos y Consultoría en las Organizaciones','Recursos Humanos','Formación Continuada Les Heures - UB','00001','00010','ES'),
 ('0000000000038',1,0x00,'2000-01-01 00:00:00',NULL,'2007-01-01 00:00:00','Llicenciatura en Dret','Dret de l\'empresa','UOC','00032','00004','ES'),
 ('0000000000039',1,0x00,'2002-01-01 00:00:00',NULL,'2002-12-31 00:00:00','Curs comptabilitat avançada','comptabilitat i finances','Centre d\'Estudis Financers','00032','00006','ES');
INSERT INTO `education` VALUES  ('0000000000040',2,0x00,'2005-10-01 00:00:00',NULL,'2006-06-30 00:00:00','Master Internacional en Secretariado Ejecutivo y de Dirección','Secretariado','CIC','00033','00010','ES'),
 ('0000000000041',1,0x00,'1994-10-01 00:00:00',NULL,'1997-06-30 00:00:00','Master in Knowledge Engineering','no','Universidad Politécnica de Madrid','00028','00010','ES'),
 ('0000000000042',1,0x00,'1999-10-01 00:00:00',NULL,'1999-06-30 00:00:00','Distributed Systems','no','Ecole Politechnique Fédérale de Lausanne','00028','00011','CH'),
 ('0000000000043',1,0x00,'1990-09-01 00:00:00',NULL,'1994-09-01 00:00:00','Matemáticas','Matemáticas','Universitat Autònoma de Barcelona','00053','00004','ES'),
 ('0000000000044',1,0x00,'1994-09-01 00:00:00',NULL,'1995-09-01 00:00:00','Economia i Finances','Economia i Finances','Universitat Pompeu Fabra','00053','00010','ES'),
 ('0000000000045',1,0x00,'1995-09-01 00:00:00',NULL,'1999-09-01 00:00:00','Economia, Empresa i Finances','Game Theory','Universitat Pompeu Fabra','00053','00002','ES'),
 ('0000000000046',1,0x00,'1989-09-01 00:00:00',NULL,'1994-06-01 00:00:00','MD (Medical Doctor)','.','UNIVERSITAT AUTONOMA DE BARCELONA-HOSPITAL DEL MAR','00061','00004','ES');
INSERT INTO `education` VALUES  ('0000000000047',1,0x00,'2003-04-03 00:00:00',NULL,NULL,'DOCTOR IN MEDICINE','LIFE AND HEALTH SCIENCES','UNIVERSITAT POMPEU FABRA','00061','00002','ES'),
 ('0000000000048',1,0x00,'1992-09-09 00:00:00',NULL,'2003-06-22 00:00:00','BS','Biology','UB','00062','00004','ES'),
 ('0000000000049',1,0x00,'2000-09-01 00:00:00',NULL,'2005-02-01 00:00:00','Biotechnology degree','Biotechnology','Universitat Autònoma de Barcelona','00065','00004','ES'),
 ('0000000000050',1,0x00,'1990-09-16 00:00:00',NULL,'1995-06-30 00:00:00','Licenciado','Química Orgánica','Universitat de Barcelona','00067','00004','ES'),
 ('0000000000051',2,0x00,'1995-05-01 00:00:00',NULL,'1997-12-31 00:00:00','Licenciado en grado','Química','Universitat  de Barcelona','00067','00010','ES'),
 ('0000000000052',1,0x00,'1997-01-01 00:00:00',NULL,'2000-12-31 00:00:00','Doctor','Químicas','Universitat Autònoma de Barcelona','00067','00002','ES'),
 ('0000000000053',1,0x00,'2002-11-01 00:00:00',NULL,'2003-03-31 00:00:00','Postgraduado','Técnicas avanzadas de formulación','Fundació Bosch i Gimpera','00067','00011','ES'),
 ('0000000000054',1,0x00,'1991-10-01 00:00:00',NULL,'1996-06-01 00:00:00','Biology','Biology','Universidad de Salamanca','00070','00004','ES');
INSERT INTO `education` VALUES  ('0000000000055',2,0x00,'1996-10-01 00:00:00',NULL,'2000-06-25 00:00:00','Translation and Interpreting','Biomedicine','Universitat Pompeu Fabra','00059','00004','ES'),
 ('0000000000056',2,0x00,'1996-03-24 00:00:00',NULL,NULL,'Erasmus grant','English as a foreign language','University of Salford','00059','00007','GB'),
 ('0000000000057',1,0x00,'1989-09-03 00:00:00',NULL,NULL,'BUP + COU','Lletres','Escola Àlvarez','00059','00008','ES'),
 ('0000000000058',2,0x00,'1982-09-05 00:00:00',NULL,'1989-06-28 00:00:00','EGB','EGB','Escola Àlvarez','00059','00009','ES'),
 ('0000000000059',2,0x00,'1991-09-02 00:00:00',NULL,'1995-12-15 00:00:00','Biología','Bioquimica y Biologia Molecular','CBM-SO','00068','00002','ES'),
 ('0000000000060',1,0x00,'2004-10-01 00:00:00',NULL,'2006-10-20 00:00:00','PhD Programm','Basic Biomedical Research and Health and Life Sciences','Pompeu Fabra University','00072','00002','ES'),
 ('0000000000061',2,0x00,'2005-02-01 00:00:00',NULL,'2005-02-15 00:00:00','Curso de Formación para usuarios de Animales para la Experimentación y otras finalidades Científicas','Personal Investigador','Universitat Autònoma de Barcelona','00072','00011','ES'),
 ('0000000000062',1,0x00,'1999-09-15 00:00:00',NULL,'2004-06-24 00:00:00','Biologia','Humana','Universitat Pompeu Fabra','00072','00004','ES');
INSERT INTO `education` VALUES  ('0000000000063',1,0x00,'1992-10-01 00:00:00',NULL,'1997-03-10 00:00:00','Llicenciat','Quimica','Universitat de Barcelona','00075','00004','ES'),
 ('0000000000064',1,0x00,'1998-03-15 00:00:00',NULL,'2003-03-03 00:00:00','Doctor','Quimica','Universitat de Barcelona','00075','00002','ES'),
 ('0000000000065',1,0x00,'2000-09-13 00:00:00',NULL,'2005-06-15 00:00:00','Quemistry','Quemistry','Universidad de Valladolid','00074','00004','ES'),
 ('0000000000066',1,0x00,'2000-09-01 00:00:00',NULL,'2005-07-20 00:00:00','Biologia','BIOLOGÍA CELULAR Y GENÉTICA','UAB','00078','00004','ES'),
 ('0000000000067',1,0x00,'2005-10-01 00:00:00',NULL,NULL,'BIOTECNOLOGÍA','BIOTECNOLOGÍA','UB','00078','00002','ES'),
 ('0000000000068',1,0x00,'1974-06-16 00:00:00',NULL,'1976-06-16 00:00:00','BACHILLERAT','CIENCIES','ESCOLA BON SALVADOR','00037','00008','ES'),
 ('0000000000069',3,0x01,'1996-10-10 00:00:00',NULL,'1997-06-16 00:00:00','GESTIÓ ADM. INFORMATI','INFORMATICA DE GESTIÓ','DIPUTACIÓ DE BARCELONA','00037','00006','ES'),
 ('0000000000070',1,0x00,'1998-09-01 00:00:00',NULL,'2002-06-15 00:00:00','BS','Biochemistry','UB','00085','00004','ES');
INSERT INTO `education` VALUES  ('0000000000071',1,0x00,'2000-10-16 00:00:00',NULL,'2006-06-22 00:00:00','Chemistry','Biochemistry','Universität zu Koeln','00084','00005','DE'),
 ('0000000000072',1,0x00,'1972-09-15 00:00:00',NULL,'1977-06-30 00:00:00','Química','Quimica Orgánica','Universitat de Barcelona','00069','00004','ES'),
 ('0000000000073',1,0x00,'1977-09-15 00:00:00',NULL,'1983-06-30 00:00:00','Farmacia','Bioquímica','Universitat de Barcelona','00069','00004','ES'),
 ('0000000000074',1,0x00,'1977-09-01 00:00:00',NULL,'1983-09-30 00:00:00','Quimica','Química Orgánica','Universitat de Barcelona','00069','00002','ES'),
 ('0000000000075',1,0x00,'2001-09-17 00:00:00',NULL,'2003-06-20 00:00:00','Laboratory of clinical diagnosis','Sanitary','Escola Bonanova','00087','00007','ES'),
 ('0000000000076',1,0x00,'1999-09-20 00:00:00',NULL,'2001-06-08 00:00:00','Scientist','Sciences ','J.V.Foix','00087','00008','ES'),
 ('0000000000077',1,0x00,'2000-09-25 00:00:00',NULL,'0006-06-22 00:00:00','Biology','Biology','Universitat Pompeu Fabra','00071','00004','ES'),
 ('0000000000078',1,0x00,'2001-03-11 00:00:00',NULL,'1996-10-25 00:00:00','Lic. en Cs. Biologicas','--','Facultad de Cs. Biologicas, Universidad de Buenos Aires','00054','00004','AR');
INSERT INTO `education` VALUES  ('0000000000079',1,0x00,'1985-03-10 00:00:00',NULL,'1991-12-09 00:00:00','Bachiller','Orientacion pedagogica','Esculea Normal superior N3','00054','00008','AR'),
 ('0000000000080',1,0x00,'1997-06-02 00:00:00',NULL,'2002-10-14 00:00:00','Doctor en Ciencias Biologicas','--','Universidad de Buenos Aires','00054','00002','AR'),
 ('0000000000081',1,0x00,'2003-01-09 00:00:00',NULL,'2005-01-10 00:00:00','Beca post Docotoral','Biologia Molecular y Celular','CNIO-EMBL (FUndacion Carolina)','00054','00011','ES'),
 ('0000000000082',1,0x00,'1972-09-15 00:00:00',NULL,'1977-06-30 00:00:00','Quimico','Quimica Orgánica','Universitat de Barcelona','00021','00004','ES'),
 ('0000000000083',1,0x00,'1977-09-15 00:00:00',NULL,'1983-06-30 00:00:00','Farmacia','Bioquímica','Universitat de Barcelona','00021','00004','ES'),
 ('0000000000084',1,0x00,'1977-09-01 00:00:00',NULL,'1983-09-30 00:00:00','Quimica','Química Orgánica','Universitat de Barcelona','00021','00002','ES'),
 ('0000000000085',1,0x00,'1998-09-01 00:00:00',NULL,'2002-06-30 00:00:00','Chemistry','physical chemistry','UB','00089','00004','ES'),
 ('0000000000086',1,0x00,'1993-10-01 00:00:00',NULL,'1996-07-31 00:00:00','B. Sc.','Biology','Tel-Aviv University','00091','00008','IL');
INSERT INTO `education` VALUES  ('0000000000087',1,0x00,'1997-10-01 00:00:00',NULL,'1999-12-31 00:00:00','M.Sc.','Developmental Biology','Weizmann Institute of Science','00091','00010','IL'),
 ('0000000000088',1,0x00,'2000-01-01 00:00:00',NULL,'2005-11-30 00:00:00','Ph.D','Developmental Biology','Weizmann Institute of Science','00091','00002','IL'),
 ('0000000000089',1,0x00,'1996-09-01 00:00:00',NULL,'0001-02-01 00:00:00','BIOCHEMISTRY','BIOCHEMISTRY','UNIVERSITAT AUTÒNOMA DE BARCELONA','00081','00004','ES'),
 ('0000000000090',1,0x00,'2001-09-01 00:00:00',NULL,'2005-01-31 00:00:00','STRUCTURE-FUNCTION RELATIONSHIP OF THE COLLAGEN RECEPTOR, GLYCOPROTEIN VI','MEDICINE','UNIVERSITY OF BIRMINGHAM','00081','00002','GB'),
 ('0000000000091',1,0x00,'1998-09-14 00:00:00',NULL,'2002-07-15 00:00:00','Chemistry','Organic Chemistry','Universitat de Barcelona','00098','00004','ES'),
 ('0000000000092',1,0x00,'2002-09-02 00:00:00',NULL,'2003-09-26 00:00:00','Experimental Organic Chemistry','Organic Chemistry','Univseristat de Barcelona','00098','00010','ES'),
 ('0000000000093',1,0x00,'2003-01-08 00:00:00',NULL,'2006-11-08 00:00:00','Chemistry','Organic Chemistry','Universitat de Barcelona','00098','00002','ES');
INSERT INTO `education` VALUES  ('0000000000094',1,0x00,'2002-09-01 00:00:00',NULL,'2006-06-19 00:00:00','Licenciatura en ciencias químicas','Química Orgánica','Facultad de Química de Universidad de Barcelona','00099','00004','ES'),
 ('0000000000095',1,0x00,'1998-09-01 00:00:00',NULL,'2002-06-30 00:00:00','Quimica','Organica','Universitat de Barcelona','00101','00004','ES'),
 ('0000000000096',1,0x00,'2002-09-01 00:00:00',NULL,'2007-06-22 00:00:00','Quimica','Organica','Universitat de Barcelona','00101','00002','ES'),
 ('0000000000097',1,0x00,'2002-09-01 00:00:00',NULL,'2003-06-30 00:00:00','Quimica','Organica','Universitat de Barcelona','00101','00010','ES'),
 ('0000000000098',2,0x00,'2000-09-17 00:00:00',NULL,'2005-02-15 00:00:00','Biology','Medical biology','Universitat de Barcelona','00079','00004','ES'),
 ('0000000000099',1,0x00,'1998-09-17 00:00:00',NULL,'2002-06-01 00:00:00','QUIMICA','ORGANICA','Universidad de Alcalá (Madrid)','00100','00004','ES'),
 ('0000000000100',1,0x00,'1997-10-02 00:00:00',NULL,NULL,'Química','Orgànica','Institut Químic de Sarrià','00104','00004','ES'),
 ('0000000000101',1,0x00,'1999-10-03 00:00:00',NULL,'2007-06-13 00:00:00','Biologia','-','Universidad Sevilla','00105','00004','ES');
INSERT INTO `education` VALUES  ('0000000000102',1,0x00,'1998-09-15 00:00:00',NULL,'1998-06-04 00:00:00','Bachillerada','Ciencies de la Salud','IES Príncep de Viana','00092','00008','ES'),
 ('0000000000103',1,0x00,'2000-10-01 00:00:00',NULL,'2005-06-02 00:00:00','Biología','Recerca Biomèdica','Universitat Pompeu Fabra','00092','00004','ES'),
 ('0000000000104',1,0x00,'2000-10-02 00:00:00',NULL,'2004-02-20 00:00:00','Bioquimica','Bioquimica','Universidad Autonoma de Madrid','00094','00004','ES'),
 ('0000000000105',1,0x00,'2000-09-01 00:00:00',NULL,'2004-09-01 00:00:00','BSc','Chemistry','Dept. Chemistry, University College Dublin','00106','00004','IE'),
 ('0000000000106',1,0x00,'2005-01-09 00:00:00',NULL,'2006-10-05 00:00:00','Biomedicina','Biomedicina','Universidad de Barcelona','00094','00010','ES'),
 ('0000000000107',2,0x01,'2007-06-01 00:00:00',NULL,'2007-06-02 00:00:00','asdf','asdf','asdf','00030','00011','ES'),
 ('0000000000108',2,0x01,'2007-06-11 00:00:00',NULL,NULL,'asdf','asdf','asdf','00030','00009','ES'),
 ('0000000000109',1,0x00,'2001-09-15 00:00:00',NULL,'2006-06-30 00:00:00','Chemistry','Organic Chemistry','UB','00112','00004','ES');
INSERT INTO `education` VALUES  ('0000000000110',1,0x00,'2001-12-01 00:00:00',NULL,'2005-10-14 00:00:00','Dr','Genetics','Institut Jaques Monod','00090','00002','FR'),
 ('0000000000111',1,0x00,'1992-10-01 00:00:00',NULL,'1998-06-01 00:00:00','BIOQUIMICA','BIOQUIMICA','UNIV DE GRANADA','00093','00004','ES'),
 ('0000000000112',1,0x00,'1999-01-01 00:00:00',NULL,'2004-10-10 00:00:00','PAPEL DE LAS INTEGRINAS DURANTE EL DESARROLLO DE DROSOPHILA MELANOGASTER','BIOLOGIA DEL DESARROLLO','UNIV. CAMBRDIGE-UNIV. GRANADA','00093','00002','ES'),
 ('0000000000113',1,0x00,'1992-03-10 00:00:00',NULL,'1994-03-24 00:00:00','aaaa','aaa','aaa','00118','00005','SH'),
 ('0000000000114',1,0x00,'1992-03-12 00:00:00',NULL,'1994-03-29 00:00:00','gregt','gd','dgdg','00122','00007','UY'),
 ('0000000000116',1,0x00,'1996-03-11 00:00:00',NULL,'2002-03-12 00:00:00','sdzfs','sdfds','sdff','00114','00007','ES'),
 ('0000000000117',1,0x00,'1996-03-10 00:00:00',NULL,'2005-03-13 00:00:00','fsdf','sfsf','sfsfs','00005','00004','VE'),
 ('0000000000118',1,0x00,'1994-03-16 00:00:00',NULL,'1998-03-19 00:00:00','fsdsdf','fssf','sff','00110','00002','UA');
INSERT INTO `education` VALUES  ('0000000000119',1,0x00,'1958-03-18 00:00:00',NULL,'1992-03-23 00:00:00','fsdf','asdsff','fasfsda','00111','00012','VN'),
 ('0000000000121',1,0x00,'1993-07-15 00:00:00',NULL,'2001-07-10 00:00:00','sfdfs','fsf','sfsf','00136','00008','ES');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;


--
-- Definition of table `entity`
--

DROP TABLE IF EXISTS `entity`;
CREATE TABLE `entity` (
  `entitycode` varchar(100) NOT NULL,
  `version` int(11) NOT NULL,
  `hashkey` varchar(22) default NULL,
  PRIMARY KEY  (`entitycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `entity`
--

/*!40000 ALTER TABLE `entity` DISABLE KEYS */;
INSERT INTO `entity` VALUES  ('00001',21,NULL),
 ('00002',7,NULL),
 ('00003',10,NULL),
 ('00004',7,NULL),
 ('00005',5,NULL),
 ('00006',10,NULL),
 ('00007',1,NULL),
 ('00008',1,NULL),
 ('00009',1,NULL),
 ('00010',1,NULL),
 ('00011',1,NULL),
 ('00012',1,NULL),
 ('00013',1,NULL),
 ('00014',1,NULL),
 ('00015',1,NULL),
 ('00016',1,NULL),
 ('00017',3,NULL),
 ('00018',2,NULL),
 ('00019',1,NULL),
 ('00020',1,NULL),
 ('00021',1,NULL),
 ('00022',1,NULL),
 ('00023',1,NULL),
 ('00024',1,NULL),
 ('00025',1,NULL),
 ('00026',1,NULL),
 ('00027',1,NULL),
 ('00028',3,NULL),
 ('00029',8,NULL),
 ('00030',19,NULL),
 ('00031',3,NULL),
 ('00032',3,NULL),
 ('00033',2,NULL),
 ('00034',3,NULL),
 ('00035',2,NULL),
 ('00036',3,NULL),
 ('00037',3,NULL),
 ('00038',3,NULL),
 ('00039',2,NULL),
 ('00040',3,NULL),
 ('00041',2,NULL),
 ('00042',2,NULL),
 ('00043',2,NULL),
 ('00044',3,NULL),
 ('00045',2,NULL),
 ('00046',3,NULL),
 ('00047',3,NULL),
 ('00048',3,NULL),
 ('00049',3,NULL),
 ('00050',2,NULL),
 ('00051',2,NULL),
 ('00052',2,NULL);
INSERT INTO `entity` VALUES  ('00053',3,NULL),
 ('00054',2,NULL),
 ('00055',2,NULL),
 ('00056',1,NULL),
 ('00057',2,NULL),
 ('00058',2,NULL),
 ('00059',2,NULL),
 ('00060',2,NULL),
 ('00061',3,NULL),
 ('00062',3,NULL),
 ('00063',1,NULL),
 ('00064',1,NULL),
 ('00065',2,NULL),
 ('00066',2,NULL),
 ('00067',4,NULL),
 ('00068',2,NULL),
 ('00069',3,NULL),
 ('00070',2,NULL),
 ('00071',2,NULL),
 ('00072',2,NULL),
 ('00073',2,NULL),
 ('00074',2,NULL),
 ('00075',2,NULL),
 ('00076',2,NULL),
 ('00077',2,NULL),
 ('00078',2,NULL),
 ('00079',2,NULL),
 ('00080',2,NULL),
 ('00081',2,NULL),
 ('00082',1,NULL),
 ('00083',2,NULL),
 ('00084',2,NULL),
 ('00085',3,NULL),
 ('00086',2,NULL),
 ('00087',2,NULL),
 ('00088',2,NULL),
 ('00089',3,NULL),
 ('00090',3,NULL),
 ('00091',3,NULL),
 ('00092',2,NULL),
 ('00093',2,NULL),
 ('00094',3,NULL),
 ('00095',2,NULL),
 ('00096',2,NULL),
 ('00097',2,NULL),
 ('00098',2,NULL),
 ('00099',3,NULL),
 ('00100',3,NULL),
 ('00101',2,NULL),
 ('00102',2,NULL),
 ('00103',1,NULL),
 ('00104',2,NULL),
 ('00105',2,NULL);
INSERT INTO `entity` VALUES  ('00106',2,NULL),
 ('00107',2,NULL),
 ('00108',2,NULL),
 ('00109',2,NULL),
 ('00110',3,NULL),
 ('00111',3,NULL),
 ('00112',3,NULL),
 ('00113',2,NULL),
 ('00114',3,NULL),
 ('00115',2,NULL),
 ('00116',3,NULL),
 ('00118',11,NULL),
 ('00123',2,NULL),
 ('00124',2,NULL),
 ('00129',1,NULL),
 ('00130',2,NULL),
 ('00132',1,NULL),
 ('00133',6,NULL),
 ('00135',3,NULL),
 ('00136',1,NULL),
 ('99999',138,NULL),
 ('administrator',41,'-652229939'),
 ('basic',107,'93508654'),
 ('guest',16,'98708952'),
 ('irbpeople_ro',0,NULL),
 ('irbpeople_rw',0,NULL),
 ('supervisor',67,'-1697229976');
/*!40000 ALTER TABLE `entity` ENABLE KEYS */;


--
-- Definition of table `funding_detail`
--

DROP TABLE IF EXISTS `funding_detail`;
CREATE TABLE `funding_detail` (
  `funding_detailcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `institution` varchar(100) default NULL,
  `irb_budget_code` varchar(100) default NULL,
  `percentage` int(11) default '0',
  `funding_detail_personal` varchar(255) default NULL,
  PRIMARY KEY  (`funding_detailcode`),
  KEY `FK97B1B7F3CB65F03C` (`funding_detail_personal`),
  CONSTRAINT `FK97B1B7F3CB65F03C` FOREIGN KEY (`funding_detail_personal`) REFERENCES `personal` (`personalcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `funding_detail`
--

/*!40000 ALTER TABLE `funding_detail` DISABLE KEYS */;
INSERT INTO `funding_detail` VALUES  ('0000000000002',1,0x00,'IRB','Recursos Humanos',100,'00002'),
 ('0000000000003',1,0x00,'IRB','Recursos Humanos',100,'00001'),
 ('0000000000004',1,0x00,'IRB','Informática',100,'00028'),
 ('0000000000005',1,0x00,'IRB','Botín',100,'00034'),
 ('0000000000006',1,0x00,'IRB',NULL,100,'00029'),
 ('0000000000007',1,0x00,'IRB',NULL,100,'00031'),
 ('0000000000008',1,0x00,'IRB','Dinamización Orozco',100,'00089'),
 ('0000000000009',1,0x00,'IRB','PRJI.2005 SGR 00821 (AGAUR)',100,'00091'),
 ('0000000000010',2,0x01,'fdsdfs','Botín',785,'00001');
/*!40000 ALTER TABLE `funding_detail` ENABLE KEYS */;


--
-- Definition of table `gender`
--

DROP TABLE IF EXISTS `gender`;
CREATE TABLE `gender` (
  `gendercode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`gendercode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gender`
--

/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES  ('00001',205,0x00,'Male'),
 ('00002',225,0x00,'Female');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;


--
-- Definition of table `grant_concession`
--

DROP TABLE IF EXISTS `grant_concession`;
CREATE TABLE `grant_concession` (
  `grant_concessioncode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `call_code` varchar(100) default NULL,
  `grant_concession_personal` varchar(255) default NULL,
  `table_grant` varchar(255) default NULL,
  `current` bit(1) NOT NULL,
  PRIMARY KEY  (`grant_concessioncode`),
  KEY `FKCDCC39E7F61C7FB7` (`table_grant`),
  KEY `FKCDCC39E7EEC8CCC8` (`grant_concession_personal`),
  CONSTRAINT `FKCDCC39E7EEC8CCC8` FOREIGN KEY (`grant_concession_personal`) REFERENCES `personal` (`personalcode`),
  CONSTRAINT `FKCDCC39E7F61C7FB7` FOREIGN KEY (`table_grant`) REFERENCES `table_grant` (`grantcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grant_concession`
--

/*!40000 ALTER TABLE `grant_concession` DISABLE KEYS */;
INSERT INTO `grant_concession` VALUES  ('0000000000001',2,0x01,'2007-06-05 00:00:00','2007-06-30 00:00:00','fgfgf','00030','00006',0x00),
 ('0000000000002',2,0x01,'2007-06-12 00:00:00','2007-06-01 00:00:00','fgfgfg','00029','00007',0x00),
 ('0000000000003',1,0x00,'2005-04-01 00:00:00','2009-04-01 00:00:00','-','00038','00003',0x01),
 ('0000000000004',1,0x00,'2002-01-01 00:00:00','2005-12-31 00:00:00','AP2001-2612','00047','00003',0x00),
 ('0000000000005',1,0x00,'2006-01-01 00:00:00','2007-01-31 00:00:00','13/05-IRB','00047','00001',0x00),
 ('0000000000006',1,0x00,'2003-05-01 00:00:00','2007-04-30 00:00:00','BES-2003-2231','00048','00004',0x00),
 ('0000000000007',1,0x00,'2005-10-01 00:00:00',NULL,'08/05-IRB','00065','00002',0x01),
 ('0000000000008',1,0x00,'2004-01-01 00:00:00','2006-12-31 00:00:00','EU-Commission FP6-2002-Lifescihealth-LSH-2002-2.1.3.3 ','00068','00002',0x00),
 ('0000000000009',1,0x00,'2004-01-01 00:00:00','2006-12-31 00:00:00','BFU2004-0142-E','00068','00002',0x00),
 ('0000000000010',1,0x00,'2004-01-01 00:00:00','2005-12-31 00:00:00','BBVA grant','00068','00002',0x00),
 ('0000000000011',1,0x00,'2004-12-13 00:00:00','2007-12-13 00:00:00','BFU2004-00167/BMC ','00068','00002',0x00);
INSERT INTO `grant_concession` VALUES  ('0000000000012',1,0x00,'2006-01-01 00:00:00','2008-12-31 00:00:00',' 2005 SGR 00118 ','00068','00005',0x01),
 ('0000000000013',1,0x00,'2005-01-01 00:00:00','2005-12-31 00:00:00','2005FI 00464','00072','00005',0x00),
 ('0000000000014',1,0x00,'2006-01-01 00:00:00','2006-12-31 00:00:00','2006FIC 00550','00072','00005',0x00),
 ('0000000000015',1,0x00,'2007-01-01 00:00:00','2007-12-31 00:00:00','2207FIC 00567','00072','00005',0x00),
 ('0000000000016',1,0x00,'2004-09-01 00:00:00','2004-12-31 00:00:00','CODIC13/04-N1','00072','00002',0x00),
 ('0000000000017',1,0x00,'2006-10-01 00:00:00',NULL,'BES2006-12507','00074','00004',0x01),
 ('0000000000018',1,0x00,'2005-10-01 00:00:00','2007-01-01 00:00:00','C07/05-N2','00078','00001',0x00),
 ('0000000000019',1,0x00,'2007-01-01 00:00:00',NULL,'2007FI_B 01280','00078','00005',0x01),
 ('0000000000020',1,0x00,'0006-07-03 00:00:00',NULL,'02/06-13','00071','00001',0x01),
 ('0000000000021',1,0x00,'2003-01-01 00:00:00','2006-12-31 00:00:00','AP2002-2907','00098','00003',0x00),
 ('0000000000022',1,0x00,'2007-05-01 00:00:00','2111-05-01 00:00:00','AP-2006-00733','00099','00003',0x01);
INSERT INTO `grant_concession` VALUES  ('0000000000023',1,0x00,'2005-03-01 00:00:00',NULL,'08/05-N14','00079','00001',0x01),
 ('0000000000024',1,0x00,'2005-03-01 00:00:00','2009-03-01 00:00:00','AP-2004-5502','00100','00003',0x01),
 ('0000000000025',2,0x01,'2007-06-11 00:00:00','2007-06-12 00:00:00','1','00030','00002',0x00),
 ('0000000000026',1,0x00,'2007-05-01 00:00:00','2011-05-01 00:00:00','AP-2006-00734','00112','00003',0x01),
 ('0000000000027',1,0x00,'1992-03-24 00:00:00','1993-03-12 00:00:00','godugior','00005','00001',0x00),
 ('0000000000028',9,0x00,'2004-03-02 00:00:00','2005-03-23 00:00:00','dfgdg','00001','00005',0x00),
 ('0000000000029',2,0x01,'1999-03-10 00:00:00',NULL,'sdasda','00024','00003',0x01),
 ('0000000000030',1,0x00,'2008-03-02 00:00:00',NULL,'dfgdg','00010','00002',0x01),
 ('0000000000031',2,0x01,'2008-03-02 00:00:00',NULL,'aa','00118','00002',0x01),
 ('0000000000032',6,0x00,'2008-01-01 00:00:00',NULL,'adfadf','00001','00004',0x01),
 ('0000000000033',2,0x00,'2008-01-01 00:00:00','2008-12-01 00:00:00','lakdfj','00031','00001',0x01);
/*!40000 ALTER TABLE `grant_concession` ENABLE KEYS */;


--
-- Definition of table `holiday`
--

DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday` (
  `holidaycode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `start_date` datetime default NULL,
  `end_date` varchar(100) default NULL,
  `days` int(11) default '0',
  `description` text,
  `holiday_personal` varchar(255) default NULL,
  `type_of_holiday` varchar(255) default NULL,
  PRIMARY KEY  (`holidaycode`),
  KEY `FK6BE0B43825517957` (`holiday_personal`),
  KEY `FK6BE0B438DD19F3E3` (`type_of_holiday`),
  CONSTRAINT `FK6BE0B43825517957` FOREIGN KEY (`holiday_personal`) REFERENCES `personal` (`personalcode`),
  CONSTRAINT `FK6BE0B438DD19F3E3` FOREIGN KEY (`type_of_holiday`) REFERENCES `type_of_holidays` (`type_of_holidayscode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `holiday`
--

/*!40000 ALTER TABLE `holiday` DISABLE KEYS */;
INSERT INTO `holiday` VALUES  ('0000000000001',1,0x00,'2008-02-13 00:00:00','12/03/2008',30,'','00001','00005');
/*!40000 ALTER TABLE `holiday` ENABLE KEYS */;


--
-- Definition of table `irbholiday`
--

DROP TABLE IF EXISTS `irbholiday`;
CREATE TABLE `irbholiday` (
  `irbholidaycode` varchar(20) NOT NULL,
  `initialdate` date default NULL,
  `enddate` date default NULL,
  `description` varchar(100) character set latin1 default NULL,
  `type` int(10) unsigned default NULL,
  `status` int(10) unsigned default NULL,
  `personalcode` varchar(255) default NULL,
  `validationcode` varchar(100) default NULL,
  `holidays` int(10) unsigned default NULL,
  `previousyearholidays` int(10) unsigned default NULL,
  `aps` int(10) unsigned default NULL,
  PRIMARY KEY  USING BTREE (`irbholidaycode`),
  KEY `Index_2` (`personalcode`),
  CONSTRAINT `FK_irbholiday_1` FOREIGN KEY (`personalcode`) REFERENCES `personal` (`personalcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `irbholiday`
--

/*!40000 ALTER TABLE `irbholiday` DISABLE KEYS */;
INSERT INTO `irbholiday` VALUES  ('0000000000521','2008-04-10','2008-04-10','Limit vacances',3,1,NULL,NULL,0,0,0),
 ('0000000000522','2008-06-02','2008-06-02','10/06/2008',0,1,'00001',NULL,1,0,0),
 ('0000000000523','2008-10-29','2008-11-20','20/11/2008',1,1,'00001',NULL,0,0,17),
 ('0000000000524','2008-11-21','2008-12-01','',0,1,'00001',NULL,7,0,0);
/*!40000 ALTER TABLE `irbholiday` ENABLE KEYS */;


--
-- Definition of table `irbholidayinfo`
--

DROP TABLE IF EXISTS `irbholidayinfo`;
CREATE TABLE `irbholidayinfo` (
  `personalcode` varchar(255) default NULL,
  `year` int(10) unsigned default NULL,
  `holidays` int(10) unsigned default NULL,
  `pendingholidays` int(10) unsigned default NULL,
  `aps` int(10) unsigned default NULL,
  `pendingaps` int(10) unsigned default NULL,
  `previousyearholidays` int(10) unsigned default NULL,
  `irbholidayinfocode` varchar(255) NOT NULL,
  `holidaysforyear` int(10) unsigned default NULL,
  `apsforyear` int(10) unsigned default NULL,
  PRIMARY KEY  USING BTREE (`irbholidayinfocode`),
  UNIQUE KEY `Index_2` USING BTREE (`personalcode`,`year`),
  CONSTRAINT `FK_irbholidayinfo_1` FOREIGN KEY (`personalcode`) REFERENCES `personal` (`personalcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `irbholidayinfo`
--

/*!40000 ALTER TABLE `irbholidayinfo` DISABLE KEYS */;
INSERT INTO `irbholidayinfo` VALUES  ('00001',2008,17,0,0,0,0,'0000000000724',25,9),
 ('00136',2008,23,0,9,0,0,'0000000000725',23,9),
 ('00001',2007,0,0,0,0,0,'0000000000726',0,0),
 ('00001',2009,23,0,9,0,17,'0000000000727',23,9),
 ('00136',2009,23,0,9,0,23,'0000000000728',23,9),
 ('00136',2010,23,0,9,0,23,'0000000000729',23,9),
 ('00001',2010,23,0,9,0,23,'0000000000730',23,9),
 ('00136',2007,0,0,0,0,0,'0000000000731',0,0),
 ('00001',2006,0,0,0,0,0,'0000000000732',0,0),
 ('00136',2006,0,0,0,0,0,'0000000000733',0,0),
 ('00001',2005,0,0,0,0,0,'0000000000734',0,0),
 ('00136',2005,0,0,0,0,0,'0000000000735',0,0),
 ('00001',2011,23,0,9,0,23,'0000000000736',23,9),
 ('00136',2011,23,0,9,0,23,'0000000000737',23,9),
 ('00001',2012,23,0,9,0,23,'0000000000738',23,9),
 ('00136',2012,23,0,9,0,23,'0000000000739',23,9),
 ('00001',2013,23,0,9,0,23,'0000000000740',23,9),
 ('00136',2013,23,0,9,0,23,'0000000000741',23,9),
 ('00001',2014,23,0,9,0,23,'0000000000742',23,9),
 ('00136',2014,23,0,9,0,23,'0000000000743',23,9),
 ('00001',2015,23,0,9,0,23,'0000000000744',23,9);
INSERT INTO `irbholidayinfo` VALUES  ('00136',2015,23,0,9,0,23,'0000000000745',23,9);
/*!40000 ALTER TABLE `irbholidayinfo` ENABLE KEYS */;


--
-- Definition of table `irbpeople_nis`
--

DROP TABLE IF EXISTS `irbpeople_nis`;
CREATE TABLE `irbpeople_nis` (
  `usercode` varchar(5) NOT NULL,
  `username` varchar(20) NOT NULL,
  `ciphered_pwd` varchar(100) NOT NULL,
  `cipher_type` varchar(10) NOT NULL,
  `uid` varchar(5) NOT NULL,
  `group_name` varchar(50) NOT NULL,
  `gecos` varchar(100) NOT NULL,
  `home_dir` varchar(50) NOT NULL,
  `shell` varchar(20) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `irbpeople_nis`
--

/*!40000 ALTER TABLE `irbpeople_nis` DISABLE KEYS */;
INSERT INTO `irbpeople_nis` VALUES  ('00208','gelpi','lZOU.ep0Tx4jc','DES','500','orozco','Josep Lluis Gelpi','/home/gelpi','/bin/bash'),
 ('00432','modesto','$1$z6wgOR84$raArfGuJ6gD.UB2IEt.v80','MD5','501','orozco','Modesto Orozco','/home/modesto','/bin/bash'),
 ('99999','javier','$1$h50UDSy8$aNMU4lsnkTEeIAoZ70Ssk.','MD5','502','orozco','F. Javier Luque','/home/javier','/bin/bash'),
 ('00303','faustino','ny1f7xhhYAaZg','DES','503','orozco','Ignacio Faustino','/home/faustino','/bin/bash'),
 ('00265','rgarcia','/Bja6uV/DACR6','DES','505','orozco','Rebeca Garcia','/home/rgarcia','/bin/bash'),
 ('99999','elena','$1$t0CZ5184$562JLeJ8kqS52O/ovqthU1','MD5','506','orozco','Elena Cubero','/home/elena','/bin/bash'),
 ('99999','xbarril','AiBXqNO4RPYCk','DES','507','orozco','Xavier Barril','/home/xbarril','/bin/bash'),
 ('99999','jramon','*LK*$1$EZ7R0HHx$BjK3BJhHsafIc8SQpVfky1','','509','orozco','Jose Ramon Blas','/home/jramon','/bin/bash'),
 ('99999','david','$1$apxzWOED$Gz7ZPNLzp0.4HVMloLMBm/','MD5','510','orozco','David Talavera','/home/david','/bin/bash'),
 ('99999','jordi','*LK*','','514','orozco','Jordi Munoz','/home/jordi','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('00198','xavier','$1$tt3NlTem$sEx6iD6YZn54N1ALOdqJ10','MD5','515','orozco','Xavier de la Cruz','/home/xavier','/bin/bash'),
 ('99999','manu','$1$GHugN0nm$EC4TgEAU8.w.C9mMSTyzm/','MD5','517','orozco','Manuel Rueda','/home/manu','/bin/bash'),
 ('99999','rgoni','$1$hF67/Mkb$xiTZ0HrlMBJu/BzMlhqMN.','MD5','518','orozco','J. Ramon Goni','/home/rgoni','/bin/bash'),
 ('00471','agnes','ze0DER72O71BM','DES','520','orozco','Agnes Noy','/home/agnes','/bin/bash'),
 ('00508','ivanp','$1$G7PdP8dS$uz/Eq6Ni0Yi6KR/U5JEie1','MD5','521','orozco','Ivan Parraga','/home/ivanp','/bin/bash'),
 ('99999','isanchez','*LK*','','522','orozco','Ivan Sanchez','/home/isanchez','/bin/bash'),
 ('00473','tmeyer','pMY6mu58xW2KU','DES','523','orozco','Tim Meyer','/home/tmeyer','/bin/bash'),
 ('00201','hospital','mJLS/UWDXHTWI','DES','525','orozco','Adam Hospital','/home/hospital','/bin/bash'),
 ('99999','juan','$1$ZeFDNJRu$PK/0Bz3P5.L64M2Q9VToM0','MD5','526','orozco','Juan Fernandez Recio','/home/juan','/bin/bash'),
 ('00089','aperez','LkjQ1P0/jjYZw','DES','529','orozco','Alberto Perez','/home/aperez','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('99999','charlie','*LK*','','531','orozco','Laughton Charles','/home/charlie','/bin/bash'),
 ('99999','antonio','*LK*','','536','orozco','Antonio Morreale','/home/antonio','/bin/bash'),
 ('00262','oliver','$1$GxPxCtLf$I33FT1SiyQvjILCqfvGk00','MD5','538','orozco','Oliver Carrillo Parramon','/home/oliver','/bin/bash'),
 ('99999','ivan','*LK*','','540','orozco','Ivan Marchan','/home/ivan','/bin/bash'),
 ('99999','pilar','*LK*$1$BbzZaKnC$YS9OCqeMS4ZzxAxCMjTDP0','','545','orozco','Pilar de Miguel Ortega','/home/pilar','/bin/bash'),
 ('00472','davidp','$1$Pe9T.mb7$yebVy9gb3Sfb1WozoG.Ow0','MD5','548','orozco','David Piedra','/home/davidp','/bin/bash'),
 ('99999','abidon','MWrLpS5Y5JLhs','DES','550','orozco','Axel Bidon','/home/abidon','/bin/bash'),
 ('99999','ignacio','$1$OHcJFlW1$SaNj3x3B8rYWDKRWiTiKF.','MD5','552','orozco','Ignacio Soteras','/home/ignacio','/bin/bash'),
 ('00302','sergi','$1$3/pFSme1$st3S7Lp/2yIfK456QibeY1','MD5','553','orozco','Sergi Lois','/home/sergi','/bin/bash'),
 ('99999','acrespo','$1$3X3S9tPP$zQFWB3e6uk99madwesLsd.','MD5','559','orozco','Alejandro Crespo','/home/acrespo','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('99999','toni','*LK*','','563','orozco','Antoni Valencia','/home/toni','/bin/bash'),
 ('99999','jcamps','3AyI351mlK2Ic','DES','570','orozco','Jordi Camps','/home/jcamps','/bin/bash'),
 ('99999','albert','$1$9Hyron0M$oB0zPAEQOX5NbLnn9x.fm/','MD5','577','orozco','Albert Salichs','/home/albert','/bin/bash'),
 ('99999','judith','*LK*','','578','orozco','Noname Nofname','/home/judith','/bin/bash'),
 ('99999','grosdidier','$1$evMivz8r$cr0r1kqRob4H7.mf331wd1','MD5','580','orozco','Solene Grosdidier','/home/grosdidier','/bin/bash'),
 ('99999','damian','$1$LnvRCOSE$9MgB60meHByTDR1jssJNR.','MD5','581','orozco','Damian Flaneo','/home/damian','/bin/bash'),
 ('99999','orey','$1$qwFHqjTz$OtL5CPYNwYPd.SI46dvlq/','MD5','582','orozco','Oscar Rey Puiggros','/home/orey','/bin/bash'),
 ('99999','lucie','$1$DupKddyS$2TP5tkqCaN.G3xtMsb98a/','MD5','584','orozco','Lucie Rivail','/home/lucie','/bin/bash'),
 ('99999','neva','$1$/GkrAbRE$JeO96/8..jGLY3H.C9rpn1','MD5','585','orozco','Nera Besker','/home/neva','/bin/bash'),
 ('00305','marco','$1$I8NQqFdG$7pfIwrsBE0qotyXAgUXXh0','MD5','586','orozco','Marco D\'Abramo','/home/marco','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('99999','kontoyianni','Ly4MUQehOU3n.','DES','1004','orozco','Maria Kontoyianni','/home/kontoyianni','/bin/bash'),
 ('99999','tammy','*LK*','','1016','orozco','Noname Nofname','/home/tammy','/bin/bash'),
 ('99999','javi','*LK*','','1017','orozco','Noname Nofname','/home/javi','/bin/bash'),
 ('99999','dgarcia','*LK*$1$jCIMo8jh$Mrg/7IeHEdmSZjpuCIQF81','','1018','orozco','David Garcia','/home/dgarcia','/bin/bash'),
 ('00470','agusti','$1$SpGC32VP$umki1NY82/p06B30UNR.S.','MD5','1020','orozco','Agusti Emperador Badimon','/home/agusti','/bin/bash'),
 ('99999','jseco','$1$hDo6BdCv$R..JqJuFGqoyTZVq5dkzD1','MD5','1024','orozco','Jesus Seco','/home/jseco','/bin/bash'),
 ('00546','laura','$1$9ovS/uGd$2eCy5mbrg.eNqxhLk9h.l0','MD5','1027','orozco','Laura Perez','/home/laura','/bin/bash'),
 ('99999','mathias','*LK*','','1039','orozco','Noname Nofname','/home/mathias','/bin/bash'),
 ('99999','anare','*LK*','','1043','orozco','Noname Nofname','/home/anare','/bin/bash'),
 ('99999','mnicolas','*LK*','','1048','orozco','Marc Nicolas','/home/mnicolas','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('00169','lorell','$1$HL/cm6a/$cMpNU4SiwWV2rOVTlSI130','MD5','1049','orozco','Laura Orellana','/home/lorell','/bin/bash'),
 ('99999','mousumi','$1$x8qOcytl$JJ8fHI/MCj.aXW6GotUxN0','MD5','1050','orozco','Mousumi Bhattacharyya','/home/mousumi','/bin/bash'),
 ('00202','jmorata','$1$ygRxgd1F$ef0npRvw9Us1T4JyVsPFU.','MD5','1051','orozco','Jordi Morata','/home/jmorata','/bin/bash'),
 ('99999','ahernandez','$1$KYtE1yjg$wL4kZkGrW2XIwfmJAQPk9.','MD5','1052','orozco','Albert Hernandez','/home/ahernandez','/bin/bash'),
 ('99999','mvittoria','$1$K1jLyFbf$0zs3bq6CiNs3Eldugday//','MD5','1053','orozco','Maria Vittoria','/home/mvittoria','/bin/bash'),
 ('99999','mbarbany','$1$ZysTs0QF$0aP6SK3T3fxQIkrhBla6l/','MD5','1054','orozco','Montse Barbany','/home/mbarbany','/bin/bash'),
 ('99999','malbarracin','*LK*','','1057','orozco','Maria Albarracin','/home/malbarracin','/bin/bash'),
 ('00545','carlesfe','FKIAcoxS/gwOE','DES','1058','orozco','Carles Fenollosa','/home/carlesfe','/bin/bash'),
 ('00306','mireia','b79o/ZHnRFKQ2','DES','1059','orozco','Mireia Ruiz Rodriguez','/home/mireia','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('00267','annalisa','aUD6rbGpTZHm.','DES','1060','orozco','Annalisa Arcella','/home/annalisa','/bin/bash'),
 ('99999','campa','5gur/KhX5uaVk','DES','1061','orozco','Josep Maria Campanera','/home/campa','/bin/bash'),
 ('99999','torrents','*LK*$1$Ps2psG.j$NR65bYKVWei6.QLgONKSt/','','1067','orozco','David Torrents','/home/torrents','/bin/bash'),
 ('99999','cpons','$1$2QRH6Ki9$Sf4WexQGEmcnG7cWAJ.9q.','MD5','1068','orozco','Carles Pons','/home/cpons','/bin/bash'),
 ('99999','jesus','*LK*$1$h50UDSy8$aNMU4lsnkTEeIAoZ70Ssk.','','1069','orozco','Jesus Lopez','/home/jesus','/bin/bash'),
 ('99999','jiali','*LK*','','1070','orozco','Noname Nofname','/home/jiali','/bin/bash'),
 ('99999','dfernandez','*LK*','','1071','orozco','Daniel Fernandez','/home/dfernandez','/bin/bash'),
 ('00474','jalcantara','/0JLAJXaTE3jc','DES','10001','orozco','Jose Antonio Alcantara Ruiz','/home/jalcantara','/bin/bash'),
 ('00537','apaladino','96yWOyLBhzfK6','DES','10002','orozco','Antonella Paladino','/home/apaladino','/bin/bash'),
 ('99999','rsoliva','*LK*VnmCC2yRAAtJ2','','504','uriach','Roberto Soliva','/home/rsoliva','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('99999','cferrer','$1$fJs6coFS$dyj26OIFqMOh0jusfNzbg.','MD5','1045','uriach','Carles Ferrer','/home/cferrer','/bin/bash'),
 ('00428','paloy','/m/dzDJZov.nY','DES','1005','paloy','Patrick Aloy','/home/paloy','/bin/bash'),
 ('99999','apanjkovich','SlIKEametrneQ','DES','1006','paloy','Alejandro Panjkovich','/home/apanjkovich','/bin/tcsh'),
 ('00504','astein','CzDF3P6Q.DRVM','DES','1011','paloy','Amelie Stein','/home/astein','/bin/bash'),
 ('00505','rpache','f2JgczTDnF4yQ','DES','1012','paloy','Roland Pache','/home/rpache','/bin/bash'),
 ('00503','azanzoni','xRgO0X9NjqqQ6','DES','1015','paloy','Andreas Zanzoni','/home/azanzoni','/bin/bash'),
 ('00570','mbecker','RBhVJttUBqqG2','DES','1083','paloy','Max Becker','/home/mbecker','/bin/bash'),
 ('00029','flozano','GbRS5rDtLi/pU','DES','1007','its','Francisco Lozano','/home/flozano','/bin/bash'),
 ('00030','dvillanueva','eu6FkBebpcZ7I','DES','1008','its','David Villanueva','/home/dvillanueva','/bin/bash'),
 ('00028','rbartolome','d3e1lPkYdNgII','DES','1025','its','Roberto Bartolome','/home/rbartolome','/bin/bash'),
 ('00289','sanchez','aY5SB4r2JACD.','DES','1042','its','Jesus Sanchez','/home/sanchez','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('00078','mcastro','zgPesKtKv2e4g','DES','1009','ribas','Manuel Castro','/home/mcastro','/bin/bash'),
 ('99999','ibelda','VWOBMIcqxMroA','DES','1021','giralt','Ignasi Belda','/home/ibelda','/bin/bash'),
 ('00343','smadurga','3TXMSQMHgG83k','DES','1022','giralt','Sergio Madurga','/home/smadurga','/bin/bash'),
 ('99999','sherwood','*LK*','','1028','ocer','Sarah Sherwood','/home/sherwood','/bin/bash'),
 ('99999','armengou','*LK*','','1029','ocer','Sonia Armengou','/home/armengou','/bin/bash'),
 ('99999','mgavalda','*LK*','','1030','ocer','Meritxell Gavalda','/home/mgavalda','/bin/bash'),
 ('99999','alsina','*LK*','','1062','ocer','Anna Alsina','/home/alsina','/bin/bash'),
 ('99999','yates','*LK*','','1072','ocer','Tanya Yates','/home/yates','/bin/bash'),
 ('99999','martinez','*LK*','','1035','hr','Sylvia Martinez','/home/martinez','/bin/bash'),
 ('99999','aguade','*LK*','','1036','hr','Silvia Aguade','/home/aguade','/bin/bash'),
 ('99999','rovira','*LK*','','1081','hr','Maria Rovira','/home/rovira','/bin/bash'),
 ('99999','ramirez','*LK*','','1031','finance','Silvia Ramirez','/home/ramirez','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('99999','lopez','*LK*','','1032','finance','Sara Lopez','/home/lopez','/bin/bash'),
 ('99999','coarasa','*LK*','','1034','finance','Carles Coarasa','/home/coarasa','/bin/bash'),
 ('99999','serra','*LK*','','1074','finance','Stella Serra','/home/serra','/bin/bash'),
 ('99999','xlopez','*LK*','','1078','finance','Xavier Lopez','/home/xlopez','/bin/bash'),
 ('99999','mjlopez','*LK*','','1079','finance','Maria Jesus Lopez','/home/mjlopez','/bin/bash'),
 ('99999','delahoz','*LK*','','1080','finance','Elisava De la Hoz','/home/delahoz','/bin/bash'),
 ('99999','ccoletas','*LK*','','1093','finance','Cristina Coletas','/home/ccoletas','/bin/bash'),
 ('99999','navia','*LK*','','1033','raa','Margarita Navia','/home/navia','/bin/bash'),
 ('99999','caminal','*LK*','','1044','raa','Clara Caminal','/home/caminal','/bin/bash'),
 ('99999','estevez','*LK*','','1046','directorate','Maria Estevez','/home/estevez','/bin/bash'),
 ('99999','corominas','*LK*','','1073','directorate','Margarida Corominas','/home/corominas','/bin/bash'),
 ('00017','guinovart','x3hBFP0r8eYB.','DES','1077','directorate','Joan J. Guinovart Cirera','/home/guinovart','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('00454','auer','4N9uKqzfltuEQ','DES','1064','genomics','Herbert Auer','/home/auer','/bin/bash'),
 ('00456','rodriguez','JAaQOL.HnZf1E','DES','1065','genomics','Silvia Rodriguez','/home/rodriguez','/bin/bash'),
 ('99999','beltran','AOS3W32UJRuPg','DES','1066','genomics','Sergi Beltran','/home/beltran','/bin/bash'),
 ('99999','salvatella','UaT/steeOoxdY','DES','1075','salvatella','Xavier Salvatella','/home/salvatella','/bin/bash'),
 ('99999','fenwick','hwb0QsFFyNS6Y','DES','1086','salvatella','Robert Fenwick','/home/fenwick','/bin/bash'),
 ('99999','martin','*LK*','','1076','macias','Pau Martin','/home/martin','/bin/bash'),
 ('00365','subiros','.kX1zLZRjnEFw','DES','1082','albericio','Ramon Subiros','/home/subiros','/bin/bash'),
 ('00468','vega','08XUpa6D0sCLc','DES','1084','coll','Cristina Vega','/home/vega','/bin/bash'),
 ('00141','ffernandez','tmDmSQIcoZZ1w','DES','1085','coll','Francisco Fernandez','/home/ffernandez','/bin/bash'),
 ('00425','rgomis','*LK*','','1088','metlab','Roger Gomis','/home/rgomis','/bin/bash'),
 ('99999','smartorell','*LK*','','1089','metlab','Sara Martorell','/home/smartorell','/bin/bash');
INSERT INTO `irbpeople_nis` VALUES  ('99999','mguiu','*LK*','','1090','metlab','Marc Guiu','/home/mguiu','/bin/bash'),
 ('99999','efernandez','*LK*','','1091','metlab','Esther Fernandez','/home/efernandez','/bin/bash'),
 ('99999','drossell','*LK*','','1092','biostats','David Rossell','/home/drossell','/bin/bash');
/*!40000 ALTER TABLE `irbpeople_nis` ENABLE KEYS */;


--
-- Definition of table `irbpeople_nisgroups`
--

DROP TABLE IF EXISTS `irbpeople_nisgroups`;
CREATE TABLE `irbpeople_nisgroups` (
  `gid` varchar(5) NOT NULL,
  `group_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`gid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `irbpeople_nisgroups`
--

/*!40000 ALTER TABLE `irbpeople_nisgroups` DISABLE KEYS */;
INSERT INTO `irbpeople_nisgroups` VALUES  ('500','orozco'),
 ('510','uriach'),
 ('1003','paloy'),
 ('1004','its'),
 ('1005','ribas'),
 ('1007','giralt'),
 ('1008','ocer'),
 ('1009','hr'),
 ('1010','finance'),
 ('1011','raa'),
 ('1012','directorate'),
 ('1013','genomics'),
 ('1014','system'),
 ('1015','salvatella'),
 ('1016','macias'),
 ('1017','albericio'),
 ('1018','coll'),
 ('1019','metlab'),
 ('1020','biostats');
/*!40000 ALTER TABLE `irbpeople_nisgroups` ENABLE KEYS */;


--
-- Definition of table `language`
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE `language` (
  `languagecode` varchar(15) NOT NULL,
  `language` varchar(30) default NULL,
  PRIMARY KEY  (`languagecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `language`
--

/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES  ('ct','Catalan'),
 ('en','English'),
 ('es','Castellano');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;


--
-- Definition of table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `locationcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  `buildingcode` varchar(255) default NULL,
  PRIMARY KEY  (`locationcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location`
--

/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES  ('',64,0x01,'Ramón Eritja: Nucleic acid chemistry'),
 ('0000',1,0x01,'Laboratorio Dr. Fita'),
 ('0001',6,0x01,'Laboratotio Dr. Giralt'),
 ('D0001',9,0x01,'Despacho 1'),
 ('D0003',5,0x01,'Laboratorio Dr. Albericio'),
 ('dsgdgf',3,0x01,'Laboratorio Dr. Aloy'),
 ('Dweas',2,0x01,'Laboratorio Dr. Azorin'),
 ('L0001',2,0x01,'Laboratorio Dr. Batlle'),
 ('L0002',2,0x01,'Laboratorio Dra. Caelles'),
 ('L0003',2,0x01,'Laboratorio Dr. Casanova'),
 ('P1B1',48,0x00,'Administración'),
 ('P1B11/12',19,0x00,'Antoni Riera: Asymmetric synthesis'),
 ('P1B13/14',6,0x00,'Marius Rubiralta: Peptidomimetic and heterocycles'),
 ('P1B21/22/23/24',4,0x00,'Ernest Giralt: Peptides and proteins'),
 ('P1B22/23/24',1,0x00,'Fernando Albericio: Combinatorial chemistry'),
 ('PBA11/12/13',4,0x00,'Antonio Zorzano: Heterogenic and multigenic diseases'),
 ('PBA11/12/13/14',0,0x00,'Manuel Palacín: Heterogenic and multigenic diseases'),
 ('PBA21',6,0x00,'Maria J. Macias: Biomolecular NMR spectroscopy'),
 ('PBA23',12,0x00,'Eduard Batlle: Colorectal cancer laboratory I');
INSERT INTO `location` VALUES  ('PBA24',10,0x00,'Elena Sancho: Colorectal cancer laboratory II'),
 ('PBA3',6,0x00,'Ferran Azorín: Chromatin structure and function'),
 ('PBB11',3,0x00,'Carme Caelles: Cell signalling'),
 ('PBB12',9,0x00,'Ramón Eritja: Nucleic acid chemistry '),
 ('PBB13/14',25,0x00,'Joan J. Guinovart: Metabolic engineering and diabetes '),
 ('PBB21',16,0x00,'Marco Milan: Developmental biology of Drosophila'),
 ('PBB23/24',0,0x00,'Antonio Celada: Gene expression'),
 ('PBB2Pas',14,0x00,'Lluis Ribas de Pouplana: Gene translation laboratory'),
 ('PBB31',5,0x00,'Jordi Casanova: Morphogenesis in Drosophila'),
 ('PBB42',1,0x00,'Ignasi Fita: Macromolecular aggregates'),
 ('PBB44',0,0x00,'Miquel Coll: Proteins, nucleic acids and complexes'),
 ('PBB51/52',28,0x00,'MetLab: Growth control and cancer metastasis'),
 ('PBC22/23',7,0x00,'Miquel Pons: Biomolecular NMR'),
 ('PBC32/33/34',4,0x00,'Modesto Orozco: Molecular modelling and bioinformatics'),
 ('PSA12/13/14',0,0x00,'Eduardo Soriano: Neurobiology and regeneration'),
 ('PSB13/14',20,0x00,'Cayetano Gonzalez: Cell division laboratory');
INSERT INTO `location` VALUES  ('PSC14',0,0x00,'Core facilities: Genomics'),
 ('S0001',3,0x01,'Laboratorio Dr. Celada'),
 ('S0002',2,0x01,'Laboratorio Dr. Coll'),
 ('S1C32',0,0x00,'Patrick Aloy: Structural bioinformatics');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;


--
-- Definition of table `maintable`
--

DROP TABLE IF EXISTS `maintable`;
CREATE TABLE `maintable` (
  `tablename` varchar(100) NOT NULL,
  PRIMARY KEY  (`tablename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `maintable`
--

/*!40000 ALTER TABLE `maintable` DISABLE KEYS */;
/*!40000 ALTER TABLE `maintable` ENABLE KEYS */;


--
-- Definition of table `marital_status`
--

DROP TABLE IF EXISTS `marital_status`;
CREATE TABLE `marital_status` (
  `marital_statuscode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`marital_statuscode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `marital_status`
--

/*!40000 ALTER TABLE `marital_status` DISABLE KEYS */;
INSERT INTO `marital_status` VALUES  ('00001',98,0x00,'Soltero/a'),
 ('00002',15,0x00,'Casado/a'),
 ('00003',1,0x00,'Divorciado/a'),
 ('00004',0,0x00,'Viudo/a'),
 ('00005',0,0x00,'Separado/a'),
 ('00006',0,0x00,'Pareja de hecho'),
 ('00007',1,0x01,'');
/*!40000 ALTER TABLE `marital_status` ENABLE KEYS */;


--
-- Definition of table `nationality`
--

DROP TABLE IF EXISTS `nationality`;
CREATE TABLE `nationality` (
  `nationalitycode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  `country_region` varchar(45) default NULL,
  PRIMARY KEY  (`nationalitycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nationality`
--

/*!40000 ALTER TABLE `nationality` DISABLE KEYS */;
INSERT INTO `nationality` VALUES  ('ABW',0,0x00,'Aruba','00008'),
 ('AFG',0,0x00,'Afghanistan','00008'),
 ('AGO',0,0x00,'Angola','00008'),
 ('AIA',0,0x00,'Anguilla','00008'),
 ('ALA',0,0x00,'Åland Islands','00008'),
 ('ALB',0,0x00,'Albania','00008'),
 ('AND',0,0x00,'Andorra','00008'),
 ('ANT',0,0x00,'Netherlands Antilles','00008'),
 ('ARE',22,0x00,'United Arab Emirates','00008'),
 ('ARG',5,0x00,'Argentina','00008'),
 ('ARM',0,0x00,'Armenia','00008'),
 ('ASM',0,0x00,'American Samoa','00008'),
 ('ATA',0,0x00,'Antarctica','00008'),
 ('ATF',0,0x00,'French Southern Territories','00008'),
 ('ATG',0,0x00,'Antigua and Barbuda','00008'),
 ('AUS',0,0x00,'Australia','00008'),
 ('AUT',0,0x00,'Austria','00008'),
 ('AZE',0,0x00,'Azerbaijan','00008'),
 ('BDI',0,0x00,'Burundi','00008'),
 ('BEL',0,0x00,'Belgium','00008'),
 ('BEN',0,0x00,'Benin','00008'),
 ('BFA',0,0x00,'Burkina Faso','00008'),
 ('BGD',0,0x00,'Bangladesh','00008'),
 ('BGR',0,0x00,'Bulgaria','00008'),
 ('BHR',0,0x00,'Bahrain','00008'),
 ('BHS',0,0x00,'Bahamas','00008');
INSERT INTO `nationality` VALUES  ('BIH',0,0x00,'Bosnia and Herzegovina','00008'),
 ('BLR',0,0x00,'Belarus','00008'),
 ('BLZ',0,0x00,'Belize','00008'),
 ('BMU',0,0x00,'Bermuda','00008'),
 ('BOL',0,0x00,'Bolivia','00008'),
 ('BRA',0,0x00,'Brazil','00008'),
 ('BRB',0,0x00,'Barbados','00008'),
 ('BRN',0,0x00,'Brunei Darussalam','00008'),
 ('BTN',0,0x00,'Bhutan','00008'),
 ('BVT',0,0x00,'Bouvet Island','00008'),
 ('BWA',0,0x00,'Botswana','00008'),
 ('CAF',0,0x00,'Central African Republic','00008'),
 ('CAN',1,0x00,'Canada','00008'),
 ('CCK',0,0x00,'Cocos (Keeling) Islands','00008'),
 ('CHE',0,0x00,'Switzerland','00008'),
 ('CHL',0,0x00,'Chile','00008'),
 ('CHN',2,0x00,'China','00008'),
 ('CIV',0,0x00,'Côte d\'Ivoire','00008'),
 ('CMR',0,0x00,'Cameroon','00008'),
 ('COD',0,0x00,'Congo, the Democratic Republic of the','00008'),
 ('COG',0,0x00,'Congo','00008'),
 ('COK',0,0x00,'Cook Islands','00008'),
 ('COL',4,0x00,'Colombia','00008'),
 ('COM',0,0x00,'Comoros','00008'),
 ('CPV',0,0x00,'Cape Verde','00008'),
 ('CRI',0,0x00,'Costa Rica','00008');
INSERT INTO `nationality` VALUES  ('CUB',0,0x00,'Cuba','00008'),
 ('CXR',0,0x00,'Christmas Island','00008'),
 ('CYM',0,0x00,'Cayman Islands','00008'),
 ('CYP',0,0x00,'Cyprus','00008'),
 ('CZE',0,0x00,'Czech Republic','00008'),
 ('DEU',16,0x00,'Germany','00008'),
 ('DJI',0,0x00,'Djibouti','00008'),
 ('DMA',0,0x00,'Dominica','00008'),
 ('DNK',1,0x00,'Denmark','00008'),
 ('DOM',0,0x00,'Dominican Republic','00008'),
 ('DZA',0,0x00,'Algeria','00008'),
 ('ECU',0,0x00,'Ecuador','00008'),
 ('EGY',0,0x00,'Egypt','00008'),
 ('ERI',0,0x00,'Eritrea','00008'),
 ('ESH',0,0x00,'Western Sahara','00008'),
 ('ESP',351,0x00,'Spain','00008'),
 ('EST',0,0x00,'Estonia','00008'),
 ('ETH',0,0x00,'Ethiopia','00008'),
 ('FIN',0,0x00,'Finland','00008'),
 ('FJI',0,0x00,'Fiji','00008'),
 ('FLK',0,0x00,'Falkland Islands (Malvinas)','00008'),
 ('FRA',54,0x00,'France','00008'),
 ('FRO',0,0x00,'Faroe Islands','00008'),
 ('FSM',0,0x00,'Micronesia, Federated States of','00008'),
 ('GAB',0,0x00,'Gabon','00008'),
 ('GBR',10,0x00,'United Kingdom','00008'),
 ('GEO',0,0x00,'Georgia','00008');
INSERT INTO `nationality` VALUES  ('GGY',0,0x00,'Guernsey','00008'),
 ('GHA',0,0x00,'Ghana','00008'),
 ('GIB',0,0x00,'Gibraltar','00008'),
 ('GIN',0,0x00,'Guinea','00008'),
 ('GLP',0,0x00,'Guadeloupe','00008'),
 ('GMB',0,0x00,'Gambia','00008'),
 ('GNB',0,0x00,'Guinea-Bissau','00008'),
 ('GNQ',0,0x00,'Equatorial Guinea','00008'),
 ('GRC',0,0x00,'Greece','00008'),
 ('GRD',0,0x00,'Grenada','00008'),
 ('GRL',0,0x00,'Greenland','00008'),
 ('GTM',0,0x00,'Guatemala','00008'),
 ('GUF',0,0x00,'French Guiana','00008'),
 ('GUM',0,0x00,'Guam','00008'),
 ('GUY',0,0x00,'Guyana','00008'),
 ('HKG',0,0x00,'Hong Kong','00008'),
 ('HMD',0,0x00,'Heard Island and McDonald Islands','00008'),
 ('HND',0,0x00,'Honduras','00008'),
 ('HRV',0,0x00,'Croatia','00008'),
 ('HTI',0,0x00,'Haiti','00008'),
 ('HUN',0,0x00,'Hungary','00008'),
 ('IDN',0,0x00,'Indonesia','00008'),
 ('IMN',0,0x00,'Isle of Man','00008'),
 ('IND',0,0x00,'India','00008'),
 ('IOT',0,0x00,'British Indian Ocean Territory','00008'),
 ('IRL',1,0x00,'Ireland','00008'),
 ('IRN',0,0x00,'Iran, Islamic Republic of','00008');
INSERT INTO `nationality` VALUES  ('IRQ',0,0x00,'Iraq','00008'),
 ('ISL',0,0x00,'Iceland','00008'),
 ('ISR',8,0x00,'Israel','00008'),
 ('ITA',2,0x00,'Italy','00008'),
 ('JAM',0,0x00,'Jamaica','00008'),
 ('JEY',0,0x00,'Jersey','00008'),
 ('JOR',0,0x00,'Jordan','00008'),
 ('JPN',0,0x00,'Japan','00008'),
 ('KAZ',0,0x00,'Kazakhstan','00008'),
 ('KEN',0,0x00,'Kenya','00008'),
 ('KGZ',0,0x00,'Kyrgyzstan','00008'),
 ('KHM',0,0x00,'Cambodia','00008'),
 ('KIR',0,0x00,'Kiribati','00008'),
 ('KNA',0,0x00,'Saint Kitts and Nevis','00008'),
 ('KOR',0,0x00,'Korea, Republic of','00008'),
 ('KWT',0,0x00,'Kuwait','00008'),
 ('LAO',0,0x00,'Lao People\'s Democratic Republic','00008'),
 ('LBN',0,0x00,'Lebanon','00008'),
 ('LBR',0,0x00,'Liberia','00008'),
 ('LBY',0,0x00,'Libyan Arab Jamahiriya','00008'),
 ('LCA',0,0x00,'Saint Lucia','00008'),
 ('LIE',0,0x00,'Liechtenstein','00008'),
 ('LKA',0,0x00,'Sri Lanka','00008'),
 ('LSO',0,0x00,'Lesotho','00008'),
 ('LTU',0,0x00,'Lithuania','00008'),
 ('LUX',0,0x00,'Luxembourg','00008'),
 ('LVA',0,0x00,'Latvia','00008');
INSERT INTO `nationality` VALUES  ('MAC',0,0x00,'Macao','00008'),
 ('MAR',0,0x00,'Morocco','00008'),
 ('MCO',0,0x00,'Monaco','00008'),
 ('MDA',0,0x00,'Moldova, Republic of','00008'),
 ('MDG',0,0x00,'Madagascar','00008'),
 ('MDV',0,0x00,'Maldives','00008'),
 ('MEX',4,0x00,'Mexico','00008'),
 ('MHL',0,0x00,'Marshall Islands','00008'),
 ('MKD',0,0x00,'Macedonia, the former Yugoslav Republic of','00008'),
 ('MLI',0,0x00,'Mali','00008'),
 ('MLT',0,0x00,'Malta','00008'),
 ('MMR',0,0x00,'Myanmar','00008'),
 ('MNE',0,0x00,'Montenegro','00008'),
 ('MNG',0,0x00,'Mongolia','00008'),
 ('MNP',0,0x00,'Northern Mariana Islands','00008'),
 ('MOZ',0,0x00,'Mozambique','00008'),
 ('MRT',0,0x00,'Mauritania','00008'),
 ('MSR',0,0x00,'Montserrat','00008'),
 ('MTQ',0,0x00,'Martinique','00008'),
 ('MUS',0,0x00,'Mauritius','00008'),
 ('MWI',0,0x00,'Malawi','00008'),
 ('MYS',0,0x00,'Malaysia','00008'),
 ('MYT',0,0x00,'Mayotte','00008'),
 ('NAM',0,0x00,'Namibia','00008'),
 ('NCL',0,0x00,'New Caledonia','00008'),
 ('NER',0,0x00,'Niger','00008');
INSERT INTO `nationality` VALUES  ('NFK',0,0x00,'Norfolk Island','00008'),
 ('NGA',0,0x00,'Nigeria','00008'),
 ('NIC',0,0x00,'Nicaragua','00008'),
 ('NIU',0,0x00,'Niue','00008'),
 ('NLD',0,0x00,'Netherlands','00008'),
 ('NOR',0,0x00,'Norway','00008'),
 ('NPL',0,0x00,'Nepal','00008'),
 ('NRU',0,0x00,'Nauru','00008'),
 ('NZL',0,0x00,'New Zealand','00008'),
 ('OMN',0,0x00,'Oman','00008'),
 ('PAK',0,0x00,'Pakistan','00008'),
 ('PAN',0,0x00,'Panama','00008'),
 ('PCN',0,0x00,'Pitcairn','00008'),
 ('PER',0,0x00,'Peru','00008'),
 ('PHL',0,0x00,'Philippines','00008'),
 ('PLW',0,0x00,'Palau','00008'),
 ('PNG',0,0x00,'Papua New Guinea','00008'),
 ('POL',0,0x00,'Poland','00008'),
 ('PRI',0,0x00,'Puerto Rico','00008'),
 ('PRK',0,0x00,'Korea, Democratic People\'s Republic of','00008'),
 ('PRT',3,0x00,'Portugal','00008'),
 ('PRY',0,0x00,'Paraguay','00008'),
 ('PSE',0,0x00,'Palestinian Territory, Occupied','00008'),
 ('PYF',0,0x00,'French Polynesia','00008'),
 ('QAT',0,0x00,'Qatar','00008'),
 ('REU',0,0x00,'Réunion','00008'),
 ('ROU',0,0x00,'Romania','00008');
INSERT INTO `nationality` VALUES  ('RUS',0,0x00,'Russian Federation','00008'),
 ('RWA',0,0x00,'Rwanda','00008'),
 ('SAU',0,0x00,'Saudi Arabia','00008'),
 ('SDN',0,0x00,'Sudan','00008'),
 ('SEN',0,0x00,'Senegal','00008'),
 ('SGP',0,0x00,'Singapore','00008'),
 ('SGS',0,0x00,'South Georgia and the South Sandwich Islands','00008'),
 ('SHN',0,0x00,'Saint Helena','00008'),
 ('SJM',0,0x00,'Svalbard and Jan Mayen','00008'),
 ('SLB',0,0x00,'Solomon Islands','00008'),
 ('SLE',0,0x00,'Sierra Leone','00008'),
 ('SLV',0,0x00,'El Salvador','00008'),
 ('SMR',0,0x00,'San Marino','00008'),
 ('SOM',0,0x00,'Somalia','00008'),
 ('SPM',0,0x00,'Saint Pierre and Miquelon','00008'),
 ('SRB',0,0x00,'Serbia','00008'),
 ('STP',0,0x00,'Sao Tome and Principe','00008'),
 ('SUR',0,0x00,'Suriname','00008'),
 ('SVK',0,0x00,'Slovakia','00008'),
 ('SVN',0,0x00,'Slovenia','00008'),
 ('SWE',0,0x00,'Sweden','00008'),
 ('SWZ',0,0x00,'Swaziland','00008'),
 ('SYC',1,0x00,'Seychelles','00008'),
 ('SYR',0,0x00,'Syrian Arab Republic','00008'),
 ('TCA',0,0x00,'Turks and Caicos Islands','00008');
INSERT INTO `nationality` VALUES  ('TCD',0,0x00,'Chad','00008'),
 ('TGO',0,0x00,'Togo','00008'),
 ('THA',0,0x00,'Thailand','00008'),
 ('TJK',0,0x00,'Tajikistan','00008'),
 ('TKL',1,0x00,'Tokelau','00008'),
 ('TKM',0,0x00,'Turkmenistan','00008'),
 ('TLS',0,0x00,'Timor-Leste','00008'),
 ('TON',1,0x00,'Tonga','00008'),
 ('TTO',0,0x00,'Trinidad and Tobago','00008'),
 ('TUN',0,0x00,'Tunisia','00008'),
 ('TUR',0,0x00,'Turkey','00008'),
 ('TUV',0,0x00,'Tuvalu','00008'),
 ('TWN',0,0x00,'Taiwan, Province of China','00008'),
 ('TZA',0,0x00,'Tanzania, United Republic of','00008'),
 ('UGA',12,0x00,'Uganda','00008'),
 ('UKR',3,0x00,'Ukraine','00008'),
 ('UMI',1,0x00,'United States Minor Outlying Islands','00008'),
 ('URY',4,0x00,'Uruguay','00008'),
 ('USA',5,0x00,'United States','00008'),
 ('UZB',0,0x00,'Uzbekistan','00008'),
 ('VAT',2,0x00,'Holy See (Vatican City State)','00008'),
 ('VCT',0,0x00,'Saint Vincent and the Grenadines','00008'),
 ('VEN',2,0x00,'Venezuela','00008'),
 ('VGB',0,0x00,'Virgin Islands, British','00008'),
 ('VIR',0,0x00,'Virgin Islands, U.S.','00008');
INSERT INTO `nationality` VALUES  ('VNM',0,0x00,'Viet Nam','00008'),
 ('VUT',1,0x00,'Vanuatu','00008'),
 ('WLF',0,0x00,'Wallis and Futuna','00008'),
 ('WSM',0,0x00,'Samoa','00008'),
 ('YEM',0,0x00,'Yemen','00008'),
 ('ZAF',0,0x00,'South Africa','00008'),
 ('ZMB',0,0x00,'Zambia','00008'),
 ('ZWE',0,0x00,'Zimbabwe','00008');
/*!40000 ALTER TABLE `nationality` ENABLE KEYS */;


--
-- Definition of table `organization_unit`
--

DROP TABLE IF EXISTS `organization_unit`;
CREATE TABLE `organization_unit` (
  `organization_unitcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`organization_unitcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `organization_unit`
--

/*!40000 ALTER TABLE `organization_unit` DISABLE KEYS */;
INSERT INTO `organization_unit` VALUES  ('00001',1,0x00,'Research Programmes'),
 ('00002',1,0x00,'Core Facilities'),
 ('00003',2,0x00,'Administration'),
 ('00004',1,0x00,'Directorate');
/*!40000 ALTER TABLE `organization_unit` ENABLE KEYS */;


--
-- Definition of table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `paymentcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`paymentcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payment`
--

/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES  ('00001',19,0x00,'12 pagas'),
 ('00002',100,0x00,'14 pagas');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


--
-- Definition of table `payroll_institution`
--

DROP TABLE IF EXISTS `payroll_institution`;
CREATE TABLE `payroll_institution` (
  `payroll_institutioncode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`payroll_institutioncode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payroll_institution`
--

/*!40000 ALTER TABLE `payroll_institution` DISABLE KEYS */;
INSERT INTO `payroll_institution` VALUES  ('00001',136,0x00,'IRB'),
 ('00002',2,0x00,'PCB'),
 ('00003',4,0x00,'UB'),
 ('00004',8,0x00,'ICREA'),
 ('00005',47,0x00,'MEC'),
 ('00006',27,0x00,'AGAUR'),
 ('00007',12,0x00,'CSIC'),
 ('00008',1,0x00,'FBG - Fundació Bosch i Gimpera'),
 ('00009',19,0x00,'CIBERER'),
 ('00010',1,0x01,'');
/*!40000 ALTER TABLE `payroll_institution` ENABLE KEYS */;


--
-- Definition of table `permission`
--

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permissioncode` varchar(20) NOT NULL,
  `permissions` int(11) NOT NULL,
  `tablename` varchar(100) NOT NULL,
  `typeinstancecode` varchar(20) default NULL,
  `entitycode` varchar(100) NOT NULL,
  PRIMARY KEY  (`permissioncode`),
  KEY `FKE125C5CFD0F0BEA8` (`typeinstancecode`),
  KEY `FKE125C5CFA2B4A8DC` (`entitycode`),
  KEY `FKE125C5CF75149191` (`tablename`),
  CONSTRAINT `FKE125C5CF75149191` FOREIGN KEY (`tablename`) REFERENCES `maintable` (`tablename`),
  CONSTRAINT `FKE125C5CFA2B4A8DC` FOREIGN KEY (`entitycode`) REFERENCES `entity` (`entitycode`),
  CONSTRAINT `FKE125C5CFD0F0BEA8` FOREIGN KEY (`typeinstancecode`) REFERENCES `typeinstance` (`typeinstancecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `permission`
--

/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


--
-- Definition of table `personal`
--

DROP TABLE IF EXISTS `personal`;
CREATE TABLE `personal` (
  `personalcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `name` varchar(100) default NULL,
  `surname` varchar(100) default NULL,
  `dni` varchar(100) default NULL,
  `birth_date` datetime default NULL,
  `birth_city` varchar(100) default NULL,
  `street` varchar(100) default NULL,
  `city` varchar(100) default NULL,
  `zip_code` varchar(100) default NULL,
  `phone` varchar(100) default NULL,
  `phone2` varchar(100) default NULL,
  `ice_phone` varchar(100) default NULL,
  `ss_number` varchar(100) default NULL,
  `bank_account` varchar(100) default NULL,
  `research_project` varchar(100) default NULL,
  `sponsoring_agency` varchar(100) default NULL,
  `holding_institution` varchar(100) default NULL,
  `principal_investigator` varchar(100) default NULL,
  `end_of_contract_reason` varchar(100) default NULL,
  `end_of_contract_address` varchar(100) default NULL,
  `end_of_contract_city` varchar(100) default NULL,
  `end_of_contract_zip_code` varchar(100) default NULL,
  `end_of_contract_phone` varchar(100) default NULL,
  `end_of_contract_email` varchar(100) default NULL,
  `birth_country` varchar(255) default NULL,
  `nationality` varchar(255) default NULL,
  `nationality_2` varchar(255) default NULL,
  `country` varchar(255) default NULL,
  `payments` varchar(255) default NULL,
  `end_of_contract_country` varchar(255) default NULL,
  `gender` varchar(255) default NULL,
  `marital_status` varchar(255) default NULL,
  `bank` varchar(255) default NULL,
  `working_hours` varchar(255) default NULL,
  `category` varchar(255) default NULL,
  `state` varchar(255) default NULL,
  `photo` varchar(255) default NULL,
  `surname1` varchar(100) default NULL,
  `surname2` varchar(100) default NULL,
  `validationCode` varchar(100) default NULL,
  `personal_email` varchar(255) default NULL,
  `language` varchar(15) default NULL,
  `username` varchar(45) default NULL,
  PRIMARY KEY  (`personalcode`),
  UNIQUE KEY `unique_username` (`username`),
  KEY `FK4910164036A71A52` (`gender`),
  KEY `FK49101640A3DE3548` (`bank`),
  KEY `FK49101640AFA0080A` (`marital_status`),
  KEY `FK4910164026059A32` (`working_hours`),
  KEY `FK49101640280E555B` (`nationality_2`),
  KEY `FK49101640577B00DC` (`birth_country`),
  KEY `FK4910164057A6BACC` (`category`),
  KEY `FK49101640CB51A663` (`payments`),
  KEY `FK4910164019FA145C` (`country`),
  KEY `FK491016406746C8A8` (`nationality`),
  KEY `FK491016404E600C12` (`state`),
  KEY `Index_14` (`photo`),
  CONSTRAINT `FK4910164019FA145C` FOREIGN KEY (`country`) REFERENCES `country` (`countrycode`),
  CONSTRAINT `FK4910164026059A32` FOREIGN KEY (`working_hours`) REFERENCES `working_hours` (`working_hourscode`),
  CONSTRAINT `FK49101640280E555B` FOREIGN KEY (`nationality_2`) REFERENCES `nationality` (`nationalitycode`),
  CONSTRAINT `FK4910164036A71A52` FOREIGN KEY (`gender`) REFERENCES `gender` (`gendercode`),
  CONSTRAINT `FK491016404E600C12` FOREIGN KEY (`state`) REFERENCES `personalstate` (`personalstatecode`),
  CONSTRAINT `FK49101640577B00DC` FOREIGN KEY (`birth_country`) REFERENCES `country` (`countrycode`),
  CONSTRAINT `FK4910164057A6BACC` FOREIGN KEY (`category`) REFERENCES `category` (`categorycode`),
  CONSTRAINT `FK491016406746C8A8` FOREIGN KEY (`nationality`) REFERENCES `nationality` (`nationalitycode`),
  CONSTRAINT `FK49101640A3DE3548` FOREIGN KEY (`bank`) REFERENCES `bank` (`bankcode`),
  CONSTRAINT `FK49101640AFA0080A` FOREIGN KEY (`marital_status`) REFERENCES `marital_status` (`marital_statuscode`),
  CONSTRAINT `FK49101640CB51A663` FOREIGN KEY (`payments`) REFERENCES `payment` (`paymentcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personal`
--

/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES  ('00001',663,0x00,'Nombre2','surname1 surname2','78541256S','1969-03-26 00:00:00','Barcelona','Avda. Josep Tarradellas 145 2º 4ª','Barcelona','08029','123456789','123456789','666666666','NASS','12345678900000000001','','dfdefd','fggfg','','aaasdfadsfa','fsfsfsf','sdffsefssdfasdf','897','79789','','ES','ESP','FRA','AX','00001','EH','00002','00001','','00001','00015','5','0000000000054','APELLIDO','SURNAME2',NULL,'','en','adria'),
 ('00002',190,0x00,'10','surname1 surname2','78596412A','1962-10-04 00:00:00','BARCELONA','Carrer Indibil, 6-10 - Entl4ª','BARCELONA','08004','123456789','123456789','666666666','NASS','12345678900000000001','','','','','motivo','address','ciudad','zipcode','749183749143','emaldj','ES','ESP',NULL,'ES','00002','GA','00002','00002','ES0081','00001','00019','6',NULL,'SURNAME1','SURNAME2',NULL,'','en','100002'),
 ('00003',9,0x00,'name 00003','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,'AS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00003'),
 ('00004',3,0x00,'name 00004','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,'AS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00004');
INSERT INTO `personal` VALUES  ('00005',157,0x00,'name 00005','surname1 surname2','47853698U','1991-03-13 00:00:00','Barcelona','daa','saf','79877','123456789','123456789','666666666','NASS','12345678900000000001','','','','','motivo','dir','ci','zip','17918347','email@a.com','UG','UGA','UGA','VE',NULL,'SH','00002',NULL,'',NULL,NULL,'6',NULL,'SUAASDFASDF','SURNAME2',NULL,'','es','n00005'),
 ('00006',9,0x00,'name 00006','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,'AX',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00006'),
 ('00007',5,0x00,'name 00007','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,'AX',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00007'),
 ('00008',2,0x00,'name 00008','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00008'),
 ('00009',2,0x00,'name 00009','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00009');
INSERT INTO `personal` VALUES  ('00010',5,0x00,'name 00010','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00010'),
 ('00011',2,0x00,'name 00011','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00011'),
 ('00012',2,0x00,'name 00012','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00012'),
 ('00013',2,0x00,'name 00013','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00013'),
 ('00014',2,0x00,'name 00014','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00014');
INSERT INTO `personal` VALUES  ('00015',2,0x00,'name 00015','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00015'),
 ('00016',2,0x00,'name 00016','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00016'),
 ('00018',2,0x00,'name 00018','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00018'),
 ('00019',2,0x00,'name 00019','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00019'),
 ('00020',2,0x00,'name 00020','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00020');
INSERT INTO `personal` VALUES  ('00021',8,0x00,'name 00021','surname1 surname2','DNI','1955-08-09 00:00:00','Lleida','Tarragona 106, 1, 1','Barcelona','08015','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','','ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00021'),
 ('00022',2,0x00,'name 00022','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00022'),
 ('00023',2,0x00,'name 00023','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00023'),
 ('00024',4,0x00,'name 00024','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00024'),
 ('00025',3,0x00,'name 00025','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00025');
INSERT INTO `personal` VALUES  ('00026',2,0x00,'name 00026','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00026'),
 ('00027',2,0x00,'name 00027','surname1 surname2','',NULL,'','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00027'),
 ('00028',157,0x00,'name 00028','surname1 surname2','DNI','1967-12-11 00:00:00','Vitoria','Av. Arquitecto Sert, 46','Bellaterra','08193','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','','ES','ESP',NULL,'ES','00002','US','00001','00001','ES2042','00001','00010','6',NULL,'surname1','surname2',NULL,NULL,'es','n00028'),
 ('00029',29,0x00,'name 00029','surname1 surname2','DNI','1968-11-11 00:00:00','Madrid','C/Anselm Clave 37','El Papiol','08754','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','','ES','ESP',NULL,'ES','00001','UA','00001','00002',NULL,'00001','00015','6',NULL,'surname1','surname2',NULL,NULL,'es','n00029'),
 ('00030',37,0x00,'name 00030','surname1 surname2','DNI','1973-04-18 00:00:00','Barcelona','Ctra. Sant Cugat, 81-91','Barcelona','08035','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00030');
INSERT INTO `personal` VALUES  ('00031',34,0x00,'name 00031','surname1 surname2','DNI','1969-09-06 00:00:00','Terrassa','c/ Lepanto 257 esc F 3º3ª','Terrassa','08224','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','','ES','ESP',NULL,'ES','00002','UM','00001','00001','ES2074','00001','00015','6',NULL,'surname1','surname2',NULL,NULL,'es','n00031'),
 ('00032',18,0x00,'name 00032','surname1 surname2','DNI','1976-01-20 00:00:00','Barcelona','Doctor Reig, 78 Àtic','Viladecans','08840','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,'','','','','','','ES','ESP',NULL,'ES',NULL,'GB','00002',NULL,NULL,NULL,NULL,'6',NULL,'surname1','surname2',NULL,NULL,'es','n00032'),
 ('00033',12,0x00,'name 00033','surname1 surname2','DNI','2007-09-19 00:00:00','BARCELONA','C/PRAT DE LA MANTA 61, 3-2','HOSPITALET DE LLOBREGAT','08902','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00033'),
 ('00034',15,0x00,'name 00034','surname1 surname2','DNI','1977-06-15 00:00:00','La Orotava','Villarroel, 182 3º4ª','Barcelona','08036','123456789','123456789','666666666','NASS','12345678900000000001','Botin','Fundacion M Botín','IRB','J Guinovart','','','','','','','ES','ESP',NULL,'ES',NULL,'VG','00001',NULL,'',NULL,NULL,'6',NULL,'SURNAME1','SURNAME2',NULL,'enrique.cano@justinmind.com','es','n00034');
INSERT INTO `personal` VALUES  ('00035',5,0x00,'name 00035','surname1 surname2','DNI','1980-10-26 00:00:00','Maó','Nicaragua 57 ent. 2ª','Barcelona','08029','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00035'),
 ('00036',27,0x00,'name 00036','surname1 surname2','DNI','1972-01-05 00:00:00','Barcelona','Cervantes Nº5','Sant Andreu de la Barca','08740','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00036'),
 ('00037',7,0x00,'name 00037','surname1 surname2','DNI','1959-04-17 00:00:00','Barcelona','Pi i Margall, 151 2º2ª Esc. A','Sant Boi de Llobregat','08830','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00037'),
 ('00038',9,0x00,'name 00038','surname1 surname2','DNI','1981-10-05 00:00:00','Barcelona','Plaça Lesseps 8, SobreÀtic','Barcelona','08023','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00038');
INSERT INTO `personal` VALUES  ('00039',6,0x00,'name 00039','surname1 surname2','DNI','1971-08-29 00:00:00','Barcelona','Passatge Tona, 8','Barcelona','08023','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00039'),
 ('00040',12,0x00,'name 00040','surname1 surname2','DNI','1963-08-09 00:00:00','Las Palmas de Gran Canaria','Esteve terradas 51-55 esc. A. 4º-4ª','Barcelona','08023','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00040'),
 ('00041',10,0x00,'name 00041','surname1 surname2','DNI','1978-11-04 00:00:00','Barcelona','c/ Marina 63, 3 2','Barcelona','08005','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP','ESP','ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00041'),
 ('00042',21,0x00,'name 00042','surname1 surname2','DNI','1963-11-14 00:00:00','Caracas','Plaça Tirant lo Blanc 3 1-1','Barcelona','08005','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'VE','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00042');
INSERT INTO `personal` VALUES  ('00043',6,0x00,'name 00043','surname1 surname2','DNI','1971-02-18 00:00:00','London','c/ Anselm Clavé 37','El Papiol','08028','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'GB','GBR','CAN','ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00043'),
 ('00044',12,0x00,'name 00044','surname1 surname2','DNI','1968-12-16 00:00:00','México, D. F.','Baquer 25,1º 2ª','Hospitalet','08903','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'MX','MEX',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00044'),
 ('00045',3,0x00,'name 00045','surname1 surname2','DNI','1982-04-02 00:00:00','Vilafranca del Penedès','c/St. Salvador 93-95 2n 2a','Barcelona','08024','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00045'),
 ('00046',7,0x00,'name 00046','surname1 surname2','DNI','1981-04-05 00:00:00','Ourense','Avda. Remolar 157, 4º 1ª','El Prat de Llobregat','08820','123456789','123456789','666666666','NASS','12345678900000000001','','','','',NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00046');
INSERT INTO `personal` VALUES  ('00047',16,0x00,'name 00047','surname1 surname2','DNI','1978-01-27 00:00:00','Barcelona','Passeig Valldaura 158-B 7e 3a','Barcelona','08042','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00047'),
 ('00048',12,0x00,'name 00048','surname1 surname2','DNI','1978-12-26 00:00:00','Mataró','c/ El Salvador 23 4º-3ª','Mataró','08034','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00048'),
 ('00049',10,0x00,'name 00049','surname1 surname2','DNI','1983-04-30 00:00:00','BARCELONA','C/CLOT 226','BARCELONA','08027','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP','ESP','ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00049'),
 ('00050',6,0x00,'name 00050','surname1 surname2','DNI','1970-04-11 00:00:00','CALI','TINENT CORONEL SAGUES No. 4 2-2','ST SADURNI D\'ANOIA','08770','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CO','ESP','COL','ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00050');
INSERT INTO `personal` VALUES  ('00051',6,0x00,'name 00051','surname1 surname2','DNI','1980-04-14 00:00:00','Barcelona','Pujades 12','Barcelona','08005','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00051'),
 ('00052',3,0x00,'name 00052','surname1 surname2','DNI',NULL,'Barcelona','Ctra. Sant Cugat 81-91','Barcelona','08035','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00052'),
 ('00053',13,0x00,'name 00053','surname1 surname2','DNI','1972-11-06 00:00:00','Girona','Alzina, 2, esc. D, 4-2','Barcelona','08024','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00053'),
 ('00054',12,0x00,'name 00054','surname1 surname2','DNI','1972-04-11 00:00:00','Capital Federal','Enrique Granados 97 4º 1ª','Barcelona','08008','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'AR','ARG',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00054');
INSERT INTO `personal` VALUES  ('00055',4,0x00,'name 00055','surname1 surname2','DNI','1971-09-26 00:00:00','Sabadell','C/ Sant Oleguer, 16','Cerdanyola del Vallès','08290','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00055'),
 ('00057',4,0x00,'name 00057','surname1 surname2','DNI','1984-02-27 00:00:00','Vilafranca del penedes','C\\major Nº 63','ST. Pere de ribes','08810','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00057'),
 ('00058',3,0x00,'name 00058','surname1 surname2','DNI','1969-12-21 00:00:00','Roa (Burgos)','Josep Tarradellas 17 1r 1a','Vilafranca del Penedès','08720','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00058'),
 ('00059',29,0x00,'name 00059','surname1 surname2','DNI','1975-08-31 00:00:00','Barcelona','Serra Xic 3, 4t 4a','Barcelona','08003','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00059');
INSERT INTO `personal` VALUES  ('00060',4,0x00,'name 00060','surname1 surname2','DNI','1980-09-22 00:00:00','Mercatello sul Metauro','C/ Floridablanca 55 2o 4a','Barcelona','08015','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'IT','ITA',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00060'),
 ('00061',13,0x00,'name 00061','surname1 surname2','DNI','1970-09-11 00:00:00','BARCELONA','GRAN VIA CORTS CATALANES 143-145, 4-1','BARCELONA','08014','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00061'),
 ('00062',7,0x00,'name 00062','surname1 surname2','DNI','1977-05-31 00:00:00','barcelona','amadeu vives 19','piera','08784','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00062'),
 ('00065',8,0x00,'name 00065','surname1 surname2','DNI','1982-11-06 00:00:00','Barcelona','Josep Samitier 1-5 (PCB)','Barcelona','08028','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00065');
INSERT INTO `personal` VALUES  ('00066',5,0x00,'name 00066','surname1 surname2','DNI','1981-04-04 00:00:00','BALAGUER','','BARCELONA','','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00066'),
 ('00067',8,0x00,'name 00067','surname1 surname2','DNI','1972-05-21 00:00:00','Badalona','c/Transversal, 198, 1º1ª','Terrassa','08225','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00067'),
 ('00068',13,0x00,'name 00068','surname1 surname2','DNI','1968-07-29 00:00:00','Madrid','Roser, 14','Masquefa','08783','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP','ESP','ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00068'),
 ('00069',10,0x00,'name 00069','surname1 surname2','DNI','1955-08-09 00:00:00','Lleida','Tarragona 106, 1, 1','Barcelona','08015','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'2',NULL,'surname1','surname2',NULL,NULL,'es','n00069');
INSERT INTO `personal` VALUES  ('00070',8,0x00,'name 00070','surname1 surname2','DNI','1973-04-15 00:00:00','S. L. del Escorial','c/ Josep Samitier, 1-5','Barcelona','08028','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00070'),
 ('00071',3,0x00,'name 00071','surname1 surname2',NULL,NULL,NULL,NULL,NULL,NULL,'123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00071'),
 ('00072',21,0x00,'name 00072','surname1 surname2','DNI','1981-08-09 00:00:00','Barcelona','Sardenya 515 8 3','Barcelona','08024','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00072'),
 ('00073',4,0x00,'name 00073','surname1 surname2','DNI','1978-05-06 00:00:00','Heidelberg','Floridablanca 99, 5 4','Barcelona','08015','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'DE','DEU',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00073');
INSERT INTO `personal` VALUES  ('00074',5,0x00,'name 00074','surname1 surname2','DNI','1982-05-28 00:00:00','Zaragoza','C/Sant Lluis 21 1º1ª','Barcelona','08012','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00074'),
 ('00075',7,0x00,'name 00075','surname1 surname2','DNI','1974-07-20 00:00:00','Sant Romà d\'Abella','Trinxant 130, 4-4','Barcelona','08041','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00075'),
 ('00076',1,0x00,'name 00076','surname1 surname2',NULL,NULL,NULL,NULL,NULL,NULL,'123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00076'),
 ('00077',4,0x00,'name 00077','surname1 surname2','DNI','1974-03-19 00:00:00','Strasbourg','C/Cremat Gran i Xic, 5-7, 1º2ª','Barcelona','08003','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'FR','FRA',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00077');
INSERT INTO `personal` VALUES  ('00078',9,0x00,'name 00078','surname1 surname2','DNI','1982-02-06 00:00:00','Lloret de Mar','Avda. Vidreres nº 113 4º 1ª','Lloret de Mar','17310','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00078'),
 ('00079',10,0x00,'name 00079','surname1 surname2','DNI','1982-01-07 00:00:00','Barcelona','Rubén Dario, 51, ground floor','Barcelona','08030','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00079'),
 ('00080',3,0x00,'name 00080','surname1 surname2','DNI','1981-05-28 00:00:00','Oviedo','Bruc, 176 3º 1ª','Barcelona','08037','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00080'),
 ('00081',7,0x00,'name 00081','surname1 surname2','DNI','1978-01-04 00:00:00','SANT MARTÍ DE MALDÀ','C/COMTE BORRELL, 141, 3R, 2A','BARCELONA','08015','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00081');
INSERT INTO `personal` VALUES  ('00083',3,0x00,'name 00083','surname1 surname2','DNI','2007-07-27 00:00:00','Miranda de Ebro (Burgos)','Norte 78, 4º-2ª','Esplugues de Llobregat','08950','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00083'),
 ('00084',6,0x00,'name 00084','surname1 surname2','DNI','1980-11-29 00:00:00','Muelheim an der Ruhr','Hermannstr. 130','Muelheim an der Ruhr','45479','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'DE','DEU',NULL,'DE',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00084'),
 ('00085',6,0x00,'name 00085','surname1 surname2','DNI','1977-01-05 00:00:00','Barcelona','Aribau 296 1-2','Barcelona','08006','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00085'),
 ('00086',6,0x00,'name 00086','surname1 surname2','DNI','1971-11-10 00:00:00','Barcelona','C. Sant Antoni Maria Claret, 264, esc.D, 6e4a','Barcelona','08041','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00086');
INSERT INTO `personal` VALUES  ('00087',6,0x00,'name 00087','surname1 surname2','DNI','1983-07-13 00:00:00','Terrassa','Avda. del Bosque 14','Barcelona','08191','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00087'),
 ('00088',5,0x00,'name 00088','surname1 surname2','DNI','1981-01-16 00:00:00','Lisbon','Carrer de les Carretes n-61 1o 3a','Barcelona','08001','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PT','PRT',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00088'),
 ('00089',14,0x00,'name 00089','surname1 surname2','DNI','1980-06-17 00:00:00','barcelona','pescadors 2, 2','castelldefels','08860','123456789','123456789','666666666','NASS','12345678900000000001','IRB','IRB','IRB','Modesto Orozco',NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'5',NULL,'surname1','surname2',NULL,NULL,'es','n00089'),
 ('00090',9,0x00,'name 00090','surname1 surname2','DNI','1974-08-22 00:00:00','Lübeck','c Santuari 121 atc 1a','Barcelona','08032','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'DE','DEU',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00090');
INSERT INTO `personal` VALUES  ('00091',17,0x00,'name 00091','surname1 surname2','DNI','1972-01-01 00:00:00','Be\'er Ya\'akov','Av.Mistral 6, 4-2','Barcelona','08015','123456789','123456789','666666666','NASS','12345678900000000001','Cell Division, Molecular dissection of the centrosome.','Generalitat de Cataluña','IRB','Cayetano Gonzalez',NULL,NULL,NULL,NULL,NULL,NULL,'IL','ISR','DEU','ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00091'),
 ('00092',5,0x00,'name 00092','surname1 surname2','DNI','1982-05-26 00:00:00','Barcelona','Meridiana 254, 1º 4ª','Barcelona','08027','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00092'),
 ('00093',7,0x00,'name 00093','surname1 surname2','DNI','1974-11-28 00:00:00','Malaga','Enric Granados 97, 4-1','Barcelona','08008','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00093'),
 ('00094',6,0x00,'name 00094','surname1 surname2','DNI','1976-06-09 00:00:00','Londres','Francoli 26 Atico 2º','Barcelona','08006','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'GB','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00094');
INSERT INTO `personal` VALUES  ('00095',5,0x00,'name 00095','surname1 surname2','DNI','1966-08-30 00:00:00','Vic','c/ Perot Rocaguinarda 21, Esc B, 3-1','Vic','08500','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00095'),
 ('00096',6,0x00,'name 00096','surname1 surname2','DNI','1957-07-27 00:00:00','Balsareny','Alaba, 50 3 2','Barcelona','08005','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00096'),
 ('00097',3,0x00,'name 00097','surname1 surname2','DNI','1979-07-24 00:00:00','Barcelona','Argentona 20','Barcelona','08024','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00097'),
 ('00098',8,0x00,'name 00098','surname1 surname2','DNI','1980-06-17 00:00:00','Barcelona','Còrsega 200 6-3','Barcelona','08036','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00098');
INSERT INTO `personal` VALUES  ('00099',8,0x00,'name 00099','surname1 surname2','DNI','1984-08-01 00:00:00','ZHE JIANG','C/Sant Jordi nº 5 Esc A, 3º 3ª','Barcelona','08740','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CN','CHN',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00099'),
 ('00100',10,0x00,'name 00100','surname1 surname2','DNI','1979-01-12 00:00:00','Madrid','paris,18 4º1ª','Barcelona','08903','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00100'),
 ('00101',8,0x00,'name 00101','surname1 surname2','DNI','1980-11-30 00:00:00','Barcelona','Misser Sitges 7 4rt','Tarragona','43003','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00101'),
 ('00102',3,0x00,'name 00102','surname1 surname2','DNI','1971-12-06 00:00:00','Barcelona','C/Barcelona, 17-21, esc.B 4º2ª','Cornellà de LLobregat','08940','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00102');
INSERT INTO `personal` VALUES  ('00104',5,0x00,'name 00104','surname1 surname2','DNI','1979-08-05 00:00:00','Barcelona','C/Còrsega 218, 1-1','Barcelona','08036','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00104'),
 ('00105',4,0x00,'name 00105','surname1 surname2','DNI','1981-02-19 00:00:00','Cádiz','Gran de Gracia,210, ppal2ª','Barcelona','08012','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00105'),
 ('00106',4,0x00,'name 00106','surname1 surname2','DNI',NULL,'Dublin','c/ pujos 38, 1-3','Hospitalet De Llobregat','08903','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'IE','IRL',NULL,'IE',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00106'),
 ('00107',1,0x00,'name 00107','surname1 surname2',NULL,NULL,NULL,NULL,NULL,NULL,'123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00107');
INSERT INTO `personal` VALUES  ('00108',7,0x00,'name 00108','surname1 surname2','DNI','1979-10-17 00:00:00','BARCELONA','SANT ISCLE 76 BJOS 1','BARCELONA','08031','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00108'),
 ('00109',3,0x00,'name 00109','surname1 surname2','DNI','1974-02-22 00:00:00','Barcelona','c/Alcolea 58','Barcelona','08014','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00109'),
 ('00110',31,0x01,'name 00110','surname1 surname2','78541256S','1978-11-05 00:00:00','Barcelona','C/Major 37bis','Sant Just Desvern','08960','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','','ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'4',NULL,'SURNAME1','SURNAME2',NULL,NULL,'es','n00110'),
 ('00111',142,0x00,'name 00111','surname1 surname2','78856357C','1956-09-22 00:00:00','Barcelona','C/. Clot 226','Barcelona','08027','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','','ES','ESP',NULL,'ES',NULL,NULL,'00002',NULL,NULL,'00005',NULL,'5','0000000000047','SURNAME1','SURNAME2',NULL,NULL,'es','n00111');
INSERT INTO `personal` VALUES  ('00112',10,0x00,'name 00112','surname1 surname2','DNI','1983-01-24 00:00:00','Barcelona','Av.Paral.lel 139 Esc.B 8º1ª','Barcelona','08004','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP','ESP','ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n00112'),
 ('00113',3,0x00,'name 00113','surname1 surname2','DNI','1977-09-26 00:00:00','Lleida','plaça de la sardana n7','esplugues de llobregat','08950','123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00113'),
 ('00114',12,0x00,'name 00114','surname1 surname2','47856314B','1958-03-25 00:00:00','barcelona','pi i margall 151','Stboi de llobregat','08830','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','','ES','ESP','ESP','ES',NULL,NULL,'00002',NULL,NULL,NULL,NULL,'5',NULL,'SURNAME1','SURNAME2',NULL,NULL,'es','n00114'),
 ('00115',1,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'es',NULL);
INSERT INTO `personal` VALUES  ('00116',1,0x00,'name 00116','surname1 surname2',NULL,NULL,NULL,NULL,NULL,NULL,'123456789','123456789','666666666','NASS','12345678900000000001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'surname1','surname2',NULL,NULL,'es','n00116'),
 ('00118',242,0x01,'name 00118',NULL,'12345678G','1985-03-25 00:00:00','fgsdsd','sffsf','sffs','77558','8789798798','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES',NULL,NULL,'00001',NULL,'',NULL,NULL,'5',NULL,'SURNAME1','SURNAME2',NULL,NULL,'es','n00118'),
 ('00119',10,0x01,'jordi',NULL,'',NULL,'','','','','','','','NBBBBBBBB','','','','','','','','','','','j@j.com',NULL,NULL,NULL,NULL,NULL,NULL,'00001',NULL,NULL,NULL,NULL,'0',NULL,'','',NULL,NULL,'es','j00119'),
 ('00120',1,0x00,'',NULL,'',NULL,'','','','','','','','','','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'','',NULL,NULL,'es','00120'),
 ('00121',1,0x00,'',NULL,'',NULL,'','','','','','','','','','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'','',NULL,NULL,'es','00121');
INSERT INTO `personal` VALUES  ('00122',12,0x01,'fgdg',NULL,'74523658D','1985-03-25 00:00:00','gdfgd','saa','sdsad','98798','578797','78798','87987879','','','','','','','','','','','789','a@b.com','ES','ESP','UMI','ES','00001',NULL,'00001',NULL,'ES3189','00005','00010','3',NULL,'GDSG','DFGDG',NULL,NULL,'es','f00122'),
 ('00123',1,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'es',NULL),
 ('00124',1,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'es',NULL),
 ('00129',1,0x00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'es',NULL),
 ('00130',2,0x00,'Enrique',NULL,'',NULL,'','','','','','','','','','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'CANO','',NULL,NULL,'es','E00130');
INSERT INTO `personal` VALUES  ('00131',1,0x00,'',NULL,'',NULL,'','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'','',NULL,NULL,'es','00131'),
 ('00132',-2147483647,0x00,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'es',NULL),
 ('00136',89,0x00,'jordi',NULL,'47823543B','2008-07-07 00:00:00','dsada','dasdd','dad','asdada','785697','','7878789','NASS','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES','00002',NULL,'00001','00001','ES2100','00005','00010','5','0000000000051','CURTO','MIRAVALLS',NULL,NULL,'es','j00136'),
 ('00137',1,0x00,'',NULL,'',NULL,'','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL,'','',NULL,NULL,'es','00137'),
 ('00138',3,0x00,'',NULL,'',NULL,'','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL,'','',NULL,'','ct','00138'),
 ('00139',1,0x00,'',NULL,'',NULL,'','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL,'','',NULL,'',NULL,'00139');
INSERT INTO `personal` VALUES  ('00140',1,0x00,'',NULL,'',NULL,'','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL,'','',NULL,'',NULL,'00140'),
 ('00141',1,0x00,'',NULL,'',NULL,'','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL,'','',NULL,'','es','00141'),
 ('00142',1,0x00,'',NULL,'',NULL,'','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL,'','',NULL,'','es','74746'),
 ('00143',1,0x00,'adfadsf',NULL,'asdfasdf',NULL,'','adfadsf','asdfasdf','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL,'ASDFASDF','',NULL,'','es','00143'),
 ('00144',1,0x00,'Enrique',NULL,'50848955a','1971-11-28 00:00:00','Madrid','Àngel Guimerà 17, 2º 1ª','Badalona','08917','+34 678 666 518','','+34 977 37 09 97','9187492874','91837491287491284791847',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ES','ESP',NULL,'ES','00001',NULL,'00001','00001','ES3025','00001','00010','0',NULL,'CANO','AYESA',NULL,'enrique.cano@justinmind.com','es','enrique'),
 ('00145',1,0x00,'Enrique',NULL,'',NULL,'','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'0',NULL,'CANO','',NULL,'','es','enrique2');
INSERT INTO `personal` VALUES  ('99999',87,0x00,'name 99999','surname1 surname2','DNI','2007-05-26 00:00:00','','','','','123456789','123456789','666666666','NASS','12345678900000000001','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,'surname1','surname2',NULL,NULL,'es','n99999');
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;


--
-- Definition of table `personalphoto`
--

DROP TABLE IF EXISTS `personalphoto`;
CREATE TABLE `personalphoto` (
  `personalPhotocode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `photo` mediumblob,
  PRIMARY KEY  (`personalPhotocode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personalphoto`
--

/*!40000 ALTER TABLE `personalphoto` DISABLE KEYS */;
INSERT INTO `personalphoto` VALUES  ('0000000000001',0,0x00,0x47494638396178011A01D50000CCCCCCCDCDCDCECECECFCFCFD0D0D0D1D1D1D2D2D2D3D3D3D4D4D4D5D5D5D6D6D6D7D7D7D8D8D8D9D9D9DADADADBDBDBDCDCDCDDDDDDDEDEDEDFDFDFE0E0E0E1E1E1E2E2E2E3E3E3E4E4E4E5E5E5E6E6E6E7E7E7E8E8E8E9E9E9EAEAEAEBEBEBECECECEDEDEDEEEEEEEFEFEFF0F0F0F1F1F1F2F2F2F3F3F3F4F4F4F5F5F5F6F6F6F7F7F7F8F8F8F9F9F9FAFAFAFBFBFBFCFCFCFDFDFDFEFEFEFFFFFF0000000000000000000000000000000000000000000000000000000000000000000000002C0000000078011A010008FE0067081C48B0A0C18308132A5CC8B0A1C38710234A9C48B1A2C58B18336ADCC8B1A3C78F20438A1C49B2A4C9932853AA5CC9B2A5CB973063CA9C49B3A6CD9B3873EADCC9B3A7CF9F40830A1D4AB4A8D1A348932A5DCAB4A9D3A750A34A9D4AB5AAD5AB58B36ADDCAB5ABD7AF60C38A1D4BB6ACD9B368D3AA5DCBB6ADDBB770E3767441B72E431875E93E6C21A243860A17367C50A13146DEC32E5EC490E1D070DE170B1DD785E150C6890F1B2E54C8D06104E58578118B1EED82B142177DFF06FE9042AE4D030062C706B110826C000418BAC090E0B6EF0214585C0CE1BBB86C020B269C4848FCF683851E7C4B604842C200E3000230F060FA6005ECE08BFE9B40F842838200D80B4C20EC3A26ECDB043E23B47D5CA10C0DD7C3C70E400132C5E6FAF9B6C00A070118DB730A4577DB7409ADD04080B119409B771086379E411CE4A75F0012B4D0DE4BEFDD468142F4C5961B42303C586184EC4964E08A05B438D08B0826A4A06C0C1E5482861552D0DD40DFAD68DC8503C550628505A0F0614B21CA168092F3C187626F42C636009110BDB822012E14442374D22124027A5502D0C08F330459A66C58CAB0C09A028CB0E44A4DCAA64042479E68D091B71D104105133040A66F5CBAE85B00880E0ADE045EFA56234237C69623412AF0289B000D4C504104076057414115242A2AA2878E9A289613606700048036208071FF031038274A75CAD64194F519D4817111C838430C1D10501C0386DE06C14032C0D0C20A21BCE99B00FE09F465826116240302C511D0817C02A9C0676C2134C4E7840A8160DC03AD1114C307051497009AB38A54AB955DEE29654130087B5B00E41AE40295B78910D18BC71E248304C59540D0B436564BD0AEBE2550EF411F280A8001F0DA7B5BBF07C5506700B792E7EC6D1EC46BD2BC921E94A741191457F269FADA39B06F051F94AF6F2F4BEB28980B5A1B736C05448B1007C571ACB26F461704F16D1A8056E701269784726C0A17B4724100C7E640435A2EF710C10A39E0DB050BEF4C6DCF0499509C9C0C8D1CDB9DB521BD908A323354C2DA518F343500FE08A079F5402D14478243EDDE964196342B84F06D9FCE6876C3680F74816F083824C2A1421FBDB14230581C6E43D8DE6641DEF2DE6680C54D13F4B740910250C04393DFD600E2C62A943500873BEE1CCF381614BA6CA93324C3ABB7B18DA7DC095D7E9B00191FA441C4A487D46403B15F2ADC40ABA7F93843CA5F4ABB6C351B4442711F947DDB0226A4AFFEFA2658E0F00C32585C75430CF88641DC9B27D4F26DC43A74F7BE3188DE47A6E7B1ED652F02BEC95D4356501C6E2D046C071981A50230B11968A94A9372417132A71035C5865124421E423C0880C6354483BEB99E003932BD198CA038021348F6E8161B0E3C0485B75121435E84800DF8500318FEB00004E6353BF3AD2965035141719A77100C6C4F73B249DA401068B887C8A038E95AE1465A38033E1920805DBCD7406E57BEC660F16B470C4016757644240A446DB719004436E09BFE1D2F7F08A121006CF810E2C9667E5ACC08175BE0C7D8902D8CB91AC8EF6253C686C0E08C0EB920844266C4234EEA7FDE7BC8F3F887BF282AA47EB7D90044E417C82DCA6E2074DC1761B2074AD9F0B121814B211AAB2480465612000348802E77C9CB04D46952285822EC9EA8314F26E401F67B480C20594A8C70117EB72356F690799B433624050D9C65850420011DDEF2510769DDA458501C0F396471B201E11D8D899054DD469D0C21A72C9BE9CC530EE4041603FF41F628E09B2236A47502F85E842640D00950C00217D000084C004684300C52EF8B81C58CC7904502E07E21C46313A167B94335949E1579A640F8099F56E2A6201F78D64715E79BAD693336E19BD9EECED63B82DC6E740D7981C5069751764610730E71DF6DE006528B8874062FF859B60A22CFDBD832213028240082C7B5C4FD879805116741847A1C26128468FB72603167A3109DFA4694C2532A4E8B1A527B12C45CE0D1D3403AD5A7951E647FB7815224AD3A918786F37D2E2C0E5A3957A70530645C0B51C06FC48A21C1B1D5A86E25884909A5ABE28C4821F814904CC15711BF1A44AB05A1EBA5D6781074CAE6733D256BB98A138185A840AA957B6C5BFE65E34F82ACC062892C925201E0238498C05200A068556B77D59942AEA60449A96FAE841019B8B34FE21261736B1581E6A160B74F952D448E3A10271A47AE03512EE544F051154C00B72E15280062AA5E707E16B0F053ECA128202B81C4400416CD8E7071E55384BCB0380708C1475710AAE22CC0ABDA550877ED2B5A3116E4B9CB5DC0031A8032037070877C2D966CDC9B55F8CEA005BB8DCD011AF08005485536D6ECA46A1752BD6749B8010D868F39132C91050B647C4BED981E2B4400D2EE95B87DC52A41406B901DAD090208C69E741322836F4188B9349E888D6598638391304008F0E64BD7DB59210F84C80651418C0384518720D621DE5DD1017CFE15E5874C79062EB014780D825F080900038CFD31678BBB61DEB9F1202FB0C0898D93009E3EE4CC0E21817CF523000B5CB8CD0D79F30CC09A5BE64000B8D95140061EBD65F66E99C343F6704176731E584120861141F44344108110072001189831A47322031488C0031AE040084AC0E9AAB8A00421E080063C30021FB3C4D6B8E600084A50C1593BFBD9D08EB6B4A74DED6A5BFBDAD8CEB6B6B7CDED6E7BFBDBE00EB7B8C74DEE729BFBDCE84EB7BAD7CDEE76BBFBDDF08EB7BC811283169C40041BA800041AB0800420E0000047400216D000566D40042768815DE7AD6D18A4E003146080010640AA0A216A000660000558936786439B2F145000C5DB58FF9C000C4001141081AC3D3E6BD444E074248750000C100111349BE58FADB5051080DB9807280008B0000A928CF3BCC580040FC0B4CF853480079060E14537FA085CB5F4AA636A04508FFA9C4AD0809E579DE401680020B53E27154460D05F5FBA007A45F625C56003214E7BDA09B081ACB73D2D285880D7E59EF6002C40AF775F8B0C36A074BE1B7E001B207AE0BBD28207ECDDF0860FC00356BE78B19C60CC90CF7C9FBC5679B180A0F09AD7FC00A4D8F9AC68E0F1A1D77C00A85A7AAD5C39F5B0675CEBB762DAD8DB3E72B3AF4AED6FCF7B004C2AF751D97DEF79FF7BE037E5F5C3E7BD098DCF944D26FFF9B1613DF38F0202D4433FF5FC9A7E524E00FAEBFEC77E009CD7FE505A8079EFF7FE0094177F4F64404DF37BFF018A57FF4C36607DF7C33E0083953F4F50D07DFBDB7E0080A77F3811036EE37FD7B70076278030417F06E87EF8A78038A10271D780C347006C068130414514E87EAD858133510268B781C3270063E7812C11033B2682D0D700096882243102F5A782B11700FBE58227818232E87F2C68832B4102219883BD270086C68326C17E40E87FF0478427C17F47687F00A88426C1554D687E6B058521E102F93585CF87003767851C31265AE880A8E6851EA18161E87D1D48861DD1027B7386B76700E9A7861601866EE87D013086728811245587DE775979881130B0687C087D0AD0717FF81029D07FFF83987A03606C87081115B388D71700D9F58810B18792F87C7E6889111103939589BDC7002DC88906C186A0087D70488A11C17DA7F87CE0A78A104187ADD87B77088B0FC180B3488BF9678B1D948BC9B77CBCB84EBE787B9E168C059182C3187AB5658C075180C9987A86C58C09713BCF187A09208D099185D50879B1858D06517EDBC87750E38DDF188EB0378EE44810E0688E5F878EE92810DAC88E5FD78DEF3803D4288F69778DF52810CE888F5F178DFB888CFEE873CB988E4E36903E578CD8887C084972C0988EB8D8904BF780FB3803B2289131578B15C98A18E973AF5891A6D89131978A15E989221973A2589123759224B789FB18892C592694A892FF0291883159268D489333108837592585A8933380893D19202E5991173994E0A1913A199248191E24099466D894BC029403719452E924784893587895C6C18554391052C895B1518554C9846269250108944678960090845F39103E789642F896EA229037B98374391030C89534989775C99578E997020182524982825910517993697898DD32811869818C591011C99214199945D28F1889809659106679924FB899921983DB5899A0892CED87916E599A04417E1D897EAA79101C89901FF99A06517D08997DB48910CE878FD2979B04C190C9F890BE5910C2978CC5379C06519CB9789CC8999CDBC89CCD6910C02989C2199DBA299A53B87AD609119FD78AFFA3B79DABB88E5A7800E1079E0ED178D84981921787E6B91083A7881B8878F1D79E069177E969877F479F17F1768E698074378AFAC91066F783E6B7761718A015C175F7797F6287A01E11035347A0B7777500EAA012717449777D4DF774163A123AC773BC077442379FE9E87020700110D06FE2C996B177000307011700022960882C17032670010A40000BCAA2B6170004A00017C05064F7021FC00012CAA30628000CF001BDD66EEB92003B8AA47698001F50A1E586028222A583A81D69B96EF7019F5A9A8303A00124DA6D2FE078610A8A92D7A4E1D602F798A6839800ECD9A62B0AA767E89AE7C69A769A8B784A6E2FF0A67B2A8909C0A6D8B69681EA8BFFA9096EA777A8C3A89DE1D6998C9A8B9FE96D2619A9C99892DE069396EA8B3369A680BAA99938A8DDA6A9A09A8B9DAA6D955AAAC388A9D9660247AAAA75280058726D2D06ABB99862D6C693B63A8C3F896D29D09FBBCA873D966DB619ACA64A7AD056ABC67A8AB84A6D07B9AC8BA890D08699D03A8800696D9F5AAD6EA88FD756A7DA3A85EE586DDEFAAD4718AED446AEAD986DE87A8AEABAAE99D8AEEEBA88F01AAF7C38AFF4EA86F67AAF6198AFFA3A85FCDAAF47F8AF009B83023BB02A58B006BB81089BB00DB8B00CEB7F0EFBB0EE17B112EB7D145BB1D077B1189B7C1ABBB1BDD7B11E7B7B201BB2B137B2249B7A267BB29A97B22A0B792CDBB27CF7B2E4309B76323BB35557B336EB73389BB324B7B33C7B443EFBB36512B4422B24445BB41572B4481B204ABBB4E1D1B44E8B1D501BB5C531B5547B1B567BB5B191B55ACBB557EBB5540BB6512BB64E4BB64B6BB6488BB645ABB642CBB63FEBB63C0BB7392BB7364BB7336BB7308BB72D8BB60591A67DCBB1D8F67C7FABA583FBB1810BB803E1B70421B8873B7C858BA48F2BB28D6BB8891BA6916B7BD936AE7277B96CC9B9A967AED396AD99E7B96249BA9AC7ADD646ADA167BA57C9BA9077ADD5F6ACABBBB8964BBBBD27ADCFA6AC256BBB52EABA86D7ACD356ACBCE7BB4849BC7D87AC451110003B),
 ('0000000000004',0,0x00,0xFFD8FFE000104A46494600010101025802580000FFDB0043000302020302020303030304030304050805050404050A070706080C0A0C0C0B0A0B0B0D0E12100D0E110E0B0B1016101113141515150C0F171816141812141514FFDB00430103040405040509050509140D0B0D1414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414141414FFC0001108006E009703012200021101031101FFC4001F0000010501010101010100000000000000000102030405060708090A0BFFC400B5100002010303020403050504040000017D01020300041105122131410613516107227114328191A1082342B1C11552D1F02433627282090A161718191A25262728292A3435363738393A434445464748494A535455565758595A636465666768696A737475767778797A838485868788898A92939495969798999AA2A3A4A5A6A7A8A9AAB2B3B4B5B6B7B8B9BAC2C3C4C5C6C7C8C9CAD2D3D4D5D6D7D8D9DAE1E2E3E4E5E6E7E8E9EAF1F2F3F4F5F6F7F8F9FAFFC4001F0100030101010101010101010000000000000102030405060708090A0BFFC400B51100020102040403040705040400010277000102031104052131061241510761711322328108144291A1B1C109233352F0156272D10A162434E125F11718191A262728292A35363738393A434445464748494A535455565758595A636465666768696A737475767778797A82838485868788898A92939495969798999AA2A3A4A5A6A7A8A9AAB2B3B4B5B6B7B8B9BAC2C3C4C5C6C7C8C9CAD2D3D4D5D6D7D8D9DAE2E3E4E5E6E7E8E9EAF2F3F4F5F6F7F8F9FAFFDA000C03010002110311003F00F7484F357A23802A8C3570B6C8CB7A0279AC4C99E47F1FBF696D2FE0ADAC5690C2BAA7882E0663B2126D11AFF7E43D87A7AD7C55E34FDA3FE22FC48B971378867B281BA5B581F22251EBC1CFE64D71FF00153C4F79E36F89BAFEA575219649EF2409F3160A81B0A07B002BA8F01FC20D63C6F70B1D8E23B64C2CB723A03D71F5A8949417348F56861D3B2B5D9831EAFAFDA28326B77EF2310587DA9C9C8E99E7D2BD0BE1DFED1FF103E195EC725AEB8DA8DA061E6D95FB19639067A0C9CA9F706BDDBC0BFB19E8F22A0D52FEE6695B92CAD81CD7A6D9FEC2FE0E8D9E57B9BA9D4AE0C523065E7B8E38AE7588A72D8EF783FE6B1EADF05BE2EE91F19BC1B06B3A63AA4EBFBBBBB42D96B7940E54FB7707B8AEFC0AF8FF00E16FC3ABCFD9C7F695B4D0ED2E5E6F0D789ECDFC92EBD244E761C7191DBD9ABEC30B5D309A9C6E8F0F1147D854E5100E94F51498AA97F7CB621E4932228A23236DE4E3FAD68732D4D141520158F16BA920188A519F543561756047FAB7FF00BE1AB0588A2FEDAFBD1D3F56ACB783FB99AAAB9A7018ACC1AB003A30FF00809FF0AA973AC5D7DBECFC87816CC6FF00B4AC88C643F2FC9B0F41CF5CE78E956AB537B497DE2F6157F91FDCCDF02940E2B24EBD10FF00968A3EA69BFF00090C03FE5B45FF007D8AB538BD98BD9D45BC59B6B48CB58E9E21889E258BFEFB153A6B0B27F1A1FA115574472C96E8D0C71455EF0FDAC3ABB3A48CCACA3236D15691373C2A3EA2A4BA9C41637121E891B3607B03518054D47A9348BA5DD984E25F29B69C67071D6B07A2B9518F3C947B9F9297C86F759BA991897927772D8E9F366BEAFF00D9F219FC21E113A9DECA6382E8EF44C1276FAE3B93D80AD0F1FF00C3DD2FC4511B49AD62935382D63B87BB86DD21640CDCA165037718E31915EA3F0D3C2F6F7B636967FC1691AC63681C718CD78F8AC47B482823ED29E17D84DABDEC5E8BF691D1FC290C326A1E1DD6E4B41806E05B0183F4CE47E35EFBE00F881A2F8EBC371EADA6C92C76D20395B84D8EB8F51DABC313F672B3D24EA3711096FBED48431BB95DD7939C804E01F715B5F0DFC3D3695A2EA9E1BB6BB9221756F2A24A8DF340C4100A93E86B2E6A74D251EA6CE8B926EE74FA87897C19F11BC6FA05AE99AEDADC6B7A3EA267F27389140460EA33D7208E95EB7B79AF0BF835F050F83752B396F64176F6CACED24F0AF9AF36ECAC81C7B6477CE7B74AF76AF5F0E928E87CAE65A564BC84DBCD646BABBE1BA4EC6DF1F9935AE4D64EACF81727AE224FFD0ABA25F0B3CEA7F12B1A36DA7C60018156D2C900E94F82656FE0AB4AA587CA2BE0EBE16A4B58C6C7D7FB49752A9B1523A546FA72FA55F0B20EC29A7CC1FC35E67B2AB0DCD63397731E7D305509F4B420E4574326EC72955A58CB75534AF556C8ED85592395B9D22239F947E558D7BA2C633F28FCABB3B8B7EBC5655CDB6F603DE97B5A8B747A54EBBEE7A17C3E8B2F2760B181455CF00C6145C1FA0A2BF5982B451F9649DDB67C8B7BF1B7C0F68F87F12D8924E00462D9FC85527FDA07C0F1023FB60C83FD8B594FFECB5F1141A8DD07C31958A9E849AF4DF86DE10F11FC446B95D17C353EAA2D541B8916428B183D325980EC6BDF592351E79544979FFC31854AD1868A2DFA5BFCCF45D47E20E95E255BE315ADC5AC9F6A78AD64689945C427055C67A0F63DC715D4F80F509AD195D5CA9046E1EA3B57B05C7ECA969AA7C3FD38D922D8F882D6D82ABE498E62064ABFE39C376EF9AF28D36C25D2E79ECAEE06B4BE80F973DBC830E8C3F98F7AF80C461F92AB4B6BE87D8D0C646B528BFB496A76DE2FF1FC9115D126B95B679A3388C49B6471D33EC3AD61F846D35DB6D561BF1ACF9B6B102AB1C6C9951EE3A935A16AD178CE3495E006FECCE15C0F98FD0D74E96DFDB3A5B596BB195D2E1512CC652155D50EE1B8F5C6477344229EAD1EB4669D3D1FAFF56FF23CEBF692FDA43C43F0C3E18D8CFA12C70F8AEE75536B1992012A9850167623A60829F9D786787FF6FF00F8B10951A8689A0DEAF72F1BC2C7F273FCAB97F8B7F136CFE2F7ED05A15AC0A25F0ADBDEC5A7C51062AB3A17F9DF8C1C313C1F402BEEDFD9E7E107817EC37D3C5E16D3DAE2DDD15184796C1CE72C724F4F5AF5A8C3D9C145AD59F258CAB1A951CAC78EFC37FDB575EF13F8A344D1F5AF0425826A73A40B796F7126C5DC701B0C9C8FC6BE9BB993CF8EE189C02B18FF00C7EBACD6349D3346D2B52B88AC2DED604B560AC107CAD8EDE9DAB8386F01D32E256E81A1CE7FDF15BF2F334BB9E7C1C79D33B58F695C6769ECDDAA34D716C27115E218C13C3F63F8D53B1BFB7B93F249E5B7B9E2AEDD69CB7B018E55DF19EE9C8AE8951950D270E68FE27D5D1AB42A3B3775F89D0DA98AEA30D1B0607D2A63683D2B98D320974A8BCB5691D17EEBAF381E8455EB4D66E7CD292B2BA766E87F2AE0FAB616B3D1DBC9E8454A32836E0EE8D56B55EE2AA5C43185CE2B335FF1741A4479657727A051927E959963ABEA1AEA8912D9ADA03FC73FCBFA553CB68F2F3292B1D14B0F59C3DA3568F77A176F7CA407240AC296E61FB42287049602B66E92CE1421D5AEA4EE7B5601FDEEA16EA962B1A7983242F4E7D6BE7EAD2C3F3F2AD4F46925C926EFA267A9F8193FD1276C7561455BF07A6CD3643EAFFD28AFAB5B1F9C9F93FF00077F66EF177C6191353117F61F87A59091A8DD29CCA33D224E0BFD781EF5FA47F033E10697F0A7C2706916109603E69279002F339E0B39E99FE4302B5AC7478611015C18A31811A280B81C6001EDDABA4B49634DA622ECAD8233DEBB3118EAD8A5CB397BABA034AF748D986C63580C41004FEEE381ED5E39F1CFE0327C42B4FED0D2A61A7788AD8660BA1D2503F824F51FCBE95EC76F2CD9076161D0838C1E6AF18C3F3DBD4FF5FF00EB5704A319AE591709CA9CB9A3B9F9F3E1EB5F11F877C40F6F7BA7BDB6A30398E58C0F9588F5AD4FDAD34FF1A43FB3C6A97DA6594135AC9188F575898F9D696A7EF363F88766FEE839F5C7D57F183C1935CE8773AD68D6D0BEB7689958E4E04E80F2BFEF019DBEBD2B2F5FB31A77C3CD4F5292CD2F6C974C9A59F4FBDFBAE9E59251B7763C820E6B969E1F95BE6D6DB1EA54C5DE09C34BEE7E23F836E0DB78F3C35264284D4ED8803FEBAAD7EB27ECEFE28B4D36DF577BCB88ED2DF72EE92670AA012DC926BF2E4781458A693A9DD5FCDA36A2F7AACF04B63298ED5839600B007240087001CEEC76E7EBAF83DF11BC3DE1CB49A6F1469B730412B031F889E133C0ACA7277C9863167DC0FA0AD6524DAB1C95129D9DCF79F1CFED0361E2DF87B1C57914DE188AF7518F4D9EFE621E081D652786E3CC5658C65932007C93C60DCBED4BECDF0FF58B96911CC52C4BBE270EA7F789C861C11CF515E53F16E7F097ED13A159683E11F17C126A5702436EB69234D1CD2B00B998FDEDAAA5C8007AE78AF4FF00017C1B93C33F092DBC09AB6A5F6896282349AF2CD4A82410C3686CF03681EF8E82B5A3522AA45CBA3D4E794527A331F48F1B00A089707D335D7E99F10A4830565E3EB5CBCDFB3E5C45CD86BE0E3A0B8808FD54FF004AA53FC20F17D803E435ADE01FF3CA7C13F83015F74B1597D6FB6BE6AC5C545753D8B4CF88F693E05CA00DFDF43835D15BEAB61ABA7FA3CF0CCDFDC9461BF3AF9A2E344F17690099F46BD0A3F8A34F307FE3B9AA49E36BCD2E50B72B2DB383D245287F5ACA795E1314BF7724FEE67A74A738BF7647D2DA8DC5B58FFAFD3D11FF0085A41B94FD0D605F7C418F4C7025B588A0E9B474AF36D0FE355CC08219E44BDB63C18A7C1FC8D6E4CBA078F6D58D8CE6DAE31F3DA48D8FFBE4D7915B011CBBDFAB4B9A1D5AE9FE47D560A54315FBAC4DE2DF55A9DCE9BF1034BD557E428ADE95A316A76D753A226D2CC78C57CDDE20F87BE2EF0E4AD36951DCEAB0039555E2541F5FE215A3F093E22EBD73F10F4BD0354D06F2D7CEF30B5C5C46CBB76A13E98ED5E6E2A86595E0AA61AA24FF0095BB3FB9EFF20C66595B0B09CA12552166EEBD3AADD1F63787BF71A1337FB5FE14536D9C5BF850BFB8FF00D08515CD7B1F9D9CBE8B78D1C6AEF890B7207661D8D6D58DC2C13813A32A609047F09EFC570BA1EA60B96246C27B700FF8F07B5777A6DD798A0633EDD33EBC75A94AC336524B8894496F289D3BA375AB29AA896259506D2A70CBDC5558EDB69F3206F29FB8E02B7E1F4A6CF0B387744D929044883A37B8FD2AD0321D52DA4D525567768C27DD453F2FE23B9F7AE23E24EBE74AF87325B496ED2DCDFA9B7F21002769077641ED8C8E7D6BD1EDDBCCB78C91D5457CF3F1FF00C516169E33D3349D4AF5AD19A367B78FCA91CCA5980E0AA919C8C62A6A3B4188F963C53FB3DF84B525BDBC9F4ABD93CB432B59CB3B24508192595430033EDD4FD0D78A6A9F06B541797D61E1B56D22D2791ADEE22FED52BBD7A7CC392E0F5071C86AFB5AEB48D41E41345696FA5DBB214FB6EB33F93B97AF11FDE6FC715956FF00076CED9A2D7351D52DAE34CB724BDB43A63794CC410A0091C9C0241C2A8CE2BCE526B63652672BFB137ECDB07C2B8AFF00C51AA2FDA35CBBDD6B6C490CB0C21BE66423FBE40E4F385C7735F525D606A5305EC141FAE2BC35BC71A6F8264B1B5B3D5B55920806D31C5A53BC6A00181F20F5C9E3E95B36BFB407836DCC867D46E229A46DF235D5B491E5BF11C0C6063DAB68CAFAB14AF27767AFC47E5A9C36DEB8AF3FF0FF00C5FF000B78888165AC413658A8DAE0F20671F9575F05F45322B23820F23B56A999BD373444B93C543E6DAEA2EF0C891CFB782B22861F91A6893238A8AD6CA3B7BA9255272FD41E9568467DFF00C36F0AEA99371A0D8963FC71C5E5B7E6B8AC56F821E1C12896CE4BFD3A453C186E0B01F83835DCA366A457E3DABA2389AD0568CDDBD4DE9D7AB49A709356F3399B1F086A9A3C7B20D6FEDB103F2ADE43F301FEF29FE95A1A6D9DFAEA31B5D410EC00FEF6393760FD0806B67191C1E29F0AFCC2BCB9E0E854A8AA38EA9DCED9E6789AA9C6A4AF7F25FA1D26B77C74CF03AC8A4AB16500AF5E5A8ACFD7E2D5754D1560B7B6B64D3A008D25CCD390C58FF0AA053EA39268AEC95EFA1E623CD344BF1E705E55FD09E47B13DBBD76BA6DF880AEF90DB38C6D94708DE99EFED5C5788B436B3B837167F3479DCA1471F80EE7DCD6F7843C416FA8C62DEE70265183BB19F7E7A7BF15B580F4BD23558EE5424AC8AF8E1D5800DFD6B61E0270413F864D7236FA2C4C328BB0F678CE39FAFB8AD7B3BBBBD39424E3CF83B480608F4FAD2B7619AA88234DB82146703D2BC97E3D68BF6BD3B4CD48433482DE7F289B62048A5B051867A80CB8C7BD7ABC93A94126FDD11E84FF008579DF8EFC4ED1785FC5A34BD4ADA3D77478A3BD449E312F9008055990919070DF8D2924E2EE2B36ED1DCE06C608BCF8E4916196E9B1FBD7B44F333F8E715A51F83B50D6350FB524FE782D932DDE4951DD118718FF00671815E68BE3CBD457D52EA54D56ED7124DE54580E49F99B6AE303A9C57B7FC38F1B5A78CB4759A028AEA01C47C02A7A1C7AF6C76AF3954A5527C906764B0589A34BDAD55A1CF5F780EE61F985A92BFDE8F0C3F4AC2BDF0E25BAB3C902B850490579AF6CE49EDEC7F4EBC5366B68AE06D9625933D9D41FF035ABA2BA1C773E65D0B56D07C48E67D1DACAE8C67E61001E6A1FF694FCCBF8815D42DFB8E0BB2FB1AEEBC5BF037C1BE33985CDE694B06A0BCC77D64E60B84F4C38C37EB5C0EBDF04BC7DA05BB0F0CF8AAD3C416CA72B65E258764D81FC22E231CE7FDA53F5A9E5947A1D7ECE8D5F827CAFB4BFCD7EA916975A962FB931FE74B378CEE2D232CCA9260742307F4AAC9E11F135ADB47FDA7A4791332E48B5904ABC6338C76E6B1352B09806465646FEEB0C1FC8D5A91C8E3CAECCB517C72B4B79CC77BA6CF1E0E37C4C1C7E4715D2E95F16BC37A9600D405BB9FE1B8429FF00D6AF20BCF0E4F3CAC16091CFFB284D68683E0B837ACDA8831C43A47DDAB54D01EF967A95B5E22C904F1CC8DD191C106B42124B03C5701A4B431D9A456D1A4512F0AAA2B9FD47E26EB3E15D7E4827D1F669B8023BD95F7A4A78E8AADB94F6E450E492BB08C5C9D91F42EA4CD1782EF0A2B313222E1464F057FC28AE07C37F1C747BFD27EC377035AC8ADBDDD183AE4F2063823F114555F9D2717A17671D1A38EF86FE331E29D0FC9BB6DD790FC9364E09FF006B3E87A8C7BD6B5F687E4C86E215E473851D3E83F5E6BE65D17C473F823C6492593B9803159223D0AE7B7B8AFAE3C35709ACE9F6F304286640C013ED9193DEBAE4ADA9325665BF0CEAF7222062B82FB7828FCB0FC7A0C577361AD895313C783D0B2F4FCEBCD65B0934DB9796DA4015482558703D302BA1D03C426E42EF438E06EEA7DBE950D5C947791C10CA85A120AB0E47635F287ED3BA4CDF05F4CF19F8DD5AE35693C4AC9A7C11EDDB1DA6E84A6D90FF007005247A9207BD7D4301055248F31B374DA7F9D54F1A783F4DF88FE12D4BC39ACDB25CD86A1098E4461D3B861EE08041F6ACE5CDCAD236A528C26A525748FCCCF839A6F8965D760B9B4F16CADA3CF286B88EE60DDE5E393B4E7A7615F5144BAD4D676F3E8375FD9FE53B1C1F91E4279C920639F4C570FA0FECE9A7F82750BAB3D3B56BFB7B7576478166CC64E704804715EC9A0697169D6315BC592918C65B926BC88D1719F333DFC66631AF47920AD728699F163C57A0054D634E3791A9F9A609918F5DC99FD40AEEFC3FF18342D6631BD9ED5870E5F0C8BFF025CFEB58FE427702B3AFFC2FA6EA4C1E6B48CC9DA551B5C7FC0860D772935B9F3CECFA1EB765A95AEA288D693C770AC323CA607F4FFEB55998791180159E638DB0A9C71EADD80FD6BC3AD7C3177A55D2DC697A9C91C83A25C8F307D37021BF53530F8A1AD786AFA3B3BC68E5DE70A8BFBC4FFC7B047EB57CE89B763DAA3F91B74B91237192A540F65C718A8350D36D7558DA19608E65CFCF23283B48EC08FE75CEBFC474F36D63BCB57C4F1820C4D9FAE735B5AD788ADF43D06E7549227782D93718D40DDEC064FF005A6DAB5D8926DD911C5E13B1B6128B3436A24055844723047A5719E21F0847A74B0890ACCB2A920EDC6DC7622B8D4F19EA9E24D73EDB2CED10C031408E7644A4F000EE7DCD76F25CDC5FDCE6E2769980FBCE2BCF862A9D66E291E8D5C0D4A30E76FD4CE8343863401415FA5136896978E905D344B19E775CAE547FF5EB5D46CE2AEE99A636AD3794ACAA0752D5D2AFB23CD23B5F03699308E484C53BA8C799847FC810714575163E13D36C7E6F204B29EAE78FE5456AA13B19DA5DCFFFD9);
/*!40000 ALTER TABLE `personalphoto` ENABLE KEYS */;


--
-- Definition of table `personalstate`
--

DROP TABLE IF EXISTS `personalstate`;
CREATE TABLE `personalstate` (
  `personalstatecode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`personalstatecode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personalstate`
--

/*!40000 ALTER TABLE `personalstate` DISABLE KEYS */;
INSERT INTO `personalstate` VALUES  ('0',0,0x00,'editing'),
 ('1',1,0x00,'validating'),
 ('2',2,0x00,'validating cancelled'),
 ('3',3,0x00,'asdfasdf'),
 ('4',4,0x00,'cancelled'),
 ('5',5,0x00,'active'),
 ('6',6,0x00,'inactive');
/*!40000 ALTER TABLE `personalstate` ENABLE KEYS */;


--
-- Definition of table `position`
--

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `positioncode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`positioncode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `position`
--

/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES  ('00001',51,0x00,'Group Leader'),
 ('00002',0,0x00,'Research Director'),
 ('00003',19,0x00,'Research Associate'),
 ('00004',5,0x00,'Research Assistant'),
 ('00005',43,0x00,'Postdoctoral Fellow'),
 ('00006',62,0x00,'PhD Student'),
 ('00007',9,0x00,'MSc Student'),
 ('00008',23,0x00,'Lab Manager'),
 ('00009',16,0x00,'Lab Technician'),
 ('00010',26,0x00,'Administrative Assistant'),
 ('00011',13,0x00,'Programme Secretaries'),
 ('00012',0,0x00,'Programme Technicians'),
 ('00013',1,0x00,'Visiting Scientist'),
 ('00014',11,0x00,'Visiting Student'),
 ('00015',0,0x00,'Facility Specialist'),
 ('00016',0,0x00,'Facility Manager'),
 ('00017',32,0x00,'Administration Department Head'),
 ('00018',6,0x00,'Admistration Department Member');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;


--
-- Definition of table `professional`
--

DROP TABLE IF EXISTS `professional`;
CREATE TABLE `professional` (
  `professionalcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `email` varchar(100) default NULL,
  `phone` varchar(100) default NULL,
  `mobile_long` varchar(100) default NULL,
  `professional_personal` varchar(255) default NULL,
  `research_group` varchar(255) default NULL,
  `type_of_contract` varchar(255) default NULL,
  `building` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `position` varchar(255) default NULL,
  `professional_unit` varchar(255) default NULL,
  `payroll_institution` varchar(255) default NULL,
  `payroll_institution_2` varchar(255) default NULL,
  `mobile_short` varchar(100) default NULL,
  `lab_phone_number` varchar(100) default NULL,
  `fax` varchar(100) default NULL,
  `current` bit(1) NOT NULL default '\0',
  PRIMARY KEY  (`professionalcode`),
  KEY `FKBE478627AE41B6F0` (`professional_unit`),
  KEY `FKBE478627F189DA86` (`research_group`),
  KEY `FKBE4786273440803A` (`location`),
  KEY `FKBE478627AAC67322` (`position`),
  KEY `FKBE478627FB41C68` (`professional_personal`),
  KEY `FKBE4786272CF0EABA` (`type_of_contract`),
  KEY `FKBE478627A9AF406C` (`payroll_institution`),
  KEY `FK_professional_8` (`payroll_institution_2`),
  CONSTRAINT `FKBE4786272CF0EABA` FOREIGN KEY (`type_of_contract`) REFERENCES `type_of_contract` (`type_of_contractcode`),
  CONSTRAINT `FKBE4786273440803A` FOREIGN KEY (`location`) REFERENCES `location` (`locationcode`),
  CONSTRAINT `FKBE478627A9AF406C` FOREIGN KEY (`payroll_institution`) REFERENCES `payroll_institution` (`payroll_institutioncode`),
  CONSTRAINT `FKBE478627AAC67322` FOREIGN KEY (`position`) REFERENCES `position` (`positioncode`),
  CONSTRAINT `FKBE478627AE41B6F0` FOREIGN KEY (`professional_unit`) REFERENCES `unit` (`unitcode`),
  CONSTRAINT `FKBE478627F189DA86` FOREIGN KEY (`research_group`) REFERENCES `research_group` (`research_groupcode`),
  CONSTRAINT `FKBE478627FB41C68` FOREIGN KEY (`professional_personal`) REFERENCES `personal` (`personalcode`),
  CONSTRAINT `FK_professional_8` FOREIGN KEY (`payroll_institution_2`) REFERENCES `payroll_institution` (`payroll_institutioncode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `professional`
--

/*!40000 ALTER TABLE `professional` DISABLE KEYS */;
INSERT INTO `professional` VALUES  ('0000000000001',6,0x01,NULL,NULL,'enrique.cano@justinmind.com','','','00001','00001',NULL,'',NULL,'00010',NULL,NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000002',3,0x01,NULL,NULL,'enrique.cano@justinmind.com',NULL,NULL,'00028',NULL,NULL,'',NULL,'00014',NULL,NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000003',5,0x00,'2006-04-01 00:00:00',NULL,'enrique.cano@justinmind.com','+34 934029943','628272032','00029',NULL,NULL,'','P1B1','00017','00015','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000005',4,0x00,'2007-03-06 00:00:00',NULL,'enrique.cano@justinmind.com','39945','','00028','00001','00005','','P1B1','00018','00015','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000006',7,0x00,'2007-04-16 00:00:00',NULL,'enrique.cano@justinmind.com','934039810','660208117','00031',NULL,'00004','','P1B1','00017','00012','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000007',5,0x00,'2007-04-16 00:00:00',NULL,'','+34 93 40 06137','','00032',NULL,NULL,'','P1B1','00018','00012','00001',NULL,'','','',0x01),
 ('0000000000008',2,0x00,'2006-12-11 00:00:00',NULL,'enrique.cano@justinmind.com','934039773','','00033',NULL,NULL,'','P1B1','00010','00012','00001',NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000009',6,0x00,'2007-05-01 00:00:00',NULL,'enrique.cano@justinmind.com',NULL,NULL,'00034','00015',NULL,'','','00005','00003','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000010',3,0x00,'2006-01-01 00:00:00',NULL,'enrique.cano@justinmind.com','37163','','00036','00015',NULL,'','PBB13/14','00008','00003','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000011',3,0x00,'2002-02-02 00:00:00',NULL,'enrique.cano@justinmind.com','9304034718','','00037',NULL,NULL,'','P1B1',NULL,'00012','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000012',4,0x00,'2004-09-01 00:00:00','2009-04-01 00:00:00','enrique.cano@justinmind.com','934037163','','00038','00015',NULL,'','','00006','00003','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000013',1,0x00,'2004-07-04 00:00:00',NULL,'enrique.cano@justinmind.com','934037163','','00035','00015',NULL,'','',NULL,'00003','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000014',5,0x00,'2007-06-23 00:00:00',NULL,'enrique.cano@justinmind.com','934037163','','00041','00015',NULL,'','','00005','00003','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000015',2,0x00,'2006-01-01 00:00:00',NULL,'enrique.cano@justinmind.com','37163/37162','','00040','00015',NULL,'','PBB13/14','00003','00003','00001',NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000016',1,0x00,NULL,NULL,'enrique.cano@justinmind.com','','','00043',NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000017',3,0x00,'2006-02-22 00:00:00',NULL,'enrique.cano@justinmind.com','934037255','618302538','00039',NULL,NULL,'','P1B1',NULL,'00014','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000018',3,0x00,'2006-01-02 00:00:00',NULL,'enrique.cano@justinmind.com','934037160','','00042','00015',NULL,'','PBB13/14','00003','00003','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000019',1,0x00,'0006-01-09 00:00:00','0010-01-09 00:00:00','enrique.cano@justinmind.com','37163','','00045','00015',NULL,'','PBB13/14','00006','00003','00001',NULL,NULL,NULL,NULL,0x00),
 ('0000000000020',3,0x00,'2003-10-01 00:00:00',NULL,'enrique.cano@justinmind.com',NULL,NULL,'00046','00015',NULL,'','PBB13/14','00006','00003','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000021',6,0x00,'2007-02-01 00:00:00','2007-12-31 00:00:00','enrique.cano@justinmind.com','934037163','','00047','00015',NULL,'','PBB13/14','00005','00003','00001',NULL,NULL,NULL,NULL,0x00),
 ('0000000000022',6,0x00,'2003-01-07 00:00:00',NULL,'enrique.cano@justinmind.com','+34 934037163','','00048','00015',NULL,'','PBB13/14','00006','00003',NULL,NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000023',3,0x00,'2005-01-12 00:00:00',NULL,'enrique.cano@justinmind.com','934037163','','00049','00015',NULL,'','PBB13/14','00009','00003','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000024',4,0x00,'2006-02-15 00:00:00',NULL,'enrique.cano@justinmind.com','934034716','6618302574','00050',NULL,NULL,'','',NULL,'00016',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000025',3,0x00,'2003-09-01 00:00:00',NULL,'enrique.cano@justinmind.com','934037163','','00051','00015',NULL,'','PBB13/14','00006','00003','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000026',4,0x00,'2006-01-14 00:00:00','2010-11-30 00:00:00','enrique.cano@justinmind.com','934037163','','00044','00015',NULL,'','PBB13/14','00005','00003','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000027',3,0x00,'2007-02-19 00:00:00',NULL,'enrique.cano@justinmind.com','93-403.72.90','','00002',NULL,'00002','','P1B1','00010','00013','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000028',31,0x00,'2007-01-29 00:00:00',NULL,'hola_mun.do@irbbarcelona.org','+34 93 40 39782','+34 685039874','00001','00026','00003','','P1B1','00017','00013','00009','00005','11111','+34 93 40 78954','+34 93 40 78954',0x00),
 ('0000000000029',1,0x00,NULL,NULL,'enrique.cano@justinmind.com','934034955','618302463/11392','00052',NULL,NULL,'','P1B1','00018','00014','00001',NULL,NULL,NULL,NULL,0x00);
INSERT INTO `professional` VALUES  ('0000000000030',1,0x00,'2006-09-01 00:00:00',NULL,'enrique.cano@justinmind.com','934039995','11385','00053',NULL,NULL,'','P1B1',NULL,'00017','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000031',2,0x00,'2006-01-09 00:00:00',NULL,'enrique.cano@justinmind.com','934034982','','00057','00024',NULL,'','PBA24',NULL,'00005',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000032',2,0x00,'2006-06-20 00:00:00',NULL,'enrique.cano@justinmind.com','93 403 40 46','','00055',NULL,NULL,'','PBA11/12/13','00011','00003','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000033',8,0x00,'2006-03-06 00:00:00',NULL,'enrique.cano@justinmind.com','934039962','628292308 (11417)','00059','00025',NULL,'','PBB51/52','00011','00005','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000034',1,0x00,'2002-01-08 00:00:00',NULL,'enrique.cano@justinmind.com','93 4037132','','00058','00012',NULL,'','PBC22/23','00003','00002','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000035',2,0x00,'2005-11-01 00:00:00',NULL,'enrique.cano@justinmind.com','934037132','','00060','00012',NULL,'','PBC22/23','00006','00002',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000036',3,0x00,'2006-01-01 00:00:00',NULL,'enrique.cano@justinmind.com','34982','','00061','00023',NULL,'','PBA23','00005','00005',NULL,NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000037',2,0x00,'2007-06-21 00:00:00','2008-06-25 00:00:00','enrique.cano@justinmind.com','934034677','','00062','00012',NULL,'','PBA21','00006','00002',NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000038',3,0x00,'2005-10-01 00:00:00',NULL,'enrique.cano@justinmind.com','+34934034901','','00065','00004',NULL,'','PBB21','00006','00001','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000039',3,0x00,NULL,NULL,'enrique.cano@justinmind.com','934034901','','00066','00004',NULL,'','PBB21','00006','00001','00005',NULL,NULL,NULL,NULL,0x00),
 ('0000000000040',1,0x00,'2007-05-01 00:00:00',NULL,'enrique.cano@justinmind.com','34677','','00067','00012',NULL,'','',NULL,'00002',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000041',2,0x00,'1990-01-10 00:00:00','2025-08-09 00:00:00','enrique.cano@justinmind.com','934039942','','00069','00019',NULL,'','PBB12','00001','00004','00007',NULL,NULL,NULL,NULL,0x01),
 ('0000000000042',4,0x00,'2004-06-01 00:00:00',NULL,'enrique.cano@justinmind.com','934034901','','00070','00004',NULL,'','PBB21','00005','00001','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000043',3,0x00,'2004-09-01 00:00:00',NULL,'enrique.cano@justinmind.com','934034982','','00072','00023',NULL,'','PBA23','00006','00005',NULL,NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000044',3,0x00,'2003-10-01 00:00:00',NULL,'enrique.cano@justinmind.com','934034902','','00068','00004',NULL,'','PBB21','00001','00001','00004',NULL,NULL,NULL,NULL,0x01),
 ('0000000000045',2,0x00,'2004-01-01 00:00:00',NULL,'enrique.cano@justinmind.com','','','00073','00012',NULL,'','PBC22/23','00006','00002','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000046',2,0x00,'2006-01-02 00:00:00',NULL,'enrique.cano@justinmind.com','93 403 7132','','00075','00012',NULL,'','PBC22/23','00003','00002',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000047',1,0x00,'2005-12-01 00:00:00',NULL,'enrique.cano@justinmind.com','934034982','','00074','00024',NULL,'','PBA24','00006','00005','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000048',2,0x00,'2004-01-20 00:00:00',NULL,'enrique.cano@justinmind.com','+34 934034867','','00077','00005',NULL,'','PBB2Pas','00005','00001','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000049',3,0x00,'2004-06-01 00:00:00',NULL,'enrique.cano@justinmind.com','934034867','','00078','00005',NULL,'','',NULL,'00001',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000050',5,0x00,'2004-07-12 00:00:00',NULL,'enrique.cano@justinmind.com','934034867','','00079','00005',NULL,'','PBB2Pas',NULL,'00001',NULL,NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000051',1,0x00,'2003-09-15 00:00:00',NULL,'enrique.cano@justinmind.com','934034867','','00080','00005',NULL,'','PBB2Pas','00006','00001',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000052',3,0x00,'2005-05-01 00:00:00',NULL,'enrique.cano@justinmind.com','934034867','','00081','00005',NULL,'','PBB2Pas','00005','00001','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000053',1,0x00,'0005-09-01 00:00:00',NULL,'enrique.cano@justinmind.com','93 4034901','','00083','00004',NULL,'','',NULL,'00001',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000054',2,0x00,'2002-10-15 00:00:00',NULL,'enrique.cano@justinmind.com','934037188','','00085','00010',NULL,'','PBA21','00006','00002','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000055',2,0x00,'2007-01-08 00:00:00',NULL,'enrique.cano@justinmind.com','0034934037190','','00084','00010',NULL,'','PBA21','00006','00002',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000056',3,0x00,'2006-07-16 00:00:00',NULL,'enrique.cano@justinmind.com','93 4034867','','00086','00005',NULL,'','PBB2Pas','00003','00001','00004',NULL,NULL,NULL,NULL,0x01),
 ('0000000000057',2,0x00,'2007-01-01 00:00:00',NULL,'enrique.cano@justinmind.com','34867','','00087','00005',NULL,'','PBB2Pas','00009','00001','00001',NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000058',5,0x00,'2004-07-01 00:00:00',NULL,'enrique.cano@justinmind.com','39982','','00054',NULL,NULL,'','PSB13/14','00011','00001','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000059',1,0x00,'1990-01-10 00:00:00','2025-08-09 00:00:00','enrique.cano@justinmind.com','934039942','','00021','00019',NULL,'','PBB12','00001','00004','00007',NULL,NULL,NULL,NULL,0x01),
 ('0000000000060',3,0x00,'2006-09-01 00:00:00',NULL,'enrique.cano@justinmind.com','934034901','','00088','00004',NULL,'','PBB21','00006','00001',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000061',4,0x00,'2005-12-01 00:00:00',NULL,'enrique.cano@justinmind.com','93 403 7018','','00090','00003',NULL,'','PSB13/14','00005','00001',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000062',6,0x00,'2007-02-01 00:00:00',NULL,'enrique.cano@irbbarcelona.org','','','00089','00011',NULL,'','PBC32/33/34','00006','00002','00001','00006','','','',0x01),
 ('0000000000063',8,0x00,'2005-01-01 00:00:00','2007-07-31 00:00:00','enrique.cano@justinmind.com',NULL,NULL,'00091','00003',NULL,'','PSB13/14','00005','00001','00001',NULL,NULL,NULL,NULL,0x00),
 ('0000000000064',1,0x00,'2005-09-04 00:00:00',NULL,'enrique.cano@justinmind.com','93 403 70 18','','00092','00003',NULL,'','PSB13/14','00006','00001','00005',NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000065',1,0x00,'2004-10-04 00:00:00',NULL,'enrique.cano@justinmind.com','934037018','','00094','00003',NULL,'','PSB13/14','00006','00001','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000066',3,0x00,'2004-07-01 00:00:00',NULL,'enrique.cano@justinmind.com','934037018','','00093','00003',NULL,'','PSB13/14','00005','00001','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000067',2,0x00,'2002-01-08 00:00:00',NULL,'enrique.cano@justinmind.com','934037093','','00096','00021',NULL,'','P1B11/12','00001','00004','00003',NULL,NULL,NULL,NULL,0x01),
 ('0000000000068',2,0x00,'2002-01-08 00:00:00',NULL,'enrique.cano@justinmind.com','34813','','00095','00021',NULL,'','P1B11/12','00003','00004','00003',NULL,NULL,NULL,NULL,0x01),
 ('0000000000069',1,0x00,NULL,NULL,'enrique.cano@justinmind.com','','','00097',NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000070',2,0x00,'2002-09-02 00:00:00',NULL,'enrique.cano@justinmind.com','934037096','','00098','00021',NULL,'','P1B11/12','00005','00004',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000071',2,0x00,'2006-09-01 00:00:00','2011-05-01 00:00:00','enrique.cano@justinmind.com','934037096','','00099','00021',NULL,'','P1B11/12','00006','00004','00005',NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000072',3,0x00,'2002-09-01 00:00:00','2007-07-31 00:00:00','enrique.cano@justinmind.com','934037096','','00101','00021',NULL,'','P1B11/12',NULL,'00004',NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000073',1,0x00,'2004-03-12 00:00:00',NULL,'enrique.cano@justinmind.com','93 403 71 55','NO TENGO','00102','00011',NULL,'','PBC32/33/34','00010','00002','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000074',4,0x00,'2005-09-01 00:00:00','2009-03-01 00:00:00','enrique.cano@justinmind.com','934037096','','00100','00021',NULL,'','P1B11/12','00006','00004','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000075',2,0x00,'2006-06-12 00:00:00',NULL,'enrique.cano@justinmind.com','934039961','','00104','00025',NULL,'','PBB51/52','00006','00005','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000076',1,0x00,'0005-05-11 00:00:00',NULL,'enrique.cano@justinmind.com','934037018','','00105','00003',NULL,'','PSB13/14','00006','00001','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000077',1,0x00,'2006-09-01 00:00:00','2008-09-01 00:00:00','enrique.cano@justinmind.com','934039941','','00106',NULL,NULL,'','PBB12','00006','00004',NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000078',2,0x00,'2007-06-01 00:00:00',NULL,'enrique.cano@justinmind.com','934039961','','00108','00025',NULL,'','PBB51/52','00009','00005','00001',NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000079',1,0x00,'2007-02-19 00:00:00',NULL,'enrique.cano@justinmind.com','934039961','','00109','00025',NULL,'','PBB51/52','00003','00005','00001',NULL,NULL,NULL,NULL,0x01),
 ('0000000000080',14,0x00,'2006-08-28 00:00:00',NULL,'enrique.cano@justinmind.com','934039961','798798','00110','00025','00006','','PBB51/52','00008','00001','00007',NULL,NULL,NULL,NULL,0x01),
 ('0000000000081',14,0x00,'2002-04-02 00:00:00',NULL,'enrique.cano@justinmind.com','934037111','609766964','00111','00026','00004','','P1B1','00010','00017','00001','00009','','','',0x01),
 ('0000000000082',4,0x00,'2006-02-12 00:00:00','2011-05-01 00:00:00','enrique.cano@justinmind.com','93 403 70 96','','00112','00021',NULL,'','P1B11/12','00006','00004','00005',NULL,NULL,NULL,NULL,0x01),
 ('0000000000083',1,0x00,'2006-04-23 00:00:00',NULL,'enrique.cano@justinmind.com','934039941','','00113','00019',NULL,'','PBB12',NULL,'00004',NULL,NULL,NULL,NULL,NULL,0x01),
 ('0000000000084',3,0x01,'2007-06-25 00:00:00','2007-07-27 00:00:00','enrique.cano@justinmind.com','934037198','','00114','00017',NULL,'','PBA11/12/13',NULL,'00001',NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000085',9,0x00,'2007-07-01 00:00:00',NULL,'enrique.cano@justinmind.com','','','00001',NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00);
INSERT INTO `professional` VALUES  ('0000000000086',2,0x01,NULL,NULL,'enrique.cano@justinmind.com','','','00001',NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000087',5,0x00,NULL,NULL,'enrique.cano@justinmind.com','9183749','983247912843','00006','00001','00008','','PBA3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000088',3,0x00,NULL,NULL,'enrique.cano@justinmind.com','','','00007','00001',NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000089',11,0x00,'2008-03-19 00:00:00',NULL,'enrique.cano@justinmind.com','5787','8798','00118','00026','00002','','PBA23','00017','00011','00006',NULL,'','','',0x01),
 ('0000000000090',2,0x00,'2001-03-20 00:00:00','2001-10-17 00:00:00','enrique.cano@justinmind.com','2321312312','12321321','00122','00026','00006','','PBB51/52','00007','00011','00006',NULL,NULL,NULL,NULL,0x00),
 ('0000000000092',1,0x00,'2007-03-23 00:00:00',NULL,'enrique.cano@justinmind.com','89079','47689','00114','00026','00004','','PBA3','00009','00017','00006',NULL,NULL,NULL,NULL,0x01),
 ('0000000000093',3,0x00,'2005-03-07 00:00:00',NULL,'enrique.cano@justinmind.com','2321312312','628 29 44 07','00005','00026','00002','','PBB31','00003','00009','00004',NULL,NULL,NULL,NULL,0x01);
INSERT INTO `professional` VALUES  ('0000000000094',9,0x00,'2003-03-23 00:00:00','2008-12-02 00:00:00','enrique.cano@irbbarcelona.org','+34 93 40 06137','+34 678666666','00001','00026','00002','','P1B21/22/23/24','00009','00011','00009',NULL,'12345','+34 93 40 06137','+34 93 40 06137',0x01),
 ('0000000000095',2,0x01,'1990-03-14 00:00:00','2004-03-16 00:00:00','enrique.cano@justinmind.com','23423','432','00001','00026','00006','','PBB12','00005','00010','00007','00006',NULL,NULL,NULL,0x00),
 ('0000000000096',2,0x01,NULL,NULL,'enrique.cano@justinmind.com','','','00001',NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0x00),
 ('0000000000097',2,0x01,'2002-03-06 00:00:00',NULL,'enrique.cano@justinmind.com','79878','987987','00024','00026','00006','','PBB42','00013','00004','00006','00007',NULL,NULL,NULL,0x01),
 ('0000000000098',2,0x01,NULL,NULL,'enrique.cano@justinmind.com','','','00001',NULL,NULL,'','',NULL,NULL,NULL,NULL,'','','',0x00),
 ('0000000000101',2,0x01,'2008-05-30 00:00:00','2008-05-26 00:00:00','enrique.cano@justinmind.com','','','00001',NULL,NULL,'','',NULL,NULL,NULL,NULL,'','','',0x00),
 ('0000000000103',7,0x00,'2008-07-15 00:00:00',NULL,'enrique.cano@justinmind.com','4464737','5678','00136','00018','00002','','P1B13/14','00014','00011','00006','00006','','','',0x01);
INSERT INTO `professional` VALUES  ('0000000000104',2,0x01,'2008-07-18 00:00:00','2008-07-21 00:00:00','enrique.cano@justinmind.com','','','00136',NULL,'00004','','P1B22/23/24',NULL,NULL,NULL,NULL,'','','',0x00);
/*!40000 ALTER TABLE `professional` ENABLE KEYS */;





--
-- Definition of table `report`
--

DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `reportcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `name` varchar(100) default NULL,
  `date` datetime default NULL,
  `filename` varchar(100) default NULL,
  `type` varchar(100) default NULL,
  `is_public` bit(1) default '',
  `observations` text,
  `author` varchar(100) default NULL,
  PRIMARY KEY  (`reportcode`),
  KEY `FK8FDF49344C0D79E9` (`author`),
  CONSTRAINT `FK8FDF49344C0D79E9` FOREIGN KEY (`author`) REFERENCES `usuario` (`usuariocode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `report`
--

/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;


--
-- Definition of table `reportcolumn`
--

DROP TABLE IF EXISTS `reportcolumn`;
CREATE TABLE `reportcolumn` (
  `columncode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `name` varchar(255) default NULL,
  `assocCustomList` varchar(255) default NULL,
  PRIMARY KEY  (`columncode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reportcolumn`
--

/*!40000 ALTER TABLE `reportcolumn` DISABLE KEYS */;
INSERT INTO `reportcolumn` VALUES  ('0000000000001',1,0x01,'personalcode','0000000000001'),
 ('0000000000002',1,0x01,'name','0000000000001'),
 ('0000000000003',1,0x01,'surname1','0000000000001'),
 ('0000000000004',1,0x01,'surname2','0000000000001'),
 ('0000000000005',1,0x01,'personalcode','0000000000002'),
 ('0000000000006',1,0x01,'name','0000000000002'),
 ('0000000000007',1,0x01,'surname1','0000000000002'),
 ('0000000000008',0,0x00,'name','0000000000003'),
 ('0000000000009',0,0x00,'surname1','0000000000003'),
 ('0000000000010',0,0x00,'surname2','0000000000003'),
 ('0000000000011',0,0x00,'personalcode','0000000000004'),
 ('0000000000012',0,0x00,'name','0000000000004'),
 ('0000000000013',0,0x00,'surname1','0000000000004'),
 ('0000000000014',0,0x00,'surname2','0000000000004'),
 ('0000000000015',1,0x01,'name','0000000000005'),
 ('0000000000016',1,0x01,'personalcode','0000000000006'),
 ('0000000000017',1,0x01,'name','0000000000006'),
 ('0000000000018',1,0x01,'surname1','0000000000006'),
 ('0000000000019',0,0x00,'personalcode','0000000000007'),
 ('0000000000020',0,0x00,'name','0000000000007');
INSERT INTO `reportcolumn` VALUES  ('0000000000021',0,0x00,'surname1','0000000000007'),
 ('0000000000022',1,0x01,'personalcode','0000000000008'),
 ('0000000000023',1,0x01,'name','0000000000008'),
 ('0000000000024',1,0x01,'surname1','0000000000008'),
 ('0000000000025',0,0x00,'personalcode','0000000000009'),
 ('0000000000026',0,0x00,'name','0000000000009'),
 ('0000000000027',0,0x00,'surname1','0000000000009'),
 ('0000000000028',1,0x01,'personalcode','0000000000010'),
 ('0000000000029',1,0x01,'name','0000000000010'),
 ('0000000000030',1,0x01,'surname1','0000000000010'),
 ('0000000000031',1,0x01,'surname2','0000000000010'),
 ('0000000000032',1,0x01,'state','0000000000010'),
 ('0000000000033',1,0x01,'country','0000000000010'),
 ('0000000000034',0,0x00,'personalcode','0000000000011'),
 ('0000000000035',0,0x00,'name','0000000000011'),
 ('0000000000036',0,0x00,'surname1','0000000000011'),
 ('0000000000037',1,0x01,'personalcode','0000000000010'),
 ('0000000000038',1,0x01,'name','0000000000010'),
 ('0000000000039',1,0x01,'surname1','0000000000010');
INSERT INTO `reportcolumn` VALUES  ('0000000000040',1,0x01,'surname2','0000000000010'),
 ('0000000000041',1,0x01,'state','0000000000010'),
 ('0000000000042',1,0x01,'country','0000000000010'),
 ('0000000000043',1,0x01,'personalcode','0000000000010'),
 ('0000000000044',1,0x01,'name','0000000000010'),
 ('0000000000045',1,0x01,'surname1','0000000000010'),
 ('0000000000046',1,0x01,'surname2','0000000000010'),
 ('0000000000047',1,0x01,'state','0000000000010'),
 ('0000000000048',1,0x01,'name','0000000000010'),
 ('0000000000049',1,0x01,'personalcode','0000000000010'),
 ('0000000000050',1,0x01,'name','0000000000010'),
 ('0000000000051',1,0x01,'name','0000000000010'),
 ('0000000000052',1,0x01,'personalcode','0000000000010'),
 ('0000000000053',1,0x01,'name','0000000000010'),
 ('0000000000054',1,0x01,'name','0000000000010'),
 ('0000000000055',1,0x01,'name','0000000000010'),
 ('0000000000056',1,0x01,'name','0000000000010'),
 ('0000000000057',1,0x01,'country','0000000000010'),
 ('0000000000058',0,0x00,'name','0000000000012'),
 ('0000000000059',0,0x00,'country','0000000000012');
INSERT INTO `reportcolumn` VALUES  ('0000000000060',0,0x00,'surname1','0000000000012'),
 ('0000000000061',0,0x00,'state','0000000000012'),
 ('0000000000062',1,0x01,'personalcode','0000000000013'),
 ('0000000000063',1,0x01,'name','0000000000013'),
 ('0000000000064',1,0x01,'surname1','0000000000013'),
 ('0000000000065',1,0x01,'surname2','0000000000013'),
 ('0000000000066',0,0x00,'personalcode','0000000000014'),
 ('0000000000067',0,0x00,'name','0000000000014'),
 ('0000000000068',0,0x00,'surname1','0000000000014'),
 ('0000000000069',0,0x00,'surname2','0000000000014'),
 ('0000000000070',0,0x00,'state','0000000000014'),
 ('0000000000071',0,0x00,'country','0000000000014'),
 ('0000000000072',0,0x00,'name','0000000000015'),
 ('0000000000073',0,0x00,'surname1','0000000000015'),
 ('0000000000074',1,0x01,'personalcode','0000000000002'),
 ('0000000000075',1,0x01,'name','0000000000002'),
 ('0000000000076',1,0x01,'surname1','0000000000002'),
 ('0000000000077',1,0x01,'personalcode','0000000000002'),
 ('0000000000078',1,0x01,'name','0000000000002'),
 ('0000000000079',1,0x01,'surname1','0000000000002');
INSERT INTO `reportcolumn` VALUES  ('0000000000080',1,0x01,'personalcode','0000000000002'),
 ('0000000000081',1,0x01,'name','0000000000002'),
 ('0000000000082',1,0x01,'surname1','0000000000002'),
 ('0000000000083',1,0x01,'personalcode','0000000000002'),
 ('0000000000084',1,0x01,'name','0000000000002'),
 ('0000000000085',1,0x01,'surname1','0000000000002'),
 ('0000000000086',1,0x01,'personalcode','0000000000002'),
 ('0000000000087',1,0x01,'name','0000000000002'),
 ('0000000000088',1,0x01,'surname1','0000000000002'),
 ('0000000000089',0,0x00,'name','0000000000016'),
 ('0000000000090',0,0x00,'state','0000000000016'),
 ('0000000000091',1,0x01,'name','0000000000017'),
 ('0000000000092',1,0x01,'state','0000000000017'),
 ('0000000000093',0,0x00,'name','0000000000018'),
 ('0000000000094',0,0x00,'state','0000000000018'),
 ('0000000000095',1,0x01,'name','0000000000019'),
 ('0000000000096',1,0x01,'surname1','0000000000019'),
 ('0000000000097',1,0x01,'surname2','0000000000019'),
 ('0000000000098',0,0x00,'personalcode','0000000000006'),
 ('0000000000099',0,0x00,'name','0000000000006');
INSERT INTO `reportcolumn` VALUES  ('0000000000100',0,0x00,'surname1','0000000000006'),
 ('0000000000101',0,0x00,'name','0000000000020'),
 ('0000000000102',0,0x00,'surname','0000000000021'),
 ('0000000000103',0,0x00,'name','0000000000021'),
 ('0000000000104',0,0x00,'name','0000000000022'),
 ('0000000000105',0,0x00,'surname1','0000000000022'),
 ('0000000000106',0,0x00,'surname2','0000000000022'),
 ('0000000000107',1,0x01,'start_date','0000000000023'),
 ('0000000000108',1,0x01,'end_date','0000000000023'),
 ('0000000000109',1,0x01,'description','0000000000023'),
 ('0000000000110',1,0x01,'amount','0000000000023'),
 ('0000000000111',0,0x00,'start_date','0000000000023'),
 ('0000000000112',0,0x00,'description','0000000000023'),
 ('0000000000113',0,0x00,'amount','0000000000023'),
 ('0000000000114',0,0x00,'start_date','0000000000024'),
 ('0000000000115',0,0x00,'description','0000000000024'),
 ('0000000000116',0,0x00,'amount','0000000000024'),
 ('0000000000117',1,0x01,'personalcode','0000000000002'),
 ('0000000000118',1,0x01,'name','0000000000002');
INSERT INTO `reportcolumn` VALUES  ('0000000000119',1,0x01,'surname1','0000000000002'),
 ('0000000000120',0,0x00,'personalcode','0000000000002'),
 ('0000000000121',0,0x00,'name','0000000000002'),
 ('0000000000122',0,0x00,'surname1','0000000000002'),
 ('0000000000123',0,0x00,'personalcode','0000000000008'),
 ('0000000000124',0,0x00,'name','0000000000008'),
 ('0000000000125',0,0x00,'surname1','0000000000008'),
 ('0000000000126',0,0x00,'name','0000000000005'),
 ('0000000000127',1,0x01,'amount','0000000000025'),
 ('0000000000128',1,0x01,'description','0000000000025'),
 ('0000000000129',1,0x01,'start_date','0000000000025'),
 ('0000000000130',1,0x01,'end_date','0000000000025'),
 ('0000000000131',1,0x01,'amount','0000000000025'),
 ('0000000000132',1,0x01,'description','0000000000025'),
 ('0000000000133',1,0x01,'start_date','0000000000025'),
 ('0000000000134',1,0x01,'end_date','0000000000025'),
 ('0000000000135',0,0x00,'amount','0000000000025'),
 ('0000000000136',0,0x00,'description','0000000000025'),
 ('0000000000137',0,0x00,'start_date','0000000000025');
INSERT INTO `reportcolumn` VALUES  ('0000000000138',0,0x00,'end_date','0000000000025'),
 ('0000000000139',1,0x01,'start_date','0000000000026'),
 ('0000000000140',1,0x01,'end_date','0000000000026'),
 ('0000000000141',1,0x01,'description','0000000000026'),
 ('0000000000142',1,0x01,'amount','0000000000026'),
 ('0000000000143',0,0x00,'start_date','0000000000027'),
 ('0000000000144',0,0x00,'end_date','0000000000027'),
 ('0000000000145',0,0x00,'description','0000000000027'),
 ('0000000000146',0,0x00,'amount','0000000000027'),
 ('0000000000147',0,0x00,'start_date','0000000000026'),
 ('0000000000148',0,0x00,'end_date','0000000000026'),
 ('0000000000149',0,0x00,'description','0000000000026'),
 ('0000000000150',0,0x00,'amount','0000000000026'),
 ('0000000000151',0,0x00,'personalcode','0000000000028'),
 ('0000000000152',0,0x00,'name','0000000000028'),
 ('0000000000153',0,0x00,'surname1','0000000000028'),
 ('0000000000154',0,0x00,'surname2','0000000000028'),
 ('0000000000155',0,0x00,'state','0000000000028'),
 ('0000000000156',0,0x00,'country','0000000000028');
INSERT INTO `reportcolumn` VALUES  ('0000000000157',0,0x00,'personalcode','0000000000029'),
 ('0000000000158',0,0x00,'name','0000000000029'),
 ('0000000000159',0,0x00,'surname1','0000000000029'),
 ('0000000000160',0,0x00,'surname2','0000000000029'),
 ('0000000000161',0,0x00,'dni','0000000000029'),
 ('0000000000162',0,0x00,'birth_date','0000000000029'),
 ('0000000000163',0,0x00,'birth_city','0000000000029'),
 ('0000000000164',0,0x00,'street','0000000000029'),
 ('0000000000165',0,0x00,'city','0000000000029'),
 ('0000000000166',0,0x00,'zip_code','0000000000029'),
 ('0000000000167',0,0x00,'phone','0000000000029'),
 ('0000000000168',0,0x00,'phone2','0000000000029'),
 ('0000000000169',0,0x00,'ice_phone','0000000000029'),
 ('0000000000170',0,0x00,'ss_number','0000000000029'),
 ('0000000000171',0,0x00,'bank_account','0000000000029'),
 ('0000000000172',0,0x00,'research_project','0000000000029'),
 ('0000000000173',0,0x00,'sponsoring_agency','0000000000029'),
 ('0000000000174',0,0x00,'holding_institution','0000000000029'),
 ('0000000000175',0,0x00,'principal_investigator','0000000000029');
INSERT INTO `reportcolumn` VALUES  ('0000000000176',0,0x00,'end_of_contract_reason','0000000000029'),
 ('0000000000177',0,0x00,'end_of_contract_address','0000000000029'),
 ('0000000000178',0,0x00,'end_of_contract_city','0000000000029'),
 ('0000000000179',0,0x00,'end_of_contract_zip_code','0000000000029'),
 ('0000000000180',0,0x00,'end_of_contract_phone','0000000000029'),
 ('0000000000181',0,0x00,'end_of_contract_email','0000000000029'),
 ('0000000000182',0,0x00,'end_of_contract_country','0000000000029'),
 ('0000000000183',0,0x00,'birth_country','0000000000029'),
 ('0000000000184',0,0x00,'nationality','0000000000029'),
 ('0000000000185',0,0x00,'nationality_2','0000000000029'),
 ('0000000000186',0,0x00,'country','0000000000029'),
 ('0000000000187',0,0x00,'payments','0000000000029'),
 ('0000000000188',0,0x00,'gender','0000000000029'),
 ('0000000000189',0,0x00,'marital_status','0000000000029'),
 ('0000000000190',0,0x00,'bank','0000000000029'),
 ('0000000000191',0,0x00,'working_hours','0000000000029'),
 ('0000000000192',0,0x00,'category','0000000000029');
INSERT INTO `reportcolumn` VALUES  ('0000000000193',0,0x00,'state','0000000000029'),
 ('0000000000194',0,0x00,'personal_email','0000000000029'),
 ('0000000000195',0,0x00,'language','0000000000029'),
 ('0000000000196',0,0x00,'start_date','0000000000029'),
 ('0000000000197',0,0x00,'end_date','0000000000029'),
 ('0000000000198',0,0x00,'location','0000000000029'),
 ('0000000000199',0,0x00,'email','0000000000029'),
 ('0000000000200',0,0x00,'professional_phone','0000000000029'),
 ('0000000000201',0,0x00,'mobile_long','0000000000029'),
 ('0000000000202',0,0x00,'mobile_short','0000000000029'),
 ('0000000000203',0,0x00,'lab_phone_number','0000000000029'),
 ('0000000000204',0,0x00,'fax','0000000000029'),
 ('0000000000205',0,0x00,'type_of_contract','0000000000029'),
 ('0000000000206',0,0x00,'contract_is_irbs','0000000000029'),
 ('0000000000207',0,0x00,'position','0000000000029'),
 ('0000000000208',0,0x00,'payroll_institution','0000000000029'),
 ('0000000000209',0,0x00,'payroll_institution_2','0000000000029'),
 ('0000000000210',0,0x00,'research_group','0000000000029');
INSERT INTO `reportcolumn` VALUES  ('0000000000211',0,0x00,'professional_unit','0000000000029'),
 ('0000000000212',0,0x00,'organization_unit','0000000000029');
/*!40000 ALTER TABLE `reportcolumn` ENABLE KEYS */;


--
-- Definition of table `reportcustomlist`
--

DROP TABLE IF EXISTS `reportcustomlist`;
CREATE TABLE `reportcustomlist` (
  `customListcode` varchar(255) NOT NULL default '',
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `name` varchar(255) default NULL,
  `view_name` varchar(255) default NULL,
  `usercode` varchar(25) default NULL,
  `update_date` datetime default NULL,
  `comments` varchar(255) default NULL,
  PRIMARY KEY  (`customListcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reportcustomlist`
--

/*!40000 ALTER TABLE `reportcustomlist` DISABLE KEYS */;
INSERT INTO `reportcustomlist` VALUES  ('0000000000001',1,0x01,'list1','view_all_personal','99999','2008-07-18 11:15:18','this is the first list'),
 ('0000000000002',3,0x00,'pr2','view_all_personal','99999','2008-07-30 10:37:06','sss'),
 ('0000000000003',0,0x00,'third l','view_all_personal','99999','2008-07-18 12:30:02','aallld'),
 ('0000000000004',0,0x00,'asaasds','view_all_personal','99999','2008-07-18 12:36:28','dftr'),
 ('0000000000005',1,0x00,'asd','view_all_personal','99999','2008-08-01 10:51:16',''),
 ('0000000000006',1,0x00,'fghjfjfj','view_all_personal','99999','2008-07-25 13:03:16','fjjffgj'),
 ('0000000000007',0,0x00,'list_Ok','view_all_personal','99999','2008-07-21 12:33:40','ja guarda values filter'),
 ('0000000000008',1,0x00,'list__','view_all_personal','99999','2008-08-01 10:48:17','code similar 1'),
 ('0000000000009',0,0x00,'l2','view_all_personal','99999','2008-07-21 13:50:02','version > 1'),
 ('0000000000010',1,0x01,'asfasdf','view_all_personal','99999','2008-07-22 11:27:25','asdfasdf'),
 ('0000000000011',0,0x00,'fgghfgfgh','view_all_personal','99999','2008-07-22 13:25:19','');
INSERT INTO `reportcustomlist` VALUES  ('0000000000012',0,0x00,'list similar a la 10','view_all_personal','99999','2008-07-23 11:06:52','prova de save as from saved list'),
 ('0000000000013',1,0x01,'uuu','view_all_personal','99999','2008-07-23 11:10:17','u'),
 ('0000000000014',0,0x00,'name 00001','view_all_personal','99999','2008-07-24 12:26:41',''),
 ('0000000000015',0,0x00,'check between','view_all_personal','99999','2008-07-24 12:58:35',''),
 ('0000000000016',0,0x00,'between 2','view_all_personal','99999','2008-07-25 12:43:57','entre 2 i 20'),
 ('0000000000017',1,0x01,'between 2 ','view_all_personal','99999','2008-07-25 12:44:45','desar com a ,'),
 ('0000000000018',0,0x00,'22','view_all_personal','99999','2008-07-25 12:47:12',''),
 ('0000000000019',1,0x01,'ty','view_all_personal','99999','2008-07-25 12:59:30',''),
 ('0000000000020',0,0x00,'gdgf','view_all_personal','99999','2008-07-25 13:03:24',''),
 ('0000000000021',0,0x00,'view 2','view_little_personal','99999','2008-07-25 13:17:49','between date'),
 ('0000000000022',0,0x00,'sense filtre versio','view_all_personal','99999','2008-07-29 11:15:46','');
INSERT INTO `reportcustomlist` VALUES  ('0000000000023',1,0x00,'compensation','view_compensation','99999','2008-07-29 12:20:12','between a big decimal'),
 ('0000000000024',0,0x00,'copia anterior','view_compensation','99999','2008-07-29 12:20:44',''),
 ('0000000000025',2,0x00,'llistat amb nouva vista','view_compensation','99999','2008-08-01 11:38:30',''),
 ('0000000000026',1,0x00,'gh','view_compensation','99999','2008-08-11 11:05:14','hj'),
 ('0000000000027',0,0x00,'wqqwe','view_compensation','99999','2008-08-11 11:05:11','we'),
 ('0000000000028',0,0x00,'Prueba petada','view_all_personal','99999','2008-09-09 12:18:41',''),
 ('0000000000029',0,0x00,'Personal con nacionalidad española','view_listado_personal_professional','00001','2008-11-03 09:57:01','');
/*!40000 ALTER TABLE `reportcustomlist` ENABLE KEYS */;


--
-- Definition of table `reportfilter`
--

DROP TABLE IF EXISTS `reportfilter`;
CREATE TABLE `reportfilter` (
  `filtercode` varchar(255) NOT NULL default '',
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `name` varchar(255) default NULL,
  `value` varchar(255) default NULL,
  `assocCustomList` varchar(255) default NULL,
  `assocFilterType` varchar(255) default NULL,
  `type` varchar(45) default NULL,
  PRIMARY KEY  (`filtercode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reportfilter`
--

/*!40000 ALTER TABLE `reportfilter` DISABLE KEYS */;
INSERT INTO `reportfilter` VALUES  ('0000000000001',1,0x01,'personalcode','1','0000000000001','like','String'),
 ('0000000000002',1,0x01,'version','1','0000000000001','greater','Integer'),
 ('0000000000003',1,0x01,'country','Spain','0000000000001','equal','String'),
 ('0000000000004',1,0x01,'personalcode','1','0000000000002','like','String'),
 ('0000000000005',1,0x01,'version','1','0000000000002','greater','Integer'),
 ('0000000000006',0,0x00,'version','1','0000000000003','greater','Integer'),
 ('0000000000007',0,0x00,'name','1','0000000000003','like','String'),
 ('0000000000008',0,0x00,'deleted','No','0000000000003','equal','Boolean'),
 ('0000000000009',0,0x00,'personalcode','1','0000000000007','like','String'),
 ('0000000000010',0,0x00,'version','1','0000000000007','greater','Integer'),
 ('0000000000011',0,0x00,'country','Spain','0000000000007','equal','String'),
 ('0000000000012',0,0x00,'deleted','false','0000000000007','equal','Boolean'),
 ('0000000000013',1,0x01,'personalcode','1','0000000000008','like','String'),
 ('0000000000014',0,0x00,'version','1','0000000000009','greater','Integer');
INSERT INTO `reportfilter` VALUES  ('0000000000015',1,0x01,'country','Spain','0000000000010','equal','String'),
 ('0000000000016',0,0x00,'version','1','0000000000011','greaterEqual','Integer'),
 ('0000000000017',0,0x00,'version','20','0000000000011','lowerEqual','Integer'),
 ('0000000000018',1,0x01,'country','Spain','0000000000010','equal','String'),
 ('0000000000019',1,0x01,'country','Spain','0000000000010','equal','String'),
 ('0000000000020',1,0x01,'personalcode','1','0000000000010','like','String'),
 ('0000000000021',1,0x01,'version','1','0000000000010','greater','Integer'),
 ('0000000000022',0,0x00,'personalcode','1','0000000000012','like','String'),
 ('0000000000023',0,0x00,'version','1','0000000000012','greater','Integer'),
 ('0000000000024',0,0x00,'name','1','0000000000012','like','String'),
 ('0000000000025',0,0x00,'country','Spain','0000000000012','equal','String'),
 ('0000000000026',0,0x00,'deleted','No','0000000000012','equal','Boolean'),
 ('0000000000027',0,0x00,'name','00001','0000000000014','like','String'),
 ('0000000000028',0,0x00,'version','1','0000000000015','greaterEqual','Integer');
INSERT INTO `reportfilter` VALUES  ('0000000000029',0,0x00,'version','8','0000000000015','lowerEqual','Integer'),
 ('0000000000030',0,0x00,'version','2','0000000000016','greaterEqual','Integer'),
 ('0000000000031',0,0x00,'version','3','0000000000016','lowerEqual','Integer'),
 ('0000000000032',0,0x00,'personalcode','1','0000000000021','like','String'),
 ('0000000000033',0,0x00,'birth_date','11/07/1973','0000000000021','lowerEqual','Date'),
 ('0000000000034',0,0x00,'birth_date','04/07/1951','0000000000021','greaterEqual','Date'),
 ('0000000000035',0,0x00,'name','1','0000000000022','like','String'),
 ('0000000000036',1,0x01,'amount','8.000,00','0000000000023','lowerEqual','BigDecimal'),
 ('0000000000037',1,0x01,'amount','1,00','0000000000023','greaterEqual','BigDecimal'),
 ('0000000000038',0,0x00,'amount','8.000,00','0000000000023','lowerEqual','BigDecimal'),
 ('0000000000039',0,0x00,'amount','1,00','0000000000023','greaterEqual','BigDecimal'),
 ('0000000000040',0,0x00,'amount','8.000,00','0000000000024','lowerEqual','BigDecimal'),
 ('0000000000041',0,0x00,'amount','1,00','0000000000024','greaterEqual','BigDecimal');
INSERT INTO `reportfilter` VALUES  ('0000000000042',1,0x01,'amount','10.000,00','0000000000025','lowerEqual','BigDecimal'),
 ('0000000000043',1,0x01,'start_date','01/08/2008','0000000000025','lowerEqual','Date'),
 ('0000000000044',1,0x01,'end_date','01/08/2008','0000000000025','lowerEqual','Date'),
 ('0000000000045',1,0x01,'amount','1,00','0000000000025','greaterEqual','BigDecimal'),
 ('0000000000046',1,0x01,'end_date','01/08/1989','0000000000025','greaterEqual','Date'),
 ('0000000000047',1,0x01,'amount','10.000.000,00','0000000000025','lowerEqual','BigDecimal'),
 ('0000000000048',1,0x01,'start_date','01/08/2008','0000000000025','lowerEqual','Date'),
 ('0000000000049',1,0x01,'end_date','01/08/2008','0000000000025','lowerEqual','Date'),
 ('0000000000050',1,0x01,'amount','1,00','0000000000025','greaterEqual','BigDecimal'),
 ('0000000000051',1,0x01,'end_date','01/08/1989','0000000000025','greaterEqual','Date'),
 ('0000000000052',0,0x00,'amount','1,000,000.00','0000000000025','lowerEqual','BigDecimal'),
 ('0000000000053',0,0x00,'start_date','01/08/2008','0000000000025','lowerEqual','Date');
INSERT INTO `reportfilter` VALUES  ('0000000000054',0,0x00,'end_date','01/08/2008','0000000000025','lowerEqual','Date'),
 ('0000000000055',0,0x00,'amount','100.00','0000000000025','greaterEqual','BigDecimal'),
 ('0000000000056',0,0x00,'end_date','01/08/1989','0000000000025','greaterEqual','Date'),
 ('0000000000057',1,0x01,'amount','99.999.999.999.999,98','0000000000026','lowerEqual','BigDecimal'),
 ('0000000000058',1,0x01,'amount','1,00','0000000000026','greaterEqual','BigDecimal'),
 ('0000000000059',0,0x00,'amount','99999999999999.98','0000000000027','lowerEqual','BigDecimal'),
 ('0000000000060',0,0x00,'amount','1','0000000000027','greaterEqual','BigDecimal'),
 ('0000000000061',0,0x00,'amount','99999999999999.98','0000000000026','lowerEqual','BigDecimal'),
 ('0000000000062',0,0x00,'amount','1','0000000000026','greaterEqual','BigDecimal'),
 ('0000000000063',0,0x00,'nationality','Spain','0000000000029','equal','String');
/*!40000 ALTER TABLE `reportfilter` ENABLE KEYS */;


--
-- Definition of table `reportfiltertype`
--

DROP TABLE IF EXISTS `reportfiltertype`;
CREATE TABLE `reportfiltertype` (
  `filterTypecode` varchar(255) NOT NULL default '',
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`filterTypecode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reportfiltertype`
--

/*!40000 ALTER TABLE `reportfiltertype` DISABLE KEYS */;
INSERT INTO `reportfiltertype` VALUES  ('equal',1,0x00,'equal'),
 ('greater',1,0x00,'greater'),
 ('greaterEqual',1,0x00,'greaterEqual'),
 ('like',1,0x00,'like'),
 ('lower',1,0x00,'lower'),
 ('lowerEqual',1,0x00,'lowerEqual');
/*!40000 ALTER TABLE `reportfiltertype` ENABLE KEYS */;


--
-- Definition of table `reportorderby`
--

DROP TABLE IF EXISTS `reportorderby`;
CREATE TABLE `reportorderby` (
  `orderBycode` varchar(255) NOT NULL default '',
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `name` varchar(255) default NULL,
  `assocCustomList` varchar(255) default NULL,
  PRIMARY KEY  (`orderBycode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reportorderby`
--

/*!40000 ALTER TABLE `reportorderby` DISABLE KEYS */;
INSERT INTO `reportorderby` VALUES  ('0000000000001',1,0x01,'name','0000000000001'),
 ('0000000000002',1,0x01,'surname1','0000000000001'),
 ('0000000000003',1,0x01,'name','0000000000002'),
 ('0000000000004',0,0x00,'name','0000000000003'),
 ('0000000000005',0,0x00,'surname1','0000000000003'),
 ('0000000000006',0,0x00,'surname1','0000000000004'),
 ('0000000000007',1,0x01,'surname1','0000000000005'),
 ('0000000000008',1,0x01,'personalcode','0000000000006'),
 ('0000000000009',0,0x00,'name','0000000000007'),
 ('0000000000010',1,0x01,'personalcode','0000000000010'),
 ('0000000000011',1,0x01,'personalcode','0000000000010'),
 ('0000000000012',0,0x00,'personalcode','0000000000012'),
 ('0000000000013',0,0x00,'name','0000000000012'),
 ('0000000000014',0,0x00,'surname1','0000000000012'),
 ('0000000000015',1,0x01,'name','0000000000013'),
 ('0000000000016',1,0x01,'name','0000000000002'),
 ('0000000000017',1,0x01,'name','0000000000002'),
 ('0000000000018',1,0x01,'name','0000000000002'),
 ('0000000000019',1,0x01,'name','0000000000002'),
 ('0000000000020',1,0x01,'name','0000000000002');
INSERT INTO `reportorderby` VALUES  ('0000000000021',1,0x01,'name','0000000000019'),
 ('0000000000022',1,0x01,'surname1','0000000000019'),
 ('0000000000023',0,0x00,'personalcode','0000000000006'),
 ('0000000000024',0,0x00,'surname1','0000000000020'),
 ('0000000000025',0,0x00,'name','0000000000022'),
 ('0000000000026',0,0x00,'surname1','0000000000022'),
 ('0000000000027',1,0x01,'amount','0000000000023'),
 ('0000000000028',0,0x00,'amount','0000000000023'),
 ('0000000000029',0,0x00,'amount','0000000000024'),
 ('0000000000030',1,0x01,'name','0000000000002'),
 ('0000000000031',1,0x01,'surname1','0000000000002'),
 ('0000000000032',0,0x00,'name','0000000000002'),
 ('0000000000033',0,0x00,'surname1','0000000000005'),
 ('0000000000034',1,0x01,'amount','0000000000025'),
 ('0000000000035',1,0x01,'start_date','0000000000025'),
 ('0000000000036',1,0x01,'amount','0000000000025'),
 ('0000000000037',1,0x01,'start_date','0000000000025'),
 ('0000000000038',0,0x00,'amount','0000000000025'),
 ('0000000000039',0,0x00,'start_date','0000000000025'),
 ('0000000000040',0,0x00,'personalcode','0000000000029');
INSERT INTO `reportorderby` VALUES  ('0000000000041',0,0x00,'name','0000000000029'),
 ('0000000000042',0,0x00,'surname1','0000000000029'),
 ('0000000000043',0,0x00,'surname2','0000000000029'),
 ('0000000000044',0,0x00,'dni','0000000000029'),
 ('0000000000045',0,0x00,'birth_date','0000000000029'),
 ('0000000000046',0,0x00,'birth_city','0000000000029'),
 ('0000000000047',0,0x00,'street','0000000000029'),
 ('0000000000048',0,0x00,'city','0000000000029'),
 ('0000000000049',0,0x00,'zip_code','0000000000029'),
 ('0000000000050',0,0x00,'phone','0000000000029'),
 ('0000000000051',0,0x00,'phone2','0000000000029'),
 ('0000000000052',0,0x00,'ice_phone','0000000000029'),
 ('0000000000053',0,0x00,'ss_number','0000000000029'),
 ('0000000000054',0,0x00,'bank_account','0000000000029'),
 ('0000000000055',0,0x00,'research_project','0000000000029'),
 ('0000000000056',0,0x00,'sponsoring_agency','0000000000029'),
 ('0000000000057',0,0x00,'holding_institution','0000000000029'),
 ('0000000000058',0,0x00,'principal_investigator','0000000000029'),
 ('0000000000059',0,0x00,'end_of_contract_reason','0000000000029');
INSERT INTO `reportorderby` VALUES  ('0000000000060',0,0x00,'end_of_contract_address','0000000000029'),
 ('0000000000061',0,0x00,'end_of_contract_city','0000000000029'),
 ('0000000000062',0,0x00,'end_of_contract_zip_code','0000000000029'),
 ('0000000000063',0,0x00,'end_of_contract_phone','0000000000029'),
 ('0000000000064',0,0x00,'end_of_contract_email','0000000000029'),
 ('0000000000065',0,0x00,'end_of_contract_country','0000000000029'),
 ('0000000000066',0,0x00,'birth_country','0000000000029'),
 ('0000000000067',0,0x00,'nationality','0000000000029'),
 ('0000000000068',0,0x00,'nationality_2','0000000000029'),
 ('0000000000069',0,0x00,'country','0000000000029'),
 ('0000000000070',0,0x00,'payments','0000000000029'),
 ('0000000000071',0,0x00,'gender','0000000000029'),
 ('0000000000072',0,0x00,'marital_status','0000000000029'),
 ('0000000000073',0,0x00,'bank','0000000000029'),
 ('0000000000074',0,0x00,'working_hours','0000000000029'),
 ('0000000000075',0,0x00,'category','0000000000029'),
 ('0000000000076',0,0x00,'state','0000000000029');
INSERT INTO `reportorderby` VALUES  ('0000000000077',0,0x00,'personal_email','0000000000029'),
 ('0000000000078',0,0x00,'language','0000000000029'),
 ('0000000000079',0,0x00,'start_date','0000000000029'),
 ('0000000000080',0,0x00,'end_date','0000000000029'),
 ('0000000000081',0,0x00,'location','0000000000029'),
 ('0000000000082',0,0x00,'email','0000000000029'),
 ('0000000000083',0,0x00,'professional_phone','0000000000029'),
 ('0000000000084',0,0x00,'mobile_long','0000000000029'),
 ('0000000000085',0,0x00,'mobile_short','0000000000029'),
 ('0000000000086',0,0x00,'lab_phone_number','0000000000029'),
 ('0000000000087',0,0x00,'fax','0000000000029'),
 ('0000000000088',0,0x00,'type_of_contract','0000000000029'),
 ('0000000000089',0,0x00,'contract_is_irbs','0000000000029'),
 ('0000000000090',0,0x00,'position','0000000000029'),
 ('0000000000091',0,0x00,'payroll_institution','0000000000029'),
 ('0000000000092',0,0x00,'payroll_institution_2','0000000000029'),
 ('0000000000093',0,0x00,'research_group','0000000000029'),
 ('0000000000094',0,0x00,'professional_unit','0000000000029');
INSERT INTO `reportorderby` VALUES  ('0000000000095',0,0x00,'organization_unit','0000000000029');
/*!40000 ALTER TABLE `reportorderby` ENABLE KEYS */;


--
-- Definition of table `reportparameter`
--

DROP TABLE IF EXISTS `reportparameter`;
CREATE TABLE `reportparameter` (
  `reportparametercode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `class_type` varchar(100) default NULL,
  `value` varchar(100) default NULL,
  `report` varchar(255) default NULL,
  PRIMARY KEY  (`reportparametercode`),
  KEY `FK85C494D55C33DF38` (`report`),
  CONSTRAINT `FK85C494D55C33DF38` FOREIGN KEY (`report`) REFERENCES `report` (`reportcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reportparameter`
--

/*!40000 ALTER TABLE `reportparameter` DISABLE KEYS */;
/*!40000 ALTER TABLE `reportparameter` ENABLE KEYS */;


--
-- Definition of table `research_group`
--

DROP TABLE IF EXISTS `research_group`;
CREATE TABLE `research_group` (
  `research_groupcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(255) default NULL,
  `group` varchar(255) default NULL,
  `supervisor` varchar(255) default NULL,
  `unit` varchar(255) default NULL,
  PRIMARY KEY  (`research_groupcode`),
  KEY `FK7E0432BBA3EFDBD8` (`unit`),
  KEY `FK7E0432BB6E1BA78` (`supervisor`),
  CONSTRAINT `FK7E0432BB6E1BA78` FOREIGN KEY (`supervisor`) REFERENCES `personal` (`personalcode`),
  CONSTRAINT `FK7E0432BBA3EFDBD8` FOREIGN KEY (`unit`) REFERENCES `unit` (`unitcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `research_group`
--

/*!40000 ALTER TABLE `research_group` DISABLE KEYS */;
INSERT INTO `research_group` (`research_groupcode`, `version`, `deleted`, `description`, `group`, `supervisor`, `unit`) VALUES
('00001', 75, '\0', 'Ferran Azorin: Chromatin structure and function', '', '00399', '00001'),
('00002', 30, '\0', 'Jordi Casanova: Morphogenesis in Drosophila', 'mordro', '00004', '00001'),
('00003', 37, '\0', 'Cayetano Gonzalez: Cell division laboratory', '', '00005', '00001'),
('00004', 20, 0x01, 'Marco Milan: Developmental biology of Drosophila', '', '00394', '00001'),
('00005', 39, '\0', 'Lluis Ribas de Pouplana: Gene translation laboratory', 'ribas', '00007', '00001'),
('00006', 76, '\0', 'Eduardo Soriano: Neurobiology and regeneration', '', '00008', '00001'),
('00007', 44, '\0', 'Patrick Aloy: Structural bioinformatics', 'paloy', '00009', '00002'),
('00008', 67, '\0', 'Miquel Coll: Proteins, nucleic acids and complexes', 'coll', '00010', '00002'),
('00009', 29, '\0', 'Ignasi Fita: Macromolecular aggregates', '', '00011', '00002'),
('00010', 35, '\0', 'Maria J. Macias: Biomolecular NMR spectroscopy', 'macias', '00012', '00002'),
('00011', 91, '\0', 'Modesto Orozco: Molecular modelling and bioinformatics', 'orozco', '00013', '00002'),
('00012', 26, 0x01, 'Miquel Pons: Biomolecular NMR', '', '00433', '00002'),
('00013', 30, '\0', 'Carme Caelles: Cell signalling', '', '00015', '00003'),
('00014', 58, '\0', 'Antonio Celada:Gene expression', 'genexp', '00016', '00003'),
('00015', 92, '\0', 'Joan J. Guinovart: Metabolic engineering and diabetes', 'metengdia', '00017', '00003'),
('00016', 60, '\0', 'Manuel Palacin: Heterogenic and multigenic diseases', 'hetmuldis', '00018', '00003'),
('00017', 61, '\0', 'Antonio Zorzano: Heterogenic and polygenic diseases', 'hetpoldis', '00019', '00003'),
('00018', 89, '\0', 'Fernando Albericio: Combinatorial chemistry', 'albericio', '00020', '00004'),
('00019', 35, '\0', 'Ramon Eritja: Nucleic acid chemistry', 'nucaciche', '00021', '00004'),
('00020', 88, '\0', 'Ernest Giralt: Peptides and proteins', 'giralt', '00022', '00004'),
('00021', 59, '\0', 'Antoni Riera: Asymmetric synthesis', '', '00023', '00004'),
('00022', 53, '\0', 'Màrius Rubiralta: Peptidomimetic and heterocycles', 'pephet', '00024', '00004'),
('00023', 27, '\0', 'Eduard Batlle: Colorectal cancer laboratory I', 'colcani', '00025', '00005'),
('00024', 20, '\0', 'Elena Sancho: Colorectal cancer laboratory II', 'colcanii', '00026', '00005'),
('00025', 24, '\0', 'MetLab: Growth control and cancer metastasis', 'metlab', '00027', '00005'),
('00026', 15, '\0', 'Jens Luders: Microtubule organization', '', '00509', '00001'),
('00027', 35, '\0', 'Miquel Pons: Biomolecular NMR ', '', '00433', '00002'),
('00028', 49, '\0', 'Marco Milan: Development and Growth Control Laboratory', 'devbiodro', '00394', '00001'),
('00029', 14, '\0', 'Experimental Bioinformatics Laboratory', '', '00432', '00002'),
('00030', 1, 0x01, '', '', NULL, NULL),
('00031', 9, '\0', 'Xavier Salvatella: Molecular Biophysics', '', '00616', '00004');

/*!40000 ALTER TABLE `research_group` ENABLE KEYS */;


--
-- Definition of table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `entitycode` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `type` varchar(100) default NULL,
  PRIMARY KEY  (`entitycode`),
  KEY `FK358076A2B4A8DC` (`entitycode`),
  CONSTRAINT `FK358076A2B4A8DC` FOREIGN KEY (`entitycode`) REFERENCES `entity` (`entitycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES  ('administrator','Autogenerated rol which can access to the user management menu in the generated prototype',NULL),
 ('basic','',NULL),
 ('guest','',NULL),
 ('irbpeople_ro','irbpeople_ro',NULL),
 ('irbpeople_rw','irbpeople_rw',NULL),
 ('supervisor','supervisor',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


--
-- Definition of table `table_grant`
--

DROP TABLE IF EXISTS `table_grant`;
CREATE TABLE `table_grant` (
  `grantcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  `type_of_grant` varchar(255) default NULL,
  `is_irbs` bit(1) default NULL,
  PRIMARY KEY  (`grantcode`),
  KEY `FKDA67752B32537162` (`type_of_grant`),
  CONSTRAINT `FKDA67752B32537162` FOREIGN KEY (`type_of_grant`) REFERENCES `type_of_grant` (`type_of_grantcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `table_grant`
--

/*!40000 ALTER TABLE `table_grant` DISABLE KEYS */;
INSERT INTO `table_grant` VALUES  ('0000000000001',2,0x01,'aaah','00002',0x01),
 ('0000000000002',2,0x01,'bnbbb','00002',0x01),
 ('0000000000003',2,0x01,'sdgjk','00002',0x01),
 ('00001',21,0x00,'IRB Grant22','00002',0x01),
 ('00002',9,0x00,'PCB/IRB Grant','00001',0x01),
 ('00003',9,0x00,'FPU (MEC)','00001',0x00),
 ('00004',5,0x00,'FPI (MEC)','00001',0x00),
 ('00005',8,0x00,'Agaur','00001',0x00),
 ('00006',1,0x00,'Juan de la Cierva','00002',0x00),
 ('00007',1,0x00,'Ramón y Cajal','00002',0x00),
 ('00008',0,0x00,'Beatriu de Pinos','00002',0x00),
 ('00009',2,0x00,'Torres Quevedo','00002',0x00);
/*!40000 ALTER TABLE `table_grant` ENABLE KEYS */;


--
-- Definition of table `type_of_benefit`
--

DROP TABLE IF EXISTS `type_of_benefit`;
CREATE TABLE `type_of_benefit` (
  `type_of_benefitcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`type_of_benefitcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_of_benefit`
--

/*!40000 ALTER TABLE `type_of_benefit` DISABLE KEYS */;
INSERT INTO `type_of_benefit` VALUES  ('00001',9,0x00,'Ticket restaurant');
/*!40000 ALTER TABLE `type_of_benefit` ENABLE KEYS */;


--
-- Definition of table `type_of_compensation`
--

DROP TABLE IF EXISTS `type_of_compensation`;
CREATE TABLE `type_of_compensation` (
  `type_of_compensationcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`type_of_compensationcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_of_compensation`
--

/*!40000 ALTER TABLE `type_of_compensation` DISABLE KEYS */;
INSERT INTO `type_of_compensation` VALUES  ('00001',12,0x00,'Salario'),
 ('00002',1,0x00,'Dietas');
/*!40000 ALTER TABLE `type_of_compensation` ENABLE KEYS */;


--
-- Definition of table `type_of_contract`
--

DROP TABLE IF EXISTS `type_of_contract`;
CREATE TABLE `type_of_contract` (
  `type_of_contractcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  `is_irbs` bit(1) default NULL,
  PRIMARY KEY  (`type_of_contractcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_of_contract`
--

/*!40000 ALTER TABLE `type_of_contract` DISABLE KEYS */;
INSERT INTO `type_of_contract` VALUES  ('00001',0,0x00,'Convenio Universidad - Empresa',0x00),
 ('00002',39,0x00,'Contrato Eventual por Circunstancias de la Producción',0x01),
 ('00003',25,0x00,'Contrato Indefinido',0x01),
 ('00004',16,0x00,'Contrato de Interinidad',0x00),
 ('00005',10,0x00,'Contrato por Obra y Servicio Determinado',0x01),
 ('00006',16,0x00,'Contrato en Prácticas',0x00),
 ('00007',1,0x01,'Prórroga',0x00),
 ('00008',6,0x00,'Contrato para la Incorporación de Investigadores',0x00),
 ('00009',1,0x00,'Convenio de Colaboración para la Formación en Prácticas en Centros de Trabajo',0x00);
/*!40000 ALTER TABLE `type_of_contract` ENABLE KEYS */;


--
-- Definition of table `type_of_education`
--

DROP TABLE IF EXISTS `type_of_education`;
CREATE TABLE `type_of_education` (
  `type_of_educationcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`type_of_educationcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_of_education`
--

/*!40000 ALTER TABLE `type_of_education` DISABLE KEYS */;
INSERT INTO `type_of_education` VALUES  ('00001',1,0x01,'Postdoctorado'),
 ('00002',28,0x00,'Doctorado'),
 ('00003',7,0x01,'Ingeniería'),
 ('00004',59,0x00,'Licenciatura'),
 ('00005',3,0x00,'Diplomatura'),
 ('00006',5,0x00,'Ciclo Formativo Grado Medio'),
 ('00007',12,0x00,'Ciclo Formativo Grado Superior'),
 ('00008',16,0x00,'Bachillerato'),
 ('00009',5,0x00,'Graduado escolar'),
 ('00010',12,0x00,'Master'),
 ('00011',6,0x00,'Postgrado'),
 ('00012',1,0x00,'Otros');
/*!40000 ALTER TABLE `type_of_education` ENABLE KEYS */;


--
-- Definition of table `type_of_grant`
--

DROP TABLE IF EXISTS `type_of_grant`;
CREATE TABLE `type_of_grant` (
  `type_of_grantcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`type_of_grantcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_of_grant`
--

/*!40000 ALTER TABLE `type_of_grant` DISABLE KEYS */;
INSERT INTO `type_of_grant` VALUES  ('00001',6,0x00,'Predoc'),
 ('00002',8,0x00,'Postdoc');
/*!40000 ALTER TABLE `type_of_grant` ENABLE KEYS */;


--
-- Definition of table `type_of_holidays`
--

DROP TABLE IF EXISTS `type_of_holidays`;
CREATE TABLE `type_of_holidays` (
  `type_of_holidayscode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`type_of_holidayscode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_of_holidays`
--

/*!40000 ALTER TABLE `type_of_holidays` DISABLE KEYS */;
INSERT INTO `type_of_holidays` VALUES  ('00001',0,0x00,'Verano'),
 ('00002',0,0x00,'Semana santa'),
 ('00003',0,0x00,'Navidades'),
 ('00004',0,0x00,'Puente'),
 ('00005',1,0x00,'Asuntos personales convenio'),
 ('00006',1,0x01,'ashkduetsgxnbdhdjsksswuqoq'),
 ('00007',1,0x01,'01234567890123456789'),
 ('00008',2,0x01,'0123456789012345678902'),
 ('00009',2,0x01,'012345678901234567890'),
 ('00010',1,0x01,'01234567890123456789');
/*!40000 ALTER TABLE `type_of_holidays` ENABLE KEYS */;


--
-- Definition of table `type_of_institution`
--

DROP TABLE IF EXISTS `type_of_institution`;
CREATE TABLE `type_of_institution` (
  `type_of_institutioncode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`type_of_institutioncode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_of_institution`
--

/*!40000 ALTER TABLE `type_of_institution` DISABLE KEYS */;
INSERT INTO `type_of_institution` VALUES  ('00001',0,NULL,'Universidad'),
 ('00002',0,NULL,'Institución privada'),
 ('00003',0,NULL,'Institución pública'),
 ('00004',67,0x00,'Entidad pública'),
 ('00005',70,0x00,'Entidad privada'),
 ('00006',16,0x00,'Universidad'),
 ('00007',4,0x00,'Autónomo');
/*!40000 ALTER TABLE `type_of_institution` ENABLE KEYS */;


--
-- Definition of table `typeinstance`
--

DROP TABLE IF EXISTS `typeinstance`;
CREATE TABLE `typeinstance` (
  `typeinstancecode` varchar(20) NOT NULL,
  `typetable` varchar(100) NOT NULL,
  `pkey` tinyblob NOT NULL,
  `tablename` varchar(100) NOT NULL,
  PRIMARY KEY  (`typeinstancecode`),
  KEY `FK5CCF49CF75149191` (`tablename`),
  CONSTRAINT `FK5CCF49CF75149191` FOREIGN KEY (`tablename`) REFERENCES `maintable` (`tablename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `typeinstance`
--

/*!40000 ALTER TABLE `typeinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `typeinstance` ENABLE KEYS */;


--
-- Definition of table `umgroup`
--

DROP TABLE IF EXISTS `umgroup`;
CREATE TABLE `umgroup` (
  `entitycode` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY  (`entitycode`),
  KEY `FKED1A46E7A2B4A8DC` (`entitycode`),
  CONSTRAINT `FKED1A46E7A2B4A8DC` FOREIGN KEY (`entitycode`) REFERENCES `entity` (`entitycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `umgroup`
--

/*!40000 ALTER TABLE `umgroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `umgroup` ENABLE KEYS */;


--
-- Definition of table `umuser`
--

DROP TABLE IF EXISTS `umuser`;
CREATE TABLE `umuser` (
  `entitycode` varchar(100) NOT NULL,
  `password` tinyblob NOT NULL,
  `inidate` int(11) NOT NULL,
  `enddate` int(11) default NULL,
  `deletedboolean` int(11) NOT NULL,
  `activeboolean` int(11) NOT NULL,
  `languagecode` varchar(15) NOT NULL,
  PRIMARY KEY  (`entitycode`),
  KEY `FKCDDDEE6321DABF06` (`languagecode`),
  KEY `FKCDDDEE63A2B4A8DC` (`entitycode`),
  CONSTRAINT `FKCDDDEE6321DABF06` FOREIGN KEY (`languagecode`) REFERENCES `language` (`languagecode`),
  CONSTRAINT `FKCDDDEE63A2B4A8DC` FOREIGN KEY (`entitycode`) REFERENCES `entity` (`entitycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `umuser`
--

/*!40000 ALTER TABLE `umuser` DISABLE KEYS */;
INSERT INTO `umuser` VALUES  ('00001',0x0CC175B9C0F1B6A831C399E269772661,20070529,NULL,0,1,'es'),
 ('00002',0x0CC175B9C0F1B6A831C399E269772661,20070529,NULL,0,1,'en'),
 ('00003',0x0CC175B9C0F1B6A831C399E269772661,20070529,NULL,0,1,'en'),
 ('00004',0x0CC175B9C0F1B6A831C399E269772661,20070529,NULL,0,1,'en'),
 ('00005',0x0CC175B9C0F1B6A831C399E269772661,20070604,NULL,0,1,'en'),
 ('00006',0x0CC175B9C0F1B6A831C399E269772661,20070604,NULL,0,1,'en'),
 ('00007',0x0CC175B9C0F1B6A831C399E269772661,20070614,NULL,0,1,'en'),
 ('00008',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00009',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00010',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00011',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00012',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00013',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00014',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00015',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en');
INSERT INTO `umuser` VALUES  ('00016',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00017',0x0CC175B9C0F1B6A831C399E269772661,20070610,NULL,0,1,'en'),
 ('00018',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00019',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00020',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00021',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00022',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00023',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00024',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00025',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00026',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00027',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00028',0x0CC175B9C0F1B6A831C399E269772661,20070606,20070606,0,1,'en'),
 ('00029',0x0CC175B9C0F1B6A831C399E269772661,20070606,20070611,0,1,'en'),
 ('00030',0x0CC175B9C0F1B6A831C399E269772661,20070606,NULL,0,1,'es');
INSERT INTO `umuser` VALUES  ('00031',0x0CC175B9C0F1B6A831C399E269772661,20070610,20070610,0,1,'en'),
 ('00032',0x0CC175B9C0F1B6A831C399E269772661,20070610,20070614,0,1,'en'),
 ('00033',0x0CC175B9C0F1B6A831C399E269772661,20070610,NULL,0,1,'en'),
 ('00034',0x0CC175B9C0F1B6A831C399E269772661,20070610,20070611,0,1,'en'),
 ('00035',0x0CC175B9C0F1B6A831C399E269772661,20070610,NULL,0,1,'en'),
 ('00036',0x0CC175B9C0F1B6A831C399E269772661,20070611,20070613,0,1,'en'),
 ('00037',0x0CC175B9C0F1B6A831C399E269772661,20070611,NULL,0,1,'ct'),
 ('00038',0x0CC175B9C0F1B6A831C399E269772661,20070611,20070611,0,1,'en'),
 ('00039',0x0CC175B9C0F1B6A831C399E269772661,20070611,NULL,0,1,'en'),
 ('00040',0x0CC175B9C0F1B6A831C399E269772661,20070611,20070612,0,1,'en'),
 ('00041',0x0CC175B9C0F1B6A831C399E269772661,20070611,NULL,0,1,'en'),
 ('00042',0x0CC175B9C0F1B6A831C399E269772661,20070611,NULL,0,1,'en'),
 ('00043',0x0CC175B9C0F1B6A831C399E269772661,20070611,NULL,0,1,'en'),
 ('00044',0x0CC175B9C0F1B6A831C399E269772661,20070611,20070613,0,1,'en'),
 ('00045',0x0CC175B9C0F1B6A831C399E269772661,20070611,NULL,0,1,'en');
INSERT INTO `umuser` VALUES  ('00046',0x0CC175B9C0F1B6A831C399E269772661,20070612,20070612,0,1,'en'),
 ('00047',0x0CC175B9C0F1B6A831C399E269772661,20070612,20070613,0,1,'en'),
 ('00048',0x0CC175B9C0F1B6A831C399E269772661,20070612,20070613,0,1,'en'),
 ('00049',0x0CC175B9C0F1B6A831C399E269772661,20070612,20070613,0,1,'en'),
 ('00050',0x0CC175B9C0F1B6A831C399E269772661,20070612,NULL,0,1,'en'),
 ('00051',0x0CC175B9C0F1B6A831C399E269772661,20070612,NULL,0,1,'en'),
 ('00052',0x0CC175B9C0F1B6A831C399E269772661,20070614,NULL,0,1,'en'),
 ('00053',0x0CC175B9C0F1B6A831C399E269772661,20070617,20070617,0,1,'en'),
 ('00054',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00055',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00056',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,0,'en'),
 ('00057',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00058',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00059',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00060',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en');
INSERT INTO `umuser` VALUES  ('00061',0x0CC175B9C0F1B6A831C399E269772661,20070620,20070620,0,1,'en'),
 ('00062',0x0CC175B9C0F1B6A831C399E269772661,20070620,20070620,0,1,'en'),
 ('00063',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,0,'en'),
 ('00064',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,0,'en'),
 ('00065',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00066',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00067',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00068',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00069',0x0CC175B9C0F1B6A831C399E269772661,20070620,20070624,0,1,'en'),
 ('00070',0x0CC175B9C0F1B6A831C399E269772661,20070620,NULL,0,1,'en'),
 ('00071',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00072',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00073',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00074',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00075',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en');
INSERT INTO `umuser` VALUES  ('00076',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00077',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00078',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00079',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00080',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00081',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00082',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,0,'en'),
 ('00083',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00084',0x0CC175B9C0F1B6A831C399E269772661,20080422,NULL,0,1,'en'),
 ('00085',0x0CC175B9C0F1B6A831C399E269772661,20070621,20070621,0,1,'en'),
 ('00086',0x0CC175B9C0F1B6A831C399E269772661,20070621,NULL,0,1,'en'),
 ('00087',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00088',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00089',0x0CC175B9C0F1B6A831C399E269772661,20070624,20070624,0,1,'en'),
 ('00090',0x0CC175B9C0F1B6A831C399E269772661,20070624,20070626,0,1,'en');
INSERT INTO `umuser` VALUES  ('00091',0x0CC175B9C0F1B6A831C399E269772661,20070624,20070624,0,1,'en'),
 ('00092',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00093',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00094',0x0CC175B9C0F1B6A831C399E269772661,20070624,20070626,0,1,'en'),
 ('00095',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00096',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00097',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00098',0x0CC175B9C0F1B6A831C399E269772661,20070624,NULL,0,1,'en'),
 ('00099',0x0CC175B9C0F1B6A831C399E269772661,20070624,20070625,0,1,'en'),
 ('00100',0x0CC175B9C0F1B6A831C399E269772661,20070625,20070625,0,1,'en'),
 ('00101',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en'),
 ('00102',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en'),
 ('00103',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,0,'en'),
 ('00104',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en'),
 ('00105',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en');
INSERT INTO `umuser` VALUES  ('00106',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en'),
 ('00107',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en'),
 ('00108',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en'),
 ('00109',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en'),
 ('00110',0x0CC175B9C0F1B6A831C399E269772661,20070625,20080312,0,1,'en'),
 ('00111',0x0CC175B9C0F1B6A831C399E269772661,20070625,20080313,0,1,'en'),
 ('00112',0x0CC175B9C0F1B6A831C399E269772661,20070625,20070625,0,1,'en'),
 ('00113',0x0CC175B9C0F1B6A831C399E269772661,20070625,NULL,0,1,'en'),
 ('00114',0x0CC175B9C0F1B6A831C399E269772661,20070626,20080312,0,1,'en'),
 ('00115',0x0CC175B9C0F1B6A831C399E269772661,20070626,NULL,0,1,'en'),
 ('00116',0x0CC175B9C0F1B6A831C399E269772661,20070626,NULL,0,1,'en'),
 ('00118',0x0CC175B9C0F1B6A831C399E269772661,20080226,20080730,0,1,'ct'),
 ('00123',0x0CC175B9C0F1B6A831C399E269772661,20080306,NULL,0,0,'en'),
 ('00124',0x0CC175B9C0F1B6A831C399E269772661,20080307,20080307,0,1,'en'),
 ('00129',0x0CC175B9C0F1B6A831C399E269772661,20080403,NULL,0,1,'en');
INSERT INTO `umuser` VALUES  ('00130',0x0CC175B9C0F1B6A831C399E269772661,20080422,NULL,0,1,'ct'),
 ('00132',0x0CC175B9C0F1B6A831C399E269772661,20080423,NULL,0,1,'en'),
 ('00133',0x0CC175B9C0F1B6A831C399E269772661,20080425,NULL,0,1,'ct'),
 ('00135',0x900150983CD24FB0D6963F7D28E17F72,20080729,20080729,1,1,'en'),
 ('00136',0x0CC175B9C0F1B6A831C399E269772661,20080731,NULL,0,1,'en'),
 ('99999',0x0CC175B9C0F1B6A831C399E269772661,20070101,NULL,0,1,'en');
/*!40000 ALTER TABLE `umuser` ENABLE KEYS */;


--
-- Definition of table `unit`
--

DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `unitcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  `group` varchar(255) default NULL,
  `organization_unit` varchar(255) default NULL,
  `supervisor` varchar(255) default NULL,
  PRIMARY KEY  (`unitcode`),
  KEY `FK27D18465F28350` (`organization_unit`),
  KEY `FK_unit_2` (`supervisor`),
  CONSTRAINT `FK27D18465F28350` FOREIGN KEY (`organization_unit`) REFERENCES `organization_unit` (`organization_unitcode`),
  CONSTRAINT `FK_unit_2` FOREIGN KEY (`supervisor`) REFERENCES `personal` (`personalcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unit`
--

/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` (`unitcode`, `version`, `deleted`, `description`, `group`, `organization_unit`, `supervisor`) VALUES
('00001', 1044, '\0', 'Cell & Developmental Biology', 'celdevbio', '00001', '00394'),
('00002', 909, '\0', 'Structural & Computational Biology', '', '00001', '00429'),
('00003', 1019, '\0', 'Molecular Medicine', '', '00001', '00325'),
('00004', 1027, '\0', 'Chemistry & Molecular Pharmacology', 'chemolpa', '00001', '00333'),
('00005', 225, '\0', 'Oncology', 'oncolo', '00001', '00420'),
('00006', 27, '\0', 'Protein Expression', 'proexp', '00002', '00269'),
('00007', 31, '\0', 'Mass Spectrometry', '', '00002', '00453'),
('00008', 37, '\0', 'Mouse Mutant', 'moumut', '00002', '00116'),
('00009', 22, '\0', 'Functional Genomics', '', '00002', '00454'),
('00010', 14, '\0', 'Advanced Microscopy', '', '00002', '00611'),
('00011', 52, '\0', 'Bioinformatics-Biostatistics Unit', 'biostats', '00002', '00517'),
('00012', 79, '\0', 'Finance ', 'finance', '00003', '00619'),
('00013', 32, '\0', 'Human Resources', 'hr', '00003', '00001'),
('00014', 55, '\0', 'Communications and External Relations', 'ocer', '00003', '00043'),
('00015', 35, '\0', 'Information Technology Services', 'its', '00003', '00029'),
('00016', 35, '\0', 'Research & academic Administration', 'raa', '00003', '00050'),
('00017', 36, '\0', 'Directorate', 'directorate', '00004', '00017'),
('00018', 11, '\0', 'Purchasing', 'purchasing', '00003', '00589'),
('00019', 12, '\0', 'Innovation and Strategic Projects', 'innova', '00003', '00042');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;


--
-- Definition of table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE `usergroup` (
  `groupcode` varchar(100) NOT NULL,
  `usercode` varchar(100) NOT NULL,
  PRIMARY KEY  (`groupcode`,`usercode`),
  KEY `FK14ACB1945AE34C4A` (`groupcode`),
  KEY `FK14ACB1946778744` (`usercode`),
  CONSTRAINT `FK14ACB1945AE34C4A` FOREIGN KEY (`groupcode`) REFERENCES `umgroup` (`entitycode`),
  CONSTRAINT `FK14ACB1946778744` FOREIGN KEY (`usercode`) REFERENCES `umuser` (`entitycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usergroup`
--

/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;


--
-- Definition of table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `rolecode` varchar(100) NOT NULL,
  `usercode` varchar(100) NOT NULL,
  PRIMARY KEY  (`rolecode`,`usercode`),
  KEY `FKF02B8EC16778744` (`usercode`),
  KEY `FKF02B8EC12A7E76C2` (`rolecode`),
  CONSTRAINT `FKF02B8EC12A7E76C2` FOREIGN KEY (`rolecode`) REFERENCES `role` (`entitycode`),
  CONSTRAINT `FKF02B8EC16778744` FOREIGN KEY (`usercode`) REFERENCES `umuser` (`entitycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `userrole`
--

/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES  ('irbpeople_rw','00001'),
 ('irbpeople_rw','00002'),
 ('supervisor','00003'),
 ('supervisor','00004'),
 ('supervisor','00005'),
 ('supervisor','00006'),
 ('supervisor','00007'),
 ('supervisor','00008'),
 ('supervisor','00009'),
 ('supervisor','00010'),
 ('supervisor','00011'),
 ('supervisor','00012'),
 ('supervisor','00013'),
 ('supervisor','00014'),
 ('supervisor','00015'),
 ('supervisor','00016'),
 ('supervisor','00017'),
 ('supervisor','00018'),
 ('supervisor','00019'),
 ('supervisor','00020'),
 ('supervisor','00021'),
 ('supervisor','00022'),
 ('supervisor','00023'),
 ('supervisor','00024'),
 ('supervisor','00025'),
 ('supervisor','00026'),
 ('supervisor','00027'),
 ('basic','00028'),
 ('basic','00029'),
 ('basic','00030'),
 ('basic','00031'),
 ('basic','00032'),
 ('basic','00033'),
 ('basic','00034'),
 ('basic','00035'),
 ('basic','00036'),
 ('basic','00037'),
 ('basic','00038'),
 ('basic','00039'),
 ('basic','00040'),
 ('basic','00041'),
 ('basic','00042'),
 ('basic','00043');
INSERT INTO `userrole` VALUES  ('basic','00044'),
 ('basic','00045'),
 ('basic','00046'),
 ('basic','00047'),
 ('basic','00048'),
 ('basic','00049'),
 ('basic','00050'),
 ('basic','00051'),
 ('basic','00052'),
 ('basic','00053'),
 ('basic','00054'),
 ('basic','00055'),
 ('basic','00056'),
 ('basic','00057'),
 ('basic','00058'),
 ('basic','00059'),
 ('basic','00060'),
 ('basic','00061'),
 ('basic','00062'),
 ('basic','00063'),
 ('basic','00064'),
 ('basic','00065'),
 ('basic','00066'),
 ('basic','00067'),
 ('basic','00068'),
 ('basic','00069'),
 ('basic','00070'),
 ('basic','00071'),
 ('basic','00072'),
 ('basic','00073'),
 ('basic','00074'),
 ('basic','00075'),
 ('basic','00076'),
 ('basic','00077'),
 ('basic','00078'),
 ('basic','00079'),
 ('basic','00080'),
 ('basic','00081'),
 ('basic','00082'),
 ('basic','00083'),
 ('basic','00085'),
 ('basic','00086'),
 ('basic','00087'),
 ('basic','00088'),
 ('basic','00089'),
 ('basic','00090'),
 ('basic','00091'),
 ('basic','00092'),
 ('basic','00093'),
 ('basic','00094');
INSERT INTO `userrole` VALUES  ('basic','00095'),
 ('basic','00096'),
 ('basic','00097'),
 ('basic','00098'),
 ('basic','00099'),
 ('basic','00100'),
 ('basic','00101'),
 ('basic','00102'),
 ('basic','00103'),
 ('basic','00104'),
 ('basic','00105'),
 ('basic','00106'),
 ('basic','00107'),
 ('basic','00108'),
 ('basic','00109'),
 ('basic','00110'),
 ('basic','00111'),
 ('basic','00112'),
 ('basic','00113'),
 ('basic','00114'),
 ('basic','00115'),
 ('basic','00116'),
 ('basic','00118'),
 ('administrator','00123'),
 ('basic','00124'),
 ('basic','00129'),
 ('administrator','00132'),
 ('irbpeople_ro','00133'),
 ('irbpeople_ro','00135'),
 ('irbpeople_ro','00136'),
 ('administrator','99999'),
 ('irbpeople_rw','99999');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `usuariocode` varchar(100) NOT NULL,
  `username` varchar(50) default NULL,
  `email` varchar(100) default NULL,
  `activationCode` varchar(100) default NULL,
  `changePasswordCode` varchar(100) default NULL,
  PRIMARY KEY  (`usuariocode`),
  KEY `FK22E07F0E476D2527` (`usuariocode`),
  CONSTRAINT `FK22E07F0E476D2527` FOREIGN KEY (`usuariocode`) REFERENCES `umuser` (`entitycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES  ('00001','00001','bartolome@irbbarcelona.org','279091884043885551',NULL),
 ('00002','00002','bartolome@irbbarcelona.org','6639570104729127230',NULL),
 ('00003','00003','bartolome@irbbarcelona.org',NULL,NULL),
 ('00004','00004','bartolome@irbbarcelona.org',NULL,NULL),
 ('00005','00005','bartolome@irbbarcelona.org',NULL,NULL),
 ('00006','00006','bartolome@irbbarcelona.org',NULL,NULL),
 ('00007','00007','bartolome@irbbarcelona.org','3131037305885379',NULL),
 ('00008','00008','bartolome@irbbarcelona.org','6023626618510865918',NULL),
 ('00009','00009','bartolome@irbbarcelona.org','5686881370368730500',NULL),
 ('00010','00010','bartolome@irbbarcelona.org','599423405157426041',NULL),
 ('00011','00011','bartolome@irbbarcelona.org','3832905885022249216',NULL),
 ('00012','00012','bartolome@irbbarcelona.org','5704178195810629996',NULL),
 ('00013','00013','bartolome@irbbarcelona.org','1553051708126997996',NULL),
 ('00014','00014','bartolome@irbbarcelona.org','487338080786614458',NULL),
 ('00015','00015','bartolome@irbbarcelona.org','2733645748173082760',NULL);
INSERT INTO `usuario` VALUES  ('00016','00016','bartolome@irbbarcelona.org','4996305271351973298',NULL),
 ('00017','00017','bartolome@irbbarcelona.org','1490636795687230141',NULL),
 ('00018','00018','bartolome@irbbarcelona.org',NULL,NULL),
 ('00019','00019','bartolome@irbbarcelona.org','1666856450513555048',NULL),
 ('00020','00020','bartolome@irbbarcelona.org','8730364483058732267',NULL),
 ('00021','00021','bartolome@irbbarcelona.org','7697670953714381974',NULL),
 ('00022','00022','bartolome@irbbarcelona.org','4861225645059494068',NULL),
 ('00023','00023','bartolome@irbbarcelona.org','8463299032288168137',NULL),
 ('00024','00024','bartolome@irbbarcelona.org','2772545748872876434',NULL),
 ('00025','00025','bartolome@irbbarcelona.org','4801789049770858723',NULL),
 ('00026','00026','bartolome@irbbarcelona.org','3941698752173063490',NULL),
 ('00027','00027','bartolome@irbbarcelona.org','5778561150306580347',NULL),
 ('00028','00028','bartolome@irbbarcelona.org',NULL,NULL),
 ('00029','00029','bartolome@irbbarcelona.org',NULL,NULL),
 ('00030','00030','bartolome@irbbarcelona.org',NULL,NULL);
INSERT INTO `usuario` VALUES  ('00031','00031','bartolome@irbbarcelona.org',NULL,NULL),
 ('00032','00032','bartolome@irbbarcelona.org',NULL,NULL),
 ('00033','00033','bartolome@irbbarcelona.org',NULL,NULL),
 ('00034','00034','bartolome@irbbarcelona.org',NULL,NULL),
 ('00035','00035','bartolome@irbbarcelona.org',NULL,NULL),
 ('00036','00036','bartolome@irbbarcelona.org',NULL,NULL),
 ('00037','00037','bartolome@irbbarcelona.org',NULL,NULL),
 ('00038','00038','bartolome@irbbarcelona.org',NULL,NULL),
 ('00039','00039','bartolome@irbbarcelona.org',NULL,NULL),
 ('00040','00040','bartolome@irbbarcelona.org',NULL,NULL),
 ('00041','00041','bartolome@irbbarcelona.org',NULL,NULL),
 ('00042','00042','bartolome@irbbarcelona.org',NULL,NULL),
 ('00043','00043','bartolome@irbbarcelona.org',NULL,NULL),
 ('00044','00044','bartolome@irbbarcelona.org',NULL,NULL),
 ('00045','00045','bartolome@irbbarcelona.org',NULL,NULL),
 ('00046','00046','bartolome@irbbarcelona.org',NULL,NULL),
 ('00047','00047','bartolome@irbbarcelona.org',NULL,NULL),
 ('00048','00048','bartolome@irbbarcelona.org',NULL,NULL);
INSERT INTO `usuario` VALUES  ('00049','00049','bartolome@irbbarcelona.org',NULL,NULL),
 ('00050','00050','bartolome@irbbarcelona.org',NULL,NULL),
 ('00051','00051','bartolome@irbbarcelona.org',NULL,NULL),
 ('00052','00052','bartolome@irbbarcelona.org',NULL,NULL),
 ('00053','00053','bartolome@irbbarcelona.org',NULL,NULL),
 ('00054','00054','bartolome@irbbarcelona.org',NULL,NULL),
 ('00055','00055','bartolome@irbbarcelona.org',NULL,NULL),
 ('00056','00056','bartolome@irbbarcelona.org','3747933956562350302',NULL),
 ('00057','00057','bartolome@irbbarcelona.org',NULL,NULL),
 ('00058','00058','bartolome@irbbarcelona.org',NULL,NULL),
 ('00059','00059','bartolome@irbbarcelona.org',NULL,NULL),
 ('00060','00060','bartolome@irbbarcelona.org',NULL,NULL),
 ('00061','00061','bartolome@irbbarcelona.org',NULL,NULL),
 ('00062','00062','bartolome@irbbarcelona.org',NULL,NULL),
 ('00063','00063','bartolome@irbbarcelona.org','1964854397601853089',NULL),
 ('00064','00064','bartolome@irbbarcelona.org','1971943654848814493',NULL),
 ('00065','00065','bartolome@irbbarcelona.org',NULL,NULL);
INSERT INTO `usuario` VALUES  ('00066','00066','bartolome@irbbarcelona.org',NULL,NULL),
 ('00067','00067','bartolome@irbbarcelona.org',NULL,NULL),
 ('00068','00068','bartolome@irbbarcelona.org',NULL,NULL),
 ('00069','00069','bartolome@irbbarcelona.org',NULL,NULL),
 ('00070','00070','bartolome@irbbarcelona.org',NULL,NULL),
 ('00071','00071','bartolome@irbbarcelona.org',NULL,NULL),
 ('00072','00072','bartolome@irbbarcelona.org',NULL,NULL),
 ('00073','00073','bartolome@irbbarcelona.org',NULL,NULL),
 ('00074','00074','bartolome@irbbarcelona.org',NULL,NULL),
 ('00075','00075','bartolome@irbbarcelona.org',NULL,NULL),
 ('00076','00076','bartolome@irbbarcelona.org',NULL,NULL),
 ('00077','00077','bartolome@irbbarcelona.org',NULL,NULL),
 ('00078','00078','bartolome@irbbarcelona.org',NULL,NULL),
 ('00079','00079','bartolome@irbbarcelona.org',NULL,NULL),
 ('00080','00080','bartolome@irbbarcelona.org',NULL,NULL),
 ('00081','00081','bartolome@irbbarcelona.org',NULL,NULL),
 ('00082','00082','bartolome@irbbarcelona.org','5780560786735636107',NULL),
 ('00083','00083','bartolome@irbbarcelona.org',NULL,NULL);
INSERT INTO `usuario` VALUES  ('00084','00084','bartolome@irbbarcelona.org','7832355298503876443',NULL),
 ('00085','00085','bartolome@irbbarcelona.org',NULL,NULL),
 ('00086','00086','bartolome@irbbarcelona.org',NULL,NULL),
 ('00087','00087','bartolome@irbbarcelona.org',NULL,NULL),
 ('00088','00088','bartolome@irbbarcelona.org',NULL,NULL),
 ('00089','00089','bartolome@irbbarcelona.org',NULL,NULL),
 ('00090','00090','bartolome@irbbarcelona.org',NULL,NULL),
 ('00091','00091','bartolome@irbbarcelona.org',NULL,NULL),
 ('00092','00092','bartolome@irbbarcelona.org',NULL,NULL),
 ('00093','00093','bartolome@irbbarcelona.org',NULL,NULL),
 ('00094','00094','bartolome@irbbarcelona.org',NULL,NULL),
 ('00095','00095','bartolome@irbbarcelona.org',NULL,NULL),
 ('00096','00096','bartolome@irbbarcelona.org',NULL,NULL),
 ('00097','00097','bartolome@irbbarcelona.org',NULL,NULL),
 ('00098','00098','bartolome@irbbarcelona.org',NULL,NULL),
 ('00099','00099','bartolome@irbbarcelona.org',NULL,NULL),
 ('00100','00100','bartolome@irbbarcelona.org',NULL,NULL),
 ('00101','00101','bartolome@irbbarcelona.org',NULL,NULL);
INSERT INTO `usuario` VALUES  ('00102','00102','bartolome@irbbarcelona.org',NULL,NULL),
 ('00103','00103','bartolome@irbbarcelona.org','5395800900688485892',NULL),
 ('00104','00104','bartolome@irbbarcelona.org',NULL,NULL),
 ('00105','00105','bartolome@irbbarcelona.org',NULL,NULL),
 ('00106','00106','bartolome@irbbarcelona.org',NULL,NULL),
 ('00107','00107','bartolome@irbbarcelona.org',NULL,NULL),
 ('00108','00108','bartolome@irbbarcelona.org',NULL,NULL),
 ('00109','00109','bartolome@irbbarcelona.org',NULL,NULL),
 ('00110','00110','bartolome@irbbarcelona.org',NULL,NULL),
 ('00111','00111','bartolome@irbbarcelona.org',NULL,NULL),
 ('00112','00112','bartolome@irbbarcelona.org',NULL,NULL),
 ('00113','00113','bartolome@irbbarcelona.org',NULL,NULL),
 ('00114','00114','bartolome@irbbarcelona.org',NULL,NULL),
 ('00115','00115','bartolome@irbbarcelona.org',NULL,NULL),
 ('00116','00116','bartolome@irbbarcelona.org',NULL,NULL),
 ('00118','00118','jcurto@justinmind.com',NULL,NULL),
 ('00123','00123','bartolome@irbbarcelona.org','6307663431637709045',NULL),
 ('00124','00124','bartolome@irbbarcelona.org','1044263276315273705',NULL);
INSERT INTO `usuario` VALUES  ('00129','00129','bartolome@irbbarcelona.org','1603946621181304127',NULL),
 ('00130','00130','bartolome@irbbarcelona.org','3749570760253020733',NULL),
 ('00132','00132','bartolome@irbbarcelona.org','6627238062987164777',NULL),
 ('00133','00133','bartolome@irbbarcelona.org',NULL,NULL),
 ('00135','00135','aaaa@bbb.com','7297375150894126747',NULL),
 ('00136','00136','jcurto@justinmind.com','5215683362200421550',NULL),
 ('99999','99999','bartolome@irbbarcelona.org',NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of table `views`
--

DROP TABLE IF EXISTS `views`;
CREATE TABLE `views` (
  `viewscode` varchar(255) NOT NULL default '',
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default '\0',
  `name` varchar(31) default NULL,
  PRIMARY KEY  (`viewscode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `views`
--

/*!40000 ALTER TABLE `views` DISABLE KEYS */;
INSERT INTO `views` VALUES  ('1',1,0x00,'view_personal_professional'),
 ('4',1,0x00,'view_personal_professional');
/*!40000 ALTER TABLE `views` ENABLE KEYS */;


--
-- Definition of table `work_experience`
--

DROP TABLE IF EXISTS `work_experience`;
CREATE TABLE `work_experience` (
  `work_experiencecode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `name_of_institution_or_company` varchar(100) default NULL,
  `work_experience_personal` varchar(255) default NULL,
  `type_of_institution` varchar(255) default NULL,
  `area` varchar(255) default NULL,
  `work_experience_position` varchar(255) default NULL,
  `work_experience_country` varchar(255) default NULL,
  `department` varchar(45) default NULL,
  `position` varchar(45) default NULL,
  PRIMARY KEY  (`work_experiencecode`),
  KEY `FK3CB477583F3BB31A` (`type_of_institution`),
  KEY `FK3CB4775893367E57` (`work_experience_personal`),
  KEY `FK3CB47758B7876429` (`work_experience_position`),
  KEY `FK3CB47758A3DDC9EA` (`area`),
  KEY `FK3CB477581A636675` (`work_experience_country`),
  CONSTRAINT `FK3CB477581A636675` FOREIGN KEY (`work_experience_country`) REFERENCES `country` (`countrycode`),
  CONSTRAINT `FK3CB477583F3BB31A` FOREIGN KEY (`type_of_institution`) REFERENCES `type_of_institution` (`type_of_institutioncode`),
  CONSTRAINT `FK3CB4775893367E57` FOREIGN KEY (`work_experience_personal`) REFERENCES `personal` (`personalcode`),
  CONSTRAINT `FK3CB47758A3DDC9EA` FOREIGN KEY (`area`) REFERENCES `area` (`areacode`),
  CONSTRAINT `FK3CB47758B7876429` FOREIGN KEY (`work_experience_position`) REFERENCES `position` (`positioncode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `work_experience`
--

/*!40000 ALTER TABLE `work_experience` DISABLE KEYS */;
INSERT INTO `work_experience` VALUES  ('0000000000002',2,0x01,'2003-05-20 00:00:00','2007-05-31 00:00:00','Demo','00001',NULL,NULL,'00004','GB',NULL,NULL),
 ('0000000000003',5,0x00,'1996-02-01 00:00:00','2004-12-31 00:00:00','CERN','00028','00004','00011','00003','CH','Informática','Software Engineer'),
 ('0000000000005',1,0x00,'2004-05-03 00:00:00','2007-04-15 00:00:00','Fundació Centre de Recerca en Sanitat Animal','00031','00005','00011',NULL,'ES','Gerència','Gerent'),
 ('0000000000006',1,0x00,'2002-01-08 00:00:00','2004-05-02 00:00:00','Fundació Privada Joan XXIII','00031','00005','00011',NULL,'ES','Administració','Administrador'),
 ('0000000000007',2,0x00,'2000-07-14 00:00:00','2002-01-07 00:00:00','Institut de Reinserció Social','00031','00005','00008',NULL,'ES','Administració','Director Administratiu Financer'),
 ('0000000000008',1,0x00,'1994-11-09 00:00:00','2000-07-13 00:00:00','Comissió Obrera Nacional de Catalunya','00031','00005','00014',NULL,'ES','Comptabilitat i Finances','Responsable de Comptabilidad de la CECONC'),
 ('0000000000010',1,0x00,'1996-11-01 00:00:00','2006-02-28 00:00:00','EMBL','00029','00004','00011',NULL,'DE','ITS','System Manager');
INSERT INTO `work_experience` VALUES  ('0000000000011',3,0x01,'2007-05-31 00:00:00','1989-06-15 00:00:00','fdfdf','00029','00006','00008',NULL,'UA','','fdfdfdf'),
 ('0000000000012',1,0x00,'1995-02-01 00:00:00','1996-09-30 00:00:00','CERN','00029','00004','00011',NULL,'CH','IT','Engineer'),
 ('0000000000013',2,0x00,'1999-03-15 00:00:00','2006-04-30 00:00:00','EMBL','00043','00004','00011',NULL,'DE','Office of Information and Public Affairs','Associate Information Officer'),
 ('0000000000014',1,0x00,'2004-03-01 00:00:00','2005-12-31 00:00:00','Institut de Ciències Fotòniques','00039','00005','00011',NULL,'ES','Comunicació','Cap'),
 ('0000000000015',3,0x00,'1993-04-28 00:00:00','1993-07-28 00:00:00','Hospital Comarcal Sant Boi','00036','00004','00001',NULL,'ES','Laboratorio','técnico superior en analisis clínicos'),
 ('0000000000016',5,0x00,'1993-08-01 00:00:00','1993-09-30 00:00:00','Hospital Comarcal de Sant Boi','00036','00004','00001',NULL,'ES','Laboratorio','técnico superior en analisis clínicos'),
 ('0000000000017',3,0x00,'1993-10-01 00:00:00','1994-01-31 00:00:00','Hospital Comarcal Sant boi','00036','00004','00001',NULL,'ES','Laboratorio','técnico superior en análisis clínicos');
INSERT INTO `work_experience` VALUES  ('0000000000018',3,0x00,'1987-01-01 00:00:00','1991-07-17 00:00:00','Centro de Biología Molecular (CSIC)','00042','00004','00011',NULL,'ES','Laboratorio de Microtúbulos','Predoctoral fellow'),
 ('0000000000019',2,0x00,'1988-06-03 00:00:00','1988-07-31 00:00:00','European Molecular Biology Laboratory','00042','00004','00011',NULL,'DE','Cell Biology','Visiting Scientist'),
 ('0000000000020',1,0x00,'1991-07-18 00:00:00','1994-11-15 00:00:00','Centro de Biología Molecular (CSIC)','00042','00004','00011',NULL,'ES','Laboratori de Microtúbulos','Becario Postdoctoral'),
 ('0000000000021',1,0x00,'1992-11-02 00:00:00','1993-02-21 00:00:00','European Molecular Biology Laboratory','00042','00004','00011',NULL,'DE','Cell Biology','Visiting Scientist'),
 ('0000000000022',1,0x00,'1993-11-21 00:00:00','1994-03-12 00:00:00','European Molecular Biology Laboratory','00042','00004','00011',NULL,'ES','Cell Biology','Visiting Scientist'),
 ('0000000000023',1,0x00,'1994-12-01 00:00:00','1996-12-31 00:00:00','European Molecular Biology Laboratory','00042','00004','00011',NULL,'DE','Cell Biology','Postdoctoral Fellow'),
 ('0000000000024',1,0x00,'1997-01-01 00:00:00','1999-07-31 00:00:00','European Institute of Oncology','00042','00005','00011',NULL,'IT','Experimental Oncology','Postodoctoral Fellow');
INSERT INTO `work_experience` VALUES  ('0000000000025',1,0x00,'1999-09-01 00:00:00','2002-09-19 00:00:00','Universitat de Barcelona','00042','00004','00011',NULL,'ES','Bioquimica i Biologia Molecular','Research Associate'),
 ('0000000000026',1,0x00,'2002-09-20 00:00:00','2007-06-12 00:00:00','Institut de Recerca Biomedica','00042','00005','00011',NULL,'ES','Metabolic Engineering and Diabetes Therapy','Research Associate'),
 ('0000000000027',4,0x00,'1994-02-01 00:00:00','2002-09-11 00:00:00','Facultad de química-física','00036','00006','00001',NULL,'ES','bioquímica y biología molecular','Lab.Manager'),
 ('0000000000028',2,0x00,'2002-09-12 00:00:00','2006-12-31 00:00:00','PCB','00036','00004','00001',NULL,'ES','Ingeniería metabólica y terapia de la Diabete','Lab.Manager'),
 ('0000000000029',1,0x00,'2007-01-01 00:00:00','2007-06-15 00:00:00','IRB','00036','00004','00001',NULL,'ES','Ingeniería metabólica y Terapia de la Diabet ','Lab.Manager'),
 ('0000000000030',1,0x00,'1995-05-01 00:00:00','1997-12-31 00:00:00','Dpto. Bioquímica y Biología Molecular. Universidad de Barcelona ','00040','00006','00011',NULL,'ES','Bioquímica y Biología Molecular','Titulado Superior'),
 ('0000000000031',1,0x00,'1998-01-01 00:00:00','2000-12-31 00:00:00','Universidad de Barcelona','00040','00006','00011',NULL,'ES','Bioquímica y Biología Molecular','Titulado Superior');
INSERT INTO `work_experience` VALUES  ('0000000000032',1,0x00,'2001-01-01 00:00:00','2002-12-31 00:00:00','Universidad de Barcelona','00040','00006','00011',NULL,'ES','Bioquímica y Biología Molecular','Titutado Superior'),
 ('0000000000033',1,0x00,'2003-01-01 00:00:00','2005-12-31 00:00:00','Universidad de Barcelona','00040','00006','00011',NULL,'ES','Bioquímica y Biología Molecular','Titulado Superior'),
 ('0000000000034',1,0x00,'2004-01-01 00:00:00','2005-12-31 00:00:00','Parc Científic de Barcelona','00040','00004','00011',NULL,'ES','Ingeniería Metabólica y Terapia de la Diabete','Investigador Asociado'),
 ('0000000000035',1,0x00,'2004-11-09 00:00:00','2005-06-23 00:00:00','ANABIOL','00049','00005','00015',NULL,'ES','CONTROL DE CALIDAD DE ALIMENTOS','AUXILIAR'),
 ('0000000000036',1,0x00,'2005-12-01 00:00:00','2006-12-31 00:00:00','PCB','00049','00004','00001',NULL,'ES','ENGINYERIA METABOLICA I TERAPIA DE LA DIABETI','TECNICO'),
 ('0000000000037',1,0x00,'2007-01-01 00:00:00','2007-06-15 00:00:00','IRB','00049','00004','00001',NULL,'ES','ENGINYERIA METABOLICA I TERAPIA DE LA DIABETI','TECNICO'),
 ('0000000000038',1,0x00,'1993-11-01 00:00:00','1994-04-30 00:00:00','Instituto Nacional de Cardiología','00044','00004','00011',NULL,'MX','Fisiología','Ayudante de Investigador B');
INSERT INTO `work_experience` VALUES  ('0000000000039',1,0x00,'1994-06-01 00:00:00','1996-06-30 00:00:00','Facultad de Medicina, UNAM','00044','00006','00011',NULL,'MX','Microbiología y Parasitología','Técnico Académico Asociado A'),
 ('0000000000040',1,0x00,'2005-02-01 00:00:00','2005-06-30 00:00:00','Facultad de Ciencias, UNAM','00044','00006','00011',NULL,'MX','Biología','Profesor de Asignatura'),
 ('0000000000041',2,0x00,'1994-10-10 00:00:00','1999-03-16 00:00:00','Winterthur Seguros','00001','00005','00009',NULL,'ES','Recursos Humanos','Técnico de RR.HH.'),
 ('0000000000042',1,0x00,'1999-03-17 00:00:00','2004-09-30 00:00:00','Faurecia Asientos para Automóviles','00001','00005','00003',NULL,'ES','Recursos Humanos','Responsable de Desarrollo'),
 ('0000000000043',2,0x00,'1989-11-16 00:00:00','2006-10-15 00:00:00','FAURECIA','00002','00005','00003',NULL,'ES','RRHH','ASSISTANT'),
 ('0000000000044',2,0x00,'2004-10-01 00:00:00','2005-12-31 00:00:00','Som-os 51','00001','00005','00002',NULL,'ES','','Colaboración free-lance'),
 ('0000000000045',1,0x00,'1985-05-02 00:00:00','1989-11-15 00:00:00','TECNICAS MÉDICAS MAB, S.A.','00002','00005','00001',NULL,'ES','IMPORT','ASSISTANT');
INSERT INTO `work_experience` VALUES  ('0000000000046',1,0x00,'2006-01-01 00:00:00','2006-09-17 00:00:00','Gestmusic Endemol','00001','00005','00013',NULL,'ES','Recursos Humanos','Responsable Dpto. Recursos Humanos'),
 ('0000000000047',2,0x00,'1997-01-01 00:00:00','1998-12-31 00:00:00','Talleres MR 91, S.L.','00032','00005','00005',NULL,'ES','Administración','Aux.Administrativa'),
 ('0000000000048',1,0x00,'1998-12-31 00:00:00','2000-12-31 00:00:00','IMS Assessors Econòmics i Jurídics, S.L.','00032','00005','00006',NULL,'ES','Assessoria Fiscal i Comptable','Adminsitrativa-comptable'),
 ('0000000000049',1,0x00,'2000-01-01 00:00:00','2002-12-31 00:00:00','Institut de Reinserció Social','00032','00004','00014',NULL,'ES','Administració','Responsable de Compres'),
 ('0000000000050',1,0x00,'2002-01-01 00:00:00','2007-04-16 00:00:00','Institut de Reinserció Social','00032','00004','00014',NULL,'ES','Administración','Responsable de Comptabilitat'),
 ('0000000000051',1,0x00,'1990-09-01 00:00:00','1991-09-30 00:00:00','Caja Laboral','00028','00005','00009',NULL,'ES','Informática','Software Developer'),
 ('0000000000052',2,0x00,'1997-06-01 00:00:00','1997-10-01 00:00:00','Darmex','00033','00005','00015',NULL,'ES','Administración','Aux.Administrativa-Contable');
INSERT INTO `work_experience` VALUES  ('0000000000053',1,0x00,'1999-06-01 00:00:00','1999-12-28 00:00:00','AIRTEL','00033','00005','00013',NULL,'ES','Atención al Cliente','Teleoperadora'),
 ('0000000000054',1,0x00,'2000-01-12 00:00:00','2000-03-30 00:00:00','Compaq','00033','00005','00013',NULL,'ES','Comercial','Administrativa comercial'),
 ('0000000000055',2,0x00,'1993-05-01 00:00:00','1996-02-28 00:00:00','Hewlett Packard','00028','00005','00016',NULL,'ES','Informática','Software'),
 ('0000000000056',1,0x00,'2000-06-01 00:00:00','2002-07-20 00:00:00','Share Informatica (ST ECI)','00033','00005','00015',NULL,'ES','Administracion','Administrativa'),
 ('0000000000057',2,0x00,'2005-01-01 00:00:00','2007-03-06 00:00:00','Free-lance','00028','00007','00015',NULL,'ES','','Software Developer'),
 ('0000000000058',1,0x00,'2002-07-21 00:00:00','2003-03-01 00:00:00','Bufet Fabregas Oriola','00033','00005','00006',NULL,'ES','Adiministración Recobro','Administrativa'),
 ('0000000000059',1,0x00,'2003-03-02 00:00:00','2006-12-10 00:00:00','Bufet Oriola Abogados','00033','00005','00006',NULL,'ES','Administración Grandes Cuentas','Responsable Dept.'),
 ('0000000000060',2,0x00,'1994-09-01 00:00:00','1999-09-01 00:00:00','Universitat Pompeu Fabra','00053','00006','00011',NULL,'ES','Economia i Finances','Teaching Assistant');
INSERT INTO `work_experience` VALUES  ('0000000000061',1,0x00,'1999-09-01 00:00:00','2001-09-01 00:00:00','Arbora&Ausonia','00053','00005','00001',NULL,'ES','Marketing','Assistant Brand Manager'),
 ('0000000000062',1,0x00,'2001-09-01 00:00:00','2006-09-01 00:00:00','Retevisión','00053','00005','00016',NULL,'ES','Marketing Residencial','Gerente de Marketing'),
 ('0000000000063',1,0x00,'2002-10-01 00:00:00','2007-05-15 00:00:00','COLUMBIA UNIVERSITY','00061','00006','00011',NULL,'US','INSTITUTE FOR CANCER GENETICS','POSTDOCTORAL RESEARCH SCIENTIST'),
 ('0000000000064',1,0x00,'1999-01-01 00:00:00','2002-10-01 00:00:00','INSTITUT MUNICIPAL INVESTIGACIO MEDICA (IMIM)','00061','00004','00011',NULL,'ES','UNITAT DE BIOLOGIA CEL.LULAR I MOLECULAR','PhD student'),
 ('0000000000065',2,0x00,'1995-01-01 00:00:00','1998-12-31 00:00:00','HOSPITAL DEL MAR-IMAS','00061','00004','00001',NULL,'ES','DEPARTMENT OF PATHOLOGY','PATHOLOGY RESIDENCY PROGRAM'),
 ('0000000000066',1,0x00,'1994-01-01 00:00:00','1995-02-28 00:00:00','Espelsa','00029','00005','00016',NULL,'ES','IT','Ingeniero de Informática'),
 ('0000000000067',1,0x00,'1998-10-18 00:00:00','2004-05-31 00:00:00','CBMSO','00070','00004','00011',NULL,'ES','','Predoctoral fellow');
INSERT INTO `work_experience` VALUES  ('0000000000068',1,0x00,'2004-12-01 00:00:00','2005-09-30 00:00:00','IRB','00065','00004','00011',NULL,'ES','','practical training'),
 ('0000000000069',2,0x00,'2003-12-01 00:00:00','2006-03-02 00:00:00','Pla Integral Casc Antic','00059','00005','00015',NULL,'ES','Gerència','Coordinadora tècnica'),
 ('0000000000070',1,0x00,'2000-01-01 00:00:00','2003-12-31 00:00:00','Raffel Pages','00059','00005','00015',NULL,'ES','Gerència/ Comunicació','Secretària de direcció'),
 ('0000000000071',3,0x00,'1998-01-01 00:00:00','2000-12-31 00:00:00','Fundació Casc Antic','00059','00007','00011',NULL,'ES','Projectes Europeus','Translator-interpreter/ development worker'),
 ('0000000000072',1,0x00,'1999-01-01 00:00:00','2000-12-31 00:00:00','Nissan Motor España','00059','00005','00003',NULL,'ES','Training','Teacher of English'),
 ('0000000000073',1,0x00,'1997-01-01 00:00:00','1997-12-31 00:00:00','Rafael Vargas Fotógrafo','00059','00005','00007',NULL,'ES','Adminsitration','Administrative assistant'),
 ('0000000000074',1,0x00,'1995-01-01 00:00:00','1999-12-31 00:00:00','NGOs','00059','00007','00008',NULL,'ES','','Translator-interpreter/ Teacher of languages');
INSERT INTO `work_experience` VALUES  ('0000000000075',1,0x00,'2000-06-01 00:00:00','2003-09-30 00:00:00','EMBL','00068','00004','00011',NULL,'ES','','Staff Scientist'),
 ('0000000000076',2,0x00,'2004-01-01 00:00:00','2004-06-24 00:00:00','Almirall Prodesfarma S.A.','00072','00005','00001',NULL,'ES','Pharmacokinethics and Drug Metabolism','Research Assistant in Work Practice'),
 ('0000000000077',2,0x00,'2003-07-01 00:00:00','2003-11-30 00:00:00','Advancell. Advanced in vitro cell tecnologies','00072','00005','00011',NULL,'ES','Research','Research Assistant in Work Practice'),
 ('0000000000078',1,0x00,'2002-08-01 00:00:00','2002-09-15 00:00:00','Université de Genève','00072','00006','00011',NULL,'CH','Biochemistry','Research Assistant in Work Practice'),
 ('0000000000079',1,0x00,'2000-07-01 00:00:00','2000-07-31 00:00:00','Nijmegen University','00072','00006','00011',NULL,'NL','Biochemistry','Research Assistant in Work Practice'),
 ('0000000000080',1,0x00,'2002-01-01 00:00:00',NULL,'Universitat de Barcelona','00075','00006','00011',NULL,'ES','Dept. Quimica Organica','Professor associat'),
 ('0000000000081',1,0x00,'1994-07-01 00:00:00','1999-06-30 00:00:00','EMBL','00069','00004','00001',NULL,'DE','biochemical instrumentation ','Group Leader');
INSERT INTO `work_experience` VALUES  ('0000000000082',1,0x00,'1994-07-01 00:00:00','1999-06-30 00:00:00','EMBL','00021','00004','00001',NULL,'DE','biochemical instrumentation ','Group Leader'),
 ('0000000000083',1,0x00,'1997-04-01 00:00:00','1997-09-30 00:00:00','Hayim Shiba Medical Center, Tel 	Hashomer','00091','00004','00001',NULL,'IL','Genetic Institue','Lab technician'),
 ('0000000000084',2,0x00,'2007-02-26 00:00:00','2007-04-12 00:00:00','SIMPSON HOUSE-CROSSREACH','00108','00005','00015',NULL,'GB','DOMESTIC','CLEANER'),
 ('0000000000085',1,0x00,'2005-11-28 00:00:00','2007-02-23 00:00:00','APEX HOTEL','00108','00005','00012',NULL,'GB','HOUSEKEEPING','ROOM ATTENDANT'),
 ('0000000000087',2,0x01,'2002-07-01 00:00:00','2006-04-04 00:00:00','LABORATORI DE DIAGNOSTIC GENERAL','00110','00004','00008',NULL,'ZM','','Doctor'),
 ('0000000000091',5,0x00,'2000-01-01 00:00:00',NULL,'asdfadsfa','00030','00004','00010',NULL,'ES','assdfasdf','asdfasdf'),
 ('0000000000092',2,0x00,'2000-01-01 00:00:00',NULL,'adfadf','00030','00004','00007',NULL,'ES','adsfasdf','asdfasdf'),
 ('0000000000093',1,0x00,'2000-01-01 00:00:00',NULL,'jdjd','00030','00005','00011',NULL,'ES','jdjd','djdj');
INSERT INTO `work_experience` VALUES  ('0000000000094',1,0x00,'2000-01-01 00:00:00',NULL,'lakdjf','00030','00005','00011',NULL,'ES','alkdjf','alkdfj'),
 ('0000000000095',3,0x00,'2007-10-10 00:00:00',NULL,'alkddjf','00001','00005','00014',NULL,'ES','','alkjdjf'),
 ('0000000000096',3,0x00,'2007-02-28 00:00:00',NULL,'asdfasdf','00001','00005','00016',NULL,'ES','','asdfadsf'),
 ('0000000000097',1,0x00,'2000-01-01 00:00:00','2000-12-31 00:00:00','prueba','00118','00005','00013',NULL,'ES','','jefecillo'),
 ('0000000000098',1,0x00,'2000-01-01 00:00:00','2000-12-31 00:00:00','asdfasdf','00001','00004','00007',NULL,'ES','','asdfasd'),
 ('0000000000099',1,0x00,'1996-08-13 00:00:00','2008-02-06 00:00:00','fsdfa','00001','00007','00015',NULL,'UA','','--');
/*!40000 ALTER TABLE `work_experience` ENABLE KEYS */;


--
-- Definition of table `working_hours`
--

DROP TABLE IF EXISTS `working_hours`;
CREATE TABLE `working_hours` (
  `working_hourscode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`working_hourscode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `working_hours`
--

/*!40000 ALTER TABLE `working_hours` DISABLE KEYS */;
INSERT INTO `working_hours` VALUES  ('00001',103,0x00,'40 horas'),
 ('00002',1,0x01,'37,5 horas'),
 ('00003',2,0x01,'37 horas'),
 ('00004',1,0x01,'20 horas'),
 ('00005',20,0x00,'20 horas'),
 ('00006',1,0x01,'');
/*!40000 ALTER TABLE `working_hours` ENABLE KEYS */;


--
-- Table structure for table `buildings`
-- 

DROP TABLE IF EXISTS `buildings`;
CREATE TABLE `buildings` (
  `type_of_buildingcode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) default NULL,
  `description` varchar(100) default NULL,
  PRIMARY KEY  (`type_of_buildingcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buildings`
-- 

INSERT INTO `buildings` (`type_of_buildingcode`, `version`, `deleted`,
`description`) VALUES
('administracion', 1, NULL, 'Edificio Administración'),
('cluster', 1, NULL, 'Edificio Clúster'),
('helix', 1, NULL, 'Edificio Hèlix');






--
-- Definition of view `view_active_personal`
--

DROP TABLE IF EXISTS `view_active_personal`;
DROP VIEW IF EXISTS `view_active_personal`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_active_personal` AS select `personal`.`personalcode` AS `personalcode`,`personal`.`version` AS `version`,`personal`.`deleted` AS `deleted`,`personal`.`name` AS `name`,`personal`.`surname` AS `surname`,`personal`.`dni` AS `dni`,`personal`.`birth_date` AS `birth_date`,`personal`.`birth_city` AS `birth_city`,`personal`.`street` AS `street`,`personal`.`city` AS `city`,`personal`.`zip_code` AS `zip_code`,`personal`.`phone` AS `phone`,`personal`.`phone2` AS `phone2`,`personal`.`ice_phone` AS `ice_phone`,`personal`.`ss_number` AS `ss_number`,`personal`.`bank_account` AS `bank_account`,`personal`.`research_project` AS `research_project`,`personal`.`sponsoring_agency` AS `sponsoring_agency`,`personal`.`holding_institution` AS `holding_institution`,`personal`.`principal_investigator` AS `principal_investigator`,`personal`.`end_of_contract_reason` AS `end_of_contract_reason`,`personal`.`end_of_contract_address` AS `end_of_contract_address`,`personal`.`end_of_contract_city` AS `end_of_contract_city`,`personal`.`end_of_contract_zip_code` AS `end_of_contract_zip_code`,`personal`.`end_of_contract_phone` AS `end_of_contract_phone`,`personal`.`end_of_contract_email` AS `end_of_contract_email`,`personal`.`birth_country` AS `birth_country`,`personal`.`nationality` AS `nationality`,`personal`.`nationality_2` AS `nationality_2`,`personal`.`country` AS `country`,`personal`.`payments` AS `payments`,`personal`.`end_of_contract_country` AS `end_of_contract_country`,`personal`.`gender` AS `gender`,`personal`.`marital_status` AS `marital_status`,`personal`.`bank` AS `bank`,`personal`.`working_hours` AS `working_hours`,`personal`.`category` AS `category`,`personal`.`state` AS `state`,`personal`.`photo` AS `photo`,if(((`personal`.`category` is not null) or (`personal`.`working_hours` is not null)),1,0) AS `isemployee` from `personal` where ((`personal`.`deleted` = 0) and (`personal`.`state` = 5));

--
-- Definition of view `view_all_personal`
--

DROP TABLE IF EXISTS `view_all_personal`;
DROP VIEW IF EXISTS `view_all_personal`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_all_personal` AS select `p`.`personalcode` AS `personalcode`,`p`.`version` AS `version`,`p`.`deleted` AS `deleted`,`p`.`name` AS `name`,`p`.`surname` AS `surname`,`p`.`dni` AS `dni`,`p`.`birth_date` AS `birth_date`,`p`.`birth_city` AS `birth_city`,`p`.`street` AS `street`,`p`.`city` AS `city`,`p`.`zip_code` AS `zip_code`,`p`.`phone` AS `phone`,`p`.`phone2` AS `phone2`,`p`.`ice_phone` AS `ice_phone`,`p`.`ss_number` AS `ss_number`,`p`.`bank_account` AS `bank_account`,`p`.`research_project` AS `research_project`,`p`.`sponsoring_agency` AS `sponsoring_agency`,`p`.`holding_institution` AS `holding_institution`,`p`.`principal_investigator` AS `principal_investigator`,`p`.`end_of_contract_reason` AS `end_of_contract_reason`,`p`.`end_of_contract_address` AS `end_of_contract_address`,`p`.`end_of_contract_city` AS `end_of_contract_city`,`p`.`end_of_contract_zip_code` AS `end_of_contract_zip_code`,`p`.`end_of_contract_phone` AS `end_of_contract_phone`,`p`.`end_of_contract_email` AS `end_of_contract_email`,`p`.`birth_country` AS `birth_country`,`p`.`nationality` AS `nationality`,`p`.`nationality_2` AS `nationality_2`,`c`.`description` AS `country`,`p`.`payments` AS `payments`,`p`.`end_of_contract_country` AS `end_of_contract_country`,`p`.`gender` AS `gender`,`p`.`marital_status` AS `marital_status`,`p`.`bank` AS `bank`,`p`.`working_hours` AS `working_hours`,`p`.`category` AS `category`,`p`.`state` AS `state`,`p`.`photo` AS `photo`,`p`.`surname1` AS `surname1`,`p`.`surname2` AS `surname2`,`p`.`validationCode` AS `validationCode` from (`personal` `p` join `country` `c` on((`p`.`country` = `c`.`countrycode`)));

--
-- Definition of view `view_compensation`
--

DROP TABLE IF EXISTS `view_compensation`;
DROP VIEW IF EXISTS `view_compensation`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_compensation` AS select `compensation`.`compensationcode` AS `compensationcode`,`compensation`.`version` AS `version`,`compensation`.`deleted` AS `deleted`,`compensation`.`start_date` AS `start_date`,`compensation`.`end_date` AS `end_date`,`compensation`.`description` AS `description`,`compensation`.`amount` AS `amount`,`compensation`.`compensation_personal` AS `compensation_personal`,`compensation`.`type_of_compensation` AS `type_of_compensation` from `compensation`;

--
-- Definition of view `view_little_personal`
--

DROP TABLE IF EXISTS `view_little_personal`;
DROP VIEW IF EXISTS `view_little_personal`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_little_personal` AS select `p`.`personalcode` AS `personalcode`,`p`.`name` AS `name`,`p`.`surname` AS `surname`,`p`.`dni` AS `dni`,`p`.`birth_date` AS `birth_date`,`c`.`description` AS `country` from (`personal` `p` join `country` `c` on((`p`.`country` = `c`.`countrycode`)));

--
-- Definition of view `view_notirb_active_personal`
--

DROP TABLE IF EXISTS `view_notirb_active_personal`;
DROP VIEW IF EXISTS `view_notirb_active_personal`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_notirb_active_personal` AS select distinct `p`.`personalcode` AS `personalcode`,`p`.`version` AS `version`,`p`.`deleted` AS `deleted`,`p`.`name` AS `name`,`p`.`surname` AS `surname`,`p`.`dni` AS `dni`,`p`.`birth_date` AS `birth_date`,`p`.`birth_city` AS `birth_city`,`p`.`street` AS `street`,`p`.`city` AS `city`,`p`.`zip_code` AS `zip_code`,`p`.`phone` AS `phone`,`p`.`phone2` AS `phone2`,`p`.`ice_phone` AS `ice_phone`,`p`.`ss_number` AS `ss_number`,`p`.`bank_account` AS `bank_account`,`p`.`research_project` AS `research_project`,`p`.`sponsoring_agency` AS `sponsoring_agency`,`p`.`holding_institution` AS `holding_institution`,`p`.`principal_investigator` AS `principal_investigator`,`p`.`end_of_contract_reason` AS `end_of_contract_reason`,`p`.`end_of_contract_address` AS `end_of_contract_address`,`p`.`end_of_contract_city` AS `end_of_contract_city`,`p`.`end_of_contract_zip_code` AS `end_of_contract_zip_code`,`p`.`end_of_contract_phone` AS `end_of_contract_phone`,`p`.`end_of_contract_email` AS `end_of_contract_email`,`p`.`birth_country` AS `birth_country`,`p`.`nationality` AS `nationality`,`p`.`nationality_2` AS `nationality_2`,`p`.`country` AS `country`,`p`.`payments` AS `payments`,`p`.`end_of_contract_country` AS `end_of_contract_country`,`p`.`gender` AS `gender`,`p`.`marital_status` AS `marital_status`,`p`.`bank` AS `bank`,`p`.`working_hours` AS `working_hours`,`p`.`category` AS `category`,`p`.`state` AS `state`,`p`.`photo` AS `photo`,`p`.`surname1` AS `surname1`,`p`.`surname2` AS `surname2`,`p`.`validationCode` AS `validationCode`,`p`.`personal_email` AS `personal_email`,`p`.`language` AS `language`,`p`.`username` AS `username` from `personal` `p` where ((`p`.`deleted` = 0) and (`p`.`state` = 5) and (not(`p`.`personalcode` in (select `pe`.`personalcode` AS `personalcode` from ((`personal` `pe` join `professional` `pr`) join `type_of_contract` `t`) where ((`pe`.`personalcode` = `pr`.`professional_personal`) and (`pr`.`current` = 1) and (`pr`.`type_of_contract` = `t`.`type_of_contractcode`) and (`t`.`is_irbs` = 1))))) and (not(`p`.`personalcode` in (select `pe`.`personalcode` AS `personalcode` from ((`personal` `pe` join `grant_concession` `g`) join `table_grant` `tg`) where ((`pe`.`personalcode` = `g`.`grant_concession_personal`) and (`g`.`current` = 1) and (`g`.`table_grant` = `tg`.`grantcode`) and (`tg`.`is_irbs` = 1))))));

--
-- Definition of view `view_organizationunit_unit`
--

DROP TABLE IF EXISTS `view_organizationunit_unit`;
DROP VIEW IF EXISTS `view_organizationunit_unit`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_organizationunit_unit` AS select `ou`.`organization_unitcode` AS `organization_unitcode`,`ou`.`description` AS `organization_unit_desc`,`u`.`unitcode` AS `unitcode`,`u`.`description` AS `unit_desc` from (`organization_unit` `ou` join `unit` `u` on((`ou`.`organization_unitcode` = `u`.`organization_unit`)));

--
-- Definition of view `view_personal_professional`
--

DROP TABLE IF EXISTS `view_personal_professional`;
DROP VIEW IF EXISTS `view_personal_professional`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_personal_professional` AS select `pe`.`personalcode` AS `personalcode`,`pe`.`name` AS `name`,`pe`.`surname1` AS `surname1`,`pe`.`surname2` AS `surname2`,`pe`.`dni` AS `dni`,`pe`.`birth_date` AS `birth_date`,`pe`.`birth_city` AS `birth_city`,`pe`.`street` AS `street`,`pe`.`city` AS `city`,`pe`.`zip_code` AS `zip_code`,`pe`.`phone` AS `phone`,`pe`.`phone2` AS `phone2`,`pe`.`ice_phone` AS `ice_phone`,`pe`.`ss_number` AS `ss_number`,`pe`.`bank_account` AS `bank_account`,`pe`.`research_project` AS `research_project`,`pe`.`sponsoring_agency` AS `sponsoring_agency`,`pe`.`holding_institution` AS `holding_institution`,`pe`.`principal_investigator` AS `principal_investigator`,`pe`.`end_of_contract_reason` AS `end_of_contract_reason`,`pe`.`end_of_contract_address` AS `end_of_contract_address`,`pe`.`end_of_contract_city` AS `end_of_contract_city`,`pe`.`end_of_contract_zip_code` AS `end_of_contract_zip_code`,`pe`.`end_of_contract_phone` AS `end_of_contract_phone`,`pe`.`end_of_contract_email` AS `end_of_contract_email`,`ecc`.`description` AS `end_of_contract_country`,`bc`.`description` AS `birth_country`,`n`.`description` AS `nationality`,`n2`.`description` AS `nationality_2`,`c`.`description` AS `country`,`pa`.`description` AS `payments`,`ge`.`description` AS `gender`,`ms`.`description` AS `marital_status`,`bk`.`description` AS `bank`,`wh`.`description` AS `working_hours`,`ca`.`description` AS `category`,`ps`.`description` AS `state`,`pe`.`personal_email` AS `personal_email`,`pe`.`language` AS `language`,`pro`.`start_date` AS `start_date`,`pro`.`end_date` AS `end_date`,`pro`.`location` AS `location`,`pro`.`email` AS `email`,`pro`.`phone` AS `professional_phone`,`pro`.`mobile_long` AS `mobile_long`,`pro`.`mobile_short` AS `mobile_short`,`pro`.`lab_phone_number` AS `lab_phone_number`,`pro`.`fax` AS `fax`,`toc`.`description` AS `type_of_contract`,`toc`.`is_irbs` AS `contract_is_irbs`,`pos`.`description` AS `position`,`pay`.`description` AS `payroll_institution`,`pay2`.`description` AS `payroll_institution_2`,`rp`.`description` AS `research_group`,`un`.`description` AS `professional_unit`,`ou`.`description` AS `organization_unit` from ((((((((((((((((((((`personal` `pe` left join `country` `ecc` on((`pe`.`end_of_contract_country` = `ecc`.`countrycode`))) left join `country` `c` on((`pe`.`country` = `c`.`countrycode`))) left join `country` `bc` on((`pe`.`birth_country` = `bc`.`countrycode`))) left join `nationality` `n` on((`pe`.`nationality` = `n`.`nationalitycode`))) left join `nationality` `n2` on((`pe`.`nationality_2` = `n2`.`nationalitycode`))) left join `payment` `pa` on((`pe`.`payments` = `pa`.`paymentcode`))) left join `gender` `ge` on((`pe`.`gender` = `ge`.`gendercode`))) left join `marital_status` `ms` on((`pe`.`marital_status` = `ms`.`marital_statuscode`))) left join `bank` `bk` on((`pe`.`bank` = `bk`.`bankcode`))) left join `working_hours` `wh` on((`pe`.`working_hours` = `wh`.`working_hourscode`))) left join `category` `ca` on((`pe`.`category` = `ca`.`categorycode`))) left join `personalstate` `ps` on((`pe`.`state` = `ps`.`personalstatecode`))) left join `professional` `pro` on(((`pe`.`personalcode` = `pro`.`professional_personal`) and (`pro`.`current` = 1)))) left join `type_of_contract` `toc` on((`pro`.`type_of_contract` = `toc`.`type_of_contractcode`))) left join `position` `pos` on((`pro`.`position` = `pos`.`positioncode`))) left join `payroll_institution` `pay` on((`pro`.`payroll_institution` = `pay`.`payroll_institutioncode`))) left join `payroll_institution` `pay2` on((`pro`.`payroll_institution_2` = `pay2`.`payroll_institutioncode`))) left join `research_group` `rp` on((`pro`.`research_group` = `rp`.`research_groupcode`))) left join `unit` `un` on((`pro`.`professional_unit` = `un`.`unitcode`))) left join `organization_unit` `ou` on((`un`.`organization_unit` = `ou`.`organization_unitcode`))) where (`pe`.`deleted` = 0);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
