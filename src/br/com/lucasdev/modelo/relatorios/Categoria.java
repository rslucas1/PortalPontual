package br.com.lucasdev.modelo.relatorios;

public class Categoria {

	private String cod_categoria;
	private String desc_categoria;
	private boolean ativo;
	public String getCod_categoria() {
		return cod_categoria;
	}
	public void setCod_categoria(String cod_categoria) {
		this.cod_categoria = cod_categoria;
	}
	public String getDesc_categoria() {
		return desc_categoria;
	}
	public void setDesc_categoria(String desc_categoria) {
		this.desc_categoria = desc_categoria;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
