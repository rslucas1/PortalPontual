package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.PedidosDiario;
import br.com.lucasdev.modelo.relatorios.PlanilhaDeSetores;
import br.com.lucasdev.modelo.relatorios.Vendedor;

public class JdbcPedidosDiario {
	
	private Connection connection;
	
	
	public JdbcPedidosDiario() {
		
		try {
			connection = new ConnectionFactory().getConnectionSqlServer();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	public List <PedidosDiario> pedidosDiarioGeral(String dataInicial, String dataFinal, String situacaoNota, String estado){
		List <PedidosDiario> pedidosdiario = new ArrayList<>();
		
		System.out.println(estado);
	
		String sql = "";
		
		if(estado.equals("ENTRADA")) {
			sql = "select DISTINCT p.nu_ped, p.cd_vend, v.nome, c.cd_clien, c.nome AS 'desc', p.valor_tot ,  n.situacao, E.nome_fant, CONVERT(varchar(10),p.dt_cad,103) as dt_cad\r\n" + 
				"\r\n" + 
				"from ped_vda p\r\n" + 
				"\r\n" + 
				"join vendedor v\r\n" + 
				"on p.cd_vend=v.cd_vend\r\n" + 
				"\r\n" + 
				"join cliente c\r\n" + 
				"on c.cd_clien = p.cd_clien\r\n" + 
				
				"JOIN EMPRESA E\r\n" + 
				"on p.cd_emp=e.cd_emp\r\n"+
				"\r\n" +
				
				
				"LEFT JOIN nota N \r\n" + 
				"ON N.nu_ped = P.nu_ped AND N.cd_emp=P.cd_emp\r\n"+
				
				
				"where \r\n" + 
				"	p.dt_cad BETWEEN '"+dataInicial+" 00:00:00' AND '"+dataFinal+" 23:59:59' AND \r\n" + 
				"	p.cd_emp IN (13, 20)  and\r\n" + 
				"	p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC') AND \r\n" + 
				"   p.situacao NOT IN ('CA')  and p.cd_clien NOT IN (87379, 62379)" +
					situacaoNota +
					" order by 9 DESC";
			
		} else if(estado.equals("FATURADO")) {
				
			sql= "select DISTINCT \r\n" + 
					"p.nu_ped,\r\n" + 
					" p.cd_vend,\r\n" + 
					"  v.nome, \r\n" + 
					"  c.cd_clien,\r\n" + 
					"   c.nome AS 'desc', \r\n" + 
					"   --p.valor_tot,\r\n" + 
					"   n.vl_tot_nf,\r\n" + 
					"    n.situacao, \r\n" + 
					"	 E.nome_fant, CONVERT(varchar(10),p.dt_cad,103) as dt_cad,\r\n" + 
					"	 n.desc_nat_oper\r\n" + 
					"\r\n" + 
					"from ped_vda p\r\n" + 
					"\r\n" + 
					"join vendedor v\r\n" + 
					"on p.cd_vend=v.cd_vend\r\n" + 
					"\r\n" + 
					"join cliente c\r\n" + 
					"on c.cd_clien = p.cd_clien\r\n" + 
					"JOIN EMPRESA E\r\n" + 
					"on p.cd_emp=e.cd_emp\r\n" + 
					"\r\n" + 
					"--LEFT JOIN nota N \r\n" + 
					"JOIN nota N\r\n" + 
					"ON N.nu_ped = P.nu_ped AND N.cd_emp=P.cd_emp\r\n" + 
					"where \r\n" + 
					"	n.dt_emis BETWEEN '"+dataInicial+" 00:00:00' AND '"+dataFinal+" 23:59:59' AND \r\n" + 
					"	n.desc_nat_oper NOT IN ('REM. BONIF/DOAÇÃO/BRINDE','REMESSA DE BONIFICACAO','DEVOL.FORNECEDOR FORA EST.','DEVOLUÇAO MERC SUJ SUBS.TRIBUT','BONIF/DOAÇÃO/BRINDE','DEVOLUÇÃO A FORNECEDOR','DEVOLUÇÃO MERC SUJ SUBST TRIB') and --INCLUIDO\r\n" + 
					"	n.tipo_nf='S' AND n.situacao NOT IN ('CA') AND --INCLUIDO\r\n" + 
					"	p.cd_emp IN (13, 20)  and\r\n" + 
					"	p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC') AND \r\n" + 
					"	p.situacao NOT IN ('CA')  and p.cd_clien NOT IN (87379, 62379)\r\n" + 
					"	order by 9 DESC";
			
				}
		
			System.out.println(sql);
				
		
		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
							
			while(rs.next()) {
				PedidosDiario registro = new PedidosDiario();
				
				registro.setNumPedido(rs.getInt(1));
				registro.setCdVendedor(rs.getString(2));
				registro.setNomeVendedor(rs.getString(3));
				registro.setCd_cliente(rs.getInt(4));
				registro.setDesc_cliente(rs.getString(5));
				registro.setValor(rs.getDouble(6));
				registro.setSituacao(rs.getString(7)!=null ? "FATURADO": "-");
				registro.setEmpresa(rs.getString(8));
				registro.setDataPedido(rs.getString(9));
			
				pedidosdiario.add(registro);
				
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		return pedidosdiario;
	}
	
	
	public List <PedidosDiario> pedidosDiarioEquipe(List lista, String dataInicial, String dataFinal, String situacaoNota, String estado){
		
		List<Vendedor> listaVendedores = new ArrayList<>();
		listaVendedores=lista;
		String inVendedores="";
		int tamanhoLista = listaVendedores.size();
		int i = 1;

		for (Vendedor v : listaVendedores) {
			if (i < tamanhoLista) {
				inVendedores += "'"+v.getCd_venda()+"'"+ ", ";
			} else {
				inVendedores += "'"+v.getCd_venda()+"'";
			}

			i++;
		}
		
		
		
		
		List <PedidosDiario> pedidosdiario = new ArrayList<>();
				
		String sql = "";
		
				if(estado.equals("ENTRADA")) {
				
					sql ="select DISTINCT p.nu_ped, p.cd_vend, v.nome, c.cd_clien, c.nome AS 'desc', p.valor_tot , n.situacao,E.nome_fant, CONVERT(varchar(10),p.dt_cad,103) as dt_cad\r\n" + 
					"\r\n" + 
					"from ped_vda p\r\n" + 
					"\r\n" + 
					"join vendedor v\r\n" + 
					"on p.cd_vend=v.cd_vend\r\n" + 
					"\r\n" + 
					"join cliente c\r\n" + 
					"on c.cd_clien = p.cd_clien\r\n" +
					
					"JOIN EMPRESA E\r\n" + 
					"on p.cd_emp=e.cd_emp\r\n"+
	
					"LEFT JOIN nota N \r\n" + 
					"ON N.nu_ped = P.nu_ped AND N.cd_emp=P.cd_emp\r\n"+
					
					"\r\n" + 
					"where \r\n" + 
					"	p.cd_vend IN ("+inVendedores+") and \r\n" + 
					"	p.dt_cad BETWEEN '"+dataInicial+" 00:00:00' AND '"+dataFinal+" 23:59:59' AND \r\n" + 
					"	p.cd_emp IN (13, 20)  and\r\n" + 
					"	p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC') AND \r\n" + 
					"   p.situacao NOT IN ('CA')  and p.cd_clien NOT IN (87379, 62379)"+
					situacaoNota +
					" order by 9 DESC";
				} else if(estado.equals("FATURADO")) {
					
					sql= "select DISTINCT \r\n" + 
							"p.nu_ped,\r\n" + 
							" p.cd_vend,\r\n" + 
							"  v.nome, \r\n" + 
							"  c.cd_clien,\r\n" + 
							"   c.nome AS 'desc', \r\n" + 
							"   --p.valor_tot,\r\n" + 
							"   n.vl_tot_nf,\r\n" + 
							"    n.situacao, \r\n" + 
							"	 E.nome_fant, CONVERT(varchar(10),p.dt_cad,103) as dt_cad,\r\n" + 
							"	 n.desc_nat_oper\r\n" + 
							"\r\n" + 
							"from ped_vda p\r\n" + 
							"\r\n" + 
							"join vendedor v\r\n" + 
							"on p.cd_vend=v.cd_vend\r\n" + 
							"\r\n" + 
							"join cliente c\r\n" + 
							"on c.cd_clien = p.cd_clien\r\n" + 
							"JOIN EMPRESA E\r\n" + 
							"on p.cd_emp=e.cd_emp\r\n" + 
							"\r\n" + 
							"--LEFT JOIN nota N \r\n" + 
							"JOIN nota N\r\n" + 
							"ON N.nu_ped = P.nu_ped AND N.cd_emp=P.cd_emp\r\n" + 
							"where \r\n" + 
							"	p.cd_vend IN ("+inVendedores+") and \r\n" + 
							"	n.dt_emis BETWEEN '"+dataInicial+" 00:00:00' AND '"+dataFinal+" 23:59:59' AND \r\n" + 
							"	n.desc_nat_oper NOT IN ('REM. BONIF/DOAÇÃO/BRINDE','REMESSA DE BONIFICACAO','DEVOL.FORNECEDOR FORA EST.','DEVOLUÇAO MERC SUJ SUBS.TRIBUT','BONIF/DOAÇÃO/BRINDE','DEVOLUÇÃO A FORNECEDOR','DEVOLUÇÃO MERC SUJ SUBST TRIB') and --INCLUIDO\r\n" + 
							"	n.tipo_nf='S' AND n.situacao NOT IN ('CA') AND --INCLUIDO\r\n" + 
							"	p.cd_emp IN (13, 20)  and\r\n" + 
							"	p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC') AND \r\n" + 
							"	p.situacao NOT IN ('CA')  and p.cd_clien NOT IN (87379, 62379)\r\n" + 
							"	order by 9 DESC";
					
					
				}
		
				System.out.println(sql);
		
		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
							
			while(rs.next()) {
				PedidosDiario registro = new PedidosDiario();
				
				registro.setNumPedido(rs.getInt(1));
				registro.setCdVendedor(rs.getString(2));
				registro.setNomeVendedor(rs.getString(3));
				registro.setCd_cliente(rs.getInt(4));
				registro.setDesc_cliente(rs.getString(5));
				registro.setValor(rs.getDouble(6));
				registro.setSituacao(rs.getString(7)!=null ? "FATURADO": "-");
				registro.setEmpresa(rs.getString(8));
			
				pedidosdiario.add(registro);
				
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		return pedidosdiario;
	}
	
	
	
	public String descFila(String cdFila) {
		String descricao;
				
		switch (cdFila) {
		case "CAPV":
			descricao="Aguardando importação do pedido";
			break;
		case "BLAV":
			descricao="Aguardando analise da Adm de vendas";
			break;
		case "GERV":
			descricao="Aguardando analise da gerencia comercial";
			break;
		case "BLGV":
			descricao="Aguardando analise da gerencia comercial";
			break;
		case "CRED":
			descricao="Aguardando analise de credito";
			break;
		case "BLOQ":
			descricao="Aguardando analise de credito";
			break;
		case "EXPE":
			descricao="Aguardando separação do pedido";
			break;
		case "FATU":
			descricao="Aguardando o pedido ser faturado";
			break;
		case "ENTR":
			descricao="O pedido saiu para Entrega";
			break;
		
		default:
			descricao="Erro ao processar a requisição"+cdFila;
						
		}
		
		return descricao;
	}
	
}
