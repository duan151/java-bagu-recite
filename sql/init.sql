-- Java 八股网站 初始化脚本（MySQL 8）
CREATE DATABASE IF NOT EXISTS java_bagu DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE java_bagu;

-- 题目分类
CREATE TABLE IF NOT EXISTS category (
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    name        VARCHAR(64)     NOT NULL COMMENT '分类名称',
    sort        INT             NOT NULL DEFAULT 0 COMMENT '排序',
    create_time DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_name (name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='题目分类';

-- 面试题
CREATE TABLE IF NOT EXISTS question (
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    category_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '分类id',
    title       VARCHAR(255)    NOT NULL COMMENT '题目',
    content_md  TEXT            NULL COMMENT '题目补充说明(Markdown)',
    answer_md   MEDIUMTEXT      NULL COMMENT '答案(Markdown)',
    difficulty  TINYINT         NOT NULL DEFAULT 2 COMMENT '难度：1简单 2中等 3困难',
    starred     TINYINT         NOT NULL DEFAULT 0 COMMENT '是否收藏：0否 1是',
    mastered    TINYINT         NOT NULL DEFAULT 0 COMMENT '是否已掌握：0否 1是',
    create_time DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_category (category_id),
    KEY idx_title (title)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='面试题';

-- 复习记录（后续做随机抽题/复习打卡用）
CREATE TABLE IF NOT EXISTS review_log (
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    question_id BIGINT UNSIGNED NOT NULL COMMENT '题目id',
    result      TINYINT         NOT NULL DEFAULT 1 COMMENT '结果：1会 0不会',
    review_time DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '复习时间',
    PRIMARY KEY (id),
    KEY idx_question (question_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='复习记录';

-- 用户表
CREATE TABLE IF NOT EXISTS `user`
(
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    username        VARCHAR(64)     NOT NULL COMMENT '登录账号',
    password        VARCHAR(100)    NOT NULL COMMENT '密码（BCrypt 加密后的密文）',
    nickname        VARCHAR(64)     NOT NULL DEFAULT '' COMMENT '昵称',
    avatar          VARCHAR(255)    NOT NULL DEFAULT '' COMMENT '头像地址',
    role            TINYINT         NOT NULL DEFAULT 1 COMMENT '角色：1普通用户 2管理员',
    status          TINYINT         NOT NULL DEFAULT 1 COMMENT '状态：0删除 1正常 2拉黑',
    last_login_time DATETIME        NULL COMMENT '最后登录时间',
    create_time     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- 初始分类
INSERT INTO category (name, sort) VALUES
    ('Java基础', 1),
    ('集合', 2),
    ('并发', 3),
    ('JVM', 4),
    ('Spring', 5),
    ('MySQL', 6),
    ('Redis', 7),
    ('消息队列', 8),
    ('分布式', 9),
    ('设计模式', 10),
    ('项目', 11)
ON DUPLICATE KEY UPDATE sort = VALUES(sort);
