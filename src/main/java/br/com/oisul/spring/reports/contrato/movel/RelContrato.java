package br.com.oisul.spring.reports.contrato.movel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import br.com.oisul.spring.utils.DatabaseUtils;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RelContrato {
	
	@SuppressWarnings("rawtypes")
	public byte[] geraRelatorio(Integer idVenda){
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DatabaseUtils.getConnection();
			stmt= connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT  ");
			sql.append("venda.idUsuario, ");
			sql.append("empresa.derazaosocial, ");
			sql.append("empresa.nucnpj, ");
			sql.append("empresa.deendereco, ");
			sql.append("empresa.nmbairro, ");
			sql.append("empresa.nucep, ");
			sql.append("empresa.nmmunicipio, ");
			sql.append("empresa.deuf, ");
			sql.append("IF(empresa.deenderecocob is null, 'X', '') as cobMesmoReceita, ");
			sql.append("IF(empresa.deenderecocob is null, '', 'X') as cobDifReceita, ");
			sql.append("empresa.deenderecocob, ");
			sql.append("empresa.nmbairrocob, ");
			sql.append("empresa.nmmunicipiocob, ");
			sql.append("empresa.deufcob, ");
			sql.append("empresa.nucepcob, ");
			sql.append("empresa.nmgestorconta, ");
			sql.append("empresa.nucpfgestorconta, ");
			sql.append("empresa.deemailgestorconta, ");
			sql.append("empresa.nudddfixo, ");
			sql.append("empresa.nutelfixo, ");
			sql.append("empresa.nudddcelular, ");
			sql.append("empresa.nutelcelular, ");
			sql.append("empresa.nmreplegal, ");
			sql.append("empresa.nucpfreplegal, ");
			sql.append("produtoPerfil1.codigoOi as p1Codigo, ");
			sql.append("perfil1.nuddd as p1Ddd, ");
			sql.append("perfil1.qtacessos as p1QtAcessos, ");
			sql.append("produtoPerfil1.vlPlano as p1VlTotal, ");
			sql.append("tipochipPerfil1.decontrato as p1TipoChip, ");
			sql.append("produtoPerfil2.codigoOi as p2Codigo, ");
			sql.append("perfil2.nuddd as p2Ddd, ");
			sql.append("perfil2.qtacessos as p2QtAcessos, ");
			sql.append("produtoPerfil2.vlPlano as p2VlTotal, ");
			sql.append("tipochipPerfil2.decontrato as p2TipoChip, ");
			sql.append("produtoPerfil3.codigoOi as p3Codigo, ");
			sql.append("perfil3.nuddd as p3Ddd, ");
			sql.append("perfil3.qtacessos as p3QtAcessos, ");
			sql.append("produtoPerfil3.vlPlano as p3VlTotal, ");
			sql.append("tipochipPerfil3.decontrato as p3TipoChip, ");
			sql.append("produtoPerfil4.codigoOi as p4Codigo, ");
			sql.append("perfil4.nuddd as p4Ddd, ");
			sql.append("perfil4.qtacessos as p4QtAcessos, ");
			sql.append("produtoPerfil4.vlPlano as p4VlTotal, ");
			sql.append("tipochipPerfil4.decontrato as p4TipoChip, ");
			sql.append("produtoPerfil5.codigoOi as p5Codigo, ");
			sql.append("perfil5.nuddd as p5Ddd, ");
			sql.append("perfil5.qtacessos as p5QtAcessos, ");
			sql.append("produtoPerfil5.vlPlano as p5VlTotal, ");
			sql.append("tipochipPerfil5.decontrato as p5TipoChip, ");
			sql.append("produtoPerfil6.codigoOi as p6Codigo, ");
			sql.append("perfil6.nuddd as p6Ddd, ");
			sql.append("perfil6.qtacessos as p6QtAcessos, ");
			sql.append("produtoPerfil6.vlPlano as p6VlTotal, ");
			sql.append("tipochipPerfil6.decontrato as p6TipoChip, ");
			sql.append("produtoPerfil1.vlPlano, ");
			sql.append("(select count(*) from vendaitem where vendaitem.idvenda = venda.idvenda) as totalAcessos, ");
			sql.append("(select count(*) from vendaitem where vendaitem.idvenda = venda.idvenda and vendaitem.flPortabilidade='S') as qtAcessosPortados, ");
			sql.append("IF((select count(*) from vendaitem where vendaitem.idvenda = venda.idvenda and vendaitem.flPortabilidade='S') > 0, 'X', '') as possuiAcessosPortados, ");
			sql.append("IF((select count(*) from vendaitem where vendaitem.idvenda = venda.idvenda and vendaitem.flPortabilidade='S') > 0, '', 'X') as naoPossuiAcessosPortados, ");
			sql.append("IF(empresa.nudiavencimento = '1', 'X', '') as vencimento1, ");
			sql.append("IF(empresa.nudiavencimento = '4', 'X', '') as vencimento4, ");
			sql.append("IF(empresa.nudiavencimento = '10', 'X', '') as vencimento10, ");
			sql.append("IF(empresa.nudiavencimento = '14', 'X', '') as vencimento14, ");
			sql.append("IF(perfil1.nuddd > 0, 'R$1,00', '') as p1VlChip, ");
			sql.append("IF(perfil1.nuddd > 0, 'À Vista', '') as p1FormaPagto, ");
			sql.append("IF(perfil2.nuddd > 0, 'R$1,00', '') as p2VlChip, ");
			sql.append("IF(perfil2.nuddd > 0, 'À Vista', '') as p2FormaPagto, ");
			sql.append("IF(perfil3.nuddd > 0, 'R$1,00', '') as p3VlChip, ");
			sql.append("IF(perfil3.nuddd > 0, 'À Vista', '') as p3FormaPagto, ");
			sql.append("IF(perfil4.nuddd > 0, 'R$1,00', '') as p4VlChip, ");
			sql.append("IF(perfil4.nuddd > 0, 'À Vista', '') as p4FormaPagto, ");
			sql.append("IF(perfil5.nuddd > 0, 'R$1,00', '') as p5VlChip, ");
			sql.append("IF(perfil5.nuddd > 0, 'À Vista', '') as p5FormaPagto, ");
			sql.append("IF(perfil6.nuddd > 0, 'R$1,00', '') as p6VlChip, ");
			sql.append("IF(perfil6.nuddd > 0, 'À Vista', '') as p6FormaPagto,  ");
			sql.append("DAY(now()) as dia, ");
			sql.append("MONTH(now()) as mes, ");
			sql.append("YEAR(now()) as ano ");
			sql.append("FROM venda venda ");
			sql.append("inner join empresa empresa on venda.idempresa = empresa.idempresa  ");
			sql.append("left join perfilvenda perfil1 on venda.idvenda = perfil1.idvenda and perfil1.nuperfil = 1 "); 
			sql.append("left join produto produtoPerfil1 on perfil1.idproduto = produtoPerfil1.idproduto ");
			sql.append("left join tipochip tipochipPerfil1 on perfil1.fltipochip = tipochipPerfil1.fltipochip ");
			sql.append("left join perfilvenda perfil2 on venda.idvenda = perfil2.idvenda and perfil2.nuperfil = 2  ");
			sql.append("left join produto produtoPerfil2 on perfil2.idproduto = produtoPerfil2.idproduto ");
			sql.append("left join tipochip tipochipPerfil2 on perfil2.fltipochip = tipochipPerfil2.fltipochip ");
			sql.append("left join perfilvenda perfil3 on venda.idvenda = perfil3.idvenda and perfil3.nuperfil = 3 ");
			sql.append("left join produto produtoPerfil3 on perfil3.idproduto = produtoPerfil3.idproduto ");
			sql.append("left join tipochip tipochipPerfil3 on perfil3.fltipochip = tipochipPerfil3.fltipochip ");
			sql.append("left join perfilvenda perfil4 on venda.idvenda = perfil4.idvenda and perfil4.nuperfil = 4 ");
			sql.append("left join produto produtoPerfil4 on perfil4.idproduto = produtoPerfil4.idproduto ");
			sql.append("left join tipochip tipochipPerfil4 on perfil4.fltipochip = tipochipPerfil4.fltipochip ");
			sql.append("left join perfilvenda perfil5 on venda.idvenda = perfil5.idvenda and perfil5.nuperfil = 5  ");
			sql.append("left join produto produtoPerfil5 on perfil5.idproduto = produtoPerfil5.idproduto ");
			sql.append("left join tipochip tipochipPerfil5 on perfil5.fltipochip = tipochipPerfil5.fltipochip ");
			sql.append("left join perfilvenda perfil6 on venda.idvenda = perfil6.idvenda and perfil6.nuperfil = 6  ");
			sql.append("left join produto produtoPerfil6 on perfil6.idproduto = produtoPerfil6.idproduto ");
			sql.append("left join tipochip tipochipPerfil6 on perfil6.fltipochip = tipochipPerfil6.fltipochip ");
			sql.append("where venda.idvenda = "+idVenda);
			
			ResultSet rs = stmt.executeQuery(sql.toString());
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream is = this.getClass().getResourceAsStream("/br/com/oisul/spring/reports/contrato/movel/relContratoMovel.jasper");
			
			JRResultSetDataSource relResult = new JRResultSetDataSource(rs);
			@SuppressWarnings("unchecked")
			JasperPrint jpPrint = JasperFillManager.fillReport(is, new HashMap(), relResult);
//			JasperExportManager.exportReportToPdfFile(jpPrint, "c:/java/relContratoMovel"+(new Date()).getTime()+".pdf");
			JasperExportManager.exportReportToPdfStream(jpPrint, out);
			return out.toByteArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { connection.close();} catch (SQLException e) {}
			try { stmt.close(); } catch (SQLException e) {}
		}
		return null;
		
		
		
	}
	
	
}
