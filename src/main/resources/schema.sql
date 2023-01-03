DROP TABLE IF EXISTS `food`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `bbs`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `adr`;


CREATE TABLE `food`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `addr` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `price` varchar(255) DEFAULT NULL,
    `stock` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `bbs`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `title` varchar(255) DEFAULT NULL,
    `body` varchar(255) DEFAULT NULL,
    `date` varchar(255) DEFAULT NULL,
    `time` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
);

create table users(
      username varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(500) not null,
      enabled boolean not null
);
create table authorities (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
     username varchar_ignorecase(50) not null,
     authority varchar_ignorecase(50) not null,
     PRIMARY KEY (`id`),
     constraint fk_authorities_users foreign key(username)
         references users(username)
);

CREATE TABLE `cart`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) DEFAULT NULL,
    `food_id` bigint(20) DEFAULT NULL,
    `num` bigint(20)  DEFAULT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `adr`(
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `province` varchar(255) DEFAULT NULL,
   `city` varchar(255) DEFAULT NULL,
   `street` varchar(255) DEFAULT NULL,
   `user` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
);