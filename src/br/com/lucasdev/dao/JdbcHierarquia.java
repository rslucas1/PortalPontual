package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.PrioritizedParameterNameDiscoverer;

import br.com.lucasdev.modelo.relatorios.PedidosDiario;
import br.com.lucasdev.modelo.relatorios.Vendedor;

public class JdbcHierarquia {
	
	private Connection connectionSqlServer;
	
	public JdbcHierarquia (){
		
	try {
		connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
	}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Vendedor> getEquipeVendedoresGerente(String cd_gerente){
		List<Vendedor> vendedores = new ArrayList<>();
		String sql="select v.cd_vend, e.descricao, g.descricao  from vendedor v\r\n" + 
				"join equipe e\r\n" + 
				"on v.cd_equipe=e.cd_equipe\r\n" + 
				"join gerencia g\r\n" + 
				"on g.cd_gerencia=e.cd_gerencia\r\n" + 
				"where g.cd_vend_ger='"+cd_gerente+"'\r\n" + 
				"and v.ativo=1";
		
		System.out.println(sql);
		
		
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
										
			while(rs.next()) {
				Vendedor registro = new Vendedor();
				registro.setCd_venda(rs.getString(1));
				vendedores.add(registro);
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
			
		return vendedores;
		
	}
	
	public List<Vendedor> getEquipeVendedoresSupervisor(String cd_supervisor){
		List<Vendedor> vendedores = new ArrayList<>();
		String sql="select v.cd_vend, e.descricao, g.descricao  from vendedor v\r\n" + 
				"join equipe e\r\n" + 
				"on v.cd_equipe=e.cd_equipe\r\n" + 
				"join gerencia g\r\n" + 
				"on g.cd_gerencia=e.cd_gerencia\r\n" + 
				"where e.cd_vend_sup='"+cd_supervisor+"'\r\n" + 
				"and v.ativo=1";
				
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
										
			while(rs.next()) {
				Vendedor registro = new Vendedor();
				registro.setCd_venda(rs.getString(1));
				vendedores.add(registro);
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
			
		return vendedores;
		
	}
	
	public List<Vendedor> getGerenciaAtiva(){
		List<Vendedor> gerenciaAtiva = new ArrayList<>();
		
		
		String sql="select cd_gerencia, descricao, v.nome as 'VEND_ASSOC' from gerencia g\r\n" + 
						"\r\n" + 
						"join vendedor v\r\n" + 
						"on g.cd_vend_ger=v.cd_vend\r\n" + 
						"\r\n" + 
						"where \r\n" + 
						"	g.cd_emp=13 and\r\n" + 
						"	g.ativo=1 and\r\n" + 
						"	cd_gerencia not in ('PC01','N/13', 'PC03', 'PC04', 'PC05', 'PC07', 'PC08')";
				
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println(sql);
										
			while(rs.next()) {
				Vendedor registro = new Vendedor();
				registro.setCd_venda(rs.getString(1));
				registro.setNome(rs.getString(2));
				registro.setCd_guerra(rs.getString(3));
				gerenciaAtiva.add(registro);
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
			
				
		
		return gerenciaAtiva;
	}
	
	public String getCdGerencia(String cd_vendGer){
		String cdGerencia="";
		
		
		String sql="select g.cd_gerencia from gerencia g\r\n" + 
				"where g.cd_emp=13 and\r\n" + 
				"g.ativo=1 and\r\n" + 
				"g.cd_vend_ger='"+cd_vendGer+"'";
				
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
										
			while(rs.next()) {
			
				cdGerencia=rs.getString(1);
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
			
					
		return cdGerencia;
	}
	

}
