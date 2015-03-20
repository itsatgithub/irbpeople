ALTER TABLE `personal` ADD COLUMN `access_scientific_publications`  tinyint(4) NULL DEFAULT 0 AFTER `second_affiliation`;


ALTER 
VIEW `view_active_personal` AS 
SELECT
	`personal`.`personalcode` AS `personalcode`,
	`personal`.`version` AS `version`,
	`personal`.`deleted` AS `deleted`,
	`personal`.`name` AS `name`,
	`personal`.`surname` AS `surname`,
	`personal`.`surname1` AS `surname1`,
	`personal`.`surname2` AS `surname2`,
	`personal`.`dni` AS `dni`,
	`personal`.`birth_date` AS `birth_date`,
	`personal`.`birth_city` AS `birth_city`,
	`personal`.`street` AS `street`,
	`personal`.`city` AS `city`,
	`personal`.`zip_code` AS `zip_code`,
	`personal`.`phone` AS `phone`,
	`personal`.`phone2` AS `phone2`,
	`personal`.`ice_phone` AS `ice_phone`,
	`personal`.`ss_number` AS `ss_number`,
	`personal`.`bank_account` AS `bank_account`,
	`personal`.`access_scientific_publications` AS `access_scientific_publications`,
	`personal`.`research_project` AS `research_project`,
	`personal`.`sponsoring_agency` AS `sponsoring_agency`,
	`personal`.`holding_institution` AS `holding_institution`,
	`personal`.`principal_investigator` AS `principal_investigator`,
	`personal`.`end_of_contract_reason` AS `end_of_contract_reason`,
	`personal`.`end_of_contract_address` AS `end_of_contract_address`,
	`personal`.`end_of_contract_city` AS `end_of_contract_city`,
	`personal`.`end_of_contract_zip_code` AS `end_of_contract_zip_code`,
	`personal`.`end_of_contract_phone` AS `end_of_contract_phone`,
	`personal`.`end_of_contract_email` AS `end_of_contract_email`,
	`personal`.`birth_country` AS `birth_country`,
	`personal`.`nationality` AS `nationality`,
	`personal`.`nationality_2` AS `nationality_2`,
	`personal`.`country` AS `country`,
	`personal`.`payments` AS `payments`,
	`personal`.`end_of_contract_country` AS `end_of_contract_country`,
	`personal`.`gender` AS `gender`,
	`personal`.`marital_status` AS `marital_status`,
	`personal`.`bank` AS `bank`,
	`personal`.`working_hours` AS `working_hours`,
	`personal`.`category` AS `category`,
	`personal`.`state` AS `state`,
	`personal`.`photo` AS `photo`,
	`personal`.`bic` AS `bic`,
	`personal`.`validationCode` AS `validationCode`,
	`personal`.`personal_email` AS `personal_email`,
	`personal`.`language` AS `language`,
	`personal`.`username` AS `username`,

IF (
	(
		(
			`personal`.`category` IS NOT NULL
		)
		OR (
			`personal`.`working_hours` IS NOT NULL
		)
	),
	1,
	0
) AS `isemployee`
FROM
	`personal`
WHERE
	(
		(`personal`.`deleted` = 0)
		AND (`personal`.`state` = 5)
	);


	
	

	
	
ALTER VIEW `view_grant_info` AS 
SELECT
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
	`pe`.`access_scientific_publications` AS `access_scientific_publications`,
	`pe`.`bic` AS `bic`,
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
	`rp2`.`description` AS `research_group_2`,
	`rp3`.`description` AS `research_group_3`,
	`rp4`.`description` AS `research_group_4`,
	`un`.`description` AS `professional_unit`,
	`un2`.`description` AS `professional_unit_2`,
	`un3`.`description` AS `professional_unit_3`,
	`un4`.`description` AS `professional_unit_4`,
	`ou`.`description` AS `organization_unit`,
	`gc`.`start_date` AS `grant_start_date`,
	`gc`.`end_date` AS `grant_end_date`,
	`gc`.`call_code` AS `call_code`,
	`ai`.`start_date` AS `academic_info_start_date`,
	`ai`.`end_date` AS `academic_info_end_date`,
	`ai`.`lab_rotation_date` AS `academic_info_lab_rotation_date`,
	`ai`.`thesis_defense_date` AS `academic_info_thesis_defense_date`,
	`ai`.`studies_name` AS `academic_info_studies_name`,
	`ai`.`thesis_director` AS `academic_info_thesis_director`,
	`ai`.`thesis_codirector` AS `academic_info_thesis_codirector`,
	`ai`.`university_enrolled` AS `academic_info_university_enrolled`,
	`ai`.`tac0` AS `academic_info_tac0`,
	`airg`.`description` AS `academic_info_lab_rotation_lab`,
	`airg2`.`description` AS `academic_info_lab_rotation_lab_2`,
	`ts`.`description` AS `academic_info_type_study`,
	`tg`.`description` AS `grant`,
	`typ`.`description` AS `type_of_grant`
FROM
	(
		(
			(
				(
					(
						(
							(
								(
									(
										(
											(
												(
													(
														(
															(
																(
																	(
																		(
																			(
																				(
																					(
																						(
																							(
																								(
																									(
																										(
																											(
																												(
																													(
																														(
																															(
																																(
																																	(
																																		`personal` `pe`
																																		LEFT JOIN `country` `ecc` ON (
																																			(
																																				`pe`.`end_of_contract_country` = `ecc`.`countrycode`
																																			)
																																		)
																																	)
																																	LEFT JOIN `country` `c` ON (
																																		(
																																			`pe`.`country` = `c`.`countrycode`
																																		)
																																	)
																																)
																																LEFT JOIN `country` `bc` ON (
																																	(
																																		`pe`.`birth_country` = `bc`.`countrycode`
																																	)
																																)
																															)
																															LEFT JOIN `nationality` `n` ON (
																																(
																																	`pe`.`nationality` = `n`.`nationalitycode`
																																)
																															)
																														)
																														LEFT JOIN `nationality` `n2` ON (
																															(
																																`pe`.`nationality_2` = `n2`.`nationalitycode`
																															)
																														)
																													)
																													LEFT JOIN `payment` `pa` ON (
																														(
																															`pe`.`payments` = `pa`.`paymentcode`
																														)
																													)
																												)
																												LEFT JOIN `gender` `ge` ON (
																													(
																														`pe`.`gender` = `ge`.`gendercode`
																													)
																												)
																											)
																											LEFT JOIN `marital_status` `ms` ON (
																												(
																													`pe`.`marital_status` = `ms`.`marital_statuscode`
																												)
																											)
																										)
																										LEFT JOIN `bank` `bk` ON (
																											(
																												`pe`.`bank` = `bk`.`bankcode`
																											)
																										)
																									)
																									LEFT JOIN `working_hours` `wh` ON (
																										(
																											`pe`.`working_hours` = `wh`.`working_hourscode`
																										)
																									)
																								)
																								LEFT JOIN `category` `ca` ON (
																									(
																										`pe`.`category` = `ca`.`categorycode`
																									)
																								)
																							)
																							LEFT JOIN `personalstate` `ps` ON (
																								(
																									`pe`.`state` = `ps`.`personalstatecode`
																								)
																							)
																						)
																						LEFT JOIN `professional` `pro` ON (
																							(
																								(
																									`pe`.`personalcode` = `pro`.`professional_personal`
																								)
																								AND (`pro`.`current` = 1)
																								AND (`pro`.`deleted` = 0)
																							)
																						)
																					)
																					LEFT JOIN `type_of_contract` `toc` ON (
																						(
																							`pro`.`type_of_contract` = `toc`.`type_of_contractcode`
																						)
																					)
																				)
																				JOIN `position` `pos` ON (
																					(
																						(
																							`pro`.`position` = `pos`.`positioncode`
																						)
																						AND (
																							`pos`.`positioncode` IN (
																								_latin1 '00006',
																								_latin1 '00007'
																							)
																						)
																					)
																				)
																			)
																			LEFT JOIN `payroll_institution` `pay` ON (
																				(
																					`pro`.`payroll_institution` = `pay`.`payroll_institutioncode`
																				)
																			)
																		)
																		LEFT JOIN `payroll_institution` `pay2` ON (
																			(
																				`pro`.`payroll_institution_2` = `pay2`.`payroll_institutioncode`
																			)
																		)
																	)
																	LEFT JOIN `research_group` `rp` ON (
																		(
																			`pro`.`research_group` = `rp`.`research_groupcode`
																		)
																	)
																)
																LEFT JOIN `research_group` `rp2` ON (
																	(
																		`pro`.`research_group_2` = `rp2`.`research_groupcode`
																	)
																)
															)
															LEFT JOIN `research_group` `rp3` ON (
																(
																	`pro`.`research_group_3` = `rp3`.`research_groupcode`
																)
															)
														)
														LEFT JOIN `research_group` `rp4` ON (
															(
																`pro`.`research_group_4` = `rp4`.`research_groupcode`
															)
														)
													)
													LEFT JOIN `unit` `un` ON (
														(
															`pro`.`professional_unit` = `un`.`unitcode`
														)
													)
												)
												LEFT JOIN `unit` `un2` ON (
													(
														`pro`.`professional_unit_2` = `un2`.`unitcode`
													)
												)
											)
											LEFT JOIN `unit` `un3` ON (
												(
													`pro`.`professional_unit_3` = `un3`.`unitcode`
												)
											)
										)
										LEFT JOIN `unit` `un4` ON (
											(
												`pro`.`professional_unit_4` = `un4`.`unitcode`
											)
										)
									)
									LEFT JOIN `organization_unit` `ou` ON (
										(
											`un`.`organization_unit` = `ou`.`organization_unitcode`
										)
									)
								)
								LEFT JOIN `grant_concession` `gc` ON (
									(
										(
											`gc`.`grant_concession_personal` = `pe`.`personalcode`
										)
										AND (`gc`.`current` = 1)
										AND (`gc`.`deleted` = 0)
									)
								)
							)
							LEFT JOIN `table_grant` `tg` ON (
								(
									`tg`.`grantcode` = `gc`.`table_grant`
								)
							)
						)
						LEFT JOIN `type_of_grant` `typ` ON (
							(
								`typ`.`type_of_grantcode` = `tg`.`type_of_grant`
							)
						)
					)
					LEFT JOIN `academic_info` `ai` ON (
						(
							(
								`ai`.`academic_info_personal` = `pe`.`personalcode`
							)
							AND (`ai`.`current` = 1)
							AND (`ai`.`deleted` = 0)
						)
					)
				)
				LEFT JOIN `type_of_study` `ts` ON (
					(
						`ts`.`type_of_studycode` = `ai`.`type_of_study`
					)
				)
			)
			LEFT JOIN `research_group` `airg` ON (
				(
					`ai`.`lab_rotation_lab` = `airg`.`research_groupcode`
				)
			)
		)
		LEFT JOIN `research_group` `airg2` ON (
			(
				`ai`.`lab_rotation_lab` = `airg2`.`research_groupcode`
			)
		)
	)
WHERE
	(`pe`.`deleted` = 0);
	
	
	
	
	
	
	
ALTER 
VIEW `view_grant_professional` AS 
SELECT
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
	`pe`.`access_scientific_publications` AS `access_scientific_publications`,
	`pe`.`bic` AS `bic`,
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
	`rp2`.`description` AS `research_group_2`,
	`rp3`.`description` AS `research_group_3`,
	`rp4`.`description` AS `research_group_4`,
	`un`.`description` AS `professional_unit`,
	`un2`.`description` AS `professional_unit_2`,
	`un3`.`description` AS `professional_unit_3`,
	`un4`.`description` AS `professional_unit_4`,
	`ou`.`description` AS `organization_unit`,
	`gc`.`start_date` AS `grant_start_date`,
	`gc`.`end_date` AS `grant_end_date`,
	`gc`.`call_code` AS `call_code`,
	`tg`.`description` AS `grant`,
	`typ`.`description` AS `type_of_grant`,
	`ai`.`start_date` AS `academic_info_start_date`,
	`ai`.`end_date` AS `academic_info_end_date`,
	`ai`.`lab_rotation_date` AS `academic_info_lab_rotation_date`,
	`ai`.`thesis_defense_date` AS `academic_info_thesis_defense_date`,
	`ai`.`studies_name` AS `academic_info_studies_name`,
	`ai`.`thesis_director` AS `academic_info_thesis_director`,
	`ai`.`thesis_codirector` AS `academic_info_thesis_codirector`,
	`ai`.`university_enrolled` AS `academic_info_university_enrolled`,
	`ai`.`tac0` AS `academic_info_tac0`,
	`airg`.`description` AS `academic_info_lab_rotation_lab`,
	`airg2`.`description` AS `academic_info_lab_rotation_lab_2`,
	`ts`.`description` AS `academic_info_type_study`
FROM
	(
		(
			(
				(
					(
						(
							(
								(
									(
										(
											(
												(
													(
														(
															(
																(
																	(
																		(
																			(
																				(
																					(
																						(
																							(
																								(
																									(
																										(
																											(
																												(
																													(
																														(
																															(
																																(
																																	(
																																		`personal` `pe`
																																		LEFT JOIN `country` `ecc` ON (
																																			(
																																				`pe`.`end_of_contract_country` = `ecc`.`countrycode`
																																			)
																																		)
																																	)
																																	LEFT JOIN `country` `c` ON (
																																		(
																																			`pe`.`country` = `c`.`countrycode`
																																		)
																																	)
																																)
																																LEFT JOIN `country` `bc` ON (
																																	(
																																		`pe`.`birth_country` = `bc`.`countrycode`
																																	)
																																)
																															)
																															LEFT JOIN `nationality` `n` ON (
																																(
																																	`pe`.`nationality` = `n`.`nationalitycode`
																																)
																															)
																														)
																														LEFT JOIN `nationality` `n2` ON (
																															(
																																`pe`.`nationality_2` = `n2`.`nationalitycode`
																															)
																														)
																													)
																													LEFT JOIN `payment` `pa` ON (
																														(
																															`pe`.`payments` = `pa`.`paymentcode`
																														)
																													)
																												)
																												LEFT JOIN `gender` `ge` ON (
																													(
																														`pe`.`gender` = `ge`.`gendercode`
																													)
																												)
																											)
																											LEFT JOIN `marital_status` `ms` ON (
																												(
																													`pe`.`marital_status` = `ms`.`marital_statuscode`
																												)
																											)
																										)
																										LEFT JOIN `bank` `bk` ON (
																											(
																												`pe`.`bank` = `bk`.`bankcode`
																											)
																										)
																									)
																									LEFT JOIN `working_hours` `wh` ON (
																										(
																											`pe`.`working_hours` = `wh`.`working_hourscode`
																										)
																									)
																								)
																								LEFT JOIN `category` `ca` ON (
																									(
																										`pe`.`category` = `ca`.`categorycode`
																									)
																								)
																							)
																							LEFT JOIN `personalstate` `ps` ON (
																								(
																									`pe`.`state` = `ps`.`personalstatecode`
																								)
																							)
																						)
																						LEFT JOIN `professional` `pro` ON (
																							(
																								(
																									`pe`.`personalcode` = `pro`.`professional_personal`
																								)
																								AND (`pro`.`current` = 1)
																								AND (`pro`.`deleted` = 0)
																							)
																						)
																					)
																					LEFT JOIN `type_of_contract` `toc` ON (
																						(
																							`pro`.`type_of_contract` = `toc`.`type_of_contractcode`
																						)
																					)
																				)
																				LEFT JOIN `position` `pos` ON (
																					(
																						`pro`.`position` = `pos`.`positioncode`
																					)
																				)
																			)
																			LEFT JOIN `payroll_institution` `pay` ON (
																				(
																					`pro`.`payroll_institution` = `pay`.`payroll_institutioncode`
																				)
																			)
																		)
																		LEFT JOIN `payroll_institution` `pay2` ON (
																			(
																				`pro`.`payroll_institution_2` = `pay2`.`payroll_institutioncode`
																			)
																		)
																	)
																	LEFT JOIN `research_group` `rp` ON (
																		(
																			`pro`.`research_group` = `rp`.`research_groupcode`
																		)
																	)
																)
																LEFT JOIN `research_group` `rp2` ON (
																	(
																		`pro`.`research_group_2` = `rp2`.`research_groupcode`
																	)
																)
															)
															LEFT JOIN `research_group` `rp3` ON (
																(
																	`pro`.`research_group_3` = `rp3`.`research_groupcode`
																)
															)
														)
														LEFT JOIN `research_group` `rp4` ON (
															(
																`pro`.`research_group_4` = `rp4`.`research_groupcode`
															)
														)
													)
													LEFT JOIN `unit` `un` ON (
														(
															`pro`.`professional_unit` = `un`.`unitcode`
														)
													)
												)
												LEFT JOIN `unit` `un2` ON (
													(
														`pro`.`professional_unit_2` = `un2`.`unitcode`
													)
												)
											)
											LEFT JOIN `unit` `un3` ON (
												(
													`pro`.`professional_unit_3` = `un3`.`unitcode`
												)
											)
										)
										LEFT JOIN `unit` `un4` ON (
											(
												`pro`.`professional_unit_4` = `un4`.`unitcode`
											)
										)
									)
									LEFT JOIN `organization_unit` `ou` ON (
										(
											`un`.`organization_unit` = `ou`.`organization_unitcode`
										)
									)
								)
								LEFT JOIN `grant_concession` `gc` ON (
									(
										(
											`gc`.`grant_concession_personal` = `pe`.`personalcode`
										)
										AND (`gc`.`current` = 1)
										AND (`gc`.`deleted` = 0)
									)
								)
							)
							LEFT JOIN `table_grant` `tg` ON (
								(
									`tg`.`grantcode` = `gc`.`table_grant`
								)
							)
						)
						LEFT JOIN `type_of_grant` `typ` ON (
							(
								`typ`.`type_of_grantcode` = `tg`.`type_of_grant`
							)
						)
					)
					LEFT JOIN `academic_info` `ai` ON (
						(
							(
								`ai`.`academic_info_personal` = `pe`.`personalcode`
							)
							AND (`ai`.`current` = 1)
							AND (`ai`.`deleted` = 0)
						)
					)
				)
				LEFT JOIN `type_of_study` `ts` ON (
					(
						`ts`.`type_of_studycode` = `ai`.`type_of_study`
					)
				)
			)
			LEFT JOIN `research_group` `airg` ON (
				(
					`ai`.`lab_rotation_lab` = `airg`.`research_groupcode`
				)
			)
		)
		LEFT JOIN `research_group` `airg2` ON (
			(
				`ai`.`lab_rotation_lab` = `airg2`.`research_groupcode`
			)
		)
	)
WHERE
	(`pe`.`deleted` = 0); 

	
	
	
ALTER  VIEW `view_notirb_active_personal` AS 
SELECT DISTINCT
	`p`.`personalcode` AS `personalcode`,
	`p`.`version` AS `version`,
	`p`.`deleted` AS `deleted`,
	`p`.`name` AS `name`,
	`p`.`surname` AS `surname`,
	`p`.`surname1` AS `surname1`,
	`p`.`surname2` AS `surname2`,
	`p`.`dni` AS `dni`,
	`p`.`birth_date` AS `birth_date`,
	`p`.`birth_city` AS `birth_city`,
	`p`.`street` AS `street`,
	`p`.`city` AS `city`,
	`p`.`zip_code` AS `zip_code`,
	`p`.`phone` AS `phone`,
	`p`.`phone2` AS `phone2`,
	`p`.`ice_phone` AS `ice_phone`,
	`p`.`ss_number` AS `ss_number`,
	`p`.`bank_account` AS `bank_account`,
  `p`.`access_scientific_publications` AS `access_scientific_publications`,
	`p`.`bic` AS `bic`,
	`p`.`research_project` AS `research_project`,
	`p`.`sponsoring_agency` AS `sponsoring_agency`,
	`p`.`holding_institution` AS `holding_institution`,
	`p`.`principal_investigator` AS `principal_investigator`,
	`p`.`end_of_contract_reason` AS `end_of_contract_reason`,
	`p`.`end_of_contract_address` AS `end_of_contract_address`,
	`p`.`end_of_contract_city` AS `end_of_contract_city`,
	`p`.`end_of_contract_zip_code` AS `end_of_contract_zip_code`,
	`p`.`end_of_contract_phone` AS `end_of_contract_phone`,
	`p`.`end_of_contract_email` AS `end_of_contract_email`,
	`p`.`birth_country` AS `birth_country`,
	`p`.`nationality` AS `nationality`,
	`p`.`nationality_2` AS `nationality_2`,
	`p`.`country` AS `country`,
	`p`.`payments` AS `payments`,
	`p`.`end_of_contract_country` AS `end_of_contract_country`,
	`p`.`gender` AS `gender`,
	`p`.`marital_status` AS `marital_status`,
	`p`.`bank` AS `bank`,
	`p`.`working_hours` AS `working_hours`,
	`p`.`category` AS `category`,
	`p`.`state` AS `state`,
	`p`.`photo` AS `photo`,
	`p`.`validationCode` AS `validationCode`,
	`p`.`personal_email` AS `personal_email`,
	`p`.`language` AS `language`,
	`p`.`username` AS `username`
FROM
	`personal` `p`
WHERE
	(
		(`p`.`deleted` = 0)
		AND (`p`.`state` = 5)
		AND (
			NOT (
				`p`.`personalcode` IN (
					SELECT
						`pe`.`personalcode` AS `personalcode`
					FROM
						(
							(
								`personal` `pe`
								JOIN `professional` `pr`
							)
							JOIN `type_of_contract` `t`
						)
					WHERE
						(
							(
								`pe`.`personalcode` = `pr`.`professional_personal`
							)
							AND (`pr`.`current` = 1)
							AND (`pr`.`deleted` = 0)
							AND (
								`pr`.`type_of_contract` = `t`.`type_of_contractcode`
							)
							AND (`t`.`is_irbs` = 1)
							AND (`t`.`deleted` = 0)
						)
				)
			)
		)
		AND (
			NOT (
				`p`.`personalcode` IN (
					SELECT
						`pe`.`personalcode` AS `personalcode`
					FROM
						(
							(
								`personal` `pe`
								JOIN `grant_concession` `g`
							)
							JOIN `table_grant` `tg`
						)
					WHERE
						(
							(
								`pe`.`personalcode` = `g`.`grant_concession_personal`
							)
							AND (`g`.`current` = 1)
							AND (`g`.`deleted` = 0)
							AND (
								`g`.`table_grant` = `tg`.`grantcode`
							)
							AND (`tg`.`is_irbs` = 1)
							AND (`tg`.`deleted` = 0)
						)
				)
			)
		)
	); 


	
	
ALTER 
VIEW `view_personal_professional` AS 
SELECT
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
  `pe`.`access_scientific_publications` AS `access_scientific_publications`,
	`pe`.`bic` AS `bic`,
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
	`pe`.`second_affiliation` AS `second_affiliation`,
	`ps`.`description` AS `state`,
	`pe`.`personal_email` AS `personal_email`,
	`pe`.`language` AS `language`,
	(
		SELECT
			min(`pro2`.`start_date`) AS `min(``pro2``.``start_date``)`
		FROM
			`professional` `pro2`
		WHERE
			(
				(
					`pro2`.`professional_personal` = `pe`.`personalcode`
				)
				AND (`pro2`.`deleted` = 0)
			)
	) AS `first_start_date`,
	`pro`.`start_date` AS `professional_start_date`,
	`pro`.`end_date` AS `professional_end_date`,
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
	`rp2`.`description` AS `research_group_2`,
	`rp3`.`description` AS `research_group_3`,
	`rp4`.`description` AS `research_group_4`,
	`un`.`description` AS `professional_unit`,
	`un2`.`description` AS `professional_unit_2`,
	`un3`.`description` AS `professional_unit_3`,
	`un4`.`description` AS `professional_unit_4`,
	`ou`.`description` AS `organization_unit`,
	`gc`.`start_date` AS `grant_start_date`,
	`gc`.`end_date` AS `grant_end_date`,
	`gc`.`call_code` AS `call_code`,
	`tg`.`description` AS `grant`,
	`typ`.`description` AS `type_of_grant`,
	`com`.`start_date` AS `compensation_start_date`,
	`com`.`end_date` AS `compensation_end_date`,
	`com`.`amount` AS `amount`,
	`com`.`description` AS `compensation_description`,
	`tco`.`description` AS `type_of_compensation`,
	`ai`.`start_date` AS `academic_info_start_date`,
	`ai`.`end_date` AS `academic_info_end_date`,
	`ai`.`lab_rotation_date` AS `academic_info_lab_rotation_date`,
	`ai`.`thesis_defense_date` AS `academic_info_thesis_defense_date`,
	`ai`.`studies_name` AS `academic_info_studies_name`,
	`ai`.`thesis_director` AS `academic_info_thesis_director`,
	`ai`.`thesis_codirector` AS `academic_info_thesis_codirector`,
	`ai`.`university_enrolled` AS `academic_info_university_enrolled`,
	`ai`.`tac0` AS `academic_info_tac0`,
	`airg`.`description` AS `academic_info_lab_rotation_lab`,
	`airg2`.`description` AS `academic_info_lab_rotation_lab_2`,
	`ts`.`description` AS `academic_info_type_study`,
	(
		SELECT
			`typedu`.`description` AS `description`
		FROM
			(
				`education` `edu`
				JOIN `type_of_education` `typedu` ON (
					(
						(
							`edu`.`type` = `typedu`.`type_of_educationcode`
						)
						AND (`edu`.`deleted` = 0)
					)
				)
			)
		WHERE
			(
				(
					`edu`.`education_personal` = `pe`.`personalcode`
				)
				AND (
					`typedu`.`order` = (
						SELECT
							max(`typedu2`.`order`) AS `max(``typedu2``.``order``)`
						FROM
							(
								`education` `edu2`
								JOIN `type_of_education` `typedu2` ON (
									(
										(
											`edu2`.`type` = `typedu2`.`type_of_educationcode`
										)
										AND (`edu2`.`deleted` = 0)
									)
								)
							)
						WHERE
							(
								`edu2`.`education_personal` = `pe`.`personalcode`
							)
					)
				)
			)
		LIMIT 1
	) AS `max_education`
FROM
	(
		(
			(
				(
					(
						(
							(
								(
									(
										(
											(
												(
													(
														(
															(
																(
																	(
																		(
																			(
																				(
																					(
																						(
																							(
																								(
																									(
																										(
																											(
																												(
																													(
																														(
																															(
																																(
																																	(
																																		(
																																			(
																																				`personal` `pe`
																																				LEFT JOIN `country` `ecc` ON (
																																					(
																																						`pe`.`end_of_contract_country` = `ecc`.`countrycode`
																																					)
																																				)
																																			)
																																			LEFT JOIN `country` `c` ON (
																																				(
																																					`pe`.`country` = `c`.`countrycode`
																																				)
																																			)
																																		)
																																		LEFT JOIN `country` `bc` ON (
																																			(
																																				`pe`.`birth_country` = `bc`.`countrycode`
																																			)
																																		)
																																	)
																																	LEFT JOIN `nationality` `n` ON (
																																		(
																																			`pe`.`nationality` = `n`.`nationalitycode`
																																		)
																																	)
																																)
																																LEFT JOIN `nationality` `n2` ON (
																																	(
																																		`pe`.`nationality_2` = `n2`.`nationalitycode`
																																	)
																																)
																															)
																															LEFT JOIN `payment` `pa` ON (
																																(
																																	`pe`.`payments` = `pa`.`paymentcode`
																																)
																															)
																														)
																														LEFT JOIN `gender` `ge` ON (
																															(
																																`pe`.`gender` = `ge`.`gendercode`
																															)
																														)
																													)
																													LEFT JOIN `marital_status` `ms` ON (
																														(
																															`pe`.`marital_status` = `ms`.`marital_statuscode`
																														)
																													)
																												)
																												LEFT JOIN `bank` `bk` ON (
																													(
																														`pe`.`bank` = `bk`.`bankcode`
																													)
																												)
																											)
																											LEFT JOIN `working_hours` `wh` ON (
																												(
																													`pe`.`working_hours` = `wh`.`working_hourscode`
																												)
																											)
																										)
																										LEFT JOIN `category` `ca` ON (
																											(
																												`pe`.`category` = `ca`.`categorycode`
																											)
																										)
																									)
																									LEFT JOIN `personalstate` `ps` ON (
																										(
																											`pe`.`state` = `ps`.`personalstatecode`
																										)
																									)
																								)
																								LEFT JOIN `professional` `pro` ON (
																									(
																										(
																											`pe`.`personalcode` = `pro`.`professional_personal`
																										)
																										AND (`pro`.`current` = 1)
																										AND (`pro`.`deleted` = 0)
																									)
																								)
																							)
																							LEFT JOIN `type_of_contract` `toc` ON (
																								(
																									`pro`.`type_of_contract` = `toc`.`type_of_contractcode`
																								)
																							)
																						)
																						LEFT JOIN `position` `pos` ON (
																							(
																								`pro`.`position` = `pos`.`positioncode`
																							)
																						)
																					)
																					LEFT JOIN `payroll_institution` `pay` ON (
																						(
																							`pro`.`payroll_institution` = `pay`.`payroll_institutioncode`
																						)
																					)
																				)
																				LEFT JOIN `payroll_institution` `pay2` ON (
																					(
																						`pro`.`payroll_institution_2` = `pay2`.`payroll_institutioncode`
																					)
																				)
																			)
																			LEFT JOIN `research_group` `rp` ON (
																				(
																					`pro`.`research_group` = `rp`.`research_groupcode`
																				)
																			)
																		)
																		LEFT JOIN `research_group` `rp2` ON (
																			(
																				`pro`.`research_group_2` = `rp2`.`research_groupcode`
																			)
																		)
																	)
																	LEFT JOIN `research_group` `rp3` ON (
																		(
																			`pro`.`research_group_3` = `rp3`.`research_groupcode`
																		)
																	)
																)
																LEFT JOIN `research_group` `rp4` ON (
																	(
																		`pro`.`research_group_4` = `rp4`.`research_groupcode`
																	)
																)
															)
															LEFT JOIN `unit` `un` ON (
																(
																	`pro`.`professional_unit` = `un`.`unitcode`
																)
															)
														)
														LEFT JOIN `unit` `un2` ON (
															(
																`pro`.`professional_unit_2` = `un2`.`unitcode`
															)
														)
													)
													LEFT JOIN `unit` `un3` ON (
														(
															`pro`.`professional_unit_3` = `un3`.`unitcode`
														)
													)
												)
												LEFT JOIN `unit` `un4` ON (
													(
														`pro`.`professional_unit_4` = `un4`.`unitcode`
													)
												)
											)
											LEFT JOIN `organization_unit` `ou` ON (
												(
													`un`.`organization_unit` = `ou`.`organization_unitcode`
												)
											)
										)
										LEFT JOIN `grant_concession` `gc` ON (
											(
												(
													`gc`.`grant_concession_personal` = `pe`.`personalcode`
												)
												AND (`gc`.`current` = 1)
												AND (`gc`.`deleted` = 0)
											)
										)
									)
									LEFT JOIN `table_grant` `tg` ON (
										(
											`tg`.`grantcode` = `gc`.`table_grant`
										)
									)
								)
								LEFT JOIN `type_of_grant` `typ` ON (
									(
										`typ`.`type_of_grantcode` = `tg`.`type_of_grant`
									)
								)
							)
							LEFT JOIN `compensation` `com` ON (
								(
									(
										`pe`.`personalcode` = `com`.`compensation_personal`
									)
									AND (`com`.`current` = 1)
									AND (`com`.`deleted` = 0)
								)
							)
						)
						LEFT JOIN `type_of_compensation` `tco` ON (
							(
								`com`.`type_of_compensation` = `tco`.`type_of_compensationcode`
							)
						)
					)
					LEFT JOIN `academic_info` `ai` ON (
						(
							(
								`ai`.`academic_info_personal` = `pe`.`personalcode`
							)
							AND (`ai`.`current` = 1)
							AND (`ai`.`deleted` = 0)
						)
					)
				)
				LEFT JOIN `type_of_study` `ts` ON (
					(
						`ts`.`type_of_studycode` = `ai`.`type_of_study`
					)
				)
			)
			LEFT JOIN `research_group` `airg` ON (
				(
					`ai`.`lab_rotation_lab` = `airg`.`research_groupcode`
				)
			)
		)
		LEFT JOIN `research_group` `airg2` ON (
			(
				`ai`.`lab_rotation_lab` = `airg2`.`research_groupcode`
			)
		)
	)
WHERE
	(`pe`.`deleted` = 0); 

	
	
	
	
	
ALTER 
VIEW `view_personal_professional_date` AS 
SELECT
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
  `pe`.`access_scientific_publications` AS `access_scientific_publications`,
	`pe`.`bic` AS `bic`,
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
	`pe`.`second_affiliation` AS `second_affiliation`,
	`ps`.`description` AS `state`,
	`pe`.`personal_email` AS `personal_email`,
	`pe`.`language` AS `language`,
	(
		SELECT
			min(`pro2`.`start_date`) AS `min(``pro2``.``start_date``)`
		FROM
			`professional` `pro2`
		WHERE
			(
				(
					`pro2`.`professional_personal` = `pe`.`personalcode`
				)
				AND (`pro2`.`deleted` = 0)
			)
	) AS `first_start_date`,
	`pro`.`start_date` AS `professional_start_date`,
	`pro`.`end_date` AS `professional_end_date`,
	`pro`.`location` AS `location`,
	`pro`.`email` AS `email`,
	`pro`.`phone` AS `professional_phone`,
	`pro`.`mobile_long` AS `mobile_long`,
	`pro`.`mobile_short` AS `mobile_short`,
	`pro`.`lab_phone_number` AS `lab_phone_number`,
	`pro`.`fax` AS `fax`,
	`pro`.`deleted` AS `prodeleted`,
	`toc`.`description` AS `type_of_contract`,
	`toc`.`is_irbs` AS `contract_is_irbs`,
	`pos`.`description` AS `position`,
	`pay`.`description` AS `payroll_institution`,
	`pay2`.`description` AS `payroll_institution_2`,
	`rp`.`description` AS `research_group`,
	`rp2`.`description` AS `research_group_2`,
	`rp3`.`description` AS `research_group_3`,
	`rp4`.`description` AS `research_group_4`,
	`un`.`description` AS `professional_unit`,
	`un2`.`description` AS `professional_unit_2`,
	`un3`.`description` AS `professional_unit_3`,
	`un4`.`description` AS `professional_unit_4`,
	`ou`.`description` AS `organization_unit`
FROM
	(
		(
			(
				(
					(
						(
							(
								(
									(
										(
											(
												(
													(
														(
															(
																(
																	(
																		(
																			(
																				(
																					(
																						(
																							(
																								(
																									(
																										(
																											`personal` `pe`
																											LEFT JOIN `country` `ecc` ON (
																												(
																													`pe`.`end_of_contract_country` = `ecc`.`countrycode`
																												)
																											)
																										)
																										LEFT JOIN `country` `c` ON (
																											(
																												`pe`.`country` = `c`.`countrycode`
																											)
																										)
																									)
																									LEFT JOIN `country` `bc` ON (
																										(
																											`pe`.`birth_country` = `bc`.`countrycode`
																										)
																									)
																								)
																								LEFT JOIN `nationality` `n` ON (
																									(
																										`pe`.`nationality` = `n`.`nationalitycode`
																									)
																								)
																							)
																							LEFT JOIN `nationality` `n2` ON (
																								(
																									`pe`.`nationality_2` = `n2`.`nationalitycode`
																								)
																							)
																						)
																						LEFT JOIN `payment` `pa` ON (
																							(
																								`pe`.`payments` = `pa`.`paymentcode`
																							)
																						)
																					)
																					LEFT JOIN `gender` `ge` ON (
																						(
																							`pe`.`gender` = `ge`.`gendercode`
																						)
																					)
																				)
																				LEFT JOIN `marital_status` `ms` ON (
																					(
																						`pe`.`marital_status` = `ms`.`marital_statuscode`
																					)
																				)
																			)
																			LEFT JOIN `bank` `bk` ON (
																				(
																					`pe`.`bank` = `bk`.`bankcode`
																				)
																			)
																		)
																		LEFT JOIN `working_hours` `wh` ON (
																			(
																				`pe`.`working_hours` = `wh`.`working_hourscode`
																			)
																		)
																	)
																	LEFT JOIN `category` `ca` ON (
																		(
																			`pe`.`category` = `ca`.`categorycode`
																		)
																	)
																)
																LEFT JOIN `personalstate` `ps` ON (
																	(
																		`pe`.`state` = `ps`.`personalstatecode`
																	)
																)
															)
															LEFT JOIN `professional` `pro` ON (
																(
																	(
																		`pe`.`personalcode` = `pro`.`professional_personal`
																	)
																	AND (`pro`.`deleted` = 0)
																)
															)
														)
														LEFT JOIN `type_of_contract` `toc` ON (
															(
																`pro`.`type_of_contract` = `toc`.`type_of_contractcode`
															)
														)
													)
													LEFT JOIN `position` `pos` ON (
														(
															`pro`.`position` = `pos`.`positioncode`
														)
													)
												)
												LEFT JOIN `payroll_institution` `pay` ON (
													(
														`pro`.`payroll_institution` = `pay`.`payroll_institutioncode`
													)
												)
											)
											LEFT JOIN `payroll_institution` `pay2` ON (
												(
													`pro`.`payroll_institution_2` = `pay2`.`payroll_institutioncode`
												)
											)
										)
										LEFT JOIN `research_group` `rp` ON (
											(
												`pro`.`research_group` = `rp`.`research_groupcode`
											)
										)
									)
									LEFT JOIN `research_group` `rp2` ON (
										(
											`pro`.`research_group_2` = `rp2`.`research_groupcode`
										)
									)
								)
								LEFT JOIN `research_group` `rp3` ON (
									(
										`pro`.`research_group_3` = `rp3`.`research_groupcode`
									)
								)
							)
							LEFT JOIN `research_group` `rp4` ON (
								(
									`pro`.`research_group_4` = `rp4`.`research_groupcode`
								)
							)
						)
						LEFT JOIN `unit` `un` ON (
							(
								`pro`.`professional_unit` = `un`.`unitcode`
							)
						)
					)
					LEFT JOIN `unit` `un2` ON (
						(
							`pro`.`professional_unit_2` = `un2`.`unitcode`
						)
					)
				)
				LEFT JOIN `unit` `un3` ON (
					(
						`pro`.`professional_unit_3` = `un3`.`unitcode`
					)
				)
			)
			LEFT JOIN `unit` `un4` ON (
				(
					`pro`.`professional_unit_4` = `un4`.`unitcode`
				)
			)
		)
		LEFT JOIN `organization_unit` `ou` ON (
			(
				`un`.`organization_unit` = `ou`.`organization_unitcode`
			)
		)
	)
WHERE
	(`pe`.`deleted` = 0) ;

	