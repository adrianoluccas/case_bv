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
CREATE DATABASE IF NOT EXISTS `case_bv` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `case_bv`;

-- Copiando estrutura para tabela case_bv.money_into_machine
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
	(1, 0.01, 0, 1),
	(2, 0.05, 0, 1),
	(3, 0.10, 0, 1),
	(4, 0.25, 0, 1),
	(5, 0.50, 0, 1),
	(6, 2.00, 0, 1),
	(7, 5.00, 0, 1),
	(8, 10.00, 0, 1);
/*!40000 ALTER TABLE `money_into_machine` ENABLE KEYS */;

-- Copiando estrutura para tabela case_bv.order_payment
CREATE TABLE IF NOT EXISTS `order_payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_order` int(11) NOT NULL,
  `dt_creation` datetime DEFAULT current_timestamp(),
  `dt_update` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` varchar(20) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `id_machine` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela case_bv.order_payment: ~47 rows (aproximadamente)
DELETE FROM `order_payment`;
/*!40000 ALTER TABLE `order_payment` DISABLE KEYS */;
INSERT INTO `order_payment` (`id`, `id_order`, `dt_creation`, `dt_update`, `status`, `price`, `id_machine`) VALUES
	(49, 12, '2020-10-12 23:49:33', '2020-10-12 23:49:33', 'OK', 10.00, 1),
	(50, 12, '2020-10-12 23:50:14', '2020-10-12 23:50:14', 'CHANGEBACK_QTT_1', -5.00, 1),
	(51, 12, '2020-10-12 23:50:14', '2020-10-12 23:50:14', 'CHANGEBACK_QTT_1', -2.00, 1),
	(52, 12, '2020-10-12 23:50:14', '2020-10-12 23:50:14', 'CHANGEBACK_QTT_1', -0.10, 1);
/*!40000 ALTER TABLE `order_payment` ENABLE KEYS */;

-- Copiando estrutura para tabela case_bv.product
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
	(1, 'CHOCOLATE', 0, 2.35, 1),
	(2, 'SANDUICHE', 0, 7.50, 1),
	(3, 'REFRIGERANTE', 0, 5.15, 1),
	(4, 'BISCOITO', 0, 2.90, 1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
