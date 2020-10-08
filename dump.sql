-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.3.11-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para case_bv
DROP DATABASE IF EXISTS `case_bv`;
CREATE DATABASE IF NOT EXISTS `case_bv` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `case_bv`;

-- Copiando estrutura para tabela case_bv.money_into_machine
DROP TABLE IF EXISTS `money_into_machine`;
CREATE TABLE IF NOT EXISTS `money_into_machine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(4,2) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `id_machine` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `price` (`price`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela case_bv.money_into_machine: ~7 rows (aproximadamente)
DELETE FROM `money_into_machine`;
/*!40000 ALTER TABLE `money_into_machine` DISABLE KEYS */;
INSERT INTO `money_into_machine` (`id`, `price`, `quantity`, `id_machine`) VALUES
	(1, 0.01, 100, 1),
	(2, 0.05, 100, 1),
	(3, 0.10, 100, 1),
	(4, 0.25, 100, 1),
	(5, 0.50, 100, 1),
	(6, 2.00, 100, 1),
	(7, 5.00, 100, 1),
	(8, 10.00, 100, 1);
/*!40000 ALTER TABLE `money_into_machine` ENABLE KEYS */;

-- Copiando estrutura para tabela case_bv.order
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL,
  `dt_creation` datetime NOT NULL DEFAULT current_timestamp(),
  `dt_update` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` varchar(10) NOT NULL,
  `price` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela case_bv.order: ~0 rows (aproximadamente)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Copiando estrutura para tabela case_bv.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `quantity` smallint(6) NOT NULL DEFAULT 0,
  `charge` decimal(5,2) NOT NULL,
  `id_machine` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela case_bv.product: ~4 rows (aproximadamente)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `name`, `quantity`, `charge`, `id_machine`) VALUES
	(1, 'CHOCOLATE', 100, 2.35, 1),
	(2, 'SANDUICHE', 100, 7.50, 1),
	(3, 'REFRIGERANTE', 100, 5.15, 1),
	(4, 'BISCOITO', 100, 2.90, 1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
