INSERT INTO crp_programs (`name`, acronym, program_type, color, is_active, created_by, modified_by, active_since, modification_justification, global_unit_id)
SELECT `name`, acronym, program_type, color, is_active, 3, 3, NOW(), '', 29 from center_programs;