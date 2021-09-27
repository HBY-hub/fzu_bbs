drop database bbs ;
create database bbs;
use bbs;
CREATE TABLE `tb_user` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `user_name` varchar(20) NOT NULL COMMENT '用户名',
                           `password` varchar(20) NOT NULL COMMENT '密码',
                           `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
                           `age` int(11) DEFAULT NULL COMMENT '年龄',
                           `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                           `phone` varchar(50) DEFAULT NULL COMMENT '手机',
                           `academy` varchar(50) DEFAULT NULL COMMENT '学院',
                           `avatar` varchar(100) DEFAULT NULL comment '头像url',
                           `face_data` json DEFAULT NULL comment '脸部数据',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `tb_passage` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `title` varchar(255) NOT NULL COMMENT '标题',
                           `description` varchar(255) NOT NULL COMMENT '描述',
                           `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
                           `content` text DEFAULT NULL COMMENT '内容',
                           `user_name` varchar(50) DEFAULT NULL COMMENT '发帖人',
                           `read_num` int(11) DEFAULT 0 COMMENT '阅读量',
                           `like_num` int(11) DEFAULT 0 comment '点赞数',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `tb_theme` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                              `theme` varchar(255) NOT NULL COMMENT '主题名',
                              `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `tb_academy` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                            `academy` varchar(255) NOT NULL COMMENT '学院名',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `tb_comment` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                              `user_name` varchar(255) NOT NULL COMMENT '学院名',
                              `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `content` varchar(255) NOT NULL comment '评论内容',
                              `passage` int(11) not null comment '所属文章',
                              `father` int(11) default 0 comment '父id',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `tb_follow` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                              `from` varchar(255) NOT NULL COMMENT '关注人',
                              `from_id` bigint NOT NULL COMMENT '关注人id',
                              `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `to` varchar(255) NOT NULL comment '被关注人',
                              `to_id` bigint NOT NULL comment '被关注人id',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `tb_passage_theme` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                             `theme` varchar(255) NOT NULL COMMENT '主题',
                             `theme_id` bigint NOT NULL COMMENT '主题id',
                             `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `passage` varchar(255) NOT NULL comment '文章',
                             `passage_id` bigint NOT NULL comment '文章id',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `tb_user` (`id`, `user_name`, `password`,  `age`, `email`,phone,academy) VALUES
    ('1', 'zhangsan', '123456', '18', 'test1@itcast.cn','111111111','计算机学院');

INSERT INTO `tb_user` ( `user_name`, `password`,  `age`, `email`,phone,academy) VALUES
    ( 'lisi', '123456', '182', 'test1@dsfljitcast.cn','11dsfs1111111','计算机学院');

INSERT INTO `tb_passage` (`id`,`title` ,`user_name`, `description`,  `content`) VALUES
    ('1','here is a title', 'zhangsan', 'here is description', 'here is content');
INSERT INTO `tb_passage` (`title` ,`user_name`, `description`,  `content`) VALUES
    ('here is a title', 'lisi', 'here is description', 'here is content');
INSERT INTO `tb_theme` (`id`,  `theme`) VALUES
    ('1', '失物招领');
INSERT INTO `tb_academy` (`id`,  `academy`) VALUES
    ('1', '计算机学院');
INSERT INTO `tb_comment` (`id`,  `user_name`, `content`,`passage`) VALUES
    ('1', 'zhangsan','here is a comment',1);
INSERT INTO `tb_follow` (`id`,  `from`,`from_id`,`to`,`to_id`) VALUES
    ('1', 'zhangans','1','lisi','2');
INSERT INTO `tb_follow` (  `from`,`from_id`,`to`,`to_id`) VALUES
    ('lisi','1', 'zhangans','2');
INSERT INTO `tb_passage_theme` ( `id`,`passage`,`passage_id`,`theme`,`theme_id`) VALUES
    ('1','here id a title','1','失物招领','1');


-- 修改一下两个id值的属性，统一为Integer
USE bbs
ALTER TABLE `tb_comment` MODIFY `passage` BIGINT(20)
ALTER TABLE `tb_comment` MODIFY `father` BIGINT(20)

-- 插一些数据
-- user
INSERT INTO `bbs`.`tb_user` (`user_name`, `password`, `age`, `email`, `phone`, `academy`) VALUES ('lisa', '654321', '19', 'abc@qq.com', '123321231', '土木工程学院');
INSERT INTO `bbs`.`tb_user` (`USER_nAME`, `password`, `age`, `email`, `phone`, `academy`) VALUES ('lisay', '666666', '21', 'Absn@qq.com', '122132341', '土木工程学院');
INSERT INTO `bbs`.`tb_user` (`user_name`, `password`, `age`, `EMaIL`, `phone`, `academy`) VALUES ('slisa', '434434', '22', 'dfsd@qq.com', '134324234', '电气学院');
INSERT INTO `bbs`.`tb_user` (`user_name`, `passwORD`, `agE`, `email`, `PHONe`, `academy`) VALUES ('slisary', '222222', '17', 'Rwydsuy@qq.com', '231321321', '计算机学院');

-- passage
INSERT INTO `bbs`.`tb_passage` (`title`, `description`, `user_name`) VALUES ('母猪产后护理', '论母猪产后护理', 'lisa');
INSERT INTO `bbs`.`tb_passage` (`title`, `description`, `user_name`) VALUES ('黑暗料理之道', 'aaaaaa', 'lisay');
INSERT INTO `bbs`.`tb_passage` (`title`, `description`, `user_name`) VALUES ('母狗产后护理', 'bbbbb', 'lisa');

-- comment
INSERT INTO `bbs`.`tb_comment` (`user_name`, `content`, `passage`, `father`) VALUES ('lisa', 'aaaaa', '1', '1');
INSERT INTO `BBS`.`tb_comment` (`user_name`, `content`, `pASSAGE`, `Father`) VALUES ('lsl', 'bbbbb', '1', '2');