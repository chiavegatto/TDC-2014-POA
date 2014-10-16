package br.com.calcdesempenho;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import br.com.calcdesempenho.adapter.ListaAbastecimentoAdapter;
import br.com.calcdesempenho.dao.AbastecimentoDAO;
import br.com.calcdesempenho.modelo.Abastecimento;

public class MesesAnterioresAbastecimentos extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_abastecimento);

		AbastecimentoDAO dao = new AbastecimentoDAO(this);
		carregaLista(dao);
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
	
	private void carregaLista(final AbastecimentoDAO dao) {
		Intent intent = getIntent();
		String dataInicio = intent.getStringExtra("dataInicial");
		String dataFinal = intent.getStringExtra("dataFinal");
		List<Abastecimento> abastecimentos = dao.getListaAbastecimento(dataInicio, dataFinal);
	
		ListaAbastecimentoAdapter listaAbastecimentoAdapter = new ListaAbastecimentoAdapter(
				this, abastecimentos);
		setListAdapter(listaAbastecimentoAdapter);
	}

}
