package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCubagem {
	
	private Connection connectionOracle;
	
	public JdbcCubagem() {
		try {
			System.out.println("INICIANDO CONSTRUTOR ORACLE");
			connectionOracle = new ConnectionFactory().getConnectionOracle();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
				
	}
	
	public void CalculaPMG() {
		System.out.println("ABRINDO EXEC");
		
		try {
			
			
			
			PreparedStatement stmt = this.connectionOracle.prepareStatement("SELECT SITE, NOTA, USUARIO FROM DASH_PCP WHERE USUARIO='AR444' AND NOTA='607'");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("OK PORRA");
				System.out.println(rs.getString(1) +" " +rs.getString(2));
				
				
			}
			
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		
		
		
	}
	
	

}

