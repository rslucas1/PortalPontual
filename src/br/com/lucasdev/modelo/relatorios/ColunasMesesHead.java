package br.com.lucasdev.modelo.relatorios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ColunasMesesHead {

	private String coluna01, coluna02, coluna03, coluna04, coluna05, coluna06, coluna07, coluna08, coluna09, coluna10,
			coluna11, coluna12;

	public ColunasMesesHead() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MMM");

		coluna01 = hoje.plusMonths(1).format(formatador);
		coluna01 = coluna01.toUpperCase();
		coluna02 = hoje.plusMonths(2).format(formatador);
		coluna02 = coluna02.toUpperCase();
		coluna03 = hoje.plusMonths(3).format(formatador);
		coluna03 = coluna03.toUpperCase();
		coluna04 = hoje.plusMonths(4).format(formatador);
		coluna04 = coluna04.toUpperCase();
		coluna05 = hoje.plusMonths(5).format(formatador);
		coluna05 = coluna05.toUpperCase();
		coluna06 = hoje.plusMonths(6).format(formatador);
		coluna06 = coluna06.toUpperCase();
		coluna07 = hoje.plusMonths(7).format(formatador);
		coluna07 = coluna07.toUpperCase();
		coluna08 = hoje.plusMonths(8).format(formatador);
		coluna08 = coluna08.toUpperCase();
		coluna09 = hoje.plusMonths(9).format(formatador);
		coluna09 = coluna09.toUpperCase();
		coluna10 = hoje.plusMonths(10).format(formatador);
		coluna10 = coluna10.toUpperCase();
		coluna11 = hoje.plusMonths(11).format(formatador);
		coluna11 = coluna11.toUpperCase();
		coluna12 = hoje.plusMonths(12).format(formatador);
		coluna12 = coluna12.toUpperCase();
	}

	public String getColuna01() {
		return coluna01;
	}

	public void setColuna01(String coluna01) {
		this.coluna01 = coluna01;
	}

	public String getColuna02() {
		return coluna02;
	}

	public void setColuna02(String coluna02) {
		this.coluna02 = coluna02;
	}

	public String getColuna03() {
		return coluna03;
	}

	public void setColuna03(String coluna03) {
		this.coluna03 = coluna03;
	}

	public String getColuna04() {
		return coluna04;
	}

	public void setColuna04(String coluna04) {
		this.coluna04 = coluna04;
	}

	public String getColuna05() {
		return coluna05;
	}

	public void setColuna05(String coluna05) {
		this.coluna05 = coluna05;
	}

	public String getColuna06() {
		return coluna06;
	}

	public void setColuna06(String coluna06) {
		this.coluna06 = coluna06;
	}

	public String getColuna07() {
		return coluna07;
	}

	public void setColuna07(String coluna07) {
		this.coluna07 = coluna07;
	}

	public String getColuna08() {
		return coluna08;
	}

	public void setColuna08(String coluna08) {
		this.coluna08 = coluna08;
	}

	public String getColuna09() {
		return coluna09;
	}

	public void setColuna09(String coluna09) {
		this.coluna09 = coluna09;
	}

	public String getColuna10() {
		return coluna10;
	}

	public void setColuna10(String coluna10) {
		this.coluna10 = coluna10;
	}

	public String getColuna11() {
		return coluna11;
	}

	public void setColuna11(String coluna11) {
		this.coluna11 = coluna11;
	}

	public String getColuna12() {
		return coluna12;
	}

	public void setColuna12(String coluna12) {
		this.coluna12 = coluna12;
	}


}
