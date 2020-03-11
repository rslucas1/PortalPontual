package br.com.lucasdev.modelo.usuario;

import java.util.Date;

public class Usuario {
	private int codigo;
	private String nome;
	private String email;
	private String senha;
	private String cd_target;
	private int ativo;
	private Date dt_cadastro;
	private String perfil;
	private int prim_acesso;
	private int ativoErp;
	
	
	
	
	public int getAtivoErp() {
		return ativoErp;
	}

	public void setAtivoErp(int ativoErp) {
		this.ativoErp = ativoErp;
	}

	public int getPrim_acesso() {
		return prim_acesso;
	}

	public void setPrim_acesso(int prim_acesso) {
		this.prim_acesso = prim_acesso;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Usuario() {
		
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	


	public String getCd_target() {
		return cd_target;
	}

	public void setCd_target(String cd_target) {
		this.cd_target = cd_target;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public Date getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}
	
	

}
