CREATE TABLE `location_status` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `location` varchar(45) NOT NULL,
                                   PRIMARY KEY (`id`)
);
CREATE TABLE `payment_status` (
                                  `id` int NOT NULL,
                                  `status` varchar(45) NOT NULL,
                                  PRIMARY KEY (`id`)
);
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `role` varchar(45) NOT NULL,
                        PRIMARY KEY (`id`)
);
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `login` varchar(45) NOT NULL,
                        `password` varchar(200) NOT NULL,
                        `first_name` varchar(45) NOT NULL,
                        `last_name` varchar(45) NOT NULL,
                        `phone_number` varchar(45) DEFAULT NULL,
                        `email` varchar(45) NOT NULL,
                        `money` int DEFAULT NULL,
                        `role_id` int NOT NULL,
                        `notify` varchar(45) NOT NULL,
                        `ban` varchar(45) NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `user_ibfk_1` (`role_id`),
                        CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);
CREATE TABLE `order` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `description` varchar(1000) NOT NULL,
                         `weight` int NOT NULL,
                         `volume` int NOT NULL,
                         `price` int NOT NULL,
                         `city_from` varchar(45) NOT NULL,
                         `city_to` varchar(45) NOT NULL,
                         `address` varchar(100) NOT NULL,
                         `date_create` date NOT NULL,
                         `date_of_sending` date DEFAULT NULL,
                         `date_of_arrival` date NOT NULL,
                         `user_id` int NOT NULL,
                         `payment_status_id` int NOT NULL,
                         `location_status_id` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `user_id` (`user_id`),
                         KEY `order_ibfk_4` (`payment_status_id`),
                         KEY `location_status_id` (`location_status_id`),
                         CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                         CONSTRAINT `order_ibfk_4` FOREIGN KEY (`payment_status_id`) REFERENCES `payment_status` (`id`),
                         CONSTRAINT `order_ibfk_5` FOREIGN KEY (`location_status_id`) REFERENCES `location_status` (`id`)
);


CREATE TABLE `reviews` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `user_id` int NOT NULL,
                           `response` varchar(233) NOT NULL,
                           `date` date NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `user_id` (`user_id`),
                           CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);