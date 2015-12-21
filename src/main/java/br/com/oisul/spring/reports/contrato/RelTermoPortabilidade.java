package br.com.oisul.spring.reports.contrato;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.com.oisul.spring.utils.DatabaseUtils;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelTermoPortabilidade {
	
	@SuppressWarnings("rawtypes")
	public byte[] geraRelatorio(Integer idVenda){
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DatabaseUtils.getConnection();
			stmt= connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append(" select  ");
			sql.append(" CONCAT('P',perfilvenda.nuperfil) as dePerfil, ");
			sql.append(" perfilvenda.nuDDD as nuDDD, ");
			sql.append(" vendaitem.nuportabilidade as nuAcesso, ");
			sql.append(" operadora.nmoperadora as deDoadora, ");
			sql.append(" produto.codigoOi as dePlanoDestino, ");
			sql.append(" 1 as codServico, ");
			sql.append(" DAY(now()) as dia, ");
			sql.append(" MONTH(now()) as mes, ");
			sql.append(" YEAR(now()) as ano, ");
			sql.append(" empresa.nmreplegal as nmRepLegal,  ");
			sql.append(" empresa.nucpfreplegal as cpfRepLegal ");
			sql.append(" from vendaitem vendaitem ");
			sql.append(" inner join venda venda on vendaitem.idvenda = venda.idvenda ");
			sql.append(" inner join empresa empresa on venda.idempresa = empresa.idempresa ");
			sql.append(" inner join perfilvenda perfilvenda on vendaitem.nuddd = perfilvenda.nuddd and vendaitem.fltipochip = perfilvenda.fltipochip and vendaitem.idproduto = perfilvenda.idproduto and perfilvenda.idvenda = vendaitem.idvenda ");
			sql.append(" inner join operadora operadora on vendaitem.idoperadora = operadora.idoperadora ");
			sql.append(" inner join produto produto on vendaitem.idproduto = produto.idproduto ");
			sql.append(" where vendaitem.nuportabilidade is not null ");
			sql.append(" and vendaitem.idvenda = "+idVenda);
			
			ResultSet rs = stmt.executeQuery(sql.toString());
			
			List<RelPortabilidadeDTO> listaPortabilidades = new ArrayList<RelPortabilidadeDTO>();
			RelPortabilidadeDTO relPortabilidadeDTO = new RelPortabilidadeDTO();
			
			
			
			
			while (rs.next()) {
				relPortabilidadeDTO = new RelPortabilidadeDTO();
				relPortabilidadeDTO.setDePerfil(rs.getString("dePerfil"));
				relPortabilidadeDTO.setNuDDD(rs.getString("nuDDD"));
				relPortabilidadeDTO.setNuAcesso(rs.getString("nuAcesso"));
				relPortabilidadeDTO.setDeDoadora(rs.getString("deDoadora"));
				relPortabilidadeDTO.setDePlanoDestino(rs.getString("dePlanoDestino"));
				relPortabilidadeDTO.setCodServico(rs.getString("codServico"));
				relPortabilidadeDTO.setDia(rs.getString("dia"));
				relPortabilidadeDTO.setMes(rs.getString("mes"));
				relPortabilidadeDTO.setAno(rs.getString("ano"));
				relPortabilidadeDTO.setNmRepLegal(rs.getString("nmRepLegal"));
				relPortabilidadeDTO.setCpfRepLegal(rs.getString("cpfRepLegal"));
				
				listaPortabilidades.add(relPortabilidadeDTO);
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream is = this.getClass().getResourceAsStream("/br/com/oisul/spring/reports/contrato/relTermoPortabilidade.jasper");
			
			RelPortabilidadeDTO dtoFake = new RelPortabilidadeDTO();
			dtoFake.setDia(relPortabilidadeDTO.getDia());
			dtoFake.setMes(relPortabilidadeDTO.getMes());
			dtoFake.setAno(relPortabilidadeDTO.getAno());
			dtoFake.setNmRepLegal(relPortabilidadeDTO.getNmRepLegal());
			dtoFake.setCpfRepLegal(relPortabilidadeDTO.getCpfRepLegal());
			
			//completa a tabela com linhas vazias
			while (listaPortabilidades.size() < 30) {
				listaPortabilidades.add(dtoFake);
			}
			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaPortabilidades);
			
			@SuppressWarnings("unchecked")
			JasperPrint jpPrint = JasperFillManager.fillReport(is, new HashMap(), ds);
//			JasperExportManager.exportReportToPdfFile(jpPrint, "c:/java/relTermoPortabilidade"+(new Date()).getTime()+".pdf");
			JasperExportManager.exportReportToPdfStream(jpPrint, out);
			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { connection.close();} catch (SQLException e) {}
			try { stmt.close(); } catch (SQLException e) {}
		}
		return null;
		
		
		
	}
	
	
}
