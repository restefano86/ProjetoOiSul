package br.com.oisul.spring.reports.contrato;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import br.com.oisul.spring.utils.DatabaseUtils;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RelContrato {
	
	public void geraRelatorio(){
		
		try {
			Connection connection = DatabaseUtils.getConnection();
			Statement stmt= connection.createStatement();
			ResultSet rs = stmt.executeQuery("select empresa.deRazaoSocial from empresa as empresa");
//			while (rs.next()) {
//				System.out.println(rs.getString("idEmpresa"));
//			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream is = this.getClass().getResourceAsStream("/br/com/oisul/spring/reports/contrato/Blank_A4.jasper");
			
			JRResultSetDataSource relResult = new JRResultSetDataSource(rs);
			JasperPrint jpPrint = JasperFillManager.fillReport(is, new HashMap(), relResult);
			JasperExportManager.exportReportToPdfFile(jpPrint, "c:/java/t3.pdf");
//			JasperExportManager.exportReportToPdfStream(jpPrint, out);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
