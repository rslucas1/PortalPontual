package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.lucasdev.modelo.positivacao.ClienteDetalhado;

public class JdbcConsultaCliente {
	
	private Connection conectionSqlServer;
	
	public JdbcConsultaCliente() {
		try {
			conectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
	}
	
	
	public ClienteDetalhado getClienteDetalhado(String cnpj) {
		ClienteDetalhado clienteDetalhado = new ClienteDetalhado();
		String sql ="select\r\n" + 
				" c.cd_clien cd_clien,\r\n" + 
				" nome descricao, \r\n" + 
				" tp_cliente tp_cli, \r\n" + 
				" nome_res fantasia,\r\n" + 
				" cgc_cpf,\r\n" + 
				" g.descricao grupo, \r\n" + 
				" e.logradouro logradouro, \r\n" + 
				" e.numero numero,\r\n" + 
				" e.cep cep, \r\n" + 
				" r.descricao segmento, \r\n" + 
				" a.descricao area, \r\n" + 
				" e.bairro,\r\n" + 
				" e.municipio, \r\n" + 
				" e.distrito,\r\n" + 
				" c.ativo,\r\n" + 
				" c.cd_vend,\r\n" + 
				"CONVERT(varchar(10), c.dt_ult_compra, 103) as dt_ult_compra\r\n" + 
				" \r\n" + 
				"from cliente c \r\n" + 
				"\r\n" + 
				"join grupocli g \r\n" + 
				"on g.cd_grupocli = c.cd_grupocli \r\n" + 
				"\r\n" + 
				"join end_cli e\r\n" + 
				"on e.cd_clien = c.cd_clien\r\n" + 
				"\r\n" + 
				"join ram_ativ r\r\n" + 
				"on r.ram_ativ = c.ram_ativ\r\n" + 
				"\r\n" + 
				"join area a\r\n" + 
				"on c.cd_area = a.cd_area\r\n" + 
				"\r\n" + 
				" where c.cgc_cpf='"+cnpj+"' and  e.tp_end='FA'";
				
				System.out.println(sql);
		
				
		try {
			
			PreparedStatement stmt = this.conectionSqlServer.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			boolean cadastrado=false;
			
				while (rs.next()) {
					cadastrado=true;
					
					clienteDetalhado.setCd_cliente(rs.getInt("cd_clien"));
					clienteDetalhado.setDesc_cliente(rs.getString("descricao"));
					clienteDetalhado.setTp_Cli(rs.getString("tp_cli"));
					clienteDetalhado.setFantasia(rs.getString("fantasia"));
					clienteDetalhado.setCgc_cpf(rs.getString("cgc_cpf"));
					clienteDetalhado.setLogradouro(rs.getString("logradouro"));
					clienteDetalhado.setNumero(rs.getString("numero"));
					clienteDetalhado.setCep(rs.getString("cep"));
					clienteDetalhado.setSegmento(rs.getString("segmento"));
					clienteDetalhado.setArea(rs.getString("area"));
					clienteDetalhado.setBairro(rs.getString("bairro"));
					clienteDetalhado.setMunicipio(rs.getString("municipio"));
					clienteDetalhado.setDistrito(rs.getString("distrito"));
					clienteDetalhado.setGrupoCli(rs.getString("grupo"));
					String situacao = (rs.getString("ativo").equals("1")) ? "ATIVO" : "INATIVO";
					clienteDetalhado.setSituacao(situacao);
					String vendedor = rs.getString("cd_vend");
					clienteDetalhado.setDtUltimaCompra(rs.getString("dt_ult_compra"));
					
					
					
					if(vendedor.equals("990LG001")) {
						clienteDetalhado.setVendedor("***CLIENTE ASSOCIADO A BASE INATIVA***");					
					}else {
						clienteDetalhado.setVendedor(vendedor);
					}
					
	
				}
				
				if(cadastrado==false) {
					clienteDetalhado.setVendedor("CNPJ NÃO CADASTRADO EM NOSSA BASE");
					clienteDetalhado.setDesc_cliente("CNPJ NÃO CADASTRADO EM NOSSA BASE");
					
				}
			
			System.out.println(cadastrado);
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return clienteDetalhado;
	}

}
