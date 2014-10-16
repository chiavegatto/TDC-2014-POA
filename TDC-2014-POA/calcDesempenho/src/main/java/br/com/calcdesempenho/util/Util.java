package br.com.calcdesempenho.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String retornaMesAnoAtual() {
		DateFormat dateFormat = new SimpleDateFormat("/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String retornaAnoAtual() {
		DateFormat dateFormat = new SimpleDateFormat("/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String converteExibicaoTelaParaFormatoBanco(String data) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAlterada = formato.parse(data);
		SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
		data=formatoBanco.format(dataAlterada);
		return data;
		
	}
	
	public static String converteDataParaExibicaoGrid(String data) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date dataAlterada = formato.parse(data);
		SimpleDateFormat formatoBanco = new SimpleDateFormat("dd/MM/yyyy");
		data=formatoBanco.format(dataAlterada);
		return data;
	}
}
