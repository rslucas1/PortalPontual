package br.com.lucasdev.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formata {
    public static String moeda(double valor){
        return NumberFormat.getCurrencyInstance().format(valor);
    }
    
    public static String data(String dataRecebida) {
    	
    	DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate dataLocaldate = LocalDate.parse(dataRecebida,formato);
    	      	    	
    	return dataLocaldate.format(formatadorBarra);
    }
    
    public static String dateTime(String dataRecebida) {
    	
    	DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    	
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate dataLocaldate = LocalDate.parse(dataRecebida,formato);
    	      	    	
    	return dataLocaldate.format(formatadorBarra);
    }
    
    

}
