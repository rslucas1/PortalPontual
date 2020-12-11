package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.CodDescricao;
import br.com.lucasdev.modelo.relatorios.ExpedicaoCarregamento;
import br.com.lucasdev.modelo.relatorios.PedidoProducao;
import br.com.lucasdev.util.Formata;

public class JdbcPedidoProducao {

	private Connection connectionSqlServerErp;
	private Connection connectionSqlServerTms;
	
	public JdbcPedidoProducao() {
		
		
		try {
			connectionSqlServerErp = new ConnectionFactory().getConnectionSqlServer();
			connectionSqlServerTms = new ConnectionFactory().getConnectionTms();
			
				
		
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
	}
	
	public List <PedidoProducao> getpedidoProducao(String dt_inicio, String dt_final, String filtroStatus){
		List <PedidoProducao> pedidoProducao = new ArrayList<>();
		
	
		String sql_erp = "select distinct \r\n" + 
				"p.dt_ped [DATA ENTRADA],\r\n" + 
				"e.dt_criacao [DATA SEPARAÇÃO],\r\n" + 
				"P.nu_ped[PEDIDO],\r\n" + 
				"n.nu_nf_emp_fat[NOTA],\r\n" + 
				"rm.nu_rom[ROTEIRO],\r\n" + 
				"c.distrito[DISTRITO],\r\n" + 
				"c.bairro[BAIRRO],\r\n" +
				"CONVERT (VARCHAR(10), ev.dt_encer, 103) AS [DT ENTREGA]\r\n" +
				"from ped_vda p\r\n" + 
				"LEFT JOIN it_rom rm ON rm.nu_ped = p.nu_ped\r\n" + 
				"LEFT JOIN romaneio r ON r.nu_rom = rm.nu_rom\r\n" + 
				"LEFT JOIN evento e ON e.nu_ped = p.nu_ped\r\n" + 
				"LEFT JOIN nota n ON n.nu_ped = p.nu_ped and n.cd_emp = p.cd_emp\r\n" + 
				"LEFT JOIN end_cli c ON c.cd_clien = p.cd_clien\r\n" +
				"LEFT JOIN evento ev ON ev.nu_ped=p.nu_ped and ev.cd_fila='ENTR' AND ev.cd_emp IN (13, 20) AND ev.situacao!='CA'\r\n" + 
				"where p.cd_emp  IN (13, 20)\r\n" + 
				"AND e.cd_fila ='SEPA'\r\n" + 
				"AND C.tp_end = 'FA'\r\n" + 
				"AND p.dt_cad between '"+dt_inicio+" 00:00:00' AND '"+dt_final+" 23:59:00'\r\n" +
				"AND rm.situacao NOT IN('CA')\r\n"+
				"order by p.dt_ped, c.distrito, c.bairro, rm.nu_rom";
		
				System.out.println(sql_erp);
		
		try {
			PreparedStatement stmt_erp = (PreparedStatement) connectionSqlServerErp.prepareStatement(sql_erp);
			ResultSet rs_erp = stmt_erp.executeQuery();
							
			while(rs_erp.next()) {
				PedidoProducao registro = new PedidoProducao();
				
				
				
				registro.setDt_entrada(Formata.data(rs_erp.getString(1).substring(0, 10)));
				registro.setDt_inic_sepa(Formata.data(rs_erp.getString(2).substring(0, 10)));
				registro.setNu_pedido(rs_erp.getString(3));
				registro.setNota(rs_erp.getString(4));
				
				registro.setRoteiro(rs_erp.getString(5)!=null ? rs_erp.getString(5):"aguardando roteirizar");
				registro.setDistrito(rs_erp.getString(6));
				registro.setBairro(rs_erp.getString(7));
				registro.setDt_baixaErp(rs_erp.getString(8)!=null ? rs_erp.getString(8) : "não baixado no Erp");

							
				pedidoProducao.add(registro);
				
			}
			
			System.out.println("TESTE "+ conversorListaIntSql(pedidoProducao));
			
			if(!conversorListaIntSql(pedidoProducao).equals("")) {
			
				List<PedidoProducao> selectTms = new ArrayList<>();
										
												
				String sql_tms="select \r\n" + 
						"m.id_Manifesto,\r\n" + 
						"nt.nr_NotaFiscal,\r\n" + 
						"oc.ds_Ocorrencia,\r\n" + 
						"ps.ds_Pessoa,\r\n" + 
						"cast (vl_Extra as money) Saida,\r\n" + 
						"cast (vl_Servico as money) Total,\r\n" + 
						"v.ds_Modelo\r\n" + 
						"\r\n" + 
						"from tbdManifesto m\r\n" + 
						"\r\n" + 
						"INNER JOIN tbdOcorrenciaNota nt ON nt.id_Manifesto = m.id_Manifesto\r\n" + 
						"INNER JOIN tbdOcorrencia oc ON OC.id_Ocorrencia = nt.id_Ocorrencia\r\n" + 
						"INNER JOIN tbdVeiculo v on v.id_Veiculo = m.id_Veiculo\r\n" + 
						"INNER JOIN tbdPessoa ps on ps.id_Pessoa = m.id_Motorista" + 
						"\r\n" + 
						"where \r\n" + 
						"\r\n" + 
						"nt.nr_NotaFiscal in ("+conversorListaIntSql(pedidoProducao)+") AND m.ds_Clientes='PONTUAL 06 - ITAPEVI'";
									
						System.out.println(sql_tms);
				
				
						PreparedStatement stmt_tms = (PreparedStatement) connectionSqlServerTms.prepareStatement(sql_tms);
						ResultSet rs_tms = stmt_tms.executeQuery();
				
						while(rs_tms.next()) {
							PedidoProducao registro = new PedidoProducao();
							String nota;
							nota = rs_tms.getString(2);
							nota = nota.replace(" " , "");
							registro.setNota(nota);							
							registro.setStatus_entrega(rs_tms.getString(3));
							registro.setMotorista(rs_tms.getString(4));
							registro.setFrete(Formata.moeda(rs_tms.getInt(5)));
							registro.setCarro(rs_tms.getString(7));
					
							selectTms.add(registro);
									
				}
						
						for (PedidoProducao tms :  selectTms) {
							System.out.println("SOUT TMS: "+ tms.getNota()+"|"+tms.getRoteiro()+"|"+tms.getStatus_entrega()+"|"+tms.getFrete());
							
							for (PedidoProducao erp : pedidoProducao) {
								
								if(tms.getNota().equals(erp.getNota())){
									erp.setStatus_entrega(tms.getStatus_entrega()!=null ? tms.getStatus_entrega() : "pendente");
									erp.setMotorista(tms.getMotorista().equals(null) ? "montagem TMS pendente" :tms.getMotorista() );
									erp.setFrete(tms.getFrete());
									erp.setCarro(tms.getCarro());
									break;
								}
							}
							
						}
						
						
			

			}//fim IF
			
			
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		for (PedidoProducao p:pedidoProducao) {
			System.out.println(p.getNu_pedido()+"|"+p.getRoteiro()+"|"+p.getStatus_entrega());

		}
		
		
		
		return pedidoProducao;
	}
	
		public String conversorListaIntSql(List<PedidoProducao> notas) {
			
			String inNotas="";
			
			int tamanhoLista = 0;
					
			tamanhoLista = notas.size();
			
			int i = 1;
			
			if(tamanhoLista>0) {
				for (PedidoProducao p : notas) {
					String registro= "'"+p.getNota()+"'";
					if (i < tamanhoLista) {
						inNotas +=  registro + ", ";
					} else {
						inNotas += registro;
					}
		
					i++;
				}
			}	

			
			return inNotas;
	
		}	
		
		
		public List<CodDescricao> getStatusEntrega(){
			List<CodDescricao> statusEntrega = new ArrayList<>();
			
			String sql_tms="select id_Ocorrencia, ds_Ocorrencia from tbdOcorrencia\r\n" + 
					"where id_Ocorrencia in (5,70,15,30,32,19,28,26,3,18,45,53,7,1,17)\r\n" + 
					"order by ds_Ocorrencia";
			
			try {
				
				PreparedStatement stmt_tms = connectionSqlServerTms.prepareStatement(sql_tms);
				ResultSet rs_tms = stmt_tms.executeQuery();
				
				while(rs_tms.next()) {
					CodDescricao registro = new CodDescricao();
					
					registro.setCodigo(rs_tms.getString(1));
					registro.setDescricao(rs_tms.getString(2));
					
					statusEntrega.add(registro);
					
				}
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
			
			
			return statusEntrega;
		}
		
}
	
	
	