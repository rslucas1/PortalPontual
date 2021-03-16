package br.com.lucasdev.modelo.relatorios;

import java.util.Date;

public class RelatorioDespesa {

	private int cd_centroCusto, cd_contaContabil, cd_forn, dt_vencimento;
	private String centroCusto, contaContabil, razaoSocial, estrutura, reponsavelPgtp;
	private double valor;
	
	
	
	public int getCd_centroCusto() {
		return cd_centroCusto;
	}
	public void setCd_centroCusto(int cd_centroCusto) {
		this.cd_centroCusto = cd_centroCusto;
	}
	public int getCd_contaContabil() {
		return cd_contaContabil;
	}
	public void setCd_contaContabil(int cd_contaContabil) {
		this.cd_contaContabil = cd_contaContabil;
	}
	public int getCd_forn() {
		return cd_forn;
	}
	public void setCd_forn(int cd_forn) {
		this.cd_forn = cd_forn;
	}
	public String getCentroCusto() {
		return centroCusto;
	}
	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}
	public String getContaContabil() {
		return contaContabil;
	}
	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getEstrutura() {
		return estrutura;
	}
	public void setEstrutura(String estrutura) {
		this.estrutura = estrutura;
	}
	public String getReponsavelPgtp() {
		return reponsavelPgtp;
	}
	public void setReponsavelPgtp(String reponsavelPgtp) {
		this.reponsavelPgtp = reponsavelPgtp;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getDt_vencimento() {
		return dt_vencimento;
	}
	public void setDt_vencimento(int dt_vencimento) {
		this.dt_vencimento = dt_vencimento;
	}
	
	
	 
	
}
