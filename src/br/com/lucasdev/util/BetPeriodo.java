package br.com.lucasdev.util;

public class BetPeriodo {
	
	
	public String getPeriodo(int mes, String ano) {

		String periodo = "";

		switch (mes) {
		case 1:
			periodo = "'" + ano + "-01-01 00:00:00' and '" + ano + "-01-31 00:00:00'";
			break;
		case 2:
			periodo = "'" + ano + "-02-01 00:00:00' and '" + ano + "-02-28 00:00:00'";
			break;
		case 3:
			periodo = "'" + ano + "-03-01 00:00:00' and '" + ano + "-03-31 00:00:00'";
			break;
		case 4:
			periodo = "'" + ano + "-04-01 00:00:00' and '" + ano + "-04-30 00:00:00'";
			break;
		case 5:
			periodo = "'" + ano + "-05-01 00:00:00' and '" + ano + "-05-31 00:00:00'";
			break;
		case 6:
			periodo = "'" + ano + "-06-01 00:00:00' and '" + ano + "-06-30 00:00:00'";
			break;
		case 7:
			periodo = "'" + ano + "-07-01 00:00:00' and '" + ano + "-07-31 00:00:00'";
			break;
		case 8:
			periodo = "'" + ano + "-08-01 00:00:00' and '" + ano + "-08-31 00:00:00'";
			break;
		case 9:
			periodo = "'" + ano + "-09-01 00:00:00' and '" + ano + "-09-30 00:00:00'";
			break;
		case 10:
			periodo = "'" + ano + "-10-01 00:00:00' and '" + ano + "-10-31 00:00:00'";
			break;
		case 11:
			periodo = "'" + ano + "-11-01 00:00:00' and '" + ano + "-11-30 00:00:00'";
			break;
		case 12:
			periodo = "'" + ano + "-12-01 00:00:00' and '" + ano + "-12-31 00:00:00'";
			break;

		}

		return periodo;

	}


}
