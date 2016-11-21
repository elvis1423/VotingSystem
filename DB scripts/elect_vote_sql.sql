CREATE TABLE `ft_vote_elect` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '投票主键',
  `name` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `may_choose_count` int(10) DEFAULT NULL COMMENT '可以投几次',
  `is_publish` int(10) DEFAULT NULL COMMENT '是否发布，0：未发布，1：已发布',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `ft_vote_elect_decision` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '投票决定',
  `c_name` varchar(255) DEFAULT NULL COMMENT '中文名称',
  `e_name` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `vote_elect_id` int(10) DEFAULT NULL COMMENT '投票主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `ft_vote_elect_option` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '投票选项主键',
  `name` varchar(100) DEFAULT NULL COMMENT '投票选项名称',
  `content` varchar(255) DEFAULT NULL COMMENT '投票选项内容',
  `img_url` varchar(255) DEFAULT NULL COMMENT '投票选项名称',
  `vote_elect_id` int(10) DEFAULT NULL COMMENT '投票主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `ft_vote_elect_result` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '投票结果主键',
  `vote_elect_id` int(10) DEFAULT NULL COMMENT '投票主键',
  `vote_elect_option_id` int(10) DEFAULT NULL COMMENT '投票选项主键',
  `vote_elect_result` varchar(255) DEFAULT NULL COMMENT '投票结果',
  `user_id` int(10) DEFAULT NULL COMMENT '用户主键',
  `voter_name` varchar(255) DEFAULT NULL COMMENT '投票人姓名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '投票时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
