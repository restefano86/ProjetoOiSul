package br.com.oisul.spring.utils;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseUtils {

	public static Connection getConnection(){
		InitialContext context;
		Connection connection = null;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/MySQLDS");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
}
