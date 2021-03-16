package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.relatorios.RelatorioDespesa;

public class JdbcDespesas {

	private Connection connectionMySql;
	private Connection connectionSqlServer;

	public JdbcDespesas() {
		try {
			connectionMySql = new ConnectionFactory().getConnectionMySql();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

	public List<RelatorioDespesa> getFornecedores() {
		List<RelatorioDespesa> fornecedores = new ArrayList<>();

		try {

			PreparedStatement stmt = this.connectionMySql.prepareStatement("select * from configdespesa");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				RelatorioDespesa registro = new RelatorioDespesa();
				
				registro.setCd_forn(rs.getInt(1));
				registro.setDt_vencimento(rs.getInt(2));
				registro.setEstrutura(rs.getString(3));
				registro.setReponsavelPgtp(rs.getString(4));
				
				fornecedores.add(registro);

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

		return fornecedores;
	}
}
