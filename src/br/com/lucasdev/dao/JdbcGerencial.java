package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.DescontoFinanceiro;
import br.com.lucasdev.modelo.relatorios.Vendedor;
import br.com.lucasdev.util.BetPeriodo;

public class JdbcGerencial {
	
	private Connection connectionSqlServer;
	
	public JdbcGerencial (){
		
	try {
		connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
	}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<DescontoFinanceiro> getDescontoFinanceiroDet(int mes, String ano){
		List<DescontoFinanceiro> getDescontoFinanceiroDet = new ArrayList<>();
		String periodo = new BetPeriodo().getPeriodo(mes, ano);
		
		String sql="SELECT\r\n" + 
				"	ped.nu_ped NU_PEDIDO,\r\n" + 
				"	--ISNULL('R$ '+ CAST (nota.vl_desc_fin AS VARCHAR(10)),'-') VL_DESC_FINANCEIRO,\r\n" + 
				"	--ISNULL('R$ ' + CAST (nota.vl_desc_geral AS VARCHAR(10)),'-') VL_DESC_GERAL,	\r\n" + 
				"	'R$ ' + CAST (nota.vl_tot_nf AS VARCHAR(10)) VL_TOT_NOTA,\r\n" + 
				"	ISNULL('R$ '+ CAST (nota.vl_desc_fin AS VARCHAR(10)),'-') VL_DESC_FINANCEIRO,\r\n" + 
				"	--SUM((100*nota.vl_desc_fin)/nota.vl_tot_nf) as PERC,\r\n" + 
				"	CAST (SUM(nota.perc_desc_fin * 100) AS DECIMAL(10,1))as PERC,\r\n" + 
				"	nota.nu_nf_emp_fat  NU_NOTA, \r\n" + 
				"	convert (varchar, nota.dt_emis, 103) as EMISSAO_NOTA ,\r\n" + 
				"	cli.cd_clien COD_CLIENTE,\r\n" + 
				"	cli.nome NOME\r\n" + 
				"	--gcli.descricao GRUPO_CLI, gcli.cd_grupocli,\r\n" + 
				"	--nota.perc_desc_fin,\r\n" + 
				"	--nota.desc_nat_oper\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"FROM\r\n" + 
				"nota nota\r\n" + 
				"	JOIN empresa emp \r\n" + 
				"		ON nota.cd_emp = emp.cd_emp \r\n" + 
				"			JOIN ped_vda ped\r\n" + 
				"				ON nota.nu_ped = ped.nu_ped AND emp.cd_emp = ped.cd_emp\r\n" + 
				"					JOIN cliente cli\r\n" + 
				"						ON nota.cd_clien = cli.cd_clien \r\n" + 
				"							--JOIN grupocli gcli\r\n" + 
				"								--ON cli.cd_grupocli = gcli.cd_grupocli \r\n" + 
				"		\r\n" + 
				"WHERE\r\n" + 
				"	nota.situacao IN ('AB','FA') \r\n" + 
				"	AND nota.vl_desc_fin > 0 \r\n" + 
				"	AND nota.cd_emp = 13 \r\n" + 
				"	--AND ped.nu_ped = 938516\r\n" + 
				"	AND nota.vl_desc_fin > 0\r\n" + 
				"	AND nota.dt_emis between "+ periodo + 
				"	AND nota.desc_nat_oper NOT IN('BONIF/DOA플O/BRINDE', 'REM. BONIF/DOA플O/BRINDE')\r\n" + 
				"	AND nota.tipo_nf='S'\r\n" + 
				"	AND ped.tp_ped NOT IN ('NP','PE')\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"GROUP BY\r\n" + 
				"	ped.nu_ped,\r\n" + 
				"	nota.vl_desc_geral,\r\n" + 
				"	cli.cd_clien,	\r\n" + 
				"	nota.nu_nf_emp_fat,\r\n" + 
				"	nota.vl_desc_fin,\r\n" + 
				"	nota.vl_tot_nf,\r\n" + 
				"	cli.nome,\r\n" + 
				"	nota.dt_emis,\r\n" + 
				"	nota.perc_desc_fin\r\n" + 
				"\r\n" ;
		
		
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
										
			while(rs.next()) {
				DescontoFinanceiro registro = new DescontoFinanceiro();
				
				registro.setNumPedido(rs.getString(1));
				registro.setVlTotalNota(rs.getString(2));
				registro.setVlDescFin(rs.getString(3));
				registro.setPercDescFinan(rs.getString(4)+"%");
				registro.setNumNota(rs.getString(5));
				registro.setEmissaoNota(rs.getString(6));
				registro.setNomeCliente(rs.getString(7)+" - "+rs.getString(8));
				
				getDescontoFinanceiroDet.add(registro);
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		
		return getDescontoFinanceiroDet;
	}
	
	
	public double getDescontoFinanceiroTotal(int mes, String ano) {
		double valorTotal=0;
		String periodo = new BetPeriodo().getPeriodo(mes, ano);
		
		String sql="SELECT\r\n" + 
				"		 SUM(nota.vl_desc_fin)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"FROM\r\n" + 
				"nota nota\r\n" + 
				"	JOIN empresa emp \r\n" + 
				"		ON nota.cd_emp = emp.cd_emp \r\n" + 
				"			JOIN ped_vda ped\r\n" + 
				"				ON nota.nu_ped = ped.nu_ped AND emp.cd_emp = ped.cd_emp\r\n" + 
				"					JOIN cliente cli\r\n" + 
				"						ON nota.cd_clien = cli.cd_clien \r\n" + 
				"							--JOIN grupocli gcli\r\n" + 
				"								--ON cli.cd_grupocli = gcli.cd_grupocli \r\n" + 
				"		\r\n" + 
				"WHERE\r\n" + 
				"	nota.situacao IN ('AB','FA') \r\n" + 
				"	AND nota.vl_desc_fin > 0 \r\n" + 
				"	AND nota.cd_emp = 13 \r\n" + 
				"	--AND ped.nu_ped = 938516\r\n" + 
				"	AND nota.vl_desc_fin > 0\r\n" + 
				"	AND nota.dt_emis between "+ periodo + 
				"	AND nota.desc_nat_oper NOT IN('BONIF/DOA플O/BRINDE', 'REM. BONIF/DOA플O/BRINDE')\r\n" + 
				"	AND nota.tipo_nf='S'\r\n" + 
				"	AND ped.tp_ped NOT IN ('NP','PE')";
		
		
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
										
			while(rs.next()) {
				valorTotal=rs.getDouble(1);
				
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		return valorTotal;
	}
	
	
	
	
	

}
