package br.com.lucasdev.modelo.relatorios;

public class Tracking {
	private int qtdPedido;
	private double valor;
	private String ultPed, valorMoeda;
	
	
	public int getQtdPedido() {
		return qtdPedido;
	}
	public String getValorMoeda() {
		return valorMoeda;
	}
	public void setValorMoeda(String valorMoeda) {
		this.valorMoeda = valorMoeda;
	}
	public void setQtdPedido(int qtdPedido) {
		this.qtdPedido = qtdPedido;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getUltPed() {
		return ultPed;
	}
	public void setUltPed(String ultPed) {
		this.ultPed = ultPed;
	}
		
}
