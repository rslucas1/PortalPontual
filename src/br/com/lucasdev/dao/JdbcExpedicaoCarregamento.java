package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.ExpedicaoCarregamento;

public class JdbcExpedicaoCarregamento {

	private Connection connectionSqlServer;
	private Connection connectionMySql;
	
	public JdbcExpedicaoCarregamento() {
		
		
		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			connectionMySql = new ConnectionFactory().getConnectionMySql();
			
			
			
			
						
		
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
	}
	
	public List<ExpedicaoCarregamento> getExpedicaoCarregamento(String nu_romaneio){
		List<ExpedicaoCarregamento> expedicaoCarregamento = new ArrayList<>();
		
		String sqlBuscaCarga="select distinct nu_rom, n.nu_nf_emp_fat , r.nu_ped, p.cd_clien, c.nome, n.vl_tot_nf, convert(varchar(10), e.dt_carregamento,103) as 'DT_CARREGAMENTO'\r\n" + 
				"\r\n" + 
				" from it_rom r\r\n" + 
				"\r\n" + 
				"left join ped_vda p\r\n" + 
				"on r.nu_ped=p.nu_ped\r\n" + 
				"\r\n" + 
				"left join cliente c\r\n" + 
				"on p.cd_clien=c.cd_clien\r\n" + 
				"\r\n" + 
				"left join nota n\r\n" + 
				"on p.nu_ped = n.nu_ped\r\n" + 
				"\r\n" + 
				"left join portal_entrega e\r\n" + 
				"ON e.nu_nota = n.nu_nf_emp_fat\r\n" + 
				"\r\n" + 
				"where r.cd_emp IN (13, 20)\r\n" + 
				"and nu_rom="+nu_romaneio+"\r\n" + 
				"and r.situacao in ('AB','EN', 'PE')\r\n" + 
				"\r\n" + 
				"order by n.nu_nf_emp_fat";
		
		
				System.out.println(sqlBuscaCarga);
		
		try {
			PreparedStatement stmt_1 = this.connectionSqlServer.prepareStatement(sqlBuscaCarga);
			ResultSet rs_1 = stmt_1.executeQuery();
			
			while(rs_1.next()){
				
				ExpedicaoCarregamento registro = new ExpedicaoCarregamento();
				
				registro.setNota(rs_1.getInt(2));
				registro.setNu_ped(rs_1.getInt(3));
				registro.setCliente(rs_1.getString(4)+" - "+rs_1.getString(5));
				registro.setVl_nota(rs_1.getDouble(6));
				registro.setSituacao(rs_1.getString(7)!=null ? "Saída confirmada em "+rs_1.getString(7) : "aguardando saída");
				
				expedicaoCarregamento.add(registro);
				
			}
			
			
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
		
		
		return expedicaoCarregamento;
		
	}
	
		public boolean setCarregamento(String usuario, List<ExpedicaoCarregamento> notas, String data, String carga) {
			
			
			try {
				
				PreparedStatement stmt_insert;
				
				for (ExpedicaoCarregamento n : notas ) {
					
					PreparedStatement stmt_consulta = this.connectionSqlServer.prepareStatement("select nu_nota from portal_entrega where nu_nota="+n.getNota());
					
					ResultSet rs = stmt_consulta.executeQuery();
					
					if(!rs.next()) {
						String sql="insert into portal_entrega (nu_nota, nu_ped, usr_carregamento, dt_carregamento, romaneio) values ("+n.getNota()+","+n.getNu_ped()+",'"+usuario+"','"+data+"','"+carga+"')";
						stmt_insert = this.connectionSqlServer.prepareStatement(sql);
						stmt_insert.execute();
						
					}
					
												
				}
							
			
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
					
				
			return false;
			
		} 
		
		
		public List<ExpedicaoCarregamento> consultaRomaneio(String romaneio){			
			List<ExpedicaoCarregamento> expedicaoCarregamento = new ArrayList<>();
			
			String sql = "select distinct e.nu_nota, e.nu_ped, CONVERT(varchar(10), e.dt_carregamento,103)as 'dt_carregamento', p.cd_clien, c.nome, e.romaneio, convert(varchar(10), e.dt_entrega,103) from portal_entrega e\r\n" + 
					"left join ped_vda p\r\n" + 
					"ON p.nu_ped=e.nu_ped and p.cd_emp IN (13, 20)\r\n" + 
					"left join cliente c\r\n" + 
					"ON c.cd_clien=p.cd_clien\r\n" + 
					"where romaneio='"+romaneio+"'";
			
			System.out.println(sql);
			
			
			try {
				PreparedStatement stmt_1 = this.connectionSqlServer.prepareStatement(sql);
				ResultSet rs_1 = stmt_1.executeQuery();
				
				while(rs_1.next()){
					
					ExpedicaoCarregamento registro = new ExpedicaoCarregamento();
					
					registro.setNota(rs_1.getInt(1));
					registro.setNu_ped(rs_1.getInt(2));
					registro.setDataCarregamento(rs_1.getString(3));
					registro.setCliente(rs_1.getString(4)+" - "+rs_1.getString(5));
					registro.setNu_romaneio(rs_1.getString(6));
					registro.setSituacao(rs_1.getString(7)!=null ? "Entrega confirmada em "+rs_1.getString(7) : "pendente");
					
					expedicaoCarregamento.add(registro);
					
					
				}
				
				
				
				
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
				
			}
			
			
			return expedicaoCarregamento;
			
		}
		
		public List<ExpedicaoCarregamento> confirmaEntrega(String data, String usuario, String notas){
			List<ExpedicaoCarregamento> expedicaoCarregamento = new ArrayList<>();
			
			String update="update portal_entrega set dt_entrega='"+data+"', usr_entrega='"+usuario+"' where nu_nota in ("+notas+")";
			String select="select romaneio, nu_nota, nu_ped, convert(varchar(10), dt_carregamento, 103) as 'dt_carregamento', convert(varchar(10), dt_entrega, 103) as'dt_entrega' from portal_entrega where nu_nota in("+notas+") and dt_entrega is not null";
			
			
			try {
				
				PreparedStatement stmtUpdate = this.connectionSqlServer.prepareStatement(update);
				stmtUpdate.execute();
				
				PreparedStatement stmtSelect = this.connectionSqlServer.prepareStatement(select);
				
				ResultSet rs = stmtSelect.executeQuery();
				
				while(rs.next()) {
					ExpedicaoCarregamento registro = new ExpedicaoCarregamento();
					
					registro.setNu_romaneio(rs.getString(1));
					registro.setNota(rs.getInt(2));
					registro.setNu_ped(rs.getInt(3));
					registro.setDataCarregamento(rs.getString(4));
					registro.setDataEntrega(rs.getString(5));
					registro.setConfCarregamento("Entrega Confirmada");
					
					expedicaoCarregamento.add(registro);
					
				}
					
				
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
				
			}
			
			
			return expedicaoCarregamento;
		}
		
		
		public String setObervacao(String texto, String pedido, String operacao) {
					
				String tupla="";
			
					try {
						
						
						
						
						if(operacao!=null) {
							if(operacao.equals("gravar")) {
								
								PreparedStatement stmt_verifica = this.connectionSqlServer.prepareStatement("select nu_ped, observacao from observacaoPortal where nu_ped="+pedido);
								ResultSet rs_1 = stmt_verifica.executeQuery();
								System.out.println("select nu_ped, observacao from observacaoPortal where nu_ped="+pedido);
								
								if(!rs_1.next()) {
									PreparedStatement stmt_insert = this.connectionSqlServer.prepareStatement("insert into observacaoPortal (nu_ped, observacao) values ("+pedido+", '"+texto+"')");
									System.out.println("insert into observacaoPortal (nu_ped, observacao) values ("+pedido+", '"+texto+"')");
									stmt_insert.execute();
									
									
									PreparedStatement stmt_select = this.connectionSqlServer.prepareStatement("select observacao from observacaoPortal where nu_ped="+pedido);
									ResultSet rs_2 = stmt_select.executeQuery();
									if(rs_2.next()) {
										tupla =rs_2.getString(1);
									}
									
									
									return tupla;
								}
								
														
								
								PreparedStatement stmt_update = this.connectionSqlServer.prepareStatement("update observacaoPortal set observacao='"+texto+"' where nu_ped="+pedido);
								System.out.println("update observacaoPortal set observacao='"+texto+"' where nu_ped="+pedido);
								stmt_update.execute();								
								
							}
											
							
						}
							
							
							PreparedStatement stmt_select = this.connectionSqlServer.prepareStatement("select observacao from observacaoPortal where nu_ped="+pedido);
							ResultSet rs_2 = stmt_select.executeQuery();
							if(rs_2.next()) {
								tupla =rs_2.getString(1);
							}
					
						
					}catch(SQLException e) {
						throw new RuntimeException(e);
					}
							
						
					return tupla;
					
				} 
		
		
		
		public List<ExpedicaoCarregamento> relatorio(String dataInicial, String dataFinal, String operacao, String ordenacao, String pendente,String concluido){
			List<ExpedicaoCarregamento> expedicaoCarregamento = new ArrayList<>();
			LocalDate hoje = LocalDate.now();
			
			
			String sql="select distinct\r\n" + 
					"p.nu_ped,\r\n" + 
					"p.cd_clien,\r\n" + 
					"c.nome,\r\n" + 
					"tp.descricao,\r\n" + 
					"CONVERT(VARCHAR(10), p.dt_cad, 103) AS 'ENTRADA ERP',\r\n" + 
					"DATEDIFF (day, p.dt_cad, ev.dt_criacao) as 'intervalo1',\r\n" + 
					"CONVERT (VARCHAR(10),ev.dt_criacao, 103) AS 'ENTRADA WMS',\r\n" + 
					"DATEDIFF (day, ev.dt_criacao, n.dt_emis) as 'intervalo2',\r\n" + 
					"CONVERT (VARCHAR(10),n.dt_emis, 103) AS 'DT FATURAMENTO',\r\n" + 
					"DATEDIFF (day, n.dt_emis, e.dt_carregamento) as 'intervalo3',\r\n" + 
					"CONVERT (VARCHAR(10),e.dt_carregamento, 103) as 'DT CARREGAMENTO',\r\n" + 
					"DATEDIFF (day, e.dt_carregamento, e.dt_entrega) as 'intervalo4',\r\n" + 
					"CONVERT (VARCHAR(10),e.dt_entrega, 103) as 'DT ENTREGA',\r\n" + 
					"\r\n" + 
					"n.nu_nf_emp_fat,\r\n" + 
					"ir.nu_rom,\r\n" + 
					"PG.descricao,\r\n" + 
					"DATEDIFF (day, e.dt_entrega, tit.dt_venc_orig) as 'DIAS A VENCER',\r\n" + 
					"CONVERT (VARCHAR(10),tit.dt_venc_orig, 103) AS 'DT VENCIMENTO',\r\n" + 
					"o.observacao,\r\n" + 
					"p.dt_cad,\r\n" + 
					"ev.dt_criacao,\r\n" + 
					"n.dt_emis,\r\n" + 
					"e.dt_carregamento,\r\n" + 
					"e.dt_entrega,\r\n" + 
					"ec.cep,\r\n" + 
					"ec.distrito,\r\n" + 
					"ec.bairro,\r\n" + 
					"n.vl_tot_nf\r\n" + 
					
					"\r\n" + 
					"\r\n" + 
					"from ped_vda p\r\n" + 
					"\r\n" + 
					"join cliente c\r\n" + 
					"ON c.cd_clien=p.cd_clien\r\n" + 
					"\r\n" + 
					"join tp_ped tp\r\n" + 
					"ON p.tp_ped = tp.tp_ped\r\n" + 
					"\r\n" + 
					"left join evento ev\r\n" + 
					"ON ev.nu_ped = p.nu_ped and ev.cd_fila='SEPA' and ev.cd_emp=13\r\n" + 
					"\r\n" + 
					"left join nota n\r\n" + 
					"ON n.nu_ped = p.nu_ped\r\n" + 
					"\r\n" + 
					"left join it_rom ir\r\n" + 
					"ON ir.nu_ped = p.nu_ped\r\n" + 
					"\r\n" + 
					"join promocao pg\r\n" + 
					"on pg.seq_prom = p.seq_prom\r\n" + 
					"\r\n" + 
					"left join titrec tit\r\n" + 
					"ON tit.nu_tit_emp_fat=n.nu_nf_emp_fat AND tit.serie not in ('B','C','D','E','F','G')\r\n" + 
					"\r\n" + 
					"left join portal_entrega e\r\n" + 
					"ON e.nu_nota = n.nu_nf_emp_fat\r\n" + 
					"\r\n" + 
					"left join observacaoPortal o\r\n" + 
					"ON o.nu_ped = p.nu_ped\r\n" + 
					"\r\n" + 
					"join end_cli ec\r\n" + 
					"ON c.cd_clien = EC.cd_clien and ec.tp_end='FA'\r\n" + 
					"\r\n" + 
					"where p.cd_emp IN (13, 20)\r\n" + 
					"and p.dt_cad between '"+dataInicial+" 00:00:00' and '"+dataFinal+" 23:59:00'\r\n" + 
					"AND p.seq_prom not in (360)\r\n" + 
					"AND p.tp_ped not in ('MD')\r\n" +
					"AND p.situacao NOT IN ('CA')\r\n" + 
					"order by "+ordenacao+" desc";
					
//					System.out.println(sql);
			
			
			try {
				PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					int total=0;
//					int intervaloInt=0;
					ExpedicaoCarregamento registro = new ExpedicaoCarregamento();
					
					registro.setNu_ped(rs.getInt(1));
					registro.setCliente(rs.getString(2)+" - "+rs.getString(3));
					registro.setTipoPedido(rs.getString(4));
					registro.setDataCadastro(rs.getString(5));								
					registro.setIntervalo1(rs.getInt(6));					
					registro.setDataEntradaWms(rs.getString(7));					
					registro.setIntervalo2(rs.getInt(8));
					registro.setDataFaturamento(rs.getString(9));					
					registro.setIntervalo3(rs.getInt(10));					
					registro.setDataCarregamento(rs.getString(11));
					registro.setIntervalo4(rs.getInt(12));
					registro.setDataEntrega(rs.getString(13));
					registro.setConclusao(rs.getString(13)!=null? "OK" :"pendente");
					registro.setNota(rs.getInt(14));
					registro.setNu_romaneio(rs.getString(15));
					registro.setCondPgto(rs.getString(16));
					registro.setDataVencimento(rs.getString(18));
					
					registro.setDiasVencer(rs.getString(17));
					registro.setObservacao(rs.getString(19));
					registro.setCep(rs.getString(25));
					registro.setDistrito(rs.getString(26));
					registro.setBairro(rs.getString(27));
					registro.setVl_nota(rs.getDouble(28));
					
					
					if(rs.getString(7)==null) { //IF NAO ENTROU NO WMS
						LocalDate dataCadPedido = LocalDate.parse(rs.getString(20).substring(0,10));
						Period periodo1 = Period.between(dataCadPedido, hoje);
						registro.setIntervalo1(periodo1.getDays());
																	
					} else if(rs.getString(9)==null) {
						LocalDate dataEntradaWms = LocalDate.parse(rs.getString(21).substring(0,10));
						Period periodo2 = Period.between(dataEntradaWms, hoje);
						registro.setIntervalo2(periodo2.getDays());
						
					} else if(rs.getString(11)==null) {
						LocalDate dataFaturamento = LocalDate.parse(rs.getString(22).substring(0,10));
						Period periodo3 = Period.between(dataFaturamento, hoje);
						registro.setIntervalo3(periodo3.getDays());
						
					} else if(rs.getString(13)==null) {
						LocalDate dataCarregamento = LocalDate.parse(rs.getString(23).substring(0,10));
						Period periodo4 = Period.between(dataCarregamento, hoje);
						registro.setIntervalo4(periodo4.getDays());
						
						
//						System.out.println(registro.getNu_ped());
//						System.out.println(periodo4);
//						System.out.println(dataCarregamento);
//						System.out.println(hoje);
						
												
					}
					
					total= registro.getIntervalo1() + registro.getIntervalo2() +registro.getIntervalo3() +registro.getIntervalo4();
					
					
					registro.setTotal(total);
					
					
					
					if(operacao!=null){
						if(operacao.equals("Atrasados")) {
							if(total>=4) {
								if(registro.getConclusao().equals("pendente")) {
								expedicaoCarregamento.add(registro);
								}
							}
							
						}
						else if(operacao.equals("Buscar")) {
							
							if(pendente.equals(registro.getConclusao())) {
								expedicaoCarregamento.add(registro);
								
							}else if(concluido.equals(registro.getConclusao())) {
								expedicaoCarregamento.add(registro);
								
							}
							
							
						}
						
					}
					
					
					
					
				}
				
				
				
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
				
			}
			
			
			
			return expedicaoCarregamento;
			
		}
		
		
	
	
	
}
