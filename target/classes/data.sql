INSERT INTO `food` VALUES ('1', '../../img/0001.jpeg', '香蕉', '2', '88');
INSERT INTO `food` VALUES ('2', '../../img/0002.jpeg', '苹果', '3', '23');
INSERT INTO `food` VALUES ('3', '../../img/0003.jpeg', '橙子', '5', '1000');
INSERT INTO `users` VALUES ('admin', '$2a$10$19OTdMBal7M.sHw642Ayz.yK02JKyBOE1bwTeXbQHiROmHLrnn9Zm',1);
INSERT INTO `authorities` VALUES (1,'admin', 'ROLE_ADMIN');
INSERT INTO `users` VALUES ('user', '$2a$10$xAwa3A/Dan0.h5QGJGcP0.5L1Y825TUpwUIh.pc9kZUv7vseHGxK2',2);
INSERT INTO `authorities` VALUES (2,'user', 'ROLE_USER');
INSERT INTO `bbs` VALUES (1, '公告', 'BUPT订餐系统在2022年12月30日正式上线！', '2022/12/30', '08:00');
INSERT INTO `bbs` VALUES (2, '限时优惠', '即日起到2023年1月7日，在本系统订餐可享8折优惠！', '2022/12/30', '12:00');
INSERT INTO `cart` VALUES (1, 'user', 2, 10);
INSERT INTO `cart` VALUES (2, 'user', 3, 6);

