package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.Industria;
import br.com.lucasdev.modelo.relatorios.Segmento;

public class JdbcTradeIn {

	private Connection connection;
	
	public JdbcTradeIn() {
		
		try {
			connection = new ConnectionFactory().getConnectionMySql();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	
	public List<Industria> getIndustria(String cod_industria){
		List<Industria> industrias = new ArrayList<>();
		String sql;
		if(cod_industria==null) {
		sql ="SELECT cod_industria, desc_industria from industria where ativo=1";
		}else {
			sql ="SELECT cod_industria, desc_industria from industria where ativo=1 and cod_industria='"+cod_industria+"'";
			
		}
		
		try {
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Industria registro = new Industria();
				
				registro.setDesc_industria(rs.getString("desc_industria"));
				registro.setCod_industria(rs.getString("cod_industria"));
				
				industrias.add(registro);
				
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	
		
		return industrias;
	}
	
	
	public List<Segmento> getSegmentoIndustria(){
		
		List<Segmento> segmentos = new ArrayList<>();
		
		
		return segmentos;
	}
	
	
	
	
}
