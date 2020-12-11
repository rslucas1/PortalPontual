package br.com.lucasdev.modelo.relatorios;

public class PedidoProducao {
	private String dt_entrada, dt_inic_sepa, dt_baixaErp, nu_pedido, roteiro, status_entrega, motorista, distrito, bairro, frete, carro;
	private String nota;
	
	

	public String getDt_baixaErp() {
		return dt_baixaErp;
	}

	public void setDt_baixaErp(String dt_baixaErp) {
		this.dt_baixaErp = dt_baixaErp;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getDt_entrada() {
		return dt_entrada;
	}

	public void setDt_entrada(String dt_entrada) {
		this.dt_entrada = dt_entrada;
	}

	public String getDt_inic_sepa() {
		return dt_inic_sepa;
	}

	public void setDt_inic_sepa(String dt_inic_sepa) {
		this.dt_inic_sepa = dt_inic_sepa;
	}

	public String getNu_pedido() {
		return nu_pedido;
	}

	public void setNu_pedido(String nu_pedido) {
		this.nu_pedido = nu_pedido;
	}

	public String getRoteiro() {
		return roteiro;
	}

	public void setRoteiro(String roteiro) {
		this.roteiro = roteiro;
	}

	public String getStatus_entrega() {
		return status_entrega;
	}

	public void setStatus_entrega(String status_entrega) {
		this.status_entrega = status_entrega;
	}

	public String getMotorista() {
		return motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getFrete() {
		return frete;
	}

	public void setFrete(String frete) {
		this.frete = frete;
	}

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}
	
	

}
