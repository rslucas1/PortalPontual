package br.com.lucasdev.modelo.relatorios;

import br.com.lucasdev.modelo.usuario.Usuario;

public class Vendedor{
	private String cd_venda;
	private String nome;
	private String cd_guerra;

	public String getCd_guerra() {
		return cd_guerra;
	}

	public void setCd_guerra(String cd_guerra) {
		this.cd_guerra = cd_guerra;
	}

	public String getCd_venda() {
		return cd_venda;
	}

	public void setCd_venda(String cd_venda) {
		this.cd_venda = cd_venda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
