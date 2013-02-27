update application_preferences set obtentionPhaseActive=0;

INSERT INTO `beancodes` (`bean`,`name`,`depvalues`,`version`,`depnames`,`pattern`,`codenumber`) VALUES 
  ('calendar','code','null',79,'null','{n}','0000000000080');
INSERT INTO `beancodes` (`bean`,`name`,`depvalues`,`version`,`depnames`,`pattern`,`codenumber`) VALUES 
  ('column','code','null',154,'null','{n}','0000000000151');
INSERT INTO `beancodes` (`bean`,`name`,`depvalues`,`version`,`depnames`,`pattern`,`codenumber`) VALUES 
  ('customlist','code','null',43,'null','{n}','0000000000028');
INSERT INTO `beancodes` (`bean`,`name`,`depvalues`,`version`,`depnames`,`pattern`,`codenumber`) VALUES 
  ('filter','code','null',72,'null','{n}','0000000000063');
INSERT INTO `beancodes` (`bean`,`name`,`depvalues`,`version`,`depnames`,`pattern`,`codenumber`) VALUES 
  ('irbholiday','code','null',498,'null','{n}','0000000000499');
INSERT INTO `beancodes` (`bean`,`name`,`depvalues`,`version`,`depnames`,`pattern`,`codenumber`) VALUES 
  ('irbholidayinfo','code','null',685,'null','{n}','0000000000686');
INSERT INTO `beancodes` (`bean`,`name`,`depvalues`,`version`,`depnames`,`pattern`,`codenumber`) VALUES 
  ('orderby','code','null',50,'null','{n}','0000000000040');
  
--
-- Definition of table `calendar`
--
CREATE TABLE `calendar` (
  `calendarcode` varchar(45) NOT NULL,
  `day` date NOT NULL,
  `type` char(1) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY  USING BTREE (`calendarcode`),
  UNIQUE KEY `Index_2` USING BTREE (`day`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


alter table compensation modify column description varchar(255) default NULL;

--
-- Definition of table `irbholiday`
--
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `irbholidayinfo`
--
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


UPDATE personalstate set description="validated" where personalstatecode=3;

ALTER TABLE personal ADD COLUMN validationCode varchar(100) default NULL;

ALTER TABLE personal ADD COLUMN personal_email varchar(100) default NULL;

ALTER TABLE professional CHANGE COLUMN mobile mobile_long varchar(100) default NULL;

ALTER TABLE professional ADD COLUMN payroll_institution_2 varchar(255) default NULL;

ALTER TABLE professional ADD COLUMN mobile_short varchar(255) default NULL;

ALTER TABLE professional ADD COLUMN lab_phone_number varchar(255) default NULL;

ALTER TABLE professional ADD COLUMN fax varchar(255) default NULL;

ALTER TABLE professional ADD KEY `FK_professional_8` (`payroll_institution_2`);

ALTER TABLE professional ADD CONSTRAINT `FK_professional_8` FOREIGN KEY (`payroll_institution_2`) REFERENCES `payroll_institution` (`payroll_institutioncode`);

--
-- Definition of table `reportcolumn`
--
CREATE TABLE `reportcolumn` (
  `columncode` varchar(255) NOT NULL,
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `name` varchar(255) default NULL,
  `assocCustomList` varchar(255) default NULL,
  PRIMARY KEY  (`columncode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `reportcustomlist`
--
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
-- Definition of table `reportfilter`
--
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
-- Definition of table `reportfiltertype`
--
CREATE TABLE `reportfiltertype` (
  `filterTypecode` varchar(255) NOT NULL default '',
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`filterTypecode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `reportfiltertype` (`filterTypecode`,`version`,`deleted`,`name`) VALUES 
  ('equal',1,0x00,'equal');
INSERT INTO `reportfiltertype` (`filterTypecode`,`version`,`deleted`,`name`) VALUES 
  ('greater',1,0x00,'greater');
INSERT INTO `reportfiltertype` (`filterTypecode`,`version`,`deleted`,`name`) VALUES 
  ('greaterEqual',1,0x00,'greaterEqual');
INSERT INTO `reportfiltertype` (`filterTypecode`,`version`,`deleted`,`name`) VALUES 
  ('like',1,0x00,'like');
INSERT INTO `reportfiltertype` (`filterTypecode`,`version`,`deleted`,`name`) VALUES 
  ('lower',1,0x00,'lower');
INSERT INTO `reportfiltertype` (`filterTypecode`,`version`,`deleted`,`name`) VALUES 
  ('lowerEqual',1,0x00,'lowerEqual');
  

--
-- Definition of table `reportorderby`
--
CREATE TABLE `reportorderby` (
  `orderBycode` varchar(255) NOT NULL default '',
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default NULL,
  `name` varchar(255) default NULL,
  `assocCustomList` varchar(255) default NULL,
  PRIMARY KEY  (`orderBycode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `entity` values('management',0,NULL);

INSERT INTO `role` (`entitycode`,`description`,`type`) VALUES 
  ('management','management',NULL);



  
ALTER TABLE table_grant ADD COLUMN is_irbs bit(1) default 0;

UPDATE table_grant SET is_irbs=1 WHERE description like '%IRB%';


ALTER TABLE type_of_contract ADD COLUMN is_irbs bit(1) default 0;

UPDATE type_of_contract SET is_irbs=1 WHERE description like 'contrato%';


ALTER TABLE unit ADD COLUMN supervisor varchar(255) default NULL;

ALTER TABLE unit ADD KEY `FK_unit_2`(`supervisor`);

ALTER TABLE unit ADD CONSTRAINT `FK_unit_2` FOREIGN KEY (`supervisor`) REFERENCES `personal` (`personalcode`);

--
-- Definition of table `views`
--
CREATE TABLE `views` (
  `viewscode` varchar(255) NOT NULL default '',
  `version` int(11) NOT NULL default '0',
  `deleted` bit(1) default '\0',
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`viewscode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `views`
--

/*!40000 ALTER TABLE `views` DISABLE KEYS */;
INSERT INTO `views` (`viewscode`,`version`,`deleted`,`name`) VALUES 
  ('1',1,0x00,'view_all_personal');
INSERT INTO `views` (`viewscode`,`version`,`deleted`,`name`) VALUES 
  ('2',1,0x00,'view_little_personal');
INSERT INTO `views` (`viewscode`,`version`,`deleted`,`name`) VALUES 
  ('3',1,0x00,'view_compensation');
/*!40000 ALTER TABLE `views` ENABLE KEYS */;


--
-- Definition of view `view_active_personal`
--
DROP VIEW IF EXISTS `irbdb`.`view_active_personal`;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_active_personal` AS select 
`personal`.*,if(((
`personal`.`category` is not null) or (
`personal`.`working_hours` is not null)),1,0) AS `isemployee` from `personal` where ((
`personal`.`deleted` = 0) and (
`personal`.`state` = 5));
--
-- Definition of view `view_all_personal`
--
CREATE VIEW `view_all_personal` AS select `p`.`personalcode` AS `personalcode`,`p`.`version` AS `version`,`p`.`deleted` AS `deleted`,`p`.`name` AS `name`,`p`.`surname` AS `surname`,`p`.`dni` AS `dni`,`p`.`birth_date` AS `birth_date`,`p`.`birth_city` AS `birth_city`,`p`.`street` AS `street`,`p`.`city` AS `city`,`p`.`zip_code` AS `zip_code`,`p`.`phone` AS `phone`,`p`.`phone2` AS `phone2`,`p`.`ice_phone` AS `ice_phone`,`p`.`ss_number` AS `ss_number`,`p`.`bank_account` AS `bank_account`,`p`.`research_project` AS `research_project`,`p`.`sponsoring_agency` AS `sponsoring_agency`,`p`.`holding_institution` AS `holding_institution`,`p`.`principal_investigator` AS `principal_investigator`,`p`.`end_of_contract_reason` AS `end_of_contract_reason`,`p`.`end_of_contract_address` AS `end_of_contract_address`,`p`.`end_of_contract_city` AS `end_of_contract_city`,`p`.`end_of_contract_zip_code` AS `end_of_contract_zip_code`,`p`.`end_of_contract_phone` AS `end_of_contract_phone`,`p`.`end_of_contract_email` AS `end_of_contract_email`,`p`.`birth_country` AS `birth_country`,`p`.`nationality` AS `nationality`,`p`.`nationality_2` AS `nationality_2`,`c`.`description` AS `country`,`p`.`payments` AS `payments`,`p`.`end_of_contract_country` AS `end_of_contract_country`,`p`.`gender` AS `gender`,`p`.`marital_status` AS `marital_status`,`p`.`bank` AS `bank`,`p`.`working_hours` AS `working_hours`,`p`.`category` AS `category`,`p`.`state` AS `state`,`p`.`photo` AS `photo`,`p`.`surname1` AS `surname1`,`p`.`surname2` AS `surname2`,`p`.`validationCode` AS `validationCode` from (`personal` `p` join `country` `c` on((`p`.`country` = `c`.`countrycode`)));

--
-- Definition of view `view_compensation`
--
CREATE VIEW `view_compensation` AS select `compensation`.`compensationcode` AS `compensationcode`,`compensation`.`version` AS `version`,`compensation`.`deleted` AS `deleted`,`compensation`.`start_date` AS `start_date`,`compensation`.`end_date` AS `end_date`,`compensation`.`description` AS `description`,`compensation`.`amount` AS `amount`,`compensation`.`compensation_personal` AS `compensation_personal`,`compensation`.`type_of_compensation` AS `type_of_compensation` from `compensation`;

--
-- Definition of view `view_little_personal`
--
CREATE VIEW `view_little_personal` AS select `p`.`personalcode` AS `personalcode`,`p`.`name` AS `name`,`p`.`surname` AS `surname`,`p`.`dni` AS `dni`,`p`.`birth_date` AS `birth_date`,`c`.`description` AS `country` from (`personal` `p` join `country` `c` on((`p`.`country` = `c`.`countrycode`)));

--
-- Definition of view `view_organizationunit_unit`
--
DROP VIEW IF EXISTS `view_organizationunit_unit`;
CREATE VIEW `view_organizationunit_unit` AS select `ou`.`organization_unitcode` AS `organization_unitcode`,`ou`.`description` AS `organization_unit_desc`,`u`.`unitcode` AS `unitcode`,`u`.`description` AS `unit_desc` from (`organization_unit` `ou` join `unit` `u` on((`ou`.`organization_unitcode` = `u`.`organization_unit`)));


-- ######################################################################################################
--    UPGRADE DE LA BD 2008-10-27
-- ######################################################################################################
ALTER TABLE `compensation` ADD COLUMN `current` BIT(1) NOT NULL;
ALTER TABLE `grant_concession` ADD COLUMN `current` BIT(1) NOT NULL;
ALTER TABLE `professional` ADD COLUMN `current` BIT(1) NOT NULL;

ALTER TABLE `personal` ADD COLUMN `language` VARCHAR(15) DEFAULT 'en';

insert into entity values('irbpeople_rw',0,0);
insert into entity values('irbpeople_ro',0,1);

insert into role values('irbpeople_rw','irbpeople_rw',null);
insert into role values('irbpeople_ro','irbpeople_ro',null);

update userrole set rolecode='irbpeople_rw' where rolecode='human resources';
update userrole set rolecode='irbpeople_ro' where rolecode='management';

delete from role where entitycode in ('human resources','management');

delete from entity where entitycode in ('human resources','management');

-- marcado de registros current en professional
update professional set current=1 where (start_date is not null and start_date <= curdate() and end_date is null) or (end_date is not null and start_date is not null and curdate() between start_date and end_date);
-- personas con más de un current=1 en professional
select professionalcode from professional where professional_personal in (select professional_personal from professional where current=1 group by professional_personal having count(*) > 1) and current=1;

-- marcado de registros current en compensation
update compensation set current=1 where (start_date is not null and start_date <= curdate() and end_date is null) or (end_date is not null and start_date is not null and curdate() between start_date and end_date);
-- personas con más de un current=1 en compensation
select compensationcode from compensation where compensation_personal in (select compensation_personal from compensation where current=1 group by compensation_personal having count(*) > 1) and current=1;

-- marcado de registros current en grant_concession
update grant_concession set current=1 where (start_date is not null and start_date <= curdate() and end_date is null) or (end_date is not null and start_date is not null and curdate() between start_date and end_date);
-- personas con más de un current=1 en grant_concession
select grant_concessioncode from grant_concession where grant_concession_personal in (select grant_concession_personal from grant_concession where current=1 group by grant_concession_personal having count(*) > 1) and current=1;

-- ######################################################################################################
--    UPGRADE DE LA BD 2008-11-03
-- ######################################################################################################
ALTER TABLE `research_group` MODIFY COLUMN `description` VARCHAR(255) CHARACTER SET latin1 DEFAULT NULL;
ALTER TABLE `views` MODIFY COLUMN `name` VARCHAR(31) CHARACTER SET latin1 DEFAULT NULL;

INSERT INTO `views` VALUES (4,1,0,'view_personal_professional');

-- ######################################################################################################
--    UPGRADE DE LA BD 2008-11-07
-- ######################################################################################################
ALTER TABLE `personal` ADD COLUMN `username` VARCHAR(45) AFTER `language`;
-- update personal set username=concat(substring(name,1,1),surname1);
ALTER TABLE `personal` ADD UNIQUE INDEX unique_username(`username`);
ALTER TABLE `personal` DROP FOREIGN KEY `FK49101640D9CD9233`,DROP INDEX `FK49101640D9CD9233`;


-- COMPROBAR REGISTROS ACTUALES DE PROFESSIONAL, COMPENSATION Y GRANT_CONCESSIONS HAY DUPLICADOS (DOS REGISTROS SIN FECHA DE FIN, POR EJEMPLO)
--
-- COMPROBAR PERSONAL.USERNAME (CORREGIR AQUELLOS CUYO USERNAME NO ES INICIAL+APELLIDO


-- ######################################################################################################
--    VISTA PARA EL INFORME DATOS PERSONALES Y PROFESIONALES
-- ######################################################################################################
DROP VIEW IF EXISTS `view_personal_professional`;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_personal_professional` AS
    select
        `pe`.`personalcode` AS `personalcode`,
        `pe`.`name` AS `name`,
        `pe`.`surname1` AS `surname1`,
        `pe`.`surname2` AS `surname2`,
        `pe`.`dni` AS `dni`,
        `pe`.`birth_date` AS `birth_date`,
        `pe`.`birth_city` AS `birth_city`,
        `pe`.`street` AS `street`,
        `pe`.`city` AS `city`,
        `pe`.`zip_code` AS `zip_code`,
        `pe`.`phone` AS `phone`,
        `pe`.`phone2` AS `phone2`,
        `pe`.`ice_phone` AS `ice_phone`,
        `pe`.`ss_number` AS `ss_number`,
        `pe`.`bank_account` AS `bank_account`,
        `pe`.`research_project` AS `research_project`,
        `pe`.`sponsoring_agency` AS `sponsoring_agency`,
        `pe`.`holding_institution` AS `holding_institution`,
        `pe`.`principal_investigator` AS `principal_investigator`,
        `pe`.`end_of_contract_reason` AS `end_of_contract_reason`,
        `pe`.`end_of_contract_address` AS `end_of_contract_address`,
        `pe`.`end_of_contract_city` AS `end_of_contract_city`,
        `pe`.`end_of_contract_zip_code` AS `end_of_contract_zip_code`,
        `pe`.`end_of_contract_phone` AS `end_of_contract_phone`,
        `pe`.`end_of_contract_email` AS `end_of_contract_email`,
        `ecc`.`description` AS `end_of_contract_country`,
        `bc`.`description` AS `birth_country`,
        `n`.`description` AS `nationality`,
        `n2`.`description` AS `nationality_2`,
        `c`.`description` AS `country`,
        `pa`.`description` AS `payments`,
        `ge`.`description` AS `gender`,
        `ms`.`description` AS `marital_status`,
        `bk`.`description` AS `bank`,
        `wh`.`description` AS `working_hours`,
        `ca`.`description` AS `category`,
        `ps`.`description` AS `state`,
        `pe`.`personal_email` AS `personal_email`,
        `pe`.`language` AS `language`,
        `pro`.`start_date` AS `start_date`,
        `pro`.`end_date` AS `end_date`,
        `pro`.`location` AS `location`,
        `pro`.`email` AS `email`,
        `pro`.`phone` AS `professional_phone`,
        `pro`.`mobile_long` AS `mobile_long`,
        `pro`.`mobile_short` AS `mobile_short`,
        `pro`.`lab_phone_number` AS `lab_phone_number`,
        `pro`.`fax` AS `fax`,
        `toc`.`description` AS `type_of_contract`,
        `toc`.`is_irbs` AS `contract_is_irbs`,
        `pos`.`description` AS `position`,
        `pay`.`description` AS `payroll_institution`,
        `pay2`.`description` AS `payroll_institution_2`,
        `rp`.`description` AS `research_group`,
        `un`.`description` AS `professional_unit`,
        `ou`.`description` AS `organization_unit`
        
    from `personal` `pe`
        left join `country` `ecc` on `pe`.`end_of_contract_country`=`ecc`.`countrycode`
        left join `country` `c` on `pe`.`country`=`c`.`countrycode`
        left join `country` `bc` on `pe`.`birth_country`=`bc`.`countrycode`
        left join `nationality` `n` on `pe`.`nationality`=`n`.`nationalitycode`
        left join `nationality` `n2` on `pe`.`nationality_2`=`n2`.`nationalitycode`
        left join `payment` `pa` on `pe`.`payments`=`pa`.`paymentcode`
        left join `gender` `ge` on `pe`.`gender`=`ge`.`gendercode`
        left join `marital_status` `ms` on `pe`.`marital_status`=`ms`.`marital_statuscode`
        left join `bank` `bk` on `pe`.`bank`=`bk`.`bankcode`
        left join `working_hours` `wh` on `pe`.`working_hours`=`wh`.`working_hourscode`
        left join `category` `ca` on `pe`.`category`=`ca`.`categorycode`
        left join `personalstate` `ps` on `pe`.`state`=`ps`.`personalstatecode`
        left join `professional` `pro` on `pe`.`personalcode`=`pro`.`professional_personal` and `pro`.`current`=1
        left join `type_of_contract` `toc` on `pro`.`type_of_contract`=`toc`.`type_of_contractcode`
        left join `position` `pos` on `pro`.`position`=`pos`.`positioncode`
        left join `payroll_institution` `pay` on `pro`.`payroll_institution`=`pay`.`payroll_institutioncode`
        left join `payroll_institution` `pay2` on `pro`.`payroll_institution_2`=`pay2`.`payroll_institutioncode`
        left join `research_group` `rp` on `pro`.`research_group`=`rp`.`research_groupcode`
        left join `unit` `un` on `pro`.`professional_unit`=`un`.`unitcode`
        left join `organization_unit` `ou` on `un`.`organization_unit`=`ou`.`organization_unitcode`
        
    where (`pe`.`deleted` = 0);

-- Movido aquí el 25-11-2008 porque la definición de personal.language se ejecute antes.
--
-- Definition of view `view_notirb_active_personal`
--
DROP VIEW IF EXISTS `view_notirb_active_personal`;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_notirb_active_personal` AS select distinct 
`p`.*
from `personal` `p` where ((
`p`.`deleted` = 0) and (
`p`.`state` = 5) and (not(
`p`.`personalcode` in (select `pe`.`personalcode` AS `personalcode` from ((`personal` `pe` join `professional` `pr`) join `type_of_contract` `t`) where ((`pe`.`personalcode` = `pr`.`professional_personal`) and (`pr`.`current` = 1) and (`pr`.`deleted` = 0) and (`pr`.`type_of_contract` = `t`.`type_of_contractcode`) and (`t`.`is_irbs` = 1) and (`t`.`deleted` = 0))))) and (not(
`p`.`personalcode` in (select `pe`.`personalcode` AS `personalcode` from ((`personal` `pe` join `grant_concession` `g`) join `table_grant` `tg`) where ((`pe`.`personalcode` = `g`.`grant_concession_personal`) and (`g`.`current` = 1) and (`g`.`deleted` = 0) and (`g`.`table_grant` = `tg`.`grantcode`) and (`tg`.`is_irbs` = 1) and (`tg`.`deleted` = 0))))));

-- añadido el 25-11-2008
ALTER TABLE `auditmessage` DROP FOREIGN KEY `FK8A94FCCA0174429`;

-- añadido el 25-11-2008 in situ

update personal set end_of_contract_country=null where end_of_contract_country='';

ALTER TABLE `personal` ADD CONSTRAINT `FK_personal_12` FOREIGN KEY `FK_personal_12` (`end_of_contract_country`)
    REFERENCES `country` (`countrycode`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE `type_of_education` ADD COLUMN `order` INTEGER UNSIGNED AFTER `description`;

ALTER TABLE `professional` ADD COLUMN `building` VARCHAR(255) AFTER `type_of_contract`;
