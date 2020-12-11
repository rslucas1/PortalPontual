package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.ObjetivoVendedor;
import br.com.lucasdev.modelo.usuario.Usuario;

public class JdbcObjetivoVendedor {
	
	private Connection connectionSqlServer;
	private Connection connectionMySql;
	
	public JdbcObjetivoVendedor() {
		
			
		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			connectionMySql = new ConnectionFactory().getConnectionMySql();
						
		
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	public List<ObjetivoVendedor> getObjetivoVendedor(Usuario sessaoUsuario, String dataInicial, String dataFinal){
		
//		LocalDate dataFinal;
//		dataFinal = LocalDate.now();
		
//		LocalDate dataInicial = LocalDate.now();;
//		int diaAtual = dataInicial.getDayOfMonth();
//		dataInicial =  dataInicial.minusDays(diaAtual-1);
		String filtroSup="";
		
		System.err.println(sessaoUsuario.getPerfil());
		if(sessaoUsuario.getPerfil().equals("SUPERVISOR")){
			filtroSup="	and v.equipe_cd_vend_sup='"+sessaoUsuario.getCd_target()+"' \r\n";
			
		}
		
		List<ObjetivoVendedor> objetivoVendedor = new ArrayList<>();
		
		
		
		String sql_getEquipe="select distinct\r\n" + 
				"\r\n" + 
				"equipe_descricao, \r\n" + 
				"vendedor_cd_vend, \r\n" + 
				"vendedor_nome\r\n" + 
				"	from vi_aaa_planiha_de_setores v\r\n" + 
				"	\r\n" + 
				"	join vend_emp e\r\n" + 
				"	on v.vendedor_cd_vend = e.cd_vend \r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	where  \r\n" + 
				"	v.equipe_ativo = 1\r\n" + 
				"	and v.equipe_cd_equipe NOT IN ('1', 'PC98', 'PC85')\r\n" + 
				"	and v.vendedor_ativo=1\r\n" + 
				"	and e.cd_emp IN (13, 20)\r\n" + 
				"	and e.utiliza_palm_top=1\r\n" + 
				filtroSup + 
				"ORDER BY equipe_descricao";
				
				System.out.println("SQL Objetivo getEquipe  \n" +sql_getEquipe);
				
				
				
			String sql_getVendedor_fabricante ="select * from(\r\n" + 
					"SELECT \r\n" + 
					"p.cd_vend, \r\n" + 
					"\r\n" + 
					"f.descricao as FABRICANTE, \r\n" + 
					"CAST (sum(it.preco_unit * it.qtde)as  NUMERIC (12,2)) as valor \r\n" + 
					"\r\n" + 
					"FROM it_nota it\r\n" + 
					"\r\n" + 
					"JOIN nota n\r\n" + 
					"ON n.nu_nf = it.nu_nf\r\n" + 
					"\r\n" + 
					"JOIN ped_vda p\r\n" + 
					"ON p.nu_ped = n.nu_ped\r\n" + 
					"\r\n" + 
					"JOIN tp_ped tp\r\n" + 
					"on tp.tp_ped = p.tp_ped\r\n" + 
					"\r\n" + 
					"JOIN fabric f\r\n" + 
					"on f.cd_fabric=it.cd_fabric\r\n" + 
					"\r\n" + 
					"WHERE     \r\n" + 
					"	n.situacao IN ('AB', 'DP')\r\n" + 
					"	AND	n.tipo_nf = 'S' \r\n" + 
					"	AND	n.cd_emp  IN (13, 20)\r\n" + 
					"	AND	tp.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n" + 
					"	AND f.descricao IN ('BIC','CARTA FABRIL','KIMBERLY','BARUEL','PHISALIA','SKALA', 'ALFAPARF', 'EMBELLEZE', 'BEAUTY COLOR', 'HYPERA S/A', 'STEVITA', 'YPE', 'APOLO', 'SU BELLO')\r\n" + 
					"	AND n.dt_emis  BETWEEN '"+dataInicial+" 00:00:00' and '"+dataFinal+" 00:00:00'	\r\n" + 
					"\r\n" + 
					"group by p.cd_vend, \r\n" + 
					" \r\n" + 
					"n.nu_nf_emp_fat, f.descricao\r\n" + 
					"\r\n" + 
					"	 \r\n" + 
					") em_linha\r\n" + 
					"pivot (sum(Valor) for FABRICANTE IN ([BIC], [CARTA FABRIL],[KIMBERLY],[BARUEL],[PHISALIA],[SKALA],[ALFAPARF],[EMBELLEZE],[BEAUTY COLOR],[HYPERA S/A],[STEVITA],[YPE],[APOLO],[SU BELLO])) em_colunas\r\n" + 
					"ORDER BY 1";
			
			System.out.println("SQL Objetivo sql_getVendedor_fabricante  \n" +sql_getVendedor_fabricante);
			
			String QtdClienteCarteira = "select cd_vend as VENDEDOR, COUNT(cd_clien) as QUANTIDADE from vend_cli\r\n" + 
					"\r\n" + 
					"group by cd_vend";
			
			
			String mySqlObjetivoVend = "SELECT * from objetivovendedor";
			
			String vlfaturado = "SELECT \r\n" + 
					"p.cd_vend, \r\n" + 
					"\r\n" + 
					"CAST (sum(it.preco_unit * it.qtde)as  NUMERIC (12,2)) as valor \r\n" + 
					"\r\n" + 
					"FROM it_nota it\r\n" + 
					"\r\n" + 
					"JOIN nota n\r\n" + 
					"ON n.nu_nf = it.nu_nf\r\n" + 
					"\r\n" + 
					"JOIN ped_vda p\r\n" + 
					"ON p.nu_ped = n.nu_ped\r\n" + 
					"\r\n" + 
					"JOIN tp_ped tp\r\n" + 
					"on tp.tp_ped = p.tp_ped\r\n" + 
					"\r\n" + 
					"JOIN fabric f\r\n" + 
					"on f.cd_fabric=it.cd_fabric\r\n" + 
					"\r\n" + 
					"WHERE     \r\n" + 
					"	n.situacao IN ('AB', 'DP')\r\n" + 
					"	AND	n.tipo_nf = 'S' \r\n" + 
					"	AND	n.cd_emp  IN (13, 20)\r\n" + 
					"	AND	tp.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n" + 
					"	AND n.dt_emis  BETWEEN '"+dataInicial+" 00:00:00' and '"+dataFinal+" 00:00:00'	\r\n" + 
					"\r\n" + 
					"group by p.cd_vend"; 
			
			String ped_ab ="select  \r\n" + 
					"p.cd_vend Vendedor,\r\n" + 
					"sum(p.valor_tot) [Vl pedidos]\r\n" + 
					"\r\n" + 
					"from ped_vda p\r\n" + 
					"\r\n" + 
					"JOIN tp_ped tp\r\n" + 
					"on tp.tp_ped = p.tp_ped\r\n" + 
					"\r\n" + 
					"LEFT OUTER JOIN nota n\r\n" + 
					"on n.nu_ped=p.nu_ped\r\n" + 
					"\r\n" + 
					"where dt_cad BETWEEN '"+dataInicial+" 00:00:00' and '"+dataFinal+" 00:00:00' \r\n" + 
					"AND	tp.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n" + 
					"AND p.situacao NOT IN ('CA', 'DV')\r\n" + 
					"AND n.situacao IS NULL\r\n" + 
					"\r\n" + 
					"group by p.cd_vend";
			
			String pos_vend = "SELECT \r\n" + 
					"p.cd_vend, \r\n" + 
					"count (distinct p.cd_clien)\r\n" + 
					"\r\n" + 
					"FROM ped_vda p\r\n" + 
					"\r\n" + 
					"join nota n\r\n" + 
					"on n.nu_ped=p.nu_ped\r\n" + 
					"\r\n" + 
					"JOIN tp_ped tp\r\n" + 
					"on tp.tp_ped = p.tp_ped\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"WHERE     \r\n" + 
					"	n.situacao IN ('AB', 'DP')\r\n" + 
					"	AND	n.tipo_nf = 'S' \r\n" + 
					"	AND	n.cd_emp  IN (13, 20)\r\n" + 
					"	AND	tp.tp_ped IN ('BE', 'BF', 'BS', 'TR', 'VC', 'VE', 'VP', 'VS', 'BP', 'BI', 'VB', 'SR','AS','IP','SL')\r\n" + 
					"	and P.dt_cad  BETWEEN '"+dataInicial+" 00:00:00' and '"+dataFinal+" 00:00:00'\r\n" + 
					"	--AND p.cd_vend='200AP028'\r\n" + 
					"\r\n" + 
					"	group by p.cd_vend\r\n" + 
					" \r\n" + 
					"	order by p.cd_vend\r\n" + 
					"";
		
		try {
			PreparedStatement stmt_1 = this.connectionSqlServer.prepareStatement(sql_getEquipe);
			ResultSet rs_1 = stmt_1.executeQuery();
				while(rs_1.next()) {
					ObjetivoVendedor registro = new ObjetivoVendedor();			
					
					registro.setEquipe(rs_1.getString(1));
					registro.setCd_venda(rs_1.getString(2));
					registro.setNome(rs_1.getString(3));
					
					objetivoVendedor.add(registro);
					
				} //FIM LAÇO WHILE EQUIPE
				
				
			PreparedStatement stmt_2 = this.connectionSqlServer.prepareStatement(sql_getVendedor_fabricante);	
			ResultSet rs_2 = stmt_2.executeQuery();
				while(rs_2.next()) {
					for(ObjetivoVendedor p : objetivoVendedor) {
						if(rs_2.getString(1).equals(p.getCd_venda())) {
							p.setIndustria1(rs_2.getString(2));
							p.setIndustria2(rs_2.getString(3));
							p.setIndustria3(rs_2.getString(4));
							p.setIndustria4(rs_2.getString(5));
							p.setIndustria5(rs_2.getString(6));
							p.setIndustria6(rs_2.getString(7));
							p.setIndustria7(rs_2.getString(8));
							p.setIndustria8(rs_2.getString(9));
							p.setIndustria9(rs_2.getString(10));
							p.setIndustria10(rs_2.getString(11));
							p.setIndustria11(rs_2.getString(12));
							p.setIndustria12(rs_2.getString(13));
							p.setIndustria13(rs_2.getString(14));
							p.setIndustria14(rs_2.getString(15));
							
							break;
						}//fim IF
						
					}//FIM FOR
					
				}//FIM While
				
				
				PreparedStatement stmt_3 = this.connectionSqlServer.prepareStatement(QtdClienteCarteira);
				ResultSet rs_3 = stmt_3.executeQuery();
				
				while(rs_3.next()){
					for (ObjetivoVendedor p : objetivoVendedor) {
						if(rs_3.getString(1).equals(p.getCd_venda())) {
							p.setQdadeCarteira(rs_3.getInt(2));
							break;
						}
						
					}
					
				}
				
				PreparedStatement stmt_4 = this.connectionMySql.prepareStatement(mySqlObjetivoVend);
				ResultSet rs_4 = stmt_4.executeQuery();
				
				while(rs_4.next()){
					for (ObjetivoVendedor p : objetivoVendedor) {
						if(rs_4.getString(1).equals(p.getCd_venda())) {
							p.setVlObjetivo(rs_4.getDouble(2));
							break;
						}
						
					}
					
				}
				
				
				PreparedStatement stmt_5 = this.connectionSqlServer.prepareStatement(vlfaturado);
				ResultSet rs_5 = stmt_5.executeQuery();
				
				while(rs_5.next()){
					for (ObjetivoVendedor p : objetivoVendedor) {
						if(rs_5.getString(1).equals(p.getCd_venda())){
							p.setVl_faturado(rs_5.getDouble(2));
						}
					}
					
				}
				
				PreparedStatement stmt_6 = this.connectionSqlServer.prepareStatement(ped_ab);
				ResultSet rs_6 = stmt_6.executeQuery();
				
				while(rs_6.next()){
					for (ObjetivoVendedor p : objetivoVendedor) {
						if(rs_6.getString(1).equals(p.getCd_venda())){
							p.setVl_aberto(rs_6.getDouble(2));
						}
					}
					
				}
				
				PreparedStatement stmt_7 = this.connectionSqlServer.prepareStatement(pos_vend);
				ResultSet rs_7 = stmt_7.executeQuery();
				
				while(rs_7.next()){
					for (ObjetivoVendedor p : objetivoVendedor) {
						if(rs_7.getString(1).equals(p.getCd_venda())){
							p.setPositivacao(rs_7.getInt(2));
							
						}
					}
					
				}
				
				for (ObjetivoVendedor p : objetivoVendedor) {
					
					double soma = p.getVl_faturado()+p.getVl_aberto();
					soma = Math.round(soma*100); //ARREDONDAMENTP
					soma = soma/100; //ARREDONDAMENTO
					p.setVl_total(soma);
					
					
					
					if(p.getQdadeCarteira()!=0) {
						p.setPerc_positivado((p.getPositivacao()*100)/p.getQdadeCarteira());
					
					}
					if(p.getVlObjetivo()!=0) {
						double valor = ((p.getVl_faturado()*100)/p.getVlObjetivo());
						int i = (int) valor;
						p.setPerc_fat(i);
					}
					
				}
							
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
		
				
		return objetivoVendedor;
		
	}
	
	public void atualizaObjetivo(String cd_venda, String vlObjetivo) {
		
		String existeVendedor = "select * from objetivovendedor where codVend='"+cd_venda+"';";
		String updateVendedor = "update objetivovendedor set vlObjetivo=" + vlObjetivo +" where codVend='"+cd_venda+"';";
		String insertVendedor = "insert into objetivovendedor (codVend, vlObjetivo) values('"+cd_venda+"', "+vlObjetivo+");";	
		System.out.println("TESTE... "+existeVendedor);
		System.out.println("TESTE... "+updateVendedor);
		System.out.println("TESTE... "+insertVendedor);
		
		
		try{
			PreparedStatement stmt = this.connectionMySql.prepareStatement(existeVendedor);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("caiu no IF");
				try {
					System.out.println("UPDATE... "+updateVendedor);
					PreparedStatement stmtUpdate = this.connectionMySql.prepareStatement(updateVendedor);
					stmtUpdate.execute();
					System.out.println("UPDATE... "+updateVendedor);
					
				}catch(SQLException e) {
					throw new RuntimeException(e);
					
				}
				
			}else {
				System.out.println("caiu no Else");
				PreparedStatement stmtInsert = this.connectionMySql.prepareStatement(insertVendedor);
				stmtInsert.execute();
				System.out.println("INSERT... "+insertVendedor);
								
			}
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
	}

}
