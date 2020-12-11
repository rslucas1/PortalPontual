package br.com.lucasdev.util;

public class TrataVetorInSql {
	
	public String vetorTratado(String[] segmentos) {
		if(segmentos==null) {
			
			return "todas";
		}else {
		
		
			/* TRANTANDO OS VENDEDORES PARA PASSAR PARA O SELECT */
			String segmentosTratadosSql = "";
			int tamanhoLista = segmentos.length;
			int i = 1;
	
			for (String s : segmentos) {
				String registro= "'"+s+"'";
				if (i < tamanhoLista) {
					segmentosTratadosSql +=  registro + ", ";
				} else {
					segmentosTratadosSql += registro;
				}
	
				i++;
			}
			
			System.out.println(segmentosTratadosSql);
		
			/* TRANTANDO OS VENDEDORES PARA PASSAR PARA O SELECT */
			
			
			return segmentosTratadosSql;
		}

	}
}
