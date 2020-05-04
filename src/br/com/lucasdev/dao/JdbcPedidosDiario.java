package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.PedidosDiario;
import br.com.lucasdev.modelo.relatorios.Vendedor;

public class JdbcPedidosDiario {
	private Connection connectionSqlServer;
	LocalDate hoje = LocalDate.now();
	
	public JdbcPedidosDiario (){
		
	try {
		connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
	}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List <PedidosDiario> pedidosDiarioGeral(){
		List <PedidosDiario> pedidosdiario = new ArrayList<>();
	
		String sql = "select p.nu_ped, p.cd_vend, v.nome, c.cd_clien, c.nome AS 'desc', p.valor_tot ,  p.situacao\r\n" + 
				"\r\n" + 
				"from ped_vda p\r\n" + 
				"\r\n" + 
				"join vendedor v\r\n" + 
				"on p.cd_vend=v.cd_vend\r\n" + 
				"\r\n" + 
				"join cliente c\r\n" + 
				"on c.cd_clien = p.cd_clien\r\n" + 
				"\r\n" + 
				"where \r\n" + 
				"	p.dt_cad BETWEEN '"+hoje+" 00:00:00' AND '"+hoje+" 23:59:59' AND \r\n" + 
				"	p.cd_emp=13  and\r\n" + 
				"	p.tp_ped not in ('PE','NP', 'MD') AND \r\n" + 
				"   p.situacao NOT IN ('CA')";
		
				System.out.println(sql);
		
		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
							
			while(rs.next()) {
				PedidosDiario registro = new PedidosDiario();
				
				registro.setNumPedido(rs.getInt(1));
				registro.setCdVendedor(rs.getString(2));
				registro.setNomeVendedor(rs.getString(3));
				registro.setCd_cliente(rs.getInt(4));
				registro.setDesc_cliente(rs.getString(5));
				registro.setValor(rs.getDouble(6));
				registro.setSituacao(rs.getString(7));
			
				pedidosdiario.add(registro);
				
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		return pedidosdiario;
	}
	
	
	public List <PedidosDiario> pedidosDiarioEquipe(List lista){
		
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
				
		String sql = "select p.nu_ped, p.cd_vend, v.nome, c.cd_clien, c.nome AS 'desc', p.valor_tot , p.situacao\r\n" + 
				"\r\n" + 
				"from ped_vda p\r\n" + 
				"\r\n" + 
				"join vendedor v\r\n" + 
				"on p.cd_vend=v.cd_vend\r\n" + 
				"\r\n" + 
				"join cliente c\r\n" + 
				"on c.cd_clien = p.cd_clien\r\n" + 
				"\r\n" + 
				"where \r\n" + 
				"	p.cd_vend IN ("+inVendedores+") and \r\n" + 
				"	p.dt_cad BETWEEN '"+hoje+" 00:00:00' AND '"+hoje+" 23:59:59' AND \r\n" + 
				"	p.cd_emp=13  and\r\n" + 
				"	p.tp_ped not in ('PE','NP') AND \r\n" + 
				"   p.situacao NOT IN ('CA')";
		
				System.out.println(sql);
		
		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
							
			while(rs.next()) {
				PedidosDiario registro = new PedidosDiario();
				
				registro.setNumPedido(rs.getInt(1));
				registro.setCdVendedor(rs.getString(2));
				registro.setNomeVendedor(rs.getString(3));
				registro.setCd_cliente(rs.getInt(4));
				registro.setDesc_cliente(rs.getString(5));
				registro.setValor(rs.getDouble(6));
				registro.setSituacao(rs.getString(7));
			
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
