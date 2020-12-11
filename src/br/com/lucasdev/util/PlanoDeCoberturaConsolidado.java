package br.com.lucasdev.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.dao.ConnectionFactory;
import br.com.lucasdev.dao.JdbcHierarquia;
import br.com.lucasdev.modelo.positivacao.ClienteDetalhado;
import br.com.lucasdev.modelo.relatorios.Equipe;

public class PlanoDeCoberturaConsolidado {
	
private Connection connectionSqlServer;
	
	public PlanoDeCoberturaConsolidado (){
		
	try {
		connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
	}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	
	public List<ClienteDetalhado> getClientesPlanCobConsolidado(String perfil, String cdVenda){
		List<ClienteDetalhado> clientesPlanCobConsolidado = new ArrayList<>();		
		 String filtro="";
		 		 
		
			if(perfil.equals("DIRETORIA") || perfil.equals("COMERCIAL") || perfil.equals("ADMINISTRADOR")) {
				filtro="";
			} else if (perfil.equals("GERENTE")){
				
				String cd_gerencia = new JdbcHierarquia().getCdGerencia(cdVenda);
				filtro="AND gr.cd_gerencia='"+cd_gerencia+"'";
				
			} else if (perfil.equals("SUPERVISOR")){
				
				String cd_equipe = new JdbcHierarquia().getCdEquipe(cdVenda);
				filtro="AND eq.cd_vend_sup='"+cdVenda+"'";
				
			}
				
				String sql="select\r\n" + 
						"c.cd_clien,\r\n" + 
						"c.nome,\r\n" + 
						"c.nome_res,\r\n" + 
						"c.tp_cliente,\r\n" + 
						"c.cgc_cpf,\r\n" + 
						"g.descricao,\r\n" + 
						"r.descricao,\r\n" + 
						"e.cep,\r\n" + 
						"e.logradouro,\r\n" + 
						"e.numero,\r\n" + 
						"e.bairro,\r\n" + 
						"e.municipio,\r\n" + 
						"e.distrito,\r\n" + 
						"vc.cd_vend,\r\n" + 
						"v.nome,\r\n" + 
						"v.nome_gue,\r\n" + 
						"gr.descricao AS GERENCIA,\r\n" + 
						"eq.cd_equipe,\r\n" + 
						"eq.descricao AS EQUIPE,\r\n" +
						"c.cd_area,\r\n"+
						"tc.ddd as ddd,\r\n" + 
						"tc.numero as numero"+
						"\r\n" + 
						"from cliente c\r\n" + 
						"\r\n" + 
						"join grupocli g\r\n" + 
						"on g.cd_grupocli = c.cd_grupocli\r\n" + 
						"\r\n" + 
						"join ram_ativ r\r\n" + 
						"on c.ram_ativ =r.ram_ativ\r\n" + 
						"\r\n" + 
						"join end_cli e\r\n" + 
						"on e.cd_clien = c.cd_clien and e.tp_end='FA'\r\n" + 
						"\r\n" + 
						"join vend_cli vc\r\n" + 
						"on vc.cd_clien=c.cd_clien and vc.prioritario=1 and vc.cd_vend!='990LG001'\r\n" + 
						"\r\n" + 
						"join vendedor v\r\n" + 
						"on vc.cd_vend=v.cd_vend\r\n" + 
						"\r\n" + 
						"join equipe eq\r\n" + 
						"on eq.cd_equipe=v.cd_equipe\r\n" + 
						"\r\n" + 
						"join gerencia gr\r\n" + 
						"on gr.cd_gerencia = eq.cd_gerencia\r\n" + 
						"\r\n" + 
						
						"left join tel_cli tc\r\n" + 
						"on tc.cd_clien=c.cd_clien\r\n" + 
						"\r\n" + 
						
						"WHERE vc.cd_vend!='990LG001' and gr.cd_emp=13 and eq.cd_equipe not in ('1', 'PC05', 'PC50') and tc.seq=1\r\n" +
						filtro+
						
						"ORDER BY GERENCIA, EQUIPE";
				
				System.err.println(sql);
				
			
				
				

			
						
				try { 
					PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						ClienteDetalhado registro = new ClienteDetalhado();
						registro.setCd_cliente(rs.getInt(1));
						registro.setDesc_cliente(rs.getString(2));
						registro.setFantasia(rs.getString(3));
						registro.setCgc_cpf(rs.getString(5));
						registro.setGrupoCli(rs.getString(6));
						registro.setSegmento(rs.getString(7));
						registro.setCep(rs.getString(8));
						registro.setLogradouro(rs.getString(9));
						registro.setNumero(rs.getString(10));
						registro.setBairro(rs.getString(11));
						registro.setMunicipio(rs.getString(12));
						registro.setDistrito(rs.getString(13));
						registro.setCdVendedor(rs.getString(14));
						registro.setVendedor(rs.getString(15));
						registro.setNomeGuerraVend(rs.getString(16));
						registro.setDescGerencia(rs.getString(17));
						registro.setCdEquipe(rs.getString(18));
						registro.setDescEquipe(rs.getString(19));
						registro.setArea(rs.getString(20));
						registro.setTelefone("("+rs.getString(21)+") "+rs.getString(22));
						
						clientesPlanCobConsolidado.add(registro);
											
						
					}				
					
					
				}catch(SQLException e) {
					
			}
		
				
		
		
			return clientesPlanCobConsolidado;
	}
}
