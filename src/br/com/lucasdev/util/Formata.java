package br.com.lucasdev.util;

import java.text.NumberFormat;
import java.util.Date;

public class Formata {
    public static String moeda(double valor){
        return NumberFormat.getCurrencyInstance().format(valor);
    }
    


}
