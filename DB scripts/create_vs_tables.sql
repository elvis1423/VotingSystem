create table `ft_vote_user`(
`user_id` int unsigned NOT NULL auto_increment,
`user_name` varchar(50),
`user_pw` varchar(50),
`user_ip` varchar(50),
 PRIMARY KEY (`user_id`));

create table `ft_vote_config`(
`id` int unsigned NOT NULL auto_increment,
`category` int(1) default 0,
`name` varchar(200),
`start_date` datetime,
`complete_date` datetime,
`is_active` int(1) default 1,
 PRIMARY KEY (`id`));
 
create table `ft_vote_candidate`(
`id` int unsigned NOT NULL auto_increment,
`config_id` int unsigned not null,
`candidate_name` varchar(200) not null,
primary key(`id`),
INDEX (`config_id`),
CONSTRAINT `fk_candidate_config` FOREIGN KEY (`config_id`) REFERENCES `ft_vote_config` (`id`));

create table `ft_vote_index`(
`id` int unsigned NOT NULL auto_increment,
`config_id` int unsigned not null,
`index_weight` float,
`index_name` varchar(200),
primary key(`id`),
INDEX (`config_id`),
CONSTRAINT `fk_index_config` FOREIGN KEY (`config_id`) REFERENCES `ft_vote_config` (`id`));

create table `ft_vote_2nd_index`(
`id` int unsigned NOT NULL auto_increment,
`first_index_id` int unsigned not null,
`index_weight` float,
`index_name` varchar(200),
primary key(`id`),
INDEX (`first_index_id`),
CONSTRAINT `fk_2ndindex_index` FOREIGN KEY (`first_index_id`) REFERENCES `ft_vote_index` (`id`));

create table `ft_vote_elect`(
 `user_id` int unsigned not null,
 `config_id` int unsigned not null,
 `candidate_id` int unsigned not null,
 `statistics` varchar(20) not null,
 `voter` varchar(200),
 primary key(`user_id`,`config_id`),
 index ft_vote_elect_idx1(user_id, config_id),
 constraint `fk_elect_user` foreign key (`user_id`) references `ft_vote_user` (`user_id`),
 constraint `fk_elect_config` foreign key (`config_id`) references `ft_vote_config` (`id`),
 constraint `fk_elect_candidate` foreign key (`candidate_id`) references `ft_vote_candidate` (`id`)
 );
 
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

