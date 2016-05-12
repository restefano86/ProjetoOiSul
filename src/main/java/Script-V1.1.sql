update produto set tpOferta = 'M' where idProduto < 7;
INSERT INTO produto (`codigoOi`, `vlPlano`, `dePlano`, `tpOferta`) VALUES ('3', '52.75', 'Oi Mais Celular Controle Top', 'M');
UPDATE produto SET `codigoOi`='4' WHERE `idProduto`='3';
UPDATE produto SET `codigoOi`='5' WHERE `idProduto`='4';
UPDATE produto SET `codigoOi`='6' WHERE `idProduto`='5';
DELETE FROM produto WHERE `idProduto`='6';