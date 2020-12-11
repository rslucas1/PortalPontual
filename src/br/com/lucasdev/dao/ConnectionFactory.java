package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnectionMySql() throws SQLException {

		String url = "jdbc:mysql://localhost/portal";
//		String url = "jdbc:mysql://192.168.16.100/portal";
		String usuario = "root";
		String senha = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// TESTE DE CONEXAO COM O BANCO NA CONSOLE
			System.out.println("(Conectado no banco via MY SQL)");

			return DriverManager.getConnection(url, usuario, senha);

		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);

		}
	}

	public Connection getConnectionSqlServer() throws SQLException {

//		String url = "jdbc:sqlserver://localhost:1433;databaseName=MOINHO";
		String url = "jdbc:sqlserver://192.168.16.3:1433;databaseName=MOINHO";
		String usuario = "sa";
		String senha = "moitgt2526";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// TESTE DE CONEXAO COM O BANCO NA CONSOLE
			System.out.println("(Conectado no SqlServer ERP)");

			return DriverManager.getConnection(url, usuario, senha);

		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);

		}
	}
	
	public Connection getConnectionTms() throws SQLException {
		System.out.println("(...TENTANDO CONECTAR NO TMS)");
//		String url = "jdbc:sqlserver://localhost:1433;databaseName=dtbTransporteTerrestre";
		String url = "jdbc:sqlserver://192.168.16.31:1433;databaseName=dtbTransporteTerrestre";
		String usuario = "sa";
//		String senha = "moitgt2526";
		String senha = "acesso@SA410";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// TESTE DE CONEXAO COM O BANCO NA CONSOLE
			System.out.println("(Conectado no SqlServer TMS)");

			return DriverManager.getConnection(url, usuario, senha);

		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);

		}
	}
	
	public Connection getConnectionOracle() throws SQLException {
//		System.out.println("(...TENTANDO CONECTAR NO ORACLE)");

		
		String url = "jdbc:oracle:thin:@192.168.16.32:1521:wmsprd";
		String usuario = "AG_CORE";

		String senha = "AG_CORE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// TESTE DE CONEXAO COM O BANCO NA CONSOLE
//			System.out.println("(Conectado no SqlServer ORACLE)");

			return DriverManager.getConnection(url, usuario, senha);

		} catch (ClassNotFoundException ex) {
			System.out.println("Erro ao obter conexão!\n" + ex.getMessage());
			throw new RuntimeException(ex);

		}
	}

}
