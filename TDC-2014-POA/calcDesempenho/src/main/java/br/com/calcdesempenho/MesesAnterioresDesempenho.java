package br.com.calcdesempenho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import br.com.calcdesempenho.dao.AbastecimentoDAO;

public class MesesAnterioresDesempenho extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meses_anteriores_desempenho);
		Intent intent = getIntent();
		String dataInicio = intent.getStringExtra("dataInicial");
		String dataFinal = intent.getStringExtra("dataFinal");
		AbastecimentoDAO dao= new AbastecimentoDAO(this);
		
		EditText editTextQuantidadeKM = (EditText) findViewById(R.id.editText_quantidade_km);
		editTextQuantidadeKM.setText(dao.getMaxKMAbastecimento(dataInicio, dataFinal) + " KM");
		
		EditText editTextQuantideLitros= (EditText) findViewById(R.id.editText_quantidade_litros);
		editTextQuantideLitros.setText(dao.getSomaQuantidadeLitros(dataInicio, dataFinal) + " Litros");
		
		EditText editTextQuantidadeAbastecimento = (EditText) findViewById(R.id.editText_quantidade_abastecimento);
		editTextQuantidadeAbastecimento.setText(dao.getQuantidadeVezesAbastecimento(dataInicio, dataFinal) + " Vez(es)");
		
		EditText editTextValorAbastecimento = (EditText) findViewById(R.id.editText_valor_abastecimento);
		editTextValorAbastecimento.setText("R$ " +dao.getValorAbastecimento(dataInicio, dataFinal));
		
		EditText editTextKMLitro= (EditText) findViewById(R.id.editText_km_litro);
		editTextKMLitro.setText(dao.getKMLitro(dataInicio, dataFinal) + " KM/Litro(s)");
		dao.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflateratividade = getMenuInflater();
		inflateratividade.inflate(R.menu.lista_abastecimento, menu);
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
