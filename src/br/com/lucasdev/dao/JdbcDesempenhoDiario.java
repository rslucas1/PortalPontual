package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.DesempenhoDiario;
import br.com.lucasdev.modelo.relatorios.PedidosDiario;
import br.com.lucasdev.modelo.relatorios.PlanilhaDeSetores;
import br.com.lucasdev.util.BetPeriodo;
import br.com.lucasdev.util.Formata;

public class JdbcDesempenhoDiario {
	private Connection connectionSqlServer;
	LocalDate hoje = LocalDate.now();
	
	public JdbcDesempenhoDiario (){
		
	try {
		connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
	}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List <DesempenhoDiario> getDesempenhoDiario(String periodo){
		List <DesempenhoDiario> desempenhoDiario = new ArrayList<>();
		
	
		String sql = "select * from (\r\n" + 
				"	select \r\n" + 
				"	DATEPART(dd, nt.dt_emis ) as MES_PEDIDO,\r\n" + 
				"		nt.cd_vend,\r\n" + 
				"		v.nome,\r\n" + 
				"		cast(SUM((it.qtde* it.preco_unit) ) as NUMERIC(12,2)) as Valor\r\n" + 
				"\r\n" + 
				"	from nota nt\r\n" + 
				"	\r\n" + 
				"	left join it_nota it \r\n" + 
				"		on nt.nu_nf = it.nu_nf \r\n" + 
				"	left join fabric as fb \r\n" + 
				"		on it.cd_fabric = fb.cd_fabric\r\n" + 
				"	INNER JOIN dbo.nota_tpped AS ntped\r\n" + 
				"		INNER JOIN dbo.tp_ped AS tp\r\n" + 
				"			ON ntped.tp_ped = tp.tp_ped\r\n" + 
				"				ON NT.nu_nf = ntped.nu_nf\r\n" + 
				"	JOIN vendedor v\r\n" + 
				"		ON nt.cd_vend = v.cd_vend\r\n" + 
				"	where \r\n" + 
				"	--cd_vend = '100PC006'\r\n" + 
				"	nt.dt_emis  BETWEEN" +periodo+" " + 
				"	--AND nt.cd_vend IN ()\r\n" + 
				"	AND nt.situacao IN ('AB', 'DP')\r\n" + 
				"	AND	ntped.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n" + 
				"	AND	nT.cd_emp  IN (13, 20)\r\n" + 
				"	AND	nt.tipo_nf = 'S' \r\n" + 
				"	group by\r\n" + 
				"	DATEPART(dd, nt.dt_emis ),\r\n" + 
				"	nt.dt_emis,\r\n" + 
				"	--nt.cd_clien\r\n" + 
				"	fb.descricao,\r\n" + 
				"	nt.cd_vend,\r\n" + 
				"	v.nome\r\n" + 
				"\r\n" + 
				") em_linha\r\n" + 
				"pivot (sum(Valor) for MES_PEDIDO IN ([1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12],[13],[14],[15],[16],[17],[18],[19],[20],[21],[22],[23],[24],[25],[26],[27],[28],[29],[30],[31])) em_colunas\r\n" + 
				"order by 1\r\n" + 
				" \r\n" + 
				"";
		
				System.out.println(sql);
		
		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
							
			while(rs.next()) {
				DesempenhoDiario registro = new DesempenhoDiario();
				double valorTotal=0;
				double valor=0;
				
				registro.setCodigoVendedor(rs.getString(1));
				registro.setNomeVendedor(rs.getString(2));
				String setStringValor=null;
				
				
				valor = (rs.getString(3)==null) ? 0 : rs.getInt(3);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_1(setStringValor);	
				
				valor = (rs.getString(4)==null) ? 0 : rs.getInt(4);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_2(setStringValor);	
				
				valor = (rs.getString(5)==null) ? 0 : rs.getInt(5);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_3(setStringValor);	
				
				valor = (rs.getString(6)==null) ? 0 : rs.getInt(6);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_4(setStringValor);	
				
				valor = (rs.getString(7)==null) ? 0 : rs.getInt(7);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_5(setStringValor);	
				
				valor = (rs.getString(8)==null) ? 0 : rs.getInt(8);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_6(setStringValor);	
				
				valor = (rs.getString(9)==null) ? 0 : rs.getInt(9);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_7(setStringValor);	
				
				valor = (rs.getString(10)==null) ? 0 : rs.getInt(10);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_8(setStringValor);	
				
				valor = (rs.getString(11)==null) ? 0 : rs.getInt(11);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_9(setStringValor);	
				
				valor = (rs.getString(12)==null) ? 0 : rs.getInt(12);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_10(setStringValor);	
				
				valor = (rs.getString(13)==null) ? 0 : rs.getInt(13);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_11(setStringValor);	
				
				valor = (rs.getString(14)==null) ? 0 : rs.getInt(14);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_12(setStringValor);	
				
				valor = (rs.getString(15)==null) ? 0 : rs.getInt(15);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_13(setStringValor);	
				
				valor = (rs.getString(16)==null) ? 0 : rs.getInt(16);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_14(setStringValor);	
				
				valor = (rs.getString(17)==null) ? 0 : rs.getInt(17);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_15(setStringValor);	
				
				valor = (rs.getString(18)==null) ? 0 : rs.getInt(18);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_16(setStringValor);	
				
				valor = (rs.getString(19)==null) ? 0 : rs.getInt(19);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_17(setStringValor);					
				
				valor = (rs.getString(20)==null) ? 0 : rs.getInt(20);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_18(setStringValor);	
				
				valor = (rs.getString(21)==null) ? 0 : rs.getInt(21);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_19(setStringValor);	
				
				valor = (rs.getString(22)==null) ? 0 : rs.getInt(22);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_20(setStringValor);	
				
				valor = (rs.getString(23)==null) ? 0 : rs.getInt(23);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_21(setStringValor);	
				
				valor = (rs.getString(24)==null) ? 0 : rs.getInt(24);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_22(setStringValor);	
				
				valor = (rs.getString(25)==null) ? 0 : rs.getInt(25);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_23(setStringValor);	
				
				valor = (rs.getString(26)==null) ? 0 : rs.getInt(26);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_24(setStringValor);	
				
				valor = (rs.getString(27)==null) ? 0 : rs.getInt(27);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_25(setStringValor);	
				
				valor = (rs.getString(28)==null) ? 0 : rs.getInt(28);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_26(setStringValor);	
				
				valor = (rs.getString(29)==null) ? 0 : rs.getInt(29);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_27(setStringValor);	
				
				valor = (rs.getString(30)==null) ? 0 : rs.getInt(30);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_28(setStringValor);	
				
				valor = (rs.getString(31)==null) ? 0 : rs.getInt(31);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_29(setStringValor);	
				
				valor = (rs.getString(32)==null) ? 0 : rs.getInt(32);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_30(setStringValor);	
				
				valor = (rs.getString(33)==null) ? 0 : rs.getInt(33);
				valorTotal += valor;
				setStringValor = (valor==0) ? "" : "R$ " + valor;
				registro.setDia_31(setStringValor);	
				
				registro.setValorTotal("R$ " +valorTotal);
				
				
								
			
				
				desempenhoDiario.add(registro);
				
			}
			
			
			List<PlanilhaDeSetores> planilhaDeSetores = new ArrayList<>();
			
			planilhaDeSetores = new JdbcPlanilhaDeSetores().getPlanilhaDeSetores("GERAL");
			
			for (PlanilhaDeSetores v: planilhaDeSetores ) {
				boolean localizado=false;
					for (DesempenhoDiario d:desempenhoDiario ) {
						if (v.getCd_venda().equals(d.getCodigoVendedor())) {
							localizado=true;
							break;
						}
										
					}
					
					if(localizado==false) {
						DesempenhoDiario registro = new DesempenhoDiario();
						registro.setCodigoVendedor(v.getCd_venda());
						registro.setNomeVendedor(v.getNome());
						
						desempenhoDiario.add(registro);
						
					}
				
				}
			
			
			
			
			List <PedidosDiario> pedidosAbertos = new ArrayList<>();
			
			String sqlPedidosemaberto="select  \r\n" + 
					"p.cd_vend,  \r\n" + 
					"sum(p.valor_tot)\r\n" + 
					"	from ped_vda p\r\n" + 
					"\r\n" + 
					"	join vendedor v\r\n" + 
					"	on p.cd_vend=v.cd_vend\r\n" + 
					"\r\n" + 
					"	join cliente c\r\n" + 
					"	on c.cd_clien = p.cd_clien\r\n" + 
					"\r\n" + 
					"	left join nota n\r\n" + 
					"	on n.nu_ped=p.nu_ped \r\n" + 
					"where \r\n" + 
					"	p.dt_cad BETWEEN '"+hoje.minusDays(15)+" 00:00:00' AND '"+hoje+" 23:59:59' AND \r\n" + 
					"	p.cd_emp IN (13,20)  and\r\n" + 
					"	p.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL') AND \r\n"+
					"	p.situacao IN ('AB') AND \r\n" +
					"	n.situacao IS NULL \r\n"+
					"   \r\n" + 
					"   group by p.cd_vend\r\n" + 
					"   order by p.cd_vend"; 
			System.out.println(sqlPedidosemaberto);
			
			
			PreparedStatement stmt2 = connectionSqlServer.prepareStatement(sqlPedidosemaberto);
			ResultSet rs2 = stmt2.executeQuery();
			
			while(rs2.next()) {
				PedidosDiario registro = new PedidosDiario();
				
				registro.setCdVendedor(rs2.getString(1));
				registro.setValor(rs2.getDouble(2));
				
				pedidosAbertos.add(registro);
								
			}
			
			for (DesempenhoDiario d : desempenhoDiario) {
				for (PedidosDiario p: pedidosAbertos ) {
					
					if(d.getCodigoVendedor().equals(p.getCdVendedor())) {
						d.setTotalAberto(Formata.moeda(p.getValor()));
//						System.out.println("IGUAL "+d.getCodigoVendedor()+" == "+p.getCdVendedor());
						break;
					}else{d.setTotalAberto("R$ 0,00");
//						System.out.println("DESIGUAL "+d.getCodigoVendedor()+" == "+p.getCdVendedor());
					
					}
				}
			}
			
			
			
			
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		
		
		
		return desempenhoDiario;
	}
	
		
}
	
	
	