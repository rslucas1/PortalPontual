package br.com.lucasdev.modelo.positivacao;

public class ClienteDetalhado extends Cliente {
	private String tp_Cli, fantasia, cgc_cpf, grupoCli, logradouro, numero, 
	cep, segmento, area, bairro, municipio, distrito, vendedor, situacao, dtUltimaCompra;

	
	
	
	public String getDtUltimaCompra() {
		return dtUltimaCompra;
	}

	public void setDtUltimaCompra(String dtUltimaCompra) {
		this.dtUltimaCompra = dtUltimaCompra;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTp_Cli() {
		return tp_Cli;
	}

	public void setTp_Cli(String tp_Cli) {
		this.tp_Cli = tp_Cli;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fatasia) {
		this.fantasia = fatasia;
	}

	public String getCgc_cpf() {
		return cgc_cpf;
	}

	public void setCgc_cpf(String cgc_cpf) {
		this.cgc_cpf = cgc_cpf;
	}

	public String getGrupoCli() {
		return grupoCli;
	}

	public void setGrupoCli(String grupoCli) {
		this.grupoCli = grupoCli;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	
	

}
