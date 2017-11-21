-- ----------------------------
-- Table structure for crp_timeline_events
-- ----------------------------
DROP TABLE IF EXISTS `crp_timeline_events`;
CREATE TABLE `crp_timeline_events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_date` timestamp NOT NULL ,
  `end_date` timestamp NOT NULL,
  `description` text,
  `responsible` text,
  `crp_id` bigint(20) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `active_since` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modification_justification` text,
  PRIMARY KEY (`id`),
  KEY `crp_timeline_created_fk` (`created_by`) USING BTREE,
  KEY `crp_timeline_modified_fk` (`modified_by`) USING BTREE,
  KEY `crp_timeline_crp_fk` (`crp_id`) USING BTREE,
  CONSTRAINT `crp_timeline_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`),
  CONSTRAINT `crp_timeline_ibfk_2` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`),
  CONSTRAINT `crp_timeline_ibfk_3` FOREIGN KEY (`crp_id`) REFERENCES `crps` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;