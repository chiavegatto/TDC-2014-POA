package br.com.calcdesempenho.entidades;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;

import android.widget.EditText;
import br.com.calcdesempenho.R;
import br.com.desempenho.util.Utils;

import com.robotium.solo.Solo;

public class Abastecimento {
	private String kmAbastecimento;
	private String valor;
	private String litros;
	private String data;
	
	public Abastecimento(Map<String, String> dadosAbastecimento) {
		this.kmAbastecimento = dadosAbastecimento.get("KM Abastecimento");
		this.valor = dadosAbastecimento.get("Valor");
		this.litros = dadosAbastecimento.get("Quantidade Litros");
	}
	
	public String getKmAbastecimento() {
		return kmAbastecimento;
	}
	public void setKmAbastecimento(String kmAbastecimento) {
		this.kmAbastecimento = kmAbastecimento;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getLitros() {
		return litros;
	}
	public void setLitros(String litros) {
		this.litros = litros;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public void preencherAbastecimento(Abastecimento abastecimento, Solo solo) {
		EditText editTextKMAbastecimento = (EditText) solo.getView(R.id.editText_km_abastecimento);
		solo.clearEditText(editTextKMAbastecimento);
		solo.typeText(editTextKMAbastecimento,abastecimento.getKmAbastecimento());

		EditText editTextQuantidadeLitros = (EditText) solo.getView(R.id.editText_quantidade_litros);
		solo.clearEditText(editTextQuantidadeLitros);
		solo.typeText(editTextQuantidadeLitros,	abastecimento.getLitros());
		
		EditText editTextValorAbastecimento = (EditText) solo.getView(R.id.editText_valor);
		solo.clearEditText(editTextValorAbastecimento);
		solo.typeText(editTextValorAbastecimento, abastecimento.getValor());
	}
	
	public static void preencherDataDoAbastecimento(String txDataAbastecimento, Solo solo) throws ParseException{
		Calendar cal = Utils.converteStringEmCalendar(txDataAbastecimento);
		EditText editTextDataAbastecimento =  (EditText) solo.getCurrentActivity().findViewById(R.id.editText_data);
		solo.clickOnView(editTextDataAbastecimento);
		Utils.preencherDatePickerCriadoEmTempoExecucao(cal, solo);
	}
	
}
