ALTER TABLE `project_outcomes`
MODIFY COLUMN `expected_value`  decimal(10,5) NULL DEFAULT NULL AFTER `outcome_id`;

