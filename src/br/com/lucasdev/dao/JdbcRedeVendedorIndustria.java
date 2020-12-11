package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.ColunasMesesBody;
import br.com.lucasdev.modelo.relatorios.ExpedicaoCarregamento;
import br.com.lucasdev.util.Formata;

public class JdbcRedeVendedorIndustria {
	
	private Connection connectionSqlServer;
	private Connection connectionMySql;
	
	public JdbcRedeVendedorIndustria() {
		
		
		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			connectionMySql = new ConnectionFactory().getConnectionMySql();
						
		
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
	}
	
	
	public List<ColunasMesesBody> getRedeVendedorFaturamento(String dataInicial, String dataFinal){
		List<ColunasMesesBody> redeVendedorFaturamento = new ArrayList<>();
		
		
		
		String sql_1="select distinct \r\n" + 
				"\r\n" + 
				"eq.descricao,\r\n" + 
				"c.cd_vend,\r\n" + 
				"v.nome,\r\n" + 
				"g.descricao,\r\n" + 
				"\r\n" + 
				"count (c.cd_clien)Lojas\r\n" + 
				"from cliente c\r\n" + 
				"\r\n" + 
				"join grupocli g on g.cd_grupocli = c.cd_grupocli\r\n" + 
				"join vendedor v on V.cd_vend= c.cd_vend\r\n" + 
				"join equipe eq on eq.cd_equipe = v.cd_equipe\r\n" + 
				"where c.ativo = 1 and eq.ativo = 1 and v.ativo =1 and c.cd_vend NOT IN ('990LG001') and eq.ativo=1 and eq.cd_emp IN (13, 20) and g.descricao not in ('Inexistente') \r\n" + 
				"\r\n" + 
				"group by\r\n" + 
				"eq.cd_emp,\r\n" + 
				"v.nome,\r\n" + 
				"c.cd_vend,\r\n" + 
				"eq.descricao,\r\n" + 
				"g.descricao\r\n" + 
				"\r\n" + 
				"order by 4 asc\r\n" + 
				"";
		
		
		try {
			PreparedStatement stmt_1 = this.connectionSqlServer.prepareStatement(sql_1);
			ResultSet rs_1 = stmt_1.executeQuery();
			
			System.out.println(sql_1);
			
			while(rs_1.next()){
				ColunasMesesBody registro = new ColunasMesesBody();
				
				registro.setDescEquipe(rs_1.getString(1));
				registro.setCdVendedor(rs_1.getString(2));
				registro.setVendedor(rs_1.getString(3));
				registro.setGrupoCli(rs_1.getString(4));
				registro.setNumero(rs_1.getString(5));
				registro.setColuna01("-");
				registro.setColuna02("-");
				registro.setColuna03("-");
				registro.setColuna04("-");
				registro.setColuna05("-");
				registro.setColuna06("-");
				registro.setColuna07("-");
				registro.setColuna08("-");
				registro.setColuna09("-");
				registro.setColuna10("-");
				registro.setColuna11("-");
				registro.setColuna12("-");
				registro.setColuna13("-");
				registro.setColuna14("-");
				registro.setVlAberto("-");
				registro.setVlFaturado("-");
				registro.setVlTotal("-");
				
				redeVendedorFaturamento.add(registro);
				
			}
			
			 int soma =0;
				
				for(ColunasMesesBody r: redeVendedorFaturamento) {
					double total=0;
					
					String sql_2 = "select  * from(\r\n" + 
							"SELECT\r\n" + 
							"        --DATEPART(mm, no.dt_emis) as MES_Emissao,\r\n" + 
							"		eq.descricao[Supervisor],\r\n" + 
							"		vd.cd_vend[Vendedor],\r\n" + 
							"		grupocli.descricao [Grupo/Rede],\r\n" + 
							"		f.descricao as FABRICANTE,\r\n" + 
							"		 (SELECT count (cd_clien)\r\n" + 
							"     FROM cliente\r\n" + 
							"     WHERE cd_grupocli = grupocli.cd_grupocli and cd_vend= vd.cd_vend ) [QTD LOJAS],\r\n" + 
							"		\r\n" + 
							"	  cast(SUM((itn.qtde* itn.preco_unit) ) as money) as Valor\r\n" + 
							"	FROM\r\n" + 
							"		dbo.it_nota AS itn\r\n" + 
							"			INNER JOIN dbo.nota AS no\r\n" + 
							"				INNER JOIN dbo.nota_tpped AS ntped\r\n" + 
							"					INNER JOIN dbo.tp_ped AS tp\r\n" + 
							"					ON ntped.tp_ped = tp.tp_ped\r\n" + 
							"				ON no.nu_nf = ntped.nu_nf\r\n" + 
							"			    \r\n" + 
							"			INNER JOIN dbo.cliente AS cl \r\n" + 
							"				INNER JOIN dbo.ram_ativ AS rm\r\n" + 
							"					ON cl.ram_ativ = rm.ram_ativ 				 \r\n" + 
							"				INNER JOIN dbo.end_cli AS edc\r\n" + 
							"					ON cl.cd_clien = edc.cd_clien\r\n" + 
							"					AND edc.tp_end = 'FA'							 \r\n" + 
							"				LEFT OUTER JOIN dbo.area AS ar \r\n" + 
							"					ON cl.cd_area = ar.cd_area\r\n" + 
							"				LEFT OUTER JOIN grupocli\r\n" + 
							"					ON cl.cd_grupocli = grupocli.cd_grupocli\r\n" + 
							"				ON no.cd_clien = cl.cd_clien\r\n" + 
							"			\r\n" + 
							"			INNER JOIN dbo.vendedor AS vd\r\n" + 
							"				INNER JOIN dbo.equipe AS eq \r\n" + 
							"					INNER JOIN dbo.gerencia AS ge \r\n" + 
							"					ON eq.cd_gerencia = ge.cd_gerencia\r\n" + 
							"					AND eq.cd_emp = ge.cd_emp\r\n" + 
							"				ON vd.cd_emp = eq.cd_emp \r\n" + 
							"				AND vd.cd_equipe = eq.cd_equipe \r\n" + 
							"\r\n" + 
							"				INNER JOIN grp_faix gr\r\n" + 
							"				ON vd.cd_grupo = gr.cd_grupo\r\n" + 
							"			ON no.cd_vend = vd.cd_vend			\r\n" + 
							"\r\n" + 
							"		ON itn.nu_nf = no.nu_nf \r\n" + 
							"		\r\n" + 
							"		JOIN produto p\r\n" + 
							"		ON p.cd_prod=itn.cd_prod\r\n" + 
							"		\r\n" + 
							"		JOIN fabric f\r\n" + 
							"		ON p.cd_fabric = f.cd_fabric\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"WHERE     \r\n" + 
							"	no.situacao IN ('AB', 'DP')\r\n" + 
							"	AND	no.tipo_nf = 'S' \r\n" + 
							"	AND	no.cd_emp  IN (13, 20)\r\n" + 
							"	AND	ntped.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n" + 
							"	AND no.dt_emis  BETWEEN '"+dataInicial+" 00:00:00' and '"+dataFinal+" 23:59:00'	\r\n" + 
							"	AND  grupocli.descricao NOT IN ('Inexistente')\r\n" + 
							"	AND  grupocli.descricao  IN ('"+r.getGrupoCli()+"')\r\n" + 
							"	AND  vd.cd_vend IN ('"+r.getCdVendedor()+"')\r\n" + 
							"	 \r\n" + 
							"	--AND f.descricao IN ( 'KIMBERLY', 'BIC','CARTA FABRIL','BARUEL','PHISALIA','SKALA', 'ALFAPARF', 'EMBELLEZE', 'BEAUTY COLOR', 'HYPERA S/A', 'STEVITA', 'PAMPAM', 'YPE', 'APOLO')\r\n" + 
							"\r\n" + 
							"	GROUP BY\r\n" + 
							"	--DATEPART(dd,no.dt_emis),no.dt_emis,\r\n" + 
							"	f.descricao,\r\n" + 
							"	eq.descricao,\r\n" + 
							"	vd.cd_vend,\r\n" + 
							"	grupocli.cd_grupocli, \r\n" + 
							"	grupocli.descricao,\r\n" + 
							"	 cl.cd_clien,\r\n" + 
							"	no.cd_emp,\r\n" + 
							"	 no.nu_ped, \r\n" + 
							"	 no.nome,\r\n" + 
							"	 vd.nome_gue,\r\n" + 
							"	 vd.cd_vend,\r\n" + 
							"	 vd.nome,\r\n" + 
							"	  vd.cd_equipe\r\n" + 
							"	 \r\n" + 
							") em_linha\r\n" + 
							"pivot (sum(Valor) for FABRICANTE IN ([KIMBERLY], [BIC],[CARTA FABRIL],[BARUEL],[PHISALIA],[SKALA],[ALFAPARF],[EMBELLEZE],[BEAUTY COLOR],[HYPERA S/A],[STEVITA],[PAMPAM],[YPE],[APOLO])) em_colunas\r\n" + 
							"order by 3";
					
							System.out.println("Consulta " + ++soma);
					
					PreparedStatement stmt_2 = this.connectionSqlServer.prepareStatement(sql_2);
					ResultSet rs_2 = stmt_2.executeQuery();
					
					
					while(rs_2.next()) {
						r.setColuna01(Formata.moeda(rs_2.getDouble(5)));
						r.setColuna02(Formata.moeda(rs_2.getDouble(6)));
						r.setColuna03(Formata.moeda(rs_2.getDouble(7)));
						r.setColuna04(Formata.moeda(rs_2.getDouble(8)));
						r.setColuna05(Formata.moeda(rs_2.getDouble(9)));
						r.setColuna06(Formata.moeda(rs_2.getDouble(10)));
						r.setColuna07(Formata.moeda(rs_2.getDouble(11)));
						r.setColuna08(Formata.moeda(rs_2.getDouble(12)));
						r.setColuna09(Formata.moeda(rs_2.getDouble(13)));
						r.setColuna10(Formata.moeda(rs_2.getDouble(14)));
						r.setColuna11(Formata.moeda(rs_2.getDouble(15)));
						r.setColuna12(Formata.moeda(rs_2.getDouble(16)));
						r.setColuna13(Formata.moeda(rs_2.getDouble(17)));
						r.setColuna14(Formata.moeda(rs_2.getDouble(18)));
					}
					
					
					String sql_3 ="SELECT distinct\r\n" + 
							"\r\n" + 
							"		e.descricao[Supervisor],\r\n" + 
							"		v.cd_vend[Vendedor],\r\n" + 
							"		g.descricao [Grupo/Rede],\r\n" + 
							"			 (SELECT count (cd_clien)\r\n" + 
							"     FROM cliente\r\n" + 
							"     WHERE cd_grupocli = g.cd_grupocli and cd_vend= v.cd_vend  ) [QTD LOJAS],\r\n" + 
							"	 sum (it.vl_venda) VALOR\r\n" + 
							"\r\n" + 
							"	FROM\r\n" + 
							"	ped_vda p\r\n" + 
							"\r\n" + 
							"	join vendedor v on v.cd_vend = p.cd_vend\r\n" + 
							"	join equipe e on e.cd_equipe = v.cd_equipe\r\n" + 
							"	join cliente c on c.cd_clien = p.cd_clien\r\n" + 
							"	join grupocli g ON g.cd_grupocli = c.cd_grupocli\r\n" + 
							"	join it_pedv it ON it.nu_ped = p.nu_ped\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"	where e.ativo = 1\r\n" + 
							"	and e.cd_emp  IN (13, 20)\r\n" + 
							"	and it.situacao = 'FA'\r\n" + 
							"	and p.tp_ped NOT IN ('VA')\r\n" + 
							"	and p.dt_ped BETWEEN '"+dataInicial+" 00:00:00' and '"+dataFinal+" 23:59:00'	\r\n" + 
							"	and v.cd_vend='"+r.getCdVendedor()+"' and g.descricao='"+r.getGrupoCli()+"'\r\n" + 
							"	group by\r\n" + 
							"    e.descricao,\r\n" + 
							"	v.cd_vend,\r\n" + 
							"	g.descricao,\r\n" + 
							"	g.cd_grupocli\r\n" + 
							"	order by 3";
					
//					System.out.println(sql_3);
					
					
					PreparedStatement stmt_3 = this.connectionSqlServer.prepareStatement(sql_3);
					ResultSet rs_3 = stmt_3.executeQuery();
					
					while(rs_3.next()) {
						r.setVlFaturado((Formata.moeda(rs_3.getDouble(5))));
						total += rs_3.getDouble(5);
						
					}
					
					
					String sql_4 ="SELECT distinct\r\n" + 
							"\r\n" + 
							"		e.descricao[Supervisor],\r\n" + 
							"		v.cd_vend[Vendedor],\r\n" + 
							"		g.descricao [Grupo/Rede],\r\n" + 
							"			 (SELECT count (cd_clien)\r\n" + 
							"     FROM cliente\r\n" + 
							"     WHERE cd_grupocli = g.cd_grupocli and cd_vend= v.cd_vend  ) [QTD LOJAS],\r\n" + 
							"	 sum (it.vl_venda) VALOR\r\n" + 
							"\r\n" + 
							"	FROM\r\n" + 
							"	ped_vda p\r\n" + 
							"\r\n" + 
							"	join vendedor v on v.cd_vend = p.cd_vend\r\n" + 
							"	join equipe e on e.cd_equipe = v.cd_equipe\r\n" + 
							"	join cliente c on c.cd_clien = p.cd_clien\r\n" + 
							"	join grupocli g ON g.cd_grupocli = c.cd_grupocli\r\n" + 
							"	join it_pedv it ON it.nu_ped = p.nu_ped\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"	where e.ativo = 1\r\n" + 
							"	and e.cd_emp  IN (13, 20)\r\n" + 
							"	and it.situacao = 'AB'\r\n" + 
							"	and p.tp_ped NOT IN ('VA')\r\n" + 
							"	and p.dt_ped BETWEEN '"+dataInicial+" 00:00:00' and '"+dataFinal+" 23:59:00'	\r\n" + 
							"	and v.cd_vend='"+r.getCdVendedor()+"' and g.descricao='"+r.getGrupoCli()+"'\r\n" + 
							"	group by\r\n" + 
							"    e.descricao,\r\n" + 
							"	v.cd_vend,\r\n" + 
							"	g.descricao,\r\n" + 
							"	g.cd_grupocli\r\n" + 
							"	order by 3";
					
					
					
					
					PreparedStatement stmt_4 = this.connectionSqlServer.prepareStatement(sql_4);
					ResultSet rs_4 = stmt_4.executeQuery();
					
					
					
					while(rs_4.next()) {
						r.setVlAberto((Formata.moeda(rs_4.getDouble(5))));
						System.out.println(rs_4.getDouble(5));
						total += rs_4.getDouble(5);
						
					}
					
					
										
					r.setVlTotal(Formata.moeda(total));
					
					
				}
				
				
			
			
			
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
		
		
		
		
		
		return redeVendedorFaturamento;
	}
	

}
