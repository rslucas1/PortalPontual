package br.com.ontex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.dao.ConnectionFactory;

public class JbdcOntex {
	
	private Connection connectionSqlServer;
	
	public JbdcOntex (){
		
	try {
		connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
	}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Relatorio> getRelatorio(){
		List<Relatorio> relatorio = new ArrayList<>();
		
		String sql="select distinct \r\n" + 
				"\r\n" + 
				"n.cd_clien, c.nome, c.cgc_cpf, c.vl_lim_cred\r\n" + 
				", AVG( DATEDIFF(DAY, n.dt_emis, pg.dt_pgto )) as 'RECEBIMENTO'\r\n" + 
				"--n.nu_nf_emp_fat, n.dt_emis, n.vl_tot_nf\r\n" + 
				" \r\n" + 
				"from nota n\r\n" + 
				"\r\n" + 
				"join empresa e\r\n" + 
				"on n.cd_emp=e.cd_emp\r\n" + 
				"\r\n" + 
				"join cliente c\r\n" + 
				"on c.cd_clien=n.cd_clien\r\n" + 
				"\r\n" + 
				"join ped_vda p\r\n" + 
				"on p.nu_ped = n.nu_ped and p.cd_emp = e.cd_emp\r\n" + 
				"\r\n" + 
				"join titrec t\r\n" + 
				"on n.nu_nf_emp_fat = t.nu_tit_emp_fat\r\n" + 
				"\r\n" + 
				"join pg_trec pg\r\n" + 
				"on pg.nu_tit = t.nu_tit\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"where\r\n" + 
				"n.cd_emp=13 AND\r\n" + 
				"--n.dt_emis > '2019-10-01 00:00:00' AND \r\n" + 
				"N.dt_emis between '2019-10-01 00:00:00' and '2020-03-27 00:00:00' and\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"n.desc_nat_oper NOT IN('BONIF/DOAÇÃO/BRINDE', 'REM. BONIF/DOAÇÃO/BRINDE') AND\r\n" + 
				"n.tipo_nf='S' AND "+
				//"n.cd_clien='67' AND\r\n" + 
				"p.tp_ped NOT IN ('NP','PE','MD') and\r\n" + 
				"n.situacao not in ('CA', 'DV') and\r\n" + 
				"\r\n" + 
				"t.cd_emp=13\r\n" + 
				"\r\n" + 
				"group by n.cd_clien, c.nome, c.cgc_cpf, c.vl_lim_cred\r\n" 
					;
		
		
		
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
										
			while(rs.next()) {
				Relatorio registro = new Relatorio();
				
				registro.setCod(rs.getString(1));
				registro.setRazao(rs.getString(2));
				registro.setCnpj(rs.getString(3));
				registro.setLimite(rs.getString(4));
				registro.setRecebimento(rs.getString(5));
				registro.setVlaberto(titulosAVencer(registro.getCod()));
				registro.setVlvencido(titulosVencidos(registro.getCod()));
				
				
				relatorio.add(registro);
				
				
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
				
		return relatorio;
	}
	
	public String titulosVencidos(String cdcliente) {
		String registro="0";
		
		String sql="  select distinct\r\n" + 
				"  cd_clien, sum(t.valor) as 'valor'\r\n" + 
				"  \r\n" + 
				"  from titrec t\r\n" + 
				"  \r\n" + 
				"  where\r\n" + 
				"  \r\n" + 
				"  t.cd_emp=13 and\r\n" + 
				"  t.dt_emis >'2019-09-25 00:00:00' and\r\n" + 
				"  t.dt_venc_orig <= '2020-03-26 23:59:00' and\r\n" + 
				"  t.situacao='AB' and\r\n" + 
				"  t.cd_clien="+cdcliente+"\r\n" + 
				"\r\n" + 
				"  group by cd_clien";
		
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
										
			while(rs.next()) {
				
			registro=rs.getString(2);
			
				
			
				
				
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		return registro;
		
	}
	
	
	public String titulosAVencer(String cdcliente) {
		String registro="0";
		
		String sql="   select distinct\r\n" + 
				"	cd_clien, sum(t.valor) as 'valor'\r\n" + 
				"  \r\n" + 
				"   from titrec t\r\n" + 
				"  \r\n" + 
				"  where\r\n" + 
				"  \r\n" + 
				"  t.cd_emp=13 and\r\n" + 
				"  t.dt_venc_orig>'2020-03-27 00:00:00' and\r\n" + 
				"  t.situacao='AB' and\r\n" + 
				"  t.cd_clien="+cdcliente+"\r\n" + 
				"  \r\n" + 
				"   group by cd_clien";
		
		try {
			PreparedStatement stmt = this.connectionSqlServer.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
										
			while(rs.next()) {
				
			registro=rs.getString(2);
			
				
			}
							
		}catch(SQLException e) {
				throw new RuntimeException(e);
		}
		
		return registro;
		
	}

}
