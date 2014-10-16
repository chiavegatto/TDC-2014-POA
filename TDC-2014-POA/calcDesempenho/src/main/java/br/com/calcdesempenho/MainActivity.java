package br.com.calcdesempenho;

import java.text.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import br.com.calcdesempenho.dao.AbastecimentoDAO;
import br.com.calcdesempenho.util.Util;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflateratividade = getMenuInflater();
		inflateratividade.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		selecionaOpcoesDoMenu(item);
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		prencheCamposActivity();
	}

	private void prencheCamposActivity() {
		EditText editTextQuantidadeKM = (EditText) findViewById(R.id.editText_quantidade_km);
		EditText editTextQuantideLitros = (EditText) findViewById(R.id.editText_quantidade_litros);
		EditText editTextQuantidadeAbastecimento = (EditText) findViewById(R.id.editText_quantidade_abastecimento);
		EditText editTextValorAbastecimento = (EditText) findViewById(R.id.editText_valor_abastecimento);
		EditText editTextKMLitro = (EditText) findViewById(R.id.editText_km_litro);
		AbastecimentoDAO dao = new AbastecimentoDAO(this);
		String dataInicio = null;
		String dataFinal = null;
		
		try {
			dataInicio = Util.converteExibicaoTelaParaFormatoBanco("01"+Util.retornaMesAnoAtual());
			dataFinal = Util.converteExibicaoTelaParaFormatoBanco(Util.getDateTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		if (dao.getMaxKMAbastecimento(dataInicio,dataFinal) != null){
			editTextQuantidadeKM.setText(dao.getMaxKMAbastecimento(dataInicio,dataFinal)+ " KM/Mês");			
		}
		else{
			editTextQuantidadeKM.setText("0"+ " KM/Mês");
		}
		
		if (dao.getSomaQuantidadeLitros(dataInicio,dataFinal) != null){
			editTextQuantideLitros.setText(dao.getSomaQuantidadeLitros(dataInicio,dataFinal)+ " Litro(s)/Mês");		
		}
		else{
			editTextQuantideLitros.setText("0.0"+ " Litro(s)/Mês");
		}
		
		editTextQuantidadeAbastecimento.setText(dao.getQuantidadeVezesAbastecimento(dataInicio,dataFinal) + " Vez(es)/Mês");
		
		if (dao.getValorAbastecimento(dataInicio,dataFinal)!=null){
			editTextValorAbastecimento.setText("R$ " + dao.getValorAbastecimento(dataInicio,dataFinal));
		}
		else{
			editTextValorAbastecimento.setText("R$ " + "0.0");
		}
		
		if (dao.getKMLitro(dataInicio, dataFinal)!=null){
			editTextKMLitro.setText(dao.getKMLitro(dataInicio, dataFinal) + " KM/Litro(s)/Mês");
		}
		else{
			editTextKMLitro.setText("0.0" + " KM/Litro(s)/Mês");
		}
		
		dao.close();
	}
	
	private void selecionaOpcoesDoMenu(MenuItem item) {
		int itemClicado = item.getItemId();

		switch (itemClicado) {
		case R.id.action_cadastrar_abastecimento:
			Intent irParaCadastrarAbastecimento = new Intent(this,CadastrarAbastecimento.class);
			startActivity(irParaCadastrarAbastecimento);
			break;
		case R.id.action_desempenho_meses_anteriores:
			Intent irParaMesesAnteriores = new Intent(this,MesesAnteriores.class);
			startActivity(irParaMesesAnteriores);
			break;
		case R.id.action_listar_abastecimentos:
			Intent irParaListaAbastecimento = new Intent(this,ListaAbastecimento.class);
			startActivity(irParaListaAbastecimento);
			break;
		default:
			break;
		}
	}

}
