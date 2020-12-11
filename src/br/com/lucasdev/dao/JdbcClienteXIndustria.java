package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.positivacao.Cliente;
import br.com.lucasdev.modelo.positivacao.ClienteDetalhado;
import br.com.lucasdev.modelo.relatorios.ColunasMesesBody;
import br.com.lucasdev.modelo.relatorios.Equipe;
import br.com.lucasdev.modelo.relatorios.Vendedor;
import br.com.lucasdev.util.Formata;
import br.com.lucasdev.util.PlanoDeCoberturaConsolidado;

public class JdbcClienteXIndustria {

	private Connection connectionSqlServer;

	LocalDate mesAtual = LocalDate.now();

	public JdbcClienteXIndustria() {
		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

		
public List<ColunasMesesBody> getClientexIndustria(String periodo, String perfil, String cdVenda) {
		
	
	
		/* CARREGANDO A LISTA DE CLIENTES*/
		List<ClienteDetalhado> getCarteira = new ArrayList<>();
		getCarteira = new PlanoDeCoberturaConsolidado().getClientesPlanCobConsolidado(perfil, cdVenda);
		System.err.println(getCarteira.size());
		
		List<ColunasMesesBody> planCobCliFat = new ArrayList<>();
		
		for (ClienteDetalhado c : getCarteira) {
			ColunasMesesBody registro = new ColunasMesesBody();
			registro.setCd_cliente(c.getCd_cliente());
			registro.setDesc_cliente(c.getDesc_cliente());
			registro.setFantasia(c.getFantasia());
			registro.setTp_Cli(c.getTp_Cli());
			registro.setCgc_cpf(c.getCgc_cpf());
			registro.setTelefone(c.getTelefone());
			registro.setGrupoCli(c.getGrupoCli());
			registro.setSegmento(c.getSegmento());
			registro.setArea(c.getArea());
			registro.setCep(c.getCep());
			registro.setLogradouro(c.getLogradouro());
			registro.setNumero(c.getNumero());
			registro.setBairro(c.getBairro());
			registro.setMunicipio(c.getMunicipio());
			registro.setDistrito(c.getDistrito());
			registro.setCdVendedor(c.getCdVendedor());
			registro.setVendedor(c.getVendedor());
			registro.setNomeGuerraVend(c.getNomeGuerraVend());
			registro.setDescGerencia(c.getDescGerencia());
			registro.setCdEquipe(c.getCdEquipe());
			registro.setDescEquipe(c.getDescEquipe());
			
				
			registro.setColuna01("R$ 0,00");
			registro.setColuna02("R$ 0,00");
			registro.setColuna03("R$ 0,00");
			registro.setColuna04("R$ 0,00");
			registro.setColuna05("R$ 0,00");
			registro.setColuna06("R$ 0,00");
			registro.setColuna07("R$ 0,00");
			registro.setColuna08("R$ 0,00");
			registro.setColuna09("R$ 0,00");
			registro.setColuna10("R$ 0,00");
			registro.setColuna11("R$ 0,00");
			registro.setColuna12("R$ 0,00");
			registro.setColuna13("R$ 0,00");
			registro.setColuna14("R$ 0,00");
			registro.setColuna15("R$ 0,00");

			
			planCobCliFat.add(registro);
		}
		/* CARREGANDO A LISTA DE CLIENTES*/
		
		/* TRANTANDO OS CLINTES PARA PASSAR PARA O SELECT */
			String inClintes = "";
			int tamanhoLista = getCarteira.size();
			int i = 1;
	
			for (Cliente c : getCarteira) {
				if (i < tamanhoLista) {
					inClintes += c.getCd_cliente() + ", ";
				} else {
					inClintes += c.getCd_cliente();
				}
	
				i++;
			}
		
		/* TRANTANDO OS CLINTES PARA PASSAR PARA O SELECT */
		
		String sql = "select * from(\r\n" + 
				"SELECT\r\n" + 
				"        --DATEPART(mm, no.dt_emis) as MES_Emissao,\r\n" + 
				"        f.descricao as FABRICANTE,\r\n" + 
				"		no.cd_clien AS Cod_Cliente, \r\n" + 
				"	  cast(SUM((itn.qtde* itn.preco_unit) ) as NUMERIC(12,2)) as Valor\r\n" + 
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
				"	AND no.dt_emis  BETWEEN "+periodo+"	\r\n" + 
				"	and no.cd_clien IN ("+inClintes+")\r\n" + 
				"	AND f.descricao IN ('ONTEX GLOBAL', 'BIC','CARTA FABRIL','KIMBERLY','BARUEL','PHISALIA','SKALA', 'ALFAPARF', 'EMBELLEZE', 'BEAUTY COLOR', 'HYPERA S/A', 'STEVITA', 'PAMPAM', 'YPE', 'APOLO')\r\n" + 
				"\r\n" + 
				"	GROUP BY\r\n" + 
				"	--DATEPART(dd,no.dt_emis),no.dt_emis,\r\n" + 
				"	f.descricao,\r\n" + 
				"	\r\n" + 
				"	 no.cd_clien,\r\n" + 
				"	no.cd_emp,\r\n" + 
				"	 no.nu_ped, \r\n" + 
				"	 no.nome,\r\n" + 
				"	 vd.nome_gue,\r\n" + 
				"	 vd.cd_vend,\r\n" + 
				"	 vd.nome,\r\n" + 
				"	  vd.cd_equipe\r\n" + 
				"	 \r\n" + 
				") em_linha\r\n" + 
				"pivot (sum(Valor) for FABRICANTE IN ([ONTEX GLOBAL], [BIC],[CARTA FABRIL],[KIMBERLY],[BARUEL],[PHISALIA],[SKALA],[ALFAPARF],[EMBELLEZE],[BEAUTY COLOR],[HYPERA S/A],[STEVITA],[PAMPAM],[YPE],[APOLO])) em_colunas\r\n" + 
				"order by 1";		
		
		System.out.println("Processando Script Clientes: " + sql);
		
		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
		

			while (rs.next()) {
			String coluna;


				for (ColunasMesesBody p:planCobCliFat ) {
					if(rs.getInt("Cod_Cliente") == p.getCd_cliente()) {
						p.setColuna01("teve vendas");
						
						p.setColuna01(Formata.moeda(rs.getDouble(2)));
						p.setColuna02(Formata.moeda(rs.getDouble(3)));
						p.setColuna03(Formata.moeda(rs.getDouble(4)));
						p.setColuna04(Formata.moeda(rs.getDouble(5)));
						p.setColuna05(Formata.moeda(rs.getDouble(6)));
						p.setColuna06(Formata.moeda(rs.getDouble(7)));
						p.setColuna07(Formata.moeda(rs.getDouble(8)));
						p.setColuna08(Formata.moeda(rs.getDouble(9)));
						p.setColuna09(Formata.moeda(rs.getDouble(10)));
						p.setColuna10(Formata.moeda(rs.getDouble(11)));
						p.setColuna11(Formata.moeda(rs.getDouble(12)));
						p.setColuna12(Formata.moeda(rs.getDouble(13)));
						p.setColuna13(Formata.moeda(rs.getDouble(14)));
						p.setColuna14(Formata.moeda(rs.getDouble(15)));
						p.setColuna15(Formata.moeda(rs.getDouble(16)));

						
						
						break;
						
						
					}
					
				}
			
			}
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
				
		return planCobCliFat;

		
		
	}
	
	

}