DROP TABLE `audit_log`;

CREATE TABLE `audit_log` (
  `audit_logcode` VARCHAR(45) NOT NULL,
  `date` DATETIME NOT NULL,
  `user` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `remotehost` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`audit_logcode`)
)
ENGINE = InnoDB;

delete from beancodes where bean='audit_log';
insert into beancodes values ('audit_log','code','null',0,'null','{n}','0000001');


ALTER TABLE `reportcustomlist` ADD COLUMN `periodic` BOOLEAN NOT NULL AFTER `comments`;

ALTER TABLE `grant_concession` ADD COLUMN `start_date_msc_or_phd` DATETIME AFTER `current`,
 ADD COLUMN `thesis_director` VARCHAR(100) AFTER `start_date_msc_or_phd`,
 ADD COLUMN `name_master_or_programme` VARCHAR(100) AFTER `thesis_director`,
 ADD COLUMN `university_enrolled` VARCHAR(100) AFTER `name_master_or_programme`;

DROP TABLE IF EXISTS `views_permissions`;
CREATE TABLE  `views_permissions` (
  `entitycode` varchar(100) NOT NULL,
  `view_name` varchar(31) NOT NULL DEFAULT '',
  PRIMARY KEY (`entitycode`,`view_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



update `research_group` set group=null where group=''
update `unit` set group=null where group=''

ALTER TABLE `research_group` ADD UNIQUE INDEX `unique_group`(`group`);
ALTER TABLE `unit` ADD UNIQUE INDEX `unique_group`(`group`);

DROP TABLE IF EXISTS `personal_comment`;
CREATE TABLE  `personal_comment` (
  `personal_commentcode` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `input_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `text` varchar(1000) NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `personalcode` varchar(255) NOT NULL,
  `tabindex` int(10) unsigned NOT NULL,
  PRIMARY KEY (`personal_commentcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into beancodes values ('personal_comment','code','null',0,'null','{n}','0000000001');

CREATE VIEW `view_grant_info` AS
	SELECT personalcode, dni,  name, surname1, surname2, n.description as nationality, n2.description as nationality_2, ge.description as gender,
	`pro`.`email` AS `email`,`pro`.`phone` AS `professional_phone`, pos.description as position, rp.description as research_group, pay.description as payroll_institution, pay2.description as payroll_institution_2,
	gc.start_date as grant_start_date, gc.end_date as grant_end_date, gc.call_code, gc.thesis_director, gc.name_master_or_programme, gc.university_enrolled, gc.start_date_msc_or_phd,
	tg.description as `grant`, typ.description as type_of_grant, st.description as state
	
	FROM personal pe
	left join `personalstate` st on pe.state = st.personalstatecode
	left join `nationality` `n` on `pe`.`nationality` = `n`.`nationalitycode`
	left join `nationality` `n2` on `pe`.`nationality_2` = `n2`.`nationalitycode`
	left join `gender` `ge` on `pe`.`gender` = `ge`.`gendercode`
	left join `professional` `pro` on `pe`.`personalcode` = `pro`.`professional_personal` and `pro`.`current` = 1
	left join `research_group` `rp` on `pro`.`research_group` = `rp`.`research_groupcode`
	left join `payroll_institution` `pay` on `pro`.`payroll_institution` = `pay`.`payroll_institutioncode`
	left join `payroll_institution` `pay2` on `pro`.`payroll_institution_2` = `pay2`.`payroll_institutioncode`
	inner join grant_concession gc on gc.grant_concession_personal = pe.personalcode and gc.current=1
	inner join table_grant tg on tg.grantcode = gc.table_grant
	inner join type_of_grant typ on typ.type_of_grantcode = tg.type_of_grant
	inner join `position` `pos` on `pro`.`position` = `pos`.`positioncode` and pos.positioncode in ('00006', '00007')
	
	order by surname1, surname2, name;

insert into views values ('5',1,0,'view_grant_info');

insert into `views_permissions` values('irbpeople_ro','all');
insert into `views_permissions` values('irbpeople_rw','all');
insert into `views_permissions` values('irbpeople_grant','5');

CREATE VIEW `view_compensation_info` AS
  SELECT personalcode, dni,  name, surname1, surname2, n.description as nationality, n2.description as nationality_2, ge.description as gender,
	`pro`.`email` AS `email`,`pro`.`phone` AS `professional_phone`,  rp.description as research_group, pay.description as payroll_institution, pay2.description as payroll_institution_2,
	st.description as state,
	com.start_date, com.end_date, com.amount, com.description, tco.description as type_of_compensation

	FROM personal pe
	left join `personalstate` st on pe.state = st.personalstatecode
	left join `nationality` `n` on `pe`.`nationality` = `n`.`nationalitycode`
	left join `nationality` `n2` on `pe`.`nationality_2` = `n2`.`nationalitycode`
	left join `gender` `ge` on `pe`.`gender` = `ge`.`gendercode`
	left join `professional` `pro` on `pe`.`personalcode` = `pro`.`professional_personal` and `pro`.`current` = 1 and `pro`.`deleted`=0
	left join `research_group` `rp` on `pro`.`research_group` = `rp`.`research_groupcode`
	left join `payroll_institution` `pay` on `pro`.`payroll_institution` = `pay`.`payroll_institutioncode`
	left join `payroll_institution` `pay2` on `pro`.`payroll_institution_2` = `pay2`.`payroll_institutioncode`
	inner join `compensation` `com` on pe.personalcode = com.compensation_personal and com.current=1 and com.deleted=0
	inner join `type_of_compensation` `tco` on com.type_of_compensation = tco.type_of_compensationcode

	order by surname1, surname2, name;
	
insert into views values ('6',1,0,'view_compensation_info');

CREATE VIEW `view_education_info` AS
SELECT personalcode, dni,  name, surname1, surname2, n.description as nationality, n2.description as nationality_2, ge.description as gender,
	`pro`.`email` AS `email`,`pro`.`phone` AS `professional_phone`, rp.description as research_group, pay.description as payroll_institution, pay2.description as payroll_institution_2,
	st.description as state,
	edu.start_date, edu.end_date, edu.graduation_date, edu.title, edu.speciality, edu.center, co.description as education_country,
	ted.description, pos.description as position

	FROM personal pe
	left join `personalstate` st on pe.state = st.personalstatecode
	left join `nationality` `n` on `pe`.`nationality` = `n`.`nationalitycode`
	left join `nationality` `n2` on `pe`.`nationality_2` = `n2`.`nationalitycode`
	left join `gender` `ge` on `pe`.`gender` = `ge`.`gendercode`
	left join `professional` `pro` on `pe`.`personalcode` = `pro`.`professional_personal` and `pro`.`current` = 1 and `pro`.`deleted`=0
	left join `research_group` `rp` on `pro`.`research_group` = `rp`.`research_groupcode`
	left join `payroll_institution` `pay` on `pro`.`payroll_institution` = `pay`.`payroll_institutioncode`
	left join `payroll_institution` `pay2` on `pro`.`payroll_institution_2` = `pay2`.`payroll_institutioncode`
	inner join education edu on pe.personalcode = edu.education_personal and edu.deleted=0
	inner join type_of_education ted on ted.type_of_educationcode = edu.type
	inner join country co on edu.education_country = co.countrycode
	inner join `position` `pos` on `pro`.`position` = `pos`.`positioncode`
where ted.order =
( select max(`order`)
  from
  education inner join type_of_education on `type`=type_of_educationcode
  where education_personal=pe.personalcode
  group by education_personal
)

order by surname1, surname2, name;

insert into views values ('7',1,0,'view_education_info');

delete from irbholidayinfo where year<>2009;

ALTER TABLE irbholidayinfo ADD COLUMN pendingpreviousyearholidays INTEGER UNSIGNED AFTER `pendingaps`;
ALTER TABLE irbholidayinfo ADD COLUMN previousyearholidaysforyear INTEGER UNSIGNED AFTER `apsforyear`;

update irbholidayinfo set pendingpreviousyearholidays=0;

update irbholidayinfo set previousyearholidays=(select sum(h.previousyearholidays) from irbholiday h where h.personalcode = irbholidayinfo.personalcode);
update irbholidayinfo set previousyearholidays=0 where previousyearholidays is null;

update irbholidayinfo set previousyearholidaysforyear=previousyearholidays;

ALTER TABLE `irbdb_prod`.`location` ADD COLUMN `buildingcode` VARCHAR(255) AFTER `description`;
