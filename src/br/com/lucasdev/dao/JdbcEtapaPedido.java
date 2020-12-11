package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.etapaPedidos.EtapaPedidoRelatorio;

public class JdbcEtapaPedido {
	
	private Connection connectionSqlServer;
	
	public JdbcEtapaPedido() {
		try{
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			}
		}
	
	public List<EtapaPedidoRelatorio> getEtapaPedidos(String cdVendTarget){
		
		String sql = "select distinct \r\n" + 
				"ev.nu_ped Pedido,\r\n" + 
				"convert(varchar, p.dt_cad, 103) [DataPedido],\r\n" + 
				"ev.cd_clien Cliente,\r\n" + 
				"cl.nome Nome ,\r\n" + 
				"cast (p.valor_tot as money) Valor,\r\n" + 
				"f.cd_fila Etapa\r\n" + 
				"from evento ev\r\n" + 
				"join cliente cl on cl.cd_clien = ev.cd_clien\r\n" + 
				"join ped_vda p on p.nu_ped = ev.nu_ped and ev.cd_clien = p.cd_clien and ev.cd_emp = p.cd_emp\r\n" + 
				"join fila f on f.cd_fila = ev.cd_fila\r\n" + 
				"where  ev.situacao = 'AB' \r\n" + 
				"and ev.cd_emp IN (13, 20) \r\n" + 
				"and p.tp_ped not in ('PE','NP')\r\n" + 
				"and p.cd_vend = '"+cdVendTarget+"'\r\n" + 
				"and ev.cd_fila IN (\r\n" + 
				"'BLAV',\r\n" +
				"'CAPV',\r\n" + 
				"'BLGV',\r\n" + 
				"'BLOQ',\r\n" + 
				"'CRED',\r\n" + 
				"'ENTR',\r\n" + 
				"'EXPE',\r\n" + 
				"'FATU',\r\n" + 
				"'GERV'\r\n" + 
				")";
		
		System.out.println("...Executando o Scrit -->-->--> \n"+sql);
		
		
		List<EtapaPedidoRelatorio> pedidosAbertos = new ArrayList<>();
		
		try {
			
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				EtapaPedidoRelatorio registro = new EtapaPedidoRelatorio();
				
				registro.setPedido(rs.getInt("Pedido"));
				registro.setDtPedido(rs.getString("DataPedido"));
				String cdCliente = rs.getString("Cliente");
				String descCliente = rs.getString("Nome");
				registro.setCliente(cdCliente+" - "+descCliente);
				String valor = Double.toString(rs.getDouble("Valor"));
				registro.setValor("R$ "+ valor);
				String cdfila = (rs.getString("Etapa"));
				String descFila;
				
				switch (cdfila) {
				case "CAPV":
					descFila="Aguardando importação do pedido";
					break;
				case "BLAV":
					descFila="Aguardando analise da Adm de vendas";
					break;
				case "GERV":
					descFila="Aguardando analise da gerencia comercial";
					break;
				case "BLGV":
					descFila="Aguardando analise da gerencia comercial";
					break;
				case "CRED":
					descFila="Aguardando analise de credito";
					break;
				case "BLOQ":
					descFila="Aguardando analise de credito";
					break;
				case "EXPE":
					descFila="Aguardando separação do pedido";
					break;
				case "FATU":
					descFila="Aguardando o pedido ser faturado";
					break;
				case "ENTR":
					descFila="O pedido saiu para Entrega";
					break;
				
				default:
					descFila="Erro ao processar a requisição"+cdfila;
					
								
				} 
				
				registro.setFila(descFila);
				
				pedidosAbertos.add(registro);
												
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
	
		
		
		return pedidosAbertos;
	}

}
