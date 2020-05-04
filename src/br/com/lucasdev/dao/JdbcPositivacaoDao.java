package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.positivacao.Cliente;
import br.com.lucasdev.modelo.positivacao.PedidoPositivado;
import br.com.lucasdev.modelo.positivacao.Positivacao;

public class JdbcPositivacaoDao {

	private Connection connectionSqlServer;

	public JdbcPositivacaoDao() {
		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public List<Cliente> getcarteiraClientes(String cd_target) {

		String sql = "select vc.cd_clien, c.nome from vend_cli vc\r\n" + 
				"\r\n" + 
				"join cliente c\r\n" + 
				"on vc.cd_clien = c.cd_clien\r\n" + 
				"\r\n" + 
				"where vc.cd_vend='"+cd_target+"'\r\n" + 
				"AND prioritario=1";

		List<Cliente> carteira = new ArrayList<>();

		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Cliente cliente = new Cliente();

				cliente.setCd_cliente(rs.getInt("cd_clien"));
				cliente.setDesc_cliente(rs.getString("nome"));

				carteira.add(cliente);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

		return carteira;

	}// metodo getcarteiraClientes

	public List<PedidoPositivado> getpedidosPositivados(String cd_target, String ano, int mes) {

		String sql = "SELECT DISTINCT\r\n" + "nu_ped Pedido,\r\n" + "cd_clien Cliente,\r\n" + "cd_vend Vendedor,\r\n"
				+ "tp_ped [Tipo pedido],\r\n" + "dt_ped [Data Pedido]\r\n" + "FROM ped_vda \r\n" + "where cd_vend = "
				+ "'" + cd_target + "'" + "and dt_ped between " + getPeriodo(mes, ano);

		System.out.println("...Executando o Script ->\n\t" + sql);

		List<PedidoPositivado> pedidos = new ArrayList<>();

		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PedidoPositivado pedido = new PedidoPositivado();

				pedido.setCd_cliente(rs.getInt("Cliente"));
				pedido.setNu_pedido(rs.getInt("Pedido"));
				pedido.setCd_vendedor(rs.getString("Vendedor"));
				pedido.setDt_pedido(rs.getString("Data Pedido"));

				pedidos.add(pedido);

			} // fim while

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} // fim catch

		return pedidos;

	}// metodo getPedidosPositivados

	public List<Positivacao> positivacoes(List<Cliente> carteira, List<PedidoPositivado> pedidos,
			String botaoAcionado) {

		List<Positivacao> pedidosPositivados = new ArrayList<>();

		Positivacao positivador;

		if (botaoAcionado.equals("POSITIVADOS")) {
			System.out.println("...IF POSITIVADOS selecionado");
			for (Cliente c : carteira) {
				for (PedidoPositivado p : pedidos) {
					if (c.getCd_cliente() == p.getCd_cliente()) {
						positivador = new Positivacao();
						positivador.setCd_cliente(c.getCd_cliente());
						positivador.setDesc_cliente(c.getDesc_cliente());
						positivador.setPositivado(true);
						pedidosPositivados.add(positivador);
						break;

					}
				}

			}

		} else if (botaoAcionado.equals("NÃO POSITIVADOS")) {
			for (Cliente c : carteira) {
				boolean localizado=false;
					for (PedidoPositivado p : pedidos) {
						
						if (p.getCd_cliente() == c.getCd_cliente()) {
							localizado =true;
							break;
						}else {
							localizado =false;
						}
					}
				
				if(localizado==false) {
					positivador = new Positivacao();
					positivador.setCd_cliente(c.getCd_cliente());
					positivador.setDesc_cliente(c.getDesc_cliente());
					pedidosPositivados.add(positivador);
				}

			}

		}


//		TESTANDO A LISTA
//		for (Positivacao p : pedidosPositivados) {
//			System.out.println(p.getPositivado() + "-" + p.getCd_cliente() + "-" + p.getDesc_cliente());
//		}
		return pedidosPositivados;
	}

	public String getPeriodo(int mes, String ano) {

		String periodo = "";

		switch (mes) {
		case 1:
			periodo = "'" + ano + "-01-01 00:00:00' and '" + ano + "-01-31 00:00:00'";
			break;
		case 2:
			periodo = "'" + ano + "-02-01 00:00:00' and '" + ano + "-02-28 00:00:00'";
			break;
		case 3:
			periodo = "'" + ano + "-03-01 00:00:00' and '" + ano + "-03-31 00:00:00'";
			break;
		case 4:
			periodo = "'" + ano + "-04-01 00:00:00' and '" + ano + "-04-30 00:00:00'";
			break;
		case 5:
			periodo = "'" + ano + "-05-01 00:00:00' and '" + ano + "-05-31 00:00:00'";
			break;
		case 6:
			periodo = "'" + ano + "-06-01 00:00:00' and '" + ano + "-06-30 00:00:00'";
			break;
		case 7:
			periodo = "'" + ano + "-07-01 00:00:00' and '" + ano + "-07-31 00:00:00'";
			break;
		case 8:
			periodo = "'" + ano + "-08-01 00:00:00' and '" + ano + "-08-31 00:00:00'";
			break;
		case 9:
			periodo = "'" + ano + "-09-01 00:00:00' and '" + ano + "-09-30 00:00:00'";
			break;
		case 10:
			periodo = "'" + ano + "-10-01 00:00:00' and '" + ano + "-10-31 00:00:00'";
			break;
		case 11:
			periodo = "'" + ano + "-11-01 00:00:00' and '" + ano + "-11-30 00:00:00'";
			break;
		case 12:
			periodo = "'" + ano + "-12-01 00:00:00' and '" + ano + "-12-31 00:00:00'";
			break;

		}

		return periodo;

	}

}// fim classe
