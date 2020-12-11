package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.AcompanhamentoPedidos;
import br.com.lucasdev.modelo.usuario.Usuario;

public class JdbcAcompanhamentoPedidos {
	
	private Connection connectionSqlServer;
	private Connection connectionMySql;
	
	public JdbcAcompanhamentoPedidos() {
		
			
		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			connectionMySql = new ConnectionFactory().getConnectionMySql();
						
		
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<AcompanhamentoPedidos> getAcompanhamentoPedidos(Usuario sessaoUsuario, String dataInicial, String dataFinal){
		List<AcompanhamentoPedidos> acompanhamentoPedidos = new ArrayList<>();
		
		String sql1 ="--select top 1000 * from ped_vda p\r\n" + 
				"--where p.cd_emp=13\r\n" + 
				"--order by p.nu_ped\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"select\r\n" + 
				"p.nu_ped,\r\n" + 
				"p.cd_clien,\r\n" + 
				"c.nome,\r\n" + 
				"tp.descricao,\r\n" + 
				"CONVERT(VARCHAR(10), p.dt_cad, 103) AS 'ENTRADA ERP',\r\n" + 
				"DATEDIFF (day, p.dt_cad, ev.dt_criacao) as 'intervalo1',\r\n" + 
				"CONVERT (VARCHAR(10),ev.dt_criacao, 103) AS 'ENTRADA WMS',\r\n" + 
				"DATEDIFF (day, ev.dt_criacao, n.dt_emis) as 'intervalo2',\r\n" + 
				"CONVERT (VARCHAR(10),n.dt_emis, 103) AS 'DT FATURAMENTO',\r\n" + 
				"n.nu_nf_emp_fat,\r\n" + 
				"ir.nu_rom,\r\n" + 
				"PG.descricao,\r\n" + 
				"CONVERT (VARCHAR(10),tit.dt_venc_orig, 103) AS 'DT FATURAMENTO',\r\n" + 
				"tit.dt_venc_orig\r\n" + 
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
				"\r\n" + 
				"where p.cd_emp IN (13,20)\r\n" + 
				"\r\n" + 
				"order by p.nu_ped desc\r\n" + 
				"\r\n" + 
				"--and p.dt_cad between '2020-07-01' and '2020-07-31'\r\n" + 
				"\r\n" + 
			
				"";
		
		
		try {
			PreparedStatement stmt_1 = this.connectionSqlServer.prepareStatement(sql1);
			ResultSet rs_1 = stmt_1.executeQuery();
			
			while(rs_1.next()){
				
				AcompanhamentoPedidos registro = new AcompanhamentoPedidos();
				
				registro.setNuPedido(rs_1.getString(1));
				registro.setCliente(rs_1.getString(2)+"-"+rs_1.getString(3));
				registro.setTpPedido(rs_1.getString(4));
				registro.setEntradaErp(rs_1.getString(5));
				registro.setIntervalo1(rs_1.getString(6)!=null ? rs_1.getString(6) : "-");
				registro.setEntradaWms(rs_1.getString(7));
				registro.setIntervalo2(rs_1.getString(8)!=null ? rs_1.getString(8) : "-");
				registro.setDtFaturamento(rs_1.getString(9));
				registro.setNota(rs_1.getString(10)!=null ? rs_1.getString(10) : "-");
				registro.setCarga(rs_1.getString(11)!=null ? rs_1.getString(11) : "-");
				registro.setCondPgto(rs_1.getString(12));
				registro.setIntervalo3("-");
				registro.setCarregado("-");
				registro.setIntervalo4("-");
				registro.setEntregue("-");
				registro.setDiasVencer("-");
				
				acompanhamentoPedidos.add(registro);
				
				
			}
			
			
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
		
		
		
		return acompanhamentoPedidos;
	}
	

}
