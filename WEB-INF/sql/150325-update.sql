-- ----------------------------
-- Table structure for alumni_directory_data
-- ----------------------------
DROP TABLE IF EXISTS `alumni_directory_data`;
CREATE TABLE `alumni_directory_data` (
  `alumni_directory_datacode` varchar(255) NOT NULL,
  `personal` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `unit_2` varchar(255) DEFAULT NULL,
  `research_group` varchar(255) DEFAULT NULL,
  `research_group_2` varchar(255) DEFAULT NULL,
  `irb_job_positions` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`alumni_directory_datacode`),
  KEY `fk_alumni_directory_data_irb_job_positions1` (`irb_job_positions`),
  KEY `fk_alumni_directory_data_alumni_personal1` (`personal`),
  CONSTRAINT `fk_alumni_directory_data_alumni_personal1` FOREIGN KEY (`personal`) REFERENCES `alumni_personal` (`alumni_personalcode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumni_directory_data_irb_job_positions1` FOREIGN KEY (`irb_job_positions`) REFERENCES `alumni_irb_job_positions` (`alumni_irb_job_positionscode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDBn;



ALTER 
VIEW `view_alumni_personnel` AS 
SELECT distinct
	`alumni_personal`.`alumni_personalcode` AS `alumni_personalcode`,
	`alumni_personal`.`external` AS `external`,
	`alumni_titles`.`description` AS `titles_description`,
	`alumni_personal`.`firstname` AS `firstname`,
	`alumni_personal`.`surname` AS `surname`,
	`alumni_personal`.`irb_surname` AS `irb_surname`,

	`alumni_personal`.`remarks` AS `remarks`,
	`alumni_personal`.`skype` AS `skype`,
	`alumni_personal`.`cellphone` AS `cellphone`,
	`alumni_personal`.`deceased` AS `deceased`,
	`alumni_personal`.`communications_wanted` AS `communications_wanted`,
	`gender`.`description` AS `gender`,
	`nationality`.`description` AS `nationality`,
	`nationality_2`.`description` AS `nationality_2`,
	`alumni_personal`.`birth` AS `birth`,
	`alumni_personal`.`email` AS `email`,
	`alumni_personal`.`url` AS `url`,
	`alumni_personal`.`facebook` AS `facebook`,
	`alumni_personal`.`linkedin` AS `linkedin`,
	`alumni_personal`.`twitter` AS `twitter`,
	`alumni_personal`.`keywords` AS `keywords`,
	`alumni_personal`.`biography` AS `biography`,
	`alumni_personal`.`awards` AS `awards`,
	`alumni_personal`.`ORCIDID` AS `ORCIDID`,
	`alumni_personal`.`researcherid` AS `researcherid`,
	`alumni_personal`.`pubmedid` AS `pubmedid`,
	`alumni_personal`.`verified` AS `verified`,
	`alumni_personal`.`show_data` AS `show_data`,
	`alumni_irb_jobs`.`alumni_irb_jobscode` AS `irb_jobscode`,
	`alumni_irb_jobs`.`start_date` AS `irb_jobs_start_date`,
	`alumni_irb_jobs`.`end_date` AS `irb_jobs_end_date`,
	`unit`.`description` AS `irb_jobs_unit`,
	`unit_2`.`description` AS `irb_jobs_unit_2`,
	`research_group`.`description` AS `irb_jobs_research_group`,
	`research_group_2`.`description` AS `irb_jobs_research_group_2`,
	`alumni_irb_job_positions`.`description` AS `irb_jobs_positions_description`,
	`alumni_irb_job_position_types`.`description` AS `irb_job_position_types_description`,



	`alumni_directory_data`.`alumni_directory_datacode` AS `directory_datacode`,
	`alumni_directory_data`.`start_date` AS `directory_data_start_date`,
	`alumni_directory_data`.`end_date` AS `directory_data_end_date`,
	`dd_unit`.`description` AS `directory_data_unit`,
	`dd_unit_2`.`description` AS `directory_data_unit_2`,
	`dd_research_group`.`description` AS `directory_data_research_group`,
	`dd_research_group_2`.`description` AS `directory_data_research_group_2`,


	`alumni_external_jobs`.`alumni_external_jobscode` AS `external_jobscode`,
	`alumni_external_jobs`.`start_date` AS `external_jobs_start_date`,
	`alumni_external_jobs`.`end_date` AS `external_jobs_end_date`,
	`alumni_external_jobs`.`comments` AS `external_jobs_comments`,
	`alumni_external_jobs`.`institution` AS `external_jobs_institution`,
	`alumni_external_jobs`.`address` AS `external_jobs_address`,
	`alumni_external_jobs`.`postcode` AS `external_jobs_postcode`,
	`alumni_external_jobs`.`city` AS `external_jobs_city`,
	`alumni_external_jobs`.`telephone` AS `external_jobs_telephone`,
	`alumni_external_jobs`.`current` AS `external_jobs_current`,
	`country`.`description` AS `external_jobs_country`,
	`alumni_external_job_positions`.`description` AS `external_jobs_positions_description`,
	`alumni_job_position_types`.`description` AS `external_job_position_types_description`,
	`alumni_external_job_sectors`.`description` AS `external_job_sectors_description`
FROM(((((
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
																	`alumni_personal`
																	LEFT JOIN `alumni_titles` ON (
																		(
																			(
																				`alumni_personal`.`titles` = `alumni_titles`.`alumni_titlescode`
																			)
																			AND (
																				(
																					`alumni_titles`.`deleted` = _utf8 ''
																				)
																				OR (
																					`alumni_titles`.`deleted` = 0
																				)
																			)
																		)
																	)
																)
																LEFT JOIN `gender` ON (
																	(
																		(
																			`alumni_personal`.`gender` = `gender`.`gendercode`
																		)
																		AND (
																			(`gender`.`deleted` = _utf8 '')
																			OR (`gender`.`deleted` = 0)
																		)
																	)
																)
															)
															LEFT JOIN `nationality` ON (
																(
																	(
																		`alumni_personal`.`nationality` = `nationality`.`nationalitycode`
																	)
																	AND (
																		(
																			`nationality`.`deleted` = _utf8 ''
																		)
																		OR (`nationality`.`deleted` = 0)
																	)
																)
															)
														)
														LEFT JOIN `nationality` `nationality_2` ON (
															(
																(
																	`alumni_personal`.`nationality_2` = `nationality_2`.`nationalitycode`
																)
																AND (
																	(
																		`nationality_2`.`deleted` = _utf8 ''
																	)
																	OR (
																		`nationality_2`.`deleted` = 0
																	)
																)
															)
														)
													)
													LEFT JOIN `alumni_irb_jobs` ON (
														(
															(
																`alumni_irb_jobs`.`personal` = `alumni_personal`.`alumni_personalcode`
															)
															AND (
																(
																	`alumni_irb_jobs`.`deleted` = _utf8 ''
																)
																OR (
																	`alumni_irb_jobs`.`deleted` = 0
																)
															)
														)
													)
												)
												LEFT JOIN `alumni_irb_job_positions` ON (
													(
														(
															`alumni_irb_jobs`.`irb_job_positions` = `alumni_irb_job_positions`.`alumni_irb_job_positionscode`
														)
														AND (
															(
																`alumni_irb_job_positions`.`deleted` = _utf8 ''
															)
															OR (
																`alumni_irb_job_positions`.`deleted` = 0
															)
														)
													)
												)
											)
											LEFT JOIN `alumni_job_position_types` `alumni_irb_job_position_types` ON (
												(
													(
														`alumni_irb_job_position_types`.`alumni_job_position_typescode` = `alumni_irb_job_positions`.`alumni_irb_job_positionscode`
													)
													AND (
														(
															`alumni_irb_job_position_types`.`deleted` = _utf8 ''
														)
														OR (
															`alumni_irb_job_position_types`.`deleted` = 0
														)
													)
												)
											)
										)
										LEFT JOIN `unit` ON (
											(
												(
													`unit`.`unitcode` = `alumni_irb_jobs`.`unit`
												)
												AND (
													(`unit`.`deleted` = _utf8 '')
													OR (`unit`.`deleted` = 0)
												)
											)
										)
									)
									LEFT JOIN `unit` `unit_2` ON (
										(
											(
												`unit_2`.`unitcode` = `alumni_irb_jobs`.`unit_2`
											)
											AND (
												(`unit_2`.`deleted` = _utf8 '')
												OR (`unit_2`.`deleted` = 0)
											)
										)
									)
								)
								LEFT JOIN `alumni_external_jobs` ON (
									(
										(
											`alumni_external_jobs`.`personal` = `alumni_personal`.`alumni_personalcode`
										)
										AND (
											(
												`alumni_external_jobs`.`deleted` = _utf8 ''
											)
											OR (
												`alumni_external_jobs`.`deleted` = 0
											)
										)
									)
								)
							)
							LEFT JOIN `alumni_external_job_positions` ON (
								(
									(
										`alumni_external_jobs`.`external_job_positions` = `alumni_external_job_positions`.`alumni_external_job_positionscode`
									)
									AND (
										(
											`alumni_external_job_positions`.`deleted` = _utf8 ''
										)
										OR (
											`alumni_external_job_positions`.`deleted` = 0
										)
									)
								)
							)
						)
						LEFT JOIN `alumni_job_position_types` ON (
							(
								(
									`alumni_job_position_types`.`alumni_job_position_typescode` = `alumni_external_job_positions`.`alumni_external_job_positionscode`
								)
								AND (
									(
										`alumni_job_position_types`.`deleted` = _utf8 ''
									)
									OR (
										`alumni_job_position_types`.`deleted` = 0
									)
								)
							)
						)
					)
					LEFT JOIN `alumni_external_job_sectors` ON (
						(
							(
								`alumni_external_job_sectors`.`alumni_external_job_sectorscode` = `alumni_external_jobs`.`external_job_sectors`
							)
							AND (
								(
									`alumni_external_job_sectors`.`deleted` = _utf8 ''
								)
								OR (
									`alumni_external_job_sectors`.`deleted` = 0
								)
							)
						)
					)
				)
				LEFT JOIN `country` ON (
					(
						(
							`country`.`countrycode` = `alumni_external_jobs`.`country`
						)
						AND (
							(
								`country`.`deleted` = _utf8 ''
							)
							OR (`country`.`deleted` = 0)
						)
					)
				)
			)
			LEFT JOIN `research_group` ON (
				(
					(
						`research_group`.`research_groupcode` = `alumni_irb_jobs`.`research_group`
					)
					AND (
						(
							`research_group`.`deleted` = _utf8 ''
						)
						OR (
							`research_group`.`deleted` = 0
						)
					)
				)
			)
		)
		LEFT JOIN `research_group` `research_group_2` ON (
			(
				(
					`research_group_2`.`research_groupcode` = `alumni_irb_jobs`.`research_group_2`
				)
				AND (
					(
						`research_group_2`.`deleted` = _utf8 ''
					)
					OR (
						`research_group_2`.`deleted` = 0
					)
				)
			)
		)
	)
LEFT JOIN `alumni_directory_data` ON (
														(
															(
																`alumni_directory_data`.`personal` = `alumni_personal`.`alumni_personalcode`
															)
															AND (
																(
																	`alumni_directory_data`.`deleted` = _utf8 ''
																)
																OR (
																	`alumni_directory_data`.`deleted` = 0
																)
															)
														)
													)
												)

						LEFT JOIN `unit` `dd_unit` ON (
(
	(

													`dd_unit`.`unitcode` = `alumni_directory_data`.`unit`
												)
												AND (
													(`dd_unit`.`deleted` = _utf8 '')
													OR (`dd_unit`.`deleted` = 0)
												)
											)
										)
									)
									LEFT JOIN `unit` `dd_unit_2` ON (
										(
											(
												`dd_unit_2`.`unitcode` = `alumni_directory_data`.`unit_2`
											)
											AND (
												(`dd_unit_2`.`deleted` = _utf8 '')
												OR (`dd_unit_2`.`deleted` = 0)
											)
										)
									)
								)

LEFT JOIN `research_group` `dd_research_group` ON (
				(
					(
						`dd_research_group`.`research_groupcode` = `alumni_directory_data`.`research_group`
					)
					AND (
						(
							`dd_research_group`.`deleted` = _utf8 ''
						)
						OR (
							`dd_research_group`.`deleted` = 0
						)
					)
				)
			)
		)
		LEFT JOIN `research_group` `dd_research_group_2` ON (
			(
				(
					`dd_research_group_2`.`research_groupcode` = `alumni_directory_data`.`research_group_2`
				)
				AND (
					(
						`dd_research_group_2`.`deleted` = _utf8 ''
					)
					OR (
						`dd_research_group_2`.`deleted` = 0
					)
				)
			)
		)
	)



WHERE
	(
		(
			`alumni_personal`.`deleted` = _utf8 ''
		)
		OR (
			`alumni_personal`.`deleted` = 0
		)
	)
ORDER BY
	`alumni_personal`.`surname` ;

