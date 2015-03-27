INSERT INTO alumni_directory_data
(alumni_directory_datacode, personal, start_date, end_date, unit, unit_2, research_group, research_group_2, irb_job_positions, version, deleted)
SELECT i.alumni_irb_jobscode, i.personal, i.start_date, i.end_date, i.unit, i.unit_2, i.research_group, i.research_group_2, i.irb_job_positions, i.version, i.deleted
FROM alumni_irb_jobs i;