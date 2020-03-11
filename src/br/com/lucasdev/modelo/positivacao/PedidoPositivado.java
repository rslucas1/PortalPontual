package br.com.lucasdev.modelo.positivacao;

public class PedidoPositivado {
	private int nu_pedido;
	private int cd_cliente;
	private String cd_vendedor;
	private String dt_pedido;
	public int getNu_pedido() {
		return nu_pedido;
	}
	public void setNu_pedido(int nu_pedido) {
		this.nu_pedido = nu_pedido;
	}
	public int getCd_cliente() {
		return cd_cliente;
	}
	public void setCd_cliente(int cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	public String getCd_vendedor() {
		return cd_vendedor;
	}
	public void setCd_vendedor(String cd_vendedor) {
		this.cd_vendedor = cd_vendedor;
	}
	public String getDt_pedido() {
		return dt_pedido;
	}
	public void setDt_pedido(String dt_pedido) {
		this.dt_pedido = dt_pedido;
	}
	
	
	

}
