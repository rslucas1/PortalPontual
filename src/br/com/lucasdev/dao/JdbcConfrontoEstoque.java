package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.ConfrontoEstoque;
import br.com.lucasdev.modelo.relatorios.ProdutoFator;

public class JdbcConfrontoEstoque {

	public Connection connectionSqlServer, connectionOracle, connectionMysql;

	public JdbcConfrontoEstoque() {

		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			connectionOracle = new ConnectionFactory().getConnectionOracle();
			connectionMysql = new ConnectionFactory().getConnectionMySql();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<ConfrontoEstoque> ConfrontoEstoque(boolean divergente) {

		List<ConfrontoEstoque> estoqueWMS = new ArrayList<>();
		List<ConfrontoEstoque> confrontoEstoque = new ArrayList<>();
		List<ProdutoFator> produtoFator = new ArrayList<>();
		List<ConfrontoEstoque> estoqueDivergente = new ArrayList<>();
		
		String sqlConsultaFator = "SELECT * from fatorconversao";

		try {

			PreparedStatement stmtConsultaMySql = this.connectionMysql.prepareStatement(sqlConsultaFator);
			ResultSet rs_3 = stmtConsultaMySql.executeQuery();

			while (rs_3.next()) {

				ProdutoFator registro = new ProdutoFator();

				registro.setCodProd(rs_3.getString(1));
				registro.setFator(rs_3.getInt(2));

				produtoFator.add(registro);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

		String sqlConsultaErp = "select * from(\r\n" + "	SELECT \r\n" + "\r\n"
				+ "		e.cd_prod as produto, --coluna\r\n" + "		p.descricao,\r\n"
				+ "		f.descricao as 'fabricante',\r\n" + "		p.cd_linha,\r\n"
				+ "		l.descricao as 'linha',\r\n" + "		p.unid_est,\r\n"
				+ "		e.cd_emp as cd_emp, --pívot\r\n" + "		e.qtde as qtd -- valor\r\n" + "		\r\n" + "\r\n"
				+ "	FROM estoque e\r\n" + "	\r\n" + "	JOIN PRODUTO p\r\n" + "	ON p.cd_prod=e.cd_prod\r\n" + "	\r\n"
				+ "	JOIN fabric f\r\n" + "	ON f.cd_fabric=p.cd_fabric\r\n" + "\r\n" + "	JOIN LINHA l\r\n"
				+ "	ON l.cd_linha=p.cd_linha\r\n" + "\r\n" + "	where\r\n" + "		e.cd_local='central' and\r\n"
				+ "		((e.cd_emp in (13) and e.qtde >= 0 )or (e.cd_emp in (20) and e.qtde >= 0 ))\r\n" + "\r\n"
				+ "		--INSUMOS\r\n" + "\r\n"
				+ "		and p.cd_linha NOT IN ('300', '3501','3584','7452','DYSD','DISR','DES','MC','3511','HL', 'GERA')\r\n"
				+ "		and f.descricao NOT IN ('CROWN LIFT TRUCKS DO BRASIL','DATASUPRI',\r\n"
				+ "		'DELL COMPUTADORES','ESPACIAL SUP DE ESCRITORIO','GAVETEIRO COMERCIO IMPORT','OKI COMERCIO DE MATERIAIS DE','OUTROS')\r\n"
				+ "\r\n"
				+ "and f.descricao NOT in ('3 CORACOES', 'ADRIA', 'LOG ACESS','ALL PDV', 'PONTUAL CENTER', 'ENVELOPACK IND DE EMB ESP LTDA', 'ESG COM ELETRONICO LTDA', 'ESPACIAL SUPR. DE ESCR. E INF', 'EUROART COMERCIO DE PRODUTOS D', 'FAST SHOP S.A', 'FIAT LUX', 'FINO PLAC ESTIQUETAS', 'FORTCALHAS ENFGENHARIA', 'GAVETEIRO COMERCIO IMPO TACAO', 'GENERICO', 'GENOMMA', 'GENOMMA OTC', 'GIMBA SUPRICORP SUPRIMENTOS LT', 'GIOVANNA BABY', 'GOLDEN YATA COM LTDA', 'GRACE CANDIO AUTO PEÇAS ME', 'GRAFICA TABOAO LTDA.', 'GRAFICA VENDA HOJE', 'GRANADO', 'GSIM COMUNICACAO VISUAL LTDA.', 'GSK', 'GUABI', 'HARIBO', 'HAYAMAX DIST PROD ELETRONICOS', 'HIPERMARCA PDV DIST.LOG.PROD.H', 'HYPERMARCAS DPP', 'HYPERMARCAS HBS', 'HYPERMARCAS S/A - OTC', 'I. GRAPHICS COM VISUAL EIRELI', 'IMPACTO COM. DIST. DE MAT ELET', 'INDUSTRIA DE EMB TOCANTINS', 'INFINTY DO B. PROD. ELET. LTDA', 'INGRAM MICRO BRASIL LTDA', 'INTERALUM', 'INTERMAX SERV GRAFICOS E INT', 'ITAMBE', 'JDE', 'JOHNSON E JOHNSON', 'JOSE LUIZ ALVES ELETRONICOS ME', 'KALUNGA', 'KANECHOM', 'LINEA SUCRALOSE', 'LOG ACESS DIST E LOGIST. PROD.', 'LOG DISTRIBUIDORA DE PROD. E H', 'LOG-IN DISTRIBUIÇAO', 'LOREAL PACK/MAYB', 'LOREAL PROF', 'MAGAZINE LUIZA', 'MAGAZINE LUIZA S/A', 'MARIA ZILDINETE DE MORAIS ME', 'MARTINS COMERCIO E SERVIÇOS', 'MILI', 'MITSUI ALIMENTOS', 'NIELY GOLD', 'NIVEA', 'NOBO- NOVO BRASIL EIRELI ME', 'O & M IMPORTACAO E COMERCIO DE', 'PERNOD', 'PIRACANJUBA', 'PLUSCARDS SOL TEC IDENT EIRELI', 'POSTALL TRANSPORTE E ARMAZENAG', 'PRIMME MARK COM DE ROUPAS LTDA', 'PRINT SUPPLIES COM SERV DE SUP', 'PULCOR IND E COM PRODS', 'R.L. CAVALCANTI EIRELI EPP', 'RBM WINNER COM DE EQUIP E SUPR', 'RBM WINNER COM. DE EQUIP INF', 'RBM WINNER COM.INFORMATICA', 'RCG IND. METALURGICA LTDA.', 'RECKITT', 'RECKITT FARMA', 'RECKITT OTC', 'REIMON', 'RIO BRANCO COM. E IND. DE PAPE', 'RN COMERCIO VAREJISTA S.A', 'ROBINSON CRUSOÉ', 'SALDAO DA INFORMATICA LTDA', 'SANFAT COMERCIAL LTDA ME', 'SANSUNG', 'SC JOHNSON', 'SCA DO BRASIL IND E COM LTDA', 'SCANSOURCE BRASIL', 'SCARCOM COM.INFORM.LTDA.', 'SND DIST DE PROD DE INF S/A', 'SND DISTRIBUIÇÃO DE PRODUTS DE', 'SONY', 'SP BEBS PONTUAL CENTER', 'SP MERCHAN LOGIST. ARMAZEM', 'SP PG PAULISTANA DIST E LOG PR', 'SPINA MAT. CONTRUÇÃO', 'SPINA MATERIAIS DE CONSTRUÇAO', 'SPINA MATERIAIS DE CONSTRUÇÃO', 'STOROPACK DO BRASIL EMB DE', 'SUAVETEX', 'SUPRICORP SUPRIMENTOS LTDA.', 'SUSSEX', 'TARGET MEIO DE PAGAMENTO S/A', 'TRIO', 'UNIBRASIL DIST LOG DE PROD HIG', 'UNICHARM', 'UNILEVER', 'VALORIZA SOLUCOES EMPRESARIAIS', 'VILLEOFFICE SERV GRAFICOS INF', 'WARETECH TECN E INF LTDA', 'WARETECH TECNOLOGIA E INF LTDA', 'WARETECH TECNOLOGIA E INFORMAT', 'WS INTERCOM SIT. SEG. ELETRONI', 'ZENATUR TRANSPORTES DE CARGA', 'ZENATUR TRANSPORTES DE CARGAS' \r\n"
				+ ")\r\n" + "\r\n" + "		--TESTE\r\n" + "		--and p.cd_prod='942818'\r\n" + "		\r\n"
				+ "		group by e.cd_prod, p.descricao, f.descricao,p.cd_linha, l.descricao, p.unid_est,e.cd_emp,e.qtde\r\n"
				+ "\r\n" + "\r\n" + "\r\n" + "	 \r\n" + ") em_linha\r\n"
				+ "pivot (sum(qtd) for cd_emp IN ([20],[13])) em_colunas\r\n" + "ORDER BY 3";

		System.out.println(sqlConsultaErp);

		try {

			PreparedStatement stmtConsultaErp = this.connectionSqlServer.prepareStatement(sqlConsultaErp);
			ResultSet rs_1 = stmtConsultaErp.executeQuery();

			while (rs_1.next()) {

				ConfrontoEstoque registro = new ConfrontoEstoque();

				registro.setErpCodProd(rs_1.getString(1));
				registro.setErpDescProd(rs_1.getString(2));
				registro.setErpDescFacricante(rs_1.getString(3));
				registro.setErpUnidEstoq(rs_1.getString(6));
				registro.setErpEmpSpBroker(rs_1.getInt(7));
				registro.setErpEmpPontual(rs_1.getInt(8));
				registro.setErpEmpTotal(registro.getErpEmpPontual() + registro.getErpEmpSpBroker());

				confrontoEstoque.add(registro);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

		String consEstWMS = "SELECT \r\n" + "--QUANTEN.BEREICH ,\r\n" + "--QUANTEN.PLATZ ,\r\n"
				+ "QUANTEN.ID_ARTIKEL ,\r\n" + "\r\n" + "--ARTIKEL.BEZ_1 , \r\n" + "ARTIKEL.CODE_EAN ,\r\n"
				+ "ARTIKEL.EINH_MNG,\r\n" + "QUANTEN.STAT_QK,\r\n" + "\r\n" + "--CASE QUANTEN.KNZ_IN_BEW\r\n"
				+ "--    WHEN '1' THEN 'S'\r\n" + "--     ELSE 'N'\r\n" + "--    END AS MOVIMENTACAO,\r\n" + "    \r\n"
				+ "--QUANTEN.CHARGE, \r\n" + "--QUANTEN.NR_LE_1,\r\n" + "--QUANTEN.TYP_LE_1,\r\n"
				+ "sum(QUANTEN.MNG_FREI) AS \"TOTAL DISP\"\r\n" + "--QUANTEN.MNG_RES_AUF\r\n"
				+ "--QUANTEN.MNG_ZUGANG,\r\n" + "--to_char(QUANTEN.DATUM_VERFALL,'dd/mm/yyyy')  VALIDADE,\r\n"
				+ "--(QUANTEN.MNG_FREI + QUANTEN.MNG_RES_AUF) AS \"TOTAL\"\r\n" + "\r\n"
				+ "FROM AG_CORE.ARTIKEL, AG_CORE.QUANTEN \r\n" + "WHERE ARTIKEL.ID_ARTIKEL = QUANTEN.ID_ARTIKEL\r\n"
				+ "AND ARTIKEL.ID_KLIENT =QUANTEN.ID_KLIENT\r\n" + "AND QUANTEN.ID_KLIENT= '001'\r\n"
				+ "AND STAT_QK IN ('00')\r\n" + "--AND QUANTEN.ID_ARTIKEL like '%992545'\r\n"
				+ "AND QUANTEN.ID_ARTIKEL NOT like 'B%'\r\n" + "\r\n"
				+ "group by QUANTEN.ID_ARTIKEL , ARTIKEL.CODE_EAN ,ARTIKEL.EINH_MNG,QUANTEN.STAT_QK";

		try {

			PreparedStatement stmtConsultaWMS = this.connectionOracle.prepareStatement(consEstWMS);
			ResultSet rs_2 = stmtConsultaWMS.executeQuery();

			while (rs_2.next()) {

				ConfrontoEstoque registro = new ConfrontoEstoque();

				registro.setWmsCodProd(rs_2.getString(1).replaceAll("[^0-9]+", ""));
				registro.setWmsEan(rs_2.getString(2));
				registro.setWmsUn(rs_2.getString(3));
				registro.setWmsTotal(rs_2.getInt(5));

				estoqueWMS.add(registro);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

		for (ConfrontoEstoque c : confrontoEstoque) {
			for (ConfrontoEstoque w : estoqueWMS) {

				if (c.getErpCodProd().equals(w.getWmsCodProd())) {
					System.err.println("COMPARANDO O ERP: " + c.getErpCodProd() + " | WMS: " + w.getWmsCodProd());
					c.setWmsEan(w.getWmsEan());
					c.setWmsUn(w.getWmsUn());
					c.setWmsTotal(w.getWmsTotal());

					if (!c.getWmsUn().replaceAll("[^A-Z]+", "").equals(c.getErpUnidEstoq())) {
						System.out.println("comparando o fator: " + c.getWmsUn().replaceAll("[^A-Z]+", "") + "|"
								+ c.getErpUnidEstoq());

						for (ProdutoFator p : produtoFator) {
							if (w.getWmsCodProd().equalsIgnoreCase(p.getCodProd())) {
								System.out.println("Localizado na talela de con versão o Item :" + c.getErpCodProd());

								c.setWmsTotal(c.getWmsTotal() * p.getFator());
								c.setWmsUn(c.getErpUnidEstoq());
								break;

							}

						}

					}

					if (c.getErpEmpTotal() != c.getWmsTotal()) {
						c.setDivergencia("Divergente WMS: " + (c.getWmsTotal() - c.getErpEmpTotal()));

					}
					
					if (divergente == true && c.getDivergencia() != null) {
						estoqueDivergente.add(c);

					}

				}

				// System.out.println(w.getWmsUn().replaceAll("[^A-Z]+", ""));

			}

		

		}
		
		if (divergente == true) {
			return estoqueDivergente;

		}
		


		return confrontoEstoque;
	}

}
