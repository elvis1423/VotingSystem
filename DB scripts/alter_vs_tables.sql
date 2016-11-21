ALTER TABLE ft_vote_candidate ADD COLUMN seq int unsigned;
ALTER TABLE ft_vote_index ADD COLUMN seq int unsigned;
ALTER TABLE ft_vote_2nd_index ADD COLUMN seq int unsigned;

drop table ft_vote_grade;
create table `ft_vote_grade`(
 `id` int unsigned not null auto_increment,
 `user_id` int unsigned not null,
 `config_id` int unsigned not null,
 `candidate_id` int unsigned not null,
 `index_id` int unsigned not null,
 `second_index_id` int unsigned,
 `grade` float,
 `voter` varchar(200),
 primary key(`id`),
 unique key(`user_id`, `config_id`, `candidate_id`, `index_id`, `second_index_id`),
 index ft_vote_grade_idx1(user_id, config_id),
 constraint `fk_grade_user` foreign key (`user_id`) references `ft_vote_user` (`user_id`),
 constraint `fk_grade_config` foreign key (`config_id`) references `ft_vote_config` (`id`),
 constraint `fk_grade_candidate` foreign key (`candidate_id`) references `ft_vote_candidate` (`id`),
 constraint `fk_grade_index` foreign key (`index_id`) references `ft_vote_index` (`id`),
 constraint `fk_grade_second_index` foreign key (`second_index_id`) references `ft_vote_2nd_index` (`id`)
);

ALTER TABLE ft_vote_elect ADD COLUMN is_differential int unsigned COMMENT '是否差额投票，1：是；2：否';
ALTER TABLE ft_vote_elect ADD COLUMN start_time timestamp;
ALTER TABLE ft_vote_elect ADD COLUMN complete_time timestamp;
ALTER TABLE ft_vote_config change category is_deleted int(1) default 0;
ALTER TABLE ft_vote_elect ADD COLUMN is_anonymous int(1) default 1 COMMENT '是否匿名投票，1：是；0：否';