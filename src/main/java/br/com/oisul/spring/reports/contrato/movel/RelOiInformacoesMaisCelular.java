package br.com.oisul.spring.reports.contrato.movel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import br.com.oisul.spring.utils.DatabaseUtils;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RelOiInformacoesMaisCelular {
	
	@SuppressWarnings("rawtypes")
	public byte[] geraRelatorio(Integer idVenda){
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DatabaseUtils.getConnection();
			stmt= connection.createStatement();
			StringBuilder sql = new StringBuilder();
			sql.append(" select  ");
			sql.append(" consultor.nuCpf as cpfConsultor,  ");
			sql.append(" consultor.nome as nmConsultor,  ");
			sql.append(" empresa.nmreplegal as nmRepLegal,  ");
			sql.append(" empresa.nucpfreplegal as cpfRepLegal,  ");
			sql.append("DAY(now()) as dia, ");
			sql.append("MONTH(now()) as mes, ");
			sql.append("YEAR(now()) as ano ");
			sql.append(" from venda venda ");
			sql.append(" left join usuario consultor on venda.idconsultor  = consultor.idusuario ");
			sql.append(" left join empresa empresa on venda.idempresa = empresa.idempresa ");
			sql.append(" where venda.idVenda = "+idVenda);

			ResultSet rs = stmt.executeQuery(sql.toString());
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream is = this.getClass().getResourceAsStream("/br/com/oisul/spring/reports/contrato/movel/relOiInformacoesMaisCelular.jasper");
			
			JRResultSetDataSource relResult = new JRResultSetDataSource(rs);
			@SuppressWarnings("unchecked")
			JasperPrint jpPrint = JasperFillManager.fillReport(is, new HashMap(), relResult);
//			JasperExportManager.exportReportToPdfFile(jpPrint, "c:/java/relOiInformacoesMaisCelular"+(new Date()).getTime()+".pdf");
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
