package br.com.lucasdev.modelo.relatorios;

import br.com.lucasdev.modelo.positivacao.Cliente;

public class PedidosDiario extends Cliente {
	private int numPedido;
	private String cdVendedor;
	private String nomeVendedor;
	private double valor;
	private String status;
	private String situacao;
	private int qtdPedido;
	private double valorTotal;
	
	
	
	public int getQtdPedido() {
		return qtdPedido;
	}
	public void setQtdPedido(int qtdPedido) {
		this.qtdPedido = qtdPedido;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}
	public String getCdVendedor() {
		return cdVendedor;
	}
	public void setCdVendedor(String cdVendedor) {
		this.cdVendedor = cdVendedor;
	}
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	
}
