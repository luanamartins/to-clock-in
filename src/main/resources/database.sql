CREATE TABLE `clock_in` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pis` varchar(20) NOT NULL,
  `clockin_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `work_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pis` varchar(20) NOT NULL,
  `work_date` datetime DEFAULT NULL,
  `work_hours` int(11) DEFAULT NULL,
  `work_minutes` int(11) DEFAULT NULL,
  `work_rest_minutes` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

