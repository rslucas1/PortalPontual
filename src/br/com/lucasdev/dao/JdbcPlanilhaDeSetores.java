package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.PlanilhaDeSetores;

public class JdbcPlanilhaDeSetores {
	
	private Connection connection;
	
	public JdbcPlanilhaDeSetores() {
		
		try {
			connection = new ConnectionFactory().getConnectionSqlServer();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	public List<PlanilhaDeSetores> getPlanilhaDeSetores(String urlCdEquipe){
		
		System.out.println("Entrou no método com cd: "+urlCdEquipe);
		
		List<PlanilhaDeSetores> planilhaDeSetores = new ArrayList<>();
		String filtro="";
		
		if(urlCdEquipe.equals("GERAL")) {
			filtro="";
		} else if(urlCdEquipe.equals("PC98")||urlCdEquipe.equals("PC02")) {
			filtro=" AND equipe_cd_gerencia='"+urlCdEquipe+"' \r\n";
			
		} else {
			filtro=" AND vendedor_cd_equipe='"+urlCdEquipe+"' \r\n";
			
		}	
		
		
		
		String sql="select vendedor_cd_equipe, vendedor_cd_vend, equipe_descricao, vendedor_nome_gue, vendedor_cd_vend, vendedor_nome, convert(varchar, vendedor_dt_admissao, 103) [admissão], gerencia_descricao, ddd, tel_vend_pager, vendedor_cgc, vendedor_ativo\r\n" + 
				"\r\n" + 
				" from vi_aaa_planiha_de_setores\r\n" + 
				" where  equipe_cd_gerencia NOT IN('RM01', 'PC07', 'GA04')\r\n" + 
				"		\r\n" + 
				filtro + 
		
				" ORDER BY gerencia_descricao,equipe_descricao";
				
				System.out.println(sql);
		
//		try {
//			PreparedStatement stmt = this.connection.prepareStatement(sql);
//			ResultSet rs;
//			while(rs.next()) {
//				PlanilhaDeSetores registro = new PlanilhaDeSetores();			
//				
//				registro.setCodigoSup(rs.getString(1));
//				registro.setCd_venda(rs.getString(2));
//				registro.setNomeSupervisor(rs.getString(3));
//				registro.setCd_guerra(rs.getString(4));
//				registro.setCd_venda(rs.getString(5));
//				registro.setNome(rs.getString(6));
//				registro.setDataInicio(rs.getString(7));
//				registro.setNomeGerente(rs.getString(8));
//				registro.setTelefone("("+rs.getString(9)+") "+rs.getString(10));
//				registro.setCpf(rs.getString(11));
//				registro.setAtivo(rs.getString(12));
//				
//				planilhaDeSetores.add(registro);
//				
//			}
//			
//			
//			
//		}catch(SQLException e) {
//			throw new RuntimeException(e);
//			
//		}
		
		
				
		return planilhaDeSetores;
		
	}

}
