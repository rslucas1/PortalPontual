package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnectionMySql() throws SQLException {

		String url = "jdbc:mysql://localhost/portal";
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
			System.out.println("(Conectado no banco via SQLSERVER)");

			return DriverManager.getConnection(url, usuario, senha);

		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);

		}
	}

}
