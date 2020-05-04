package br.com.lucasdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasdev.modelo.usuario.Usuario;

public class JdbcUsuarioDao {

	private Connection connectionMySql;
	private Connection connectionSqlServer;
	
	
	public JdbcUsuarioDao() {
		try {
			connectionMySql = new ConnectionFactory().getConnectionMySql();
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
	}
	
	public boolean exiteUsuario(Usuario usuario) {

		if (usuario == null) {
			throw new IllegalArgumentException("Usuario nao deve ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connectionMySql
					.prepareStatement("select * from usuarios where email = ? and senha = ?");
			
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			
			ResultSet rs = stmt.executeQuery();
			
			boolean encontrado = rs.next();
			
				if (encontrado) {				
					usuario.setNome(rs.getString("nome"));
					int codigoConvertido = Integer.parseInt(rs.getString("codigo"));
					usuario.setAtivo(rs.getInt("ativo"));
					usuario.setCodigo(codigoConvertido);
					usuario.setCd_target(rs.getString("cd_target"));
					usuario.setPerfil(rs.getString("perfil"));
					usuario.setPrim_acesso(rs.getInt("prim_acesso"));
					
//					System.out.println("Logado no sistema com o usuario " + usuario.getCodigo());
//					System.out.println("Vendedor " + usuario.getCd_target());
				}
				
			return encontrado;
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
		
			
	}
	
	public int ativoErp(String vendedor) {
		
		String sql = "select ve.utiliza_palm_top ativoerp from vend_emp ve\r\n" + 
				" where cd_emp=13 AND cd_vend='"+vendedor+"'";
		
		try {
			connectionSqlServer = new ConnectionFactory().getConnectionSqlServer();
			
			PreparedStatement stmt = connectionSqlServer.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			int ativoerp=0;
			
			while(rs.next()) {
				ativoerp = rs.getInt("ativoerp");
				
			}
			
			if(ativoerp==1) {
				return 1;
			}
			
			return 0;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Usuario> getUsuarios (){
		
		List<Usuario> usuarios = new ArrayList<>();
		String sql="SELECT codigo, nome, email, cd_target, perfil, ativo, prim_acesso FROM usuarios";
		
		try {
			PreparedStatement stmt = this.connectionMySql
					.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Usuario registro = new Usuario();
				registro.setCodigo(rs.getInt("codigo"));
				registro.setNome(rs.getString("nome"));
				registro.setEmail(rs.getString("email"));
				registro.setCd_target(rs.getString("cd_target"));
				registro.setPerfil(rs.getString("cd_target"));
				registro.setAtivo(rs.getInt("ativo"));
				registro.setPrim_acesso(rs.getInt("prim_acesso"));
				
				usuarios.add(registro);
			}
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return usuarios;
	}
	
		public boolean novoUsuario(Usuario usuario) {
			
			boolean cadastrado=false;
			LocalDate localDate = LocalDate.now();
			
			String hoje = localDate.toString();
			
			String sql ="INSERT into usuarios ("
					+ "ativo, "
					+ "nome, "
					+ "email, "
					+ "cd_target, "
					+ "dt_cadastro, "
					+ "perfil, "
					+ "prim_acesso, "
					+ "senha)\r\n" + 
					"	VALUES (1, ?,?,?,'"+hoje+"',?,0,'123456')";
		
			try {
							
				PreparedStatement stmt = this.connectionMySql
						.prepareStatement(sql);
				
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getEmail());
				stmt.setString(3, usuario.getCd_target());
				stmt.setString(4, usuario.getPerfil());				

				stmt.executeUpdate();
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
				
			}
			
			return false;
		}
		
		
		public boolean alteraSenha(int codigo, String novaSenha) {
			
			String sql ="UPDATE usuarios set prim_acesso=1, senha = '"+novaSenha+"' where codigo="+codigo;
		
			try {
							
				PreparedStatement stmt = this.connectionMySql
						.prepareStatement(sql);
				
				stmt.executeUpdate();
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
				
			}
			
			return false;
		}
	


		public void LogAcesso(Usuario sessaoUsuario) {
			LocalDate hoje = LocalDate.now();
			
			String sql = "insert INTO acessos (fk_codigo, cd_target, perfil, dt_acesso) "
					+ "VALUES ("+sessaoUsuario.getCodigo()+", '"+sessaoUsuario.getCd_target()+"', '"+sessaoUsuario.getPerfil()+"', '"+hoje+"')";
		
		
			try {
							
				PreparedStatement stmt = this.connectionMySql
						.prepareStatement(sql);
				
				stmt.executeUpdate();
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
				
			}
			
			System.out.println("Registrando log de acesso...\n"+sql);
		}

}
