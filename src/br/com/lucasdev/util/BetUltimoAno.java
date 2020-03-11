package br.com.lucasdev.util;

import java.time.LocalDate;

public class BetUltimoAno {
	
	public String getBetweenUltimoAno() {
		
		LocalDate dataInicial;
		LocalDate dataFinal;
		
		
		dataInicial = LocalDate.now().minusMonths(11);
		dataFinal = LocalDate.now();
		int diaAtual = dataInicial.getDayOfMonth();
		dataInicial = dataInicial.minusDays(diaAtual-1);
		
			
				
		return "'"+dataInicial+" 00:00:00'" + " AND '"+dataFinal+" 00:00:00'";
	}

}
