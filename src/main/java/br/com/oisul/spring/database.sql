CREATE TABLE `person` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `ddd` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `isAtivado` varchar(1) DEFAULT 'N',
  `tpUsuario` varchar(1) NOT NULL DEFAULT 'B',
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) DEFAULT NULL,
  `deRazaoSocial` varchar(80) DEFAULT NULL,
  `nuCnpj` bigint(14) unsigned zerofill DEFAULT NULL,
  `nmRepLegal` varchar(80) DEFAULT NULL,
  `nuCpfRepLegal` bigint(11) unsigned zerofill DEFAULT NULL,
  `nuDiaVencimento` int(11) DEFAULT NULL,
  `deEndereco` varchar(80) DEFAULT NULL,
  `nmBairro` varchar(80) DEFAULT NULL,
  `nuCep` int(11) DEFAULT NULL,
  `nmMunicipio` varchar(80) DEFAULT NULL,
  `deUf` varchar(2) DEFAULT NULL,
  `nuDddFixo` int(2) unsigned zerofill DEFAULT NULL,
  `nuTelFixo` int(8) unsigned zerofill DEFAULT NULL,
  `nuDddCelular` int(2) unsigned zerofill DEFAULT NULL,
  `nuTelCelular` int(8) unsigned zerofill DEFAULT NULL,
  `deEmail` varchar(80) DEFAULT NULL,
  `deEnderecoCob` varchar(80) DEFAULT NULL,
  `nmBairroCob` varchar(80) DEFAULT NULL,
  `nmMunicipioCob` varchar(80) DEFAULT NULL,
  `deUfCob` varchar(2) DEFAULT NULL,
  `nuCepCob` int(8) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

CREATE TABLE `contato` (
  `idContato` int(11) NOT NULL AUTO_INCREMENT,
  `nmContato` varchar(45) DEFAULT NULL,
  `deTelefone` varchar(45) DEFAULT NULL,
  `deEmail` varchar(45) DEFAULT NULL,
  `deMensagem` text,
  `deProduto` varchar(45) DEFAULT NULL,
  `dtContato` datetime DEFAULT NULL,
  PRIMARY KEY (`idContato`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
