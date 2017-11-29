/* Add notifyByEmail column on UserRoles */
ALTER TABLE user_roles ADD send_email_active tinyint(1) DEFAULT 1 NOT NULL AFTER `role_id`;

/* Inactivate CRP-Admin notifications */
UPDATE user_roles ur SET ur.send_email_active = 0 WHERE ur.user_id= (SELECT u.id FROM users u WHERE u.username = 'cevelasco') AND ur.role_id= (SELECT r.id FROM roles r WHERE r.acronym = "CRP-Admin" AND r.crp_id = (SELECT c.id FROM crps c WHERE c.name = "Wheat"));

UPDATE user_roles ur SET ur.send_email_active = 0 WHERE ur.user_id= (SELECT u.id FROM users u WHERE u.username = 'hdunsford') AND ur.role_id= (SELECT r.id FROM roles r WHERE r.acronym = "CRP-Admin" AND r.crp_id = (SELECT c.id FROM crps c WHERE c.name = "Maize"));
