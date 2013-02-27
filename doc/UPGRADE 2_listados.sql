DROP VIEW IF EXISTS `view_grant_info`;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_grant_info` AS
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
        `ou`.`description` AS `organization_unit`,
        `gc`.`start_date` as `grant_start_date`,
		`gc`.`end_date` as `grant_end_date`,
		`gc`.`call_code`,
		`gc`.`thesis_director`,
		`gc`.`name_master_or_programme`,
		`gc`.`university_enrolled`,
		`gc`.`start_date_msc_or_phd`,
		`tg`.`description` as `grant`,
		`typ`.`description` as `type_of_grant`

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
        left join `professional` `pro` on `pe`.`personalcode`=`pro`.`professional_personal` and `pro`.`current`=1 and `pro`.`deleted`=0
        left join `type_of_contract` `toc` on `pro`.`type_of_contract`=`toc`.`type_of_contractcode`
        inner join `position` `pos` on `pro`.`position`=`pos`.`positioncode` and pos.positioncode in ('00006', '00007')
        left join `payroll_institution` `pay` on `pro`.`payroll_institution`=`pay`.`payroll_institutioncode`
        left join `payroll_institution` `pay2` on `pro`.`payroll_institution_2`=`pay2`.`payroll_institutioncode`
        left join `research_group` `rp` on `pro`.`research_group`=`rp`.`research_groupcode`
        left join `unit` `un` on `pro`.`professional_unit`=`un`.`unitcode`
        left join `organization_unit` `ou` on `un`.`organization_unit`=`ou`.`organization_unitcode`
        left join `grant_concession` `gc` on `gc`.`grant_concession_personal` = `pe`.`personalcode` and `gc`.`current`=1 and `gc`.`deleted`=0
		left join `table_grant` `tg` on `tg`.`grantcode` = `gc`.`table_grant`
		left join `type_of_grant` `typ` on `typ`.`type_of_grantcode` = `tg`.`type_of_grant`

    where (`pe`.`deleted` = 0);





DROP VIEW IF EXISTS `view_education_info`;
CREATE VIEW `view_education_info` AS
SELECT personalcode, dni,  name, surname1, surname2, n.description as nationality, n2.description as nationality_2, ge.description as gender,
	`pro`.`email` AS `email`,`pro`.`phone` AS `professional_phone`, rp.description as research_group, pay.description as payroll_institution, pay2.description as payroll_institution_2,
	st.description as state,
	edu.start_date, edu.end_date, edu.graduation_date, edu.title, edu.speciality, edu.center, co.description as education_country,
	ted.description

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
where ted.order =
( select max(`order`)
  from
  education inner join type_of_education on `type`=type_of_educationcode
  where education_personal=pe.personalcode
  group by education_personal
)

order by surname1, surname2, name;





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
        `ou`.`description` AS `organization_unit`,
        `gc`.`start_date` as `grant_start_date`,
		`gc`.`end_date` as `grant_end_date`,
		`gc`.`call_code`,
		`gc`.`thesis_director`,
		`gc`.`name_master_or_programme`,
		`gc`.`university_enrolled`,
		`gc`.`start_date_msc_or_phd`,
		`tg`.`description` as `grant`,
		`typ`.`description` as `type_of_grant`,
		`com`.`start_date` as `compensation_start_date`,
		`com`.`end_date` as `compensation_end_date`,
		`com`.`amount`,
		`com`.`description` as `compensation_description`,
		`tco`.`description` as `type_of_compensation`
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
        left join `professional` `pro` on `pe`.`personalcode`=`pro`.`professional_personal` and `pro`.`current`=1 and `pro`.`deleted`=0
        left join `type_of_contract` `toc` on `pro`.`type_of_contract`=`toc`.`type_of_contractcode`
        left join `position` `pos` on `pro`.`position`=`pos`.`positioncode`
        left join `payroll_institution` `pay` on `pro`.`payroll_institution`=`pay`.`payroll_institutioncode`
        left join `payroll_institution` `pay2` on `pro`.`payroll_institution_2`=`pay2`.`payroll_institutioncode`
        left join `research_group` `rp` on `pro`.`research_group`=`rp`.`research_groupcode`
        left join `unit` `un` on `pro`.`professional_unit`=`un`.`unitcode`
        left join `organization_unit` `ou` on `un`.`organization_unit`=`ou`.`organization_unitcode`
        left join `grant_concession` `gc` on `gc`.`grant_concession_personal` = `pe`.`personalcode` and `gc`.`current`=1 and `gc`.`deleted`=0
		left join `table_grant` `tg` on `tg`.`grantcode` = `gc`.`table_grant`
		left join `type_of_grant` `typ` on `typ`.`type_of_grantcode` = `tg`.`type_of_grant`
		left join `compensation` `com` on `pe`.`personalcode` = `com`.`compensation_personal` and `com`.`current`=1 and `com`.`deleted`=0
		left join `type_of_compensation` `tco` on `com`.`type_of_compensation` = `tco`.`type_of_compensationcode`
    where (`pe`.`deleted` = 0);

DROP VIEW IF EXISTS `view_grant_professional`;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_grant_professional` AS
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
        `ou`.`description` AS `organization_unit`,
        `gc`.`start_date` as `grant_start_date`,
		`gc`.`end_date` as `grant_end_date`,
		`gc`.`call_code`,
		`gc`.`thesis_director`,
		`gc`.`name_master_or_programme`,
		`gc`.`university_enrolled`,
		`gc`.`start_date_msc_or_phd`,
		`tg`.`description` as `grant`,
		`typ`.`description` as `type_of_grant`
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
        left join `professional` `pro` on `pe`.`personalcode`=`pro`.`professional_personal` and `pro`.`current`=1 and `pro`.`deleted`=0
        left join `type_of_contract` `toc` on `pro`.`type_of_contract`=`toc`.`type_of_contractcode`
        left join `position` `pos` on `pro`.`position`=`pos`.`positioncode`
        left join `payroll_institution` `pay` on `pro`.`payroll_institution`=`pay`.`payroll_institutioncode`
        left join `payroll_institution` `pay2` on `pro`.`payroll_institution_2`=`pay2`.`payroll_institutioncode`
        left join `research_group` `rp` on `pro`.`research_group`=`rp`.`research_groupcode`
        left join `unit` `un` on `pro`.`professional_unit`=`un`.`unitcode`
        left join `organization_unit` `ou` on `un`.`organization_unit`=`ou`.`organization_unitcode`
        left join `grant_concession` `gc` on `gc`.`grant_concession_personal` = `pe`.`personalcode` and `gc`.`current`=1 and `gc`.`deleted`=0
		left join `table_grant` `tg` on `tg`.`grantcode` = `gc`.`table_grant`
		left join `type_of_grant` `typ` on `typ`.`type_of_grantcode` = `tg`.`type_of_grant`
		
    where (`pe`.`deleted` = 0);
    
insert into views values ('8',1,0,'view_grant_professional');


insert into `views_permissions` values('irbpeople_grant','8');

delete from views where viewscode in ('1','2','3','6');

DROP VIEW IF EXISTS `view_compensation`;
DROP VIEW IF EXISTS `view_compensation_info`;
DROP VIEW IF EXISTS `view_little_personal`;
DROP VIEW IF EXISTS `view_all_personal`;
