INSERT INTO `parameters` (`key`, `description`, `format`, `default_value`, `category`) VALUES ('show_gender_keywords_summaries', 'Show or Hide the Gender predefinied keywords opcion in search termns summary', '1', NULL, '2');
INSERT INTO `custom_parameters` (`parameter_id`, `crp_id`, `value`, `created_by`, `is_active`, `active_since`,`modified_by`, `modification_justification`) VALUES ((select id from parameters where `key`='show_gender_keywords_summaries'), '1', 'true', '3', '1', CURRENT_TIMESTAMP, '1', '');
INSERT INTO `custom_parameters` (`parameter_id`, `crp_id`, `value`, `created_by`, `is_active`, `active_since`,`modified_by`, `modification_justification`) VALUES ((select id from parameters where `key`='show_gender_keywords_summaries'), '3', 'false', '3', '1', CURRENT_TIMESTAMP, '1', '');
INSERT INTO `custom_parameters` (`parameter_id`, `crp_id`, `value`, `created_by`, `is_active`, `active_since`,`modified_by`, `modification_justification`) VALUES ((select id from parameters where `key`='show_gender_keywords_summaries'), '4', 'true', '3', '1', CURRENT_TIMESTAMP, '1', '');
INSERT INTO `custom_parameters` (`parameter_id`, `crp_id`, `value`, `created_by`, `is_active`, `active_since`,`modified_by`, `modification_justification`) VALUES ((select id from parameters where `key`='show_gender_keywords_summaries'), '5', 'true', '3', '1', CURRENT_TIMESTAMP, '1', '');
INSERT INTO `custom_parameters` (`parameter_id`, `crp_id`, `value`, `created_by`, `is_active`, `active_since`,`modified_by`, `modification_justification`) VALUES ((select id from parameters where `key`='show_gender_keywords_summaries'), '7', 'true', '3', '1', CURRENT_TIMESTAMP, '1', '');
INSERT INTO `custom_parameters` (`parameter_id`, `crp_id`, `value`, `created_by`, `is_active`, `active_since`,`modified_by`, `modification_justification`) VALUES ((select id from parameters where `key`='show_gender_keywords_summaries'), '21', 'true', '3', '1', CURRENT_TIMESTAMP, '1', '');
INSERT INTO `custom_parameters` (`parameter_id`, `crp_id`, `value`, `created_by`, `is_active`, `active_since`,`modified_by`, `modification_justification`) VALUES ((select id from parameters where `key`='show_gender_keywords_summaries'), '22', 'true', '3', '1', CURRENT_TIMESTAMP, '1', '');
