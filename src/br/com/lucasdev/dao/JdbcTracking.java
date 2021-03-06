package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.PedidosDiario;
import br.com.lucasdev.modelo.relatorios.Romaneio;
import br.com.lucasdev.modelo.relatorios.Tracking;
import br.com.lucasdev.modelo.relatorios.TrackingExpedicao;
import br.com.lucasdev.util.Formata;

public class JdbcTracking {
	
	private Connection connectionSqlServerErp;
	private Connection connectionOracle;
	private Connection mySql;
	
	public JdbcTracking() {
		
		
		try {
			connectionSqlServerErp = new ConnectionFactory().getConnectionSqlServer();
			connectionOracle = new ConnectionFactory().getConnectionOracle();
			mySql = new ConnectionFactory().getConnectionMySql();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
						
	}
	
	
	public Tracking trkPendenciaEletronica() {
		
		Tracking registro = new Tracking();
		
		String sqlTotal ="SELECT COUNT(PedVdaEleID), SUM(valor_tot)  FROM ped_vda_ele\r\n" + 
				"  WHERE cd_emp_ele IN (13,20) \r\n" + 
				"  AND situacao IN ('ab')\r\n" + 
				"  AND pend_ele_libera_auto IS NULL";
		
		
		
			String sqlUltPed ="SELECT TOP 1 CONVERT(VARCHAR(10),dt_ped,103)AS 'DATA' FROM [MOINHO].[dbo].[ped_vda_ele]\r\n" + 
					"  WHERE cd_emp_ele IN (13,20) \r\n" + 
					"  AND situacao IN ('ab')\r\n" + 
					"  --AND cd_clien IN (51029, 13089, 39974) \r\n" + 
					"  AND pend_ele_libera_auto IS NULL\r\n" + 
					"  ORDER BY dt_ped ASC";
			
			
			try {
				PreparedStatement stmt1 = (PreparedStatement) connectionSqlServerErp.prepareStatement(sqlTotal);
				ResultSet rs1 = stmt1.executeQuery();
				
				PreparedStatement stmt2 = (PreparedStatement) connectionSqlServerErp.prepareStatement(sqlUltPed);
				ResultSet rs2 = stmt2.executeQuery();
								
				if(rs1.next()) {
					registro.setQtdPedido(rs1.getInt(1));
					registro.setValor(rs1.getDouble(2));
					registro.setValorMoeda(Formata.moeda(registro.getValor()));					
					if(rs2.next()) {
					registro.setUltPed(rs2.getString(1));
					
					}
				} else {
					
					registro.setQtdPedido(0);
					registro.setValorMoeda("R$ 0,00");
					registro.setUltPed("-");
										
				}
				
		
								
			}catch(SQLException e) {
					throw new RuntimeException(e);
			}
			
			if(registro.getUltPed()==null) {
				registro.setUltPed("-");
				
			}
			
			if(registro.getValorMoeda()==null) {
				registro.setUltPed("R$ 0,00");
				
			}
			
		
		
		return registro;
		
		
		
	}
	
	public Tracking trkAdministracao() {
		Tracking registro = new Tracking();
		
		String sqlTotal ="select distinct \r\n" + 
				"--ev.nu_ped Pedido,\r\n" + 
				"--convert(varchar, p.dt_cad, 103) [DataPedido],\r\n" + 
				"--ev.cd_clien Cliente,\r\n" + 
				"--cl.nome Nome ,\r\n" + 
				"--f.cd_fila Etapa,\r\n" + 
				"COUNT(ev.nu_ped),\r\n" + 
				"SUM(cast (p.valor_tot as money)) as Valor\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"from evento ev\r\n" + 
				"join cliente cl on cl.cd_clien = ev.cd_clien\r\n" + 
				"join ped_vda p on p.nu_ped = ev.nu_ped and ev.cd_clien = p.cd_clien and ev.cd_emp = p.cd_emp\r\n" + 
				"join fila f on f.cd_fila = ev.cd_fila\r\n" + 
				"\r\n" + 
				"where  \r\n" + 
				"--ev.nu_ped=961691 and\r\n" + 
				"ev.situacao = 'AB' \r\n" + 
				"and ev.dt_criacao> '2020-11-01 00:00:00'\r\n" + 
				"and ev.cd_emp IN (13, 20) \r\n" + 
				"and p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC')\r\n" + 
				"and ev.cd_fila IN ('BLGV','GERV', 'ADMV', 'ALTP', 'BLAV' , 'CAPV')";
		
		
		
			String sqlUltPed ="select TOP 1\r\n" + 
					"--ev.nu_ped Pedido\r\n" + 
					"convert(varchar, p.dt_cad, 103) [DataPedido]\r\n" + 
					"--ev.cd_clien Cliente,\r\n" + 
					"--cl.nome Nome ,\r\n" + 
					"--f.cd_fila Etapa,\r\n" + 
					"--cast (p.valor_tot as money) Valor\r\n" + 
					"--COUNT(ev.nu_ped)\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"from evento ev\r\n" + 
					"join cliente cl on cl.cd_clien = ev.cd_clien\r\n" + 
					"join ped_vda p on p.nu_ped = ev.nu_ped and ev.cd_clien = p.cd_clien and ev.cd_emp = p.cd_emp\r\n" + 
					"join fila f on f.cd_fila = ev.cd_fila\r\n" + 
					"\r\n" + 
					"where  \r\n" + 
					"--ev.nu_ped=961691 and\r\n" + 
					"ev.situacao = 'AB' \r\n" + 
					"and ev.dt_criacao> '2020-11-01 00:00:00'\r\n" + 
					"and ev.cd_emp IN (13, 20) \r\n" + 
					"and p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC')\r\n" + 
					"and ev.cd_fila IN ('BLGV','GERV', 'ADMV', 'ALTP', 'BLAV' , 'CAPV')\r\n" + 
					"\r\n" + 
					"ORDER BY 1 ASC";
			
			
			
			
			try {
				PreparedStatement stmt1 = (PreparedStatement) connectionSqlServerErp.prepareStatement(sqlTotal);
				ResultSet rs1 = stmt1.executeQuery();
				
				PreparedStatement stmt2 = (PreparedStatement) connectionSqlServerErp.prepareStatement(sqlUltPed);
				ResultSet rs2 = stmt2.executeQuery();
								
				if(rs1.next()) {
					registro.setQtdPedido(rs1.getInt(1));
					registro.setValor(rs1.getDouble(2));
					registro.setValorMoeda(Formata.moeda(registro.getValor()));					
					if(rs2.next()) {
					registro.setUltPed(rs2.getString(1));
					
					}
				} else {
					
					registro.setQtdPedido(0);
					registro.setValorMoeda("R$ 0,00");
					registro.setUltPed("-");
										
				}
				
				if(registro.getUltPed()==null) {
					registro.setUltPed("-");
					
				}
				
				if(registro.getValorMoeda()==null) {
					registro.setUltPed("R$ 0,00");
					
				}
				
		
								
			}catch(SQLException e) {
					throw new RuntimeException(e);
			}
			
			
		
		
		return registro;
	}
	
	
	
	public Tracking trkCredito() {
		
	
			Tracking registro = new Tracking();
			
			String sqlTotal ="select distinct \r\n" + 
					"--ev.nu_ped Pedido,\r\n" + 
					"--convert(varchar, p.dt_cad, 103) [DataPedido],\r\n" + 
					"--ev.cd_clien Cliente,\r\n" + 
					"--cl.nome Nome ,\r\n" + 
					"--f.cd_fila Etapa,\r\n" + 
					"COUNT(ev.nu_ped),\r\n" + 
					"SUM(cast (p.valor_tot as money)) as Valor\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"from evento ev\r\n" + 
					"join cliente cl on cl.cd_clien = ev.cd_clien\r\n" + 
					"join ped_vda p on p.nu_ped = ev.nu_ped and ev.cd_clien = p.cd_clien and ev.cd_emp = p.cd_emp\r\n" + 
					"join fila f on f.cd_fila = ev.cd_fila\r\n" + 
					"\r\n" + 
					"where  \r\n" + 
					"--ev.nu_ped=961691 and\r\n" + 
					"ev.situacao = 'AB' \r\n" + 
					"and ev.dt_criacao> '2020-11-01 00:00:00'\r\n" + 
					"and ev.cd_emp IN (13, 20) \r\n" + 
					"and p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC')\r\n" + 
					"and ev.cd_fila IN ('BLOQ','CRED')";
			
			
			
				String sqlUltPed ="select TOP 1\r\n" + 
						"--ev.nu_ped Pedido\r\n" + 
						"convert(varchar, p.dt_cad, 103) [DataPedido]\r\n" + 
						"--ev.cd_clien Cliente,\r\n" + 
						"--cl.nome Nome ,\r\n" + 
						"--f.cd_fila Etapa,\r\n" + 
						"--cast (p.valor_tot as money) Valor\r\n" + 
						"--COUNT(ev.nu_ped)\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"from evento ev\r\n" + 
						"join cliente cl on cl.cd_clien = ev.cd_clien\r\n" + 
						"join ped_vda p on p.nu_ped = ev.nu_ped and ev.cd_clien = p.cd_clien and ev.cd_emp = p.cd_emp\r\n" + 
						"join fila f on f.cd_fila = ev.cd_fila\r\n" + 
						"\r\n" + 
						"where  \r\n" + 
						"--ev.nu_ped=961691 and\r\n" + 
						"ev.situacao = 'AB' \r\n" + 
						"and ev.dt_criacao> '2020-11-01 00:00:00'\r\n" + 
						"and ev.cd_emp IN (13, 20) \r\n" + 
						"and p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC')\r\n" + 
						"and ev.cd_fila IN ('BLOQ','CRED')\r\n" + 
						"\r\n" + 
						"ORDER BY 1 ASC";
				
				System.out.println(sqlTotal);
				
				
				try {
					PreparedStatement stmt1 = (PreparedStatement) connectionSqlServerErp.prepareStatement(sqlTotal);
					ResultSet rs1 = stmt1.executeQuery();
					
					PreparedStatement stmt2 = (PreparedStatement) connectionSqlServerErp.prepareStatement(sqlUltPed);
					ResultSet rs2 = stmt2.executeQuery();
									
					if(rs1.next()) {
						registro.setQtdPedido(rs1.getInt(1));
						registro.setValor(rs1.getDouble(2));
						registro.setValorMoeda(Formata.moeda(registro.getValor()));					
						if(rs2.next()) {
						registro.setUltPed(rs2.getString(1));
						
						}
					} else {
						
						registro.setQtdPedido(0);
						registro.setValorMoeda("R$ 0,00");
						registro.setUltPed("-");
											
					}
					
					if(registro.getUltPed()==null) {
						registro.setUltPed("-");
						
					}
					
					if(registro.getValorMoeda()==null) {
						registro.setUltPed("R$ 0,00");
						
					}
					
			
									
				}catch(SQLException e) {
						throw new RuntimeException(e);
				}
				
				
			
			
			return registro;
		}
	
	public Tracking trkRoteirizacao() {
		
		Tracking registro = new Tracking();
		
		
		String sqlTotal ="SELECT COUNT (NR_AUF), SUM(WERT_NACHN) FROM AUFTRAEGE WHERE  STAT IN (10, 15, 25) AND ART_EING != 'MAN'";
		
		String sqlUltPed ="SELECT * FROM (SELECT DATUM_BESTELL FROM AUFTRAEGE WHERE  STAT IN (10, 15, 25) AND ART_EING != 'MAN' ORDER BY DATUM_BESTELL ASC) WHERE ROWNUM=1";
		
		
		
		try {
			PreparedStatement stmt1 = (PreparedStatement) connectionOracle.prepareStatement(sqlTotal);
			ResultSet rs1 = stmt1.executeQuery();
			
			PreparedStatement stmt2 = (PreparedStatement) connectionOracle.prepareStatement(sqlUltPed);
			ResultSet rs2 = stmt2.executeQuery();
							
			if(rs1.next()) {
				registro.setQtdPedido(rs1.getInt(1));
				registro.setValor(rs1.getDouble(2));
				registro.setValorMoeda(Formata.moeda(registro.getValor()));					
				if(rs2.next()) {
				registro.setUltPed(Formata.data(rs2.getString(1).substring(0, 10)));
				
				}
			} else {
				
				registro.setQtdPedido(0);
				registro.setValorMoeda("R$ 0,00");
				registro.setUltPed("-");
									
			}
			
			
			
	
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		if(registro.getUltPed()==null) {
			registro.setUltPed("-");
			
		}
		
		if(registro.getValorMoeda()==null) {
			registro.setUltPed("R$ 0,00");
			
		}
		
		
		return registro;
	}
		
		
	public Tracking trkReserva() {
		
		Tracking registro = new Tracking();
		
		
		String sqlTotal ="SELECT COUNT (NR_AUF), SUM(WERT_NACHN) FROM AUFTRAEGE WHERE  STAT IN (35) AND ART_EING != 'MAN'";
		
		String sqlUltPed ="SELECT * FROM (SELECT DATUM_BESTELL FROM AUFTRAEGE WHERE  STAT IN (35) AND ART_EING != 'MAN' ORDER BY DATUM_BESTELL ASC) WHERE ROWNUM=1";
		
		
		
		try {
			PreparedStatement stmt1 = (PreparedStatement) connectionOracle.prepareStatement(sqlTotal);
			ResultSet rs1 = stmt1.executeQuery();
			
			PreparedStatement stmt2 = (PreparedStatement) connectionOracle.prepareStatement(sqlUltPed);
			ResultSet rs2 = stmt2.executeQuery();
							
			if(rs1.next()) {
				registro.setQtdPedido(rs1.getInt(1));
				registro.setValor(rs1.getDouble(2));
				registro.setValorMoeda(Formata.moeda(registro.getValor()));					
				if(rs2.next()) {
				registro.setUltPed(Formata.data(rs2.getString(1).substring(0, 10)));
				
				}
			} else {
				
				registro.setQtdPedido(0);
				registro.setValorMoeda("R$ 0,00");
				registro.setUltPed("-");
									
			}
			
			if(registro.getUltPed()==null) {
				registro.setUltPed("-");
				
			}
			
	
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		if(registro.getUltPed()==null) {
			registro.setUltPed("-");
			
		}
		
		if(registro.getValorMoeda()==null) {
			registro.setUltPed("R$ 0,00");
			
		}
		
		
		return registro;
		
	
	}
	
	public Tracking trkSeparacao() {
		Tracking registro = new Tracking();
		
		String sqlTotal ="SELECT COUNT (NR_AUF), SUM(WERT_NACHN) FROM AUFTRAEGE WHERE  STAT IN (54,55,74) AND ART_EING != 'MAN'";
		
		String sqlUltPed ="SELECT * FROM (SELECT DATUM_BESTELL FROM AUFTRAEGE WHERE  STAT IN (54,55,74) AND ART_EING != 'MAN' ORDER BY DATUM_BESTELL ASC) WHERE ROWNUM=1";
		
		
		
		try {
			PreparedStatement stmt1 = (PreparedStatement) connectionOracle.prepareStatement(sqlTotal);
			ResultSet rs1 = stmt1.executeQuery();
			
			PreparedStatement stmt2 = (PreparedStatement) connectionOracle.prepareStatement(sqlUltPed);
			ResultSet rs2 = stmt2.executeQuery();
							
			if(rs1.next()) {
				registro.setQtdPedido(rs1.getInt(1));
				registro.setValor(rs1.getDouble(2));
				registro.setValorMoeda(Formata.moeda(registro.getValor()));					
				if(rs2.next()) {
				registro.setUltPed(Formata.data(rs2.getString(1).substring(0, 10)));
				
				}
			} else {
				
				registro.setQtdPedido(0);
				registro.setValorMoeda("R$ 0,00");
				registro.setUltPed("-");
									
			}
			
			if(registro.getUltPed()==null) {
				registro.setUltPed("-");
				
			}
			
			if(registro.getValorMoeda()==null) {
				registro.setUltPed("R$ 0,00");
				
			}
	
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		System.out.println("RESERVA|"+registro.getQtdPedido()+"|"+registro.getValor()+"|"+registro.getUltPed());
		
		
		return registro;
		
		
	}
	
	public Tracking trkFaturamento() {
		Tracking registro = new Tracking();
		
		String sqlTotal ="SELECT COUNT (NR_AUF), SUM(WERT_NACHN) FROM AUFTRAEGE WHERE  STAT IN (75) AND HINW_ZUST IS NULL AND ART_EING != 'MAN' AND NR_AUF NOT IN ('940587','495','716','838','1301','1290', '1300', '1486','962358', '962359', '962366', '962368', '962369', '962355', '1504','962834')";
		
//		String sqlTotal ="select DISTINCT\r\n" + 
//				"COUNT(p.nu_ped) AS 'QTD',\r\n" + 
//				"sum(p.valor_tot) as 'VL TOTAL FAT'\r\n" + 
//				"\r\n" + 
//				"from ped_vda p\r\n" + 
//				"\r\n" + 
//				"left join nota n\r\n" + 
//				"on p.nu_ped=n.nu_ped and p.cd_emp=n.cd_emp\r\n" + 
//				"\r\n" + 
//				"FULL OUTER JOIN      OPENQUERY(WMSPRD, 'SELECT NR_AUF,STAT,CNPJ_PROPRIETARIO_ERP, HINW_ZUST, ART_EING FROM AUFTRAEGE ' ) AS WMST \r\n" + 
//				"                        ON WMST.NR_AUF = cast(P.nu_ped as varchar)\r\n" + 
//				"\r\n" + 
//				"where\r\n" + 
//				"\r\n" + 
//				"STAT IN (75) \r\n" + 
//				"AND n.vl_tot_nf IS NULL\r\n" + 
//				"AND WMST.HINW_ZUST IS NULL \r\n" + 
//				"AND WMST.ART_EING != 'MAN' \r\n" + 
//				"AND P.dt_cad> '2020-12-01'";
		
	
		
		String sqlUltPed ="SELECT * FROM (SELECT DATUM_BESTELL FROM AUFTRAEGE WHERE  STAT IN (75)  AND HINW_ZUST IS NULL AND ART_EING != 'MAN' AND NR_AUF NOT IN ('940587','495','716','838','962834') ORDER BY DATUM_BESTELL ASC ) WHERE ROWNUM=1";
		
//		String sqlUltPed="select DISTINCT TOP 1\r\n" + 
//				"CONVERT(VARCHAR(10), p.dt_cad, 103) AS 'ULTIMO PEDIDO',\r\n" + 
//				"n.situacao\r\n" + 
//				"\r\n" + 
//				"from ped_vda p\r\n" + 
//				"\r\n" + 
//				"left join nota n\r\n" + 
//				"on p.nu_ped=n.nu_ped and p.cd_emp=n.cd_emp\r\n" + 
//				"\r\n" + 
//				"FULL OUTER JOIN      OPENQUERY(WMSPRD, 'SELECT NR_AUF,STAT,CNPJ_PROPRIETARIO_ERP, HINW_ZUST, ART_EING FROM AUFTRAEGE ' ) AS WMST \r\n" + 
//				"                        ON WMST.NR_AUF = cast(P.nu_ped as varchar)\r\n" + 
//				"\r\n" + 
//				"where\r\n" + 
//				"\r\n" + 
//				"STAT IN (75) \r\n" + 
//				"AND n.vl_tot_nf IS NULL\r\n" + 
//				"AND WMST.HINW_ZUST IS NULL \r\n" + 
//				"AND WMST.ART_EING != 'MAN' \r\n" + 
//				"AND P.dt_cad> '2020-12-01'\r\n" + 
//				"\r\n" + 
//				"order by 1 ASC";
		

		try {
			PreparedStatement stmt1 = (PreparedStatement) connectionOracle.prepareStatement(sqlTotal);
			ResultSet rs1 = stmt1.executeQuery();
			
			PreparedStatement stmt2 = (PreparedStatement) connectionOracle.prepareStatement(sqlUltPed);
			ResultSet rs2 = stmt2.executeQuery();
							
			if(rs1.next()) {
				registro.setQtdPedido(rs1.getInt(1));
				registro.setValor(rs1.getDouble(2));
				registro.setValorMoeda(Formata.moeda(registro.getValor()));					
				if(rs2.next()) {
				registro.setUltPed(Formata.data(rs2.getString(1).substring(0, 10)));
				
				}
			} else {
				
				registro.setQtdPedido(0);
				registro.setValorMoeda("R$ 0,00");
				registro.setUltPed("-");
									
			}
			
			if(registro.getUltPed()==null) {
				registro.setUltPed("-");
				
			}
			
			
			if(registro.getValorMoeda()==null) {
				registro.setUltPed("R$ 0,00");
				
			}
	
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		return registro;
		
		
	}
	
	public Tracking trkExpedicao() {
		Tracking registro = new Tracking();
		
		String sqlTotal ="SELECT COUNT (NR_AUF), SUM(WERT_NACHN) FROM AUFTRAEGE WHERE  STAT IN (75) AND HINW_ZUST IS not NULL AND ART_EING != 'MAN' AND NR_AUF NOT IN ('940587') and DATUM_BESTELL > '01-11-2020'";
		
		String sqlUltPed ="SELECT * FROM (SELECT DATUM_BESTELL FROM AUFTRAEGE WHERE  STAT IN (75)  AND HINW_ZUST IS  not NULL AND ART_EING != 'MAN' AND NR_AUF NOT IN ('940587')  and DATUM_BESTELL > '01-11-2020' ORDER BY DATUM_BESTELL ASC ) WHERE ROWNUM=1";
		
		
		
		try {
			PreparedStatement stmt1 = (PreparedStatement) connectionOracle.prepareStatement(sqlTotal);
			ResultSet rs1 = stmt1.executeQuery();
			
			PreparedStatement stmt2 = (PreparedStatement) connectionOracle.prepareStatement(sqlUltPed);
			ResultSet rs2 = stmt2.executeQuery();
							
			if(rs1.next()) {
				registro.setQtdPedido(rs1.getInt(1));
				registro.setValor(rs1.getDouble(2));
				registro.setValorMoeda(Formata.moeda(registro.getValor()));					
				if(rs2.next()) {
				registro.setUltPed(Formata.data(rs2.getString(1).substring(0, 10)));
				
				}
			} else {
				
				registro.setQtdPedido(0);
				registro.setValorMoeda("R$ 0,00");
				registro.setUltPed("-");
									
			}
			
			if(registro.getUltPed()==null) {
				registro.setUltPed("-");
				
			}
			
			
			if(registro.getValorMoeda()==null) {
				registro.setUltPed("R$ 0,00");
				
			}
	
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		System.out.println("FATURAMENTO|"+registro.getQtdPedido()+"|"+registro.getValor()+"|"+registro.getUltPed());
		
		return registro;		
		
		
	}
	
	public List<TrackingExpedicao> detalheExpedicao(){
//		public void detalheExpedicao(){
		List<TrackingExpedicao> detalheExpedicao = new ArrayList<>();
		
		String nuPedWms = "SELECT NR_AUF FROM AUFTRAEGE WHERE  STAT IN (75) AND HINW_ZUST IS not NULL AND ART_EING != 'MAN' AND NR_AUF NOT IN ('940587') and DATUM_BESTELL > '01-11-2020' --CAPTURA O NUMERO DOS PEDIDOS";
		String pedidos ="";
		
		
		try {
			PreparedStatement stmt1 = (PreparedStatement) connectionOracle.prepareStatement(nuPedWms);
			ResultSet rs1 = stmt1.executeQuery();
			
			while(rs1.next()) {
				pedidos += rs1.getString(1) +", ";
						
			}
			pedidos = pedidos.substring(0, pedidos.length() - 2);
			
			
			
			String pedidoErp="select distinct\r\n" + 
					"convert(varchar(10), p.dt_cad, 103) as 'dt',\r\n" + 
					"p.nu_ped,\r\n" + 
					"p.cd_clien,\r\n" + 
					"c.nome,\r\n" + 
					"n.vl_tot_nf,\r\n" + 
					"n.nu_nf_emp_fat,\r\n" + 
					"ir.nu_rom\r\n" + 
					"--ir.situacao\r\n" + 
					"--n.desc_nat_oper\r\n" + 
					"from ped_vda p\r\n" + 
					"left join it_rom ir\r\n" + 
					"on ir.nu_ped=p.nu_ped and ir.situacao IN ('PE')\r\n" + 
					"join cliente c\r\n" + 
					"on p.cd_clien=c.cd_clien\r\n" + 
					"left join nota n\r\n" + 
					"on p.nu_ped = n.nu_ped "
					+ "and n.desc_nat_oper in ('VENDA MERC SUJ ICMS ST', 'VENDA DE MERCADORIA') "
					+ "and n.dt_emis > '2020-01-01 00:00:00'\r\n" + 
					"where p.cd_emp in (13,20)\r\n" + 
					"and p.dt_cad>'2020-01-01 00:00:00'\r\n" + 
					"and p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC')" + 
					"and p.nu_ped IN ("+pedidos+")\r\n" + 
					"";
			
			
			System.out.println(pedidoErp);
			
			PreparedStatement stmt2 = (PreparedStatement) connectionSqlServerErp.prepareStatement(pedidoErp);
			ResultSet rs2 = stmt2.executeQuery();
						
			while(rs2.next()) {
				TrackingExpedicao registro = new TrackingExpedicao();
				
				registro.setDtCadastro(rs2.getString(1));
				registro.setNuPed(rs2.getString(2));
				registro.setCliente(rs2.getString(3)+"-"+rs2.getString(4));
				registro.setValorMoeda(Formata.moeda(rs2.getDouble(5)));
				registro.setNotaFiscal(rs2.getString(6));
				registro.setCarga(rs2.getString(7));
				
				detalheExpedicao.add(registro);
				
			}
			
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		
		
		return detalheExpedicao;
	}
	
	
	public List<TrackingExpedicao> agendamento(String nuPed, String dt_agendamento, String usuario, String texto, String operacao) {
		
		List<TrackingExpedicao> agendamentos = new ArrayList<>();
		LocalDate hoje = LocalDate.now(); 
		
		try {
			
			if(operacao.equals("consultaAgendamentos")) {
				PreparedStatement stmt_verifica = (PreparedStatement) mySql.prepareStatement("SELECT nuPed, dt_agendamento from agendamentopedido");
				
				ResultSet rs_1 = stmt_verifica.executeQuery();
				while(rs_1.next()) {
					TrackingExpedicao registro = new TrackingExpedicao();
					registro.setNuPed(rs_1.getString(1));
					registro.setAg_dt_Agedamento(rs_1.getString(2));
					
					agendamentos.add(registro);
					
				}
				
				
			} else if(operacao.equals("insereRegistro")) {
								
				PreparedStatement stmt_verifica = (PreparedStatement) mySql.prepareStatement("SELECT * FROM agendamentopedido WHERE nuPed="+nuPed);
				
				ResultSet rs_1 = stmt_verifica.executeQuery();
						
				if(!rs_1.next()) {
					
				
					PreparedStatement stmt_insert = this.mySql.prepareStatement("insert into agendamentopedido (nuPed, dt_agendamento, dt_alteracao, usuario, observacao) VALUES ("+nuPed+",'"+dt_agendamento+"','"+hoje+"','"+usuario+"','"+texto+" <--- alterado por "+usuario+" em "+hoje+"*******')");
					System.out.println(stmt_insert);
					stmt_insert.execute();
				}else {
										
					
					PreparedStatement stmt_updateData = this.mySql.prepareStatement("UPDATE agendamentopedido SET dt_agendamento='"+dt_agendamento+"'  where nuPed="+nuPed);
					PreparedStatement stmt_updateObservacao = this.mySql.prepareStatement("UPDATE agendamentopedido SET observacao='"+rs_1.getString(3)+" | "+texto+" <-- alterado por "+usuario+" em "+hoje+"**' where nuPed="+nuPed);
															
					stmt_updateData.execute();
					stmt_updateObservacao.execute();
					
				}
				
			} else if (operacao.equals("detalhaAgendamento")) {
				
				
				PreparedStatement stmt_verifica = (PreparedStatement) mySql.prepareStatement("SELECT nuPed, dt_agendamento, observacao from agendamentopedido where nuPed="+nuPed);
				System.out.println("EXECUTANDO O detalhaAgendamento "+stmt_verifica);
				ResultSet rs_1 = stmt_verifica.executeQuery();
				
				if(rs_1.next()) {
					TrackingExpedicao registro = new TrackingExpedicao();
					registro.setNuPed(rs_1.getString(1));
					registro.setAg_dt_Agedamento(rs_1.getString(2));
					registro.setAg_texto(rs_1.getString(3));
					
					
					agendamentos.add(registro);

					
				}
				
			}else if (operacao.equals("apagar")) {
				
				
				String verifica="SELECT * FROM agendamentopedido WHERE nuPed="+nuPed;
				System.out.println(verifica);
				PreparedStatement stmt_verifica = (PreparedStatement) mySql.prepareStatement(verifica);
				System.out.println("SUCCESS PreparedStatement");
				ResultSet rs_1 = stmt_verifica.executeQuery();
				System.out.println("SUCCESS EXECUTEQUERY");
				
				if(rs_1.next()) {
				System.out.println("CONSEGUI LOCALIZAR O PEDIDO="+rs_1.getString(1));
				System.out.println("SUCCESS EXIBIR COLUNA1");
				}
								
				String insere="insert into agendamentodeletado (nuped, dt_agendamento, dt_delecao, observacao, usuario) values ("+rs_1.getString(1)+", '"+rs_1.getString(2)+"','"+hoje+"', '" +rs_1.getString(3)+"', '" + usuario+"')";
				System.out.println(insere);
				PreparedStatement stmt_insert = (PreparedStatement) mySql.prepareStatement(insere);
				stmt_insert.execute();
			
				String deleta="delete from agendamentopedido where nuPed="+nuPed;
				System.out.println(deleta);
				PreparedStatement stmt_delete = (PreparedStatement) mySql.prepareStatement(deleta);
				stmt_delete.execute();
				
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return agendamentos;
		
	}
	
	public Romaneio romaneio( String carga) {
			Romaneio romaneio = new Romaneio();
		 
		 String sql_1 ="select distinct r.nu_rom, r.descricao, r.cd_motor, r.dt_montagem, m.nome,  r.cd_veic_ent, v.descricao, v.placa\r\n" + 
					"from romaneio r\r\n" + 
					"left join motor m\r\n" + 
					"on m.cd_motor=r.cd_motor\r\n" + 
					"left join veic_ent v\r\n" + 
					"on r.cd_veic_ent = v.cd_veic_ent\r\n" + 
					"left join it_rom it\r\n" + 
					"on it.nu_rom = r.nu_rom\r\n" + 
					"left join nota n\r\n" + 
					"on n.nu_nf=it.nu_nf\r\n" + 
					"where r.nu_rom="+carga;
		 
		 String sql_2 ="select sum(n.vl_tot_nf), COUNT(i.nu_nf)\r\n" + 
		 		"from it_rom i \r\n" + 
		 		" join nota n\r\n" + 
		 		" on n.nu_nf=i.nu_nf and i.cd_emp=n.cd_emp\r\n" + 
		 		"  where nu_rom="+carga;
		 
		 
		 

			try {
				PreparedStatement stmt_verifica = (PreparedStatement) connectionSqlServerErp.prepareStatement(sql_1);
				ResultSet rs_1 = stmt_verifica.executeQuery();
				
				
				
				if(rs_1.next()) {
					romaneio.setRomaneio(rs_1.getString(1));
					romaneio.setRegiao(rs_1.getString(2));
					romaneio.setDt_montagem(rs_1.getString(4));
					romaneio.setMotorista(rs_1.getString(5));
					romaneio.setVeiculo(rs_1.getString(7));
					
				}
				
				PreparedStatement stmt_verifica2 = (PreparedStatement) connectionSqlServerErp.prepareStatement(sql_2);
				ResultSet rs_2 = stmt_verifica2.executeQuery();
				
				if(rs_2.next()) {
					
					romaneio.setVlTotalNotasFormat(Formata.moeda(rs_2.getDouble(1)));
					romaneio.setQtdeNotas(rs_2.getInt(2));
					
				}
				
				
				
				
				
				
			
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		 
		 
		 
		
		return romaneio;
	}
	
	public List<PedidosDiario> ItemRomaneio( String carga) {
		List<PedidosDiario> itemRomaneio = new ArrayList<>();
	 
	 String sql_1 ="select i.nu_ped, convert(varchar(10),p.dt_cad,103) as 'dt_cad', p.cd_clien, c.nome, e.nome_fant,n.nu_nf_emp_fat,n.vl_tot_nf\r\n" + 
	 		"from it_rom i \r\n" + 
	 		" join empresa e\r\n" + 
	 		" on e.cd_emp=i.cd_emp\r\n" + 
	 		" join ped_vda p\r\n" + 
	 		" on p.nu_ped=i.nu_ped and p.cd_emp=i.cd_emp\r\n" + 
	 		" join cliente c\r\n" + 
	 		" on p.cd_clien=c.cd_clien\r\n" + 
	 		" join nota n\r\n" + 
	 		" on n.nu_nf=i.nu_nf\r\n" + 
	 		" where nu_rom="+carga;
	 
		 System.out.println(sql_1);
	 
		try {
			PreparedStatement stmt_verifica = (PreparedStatement) connectionSqlServerErp.prepareStatement(sql_1);
			ResultSet rs_1 = stmt_verifica.executeQuery();
			
			
			
			while(rs_1.next()) {
				PedidosDiario registro = new PedidosDiario();
				
				registro.setNumPedido(rs_1.getInt(1));
				registro.setDataPedido(rs_1.getString(2));
				registro.setDesc_cliente(rs_1.getString(3)+"-"+rs_1.getString(4));
				registro.setEmpresa(rs_1.getString(5));
				registro.setNota(rs_1.getString(6));
				registro.setVlMoeda(Formata.moeda(rs_1.getDouble(7)));
			
				itemRomaneio.add(registro);
			}
			
	
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	 
	 
	
	return itemRomaneio;
}
	
	
//	public List<TrackingExpedicao> trkEntrega() {
		
		
		
		
//		String pedStat95Wms ="";
		
		
		
//			String pedidosErp ="";
			
			
//			try {
//				PreparedStatement stmt1 = (PreparedStatement) connectionSqlServerErp.prepareStatement(pedStat95Wms);
//				ResultSet rs1 = stmt1.executeQuery();
//				
//				PreparedStatement stmt2 = (PreparedStatement) connectionSqlServerErp.prepareStatement(pedStat95Wms);
//				ResultSet rs2 = stmt2.executeQuery();
//							
//				
//				if(rs1.next()) {
//					registro.setQtdPedido(rs1.getInt(1));
//					registro.setValor(rs1.getDouble(2));
//					registro.setValorMoeda(Formata.moeda(registro.getValor()));					
//					if(rs2.next()) {
//					registro.setUltPed(rs2.getString(1));
//					
//					}
//				} else {
//					
//					registro.setQtdPedido(0);
//					registro.setValorMoeda("R$ 0,00");
//					registro.setUltPed("-");
//										
//				}
//				
//				if(registro.getUltPed()==null) {
//					registro.setUltPed("-");
//					
//				}
//				
//				if(registro.getValorMoeda()==null) {
//					registro.setUltPed("R$ 0,00");
//					
//				}
//				
//		
//								
//			}catch(SQLException e) {
//					throw new RuntimeException(e);
//			}
//			
			
		
		
//		return registro;
//	}
 	
	
	
	public List<PedidosDiario> detalhaPedidos(String operacao){
		 List<PedidosDiario> filaPedidos = new ArrayList<>();
		 
		 List<String> pedidos = new ArrayList<>();
		 String consultaPedidos ="";
		 
		 if(operacao.equals("op_eletronico")) {
			 consultaPedidos ="	select\r\n" + 
			 		"		e.nome_fant,\r\n" + 
			 		"		p.nu_ped_ele,\r\n" + 
			 		"		p.cd_vend,\r\n" + 
			 		"		p.cd_clien,\r\n" + 
			 		"		c.nome,\r\n" + 
			 		"		convert(varchar(10), p.dt_ped, 103) as 'dt',\r\n" + 
			 		"		p.valor_tot\r\n" + 
			 		"	\r\n" + 
			 		"	from ped_vda_ele p\r\n" + 
			 		"\r\n" + 
			 		"	join empresa e\r\n" + 
			 		"	ON e.cd_emp=p.cd_emp_ele\r\n" + 
			 		"\r\n" + 
			 		"	join cliente c\r\n" + 
			 		"	ON c.cd_clien=p.cd_clien\r\n" + 
			 		"\r\n" + 
			 		"	WHERE cd_emp_ele IN (13,20) \r\n" + 
			 		"	AND p.situacao IN ('ab')\r\n" + 
			 		"	AND pend_ele_libera_auto IS NULL";
			 
		 }else if(operacao.equals("op_comercial")) {
			 consultaPedidos ="select distinct \r\n" + 
			 		"	e.nome_fant,\r\n" + 
			 		"    ev.nu_ped Pedido,\r\n" + 
			 		"	p.cd_vend,\r\n" + 
			 		"	p.cd_clien,\r\n" + 
			 		"	cl.nome,\r\n" + 
			 		"	convert(varchar(10), p.dt_cad,103) as 'dt',\r\n" + 
			 		"	p.valor_tot\r\n" + 
			 		"\r\n" + 
			 		"from evento ev\r\n" + 
			 		"join cliente cl on cl.cd_clien = ev.cd_clien\r\n" + 
			 		"join ped_vda p on p.nu_ped = ev.nu_ped and ev.cd_clien = p.cd_clien and ev.cd_emp = p.cd_emp\r\n" + 
			 		"join fila f on f.cd_fila = ev.cd_fila\r\n" + 
			 		"join empresa e on e.cd_emp=p.cd_emp\r\n" + 
			 		"\r\n" + 
			 		"where  \r\n" + 
			 		"--ev.nu_ped=961691 and\r\n" + 
			 		"ev.situacao = 'AB' \r\n" + 
			 		"and ev.dt_criacao> '2020-11-01 00:00:00'\r\n" + 
			 		"and ev.cd_emp IN (13, 20) \r\n" + 
			 		"and p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC')\r\n" + 
			 		"and ev.cd_fila IN ('BLGV','GERV', 'ADMV', 'ALTP', 'BLAV' , 'CAPV')";
		 }else if(operacao.equals("op_credito")) {
			 consultaPedidos ="select distinct \r\n" + 
			 		"	e.nome_fant,\r\n" + 
			 		"    ev.nu_ped Pedido,\r\n" + 
			 		"	p.cd_vend,\r\n" + 
			 		"	p.cd_clien,\r\n" + 
			 		"	cl.nome,\r\n" + 
			 		"	convert(varchar(10), p.dt_cad,103) as 'dt',\r\n" + 
			 		"	p.valor_tot\r\n" + 
			 		"\r\n" + 
			 		"from evento ev\r\n" + 
			 		"join cliente cl on cl.cd_clien = ev.cd_clien\r\n" + 
			 		"join ped_vda p on p.nu_ped = ev.nu_ped and ev.cd_clien = p.cd_clien and ev.cd_emp = p.cd_emp\r\n" + 
			 		"join fila f on f.cd_fila = ev.cd_fila\r\n" + 
			 		"join empresa e on e.cd_emp=p.cd_emp\r\n" + 
			 		"\r\n" + 
			 		"where  \r\n" + 
			 		"--ev.nu_ped=961691 and\r\n" + 
			 		"ev.situacao = 'AB' \r\n" + 
			 		"and ev.dt_criacao> '2020-11-01 00:00:00'\r\n" + 
			 		"and ev.cd_emp IN (13, 20) \r\n" + 
			 		"and p.tp_ped not in ('PE','NP', 'MD', 'VA','OP', 'RC', 'SP', 'CC')\r\n" + 
			 		"and ev.cd_fila IN ('BLOQ','CRED')";
			 
			 
			 
		 }
		 
		 
		 
		 
		 else if(operacao.equals("op_roteirizacao")||operacao.equals("op_reserva")||operacao.equals("op_separacao")||operacao.equals("op_faturamento")) {
			 
			 String nuped=buscaPedidosWms(operacao);
			 System.err.println("NU PED:"+nuped);
			 
		
			 
			 consultaPedidos ="select  e.nome_fant, p.nu_ped, p.cd_vend, p.cd_clien, c.nome ,convert(varchar(10), p.dt_cad, 103) as 'dt', p.valor_tot from ped_vda p\r\n" + 
			 		"\r\n" + 
			 		"join empresa e\r\n" + 
			 		"on e.cd_emp=p.cd_emp\r\n" + 
			 		"\r\n" + 
			 		"join cliente c\r\n" + 
			 		"on c.cd_clien=p.cd_clien\r\n" + 
			 		"\r\n" + 
			 		"where p.nu_ped in ("+nuped+")\r\n" + 
			 		"\r\n" + 
			 		"and p.dt_cad>'2021-01-01 00:00:00'";
			 
			 
		 }
			 
			 System.out.println(consultaPedidos);
			 
		 
		 try {
				PreparedStatement stmt_verifica = (PreparedStatement) connectionSqlServerErp.prepareStatement(consultaPedidos);
				ResultSet rs_1 = stmt_verifica.executeQuery();
				
				
				
				while(rs_1.next()) {
					PedidosDiario registro = new PedidosDiario();
					registro.setEmpresa(rs_1.getString(1));
					registro.setNumPedido(rs_1.getInt(2));
					registro.setCdVendedor(rs_1.getString(3));
					registro.setDesc_cliente(rs_1.getString(4)+" - "+rs_1.getString(5));
					registro.setDataPedido(rs_1.getString(6));
					registro.setValor(rs_1.getDouble(7));
					
					filaPedidos.add(registro);
				}
				
		
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		 
		 	 
		 	return filaPedidos;
		 }
		 
		
	
	
	
	public String buscaPedidosWms(String operacao) {
		
		String pedidos="";;
		
		String consultaWms="";
		
		if (operacao.equals("op_roteirizacao")) {
			
			consultaWms="SELECT NR_AUF FROM AUFTRAEGE WHERE  STAT IN (10, 15, 25)";
		
		}else if(operacao.equals("op_reserva")) {
			
			consultaWms="SELECT NR_AUF FROM AUFTRAEGE WHERE  STAT IN (35)";
								
		 }else if(operacao.equals("op_separacao")) {
			 
			 consultaWms="SELECT NR_AUF FROM AUFTRAEGE WHERE  STAT IN (54,55,74) AND ART_EING != 'MAN'";
			 
		 }else if(operacao.equals("op_faturamento")) {
			 
			 consultaWms="SELECT NR_AUF FROM AUFTRAEGE WHERE  STAT IN (75) AND HINW_ZUST IS NULL AND ART_EING != 'MAN' "
			 		+ "AND NR_AUF NOT IN ('940587','495','716','838','1301','1290', '1300', '1486','962358', '962359', '962366', '962368', '962369', '962355', '1504','962834')";
			 
		 }
		
		
		System.err.println(consultaWms);
		
		try {
			PreparedStatement stmt_verifica = (PreparedStatement) connectionOracle.prepareStatement(consultaWms);
			ResultSet rs_1 = stmt_verifica.executeQuery();
			
			
			
			while(rs_1.next()) {
				pedidos += rs_1.getString(1) +", ";
		
			}
			
			if(pedidos.length()>4) {
				
				System.out.println(pedidos.length());
					
				System.out.println("A"+pedidos);
				pedidos = pedidos.substring(0, pedidos.length() - 2);
				System.out.println("D"+pedidos);
			}else {
				pedidos="0";
				
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	
			
			return pedidos;
	
		
		
	}
	
}
