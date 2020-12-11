package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.IndFocoParVendedor;

public class JdbcParConfigIndFoco {
	
	private Connection conectionSqlServer;
	private Connection conectionMySql;
	
	public JdbcParConfigIndFoco() {

		try {
			conectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			conectionMySql = new ConnectionFactory().getConnectionMySql();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<IndFocoParVendedor> getVendedorAtivo(){
		List<IndFocoParVendedor> vendedoresAtivos = new ArrayList<>();
			String sql ="select v.cd_vend, v.nome, e.descricao\r\n" + 
					"--,ve.cd_emp ,ve.utiliza_palm_top, v.ativo, e.cd_equipe\r\n" + 
					"\r\n" + 
					"from vendedor v\r\n" + 
					"\r\n" + 
					"join equipe e\r\n" + 
					"on v.cd_equipe = e.cd_equipe\r\n" + 
					"\r\n" + 
					"join vend_emp ve\r\n" + 
					"on ve.cd_vend=v.cd_vend\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"where ve.cd_emp IN (13, 20) and ve.utiliza_palm_top=1 and\r\n" + 
					" v.ativo=1 and e.ativo=1 and e.cd_equipe not in('PC98','PC85','PC90','1700','1')\r\n" + 
					"\r\n" + 
					"ORDER BY descricao DESC";
				
		try {
			PreparedStatement stmt = conectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				IndFocoParVendedor registro = new IndFocoParVendedor();
				
				registro.setCd_venda(rs.getString(1));
				registro.setNome(rs.getString(2));
				registro.setEquipe(rs.getString(3));
				
				vendedoresAtivos.add(registro);
				
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
		List<IndFocoParVendedor> vendFocoOntex = new ArrayList<>();
		vendFocoOntex = vendFocoOntex();
		
		for (IndFocoParVendedor regAtivo : vendedoresAtivos) {
			for(IndFocoParVendedor regFoco: vendFocoOntex) {
				if(regAtivo.getCd_venda().equals(regFoco.getCd_venda())) {
					regAtivo.setFoco(regFoco.isFoco());
					break;
				}
				
				
			}
			
		}
		
		return vendedoresAtivos;
	}
	
	
	public List<IndFocoParVendedor> vendFocoOntex(){
		List<IndFocoParVendedor> vendFocoOntex = new ArrayList<>();
		
		String sql="SELECT cdVend, foco from indfocoontex";
		
		try {
			PreparedStatement stmt = this.conectionMySql.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				IndFocoParVendedor registro = new IndFocoParVendedor();
				
				registro.setCd_venda(rs.getString(1));
				registro.setFoco(rs.getBoolean(2));
				System.out.println("********"+registro.getCd_venda()+" - "+registro.isFoco());
				vendFocoOntex.add(registro);
			}
			
			return vendFocoOntex;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
		
	}
	
	public List<IndFocoParVendedor> editaVendFocoOntex(String codVend, String foco){
		List<IndFocoParVendedor> vendedoresAtualizados = new ArrayList<>();
		int bitFoco=0;
		
		System.out.println(foco);
		if(foco==null){
			bitFoco=0;
			
		}else if(foco.equals("true")) {
			bitFoco=1;
		}
		
		String existeVendedorMySql="SELECT * FROM indfocoontex WHERE cdVend='"+codVend+"'";
		String updateVendedorMySql="UPDATE indfocoontex set foco="+bitFoco+" where cdVend='"+codVend+"';";
		String insetVendedorMySql="INSERT into indfocoontex (cdVend, foco,parametrizado) VALUES ('"+codVend+"', "+bitFoco+", 1)";
		
			try {
				PreparedStatement stmtConsulta = this.conectionMySql.prepareStatement(existeVendedorMySql);
				ResultSet rs = stmtConsulta.executeQuery();
				if(rs.next()) {
					try {
						PreparedStatement stmtUpdate = this.conectionMySql.prepareStatement(updateVendedorMySql);
						stmtUpdate.execute();
					}catch(SQLException e) {
						throw new RuntimeException(e);
						
					}
				}else {
					try {
						PreparedStatement stmtInsert = this.conectionMySql.prepareStatement(insetVendedorMySql);
						stmtInsert.execute();
					}catch(SQLException e) {
						throw new RuntimeException(e);
					}
					
					
				}
				
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		
		
		
		return vendedoresAtualizados=getVendedorAtivo();
	}
	

}
