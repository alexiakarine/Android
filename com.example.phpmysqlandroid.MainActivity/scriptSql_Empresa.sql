drop database if exists empresa;
Create database empresa;
use empresa;

CREATE TABLE IF NOT EXISTS `contas` (
  `idcontas` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcontas`)
) ENGINE=InnoDB;