DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`(
    `addr` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `date` varchar(255) DEFAULT NULL,
    `stock` varchar(255) DEFAULT NULL,
PRIMARY KEY (`name`)
);
