package br.com.lucasdev.util;

import java.util.List;

import br.com.lucasdev.modelo.relatorios.Vendedor;

public class TrataListaVendedoresInSql {
	
	public String vendedoresTratadosSql(List<Vendedor> vendedores) {
		
		/* TRANTANDO OS VENDEDORES PARA PASSAR PARA O SELECT */
		String vendedoresTratadosSql = "";
		int tamanhoLista = vendedores.size();
		int i = 1;

		for (Vendedor v : vendedores) {
			String registro= "'"+v.getCd_venda()+"'";
			if (i < tamanhoLista) {
				vendedoresTratadosSql +=  registro + ", ";
			} else {
				vendedoresTratadosSql += registro;
			}

			i++;
		}
	
		/* TRANTANDO OS VENDEDORES PARA PASSAR PARA O SELECT */
		
		
		
		return vendedoresTratadosSql;
	}

}
