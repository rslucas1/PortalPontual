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

public class JdbcPlanoDeCoberturaOld {

	private Connection connectionSqlServer;

	LocalDate mesAtual = LocalDate.now();

	public JdbcPlanoDeCoberturaOld() {
		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

	public Equipe getEquipe(String cdVend) {
		String sql = "select\r\n" + "e.cd_equipe as cd_equipe, \r\n" + "e.descricao as desc_equipe, \r\n"
				+ "g.descricao as desc_gerencia\r\n" + "from equipe e \r\n" + "\r\n"
				+ "join gerencia g on e.cd_gerencia=g.cd_gerencia\r\n"
				+ "join vendedor v on v.cd_equipe=e.cd_equipe\r\n" + "\r\n" + "where v.cd_vend='" + cdVend + "'";
		Equipe equipe = new Equipe();

		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				equipe.setCdEquipe(rs.getString("cd_equipe"));
				equipe.setDescEquipe(rs.getString("desc_equipe"));
				equipe.setDescGerencia(rs.getString("desc_gerencia"));

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return equipe;
	}

	public List<Vendedor> getEquipeVendedores(String cd_Equipe) {

		List<Vendedor> vendedoresEquipe = new ArrayList<>();

		String sql = "select * from vendedor where cd_equipe='" + cd_Equipe + "' AND ativo=1";

		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Vendedor vendListado = new Vendedor();

				vendListado.setCd_venda(rs.getString("cd_vend"));
				vendListado.setNome(rs.getString("nome"));
				vendListado.setCd_guerra(rs.getString("nome_gue"));

				vendedoresEquipe.add(vendListado);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

		return vendedoresEquipe;

	}

	public List<ColunasMesesBody> getPlanoDeCoberturaVenCli(String periodo, String vendedor) {
		
		/* CARREGANDO A LISTA DE CLIENTES*/
		List<Cliente> getCarteira = new ArrayList<>();
		getCarteira = new JdbcPositivacaoDao().getcarteiraClientes(vendedor);
		
		List<ColunasMesesBody> planCobCliFat = new ArrayList<>();
		
		for (Cliente c : getCarteira) {
			ColunasMesesBody registro = new ColunasMesesBody();
			registro.setCd_cliente(c.getCd_cliente());
			registro.setDesc_cliente(c.getDesc_cliente());
			
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
		
		String sql = "select * from(\r\n" + "SELECT\r\n" + "        DATEPART(mm, no.dt_emis) as MES_Emissao,\r\n"
		+ "	  no.cd_clien AS Cod_Cliente, \r\n" 
		+ "	  cast(SUM((itn.qtde* itn.preco_unit) ) as NUMERIC(12,2)) as Valor\r\n" + "	FROM\r\n"
		+ "		dbo.it_nota AS itn\r\n" + "			INNER JOIN dbo.nota AS no\r\n"
		+ "				INNER JOIN dbo.nota_tpped AS ntped\r\n"
		+ "					INNER JOIN dbo.tp_ped AS tp\r\n"
		+ "					ON ntped.tp_ped = tp.tp_ped\r\n" + "				ON no.nu_nf = ntped.nu_nf\r\n"
		+ "			    \r\n" + "			INNER JOIN dbo.cliente AS cl \r\n"
		+ "				INNER JOIN dbo.ram_ativ AS rm\r\n"
		+ "					ON cl.ram_ativ = rm.ram_ativ 				 \r\n"
		+ "				INNER JOIN dbo.end_cli AS edc\r\n"
		+ "					ON cl.cd_clien = edc.cd_clien\r\n"
		+ "					AND edc.tp_end = 'FA'							 \r\n"
		+ "				LEFT OUTER JOIN dbo.area AS ar \r\n"
		+ "					ON cl.cd_area = ar.cd_area\r\n" + "				LEFT OUTER JOIN grupocli\r\n"
		+ "					ON cl.cd_grupocli = grupocli.cd_grupocli\r\n"
		+ "				ON no.cd_clien = cl.cd_clien\r\n" + "			\r\n"
		+ "			INNER JOIN dbo.vendedor AS vd\r\n" + "				INNER JOIN dbo.equipe AS eq \r\n"
		+ "					INNER JOIN dbo.gerencia AS ge \r\n"
		+ "					ON eq.cd_gerencia = ge.cd_gerencia\r\n"
		+ "					AND eq.cd_emp = ge.cd_emp\r\n" + "				ON vd.cd_emp = eq.cd_emp \r\n"
		+ "				AND vd.cd_equipe = eq.cd_equipe \r\n" + "\r\n"
		+ "				INNER JOIN grp_faix gr\r\n" + "				ON vd.cd_grupo = gr.cd_grupo\r\n"
		+ "			ON no.cd_vend = vd.cd_vend			\r\n" + "\r\n" + "		ON itn.nu_nf = no.nu_nf \r\n"
		+ "\r\n" + "\r\n" + "\r\n" + "WHERE     \r\n" + "	no.situacao IN ('AB', 'DP')\r\n"
		+ "	AND	no.tipo_nf = 'S' \r\n" + "	AND	no.cd_emp  IN (13)\r\n"
		+ "	AND	ntped.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n"
		+ "	AND no.dt_emis  BETWEEN " + periodo + "	and cl.cd_clien IN (" + inClintes + ")\r\n" + "\r\n" + "\r\n"
		+ "	GROUP BY\r\n" + "	DATEPART(dd,no.dt_emis),no.dt_emis,\r\n" + "	 no.cd_clien,\r\n"
		+ "	no.cd_emp,\r\n" + "	 no.nu_ped, \r\n" + "	 no.nome,\r\n" + "	 vd.nome_gue,\r\n"
		+ "	 vd.cd_vend,\r\n" + "	 vd.nome,\r\n" + "	  vd.cd_equipe\r\n" + "	 \r\n" + ") em_linha\r\n"
		+ "pivot (sum(Valor) for MES_Emissao IN ([1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12])) em_colunas\r\n"
		+ "order by 1";		
		
		System.out.println("Processando Script Clientes: " + sql);
		
		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			LocalDate hoje = LocalDate.now();

			while (rs.next()) {
			String coluna;
//				int cliente =rs.getInt("Cod_Cliente");

				for (ColunasMesesBody p:planCobCliFat ) {
					if(rs.getInt("Cod_Cliente") == p.getCd_cliente()) {
						p.setColuna01("teve vendas");
						
						p.setColuna01(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(1).getMonthValue()))));
						p.setColuna02(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(2).getMonthValue()))));
						p.setColuna03(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(3).getMonthValue()))));
						p.setColuna04(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(4).getMonthValue()))));
						p.setColuna05(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(5).getMonthValue()))));
						p.setColuna06(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(6).getMonthValue()))));
						p.setColuna07(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(7).getMonthValue()))));
						p.setColuna08(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(8).getMonthValue()))));
						p.setColuna09(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(9).getMonthValue()))));
						p.setColuna10(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(10).getMonthValue()))));
						p.setColuna11(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(11).getMonthValue()))));
						p.setColuna12(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(12).getMonthValue()))));
						
						break;
						
						
					}
					
				}
			
			}
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
				
		return planCobCliFat;

		
		
//		List<Cliente> getCarteira = new ArrayList<>();
//		getCarteira = new JdbcPositivacaoDao().getcarteiraClientes(vendedor);
//		String inClintes = "";
//		int tamanhoLista = getCarteira.size();
//		int i = 1;
//
//		for (Cliente c : getCarteira) {
//			if (i < tamanhoLista) {
//				inClintes += c.getCd_cliente() + ", ";
//			} else {
//				inClintes += c.getCd_cliente();
//			}
//
//			i++;
//		}
//
//		List<ColunasMesesBody> retornoSql = new ArrayList<>();
//
//		String sql = "select * from(\r\n" + "SELECT\r\n" + "        DATEPART(mm, no.dt_emis) as MES_Emissao,\r\n"
//				+ "	  no.cd_clien AS Cod_Cliente, \r\n" + "	  no.nome AS Descricao_do_Cliente,\r\n"
//				+ "	  cast(SUM((itn.qtde* itn.preco_unit) ) as NUMERIC(12,2)) as Valor\r\n" + "	FROM\r\n"
//				+ "		dbo.it_nota AS itn\r\n" + "			INNER JOIN dbo.nota AS no\r\n"
//				+ "				INNER JOIN dbo.nota_tpped AS ntped\r\n"
//				+ "					INNER JOIN dbo.tp_ped AS tp\r\n"
//				+ "					ON ntped.tp_ped = tp.tp_ped\r\n" + "				ON no.nu_nf = ntped.nu_nf\r\n"
//				+ "			    \r\n" + "			INNER JOIN dbo.cliente AS cl \r\n"
//				+ "				INNER JOIN dbo.ram_ativ AS rm\r\n"
//				+ "					ON cl.ram_ativ = rm.ram_ativ 				 \r\n"
//				+ "				INNER JOIN dbo.end_cli AS edc\r\n"
//				+ "					ON cl.cd_clien = edc.cd_clien\r\n"
//				+ "					AND edc.tp_end = 'FA'							 \r\n"
//				+ "				LEFT OUTER JOIN dbo.area AS ar \r\n"
//				+ "					ON cl.cd_area = ar.cd_area\r\n" + "				LEFT OUTER JOIN grupocli\r\n"
//				+ "					ON cl.cd_grupocli = grupocli.cd_grupocli\r\n"
//				+ "				ON no.cd_clien = cl.cd_clien\r\n" + "			\r\n"
//				+ "			INNER JOIN dbo.vendedor AS vd\r\n" + "				INNER JOIN dbo.equipe AS eq \r\n"
//				+ "					INNER JOIN dbo.gerencia AS ge \r\n"
//				+ "					ON eq.cd_gerencia = ge.cd_gerencia\r\n"
//				+ "					AND eq.cd_emp = ge.cd_emp\r\n" + "				ON vd.cd_emp = eq.cd_emp \r\n"
//				+ "				AND vd.cd_equipe = eq.cd_equipe \r\n" + "\r\n"
//				+ "				INNER JOIN grp_faix gr\r\n" + "				ON vd.cd_grupo = gr.cd_grupo\r\n"
//				+ "			ON no.cd_vend = vd.cd_vend			\r\n" + "\r\n" + "		ON itn.nu_nf = no.nu_nf \r\n"
//				+ "\r\n" + "\r\n" + "\r\n" + "WHERE     \r\n" + "	no.situacao IN ('AB', 'DP')\r\n"
//				+ "	AND	no.tipo_nf = 'S' \r\n" + "	AND	no.cd_emp  IN (13)\r\n"
//				+ "	AND	ntped.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n"
//				+ "	AND no.dt_emis  BETWEEN " + periodo + "	and cl.cd_clien IN (" + carteira + ")\r\n" + "\r\n" + "\r\n"
//				+ "	GROUP BY\r\n" + "	DATEPART(dd,no.dt_emis),no.dt_emis,\r\n" + "	 no.cd_clien,\r\n"
//				+ "	no.cd_emp,\r\n" + "	 no.nu_ped, \r\n" + "	 no.nome,\r\n" + "	 vd.nome_gue,\r\n"
//				+ "	 vd.cd_vend,\r\n" + "	 vd.nome,\r\n" + "	  vd.cd_equipe\r\n" + "	 \r\n" + ") em_linha\r\n"
//				+ "pivot (sum(Valor) for MES_Emissao IN ([1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12])) em_colunas\r\n"
//				+ "order by 1";
//
//		System.out.println("Processando Script Clientes: " + sql);
//
//		try {
//			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
//
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				ColunasMesesBody registro = new ColunasMesesBody();
//				String coluna;
//				LocalDate hoje = LocalDate.now();
//
//				registro.setCd_cliente(rs.getInt("Cod_Cliente"));
//				registro.setDesc_cliente(rs.getString("Descricao_do_Cliente"));
//
//				registro.setColuna01(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(1).getMonthValue()))));
//				registro.setColuna02(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(2).getMonthValue()))));
//				registro.setColuna03(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(3).getMonthValue()))));
//				registro.setColuna04(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(4).getMonthValue()))));
//				registro.setColuna05(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(5).getMonthValue()))));
//				registro.setColuna06(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(6).getMonthValue()))));
//				registro.setColuna07(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(7).getMonthValue()))));
//				registro.setColuna08(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(8).getMonthValue()))));
//				registro.setColuna09(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(9).getMonthValue()))));
//				registro.setColuna10(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(10).getMonthValue()))));
//				registro.setColuna11(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(11).getMonthValue()))));
//				registro.setColuna12(
//						Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(12).getMonthValue()))));
//
//				retornoSql.add(registro);
//
//			}
//
//			// System.out.println("Executando o Script>\n" +sql);
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//
//		}
//
//		return retornoSql;
	}

	public ClienteDetalhado getClienteDetalhado(int cd_clien) {
		ClienteDetalhado clienteDetalhado = new ClienteDetalhado();

		String sql = "select\r\n" + " c.cd_clien cd_clien,\r\n" + " nome descricao, \r\n" + " tp_cliente tp_cli, \r\n"
				+ " nome_res fantasia,\r\n" + " cgc_cpf,\r\n" + " g.descricao grupo, \r\n"
				+ " e.logradouro logradouro, \r\n" + " e.numero numero,\r\n" + " e.cep cep, \r\n"
				+ " r.descricao segmento, \r\n" + " a.descricao area, \r\n" + " e.bairro,\r\n" + " e.municipio, \r\n"
				+ " e.distrito\r\n" + "\r\n" + " \r\n" + "from cliente c \r\n" + "\r\n" + "join grupocli g \r\n"
				+ "on g.cd_grupocli = c.cd_grupocli \r\n" + "\r\n" + "join end_cli e\r\n"
				+ "on e.cd_clien = c.cd_clien\r\n" + "\r\n" + "join ram_ativ r\r\n" + "on r.ram_ativ = c.ram_ativ\r\n"
				+ "\r\n" + "join area a\r\n" + "on c.cd_area = a.cd_area\r\n" + "\r\n" + " where c.cd_clien='"
				+ cd_clien + "' and  e.tp_end='FA'";
		
		System.out.println(sql);

		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				clienteDetalhado.setCd_cliente(rs.getInt("cd_clien"));
				clienteDetalhado.setDesc_cliente(rs.getString("descricao"));
				clienteDetalhado.setTp_Cli(rs.getString("tp_cli"));
				clienteDetalhado.setFantasia(rs.getString("fantasia"));
				clienteDetalhado.setCgc_cpf(rs.getString("cgc_cpf"));
				clienteDetalhado.setLogradouro(rs.getString("logradouro"));
				clienteDetalhado.setNumero(rs.getString("numero"));
				clienteDetalhado.setCep(rs.getString("cep"));
				clienteDetalhado.setSegmento(rs.getString("segmento"));
				clienteDetalhado.setArea(rs.getString("area"));
				clienteDetalhado.setBairro(rs.getString("bairro"));
				clienteDetalhado.setMunicipio(rs.getString("municipio"));
				clienteDetalhado.setDistrito(rs.getString("distrito"));
				clienteDetalhado.setGrupoCli(rs.getString("grupo"));

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// System.out.println(clienteDetalhado.getCd_cliente()+clienteDetalhado.getDesc_cliente()+clienteDetalhado.getSegmento());
		//

		return clienteDetalhado;
	}

	public String getCd_gerencia(String cd_vendedor) {
		String cd_gerencia = null;

		String sql = "select e.cd_gerencia from vendedor v\r\n" + "\r\n"
				+ "join equipe e on v.cd_equipe = e.cd_equipe\r\n" + "\r\n" + "where v.cd_vend='" + cd_vendedor
				+ "' and e.cd_emp=13";

		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cd_gerencia = rs.getString("cd_gerencia");

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

		return cd_gerencia;

	}

	public List<Equipe> getGerencia(String cd_vend_ger) {
		List<Equipe> gerencia = new ArrayList<>();

		String sql = "select cd_equipe, e.descricao e_descricao, g.descricao g_descricao from equipe e \r\n"
				+ "join gerencia g on e.cd_gerencia = g.cd_gerencia\r\n" + "\r\n" + "where e.ativo=1 AND g.cd_gerencia='"
				+ cd_vend_ger + "'";

		System.out.println(sql);

		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Equipe registro = new Equipe();

				registro.setCdEquipe(rs.getString("cd_equipe"));
				registro.setDescEquipe(rs.getString("e_descricao"));
				registro.setDescGerencia(rs.getString("g_descricao"));

				gerencia.add(registro);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

		return gerencia;
	}
	
public List<ColunasMesesBody> getPlanoDeCobConsolidado(String periodo, String perfil, String cdVenda, String industria) {
		
		String filtroFabricante=industria;
		
		if(industria.equals("TODAS")) {
				industria="";
		}else {
			industria="f.cd_fabric='"+industria+"' AND "; 			
		}
	
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
		
		String sql = "select * from(\r\n" + "SELECT\r\n" + "        DATEPART(mm, no.dt_emis) as MES_Emissao,\r\n"
		+ "	  no.cd_clien AS Cod_Cliente, \r\n" 
		+ "	  cast(SUM((itn.qtde* itn.preco_unit) ) as NUMERIC(12,2)) as Valor\r\n" + "	FROM\r\n"
		+ "		dbo.it_nota AS itn\r\n" + "			INNER JOIN dbo.nota AS no\r\n"
		+ "				INNER JOIN dbo.nota_tpped AS ntped\r\n"
		+ "					INNER JOIN dbo.tp_ped AS tp\r\n"
		+ "					ON ntped.tp_ped = tp.tp_ped\r\n" + "				ON no.nu_nf = ntped.nu_nf\r\n"
		+ "			    \r\n" + "			INNER JOIN dbo.cliente AS cl \r\n"
		+ "				INNER JOIN dbo.ram_ativ AS rm\r\n"
		+ "					ON cl.ram_ativ = rm.ram_ativ 				 \r\n"
		+ "				INNER JOIN dbo.end_cli AS edc\r\n"
		+ "					ON cl.cd_clien = edc.cd_clien\r\n"
		+ "					AND edc.tp_end = 'FA'							 \r\n"
		+ "				LEFT OUTER JOIN dbo.area AS ar \r\n"
		+ "					ON cl.cd_area = ar.cd_area\r\n" + "				LEFT OUTER JOIN grupocli\r\n"
		+ "					ON cl.cd_grupocli = grupocli.cd_grupocli\r\n"
		+ "				ON no.cd_clien = cl.cd_clien\r\n" + "			\r\n"
		+ "			INNER JOIN dbo.vendedor AS vd\r\n" + "				INNER JOIN dbo.equipe AS eq \r\n"
		+ "					INNER JOIN dbo.gerencia AS ge \r\n"
		+ "					ON eq.cd_gerencia = ge.cd_gerencia\r\n"
		+ "					AND eq.cd_emp = ge.cd_emp\r\n" + "				ON vd.cd_emp = eq.cd_emp \r\n"
		+ "				AND vd.cd_equipe = eq.cd_equipe \r\n" + "\r\n"
		+ "				INNER JOIN grp_faix gr\r\n" + "				ON vd.cd_grupo = gr.cd_grupo\r\n"
		+ "			ON no.cd_vend = vd.cd_vend			\r\n" + "\r\n" + "		ON itn.nu_nf = no.nu_nf \r\n"
		+ 		"LEFT JOIN produto p "
		+		"ON p.cd_prod= itn.cd_prod " 

		+		"LEFT JOIN fabric f "
		+		"ON f.cd_fabric=p.cd_fabric "
		
		
		+ "WHERE "
		+ industria
		+ "no.situacao IN ('AB', 'DP')\r\n"
		+ "	AND	no.tipo_nf = 'S' \r\n" + "	AND	no.cd_emp  IN (13)\r\n"
		+ "	AND	ntped.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n"
		+ "	AND no.dt_emis  BETWEEN " + periodo + "	and cl.cd_clien IN (" + inClintes + ")\r\n" + "\r\n" + "\r\n"
		+ "	GROUP BY\r\n" + "	DATEPART(dd,no.dt_emis),no.dt_emis,\r\n" + "	 no.cd_clien,\r\n"
		+ "	no.cd_emp,\r\n" + "	 no.nu_ped, \r\n" + "	 no.nome,\r\n" + "	 vd.nome_gue,\r\n"
		+ "	 vd.cd_vend,\r\n" + "	 vd.nome,\r\n" + "	  vd.cd_equipe\r\n" + "	 \r\n" + ") em_linha\r\n"
		+ "pivot (sum(Valor) for MES_Emissao IN ([1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12])) em_colunas\r\n"
		+ "order by 1";		
		
		System.out.println("Processando Script Clientes: " + sql);
		
		try {
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			LocalDate hoje = LocalDate.now();

			while (rs.next()) {
			String coluna;


				for (ColunasMesesBody p:planCobCliFat ) {
					if(rs.getInt("Cod_Cliente") == p.getCd_cliente()) {
						p.setColuna01("teve vendas");
						
						p.setColuna01(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(1).getMonthValue()))));
						p.setColuna02(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(2).getMonthValue()))));
						p.setColuna03(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(3).getMonthValue()))));
						p.setColuna04(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(4).getMonthValue()))));
						p.setColuna05(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(5).getMonthValue()))));
						p.setColuna06(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(6).getMonthValue()))));
						p.setColuna07(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(7).getMonthValue()))));
						p.setColuna08(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(8).getMonthValue()))));
						p.setColuna09(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(9).getMonthValue()))));
						p.setColuna10(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(10).getMonthValue()))));
						p.setColuna11(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(11).getMonthValue()))));
						p.setColuna12(
								Formata.moeda(rs.getDouble(coluna = Integer.toString(hoje.plusMonths(12).getMonthValue()))));
						
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