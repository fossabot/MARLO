
INSERT INTO role_permissions (role_id,permission_id) values (22,438);
INSERT INTO role_permissions (role_id,permission_id) values  (21,438);
INSERT INTO `permissions` (`id`, `permission`, `description`, `type`) VALUES ('462', 'crp:{0}:fundingSource:{1}:budget', 'Can Edit Budget', '0');

DROP VIEW IF EXISTS `user_permissions`;
  CREATE  VIEW `user_permissions` AS
SELECT    `u`.`id`                                       AS `id`, 
          `r`.`acronym`                                  AS `ro_acronym`, 
          `r`.`id`                                       AS `role_id`, 
          replace(`p`.`permission`,'{0}',`cp`.`acronym`) AS `permission`, 
          NULL                                           AS `project_id`, 
          `cp`.`acronym`                                 AS `crp_acronym`, 
          `p`.`id`                                       AS `permission_id` 
FROM      ((((((`users` `u` 
LEFT JOIN `user_roles` `ro` 
ON       (( 
                              `ro`.`user_id` = `u`.`id`))) 
JOIN      `roles` `r` 
ON       (( 
                              `r`.`id` = `ro`.`role_id`))) 
JOIN      `role_permissions` `rp` 
ON       (( 
                              `rp`.`role_id` = `r`.`id`))) 
JOIN      `permissions` `p` 
ON       (( 
                              `p`.`id` = `rp`.`permission_id`))) 
JOIN      `crp_users` `crp` 
ON       ((( 
                                        `u`.`id` = `crp`.`user_id`) 
                    AND       ( 
                                        `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN      `crps` `cp` 
ON       (( 
                              `cp`.`id` = `crp`.`crp_id`))) 
WHERE     (( 
                              `p`.`type` = 0) 
          AND       ( 
                              `r`.`id` <> 17 and r.acronym !='FM')) 
UNION 
SELECT    `u`.`id`                                       AS `id`, 
          `r`.`acronym`                                  AS `ro_acronym`, 
          `r`.`id`                                       AS `role_id`, 
          replace(`p`.`permission`,'{0}',`cp`.`acronym`) AS `permission`, 
          NULL                                           AS `project_id`, 
          `cp`.`acronym`                                 AS `crp_acronym`, 
          `p`.`id`                                       AS `permission_id` 
FROM      (((((`users` `u` 
LEFT JOIN `user_roles` `ro` 
ON       (( 
                              `ro`.`user_id` = `u`.`id`))) 
JOIN      `roles` `r` 
ON       (( 
                              `r`.`id` = `ro`.`role_id`))) 
JOIN      `role_permissions` `rp` 
ON       (( 
                              `rp`.`role_id` = `r`.`id`))) 
JOIN      `permissions` `p` 
ON       (( 
                              `p`.`id` = `rp`.`permission_id`))) 
JOIN      `crps` `cp`) 
WHERE     (( 
                              `p`.`type` = 0) 
          AND       ( 
                              `r`.`id` = 17)) 
UNION 
SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
       replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`) AS `replace(replace(``p``.``permission``,'{0}',cp.acronym),'{1}',pro.id)`,
       NULL                                                                     AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   ((((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crp_program_leaders` `cprog` 
ON    ((( 
                            `cprog`.`user_id` = `u`.`id`) 
              AND    ( 
                            `cprog`.`is_active` = 1)))) 
JOIN   `crp_programs` `pro` 
ON    ((( 
                            `pro`.`id` = `cprog`.`crp_program_id`) 
              AND    ( 
                            `pro`.`program_type` = 1) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 
JOIN   `crps` `cp` 
ON    ((( 
                            `cp`.`id` = `crp`.`crp_id`) 
              AND    ( 
                            `pro`.`crp_id` = `cp`.`id`)))) 
WHERE  (( 
                     `p`.`type` = 3) 
       AND    ( 
                     `r`.`acronym` = 'FPL')) 
UNION 
SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
       replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`) AS `name_exp_32`,
       `pro`.`id`                                                               AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   (((((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crps` `cp` 
ON    (( 
                     `cp`.`id` = `crp`.`crp_id`))) 
JOIN   `projects` `pro` 
ON    ((( 
                            `pro`.`crp_id` = `cp`.`id`) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 
JOIN   `liaison_institutions` `lin` 
ON    (( 
                     `lin`.`id` = `pro`.`liaison_institution_id`))) 
JOIN   `liaison_users` `lus` 
ON    ((( 
                            `lus`.`institution_id` = `lin`.`id`) 
              AND    ( 
                            `u`.`id` = `lus`.`user_id`) 
              AND    ( 
                            `lus`.`is_active` = 1)))) 
WHERE  (( 
                     `p`.`type` = 1) 
       AND    ( 
                     `r`.`acronym` IN ('PMU', 
                                       'ML', 
                                       'CP', 
                                       'FPL', 
                                       'RPL'))) 
UNION 
SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
       replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`) AS `name_exp_39`,
       `pro`.`id`                                                               AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   ((((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crps` `cp` 
ON    (( 
                     `cp`.`id` = `crp`.`crp_id`))) 
JOIN   `projects` `pro` 
ON    ((( 
                            `pro`.`crp_id` = `cp`.`id`) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 
JOIN   `liaison_users` `lus` 
ON    ((( 
                            `lus`.`id` = `pro`.`liaison_user_id`) 
              AND    ( 
                            `u`.`id` = `lus`.`user_id`) 
              AND    ( 
                            `lus`.`is_active` = 1)))) 
WHERE  (( 
                     `p`.`type` = 1) 
       AND    ( 
                     `r`.`acronym` IN ('PMU', 
                                       'ML', 
                                       'CP', 
                                       'FPL', 
                                       'RPL'))) 


UNION 
SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
       replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`) AS `name_exp_39`,
       `pro`.`id`                                                               AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   ((((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crps` `cp` 
ON    (( 
                     `cp`.`id` = `crp`.`crp_id`))) 
JOIN   `projects` `pro` 
ON    ((( 
                            `pro`.`crp_id` = `cp`.`id`) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 
) 
WHERE  (( 
                     `p`.`type` = 1 and p.id=2) 
       AND    ( 
                     `r`.`acronym` IN ('PMU'
                                       ))) 

UNION 
SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
       replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`) AS `name_exp_46`,
       `pro`.`id`                                                               AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   (((((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crps` `cp` 
ON    (( 
                     `cp`.`id` = `crp`.`crp_id`))) 
JOIN   `projects` `pro` 
ON    ((( 
                            `pro`.`crp_id` = `cp`.`id`) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 
JOIN   `project_partners` `pp` 
ON    ((( 
                            `pp`.`project_id` = `pro`.`id`) 
              AND    ( 
                            `pp`.`is_active` = 1)))) 
JOIN   `project_partner_persons` `pers` 
ON    ((( 
                            `pp`.`id` = `pers`.`project_partner_id`) 
              AND    ( 
                            `pers`.`is_active` = 1) 
              AND    ( 
                            `pers`.`contact_type` = 'PL') 
              AND    ( 
                            `pers`.`user_id` = `u`.`id`)))) 
WHERE  (( 
                     `p`.`type` = 1) 
       AND    ( 
                     `r`.`acronym` = 'PL')) 
UNION 
SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
       replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`) AS `name_exp_46`,
       `pro`.`id`                                                               AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   (((((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crps` `cp` 
ON    (( 
                     `cp`.`id` = `crp`.`crp_id`))) 
JOIN   `projects` `pro` 
ON    ((( 
                            `pro`.`crp_id` = `cp`.`id`) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 
JOIN   `project_partners` `pp` 
ON    ((( 
                            `pp`.`project_id` = `pro`.`id`) 
              AND    ( 
                            `pp`.`is_active` = 1)))) 
JOIN   `project_partner_persons` `pers` 
ON    ((( 
                            `pp`.`id` = `pers`.`project_partner_id`) 
              AND    ( 
                            `pers`.`is_active` = 1) 
              AND    ( 
                            `pers`.`contact_type` = 'PC') 
              AND    ( 
                            `pers`.`user_id` = `u`.`id`)))) 
WHERE  (( 
                     `p`.`type` = 1) 
       AND    ( 
                     `r`.`acronym` = 'PC')) 
union

                                       
 

SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
       replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`) AS `name_exp_46`,
       `pro`.`id`                                                               AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   (((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crps` `cp` 
ON    (( 
                     `cp`.`id` = `crp`.`crp_id`))) 
JOIN   `funding_sources` `pro` 
ON    ((( 
                            `pro`.`crp_id` = `cp`.`id` ) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 

                            
                            WHERE 
                     `p`.`id` in (438,462)and (pro.type  <> 1  or pro.type is null) and 
r.`acronym` IN (
                                       'PMU', 
                                       'ML', 
                                       'CP', 
                                       'FPL', 
                                       'RPL'
                                       )

union 
 select u.id, `ro`.`acronym`                                                            AS `acronym`,
         `ro`.`id`                                                                 AS `rolid`, 
         replace(replace(`per`.`permission`,'{0}',`cp`.`acronym`),'{1}',pro.id) AS `name_exp_46`,
          pro.id                                                          AS `project_id`,
         `cp`.`acronym`                                                           AS `crp_acronym`,
         `per`.`id`                                                                 AS `permission_id` from users u
   INNER JOIN user_roles urol on urol.user_id=u.id 
  INNER JOIN roles ro on ro.id=urol.role_id and ro.acronym='FM'
  INNER JOIN crp_users cpu on cpu.user_id=u.id and cpu.is_active=1
  INNER JOIN crps cp on cp.id=cpu.crp_id
INNER JOIN funding_sources pro on pro.crp_id=cp.id and pro.is_active=1
  INNER JOIN role_permissions rp on rp.role_id=ro.id
  INNER JOIN permissions per on per.id=rp.permission_id
  where u.is_active=1 and per.id in (438,462)

union 
  select u.id, `ro`.`acronym`                                                            AS `acronym`,
         `ro`.`id`                                                                 AS `rolid`, 
         replace(replace(`per`.`permission`,'{0}',`cp`.`acronym`),'{1}',pro.id) AS `name_exp_46`,
          pro.id                                                          AS `project_id`,
         `cp`.`acronym`                                                           AS `crp_acronym`,
         `per`.`id`                                                                 AS `permission_id` from users u
   INNER JOIN user_roles urol on urol.user_id=u.id 
  INNER JOIN roles ro on ro.id=urol.role_id and ro.acronym='FM'
  INNER JOIN crp_users cpu on cpu.user_id=u.id and cpu.is_active=1
  INNER JOIN crps cp on cp.id=cpu.crp_id
INNER JOIN projects pro on pro.crp_id=cp.id 
  INNER JOIN role_permissions rp on rp.role_id=ro.id
  INNER JOIN permissions per on per.id=rp.permission_id
  where u.is_active=1 and per.id not in (438,462) and pro.is_active=1


union 
  select u.id, `ro`.`acronym`                                                            AS `acronym`,
         `ro`.`id`                                                                 AS `rolid`, 
         replace(replace(`per`.`permission`,'{0}',`cp`.`acronym`),'{1}',pro.id) AS `name_exp_46`,
          pro.id                                                          AS `project_id`,
         `cp`.`acronym`                                                           AS `crp_acronym`,
         `per`.`id`                                                                 AS `permission_id` from users u
   INNER JOIN user_roles urol on urol.user_id=u.id 
  INNER JOIN roles ro on ro.id=urol.role_id and ro.acronym='FM'
  INNER JOIN crp_users cpu on cpu.user_id=u.id and cpu.is_active=1
  INNER JOIN crps cp on cp.id=cpu.crp_id
INNER JOIN funding_sources pro on pro.crp_id=cp.id 
  INNER JOIN role_permissions rp on rp.role_id=ro.id
  INNER JOIN permissions per on per.id=rp.permission_id
  where u.is_active=1 and per.id in (438,462)and pro.is_active=1

UNION

SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
        replace(replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`),'{2}',lin.institution_id) AS `name_exp_32`,
       `pro`.`id`                                                               AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   (((((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crps` `cp` 
ON    (( 
                     `cp`.`id` = `crp`.`crp_id`))) 
JOIN   `projects` `pro` 
ON    ((( 
                            `pro`.`crp_id` = `cp`.`id`) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 
 
JOIN   `liaison_users` `lus` 
ON    ((
                  ( 
                            `u`.`id` = `lus`.`user_id`) 
              AND    ( 
                            `lus`.`is_active` = 1)))) 

JOIN   `liaison_institutions` `lin` 
ON    (( 
                      `lus`.`institution_id` = `lin`.`id`)))

WHERE  (( 
                     `p`.`type` = 1) 
       AND    ( 
                     `r`.`acronym` IN (
                                       'CP'
                                       ))) 
and  p.id in (447,206,429)


union 

SELECT `u`.`id`                                                                 AS `id`, 
       `r`.`acronym`                                                            AS `acronym`,
       `r`.`id`                                                                 AS `rolid`, 
       replace(replace(`p`.`permission`,'{0}',`cp`.`acronym`),'{1}',`pro`.`id`) AS `name_exp_46`,
       `pro`.`id`                                                               AS `project_id`,
       `cp`.`acronym`                                                           AS `crp_acronym`,
       `p`.`id`                                                                 AS `permission_id`
FROM   (((((((`users` `u` 
JOIN   `user_roles` `ro` 
ON    (( 
                     `ro`.`user_id` = `u`.`id`))) 
JOIN   `roles` `r` 
ON    (( 
                     `r`.`id` = `ro`.`role_id`))) 
JOIN   `role_permissions` `rp` 
ON    (( 
                     `rp`.`role_id` = `r`.`id`))) 
JOIN   `permissions` `p` 
ON    (( 
                     `p`.`id` = `rp`.`permission_id`))) 
JOIN   `crp_users` `crp` 
ON    ((( 
                            `u`.`id` = `crp`.`user_id`) 
              AND    ( 
                            `crp`.`crp_id` = `r`.`crp_id`)))) 
JOIN   `crps` `cp` 
ON    (( 
                     `cp`.`id` = `crp`.`crp_id`))) 
JOIN   `funding_sources` `pro` 
ON    ((( 
                            `pro`.`crp_id` = `cp`.`id` ) 
              AND    ( 
                            `pro`.`is_active` = 1)))) 
INNER JOIN funding_source_institutions fin on fin.funding_source_id=pro.id
inner join liaison_users lu on lu.user_id=u.id INNER JOIN liaison_institutions li on li.id=lu.institution_id and li.institution_id=fin.institution_id

                            
                            WHERE 
                     `p`.`id` in (438,462) and (pro.type  <> 1  or pro.type is null) and 
r.`acronym` IN (
                                       'CP'
                                       )
;

INSERT INTO role_permissions (role_id,permission_id) values (2,462);
INSERT INTO role_permissions (role_id,permission_id) values (4,462);
INSERT INTO role_permissions (role_id,permission_id) values (48,462);
INSERT INTO role_permissions (role_id,permission_id) values (20,462);
INSERT INTO role_permissions (role_id,permission_id) values (49,462);
INSERT INTO role_permissions (role_id,permission_id) values (27,462);
INSERT INTO role_permissions (role_id,permission_id) values (50,462);
INSERT INTO role_permissions (role_id,permission_id) values (33,462);
INSERT INTO role_permissions (role_id,permission_id) values (51,462);
INSERT INTO role_permissions (role_id,permission_id) values (38,462);
INSERT INTO role_permissions (role_id,permission_id) values (10,462);
INSERT INTO role_permissions (role_id,permission_id) values (11,462);
INSERT INTO role_permissions (role_id,permission_id) values (12,462);
INSERT INTO role_permissions (role_id,permission_id) values (10,462);
INSERT INTO role_permissions (role_id,permission_id) values (20,444);

INSERT INTO `permissions` (`id`, `permission`, `description`, `type`) VALUES ('463', 'crp:{0}:fundingSource:budget', 'can create funding source with budget', '0');
INSERT INTO role_permissions (role_id,permission_id) values (2,463);
INSERT INTO role_permissions (role_id,permission_id) values (4,463);
INSERT INTO role_permissions (role_id,permission_id) values (48,463);
INSERT INTO role_permissions (role_id,permission_id) values (20,463);
INSERT INTO role_permissions (role_id,permission_id) values (49,463);
INSERT INTO role_permissions (role_id,permission_id) values (27,463);
INSERT INTO role_permissions (role_id,permission_id) values (50,463);
INSERT INTO role_permissions (role_id,permission_id) values (33,463);
INSERT INTO role_permissions (role_id,permission_id) values (51,463);
INSERT INTO role_permissions (role_id,permission_id) values (38,463);
INSERT INTO role_permissions (role_id,permission_id) values (10,463);
INSERT INTO role_permissions (role_id,permission_id) values (11,463);
INSERT INTO role_permissions (role_id,permission_id) values (12,463);
INSERT INTO role_permissions (role_id,permission_id) values (10,463);
