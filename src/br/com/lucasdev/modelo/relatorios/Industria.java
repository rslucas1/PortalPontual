package br.com.lucasdev.modelo.relatorios;

public class Industria {
	
	
	public Industria() {
		
	}

	public Industria(String cod_industria, String desc_industria) {
		super();
		this.cod_industria = cod_industria;
		this.desc_industria = desc_industria;
	}

	private String cod_industria, desc_industria;

	public String getCod_industria() {
		return cod_industria;
	}

	public void setCod_industria(String cod_industria) {
		this.cod_industria = cod_industria;
	}

	public String getDesc_industria() {
		return desc_industria;
	}

	public void setDesc_industria(String desc_industria) {
		this.desc_industria = desc_industria;
	}
	
		

}
