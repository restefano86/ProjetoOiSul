CREATE TABLE `contato` (
  `idContato` int(11) NOT NULL AUTO_INCREMENT,
  `nmContato` varchar(45) DEFAULT NULL,
  `deTelefone` varchar(45) DEFAULT NULL,
  `deEmail` varchar(45) DEFAULT NULL,
  `deMensagem` text,
  `deProduto` varchar(45) DEFAULT NULL,
  `dtContato` datetime DEFAULT NULL,
  PRIMARY KEY (`idContato`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `empresa` (
  `idempresa` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `derazaosocial` varchar(80) DEFAULT NULL,
  `nucnpj` bigint(14) unsigned zerofill DEFAULT NULL,
  `nmreplegal` varchar(80) DEFAULT NULL,
  `nucpfreplegal` bigint(11) unsigned zerofill DEFAULT NULL,
  `nudiavencimento` int(11) DEFAULT NULL,
  `deendereco` varchar(80) DEFAULT NULL,
  `nmbairro` varchar(80) DEFAULT NULL,
  `nucep` int(11) DEFAULT NULL,
  `nmmunicipio` varchar(80) DEFAULT NULL,
  `deuf` varchar(2) DEFAULT NULL,
  `nudddfixo` int(2) unsigned zerofill DEFAULT NULL,
  `nutelfixo` int(8) unsigned zerofill DEFAULT NULL,
  `nudddcelular` int(2) unsigned zerofill DEFAULT NULL,
  `nutelcelular` int(8) unsigned zerofill DEFAULT NULL,
  `deemail` varchar(80) DEFAULT NULL,
  `deenderecocob` varchar(80) DEFAULT NULL,
  `nmbairrocob` varchar(80) DEFAULT NULL,
  `nmmunicipiocob` varchar(80) DEFAULT NULL,
  `deufcob` varchar(2) DEFAULT NULL,
  `nucepcob` int(8) unsigned zerofill DEFAULT NULL,
  `nmgestorconta` varchar(80) DEFAULT NULL,
  `nucpfgestorconta` bigint(11) DEFAULT NULL,
  `deemailgestorconta` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idempresa`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

CREATE TABLE `operadora` (
  `idoperadora` int(11) NOT NULL AUTO_INCREMENT,
  `nmoperadora` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idoperadora`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `person` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT,
  `codigoOi` int(11) DEFAULT NULL,
  `vlPlano` float DEFAULT NULL,
  `dePlano` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='		';

CREATE TABLE `situacaovenda` (
  `idsituacaovenda` int(11) NOT NULL,
  `nmsituacao` varchar(45) DEFAULT NULL,
  `desituacao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idsituacaovenda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `ddd` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `isAtivado` varchar(1) DEFAULT 'N',
  `tpUsuario` varchar(1) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

CREATE TABLE `venda` (
  `idvenda` int(11) NOT NULL AUTO_INCREMENT,
  `idconsultor` int(11) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `idempresa` int(11) DEFAULT NULL,
  `dtcadastro` datetime DEFAULT NULL,
  `dtcontratogerado` datetime NOT NULL,
  `dtcontratoenviado` datetime DEFAULT NULL,
  `idsituacao` int(11) DEFAULT NULL,
  `tpvenda` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idvenda`,`dtcontratogerado`),
  KEY `fk_venda_usuario_idx` (`idusuario`),
  KEY `fo_venda_consultor_idx` (`idconsultor`),
  KEY `fk_venda_empresa_idx` (`idempresa`),
  KEY `fk_venda_situacao_idx` (`idsituacao`),
  CONSTRAINT `fk_venda_consultor` FOREIGN KEY (`idconsultor`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_empresa` FOREIGN KEY (`idempresa`) REFERENCES `empresa` (`idempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_situacao` FOREIGN KEY (`idsituacao`) REFERENCES `situacaovenda` (`idsituacaovenda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';

CREATE TABLE `vendaitem` (
  `idvendaitem` int(11) NOT NULL AUTO_INCREMENT,
  `idvenda` int(11) DEFAULT NULL,
  `idproduto` int(11) DEFAULT NULL,
  `nuddd` int(11) DEFAULT NULL,
  `fltipochip` varchar(1) DEFAULT NULL,
  `flportabilidade` varchar(1) DEFAULT NULL,
  `nuportabilidade` int(11) DEFAULT NULL,
  `idoperadora` int(11) DEFAULT NULL,
  PRIMARY KEY (`idvendaitem`),
  KEY `fk_vendaitem_venda_idx` (`idvenda`),
  KEY `fk_vendaitem_produto_idx` (`idproduto`),
  KEY `fk_vendaitem_operadora_idx` (`idoperadora`),
  CONSTRAINT `fk_vendaitem_operadora` FOREIGN KEY (`idoperadora`) REFERENCES `operadora` (`idoperadora`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendaitem_produto` FOREIGN KEY (`idproduto`) REFERENCES `produto` (`idProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendaitem_venda` FOREIGN KEY (`idvenda`) REFERENCES `venda` (`idvenda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `projetooisul`.`produto` (`codigoOi`, `vlPlano`, `dePlano`) VALUES (1, '1', '19.22', 'Oi Mais Celular Controle');
INSERT INTO `projetooisul`.`produto` (`codigoOi`, `vlPlano`, `dePlano`) VALUES (2, '2', '39.22', 'Oi Mais Celular Controle Avançado');
INSERT INTO `projetooisul`.`produto` (`codigoOi`, `vlPlano`, `dePlano`) VALUES (3, '3', '89.00', 'Oi Mais Celular');
INSERT INTO `projetooisul`.`produto` (`codigoOi`, `vlPlano`, `dePlano`) VALUES (4, '4', '129.00', 'Oi Mais Celular Avançado');
INSERT INTO `projetooisul`.`produto` (`codigoOi`, `vlPlano`, `dePlano`) VALUES (5, '5', '349.00', 'Oi Mais Celular Top');
