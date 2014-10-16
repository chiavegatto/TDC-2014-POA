package br.com.calcdesempenho;

import java.text.ParseException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import br.com.calcdesempenho.dao.AbastecimentoDAO;
import br.com.calcdesempenho.util.Util;

public class MesesAnteriores extends Activity {
	protected EditText editTextDataInicio;
	protected EditText editTextDataFinal;
	protected AbastecimentoDAO dao;
	protected static int DATE_DIALOG_ID;
	protected Button buttonPesquisar;
	protected RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meses_anteriores);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup_desempenho_abastecimentos);
		editTextDataInicio = (EditText) findViewById(R.id.editText_data_inicio);
		editTextDataFinal = (EditText) findViewById(R.id.editText_data_final);

		colocaDataInicial();

		editTextDataInicio.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				DATE_DIALOG_ID = 0;
				showDialog(DATE_DIALOG_ID);

			}
		});
		editTextDataFinal.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				DATE_DIALOG_ID = 1;
				showDialog(DATE_DIALOG_ID);

			}
		});

		editTextDataFinal
				.setOnFocusChangeListener(new View.OnFocusChangeListener() {

					@SuppressWarnings("deprecation")
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						DATE_DIALOG_ID = 1;
						showDialog(DATE_DIALOG_ID);
						// editTextDataFinal.clearFocus();
					}
				});
	}

	@Override
	protected void onResume() {
		super.onResume();
		final Button buttonPesquisa = (Button) findViewById(R.id.button_pesquisar);
		final AbastecimentoDAO dao = new AbastecimentoDAO(MesesAnteriores.this);

		buttonPesquisa.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton_desempenho) {
					if (!dao.getQuantidadeVezesAbastecimento(getDataInicio(),getDataFinal()).equals("0")) {
						Intent irParaMeseAnterioresDesempenho = new Intent(MesesAnteriores.this,MesesAnterioresDesempenho.class);
						irParaMeseAnterioresDesempenho.putExtra("dataInicial",getDataInicio());
						irParaMeseAnterioresDesempenho.putExtra("dataFinal",getDataFinal());
						startActivity(irParaMeseAnterioresDesempenho);
					} else {
						AlertDialog.Builder dialogo = new AlertDialog.Builder(MesesAnteriores.this);
						dialogo.setMessage("Nenhum abastecimento encontrado.")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												dialog.cancel();
											}
										});
						dialogo.show();
					}

				} else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton_abastecimentos) {
					if (!dao.getListaAbastecimento(getDataInicio(),getDataFinal()).isEmpty()) {
						Intent irParaMeseAnterioresAbastecimento = new Intent(MesesAnteriores.this,MesesAnterioresAbastecimentos.class);
						irParaMeseAnterioresAbastecimento.putExtra("dataInicial", getDataInicio());
						irParaMeseAnterioresAbastecimento.putExtra("dataFinal",getDataFinal());
						startActivity(irParaMeseAnterioresAbastecimento);
					} else {
						AlertDialog.Builder dialogo = new AlertDialog.Builder(MesesAnteriores.this);
						dialogo.setMessage("Nenhum abastecimento encontrado.")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												dialog.cancel();
											}
										});
						dialogo.show();
					}
				}

			}
		});

		dao.close();
	}

	private void colocaDataInicial() {
		String dataInicio = "";
		dataInicio = dataInicio.concat("01").concat(Util.retornaMesAnoAtual());
		String dataFinal = Util.getDateTime();

		editTextDataInicio.setText(dataInicio);
		editTextDataFinal.setText(dataFinal);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar calendario = Calendar.getInstance();

		int ano = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);

		switch (id) {
		case 0:
			return new DatePickerDialog(this, mDateSetListenerInicio, ano, mes,
					dia);
		case 1:
			return new DatePickerDialog(this, mDateSetListenerFinal, ano, mes,
					dia);
		default:
			break;
		}

		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListenerInicio = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			String mes = String.valueOf(monthOfYear + 1);
			String dia = String.valueOf(dayOfMonth);

			if (mes.length() < 2) {
				mes = "0" + mes;
			}

			if (dia.length() < 2) {
				dia = "0" + dia;
			}

			String data = dia + "/" + mes + "/" + String.valueOf(year);
			editTextDataInicio.setText(data);
		}

	};

	private DatePickerDialog.OnDateSetListener mDateSetListenerFinal = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			String mes = String.valueOf(monthOfYear + 1);
			String dia = String.valueOf(dayOfMonth);

			if (mes.length() < 2) {
				mes = "0" + mes;
			}

			if (dia.length() < 2) {
				dia = "0" + dia;
			}

			String data = dia + "/" + mes + "/" + String.valueOf(year);
			editTextDataFinal.setText(data);

		}

	};

	public String getDataInicio() {
		String data = editTextDataInicio.getText().toString();
		try {
			data = Util.converteExibicaoTelaParaFormatoBanco(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;

	}

	public String getDataFinal() {
		String data = editTextDataFinal.getText().toString();
		try {
			data = Util.converteExibicaoTelaParaFormatoBanco(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflateratividade = getMenuInflater();
		inflateratividade.inflate(R.menu.meses_anteriores, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		selecionaOpcoesDoMenu(item);
		return super.onOptionsItemSelected(item);
	}

	private void selecionaOpcoesDoMenu(MenuItem item) {
		finish();
	}
}
