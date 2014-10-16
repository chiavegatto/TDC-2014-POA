package br.com.calcdesempenho;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;
import br.com.calcdesempenho.dao.AbastecimentoDAO;
import br.com.calcdesempenho.helper.CadastrarAbastecimentoHelper;
import br.com.calcdesempenho.modelo.Abastecimento;

public class CadastrarAbastecimento extends Activity {
	protected CadastrarAbastecimentoHelper abastecimentoHelper;
	protected static final int DATE_DIALOG_ID = 0;
	protected EditText dataAbastecimento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_abastecimento);

		dataAbastecimento = (EditText) findViewById(R.id.editText_data);
		//dataAbastecimento.addTextChangedListener(Mask.insert("####-##-##",dataAbastecimento));
		dataAbastecimento.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);

			}
		});
		
		dataAbastecimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				showDialog(DATE_DIALOG_ID);
				dataAbastecimento.clearFocus();
			}
		});
		
		abastecimentoHelper = new CadastrarAbastecimentoHelper(this);
		
		Intent intent = getIntent();
		final Abastecimento abastecimentoParaSerAlterado = (Abastecimento) intent.getSerializableExtra("abastecimentoClicado");

		
		Button buttonCadastrar = (Button) findViewById(R.id.button_cadastrar);
		if (abastecimentoParaSerAlterado !=null){
			buttonCadastrar.setText("Salvar");
			setTitle("Alterar Abastecimento");
			abastecimentoHelper.colocaAbastecimentoNaActivity(abastecimentoParaSerAlterado);
		}
		buttonCadastrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Abastecimento abastecimento = abastecimentoHelper.cadastraAbastecimento();

					AbastecimentoDAO dao = new AbastecimentoDAO(CadastrarAbastecimento.this);
					if (abastecimentoParaSerAlterado==null){
						dao.salvar(abastecimento);
						Toast.makeText(CadastrarAbastecimento.this,"Abastecimento cadastrado com sucesso.",Toast.LENGTH_SHORT).show();
						finish();
					}else{
						abastecimento.setId(abastecimentoParaSerAlterado.getId());
						dao.alterar(abastecimento);
						Toast.makeText(CadastrarAbastecimento.this,"Abastecimento alterado com sucesso.",Toast.LENGTH_SHORT).show();
						Intent irParaListagemDeAbastecimento = new Intent(CadastrarAbastecimento.this, ListaAbastecimento.class);
						startActivity(irParaListagemDeAbastecimento);
					}
					dao.close();
										
				} catch (Exception e) {
					Toast.makeText(CadastrarAbastecimento.this,
							"Preencha os campos obrigatórios.",Toast.LENGTH_SHORT).show();
				}

			}
		});
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar calendario = Calendar.getInstance();
		
		int ano = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		
		switch (id) {
		case DATE_DIALOG_ID:
			DatePickerDialog pickerInicio = new DatePickerDialog(this,  mDateSetListener, ano, mes,
					dia);
			return pickerInicio; 
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			String mes = String.valueOf(monthOfYear+1);
			String dia = String.valueOf(dayOfMonth);
			
			if (mes.length()<2){
				mes = "0"+mes;				
			}
			
			if (dia.length()<2){
				dia = "0"+dia;				
			}
			
			String data = dia + "/" + mes+ "/" + String.valueOf(year);
			dataAbastecimento.setText(data);
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflateratividade = getMenuInflater();
		inflateratividade.inflate(R.menu.cadastrar_abastecimento, menu);
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
