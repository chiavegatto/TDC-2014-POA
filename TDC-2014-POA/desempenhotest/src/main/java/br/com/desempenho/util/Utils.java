package br.com.desempenho.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;

import com.robotium.solo.Solo;

public class Utils {

	@SuppressLint("SimpleDateFormat")
	public static Calendar converteStringEmCalendar(String data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(formato.parse(data));
		return cal;
	}
	
	public static void preencherDatePickerCriadoEmTempoExecucao(Calendar cal, Solo solo){
		solo.setDatePicker(0, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
	    solo.clickOnText("Done");
	}
}
