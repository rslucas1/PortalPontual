package br.com.lucasdev.modelo.relatorios;

public class ConfrontoEstoque {

	private String erpCodProd, erpDescProd, erpDescFacricante, erpUnidEstoq;
	private int erpEmpSpBroker, erpEmpPontual, erpEmpTotal;

	private String wmsEan, wmsUn, wmsCodProd;
	private int wmsTotal;

	private String divergencia;

	public String getErpCodProd() {
		return erpCodProd;
	}

	public void setErpCodProd(String erpCodProd) {
		this.erpCodProd = erpCodProd;
	}

	public String getErpDescProd() {
		return erpDescProd;
	}

	public void setErpDescProd(String erpDescProd) {
		this.erpDescProd = erpDescProd;
	}

	public String getErpDescFacricante() {
		return erpDescFacricante;
	}

	public void setErpDescFacricante(String erpDescFacricante) {
		this.erpDescFacricante = erpDescFacricante;
	}

	public String getErpUnidEstoq() {
		return erpUnidEstoq;
	}

	public void setErpUnidEstoq(String erpUnidEstoq) {
		this.erpUnidEstoq = erpUnidEstoq;
	}

	public int getErpEmpSpBroker() {
		return erpEmpSpBroker;
	}

	public void setErpEmpSpBroker(int erpEmpSpBroker) {
		this.erpEmpSpBroker = erpEmpSpBroker;
	}

	public int getErpEmpPontual() {
		return erpEmpPontual;
	}

	public void setErpEmpPontual(int erpEmpPontual) {
		this.erpEmpPontual = erpEmpPontual;
	}

	public int getErpEmpTotal() {
		return erpEmpTotal;
	}

	public void setErpEmpTotal(int erpEmpTotal) {
		this.erpEmpTotal = erpEmpTotal;
	}

	public String getWmsEan() {
		return wmsEan;
	}

	public void setWmsEan(String wmsEan) {
		this.wmsEan = wmsEan;
	}

	public String getWmsUn() {
		return wmsUn;
	}

	public void setWmsUn(String wmsUn) {
		this.wmsUn = wmsUn;
	}



	public String getWmsCodProd() {
		return wmsCodProd;
	}

	public void setWmsCodProd(String wmsCodProd) {
		this.wmsCodProd = wmsCodProd;
	}

	public int getWmsTotal() {
		return wmsTotal;
	}

	public void setWmsTotal(int wmsTotal) {
		this.wmsTotal = wmsTotal;
	}

	public String getDivergencia() {
		return divergencia;
	}

	public void setDivergencia(String divergencia) {
		this.divergencia = divergencia;
	}

	@Override
	public String toString() {
		return "ConfrontoEstoque [erpCodProd=" + erpCodProd + ", erpDescProd=" + erpDescProd + ", erpDescFacricante="
				+ erpDescFacricante + ", erpUnidEstoq=" + erpUnidEstoq + ", erpEmpSpBroker=" + erpEmpSpBroker
				+ ", erpEmpPontual=" + erpEmpPontual + ", erpEmpTotal=" + erpEmpTotal + ", wmsEan=" + wmsEan
				+ ", wmsUn=" + wmsUn + ", wmsCodProd=" + wmsCodProd + ", wmsTotal=" + wmsTotal + ", divergencia="
				+ divergencia + "]";
	}



}
