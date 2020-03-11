package br.com.lucasdev.util;

import java.text.NumberFormat;

public class Formata {
    public static String moeda(double valor){
        return NumberFormat.getCurrencyInstance().format(valor);
    }

}
