/* 用户：默认初始化 */
INSERT INTO user (id, user_id_id, email, password, username, nickname)
VALUES (1, 'iaa-user-00000000', 'rottokaka@gmail.com', '$2a$10$qP7vrw8qQvayDTiX2v5rkOeX9d5pvEVwpkwJpuV2D/7DLxh3KZxV6', 'root', 'Root');
INSERT INTO user (id, user_id_id, email, password, username, nickname)
VALUES (2, 'iaa-user-00000001','rottokaka@gmail.com', '$2a$10$eovUYH5nYB2xlL6KKyoVqO5eql.ZqUekoAv3XmLHpwB8Zx42QmqKm', 'user', 'User');
INSERT INTO user (id, user_id_id, email, password, username, nickname)
VALUES (3, 'iaa-user-00000002', 'rottokaka@gmail.com', '$2a$10$A/e1Ch6VYuWA0SyYct8SH.rBmO.9phWOJ/xztU2WoqNkhVvSFliCK', 'guest', 'Guest');


/* 权限：默认初始化 */
INSERT INTO authority (id, name)
VALUES (1, 'ROLE_ROOT');
INSERT INTO authority (id, name)
VALUES (2, 'ROLE_USER');

/* 关系：用户-权限初始化 */
INSERT INTO user_authority (user_id, authority_id)
VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id)
VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id)
VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id)
VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id)
VALUES (3, 1);
INSERT INTO user_authority (user_id, authority_id)
VALUES (3, 2);
