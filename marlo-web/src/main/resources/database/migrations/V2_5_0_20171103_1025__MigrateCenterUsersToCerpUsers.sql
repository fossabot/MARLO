insert into crp_users (user_id, is_active, created_by, active_since, modified_by, global_unit_id, modification_justification)
select user_id, is_active, created_by, active_since,modified_by,23,"" from center_users;