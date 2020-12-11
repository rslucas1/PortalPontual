package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.Categoria;
import br.com.lucasdev.modelo.relatorios.Industria;
import br.com.lucasdev.modelo.relatorios.Marca;
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
		}else if(cod_industria.equals("todas")){
			Industria registro = new Industria();
			
			registro.setDesc_industria("TODAS");
			registro.setCod_industria("todas");
			
			industrias.add(registro);
			
			return industrias;
			
		}
		else{
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
	
	
	public List<Segmento> getSegmentos(){
		
		List<Segmento> segmentos = new ArrayList<>();
		
		String sql="SELECT cod_segmento, desc_segmento from segmento";
		
		try {
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Segmento registro = new Segmento();
				
				registro.setDesc_segmento(rs.getString("desc_Segmento"));
				registro.setCod_segmento(rs.getString("cod_Segmento"));
				
				segmentos.add(registro);
				
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return segmentos;
		
		
	}
	
	
	
	public List<Categoria> getCategoria(String fabricante, String inSegmentos){
		List<Categoria> categorias = new ArrayList<>();
		/* FILTRO */
		String filtro="";
		
		if(!fabricante.equals("todas")) {
			filtro="where p.cod_industria ='"+fabricante+"'";
			if(!inSegmentos.equals("todas")) {
				filtro = filtro+" AND p.cod_segmento in ("+inSegmentos+")";
			}
		}else {
			if(!inSegmentos.equals("todas")) {
				filtro = filtro+" where p.cod_segmento in ("+inSegmentos+")";
			}
			
		}
		/* FILTRO */
		
		
		
		String sql ="SELECT DISTINCT p.cod_categoria, c.desc_categoria  from produtoatributos p\r\n" + 
				"join categoria c\r\n" + 
				"on p.cod_categoria=c.cod_categoria " + filtro;
		
		try {
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Categoria registro = new Categoria();
				
				registro.setDesc_categoria(rs.getString("desc_categoria"));
				registro.setCod_categoria(rs.getString("cod_categoria"));
				
				categorias.add(registro);
				
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
			
			
			
					
					
			return categorias;
		}
	
	
	public List<Marca> getMarcas(String infabricante, String inSegmentos, String inCategorias){
		List<Marca> marcas = new ArrayList<>();
		/* FILTRO */
		String filtro=null;
		
		
		if(!infabricante.equals("todas") || !inSegmentos.equals("todas")|| !inCategorias.equals("todas")) {
			
			if(!infabricante.equals("todas")){
				filtro = " where cod_industria='"+infabricante+"'";
			}
			
			if (!inSegmentos.equals("todas")) {
				if(filtro==null) {
					filtro = filtro + " where cod_segmento in ("+inSegmentos+")";
				}else {
					filtro = filtro + " and cod_segmento in ("+inSegmentos+")";
				}
			}
			
			if(!inCategorias.equals("todas")) {
				if(filtro==null) {
					filtro = filtro + " where cod_categoria in ("+inCategorias+")";
				}else {
					filtro = filtro + " and cod_categoria in ("+inCategorias+")";
				}
				
			}
					
		} else {
			filtro ="";
		}
		/* FILTRO */
		
		
		
		String sql ="SELECT DISTINCT p.cod_marca, m.desc_marca from produtoatributos p\r\n" + 
				"\r\n" + 
				"JOIN marca m\r\n" + 
				"on p.cod_marca=m.cod_marca\r\n" + 
				"\r\n" + filtro;
			
		System.out.println(sql);
		
		try {
			
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Marca registro = new Marca();
				
				registro.setDesc_marca(rs.getString("desc_marca"));
				registro.setCod_marca(rs.getString("cod_marca"));
				
				marcas.add(registro);
				
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
						
					
			return marcas;
		}
		
	
	}
		

